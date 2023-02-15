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
		log.info("====================main");
		return new ModelAndView("/index");
	}
	
	@RequestMapping("/index")
	public ModelAndView index(ModelAndView mav) {
		log.info("====================index");
		return mav;
	}
	
	@RequestMapping("/login")
	public ModelAndView login(ModelAndView mav) throws Exception{
		log.info("====================login");
        return mav;
    }
	
	@RequestMapping("/list")
	public ModelAndView list(ModelAndView mav) throws Exception{
		log.info("====================list");
		
		mav.addObject("key", "fruits");
		
		List<String> fruitList = new ArrayList<String>();
		
		fruitList.add("apple");
		fruitList.add("orange");
		fruitList.add("banana");
		
		mav.addObject("fruitList", fruitList);
		return mav;
	}


}
