package com.webessay.controller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
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
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

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
    
    @RequestMapping(method = RequestMethod.POST, value = "/index")
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

    @RequestMapping(value = "/index")
    public String index(Principal principal) {
        return page_endpoint;
    }
    
    @RequestMapping(value = "/files/{id}", method = RequestMethod.GET)
    public void showFile(@PathVariable("id") Integer id, HttpServletResponse response, Model model) throws NoSuchRequestHandlingMethodException {
    	Uploadfile file = repo.findOne(id);
    	if (file != null) {
            byte[] image = file.getFile();
            if (image != null) {
                try {
                    response.setContentType(file.getFileContentType());
                    OutputStream out = response.getOutputStream();
                    IOUtils.copy(new ByteArrayInputStream(image), out);
                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }			
		} else {
			throw new NoSuchRequestHandlingMethodException ("File Not Found", Testupload.class);
		}
    }
    
}
