package com.example.serviceA.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ControllerA {

	@Autowired
	private RestTemplate template;

	@HystrixCommand(fallbackMethod = "fallback")
	@GetMapping("/service/a")
	public String handleA() {
		String url = "http://service-b/service/b";

		System.out.println("ControllerA - /service/a attempt to call B");
		try {
			return "Service A calling B: " + template.getForObject(url, String.class);
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}
	}

	public String fallback(Throwable th) {
		System.out.println("ControllerA - /service/a fallback");
		return "Service A: FALLBACK message - cannot call service B: " + th;
	}

}

//		System.out.println("==============================");
//		try {
//			URI uri = UriComponentsBuilder.fromUriString("https://endpoint_with_underscore").build().toUri();
//			System.out.println(uri);
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		System.out.println("==============================");