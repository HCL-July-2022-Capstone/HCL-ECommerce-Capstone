package com.jordan.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.google.common.base.Predicates;
import com.jordan.service.EmailService;
import com.okta.spring.boot.oauth.Okta;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter{
	

	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.build();
	}
	
	public void addResourceHandler(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars");
		
	}
	
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
//		auth.userDetailsService(userService);
//		auth.inMemoryAuthentication().withUser("Jordan").password("123").roles("USER")
//		.and().withUser("Admin").password("123").roles("ADMIN");
//	}
	
	protected void configure(HttpSecurity http) throws Exception{
//		http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN")
//		.antMatchers("/user").hasAnyRole("ADMIN", "USER")
//		.and().formLogin();
		http.csrf().disable()
		.headers()
        .frameOptions()
        .disable().and()
        .oauth2ResourceServer().jwt();
		Okta.configureResourceServer401ResponseBody(http);
}
	
	
//	disable cors policy
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Collections.singletonList("*"));
	    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
	    configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
	    configuration.setAllowCredentials(true);
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public EmailService emailService() {
		return new EmailService();
	}

}
