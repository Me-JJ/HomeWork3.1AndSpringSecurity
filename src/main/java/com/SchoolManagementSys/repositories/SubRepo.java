package com.SchoolManagementSys.repositories;

import com.SchoolManagementSys.entity.SubEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubRepo extends JpaRepository<SubEntity,Long> {
}
