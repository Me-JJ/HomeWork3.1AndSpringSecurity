package com.SchoolManagementSys.dto;

import com.SchoolManagementSys.entity.AdmissionRecordEntity;
import com.SchoolManagementSys.entity.SubEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class StdDto
{
    private Long id;
    private String name;
    private List<StdSubjectDto> subjects;
    private ShortAdmissionDto admissionRecordEntity;
    private LocalDateTime createDate;
    private LocalDateTime updatedDate;
}
