package com.valcos98.schoolproject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "alumnos")
public class Alumnos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombres", nullable = false, length = 50)
    private String nombres;

    @Column(name = "apellido_paterno", nullable = false, length = 25)
    private String primerApellido;

    @Column(name = "apellido_materno", nullable = false, length = 25)
    private String segundoApellido;

    public Alumnos(String nombres, String primerApellido, String segundoApellido){
        this.nombres = nombres;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
    }

    public Alumnos( ){

    }


}
