package com.SchoolManagementSys.repositories;

import com.SchoolManagementSys.entity.StdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StdRepo extends JpaRepository<StdEntity,Long> {
}
