package com.SchoolManagementSys.services;

import com.SchoolManagementSys.entity.AdmissionRecordEntity;
import com.SchoolManagementSys.repositories.AdmissionRecordRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AdmissionRecordServiceImpl implements AdmissionRecordService
{

    private Integer getNumber() {
        return new Random().nextInt(100);
    }

    private final AdmissionRecordRepo admissionRecordRepo;

    public AdmissionRecordServiceImpl(AdmissionRecordRepo admissionRecordRepo, ModelMapper modelMapper) {
        this.admissionRecordRepo = admissionRecordRepo;
    }

    @Override
    public List<AdmissionRecordEntity> getAllAdmissionRecords()
    {
        return admissionRecordRepo.findAll();
    }

    @Override
    public AdmissionRecordEntity createRecord(AdmissionRecordEntity admissionRecordEntity)
    {
        admissionRecordEntity.setFees(getNumber()*admissionRecordEntity.getFees());
        return admissionRecordRepo.save(admissionRecordEntity);
    }



}
