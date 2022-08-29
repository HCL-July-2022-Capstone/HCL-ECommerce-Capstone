package com.jordan.config;

import com.jordan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

//
//
@Configuration
//@EnableSwagger2
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserService userService;
	
//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(Predicates.not(RequestHandlerSelectors("org.springframework.boot")))
////						RequestHandlerSelectors.basePackage("org.springframework.boot")))
//				.build();
//	}
	
	public void addResourceHandler(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars");

	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userService);
//		auth.inMemoryAuthentication().withUser("Jordan").password("123").roles("USER")
//		.and().withUser("Admin").password("123").roles("ADMIN");
	}
//
	protected void configure(HttpSecurity http) throws Exception{
//		http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN")
//		.antMatchers("/user").hasAnyRole("ADMIN", "USER")
//		.and().formLogin();
		http.csrf().disable();
		
		http.authorizeRequests().antMatchers("/user/getAllUsers").hasRole("ADMIN")
		.and().formLogin().defaultSuccessUrl("/create-new-session");
				
//		http.authorizeRequests()//.antMatchers("/user/getAll")
//		.antMatchers("/user/getAll","/user/join","/user/get/{id}").permitAll();
//		
//		 http.authorizeRequests().antMatchers("/user/join").permitAll().and().authorizeRequests()
//         .antMatchers("/user/**").authenticated().and().httpBasic().and().formLogin();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
}
