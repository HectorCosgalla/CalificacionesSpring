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
        

        if (semesters.size() == 0) {
            List<CourseModel> courses = courseRepository.findAll();
            courses = addSemesterAndCourses(semesters, courses);
            courseRepository.saveAll(courses);
        }
    }

    private List<CourseModel> addSemesterAndCourses(List<SemesterModel> semesters, List<CourseModel> courses){
            
        String[] arraySemesters= {
                "Primero",
                "Segundo",
                "Tercero",
                "Cuarto",
                "Quinto",
                "Sexto"
            };

        String[][] coursesBySemester = CsvProcessor.csvToStringForCourses(
        "C:/Users/Hector Cosgalla/Documents/GitHub/CalificacionesSpring/resources/malla_curricular.csv");

        for (int i = 0; i < coursesBySemester[0].length; i++) {
            SemesterModel semester = new SemesterModel(arraySemesters[i]);
            semesters.add(semester);
            for (int j = 0; j < coursesBySemester.length; j++) {
                courses = addACourse(coursesBySemester[j][i], courses, semester);          
            }
        semesterRepository.saveAll(semesters);
        }
        return courses;
    }

    private List<CourseModel> addACourse(String Course, List<CourseModel> courses, SemesterModel semester){
        if (!Course.isEmpty()) {
            CourseModel course = new CourseModel(Course);
            course.setCourseSemester(semester);
            courses.add(course);
        }
        return courses; 
    }
}
