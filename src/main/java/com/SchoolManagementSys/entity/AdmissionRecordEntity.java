package com.SchoolManagementSys.entity;

import com.SchoolManagementSys.entity.enums.Branch;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@Table(name = "StudentAdmissionRecord")
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionRecordEntity extends AuditorEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer fees;

    @Enumerated(EnumType.STRING)
    private Branch branch;
}
