package com.ktdsuniversity.edu.replies.service;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.common.utils.AuthUtils;
import com.ktdsuniversity.edu.common.utils.ObjectUtils;
import com.ktdsuniversity.edu.common.utils.SessionUtils;
import com.ktdsuniversity.edu.config.HelloSpringApiException;
import com.ktdsuniversity.edu.files.dao.FilesDao;
import com.ktdsuniversity.edu.files.helpers.MultipartFileHandler;
import com.ktdsuniversity.edu.files.vo.request.SearchFileGroupVO;
import com.ktdsuniversity.edu.replies.dao.RepliesDao;
import com.ktdsuniversity.edu.replies.vo.RepliesVO;
import com.ktdsuniversity.edu.replies.vo.request.CreateVO;
import com.ktdsuniversity.edu.replies.vo.request.UpdateVO;
import com.ktdsuniversity.edu.replies.vo.response.RecommendResultVO;
import com.ktdsuniversity.edu.replies.vo.response.SearchResultVO;
import com.ktdsuniversity.edu.replies.vo.response.UpdateResultVO;

import jakarta.validation.Valid;

@Service
public class RepliesServiceImpl implements RepliesService {

	private static final Logger logger = LoggerFactory.getLogger(RepliesServiceImpl.class);
	
	@Autowired
	private RepliesDao repliesDao;

	@Autowired
	private FilesDao filesDao;
	
	@Autowired
	private MultipartFileHandler multipartFileHandler;
	

	@Transactional
	@Override
	public RepliesVO createNewReply(CreateVO createVO) {
		
		String fileGroupId = this.multipartFileHandler.upload(createVO.getAttachFile());
		createVO.setFileGroupId(fileGroupId);
		
		int insertCount = this.repliesDao.insertNewReply(createVO);
		if(insertCount == 1) {
			RepliesVO insertResult = this.repliesDao.selectReplyByReplyId(createVO.getId());
			return insertResult;
		}
		return null;
	}

	@Override
	public SearchResultVO findRepliesByArticleId(String articleId) {

		SearchResultVO searchResultVO = new SearchResultVO();
		int count = this.repliesDao.selectRepliesCountByArticleId(articleId);
		searchResultVO.setCount(count);
		
		if(count > 0) {
			List<RepliesVO> searchList = this.repliesDao.selectRepliesByArticleId(articleId);
			searchResultVO.setResult(searchList);
		}
		return searchResultVO;
	}

	@Transactional
	@Override
	public RecommendResultVO increaseRecommendByReplyId(String replyId) {
		
		RepliesVO insertResult = this.repliesDao.selectReplyByReplyId(replyId);
		// 조회했는데 이미 지워진 경우
		if(ObjectUtils.isNotNull(insertResult)) {
			
		    String loginEmail = AuthUtils.getUsername();
		    boolean isAdminAccount = AuthUtils.hasAnyRole("RL-20260419-000001", "RL-20260419-000002");
		    
			if(isAdminAccount || loginEmail.equals(replyId) ) {
				throw new HelloSpringApiException("권한이 부족합니다."
						, HttpStatus.BAD_REQUEST.value(), "자신의 댓글은 추천할 수 없습니다.");
			}
		}
		
		RecommendResultVO recommendResult = new RecommendResultVO();
		recommendResult.setReplyId(replyId);
		
		int updateCount = this.repliesDao.updateRecommendByReplyId(replyId);
		logger.debug("추천수 증가 결과 {}", updateCount);
		
		if(updateCount == 1) {
			recommendResult.setRecommendCount(insertResult.getRecommendCnt());
		}
		
		return recommendResult;
	}

	@Transactional
	@Override
	public boolean deleteRecommendByReplyId(String replyId) {
		
		RepliesVO reply = this.repliesDao.selectReplyByReplyId(replyId);
		String loginEmail = AuthUtils.getUsername();
		boolean isAdminAccount  = AuthUtils.hasAnyRole("RL-20260419-000001", "RL-20260419-000002");
		
		if(!isAdminAccount && !loginEmail.equals(reply.getEmail())) {
			
			if(!SessionUtils.isMineResource(reply.getEmail())) {
				throw new HelloSpringApiException("권한이 부족합니다."
						, HttpStatus.BAD_REQUEST.value(), "자신의 댓글이 아닙니다.");
			}
		}
		int deleteCount = this.repliesDao.deleteRecommendByReplyId(replyId);
		logger.debug("삭제 결과 : {}", deleteCount);
		return deleteCount == 1;
	}

	@Override
	public RepliesVO findReplyByReplyId(String replyId) {
		return this.repliesDao.selectReplyByReplyId(replyId);
	}

	@Transactional
	@Override
	public UpdateResultVO updateReply(UpdateVO updateVO) {
		RepliesVO reply = this.repliesDao.selectReplyByReplyId(updateVO.getReplyId());
		if(ObjectUtils.isNotNull(reply)) {
			
		    String loginEmail = AuthUtils.getUsername();
		    boolean isAdminAccount = AuthUtils.hasAnyRole("RL-20260419-000001", "RL-20260419-000002");
		    
		    if(!isAdminAccount && !loginEmail.equals(reply.getEmail())) {
		        throw new HelloSpringApiException("권한이 부족합니다.", HttpStatus.BAD_REQUEST.value(), "자신의 댓글이 아닙니다.");
		    }

		}
		
		updateVO.setFileGroupId(reply.getFileGroupId());
		
		// 선택한 파일들만 삭제
		if(updateVO.getDelFileNum() != null && updateVO.getDelFileNum().size() > 0) {
			// 선택한 파일들의 정보를 조회 --> 파일의 경로 --> 실제 파일을 제거
			SearchFileGroupVO searchFileGroupVO = new SearchFileGroupVO();
			searchFileGroupVO.setDeleteFileNum(updateVO.getDelFileNum());
			searchFileGroupVO.setFileGroupId(updateVO.getFileGroupId());
			List<String> deleteTargets = this.filesDao.selectFilePathByFileGroupIdAndFileNums(searchFileGroupVO);
			// 선택한 파일들을 FILES 테이블에서 제거
			for (String target: deleteTargets) {
				new File(target).delete();
			}
			// 선택한 파일들을 FILES 테이블에서 제거
			int deleteCount = this.filesDao.deleteFilesByFileGroupIdAndFileNums(searchFileGroupVO);
			logger.debug("삭제한 파일 데이터의 수: {}", deleteCount);
		}
		
		// 업로드 파일 list 가져오기
		List<MultipartFile> attachFiles = updateVO.getNewAttachFile();
		
		// 첨부파일 업로드
		String fileGroupId = updateVO.getFileGroupId();
		if (fileGroupId == null || fileGroupId.length() == 0) {
			fileGroupId = this.multipartFileHandler.upload(attachFiles);
			updateVO.setFileGroupId(fileGroupId);
		} else {
			this.multipartFileHandler.upload(attachFiles, updateVO.getFileGroupId());
		}
		
		int updateCount = this.repliesDao.updateReplyByReplyId(updateVO);

		UpdateResultVO result = new UpdateResultVO();
		result.setReplyId(updateVO.getReplyId());
		result.setUpdate(updateCount == 1);
		
		return result;
	}
}
