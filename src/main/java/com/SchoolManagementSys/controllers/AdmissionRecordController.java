package com.SchoolManagementSys.controllers;


import com.SchoolManagementSys.entity.AdmissionRecordEntity;
import com.SchoolManagementSys.services.AdmissionRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/arc")
public class AdmissionRecordController
{
    private final AdmissionRecordService admissionRecordService;

    public AdmissionRecordController(AdmissionRecordService admissionRecordService) {
        this.admissionRecordService = admissionRecordService;
    }

    @GetMapping
    public List<AdmissionRecordEntity> getAll()
    {
        return admissionRecordService.getAllAdmissionRecords();
    }

    @PostMapping
    public AdmissionRecordEntity create(@RequestBody AdmissionRecordEntity admissionRecordEntity)
    {
        return admissionRecordService.createRecord(admissionRecordEntity);
    }

}
