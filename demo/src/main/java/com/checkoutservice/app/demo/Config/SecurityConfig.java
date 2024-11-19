package com.checkoutservice.app.demo.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF protection
                .authorizeRequests()
                .requestMatchers("/login", "/**").permitAll() // Allow public URLs
                .anyRequest().authenticated() // All other requests require authentication
                .and()
                .formLogin().disable(); // Disable the default login page

        return http.build();
    }
}
