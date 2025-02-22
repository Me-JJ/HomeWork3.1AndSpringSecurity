package com.SchoolManagementSys.services;

import com.SchoolManagementSys.dto.ProfDto;
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
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ProfService
{
    private final ProfRepo profRepo;
    private final ModelMapper modelMapper;
    private final StdRepo stdRepo;


    public ProfDto createProf(ProfDto profDto)
    {
        profDto.setTitle(profDto.getTitle()+" "+RandomNum.getNumber());
        return modelMapper.map(profRepo.save(modelMapper.map(profDto, ProfEntity.class)),ProfDto.class);
    }

    public List<ProfDto> getAll()
    {
        return profRepo.findAll().stream()
                .map(prof -> modelMapper.map(prof,ProfDto.class))
                .toList();
    }

    public ProfDto updateProfStds(Long profId, Long stdId)
    {
        ProfEntity profEntity=profRepo.findById(profId).orElseThrow(()-> new ResourceNotFound("no Professor found with id -> "+profId));
        StdEntity stdEntity = stdRepo.findById(stdId).orElseThrow(()-> new ResourceNotFound("no Student found with id -> "+stdId));

        List<StdEntity> stdEntities= profEntity.getStudents();
        boolean found = false;

        for (StdEntity std : stdEntities) {
            if (Objects.equals(std.getId(), stdId)) {
                found = true;
                break;
            }
        }
        if(!found)
        {
            profEntity.getStudents().add(stdEntity);
        }
        return modelMapper.map(profRepo.save(profEntity),ProfDto.class);
    }
}
