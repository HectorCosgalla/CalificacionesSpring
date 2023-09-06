package com.valcos98.schoolproject.groupsComponents;


import java.util.Set;

import com.valcos98.schoolproject.gradesComponents.GradeModel;
import com.valcos98.schoolproject.studentsComponents.StudentModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "grupos")
public class GroupModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "letra", nullable = false, length = 1)
    private String letter;

    @ManyToOne
    @JoinColumn(name = "grados_id", nullable = false)
    private GradeModel grade;

    @OneToMany(mappedBy = "group")
    private Set<StudentModel> students;

    public GroupModel(String letter){
        this.letter = letter;
    }

    public GroupModel(){}
}
