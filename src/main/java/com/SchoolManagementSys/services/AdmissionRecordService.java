package com.SchoolManagementSys.services;

import com.SchoolManagementSys.dto.AdmissionRecordDto;
import com.SchoolManagementSys.entity.AdmissionRecordEntity;

import java.util.List;

public interface AdmissionRecordService
{
    List<AdmissionRecordDto> getAllAdmissionRecords();

    AdmissionRecordDto createRecord(AdmissionRecordEntity admissionRecordDto);
}
