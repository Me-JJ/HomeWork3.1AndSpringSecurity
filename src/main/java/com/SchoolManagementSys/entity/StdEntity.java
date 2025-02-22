package com.SchoolManagementSys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Entity
@Table(name = "Student")
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class StdEntity extends AuditorEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "Std_subs")
    private List<SubEntity>subjects;

    @OneToOne
    @JoinColumn(name = "std_adm_records")
    private AdmissionRecordEntity admissionRecordEntity;

}
