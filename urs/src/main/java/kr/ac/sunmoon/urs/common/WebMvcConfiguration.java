package kr.ac.sunmoon.urs.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	@Value("${resources.notload.list}")
	private List<String> notLoadList;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AuthCheckInterceptor())
			.addPathPatterns("/**")
			.excludePathPatterns("/common/**")
			.excludePathPatterns("/vendor/**")
			.excludePathPatterns("/css/**")
			.excludePathPatterns("/img/**")
			.excludePathPatterns("/js/**")
			.excludePathPatterns(notLoadList);
	}
}