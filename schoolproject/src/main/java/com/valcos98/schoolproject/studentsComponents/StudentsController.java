package com.valcos98.schoolproject.studentsComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentsController {

    @Autowired
    private StudentsServices studentsServices;

    @GetMapping("/")
    public String studentsAssistence(Model model){
        List<StudentModel> students = studentsServices.getAll();
        if(students.size()>0){
            model.addAttribute("alumnos", students);
        }
        return "asistencias";
    }

    @GetMapping("/alumno")
    public String newStudent(Model model){
        StudentModel student = new StudentModel();
        model.addAttribute(student);
        return "nuevo_alumno";
    }

    @PostMapping("/alumno")
    public String uploadStudent(@ModelAttribute StudentModel student){
        studentsServices.saveStudent(student);
        return "redirect:/";
    }

    @PostMapping("/alumnos")
    public String uploadStudents(@RequestParam String students){
        List<StudentModel> studentsList = new ArrayList<>();
        String[] arrayStudents = students.split("\n");
        for (String string : arrayStudents) {
            String[] studentFullName = string.split(",");
            StudentModel student = new StudentModel(studentFullName[0], studentFullName[1], studentFullName[2]);
            studentsList.add(student);
        }
        studentsServices.saveAllStudents(studentsList);
        return "redirect:/";
    }

    @GetMapping("/alumno/{id}/delete")
    public String eliminaAlumno(@PathVariable Long id){
        Optional<StudentModel> studentOptional = studentsServices.getById(id);
        if(studentOptional.isPresent()){
            StudentModel student = studentOptional.get();
            studentsServices.deleteStudent(student);
        }
        return "redirect:/";
    }

    @GetMapping("/alumno/{id}/edit")
    public String getUpdateStudent(@PathVariable Long id, Model model){
        Optional<StudentModel> studentOptional = studentsServices.getById(id);
        if(studentOptional.isPresent()){
            StudentModel student = studentOptional.get();
            model.addAttribute("alumno", student);
        }
        return "actualiza_alumno";
    }

    @PostMapping("alumno/{id}")
    public String updateStudent(@PathVariable Long id, StudentModel student){
        Optional<StudentModel> studentOptional = studentsServices.getById(id);
        if(studentOptional.isPresent()){
            StudentModel existingStudent = studentOptional.get();
            existingStudent.setNames(student.getNames());
            existingStudent.setMiddleName(student.getMiddleName());
            existingStudent.setLastName(student.getLastName());
            studentsServices.saveStudent(existingStudent);
        }
        return "redirect:/";
    }
}
