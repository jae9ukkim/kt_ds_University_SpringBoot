package com.ktdsuniversity.edu.board.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.board.dao.BoardDao;
import com.ktdsuniversity.edu.board.enums.ReadType;
import com.ktdsuniversity.edu.board.vo.BoardVO;
import com.ktdsuniversity.edu.board.vo.request.UpdateVO;
import com.ktdsuniversity.edu.board.vo.request.WriteVO;
import com.ktdsuniversity.edu.board.vo.response.SearchResultVO;
import com.ktdsuniversity.edu.files.dao.FilesDao;
import com.ktdsuniversity.edu.files.helpers.MultipartFileHandler;

@Service
public class BoardServiceImpl implements BoardService {
	
	/**
	 * 빈 컨테이너에 들어있는 객체 중 타입이 일치하는 객체를 할당 받는다.
	 */
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private MultipartFileHandler multipartFileHandler;
	
	@Autowired
	FilesDao filesDao;

	@Override
	public SearchResultVO findBoardAll() {
		SearchResultVO result = new SearchResultVO();

		// 게시글 개수 조회
		int count = this.boardDao.selectBoardCount();
		result.setCount(count);

		if(count == 0) {
			return result;
		}
		
		// 게시 목록 조회
		List<BoardVO> list = this.boardDao.selectBoardList();
		result.setResult(list);
		
		return result;
	}

	@Override
	public boolean createNewBoard(WriteVO writeVO) {
		// dao => insert 요청
		// mybatis는 insert, update, delete를 수행했을 때
		// 영향을 받은 row의 수를 반환시킨다.
		// 예> insert ==> insert 된 row의 개수 반환
		//	  update ==> update 된 row의 개수 반환
		//	  delete ==> delete 된 row의 개수 반환
		int insertCount = this.boardDao.insertNewBoard(writeVO);
		System.out.println("생성된 게시글의 개수? " + insertCount);
		
		// 첨부파일 업로드
		List<MultipartFile> attachFiles = writeVO.getAttachFile();
		this.multipartFileHandler.upload(attachFiles, writeVO.getId());
		
		return insertCount == 1;
	}

	@Override
	public BoardVO findBoardByArticleId(String articleId, ReadType readType) {
		if(readType == ReadType.VIEW) {
			// 1. 조회수 증가
			int updateCount = this.boardDao.updateViewCntIncreaseById(articleId);
			System.out.println("조회수가 증가 게시글의 수: "+updateCount);
			
			if(updateCount == 0) {
				// 존재하지 않는 게시글을 조회하려 했다.
				return null;
//			실무에서는 아래와 같이 처리
//			throw new RuntimeException("존재하지 않는 게시글입니다.");
			}
		}
		
		// 2. 게시글 조회
		// Dao는 테이블 관점에서. 테이블에는 articleId가 없다.
		BoardVO board = this.boardDao.selectBoardById(articleId);
		
		// 조회한 게시글을 반환
		return board;
	}

	@Override
	public boolean deleteBoardById(String id) {
		int deleteCount = this.boardDao.deleteBoardById(id);
		
		// 삭제하려는 게시글에 첨부된 파일 목록을 가져온다.
		List<String> deleteTargets = this.filesDao.selectFilePathByFileGroupId(id);
		
		// 파일 목록이 존재하면, 모든 파일들을 제거한다.
		if(deleteTargets != null && deleteTargets.size() > 0) {
			for (String target : deleteTargets) {
				new File(target).delete();
			}
		}
		
		// 파일 목록을 제거한 이후에 "FILES" 테이블에서 해당 파일 정보를 모두 삭제한다.
		int deleteFileCount = this.filesDao.deleteFilesByFileGroupId(id);
		System.out.println("삭제한 파일 데이터의 수: " + deleteFileCount);
		
		return deleteCount == 1;
	}

	@Override
	public boolean updateBoardByArticleId(UpdateVO updateVO) {
		int updateCount = this.boardDao.updateBoardById(updateVO);
		
		// 선택한 파일들만 삭제
		if(updateVO.getDeleteFileNum() != null && updateVO.getDeleteFileNum().size() > 0) {
			// 선택한 파일들의 정보를 조회 --> 파일의 경로 --> 실제 파일을 제거
			List<String> deleteTargets = this.filesDao.selectFilePathByFileGroupIdAndFileNums(updateVO);
			// 선택한 파일들을 FILES 테이블에서 제거
			for (String target: deleteTargets) {
				new File(target).delete();
			}
			// 선택한 파일들을 FILES 테이블에서 제거
			int deleteCount = this.filesDao.deleteFilesByFileGroupIdAndFileNums(updateVO);
			System.out.println("삭제한 파일 데이터의 수: " + deleteCount);
		}
		
		// 업로드 파일 list 가져오기
		List<MultipartFile> attachFiles = updateVO.getAttachFile();
		this.multipartFileHandler.upload(attachFiles, updateVO.getId());
		
		return updateCount == 1;
	}

}
