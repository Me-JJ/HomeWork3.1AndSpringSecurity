package com.SchoolManagementSys.services;

import com.SchoolManagementSys.entity.AdmissionRecordEntity;
import com.SchoolManagementSys.entity.ProfEntity;
import com.SchoolManagementSys.entity.StdEntity;
import com.SchoolManagementSys.exceptions.ResourceNotFound;
import com.SchoolManagementSys.repositories.AdmissionRecordRepo;
import com.SchoolManagementSys.repositories.ProfRepo;
import com.SchoolManagementSys.repositories.StdRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StdServiceImpl implements StdService
{


    private final StdRepo stdRepo;
    private final AdmissionRecordRepo admissionRecordRepo;
    private final ProfRepo profRepo;

    public StdServiceImpl(StdRepo stdRepo, AdmissionRecordRepo admissionRecordRepo, ProfRepo profRepo) {
        this.stdRepo = stdRepo;
        this.admissionRecordRepo = admissionRecordRepo;
        this.profRepo = profRepo;
    }

    @Override
    public StdEntity createStd(StdEntity stdEntity) {
        return stdRepo.save(stdEntity);
    }

    @Override
    public List<StdEntity> getAll() {
        return stdRepo.findAll();
    }

    @Override
    public StdEntity updateStdAdmissionRecord(Long stdId, Long arcId)
    {
        AdmissionRecordEntity admissionRecordEntity=
                admissionRecordRepo.findById(arcId).orElseThrow(()-> new ResourceNotFound("no Admission Record found with id -> "+arcId));
        StdEntity stdEntity = stdRepo.findById(stdId).orElseThrow(()-> new ResourceNotFound("no Student found with id -> "+stdId));

        stdEntity.setAdmissionRecordEntity(admissionRecordEntity);
        return stdRepo.save(stdEntity);
    }

    @Override
    public StdEntity updateStdProf(Long stdId, Long prodId)
    {
        ProfEntity profEntity=profRepo.findById(prodId).orElseThrow(()-> new ResourceNotFound("no Professor found with id -> "+prodId));
        StdEntity stdEntity = stdRepo.findById(stdId).orElseThrow(()-> new ResourceNotFound("no Student found with id -> "+stdId));


        List<ProfEntity> profs= stdEntity.getProfessors();
        boolean found = false;

        for (ProfEntity prof : profs) {
            if (Objects.equals(prof.getId(), prodId)) {
                found = true;
                break;
            }
        }
        if(found)
        {
            return stdEntity;
        }
        else
        {
            stdEntity.getProfessors().add(profEntity);
            profEntity.getStudents().add(stdEntity);
            return stdRepo.save(stdEntity);
        }


    }
}
