package com.mytry.restapimaven;

import java.io.Serializable;

/*
/*Autor: Carlos Alonso Escamilla Rocafuerte
/*Fecha de modificación: 05/Marzo/2020
/*Descripción: Clase Empleado que contiene sólo atributos, métodos get y set 
/*para manipular es estado de los mismos, en el contexto del sistema cada 
/*atributo de esta clase representa un campo de la tabla empleados, por
/*lo tanto nos servirá para realizar los registros
*/
public class Empleado implements Serializable {
    
    private int id;
    private String nombre;
    private String apellido;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    
    
}
