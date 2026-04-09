package org.themoviedb.actor.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.themoviedb.actor.service.ActorService;
import org.themoviedb.actor.vo.request.WriteVO;

import jakarta.validation.Valid;

@Controller
public class ActorController {
	
	private static final Logger logger = LoggerFactory.getLogger(ActorController.class);

    @Autowired
    private ActorService actorService;
    
    @GetMapping("/actor/write")
    public String viewWritePage() {
        return "actor/write";
    }
    
    @PostMapping("/actor/write")
    public String doWriteAction(@Valid @ModelAttribute WriteVO writeVO, BindingResult bindingResult, Model model) {
        
        if(bindingResult.hasErrors()) {
            model.addAttribute("inputData", writeVO);
            return "actor/write";
        }
        
        Boolean createResult = this.actorService.createNewActor(writeVO);
        logger.debug("배우 등록 결과: {}", createResult);
        
        return "redirect:/actor/list";
    }
}
