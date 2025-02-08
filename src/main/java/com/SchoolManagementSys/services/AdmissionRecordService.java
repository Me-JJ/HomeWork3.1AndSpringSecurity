package com.SchoolManagementSys.services;

import com.SchoolManagementSys.entity.AdmissionRecordEntity;
import jakarta.validation.Valid;

import java.util.List;

public interface AdmissionRecordService
{
    List<AdmissionRecordEntity> getAllAdmissionRecords();

    AdmissionRecordEntity createRecord(AdmissionRecordEntity admissionRecordDto);
}
