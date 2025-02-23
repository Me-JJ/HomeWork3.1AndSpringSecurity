package com.SchoolManagementSys.filter;

import com.SchoolManagementSys.entity.UserEntity;
import com.SchoolManagementSys.services.JwtService;
import com.SchoolManagementSys.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter
{
    private final JwtService jwtService;
    private final UserService userService;
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String reqToken = request.getHeader("Authorization");

            if (reqToken == null || !reqToken.startsWith("Bearer ")) {

                filterChain.doFilter(request, response);

                log.info("response when token = null-> {} ",response.getStatus());
                return;
            }

            String token = reqToken.split("Bearer ")[1]; // Beared dsfjsdfjbdjhajkdfnkjfda =>> split ["","dsfagfvevfe"]
            Long userId = jwtService.getUserIdFromToken(token);

            if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserEntity user = userService.getUserById(userId);

                user.setPassword("");
                UsernamePasswordAuthenticationToken authenticationToken
                        = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request) // useful info if we want to rate limit or other stuff
                );


                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

            filterChain.doFilter(request, response);

            log.info("response -> {}",response);
        }
        catch (Exception e)
        {
            handlerExceptionResolver.resolveException(request,response,null,e);
        }
    }
}
