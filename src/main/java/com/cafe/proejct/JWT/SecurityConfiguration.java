package com.cafe.proejct.JWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration   {
	
	@Autowired
    private CustomerUserDetailsService customerUserDetailsService;

	@Autowired
	JwtFilter jwtFilter;
    
	@SuppressWarnings({ "removal", "deprecation"})
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            //.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
        .cors(cors -> cors.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()))
            
           .csrf()
           .disable()
           .authorizeHttpRequests()
           
           //.requestMatchers()
//               .mvcMatchers("/user/login", "/user/signup", "/user/forgotPassword")
          // .antMatchers("/user/login", "/user/signup", "/user/forgotPassword")
           .requestMatchers("/user/login",  "/user/forgotPassword" , "/user/signup").permitAll()

           
               
               .anyRequest().authenticated()
               .and()
           .exceptionHandling()
               .and()
           .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
           
           
           
           
//           .authorizeRequests()
//            .antMatchers( "/user/signup")
//            .permitAll()
//            .anyRequest()
//            .authenticated()
//            .and()
//            .exceptionHandling()
//            .and()
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

   // @Bean(name = "authenticationManager")
	@Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    
    
   // @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider()  {
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    	provider.setUserDetailsService(customerUserDetailsService);
    	provider.setPasswordEncoder(passwordEncoder());
    	return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
	 


	

}
