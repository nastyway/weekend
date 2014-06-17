package com.nastyway.weekend.login.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.nastyway.weekend.user.model.User;
import com.nastyway.weekend.user.service.UserService;

@Controller("LoginController")
@RequestMapping("/login")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserService userService;

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

		ModelAndView mav = new ModelAndView("login/loginForm", "login", new Login());

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
	@RequestMapping(value = "/loginProcess.do", method = RequestMethod.POST)
	public String loginProcess(@ModelAttribute("login") Login login, BindingResult result, HttpServletRequest request) {

		Map<String, String> loginParam = new HashMap<String, String>();
		loginParam.put("userId", login.getUserId());
		loginParam.put("password", login.getPassword());

		if (loginService.getLoginResult(loginParam) > 0) {
			// 세션에 유저 정보를 등록
			User userInfo = userService.getUserInfo(login.getUserId());
			
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", userInfo);

			return "redirect:/main/main.do";
		} else {
			return "redirect:/login/loginForm.do";
		}
	}

	/**
	 * 로그아웃
	 */
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {

		// 세션삭제
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "/login/loginForm.do";
	}

}
