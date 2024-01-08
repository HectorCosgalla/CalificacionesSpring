package com.valcos98.schoolproject.generalComponents;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.valcos98.schoolproject.courseComponents.CourseModel;
import com.valcos98.schoolproject.courseComponents.CourseRepository;
import com.valcos98.schoolproject.semesterComponents.SemesterModel;
import com.valcos98.schoolproject.semesterComponents.SemesterRepository;

@Component
public class FirstConfiguration implements CommandLineRunner {

    private SemesterRepository semesterRepository;
    private CourseRepository courseRepository;

    public FirstConfiguration(SemesterRepository semesterRepository, CourseRepository courseRepository){
        this.semesterRepository = semesterRepository;
        this.courseRepository = courseRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        List<SemesterModel> semesters = semesterRepository.findAll();
        List<CourseModel> courses = courseRepository.findAll();

        if (semesters.size() == 0) {
            SemesterModel firstSemester = new SemesterModel("Primero");
            SemesterModel secondSemester = new SemesterModel("Segundo");
            SemesterModel thirdSemester = new SemesterModel("Tercero");
            SemesterModel fourthSemester = new SemesterModel("Cuarto");
            SemesterModel fifthSemester = new SemesterModel("Quinto");
            SemesterModel sixthSemester = new SemesterModel("Sexto");
            
            semesters.add(firstSemester);
            semesters.add(secondSemester);
            semesters.add(thirdSemester);
            semesters.add(fourthSemester);
            semesters.add(fifthSemester);
            semesters.add(sixthSemester);

            semesterRepository.saveAll(semesters);

            CourseModel englishOne = new CourseModel("ingles 1");
            CourseModel englishTwo = new CourseModel("ingles 2");
            CourseModel englishThree = new CourseModel("ingles 3");
            CourseModel englishFour = new CourseModel("ingles 4");
            CourseModel englishFive = new CourseModel("ingles 5");
            CourseModel Humanitiesthree = new CourseModel("Humanidades 3");

            englishOne.setCourseSemester(firstSemester);
            englishTwo.setCourseSemester(secondSemester);
            englishThree.setCourseSemester(thirdSemester);
            englishFour.setCourseSemester(fourthSemester);
            englishFive.setCourseSemester(fifthSemester);
            Humanitiesthree.setCourseSemester(sixthSemester);

            courses.add(englishOne);
            courses.add(englishTwo);
            courses.add(englishThree);
            courses.add(englishFour);
            courses.add(englishFive);
            courses.add(Humanitiesthree);

            courseRepository.saveAll(courses);
        }
    }
}
