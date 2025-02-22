package com.SchoolManagementSys.dto;

import com.SchoolManagementSys.entity.enums.Branch;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShortAdmissionDto
{
    private Long id;
    private Integer fees;
    private Branch branch;
}
