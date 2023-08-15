package com.valcos98.schoolproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.valcos98.schoolproject.models.Alumno;

public interface AlumnosRepository extends JpaRepository<Alumno,Long>{
    
}
