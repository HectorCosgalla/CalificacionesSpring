package com.valcos98.schoolproject.studentsComponents;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class StudentsServices {
    private final StudentsRepository studentsRepository;

    public StudentsServices(StudentsRepository studentsRepository){
        this.studentsRepository = studentsRepository;
    }

    public Optional<StudentModel> getById(Long id){
        return studentsRepository.findById(id);
    }

    public void saveStudent(StudentModel student){
        studentsRepository.save(student);
    }

    public void saveAllStudents(List<StudentModel> students){
        studentsRepository.saveAll(students);
    }

    public List<StudentModel> getAll(){
        return studentsRepository.findAll();
    }

    public void deleteStudent(StudentModel student){
        studentsRepository.delete(student);
    }   
}
