// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.webessay.controller;

import com.webessay.controller.UploadfileController;
import com.webessay.model.Uploadfile;
import com.webessay.model.UploadfileRepository;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect UploadfileController_Roo_Controller {
    
    @Autowired
    UploadfileRepository UploadfileController.uploadfileRepository;
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String UploadfileController.create(@Valid Uploadfile uploadfile, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, uploadfile);
            return "uploadfiles/create";
        }
        uiModel.asMap().clear();
        uploadfileRepository.save(uploadfile);
        return "redirect:/uploadfiles/" + encodeUrlPathSegment(uploadfile.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String UploadfileController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Uploadfile());
        return "uploadfiles/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String UploadfileController.show(@PathVariable("id") Integer id, Model uiModel) {
        uiModel.addAttribute("uploadfile", uploadfileRepository.findOne(id));
        uiModel.addAttribute("itemId", id);
        return "uploadfiles/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String UploadfileController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("uploadfiles", uploadfileRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) uploadfileRepository.count() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("uploadfiles", uploadfileRepository.findAll());
        }
        return "uploadfiles/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String UploadfileController.update(@Valid Uploadfile uploadfile, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, uploadfile);
            return "uploadfiles/update";
        }
        uiModel.asMap().clear();
        uploadfileRepository.save(uploadfile);
        return "redirect:/uploadfiles/" + encodeUrlPathSegment(uploadfile.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String UploadfileController.updateForm(@PathVariable("id") Integer id, Model uiModel) {
        populateEditForm(uiModel, uploadfileRepository.findOne(id));
        return "uploadfiles/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String UploadfileController.delete(@PathVariable("id") Integer id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Uploadfile uploadfile = uploadfileRepository.findOne(id);
        uploadfileRepository.delete(uploadfile);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/uploadfiles";
    }
    
    void UploadfileController.populateEditForm(Model uiModel, Uploadfile uploadfile) {
        uiModel.addAttribute("uploadfile", uploadfile);
    }
    
    String UploadfileController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
