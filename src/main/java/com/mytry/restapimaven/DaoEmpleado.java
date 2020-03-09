package com.mytry.restapimaven;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
/*Autor: Carlos Alonso Escamilla Rocafuerte
/*Fecha de modificación: 05/Marzo/2020
/*Descripción: Clase que implementa la interfaz Idao<T> 
/*reemplazando la literal por el objeto Empleado. 
/*Por cada método se define la sentencia sql a la que hace referencia y
/*se concatena con los datos obtenidos del objeto
*/
public class DaoEmpleado implements IDao<Empleado>, Serializable{
    //Variable de instancia única para ejecutar sentencias sql
    private ConnectionToDb con2Db = ConnectionToDb.getInstance();
    //Variable para asignar la sentencia sql
    private String sql = "";
    
    @Override
    public boolean insertRecord(Empleado t) {
        String [] values = new String []{String.valueOf(t.getId()),t.getNombre(),t.getApellido()};
        return con2Db.execute(0,values);
    }

    @Override
    public boolean deleteRecord(Empleado t) {
        String [] values = new String []{String.valueOf(t.getId())};
        return con2Db.execute(1,values);
    }

    @Override
    public boolean updateRecord(Empleado t) {
        String [] values = new String []{String.valueOf(t.getId()),t.getNombre(),t.getApellido()};
        return con2Db.execute(2,values);
    }

    @Override
    public Empleado readOneRecord(Empleado t) {
        ResultSet registro = con2Db.executeQuery(3,t.getId());
        try {    
            while(registro.next()){
                t.setId(registro.getInt(1));
                t.setNombre(registro.getString(2));
                t.setApellido(registro.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    @Override
    public List<Empleado> readAllRecords() {
        
        ResultSet registros = con2Db.executeQuery(4,0);
        
        Empleado empleado = new Empleado();
        List<Empleado> listaEmpleados = new ArrayList<>();
        try{
             while(registros.next()){
                empleado.setId(registros.getInt(1));
                empleado.setNombre(registros.getString(2));
                empleado.setApellido(registros.getString(3));
                listaEmpleados.add(empleado);
            }
        }catch(SQLException ex){
            Logger.getLogger(DaoEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaEmpleados;
    }
    
}
