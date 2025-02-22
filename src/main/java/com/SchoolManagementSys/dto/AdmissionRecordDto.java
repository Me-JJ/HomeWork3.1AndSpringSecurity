package com.SchoolManagementSys.dto;

import com.SchoolManagementSys.entity.enums.Branch;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AdmissionRecordDto
{
    private Long id;
    private Integer fees;
    private Branch branch;
    private LocalDateTime createDate;
    private LocalDateTime updatedDate;
}
