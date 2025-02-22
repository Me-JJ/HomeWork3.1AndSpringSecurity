package com.SchoolManagementSys.dto;

import com.SchoolManagementSys.entity.AdmissionRecordEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfStdDto
{
    private Long id;
    private String name;
    private ShortAdmissionDto admissionRecordEntity;
}
