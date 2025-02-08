package com.SchoolManagementSys.services;

import com.SchoolManagementSys.entity.StdEntity;
import com.SchoolManagementSys.entity.SubEntity;
import com.SchoolManagementSys.exceptions.ResourceNotFound;
import com.SchoolManagementSys.repositories.StdRepo;
import com.SchoolManagementSys.repositories.SubRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SubService
{
    private final SubRepo subRepo;
    private final StdRepo stdRepo;

    public SubService(SubRepo subRepo, StdRepo stdRepo) {
        this.subRepo = subRepo;

        this.stdRepo = stdRepo;
    }

    public SubEntity createSub(SubEntity subEntity)
    {
        return subRepo.save(subEntity);
    }

    public List<SubEntity> getAll()
    {
        return subRepo.findAll();
    }

    public StdEntity updateStds(Long subId, Long stdId) {
        SubEntity subEntity = subRepo.findById(subId).orElseThrow(() -> new ResourceNotFound("no subject found with id -> " + subId));
        StdEntity stdEntity = stdRepo.findById(stdId).orElseThrow(() -> new ResourceNotFound("no subject found with id -> " + stdId));

        List<StdEntity> stds = subEntity.getStudents();
        boolean found = false;
        for (StdEntity std : stds) {
            if (Objects.equals(std.getId(), stdId)) {
                found = true;
                break;
            }
        }
        if (found) {
            return stdEntity;
        } else {
        stdEntity.getSubjects().add(subEntity);
        subRepo.save(subEntity);
        return stdEntity;
    }
    }
}
