package com.ktdsuniversity.edu.board.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ktdsuniversity.edu.board.service.BoardService;
import com.ktdsuniversity.edu.board.vo.BoardVO;
import com.ktdsuniversity.edu.board.vo.SearchResultVO;

@Controller
public class BoardController {
	
	/**
	 * 빈 컨테이너에 들어있는 객체 중 타입이 일치하는 객체를 할당 받는다.
	 */
	@Autowired
	private BoardService boardService;

	@GetMapping("/")
	public String viewListPage(Model model) {
		
		SearchResultVO searchResult = this.boardService.findBoardAll();
		
		// 게시글의 목록을 조회.
		List<BoardVO> list = searchResult.getResult();
		// 게시글의 개수 조회.
		int searchCount = searchResult.getCount();
		
		model.addAttribute("searchResult", list);
		model.addAttribute("searchCount", searchCount);
		
		return "board/list";
	}
	
	// 게시글 등록 화면 보여주는 EndPoint
	@GetMapping("/write")
	public String viewWritePage() {
		
		return "board/write";
	}
	
	@PostMapping("/write")
	public String doWriteAction() {
		
		return null;
	}
}
