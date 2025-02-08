package com.SchoolManagementSys.repositories;

import com.SchoolManagementSys.entity.ProfEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfRepo extends JpaRepository<ProfEntity,Long> {
}
