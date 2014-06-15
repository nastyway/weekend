package com.nastyway.weekend.main.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/main")
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	/**
	 * 메인화면
	 */
	@RequestMapping("/main.do")
	public ModelAndView main() {

		ModelAndView mav = new ModelAndView("main/main");

		return mav;
	}

}
