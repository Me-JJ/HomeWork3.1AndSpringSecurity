package com.SchoolManagementSys.entity;

import com.SchoolManagementSys.entity.enums.Branch;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "StudentAdmissionRecord")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdmissionRecordEntity extends AuditorEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "admissionRecordEntity")
    @JsonIgnore
    private StdEntity student;

    private Integer fees;

    @Enumerated(EnumType.STRING)
    private Branch branch;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StdEntity getStudent() {
        return student;
    }

    public void setStudent(StdEntity student) {
        this.student = student;
    }

    public Integer getFees() {
        return fees;
    }

    public void setFees(Integer fees) {
        this.fees = fees;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdmissionRecordEntity that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getFees(), that.getFees());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFees());
    }
}
