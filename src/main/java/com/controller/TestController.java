package com.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import test.lookup.AOrBFactory;
import test.lookup.AOrBService;

@Controller("test")
@RequestMapping("/test")
public class TestController {
	
	@Resource
	private AOrBFactory aOrBFactory;
    
    @RequestMapping("/res")
    @ResponseBody
    public String filesUpload() {
    	AOrBService a = aOrBFactory.a();
    	a.say();
    	AOrBService b = aOrBFactory.b();
    	b.say();
    	AOrBService a2 = aOrBFactory.a();
    	a2.say();
		return "";
    	
    }
    
}