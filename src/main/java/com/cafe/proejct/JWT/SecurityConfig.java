//package com.cafe.proejct.JWT;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.BeanIds;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.config.annotation.web.configuration.
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	
//	
//	@Autowired 
//	CustomerUserDetailsService customerUserDetailsService;
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(customerUserDetailsService);
//	}
//	
//	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
//	
//	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
//	
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) -> Exception {
//		http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
//		.and()
//		.csrf().disable()
//		.authorizeRequests()
//		.antMatcher("/user/login" , "/user/signup" , "/user/forgotPassword")
//		.permitAll()
//		.anyrequest()
//		.authenticated()
//		.and().exceptionHandling()
//		.and()
//		.sessionManagement()
//		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		
//	 http.addFilterBefore("" , UsernamePasswordAuthenticationFilter.class);
//	}
//	
//	
//	
//	
//	
//	
//
//}
