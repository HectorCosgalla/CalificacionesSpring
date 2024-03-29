package com.valcos98.schoolproject.studentsComponents;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.valcos98.schoolproject.groupsComponents.GroupModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Getter
@Setter
@Table(name = "alumnos")
public class StudentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombres", nullable = false, length = 50)
    private String names;

    @Column(name = "apellido_paterno", nullable = false, length = 25)
    private String middleName;

    @Column(name = "apellido_materno", nullable = false, length = 25)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "grupos_id")
    private GroupModel group;

    public StudentModel(String names, String middleName, String lastName){
        this.names = names;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public StudentModel(Long id, String names, String middleName, String lastName){
        this.id = id;
        this.names = names;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public StudentModel( ){

    }


}
