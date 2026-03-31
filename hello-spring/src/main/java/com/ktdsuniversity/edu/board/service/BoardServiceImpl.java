package com.ktdsuniversity.edu.board.service;

import java.io.File;
import java.io.IOException;
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
import com.ktdsuniversity.edu.files.vo.request.UploadVO;

@Service
public class BoardServiceImpl implements BoardService {
	
	/**
	 * 빈 컨테이너에 들어있는 객체 중 타입이 일치하는 객체를 할당 받는다.
	 */
	@Autowired
	private BoardDao boardDao;

	@Autowired
	private FilesDao filesDao;

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
		if(attachFiles != null && attachFiles.size() > 0) {
			for(int i=0; i < attachFiles.size(); i++) {
				// 업로드한 파일이 서버컴퓨터의 파일 시스템에 저장되도록 한다.
				File storeFile = new File("C:\\uploadFiles", attachFiles.get(i).getOriginalFilename());
				// C:\\uploadFiles 폴더가 없으면 생성해라!
				if(!storeFile.getParentFile().exists()) {
					storeFile.getParentFile().mkdirs();
				}
				
				try {
					attachFiles.get(i).transferTo(storeFile);
					UploadVO uploadVO = new UploadVO();

					String filename = attachFiles.get(i).getOriginalFilename();
					String ext = filename.substring(filename.lastIndexOf(".") + 1);

					uploadVO.setFileNum(i + 1);
					// 새롭게 등록이 되는 게시글의 아이디를 지금은 알 수 없다.
					uploadVO.setFileGroupId(writeVO.getId()); 
					uploadVO.setObfuscateName(attachFiles.get(i).getOriginalFilename());
					uploadVO.setDisplayName(attachFiles.get(i).getOriginalFilename());
					uploadVO.setExtendName(ext);
					// 실제 경로의 파일의 크기
					uploadVO.setFileLength(storeFile.length());
					// 절대경로
					uploadVO.setFilePath(storeFile.getAbsolutePath());

					this.filesDao.insertAttachFile(uploadVO);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		// FILES 테이블에 첨부파일 데이터를 INSERT
		
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
		
		return deleteCount == 1;
	}

	@Override
	public boolean updateBoardByArticleId(UpdateVO updateVO) {
		int updateCount = this.boardDao.updateBoardById(updateVO);
		return updateCount == 1;
	}

}
