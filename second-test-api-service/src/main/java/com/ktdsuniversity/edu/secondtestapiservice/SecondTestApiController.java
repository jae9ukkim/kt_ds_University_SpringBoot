package com.ktdsuniversity.edu.secondtestapiservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

// @Controller와 @ResponsBody가 합쳐진 annotation
@RestController
// 모든 endpoint 앞에 공통적으로 붙는다
@RequestMapping("/second-service")
public class SecondTestApiController {
	
	private static final Logger logger = LoggerFactory.getLogger(SecondTestApiController.class);
	
	@GetMapping("/welcome")
	public String welcome(@RequestHeader("s-request") String header) {
		return "Welcome to the second-service";
	}
	
	@GetMapping("/message")
	public String message(@RequestHeader("s-request") String header) {
		logger.info("Second-Service s-request: {}", header);
		return "%s in Second Service".formatted(header);
	}
	
	@GetMapping("/check")
	public String check(@RequestHeader(value="s-request", required = false) String header, HttpServletRequest request) {

		if(header == null) {
			return "잘못된 요청입니다.";
		}
		// 현재 Server의 port를 가져와서 반환
		int port = request.getServerPort();
		logger.info("Second-Service port: {}", port);
		return "Second Service health check result: %d".formatted(port);
	}

}
