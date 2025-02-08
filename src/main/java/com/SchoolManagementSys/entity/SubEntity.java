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
@Table(name = "Subject")
@AllArgsConstructor
@NoArgsConstructor

public class SubEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "professor")
//    @JsonIgnore
    private ProfEntity professor;

    @ManyToMany(mappedBy = "subjects")
    @JsonIgnore
    private List<StdEntity>students;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ProfEntity getProfessor() {
        return professor;
    }

    public void setProfessor(ProfEntity professor) {
        this.professor = professor;
    }

    public List<StdEntity> getStudents() {
        return students;
    }

    public void setStudents(List<StdEntity> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubEntity subEntity)) return false;
        return Objects.equals(getId(), subEntity.getId()) && Objects.equals(getTitle(), subEntity.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle());
    }
}
