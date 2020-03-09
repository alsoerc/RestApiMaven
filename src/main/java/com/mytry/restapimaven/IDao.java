package com.mytry.restapimaven;
import java.util.List;

/*
/*Autor: Carlos Alonso Escamilla Rocafuerte
/*Fecha de modificación: 05/Marzo/2020
/*Descripción: Interfaz que define sólo los métodos a realizar sin implementación.
*/

/*<T> representa el uso de generics en Java, la literal T puede reemplazarse por cualquier tipo de dato,
/*para fines del contexto del sistema, ésta será sustituida por el objeto Empleado en la clase 
/*que lo implemente.
*/
public interface IDao<T>{
    boolean insertRecord(T t);
    boolean deleteRecord(T t);
    boolean updateRecord(T t);
    T readOneRecord(T t);
    List<T>readAllRecords();
}
