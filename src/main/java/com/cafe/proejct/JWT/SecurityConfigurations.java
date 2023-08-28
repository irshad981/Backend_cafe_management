//package com.cafe.proejct.JWT;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfigurations {
//	
//	@Autowired
//    private CustomerUserDetailsService customerUserDetailsService;
//	
//	@Autowired
//	JwtFilter jwtFilter;
//	
//	@Bean
//	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	      
//		 http
//		    .cors(cors -> cors.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()))
//            .and()
//            .csrf().disable()
//            .requestMatchers()
////            .mvcMatchers("/user/login", "/user/signup", "/user/forgotPassword")
//            .antMatchers("/user/login", "/user/signup", "/user/forgotPassword")
//             .and()
//             .authorizeRequests()
//             .anyRequest().authenticated()
//             .and()
//             .exceptionHandling()
//             .and()
//              .sessionManagement()
//              .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        
//		 
//		 
//		 
//		 
//		 return null;
//		 
//	 }
//
//}
