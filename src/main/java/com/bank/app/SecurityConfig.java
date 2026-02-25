package com.bank.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.httpBasic(basic -> {});
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));
        http.authorizeHttpRequests((authorizeRequests) ->
                authorizeRequests.requestMatchers(HttpMethod.POST,"/contacts").hasRole("ADMIN").
                        requestMatchers(HttpMethod.DELETE,"/contacts/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/contacts").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/contacts/public/**").permitAll()
                        .anyRequest().authenticated());
        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails admin=User.withUsername("admin").password(passwordEncoder().encode("admin123")).roles("ADMIN").build();
        UserDetails user= User.withUsername("user").password(passwordEncoder().encode("user123")).roles("USER").build();
        return new InMemoryUserDetailsManager(admin,user);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
