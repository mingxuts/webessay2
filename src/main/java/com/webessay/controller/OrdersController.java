package com.webessay.controller;
import com.webessay.model.Orders;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/orderses")
@Controller
@RooWebScaffold(path = "orderses", formBackingObject = Orders.class)
public class OrdersController {
}
