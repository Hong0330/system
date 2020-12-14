package kr.ac.sunmoon.urs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = { "kr.ac.sunmoon.urs" })
public class UrsApplication {
	public static void main(String[] args) {
		SpringApplication.run(UrsApplication.class, args);
	}
}