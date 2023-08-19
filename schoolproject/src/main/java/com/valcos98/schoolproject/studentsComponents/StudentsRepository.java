package com.valcos98.schoolproject.studentsComponents;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<StudentModel,Long>{
    
}
