package com.SchoolManagementSys.dto;

import com.SchoolManagementSys.entity.StdEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ProfDto
{
    private Long id;
    private String title;
    private List<ProfStdDto> students;
    private LocalDateTime createDate;
    private LocalDateTime updatedDate;
}
