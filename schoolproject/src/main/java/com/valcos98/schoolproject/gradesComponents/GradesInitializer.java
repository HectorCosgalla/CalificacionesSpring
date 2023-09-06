package com.valcos98.schoolproject.gradesComponents;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GradesInitializer implements CommandLineRunner{

    @Autowired
    private GradeRepository gradeRepository;

    @Override
    public void run(String... args){
        List<GradeModel> grades = gradeRepository.findAll();

        if (grades.size() == 0) {
            GradeModel firstGrade = new GradeModel("Primero");
            GradeModel secondGrade = new GradeModel("Segundo");
            GradeModel thirdGrade = new GradeModel("Tercero");
            GradeModel fourthGrade = new GradeModel("Cuarto");
            GradeModel fifthGrade = new GradeModel("Quinto");
            GradeModel sixthGrade = new GradeModel("Sexto");

            grades.add(firstGrade);
            grades.add(secondGrade);
            grades.add(thirdGrade);
            grades.add(fourthGrade);
            grades.add(fifthGrade);
            grades.add(sixthGrade);

            gradeRepository.saveAll(grades);
        }
    }
    
}
