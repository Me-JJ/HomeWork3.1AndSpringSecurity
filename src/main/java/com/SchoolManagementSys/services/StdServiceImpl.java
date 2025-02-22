package com.SchoolManagementSys.services;

import com.SchoolManagementSys.dto.StdDto;
import com.SchoolManagementSys.entity.AdmissionRecordEntity;
import com.SchoolManagementSys.entity.StdEntity;
import com.SchoolManagementSys.entity.SubEntity;
import com.SchoolManagementSys.exceptions.ResourceNotFound;
import com.SchoolManagementSys.repositories.AdmissionRecordRepo;
import com.SchoolManagementSys.repositories.ProfRepo;
import com.SchoolManagementSys.repositories.StdRepo;
import com.SchoolManagementSys.repositories.SubRepo;
import com.SchoolManagementSys.utils.RandomNum;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class StdServiceImpl implements StdService
{


    private final StdRepo stdRepo;
    private final AdmissionRecordRepo admissionRecordRepo;
    private final ProfRepo profRepo;
    private final ModelMapper modelMapper;
    private final SubRepo subRepo;

    @Override
    public StdDto createStd(StdDto stdDto) {
        stdDto.setName(stdDto.getName()+" "+ RandomNum.getNumber());
        return modelMapper.map(stdRepo.save(modelMapper.map(stdDto,StdEntity.class)),StdDto.class);
    }

    @Override
    public List<StdDto> getAll() {
        return stdRepo.findAll().stream()
                .map(std -> modelMapper.map(std,StdDto.class)).toList();
    }

    @Override
    public StdDto updateStdAdmissionRecord(Long stdId, Long arcId)
    {
        AdmissionRecordEntity admissionRecordEntity=
                admissionRecordRepo.findById(arcId).orElseThrow(()-> new ResourceNotFound("no Admission Record found with id -> "+arcId));
        StdEntity stdEntity = stdRepo.findById(stdId).orElseThrow(()-> new ResourceNotFound("no Student found with id -> "+stdId));

        stdEntity.setAdmissionRecordEntity(admissionRecordEntity);
        return modelMapper.map(stdRepo.save(stdEntity),StdDto.class);
    }

    @Override
    public StdDto updateStudentSubjects(Long stdId, Long subId)
    {
        StdEntity stdEntity = stdRepo.findById(stdId).orElseThrow(()-> new ResourceNotFound("no Student found with id -> "+stdId));
        SubEntity subEntity = subRepo.findById(subId).orElseThrow(()-> new ResourceNotFound("no Subject found with id -> "+subId));

        boolean found=false;
        for (SubEntity sub:stdEntity.getSubjects())
        {
            if(Objects.equals(sub.getId(),subId))
            {
                found=true;
                break;
            }
        }

        if (!found)
        {
            stdEntity.getSubjects().add(subEntity);
        }

        return modelMapper.map(stdRepo.save(stdEntity),StdDto.class);
    }

//    @Override
//    public StdDto updateStdProf(Long stdId, Long prodId)
//    {
//        ProfEntity profEntity=profRepo.findById(prodId).orElseThrow(()-> new ResourceNotFound("no Professor found with id -> "+prodId));
//        StdEntity stdEntity = stdRepo.findById(stdId).orElseThrow(()-> new ResourceNotFound("no Student found with id -> "+stdId));
//
//
//        List<StdEntity> stdEntities= profEntity.getStudents();
//        boolean found = false;
//
//        for (StdEntity std : stdEntities) {
//            if (Objects.equals(std.getId(), stdId)) {
//                found = true;
//                break;
//            }
//        }
//        if(found)
//        {
//            return ;
//        }
//        else
//        {
//            stdEntity.getProfessors().add(profEntity);
//            profEntity.getStudents().add(stdEntity);
//            return stdRepo.save(stdEntity);
//        }
//
//
//    }
}
