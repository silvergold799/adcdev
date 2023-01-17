package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class HelloController {
	
	@RequestMapping("/")
	public ModelAndView main() {
		return new ModelAndView("/index");
	}
	
	@RequestMapping("/index")
	public ModelAndView index(ModelAndView mav) {
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
