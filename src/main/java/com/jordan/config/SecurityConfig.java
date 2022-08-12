package com.jordan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jordan.service.UserService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserService userService;
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userService);
//		auth.inMemoryAuthentication().withUser("Jordan").password("123").roles("USER")
//		.and().withUser("Admin").password("123").roles("ADMIN");
	}
	
	protected void configure(HttpSecurity http) throws Exception{
//		http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN")
//		.antMatchers("/user").hasAnyRole("ADMIN", "USER")
//		.and().formLogin();
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/user/join").permitAll().and().authorizeRequests();
	//	.antMatchers("/user/**", "/post/**").authenticated().and().httpBasic();
		
		http.authorizeRequests().antMatchers("/user/getAll").permitAll().and().authorizeRequests();
		
		http.authorizeRequests().antMatchers("/user/get/{id}").permitAll().and().authorizeRequests();
		
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
}
