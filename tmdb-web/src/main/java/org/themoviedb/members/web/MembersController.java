package org.themoviedb.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.themoviedb.members.service.MembersService;

@Controller
public class MembersController {

	@Autowired
	private MembersService membersService;
	
	@GetMapping("/regist")
	public String viewRegistPage() {
		
		return "members/regist";
	}
	
	@PostMapping("/regist")
	public String doRegistAction() {
		
		return "";
	}
}
