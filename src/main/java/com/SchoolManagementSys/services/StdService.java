package com.SchoolManagementSys.services;

import com.SchoolManagementSys.dto.StdDto;

import java.util.List;

public interface StdService {

    StdDto createStd(StdDto stdDto);

    List<StdDto> getAll();

    StdDto updateStdAdmissionRecord(Long stdId, Long arcId);

    StdDto updateStudentSubjects(Long stdId, Long subId);
}
