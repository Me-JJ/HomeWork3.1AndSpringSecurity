package com.SchoolManagementSys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.util.List;
import java.util.Objects;

@Getter
@Entity
@Table(name = "Professor")
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ProfEntity extends AuditorEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "prof_stds")
    private List<StdEntity>students;

}
