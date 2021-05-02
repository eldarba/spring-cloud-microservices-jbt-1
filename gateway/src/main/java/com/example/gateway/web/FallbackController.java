package com.example.gateway.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

	@GetMapping("/fallback/serviceA")
	public String fallbackA(Throwable th) {
		System.out.println("GATEWAY fallback A");
		return "Gateway: FALLBACK message - cannot call service A: " + th;
	}

	@GetMapping("/fallback/serviceB")
	public String fallbackB(Throwable th) {
		System.out.println("GATEWAY fallback B");
		return "Gateway: FALLBACK message - cannot call service B: " + th;
	}

}
