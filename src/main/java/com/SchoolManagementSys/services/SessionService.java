package com.SchoolManagementSys.services;

import com.SchoolManagementSys.entity.SessionEntity;
import com.SchoolManagementSys.entity.UserEntity;
import com.SchoolManagementSys.repositories.SessionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService
{
    private final SessionRepo sessionRepo;
    private final UserService userService;
    private final int SESSION_LIMIT=2;

    public void generateNewSession(UserEntity user, String refreshToken)
    {
        List<SessionEntity> sessionEntityList = sessionRepo.findByUser(user);
        if(sessionEntityList.size() == SESSION_LIMIT)
        {
            sessionEntityList.sort(Comparator.comparing(SessionEntity::getLastUsedAt));
            sessionRepo.delete(sessionEntityList.getFirst());
        }

        SessionEntity newSession = SessionEntity.builder()
                .refreshToken(refreshToken)
                .user(user)
                .build();

        sessionRepo.save(newSession);
    }

    public void validateSession(String refreshToken)
    {
        SessionEntity session = sessionRepo.findByRefreshToken(refreshToken).orElseThrow(()->
                new SessionAuthenticationException("session not found for RT -> "+refreshToken));

        session.setLastUsedAt(LocalDateTime.now());
        sessionRepo.save(session);
    }
    public void logout(Long userId)
    {
        UserEntity user= userService.getUserById(userId);
        List<SessionEntity> session = sessionRepo.findByUser(user);
        session.forEach(sessionRepo::delete);
    }
}
