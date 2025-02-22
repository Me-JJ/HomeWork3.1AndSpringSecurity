package com.SchoolManagementSys.services;

import com.SchoolManagementSys.dto.AdmissionRecordDto;
import com.SchoolManagementSys.entity.AdmissionRecordEntity;
import com.SchoolManagementSys.repositories.AdmissionRecordRepo;
import com.SchoolManagementSys.utils.RandomNum;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdmissionRecordServiceImpl implements AdmissionRecordService
{

    private final AdmissionRecordRepo admissionRecordRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<AdmissionRecordDto> getAllAdmissionRecords()
    {
        return admissionRecordRepo.findAll().stream()
                .map(record -> modelMapper.map(record, AdmissionRecordDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AdmissionRecordDto createRecord(AdmissionRecordEntity admissionRecordEntity)
    {
        admissionRecordEntity.setFees(RandomNum.getNumber()*admissionRecordEntity.getFees());
        return modelMapper.map(admissionRecordRepo.save(admissionRecordEntity), AdmissionRecordDto.class);
    }

}
