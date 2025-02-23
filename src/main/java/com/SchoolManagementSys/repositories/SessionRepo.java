package com.SchoolManagementSys.repositories;

import com.SchoolManagementSys.entity.SessionEntity;
import com.SchoolManagementSys.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepo extends JpaRepository<SessionEntity,Long> {
    List<SessionEntity> findByUser(UserEntity user);

    Optional<SessionEntity> findByRefreshToken(String refreshToken);
}
