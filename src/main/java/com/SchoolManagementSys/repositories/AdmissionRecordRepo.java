package com.SchoolManagementSys.repositories;

import com.SchoolManagementSys.entity.AdmissionRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionRecordRepo extends JpaRepository<AdmissionRecordEntity,Long> {
}
