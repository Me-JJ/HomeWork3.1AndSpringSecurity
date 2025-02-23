package com.SchoolManagementSys.controllers;


import com.SchoolManagementSys.dto.AdmissionRecordDto;
import com.SchoolManagementSys.entity.AdmissionRecordEntity;
import com.SchoolManagementSys.services.AdmissionRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/arc")
@RequiredArgsConstructor
public class AdmissionRecordController
{
    private final AdmissionRecordService admissionRecordService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMISSION_RECORD_VIEW')")
    public ResponseEntity<List<AdmissionRecordDto>> getAll()
    {
        return ResponseEntity.ok(admissionRecordService.getAllAdmissionRecords());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<AdmissionRecordDto> create(@RequestBody AdmissionRecordEntity admissionRecordEntity)
    {
        return ResponseEntity.ok(admissionRecordService.createRecord(admissionRecordEntity));
    }


}
