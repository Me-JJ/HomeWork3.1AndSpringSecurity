package com.SchoolManagementSys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Entity
@Table(name = "Subject")
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class SubEntity extends AuditorEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "professor")
    private ProfEntity professor;

}
