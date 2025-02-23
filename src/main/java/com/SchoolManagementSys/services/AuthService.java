package com.SchoolManagementSys.services;

import com.SchoolManagementSys.dto.LoginDto;
import com.SchoolManagementSys.dto.LoginResponseDto;
import com.SchoolManagementSys.entity.SessionEntity;
import com.SchoolManagementSys.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService
{
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;
    private final SessionService sessionService;


    public LoginResponseDto login(LoginDto loginDto)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword())
        );

        log.info("authentication-> {}",authentication);

        UserEntity user = (UserEntity) authentication.getPrincipal();

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        sessionService.generateNewSession(user,refreshToken);

        return new LoginResponseDto(user.getId(),accessToken,refreshToken);
    }

    public LoginResponseDto refreshToken(String refreshToken)
    {
        Long userId=jwtService.getUserIdFromToken(refreshToken);
        UserEntity user = userService.getUserById(userId);

        sessionService.validateSession(refreshToken);

        String accessToken = jwtService.generateAccessToken(user);
        return new LoginResponseDto(userId,accessToken,refreshToken);
    }


}
