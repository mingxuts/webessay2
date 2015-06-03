package com.webessay.controller;
import com.webessay.model.Jobs;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/jobses")
@Controller
@RooWebScaffold(path = "jobses", formBackingObject = Jobs.class)
public class JobsController {
}
