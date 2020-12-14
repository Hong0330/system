package kr.ac.sunmoon.urs.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.google.gson.Gson;

import kr.ac.sunmoon.urs.member.Member;
import kr.ac.sunmoon.urs.member.MemberMapper;

@RestController
@RequestMapping("/common")
public class CommonController {
	@Autowired
	public CommonServiceImpl commonService;
	
	@Autowired
	public MemberMapper memberMapper;
	
	@GetMapping("/login/form")
	public ModelAndView loginForm(Member member, @CookieValue(value = "REMEMBER", required = false) Cookie rCookie) {
		ModelAndView mav = new ModelAndView("/common/loginForm");
		if (rCookie != null) {
			member.setMemberNo(Integer.parseInt(rCookie.getValue()));
			member.setRememberMemberNo(true);
		}
		
		return mav;
	}
	
	@PostMapping("/login")
	public ModelAndView login(Member member, Errors errors, HttpSession session, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		
		Cookie memberNoCookie = new Cookie("REMEMBER", String.valueOf(member.getMemberNo()));
		memberNoCookie.setPath("/");
		if (member.isRememberMemberNo()) {
			memberNoCookie.setMaxAge(60 * 60 * 24);
		} else {
			memberNoCookie.setMaxAge(0);
		}
		response.addCookie(memberNoCookie);
		
		mav.setView(new RedirectView(commonService.login(member, session)));
		return mav;
	}
	
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mav = new ModelAndView(new RedirectView("/common/login/form"));
		commonService.logout(session);
		
		return mav;
	}
	
	@PostMapping(value = "/device", produces = "application/json; charset=utf8")
	public Message receiveAuthInfo(@RequestBody String jsonMsg) {
		Gson gson = new Gson();
		Message message = gson.fromJson(jsonMsg, Message.class);
		
		Message messageToSend = commonService.receiveAuthInfo(message);
		if (messageToSend == null) {
			messageToSend = new Message();
			messageToSend.setCode("400");
		}
		
		return messageToSend;
	}
	
	@GetMapping("/check/{memberNo}/{password}")
	public String checkLogin(Member member) {
		Gson gson = new Gson();
		String result = null;
		
		try {
			if (memberMapper.count(member) == 0) {
				result = gson.toJson(member);
			} else {
				result = gson.toJson(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return result;
	}
}
