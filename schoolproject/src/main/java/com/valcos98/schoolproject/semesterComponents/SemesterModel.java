package com.valcos98.schoolproject.semesterComponents;

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
@Table(name = "semestre")
public class SemesterModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 7)
    private String name;

    @OneToMany(mappedBy = "semester")
    private Set<GroupModel> groups;

    public SemesterModel(String name){
        this.name = name;
    }
    public SemesterModel(){}
}
