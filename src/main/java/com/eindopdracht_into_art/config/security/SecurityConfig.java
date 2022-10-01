package com.eindopdracht_into_art.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.eindopdracht_into_art.controllers.endpoints.ControllerEndpointConstants.*;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;
    private final UserDetailsService userdetailsService;

    @Autowired
    public SecurityConfig(JwtRequestFilter jwtRequestFilter, UserDetailsService userdetailsService) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.userdetailsService = userdetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected AuthenticationManager authManager(
            HttpSecurity http,
            BCryptPasswordEncoder passwordEncoder
    ) throws Exception {
        return http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userdetailsService)
                .passwordEncoder(passwordEncoder)
                .and().build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().disable()
                .authorizeRequests(auth -> auth
                        .mvcMatchers(EP_NEWSLETTER + "*/**"
                                , EP_REGISTRATION
                                , EP_LOGIN + "**"
                                , EP_HELLO).permitAll()
                        .mvcMatchers(EP_USER).hasAuthority("USER")
                        .mvcMatchers("/authenticated").authenticated()
                        .anyRequest().authenticated()
                )
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Configuration // This configuration enables support for Spring Security's pre-post annotations.
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public static class MethodSecurityConfigurer
            extends GlobalMethodSecurityConfiguration {
    }

}

