package com.SchoolManagementSys.dto;

import com.SchoolManagementSys.entity.ProfEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SubDto
{
    private Long id;
    private String title;
    private SubProfDto professor;
    private LocalDateTime createDate;
    private LocalDateTime updatedDate;
}
