package com.SchoolManagementSys.services;

import com.SchoolManagementSys.entity.StdEntity;

import java.util.List;

public interface StdService {

    StdEntity createStd(StdEntity stdEntity);

    List<StdEntity> getAll();

    StdEntity updateStdAdmissionRecord(Long stdId, Long arcId);

    StdEntity updateStdProf(Long stdId,Long prodId);
}
