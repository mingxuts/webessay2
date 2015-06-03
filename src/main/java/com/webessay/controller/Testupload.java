package com.webessay.controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.webessay.model.Uploadfile;
import com.webessay.model.UploadfileRepository;


@RequestMapping("/testupload/**")
@Controller
public class Testupload {
	
	private static final String page_endpoint = "testupload/index";
	
	@Autowired
	UploadfileRepository repo;
	
	@InitBinder
	protected void initBinder(HttpServletRequest request,
	        ServletRequestDataBinder binder) throws ServletException {
	    binder.registerCustomEditor(byte[].class,
	            new ByteArrayMultipartFileEditor());
	}
    
    @RequestMapping(method = RequestMethod.POST)
    public String postForm(ModelMap modelMap, @RequestParam("testupload") MultipartFile file) throws IOException{
    	System.out.println(file.getContentType());
    	System.out.println(file.getOriginalFilename());
    	Uploadfile entity = new Uploadfile();
    	entity.setFileContentType(file.getContentType());
    	entity.setFileName(file.getOriginalFilename());
    	entity.setFileSize(file.getSize());
    	entity.setFile(file.getBytes());
    	repo.save(entity);
    	return page_endpoint;
    }
    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping
    public String index() {
        return page_endpoint;
    }
    
    
}
