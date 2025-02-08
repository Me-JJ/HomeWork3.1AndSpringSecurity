package com.SchoolManagementSys.services;

import com.SchoolManagementSys.entity.ProfEntity;
import com.SchoolManagementSys.entity.SubEntity;
import com.SchoolManagementSys.exceptions.ResourceNotFound;
import com.SchoolManagementSys.repositories.ProfRepo;
import com.SchoolManagementSys.repositories.SubRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfService
{
    private final ProfRepo profRepo;
    private final SubRepo subRepo;



    public ProfService(ProfRepo profRepo, SubRepo subRepo) {
        this.profRepo = profRepo;
        this.subRepo = subRepo;
    }

    public ProfEntity createProf(ProfEntity profEntity)
    {
        return profRepo.save(profEntity);
    }

    public List<ProfEntity> getAll()
    {
        return profRepo.findAll();
    }

    public ProfEntity updateProfSub(Long pId,Long sId)
    {
        ProfEntity profEntity=profRepo.findById(pId).orElseThrow(()-> new ResourceNotFound("no Professor found with id -> "+pId));
        SubEntity subEntity = subRepo.findById(sId).orElseThrow(() -> new ResourceNotFound("no subject found with id -> " + sId));

        subEntity.setProfessor(profEntity);
        subRepo.save(subEntity);

        return profEntity;

    }
}
