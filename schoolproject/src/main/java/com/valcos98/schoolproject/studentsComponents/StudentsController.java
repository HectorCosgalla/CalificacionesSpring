package com.valcos98.schoolproject.studentsComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping("/alumnos")
public class StudentsController {

    private StudentsRepository studentsRepository;

    public StudentsController(StudentsRepository studentsRepository){
        this.studentsRepository = studentsRepository;
    }

    @GetMapping("/lista")
    public String studentsAssistence(Model model){
        List<StudentModel> students = studentsRepository.findAll();
        if(students.size()>0){
            model.addAttribute("students", students);
        }
        return "students_pages/list_students";
    }

    @GetMapping("/alumno")
    public String newStudent(Model model){
        StudentModel student = new StudentModel();
        model.addAttribute(student);
        return "students_pages/new_student";
    }

    @PostMapping("/alumno")
    public String uploadStudent(@ModelAttribute StudentModel student){
        studentsRepository.save(student);
        return "redirect:/";
    }

    @PostMapping("/alumnozz")
    public String uploadStudents(@RequestParam String students){
        List<StudentModel> studentsList = new ArrayList<>();
        String[] arrayStudents = students.split("\n");
        for (String string : arrayStudents) {
            String[] studentFullName = string.split(",");
            StudentModel student = new StudentModel(studentFullName[0], studentFullName[1], studentFullName[2]);
            studentsList.add(student);
        }
        studentsRepository.saveAll(studentsList);
        return "redirect:/";
    }

    @GetMapping("/alumno/{id}/delete")
    public String eliminaAlumno(@PathVariable Long id){
        Optional<StudentModel> studentOptional = studentsRepository.findById(id);
        if(studentOptional.isPresent()){
            StudentModel student = studentOptional.get();
            studentsRepository.delete(student);
        }
        return "redirect:/";
    }

    @GetMapping("/alumno/{id}/edit")
    public String getUpdateStudent(@PathVariable Long id, Model model){
        Optional<StudentModel> studentOptional = studentsRepository.findById(id);
        if(studentOptional.isPresent()){
            StudentModel student = studentOptional.get();
            model.addAttribute("student", student);
        }
        return "students_pages/update_student";
    }

    @PostMapping("alumno/{id}")
    public String updateStudent(@PathVariable Long id, StudentModel student){
        Optional<StudentModel> studentOptional = studentsRepository.findById(id);
        if(studentOptional.isPresent()){
            StudentModel existingStudent = studentOptional.get();
            existingStudent.setNames(student.getNames());
            existingStudent.setMiddleName(student.getMiddleName());
            existingStudent.setLastName(student.getLastName());
            studentsRepository.save(existingStudent);
        }
        return "redirect:/";
    }
}
