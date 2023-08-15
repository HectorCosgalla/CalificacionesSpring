package com.valcos98.schoolproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.valcos98.schoolproject.models.Alumno;
import com.valcos98.schoolproject.services.AlumnosServices;

@Controller
public class AlumnosController {

    @Autowired
    private AlumnosServices alumnosServices;

    @GetMapping("/")
    public String asistenciasAlumnos(Model model){
        List<Alumno> alumnos = alumnosServices.getAll();
        if(alumnos.size()>0){
            model.addAttribute("alumnos",alumnos);
        }
        return "asistencias";
    }

    @GetMapping("/alumno")
    public String nuevoAlumno(Model model){
        Alumno alumno = new Alumno();
        model.addAttribute(alumno);
        return "nuevo_alumno";
    }

    @PostMapping("/alumno")
    public String guardaAlumno(@ModelAttribute Alumno alumno){
        alumnosServices.saveAlumno(alumno);
        return "redirect:/";
    }
}
