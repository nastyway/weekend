package com.nastyway.weekend.login.web;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nastyway.weekend.login.model.Login;
import com.nastyway.weekend.login.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;
	
	/**
	 * 로그인 체크 함수
	 * 
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping("/loginForm.do")
	public ModelAndView loginForm() {
		
		logger.info("---------------------------LoginForm.do---------------------------");
		
		ModelAndView mav = new ModelAndView("login/loginForm","login",new Login());
		
		return mav;
	}
	
	/**
	 * 로그인 체크 함수
	 * 
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/loginProcess.do", method = RequestMethod.POST)
	public ModelAndView loginProcess(@ModelAttribute("login") Login login, BindingResult result) {
		
		logger.info("---------------------------loginProcess.do---------------------------");

		ModelAndView mav = new ModelAndView();
		
		Map<String, String> loginParam = new HashMap<String,String>();
		loginParam.put("userId", login.getUserId());
		loginParam.put("password", login.getPassword());
		
		if(loginService.getLoginResult(loginParam)>0) {
			mav.setViewName("redirect:/main/main.do");
		} else {
			mav.setViewName("/login/loginForm");
		}
		
		return mav;
	}

}
