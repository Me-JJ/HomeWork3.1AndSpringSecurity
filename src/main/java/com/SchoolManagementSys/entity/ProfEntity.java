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
@Table(name = "Professor")
@AllArgsConstructor
@NoArgsConstructor

public class ProfEntity extends AuditorEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfEntity that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle());
    }

    private String title;


    @OneToMany(mappedBy = "professor")
    @JsonIgnore
    private List<SubEntity> subjects;

    @ManyToMany
    @JoinTable(name = "prof_stds")
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

    public List<SubEntity> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubEntity> subjects) {
        this.subjects = subjects;
    }

    public List<StdEntity> getStudents() {
        return students;
    }

    public void setStudents(List<StdEntity> students) {
        this.students = students;
    }
}
