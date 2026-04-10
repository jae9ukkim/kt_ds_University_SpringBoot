package com.ktdsuniversity.edu.replies.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.config.HelloSpringApiException;
import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.replies.service.RepliesService;
import com.ktdsuniversity.edu.replies.vo.RepliesVO;
import com.ktdsuniversity.edu.replies.vo.request.CreateVO;
import com.ktdsuniversity.edu.replies.vo.request.UpdateVO;
import com.ktdsuniversity.edu.replies.vo.response.RecommendResultVO;
import com.ktdsuniversity.edu.replies.vo.response.SearchResultVO;
import com.ktdsuniversity.edu.replies.vo.response.UpdateResultVO;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class RepliesController {

	private static final Logger logger = LoggerFactory.getLogger(RepliesController.class);
	
	@Autowired
	private RepliesService repliesService;
	
	// AJAX(API) 요청 / 반환	
	@ResponseBody // 반환 데이터 ==> JSON
	@PostMapping("/api/replies")
	public RepliesVO doCreateNewReplyAction(@Valid @RequestBody CreateVO createVO, // JSON 요청 데이터 ==> 객체
			BindingResult bindingResult, @SessionAttribute("__LOGIN_DATA__") MembersVO loginMember) {
		
		if(bindingResult.hasErrors()) {
			// bindingResult.getAllErrors();
			List<FieldError> errors = bindingResult.getFieldErrors();// member 변수의 validation 결과
			// return type이 RepliesVO인데 맞지 않음
			throw new HelloSpringApiException("파라미터가 충분하지 않습니다", HttpStatus.BAD_REQUEST.value(), errors);
		}
		
		createVO.setEmail(loginMember.getEmail());
		
		logger.debug("reply: {}", createVO.getReply());
		logger.debug("email: {}", createVO.getEmail());
		logger.debug("articleId: {}", createVO.getArticleId());
		logger.debug("parentReplyId: {}", createVO.getParentReplyId());
		
		RepliesVO createResult = this.repliesService.createNewReply(createVO);
		logger.debug("{}", createResult);
		
		return createResult;
	}

	@ResponseBody
	@PostMapping("/api/replies-with-file")
	public RepliesVO doCreateNewReplyWithFileAction(@Valid CreateVO createVO, 
			BindingResult bindingResult, @SessionAttribute("__LOGIN_DATA__") MembersVO loginMember) {
		
		if(bindingResult.hasErrors()) {
			// bindingResult.getAllErrors();
			List<FieldError> errors = bindingResult.getFieldErrors();// member 변수의 validation 결과
			// return type이 RepliesVO인데 맞지 않음
			throw new HelloSpringApiException("파라미터가 충분하지 않습니다", HttpStatus.BAD_REQUEST.value(), errors);
		}
		
		createVO.setEmail(loginMember.getEmail());
		
		logger.debug("reply: {}", createVO.getReply());
		logger.debug("email: {}", createVO.getEmail());
		logger.debug("articleId: {}", createVO.getArticleId());
		logger.debug("parentReplyId: {}", createVO.getParentReplyId());
		
		RepliesVO createResult = this.repliesService.createNewReply(createVO);
		logger.debug("{}", createResult);
		
		return createResult;
				
	}
	
	@ResponseBody
	@GetMapping("/api/replies/{articleId}")
	public SearchResultVO getRepliesList(@PathVariable String articleId) {
		
		SearchResultVO searchResult = this.repliesService.findRepliesByArticleId(articleId);
		
		return searchResult;
	}
	
	@ResponseBody
	@GetMapping("/api/replies/recommend/{replyId}")
	public RecommendResultVO doRecommendAction(@PathVariable String replyId) {
	
		RecommendResultVO recommendResult = this.repliesService.increaseRecommendByReplyId(replyId);
		
		return recommendResult;
	}

	@ResponseBody
	@GetMapping("/api/replies/delete/{replyId}")
	public Map<String,String> doDeleteRecommendAction(@PathVariable String replyId) {
				
		Map<String, String> resultMap = new HashMap<>();
		boolean deleteResult = this.repliesService.deleteRecommendByReplyId(replyId);
		if(deleteResult) {
			resultMap.put("replyId", replyId);
		}
		
		return resultMap;
	}
	
	@ResponseBody
	@PostMapping("/api/replies/{replyId}")
	public UpdateResultVO doUpdateReplyByReplyId(@PathVariable String replyId
			, @Valid UpdateVO updateVO, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			throw new HelloSpringApiException("파라미터가 충분하지 않습니다"
					, HttpStatus.BAD_REQUEST.value(), errors);
		}
		
		updateVO.setReplyId(replyId);
		UpdateResultVO updateResult = this.repliesService.updateReply(updateVO);
		
		return updateResult;
	}
}
