package com.valcos98.schoolproject.gradesComponents;

import java.util.Set;

import com.valcos98.schoolproject.groupsComponents.GroupModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "grado")
public class GradeModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 7)
    private String name;

    @OneToMany(mappedBy = "grade")
    private Set<GroupModel> groups;

    public GradeModel(String name){
        this.name = name;
    }
    public GradeModel(){}
}
