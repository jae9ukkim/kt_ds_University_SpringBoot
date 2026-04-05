package org.themoviedb.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.themoviedb.members.service.MembersService;
import org.themoviedb.members.vo.MembersVO;
import org.themoviedb.members.vo.request.RegistVO;
import org.themoviedb.movie.vo.response.DuplicateResultVO;

import jakarta.validation.Valid;

@Controller
public class MembersController {

	@Autowired
	private MembersService membersService;
	
	@GetMapping("/regist")
	public String viewRegistPage() {
		
		return "members/regist";
	}
	
	@PostMapping("/regist")
	public String doRegistAction(@Valid @ModelAttribute RegistVO registVO, BindingResult bindingResult, Model model) {
	    
	    if(bindingResult.hasErrors()) {
	        model.addAttribute("inputData", registVO);
	        return "/regist";
	    }
		
	    boolean createResult = this.membersService.createNewMember(registVO);
	    
		return "rediret:/login";
	}
	
	@ResponseBody
	@GetMapping("/regist/check/duplicate/{email}")
	public DuplicateResultVO doCheckDuplicateEmailAction(@PathVariable String email) {
	    
	    MembersVO membersVO = this.membersService.findMemberByEmail(email);
	    
	    DuplicateResultVO result = new DuplicateResultVO();
	    result.setEmail(email);
	    result.setDuplicate(membersVO != null);
	    
	    return result;
	}
	

	@GetMapping("/login")
	public String viewLoginPage() {
	    
	    return "members/login";
	}
}
