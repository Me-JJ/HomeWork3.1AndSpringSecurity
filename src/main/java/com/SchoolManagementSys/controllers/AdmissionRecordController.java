package com.SchoolManagementSys.controllers;


import com.SchoolManagementSys.dto.AdmissionRecordDto;
import com.SchoolManagementSys.entity.AdmissionRecordEntity;
import com.SchoolManagementSys.services.AdmissionRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/arc")
@RequiredArgsConstructor
public class AdmissionRecordController
{
    private final AdmissionRecordService admissionRecordService;

    @GetMapping
    public ResponseEntity<List<AdmissionRecordDto>> getAll()
    {
        return ResponseEntity.ok(admissionRecordService.getAllAdmissionRecords());
    }

    @PostMapping
    public ResponseEntity<AdmissionRecordDto> create(@RequestBody AdmissionRecordEntity admissionRecordEntity)
    {
        return ResponseEntity.ok(admissionRecordService.createRecord(admissionRecordEntity));
    }

}
