package com.webessay.controller;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.webessay.model.Jobs;
import com.webessay.model.JobsRepository;
import com.webessay.model.Orders;
import com.webessay.model.OrdersRepository;
import com.webessay.model.Userinfo;
import com.webessay.model.UserinfoRepository;

@RequestMapping("/jobform/**")
@Controller
public class Jobform {
	
	@Autowired
	private JobsRepository repo;
	
	@Autowired
	private OrdersRepository orderRepo;
	
	@Autowired
	private UserinfoRepository userRepo;

    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping()
    public String index(Model uiModel, @RequestParam(value = "orderid") Integer orderid) {
	    Jobs job     = new Jobs();
	    Orders order = orderRepo.findOne(orderid); 
    	
    	copyobj(order, job);
    	uiModel.addAttribute("jobs", job);
    	formatOrderDate(uiModel, job);
        return "jobform/index";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String postJob(@Valid Jobs jobs, Model uiModel){
    	repo.save(jobs);
    	return "jobform/index";
    }
    
    private void copyobj(Orders order, Jobs job){
    	Userinfo customer = userRepo.findOne(order.getCustomerId());
    	
    	Integer id 		  = order.getId();
    	Integer fileId    = order.getFileId();
    	Integer wordCount = order.getWordCount();
    	Integer subjectId = order.getSubjectId();
    	
    	String courseLevel   = order.getCourseLevel();
    	String includeFigure = order.getIncludeFigure();
    	String presentFormat = order.getPresentFormat();
    	String referencing   = order.getReferencing();
    	Short sourcesCount   = order.getSourcesCount();
    	
    	Calendar startDate = order.getStartDate();
    	Calendar deadLine = order.getDeadLine();
    	
		job.setOrderId(id);
		job.setFileId(fileId);
		job.setCourseLevel(courseLevel);
    	job.setCustomerId(customer);
		job.setIncludeFigure(includeFigure);
		job.setPresentFormat(presentFormat);
		job.setReferencing(referencing);
		job.setSourcesCount(sourcesCount);
		job.setStartDate(startDate);
		job.setDeadLine(deadLine);
		job.setSubjectId(subjectId);
		job.setWordCount(wordCount);
    }
    
    private void formatOrderDate(Model uiModel, Jobs job){
    	String pattern = DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale());
    	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    	
    	String startDate = sdf.format(job.getStartDate().getTime());
    	String deadLine = sdf.format(job.getDeadLine().getTime());
    	
    	uiModel.addAttribute("job_start_date", startDate);
    	uiModel.addAttribute("job_dead_line", deadLine);
    }
}
