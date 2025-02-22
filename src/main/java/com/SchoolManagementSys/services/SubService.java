package com.SchoolManagementSys.services;

import com.SchoolManagementSys.dto.SubDto;
import com.SchoolManagementSys.entity.ProfEntity;
import com.SchoolManagementSys.entity.StdEntity;
import com.SchoolManagementSys.entity.SubEntity;
import com.SchoolManagementSys.exceptions.ResourceNotFound;
import com.SchoolManagementSys.repositories.ProfRepo;
import com.SchoolManagementSys.repositories.StdRepo;
import com.SchoolManagementSys.repositories.SubRepo;
import com.SchoolManagementSys.utils.RandomNum;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SubService
{
    private final SubRepo subRepo;
    private final ModelMapper modelMapper;
    private final ProfRepo profRepo;

    public SubDto createSub(SubDto subDto)
    {
        subDto.setTitle(subDto.getTitle()+" "+ RandomNum.getNumber());
        return modelMapper.map(subRepo.save(modelMapper.map(subDto,SubEntity.class)),SubDto.class);
    }

    public List<SubDto> getAll()
    {
        return subRepo.findAll().stream()
                .map(sub -> modelMapper.map(sub,SubDto.class))
                .toList();
    }

    public SubDto updateSubProf(Long profId,Long subId)
    {
        ProfEntity prof = profRepo.findById(profId).orElseThrow(()->new ResourceNotFound("Prof not found with id ->"+profId));
        SubEntity sub = subRepo.findById(subId).orElseThrow(()->new ResourceNotFound("Subject not found with id ->"+subId));

        sub.setProfessor(prof);
        return modelMapper.map(subRepo.save(sub), SubDto.class);
    }
}
