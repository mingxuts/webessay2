package com.webessay.controller;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webessay.model.Messages;
import com.webessay.model.Userinfo;
import com.webessay.model.UserinfoRepository;
import com.webessay.user.RegisteredUser;

@RequestMapping("/chatroom/**")
@Controller
public class Chatroom {
	
	@Autowired
	private UserinfoRepository repo;

    @RequestMapping(value = "/index")
    public String index(Model uiModel, Principal principal) {
    	RegisteredUser user = (RegisteredUser)((Authentication) principal).getPrincipal();
    	Boolean hasRead = true;
    	List<Messages> dblist = user.getProfile().getCanReadMessages(hasRead);
    	transformViewModel(uiModel, dblist, user.getUserID());
        return "chatroom/index";
    }
    
    private void transformViewModel(Model uiModel, List<Messages> dblist, Integer currentId){
    	List<MessageView> viewlist = new ArrayList<>();
    	for (Messages obj : dblist){
    		MessageView objview = new MessageView();
    		if (obj.getFromUser().equals(currentId)){
    			objview.fromUser = "Me";
    		} else {
    			Userinfo user = repo.findOne(obj.getFromUser());
    			Integer id = user.getId();
				StringBuffer sb = new StringBuffer(500);
				sb.append("[User - ");
				sb.append(id + "] ");
				sb.append(user.getFirstName() + "." + user.getLastName());
				objview.fromUser = sb.toString();
    		}
    		if (obj.getToUser().equals(currentId)){
    			objview.toUser = "Me";
    		} else {
    			Userinfo user = repo.findOne(obj.getToUser());
    			Integer id = user.getId();
				StringBuffer sb = new StringBuffer(500);
				sb.append("[User - ");
				sb.append(id + "] ");
				sb.append(user.getFirstName() + "." + user.getLastName());
				objview.toUser = sb.toString();   			
    		}
    		objview.auditText = obj.getAuditText();
    		objview.fileId = obj.getFileId().toString();
    		viewlist.add(objview);
    	}
    	uiModel.addAttribute("msglist", viewlist);
    }
}
