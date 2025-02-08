package com.SchoolManagementSys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Student")
@AllArgsConstructor
@NoArgsConstructor
public class StdEntity extends AuditorEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StdEntity stdEntity)) return false;
        return Objects.equals(getId(), stdEntity.getId()) && Objects.equals(getName(), stdEntity.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "students")
    private List<ProfEntity>professors;

    @ManyToMany
    @JoinTable(name = "Std_subs")
//    @JsonIgnore
    private List<SubEntity>subjects;

    @OneToOne
    @JoinColumn(name = "std_adm_records")
    private AdmissionRecordEntity admissionRecordEntity;


    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProfEntity> getProfessors() {
        return professors;
    }

    public void setProfessors(List<ProfEntity> professors) {
        this.professors = professors;
    }

    public List<SubEntity> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubEntity> subjects) {
        this.subjects = subjects;
    }

    public AdmissionRecordEntity getAdmissionRecordEntity() {
        return admissionRecordEntity;
    }

    public void setAdmissionRecordEntity(AdmissionRecordEntity admissionRecordEntity) {
        this.admissionRecordEntity = admissionRecordEntity;
    }
}
