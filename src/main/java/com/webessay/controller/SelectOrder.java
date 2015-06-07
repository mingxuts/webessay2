package com.webessay.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webessay.model.Orders;
import com.webessay.model.OrdersRepository;

@RequestMapping("/selectorder/**")
@Controller
public class SelectOrder {

	@Autowired
	private OrdersRepository repo;
	
    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping
    public String index(Model uiModel) {
    	List<Orders> ordersLst = repo.findAll();
    	uiModel.addAttribute("orderslst", ordersLst);
        return "selectorder/index";
    }
}
