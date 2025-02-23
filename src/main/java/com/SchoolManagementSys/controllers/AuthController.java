package com.SchoolManagementSys.controllers;

import com.SchoolManagementSys.advice.ApiResponse;
import com.SchoolManagementSys.dto.LoginDto;
import com.SchoolManagementSys.dto.LoginResponseDto;
import com.SchoolManagementSys.dto.SignUpDto;
import com.SchoolManagementSys.dto.UserDto;
import com.SchoolManagementSys.entity.UserEntity;
import com.SchoolManagementSys.services.AuthService;
import com.SchoolManagementSys.services.JwtService;
import com.SchoolManagementSys.services.SessionService;
import com.SchoolManagementSys.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController
{
    private final UserService userService;
    private final AuthService authService;
    private final SessionService sessionService;
    private final JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto)
    {
        return ResponseEntity.ok(userService.createUser(signUpDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> signIn(@RequestBody LoginDto loginDto, HttpServletResponse response)
    {
        LoginResponseDto loginResponseDto = authService.login(loginDto);
        Cookie cookie = new Cookie("refreshToken",loginResponseDto.getRefreshToken());
        cookie.setHttpOnly(true); // no one can access the cookie

        response.addCookie(cookie);
        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/refresh")
    @Secured({"ROLE_ADMIN","ROLE_PROF","ROLE_STUDENT"})
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest httpServletRequest)
    {
        String refreshToken = getRefreshToken(httpServletRequest);

        LoginResponseDto loginResponseDto = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/logout")
    @Secured({"ROLE_ADMIN","ROLE_PROF","ROLE_STUDENT"})
    public ApiResponse<String> logout(HttpServletRequest request)
    {
        String refreshToken = getRefreshToken(request);
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        sessionService.logout(userId);
        //        return ResponseEntity.ok("Session Deleappted Successfully");
        return new ApiResponse<>("Session Deleted Successfully");
    }


    private String getRefreshToken(HttpServletRequest httpServletRequest)
    {
        return Arrays.stream(httpServletRequest.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new AuthenticationServiceException("Refresh Token not found inside cookie"));
    }
}
