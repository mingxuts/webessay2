package com.webessay.controller;
import com.webessay.model.Uploadfile;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/uploadfiles")
@Controller
@RooWebScaffold(path = "uploadfiles", formBackingObject = Uploadfile.class)
public class UploadfileController {
}
