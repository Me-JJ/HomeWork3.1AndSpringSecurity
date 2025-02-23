package com.SchoolManagementSys.configs;

import com.SchoolManagementSys.filter.JwtAuthFilter;
import com.SchoolManagementSys.handler.OAuthSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.nio.file.AccessDeniedException;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig
{
    private final JwtAuthFilter jwtAuthFilter;
    private final OAuthSuccessHandler oAuthSuccessHandler;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.authorizeHttpRequests(auth -> auth
                            .requestMatchers("/auth/login","/auth/signup","/home.html").permitAll()
                            .anyRequest().authenticated())
                    .csrf(AbstractHttpConfigurer::disable)
                    .addFilterAfter(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                    .sessionManagement(sesConfig -> sesConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // no session stored -> used in cased of JWT authentication
                    .oauth2Login(oAuth ->
                        oAuth.failureUrl("/login?error=true")
                                .successHandler(oAuthSuccessHandler)
                    );

//                    .formLogin(Customizer.withDefaults());

            return httpSecurity.build();

    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

