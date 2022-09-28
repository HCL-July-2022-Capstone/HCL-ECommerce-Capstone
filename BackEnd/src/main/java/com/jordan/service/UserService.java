package com.jordan.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService
{
	private final RestTemplate restTemplate;

	public UserService(RestTemplateBuilder restTemplateBuilder)
	{
		this.restTemplate = restTemplateBuilder.build();
	}

	public String getUsername()
	{
		String url = "https://dev-32668171.okta.com/oauth2/v1/userinfo";
		return this.restTemplate.getForObject(url, String.class);
	}

	public static String getToken()
	{
		String token = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null)
		{
			token = authentication.getName();
		}
		return token;
	}
}
