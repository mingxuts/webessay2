package com.webessay.controller;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.webessay.model.Messages;
import com.webessay.model.MessagesRepository;
import com.webessay.model.Uploadfile;
import com.webessay.model.UploadfileRepository;
import com.webessay.user.RegisteredUser;
import com.webessay.user.UserUtils;

@RequestMapping("/composemsg/**")
@Controller
public class Composemsg {
	
	@Autowired
	private MessagesRepository repo;
	
	@Autowired
	private UploadfileRepository uploadrepo;

    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping
    public String index(Model uiModel, Principal principal) {
    	Messages msg = new Messages();
    	msg.setFromUser(UserUtils.getUserId(principal));
    	uiModel.addAttribute("messages", msg);
    	
    	RegisteredUser user = (RegisteredUser)((Authentication) principal).getPrincipal();
    	Map<Integer, String> contacts = user.getProfile().getContactsList();
    	uiModel.addAttribute("contacts", contacts.entrySet());
        return "composemsg/index";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String postForm(@Valid Messages msg, Model uiModel, @RequestParam("tempfile") MultipartFile file){
    	try {
			upload(msg, file);
			repo.save(msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//uiModel.asMap().clear();
    	return "composemsg/index";
    }
    
	@InitBinder
	protected void initBinder(HttpServletRequest request,
	        ServletRequestDataBinder binder) throws ServletException {
	    binder.registerCustomEditor(byte[].class,
	            new ByteArrayMultipartFileEditor());
	}     
    
    private void upload(Messages msg, MultipartFile file) throws Exception{
    	Uploadfile entity = new Uploadfile();
    	entity.setFileContentType(file.getContentType());
    	entity.setFileName(file.getOriginalFilename());
    	entity.setFileSize(file.getSize());
    	entity.setFile(file.getBytes());
    	uploadrepo.save(entity);
    	msg.setFileId(entity.getId());
    }
    
}
