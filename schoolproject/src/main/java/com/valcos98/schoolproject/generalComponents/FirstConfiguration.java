package com.valcos98.schoolproject.generalComponents;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.valcos98.schoolproject.semesterComponents.SemesterModel;
import com.valcos98.schoolproject.semesterComponents.SemesterRepository;

@Component
public class FirstConfiguration implements CommandLineRunner {

    private SemesterRepository semesterRepository;

    public FirstConfiguration(SemesterRepository semesterRepository){
        this.semesterRepository = semesterRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        List<SemesterModel> grades = semesterRepository.findAll();

        if (grades.size() == 0) {
            SemesterModel firstGrade = new SemesterModel("Primero");
            SemesterModel secondGrade = new SemesterModel("Segundo");
            SemesterModel thirdGrade = new SemesterModel("Tercero");
            SemesterModel fourthGrade = new SemesterModel("Cuarto");
            SemesterModel fifthGrade = new SemesterModel("Quinto");
            SemesterModel sixthGrade = new SemesterModel("Sexto");

            grades.add(firstGrade);
            grades.add(secondGrade);
            grades.add(thirdGrade);
            grades.add(fourthGrade);
            grades.add(fifthGrade);
            grades.add(sixthGrade);

            semesterRepository.saveAll(grades);
        }
    }
}
