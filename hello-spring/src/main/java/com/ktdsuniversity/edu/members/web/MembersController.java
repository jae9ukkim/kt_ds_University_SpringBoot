package com.ktdsuniversity.edu.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ktdsuniversity.edu.members.service.MembersService;
import com.ktdsuniversity.edu.members.vo.request.MembersVO;

@Controller
public class MembersController {

	@Autowired
	private MembersService membersService;
	
	@GetMapping("/regist")
	public String viewRegistPage() {
		
		return "members/regist";
	}

	@PostMapping("/regist")
	public String doRegistAction(MembersVO membersVO) {
		
		boolean createResult = membersService.createNewMember(membersVO);
		System.out.println("계정 생성 성공? " + createResult);
		
		return "redirect:/login";
	}
	
	
}
