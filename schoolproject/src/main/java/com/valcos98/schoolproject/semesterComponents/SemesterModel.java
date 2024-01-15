package com.valcos98.schoolproject.semesterComponents;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.valcos98.schoolproject.courseComponents.CourseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Getter
@Setter
@Table(name = "semestres")
public class SemesterModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "semestre", nullable = false, length = 7)
    private String name;

    @OneToMany(mappedBy = "courseSemester")
    private List<CourseModel> courses;

    public SemesterModel(String name){
        this.name = name;
    }
    public SemesterModel(){}
}
