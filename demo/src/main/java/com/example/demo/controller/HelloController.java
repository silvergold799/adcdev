package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class HelloController {
	private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@RequestMapping("/")
	public ModelAndView main() {
		log.info("main"); // default
		return new ModelAndView("/index");
	}
	
	@RequestMapping("/index")
	public ModelAndView index(ModelAndView mav) {
		
		log.info("index"); // default 
		return mav;
	}
	
	@RequestMapping("/login")
	public ModelAndView login(ModelAndView mav) throws Exception{
        
        mav.addObject("key", "fruits");
        
        List<String> fruitList = new ArrayList<String>();
        
        fruitList.add("apple");
        fruitList.add("orange");
        fruitList.add("banana");
         
        mav.addObject("value", fruitList);
        
        return mav;
    }


}
