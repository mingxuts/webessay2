package com.webessay.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.webessay.model.Messages;
import com.webessay.model.MessagesRepository;

@RequestMapping("/auditmsgs/**")
@Controller
public class Auditmsgs {
	
	@Autowired
	private MessagesRepository repo;

    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping(value = "/index")
    public String index(Model uiModel) {    	
    	Boolean hasAdudit = false;
    	Messages msg = new Messages();
		List<Messages> msglist = repo.findByHasAdudit(hasAdudit);
		uiModel.addAttribute("msglist", msglist);
        return "auditmsgs/index";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model uiModel, @RequestParam Integer id){
    	Messages msg = repo.findOne(id);
    	Integer msgid = msg.getId();
    	uiModel.addAttribute("msgid", msgid);
    	
    	String text = msg.getText();
    	uiModel.addAttribute("oldtext", text);
    	
    	String auditText = msg.getAuditText();
    	uiModel.addAttribute("auditText", auditText);
		return "auditmsgs/edit";
    	
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(@RequestParam("msgid") Integer id, @RequestParam("auditText") String auditText){
    	Messages msg = repo.findOne(id);
    	msg.setAuditText(auditText);
    	msg.setHasAdudit(true);
    	repo.save(msg);
		return "redirect:auditmsgs/index";
    	
    }
    
}
