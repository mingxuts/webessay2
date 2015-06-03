package com.webessay.controller;
import com.webessay.model.Subjects;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/subjectses")
@Controller
@RooWebScaffold(path = "subjectses", formBackingObject = Subjects.class)
public class SubjectsController {
}
