package com.valcos98.schoolproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.valcos98.schoolproject.models.Alumno;
import com.valcos98.schoolproject.repositories.AlumnosRepository;

@Service
public class AlumnosServices {
    public final AlumnosRepository alumnosRepository;

    public AlumnosServices(AlumnosRepository alumnosRepository){
        this.alumnosRepository = alumnosRepository;
    }

    public Optional<Alumno> getById(Long id){
        return alumnosRepository.findById(id);
    }

    public void saveAlumno(Alumno alumno){
        alumnosRepository.save(alumno);
    }

    public void saveAllAlumnos(List<Alumno> alumnos){
        alumnosRepository.saveAll(alumnos);
    }

    public List<Alumno> getAll(){
        return alumnosRepository.findAll();
    }
}
