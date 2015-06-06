package com.webessay.controller;
import java.lang.reflect.Field;
import java.security.Principal;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.webessay.model.Orders;
import com.webessay.model.OrdersRepository;
import com.webessay.user.UserUtils;

@RequestMapping("/orderform/**")
@Controller
public class Orderform {
	
	@Autowired
	private OrdersRepository repo;
	
	private String page_endpoint = "orderform/index";	

    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping
    public String index(Model uiModel, Principal principal) {
    	
    	establishData(uiModel);
    	addDatetimeFormatPatterns(uiModel);
    	Orders orders = new Orders();
    	orders.setCustomerId(UserUtils.getUserId(principal));
    	uiModel.addAttribute("orders", orders);
        return page_endpoint;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String postForm(@Valid Orders orders, BindingResult bindingResult,  Model uiModel, @RequestParam("tempfile") MultipartFile multipartFile){
    	orders.setCreateDate(new GregorianCalendar());
    	repo.save(orders);    	
    	return page_endpoint;
    }
    
    private void addDatetimeFormatPatterns(Model uiModel){
        uiModel.addAttribute("order_startdate_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("order_deadline_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));    	
    }
    private void establishData(Model uiModel){
        uiModel.addAttribute("wordcounts", com.webessay.customtypes.Wordcount.getAllWordCountValues());
        uiModel.addAttribute("courselevels", com.webessay.customtypes.Courselevel.getAllCourses());
        uiModel.addAttribute("sourcescount", com.webessay.customtypes.Sourcescount.getAllSourcescountValues());
        uiModel.addAttribute("presentationformat", com.webessay.customtypes.Presentationformat.getAllPresentationformat());
        uiModel.addAttribute("referencingformat", com.webessay.customtypes.Referencingformat.getAllReferencingformat());
        uiModel.addAttribute("includegraph", com.webessay.customtypes.Includegraph.getAllIncludegraphs());    	
    }
    
	@InitBinder
	protected void initBinder(HttpServletRequest request,
	        ServletRequestDataBinder binder) throws ServletException {
	    binder.registerCustomEditor(byte[].class,
	            new ByteArrayMultipartFileEditor());
	} 
	

}
