package com.valcos98.schoolproject.courseComponents;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.valcos98.schoolproject.groupsComponents.GroupModel;
import com.valcos98.schoolproject.semesterComponents.SemesterModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Getter
@Setter
@Table(name = "materias")
public class CourseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "materia", nullable = false)
    private String courseName;

    @ManyToMany(mappedBy = "courses")
    private List<GroupModel> groups;

    @ManyToOne
    @JoinColumn(name = "semestre_id", nullable = false)
    private SemesterModel courseSemester;
    
    public CourseModel(){}
    public CourseModel(String courseName){
        this.courseName = courseName;
    }
    
}
