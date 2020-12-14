package kr.ac.sunmoon.urs.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@ControllerAdvice(basePackages = {"kr.ac.sunmoon.urs"})
public class ExcptionHandler {
	@ExceptionHandler(RuntimeException.class)
	public String runtimeException() {
		return "error/500";
	}
	
	@RequestMapping(value = "/error", method = RequestMethod.POST)
	public String error() {
		return "error/404";
	}
	
	@RequestMapping(value = "/notdelete", method = RequestMethod.DELETE)
	public String notDelete() {
		return "error/notdelete";
	}
}