package com.mytry.restapimaven;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
/*Autor: Carlos Alonso Escamilla Rocafuerte
/*Fecha de modificación: 05/Marzo/2020
/*Descripción: Clase conexión a una base de datos de nombre empleados que se encuentra 
/*en un contenedor, mismo que está ligado através del puerto 7000 del host, se implementa 
/*patrón singleton y se incluyen dos métodos para ejecutar sentencias sql.
*/
public class ConnectionToDb implements Serializable{
    
    //Variable de instancia de clase única para implementar patrón singleton
    private static ConnectionToDb connectToDb;
    //Variable para gestionar la conexión
    private transient  Connection driverPostgres;
    private boolean successQuery = false;
    private transient  PreparedStatement preSentencia;
    
    
    //Constructor privado con las credenciales para cceder a la base de datos
    private ConnectionToDb() {        
        try {
            Class.forName("org.postgresql.Driver");
            driverPostgres = DriverManager.getConnection("jdbc:postgresql://10.40.7.40:7000/empleados",
                    "postgres", "12334");
            Logger.getLogger(ConnectionToDb.class.getName()).log(Level.INFO, "Me conecté papu");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
        }            
    }
    
    //Implementación patrón Singleton
    public static ConnectionToDb getInstance(){
        if(connectToDb == null)
            connectToDb = new ConnectionToDb();
        return connectToDb;
    }
    
  public boolean insert(int id, String nombre, String apellido){
        try{
            preSentencia = driverPostgres.prepareStatement("INSERT INTO empleados VALUES(?,?,?)");
            preSentencia.setInt(1,id);
            preSentencia.setString(2, nombre);
            preSentencia.setString(3, apellido);
            if(preSentencia.executeUpdate() > 0)
                successQuery = true;
        }catch(SQLException e){
            Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, e);
        }
        return successQuery;
    }
  
  
public boolean execute(int statementOption, String[] values){
    try{
        
        preSentencia = driverPostgres.prepareStatement(getStatement(statementOption));
        
        switch(statementOption){
            case 0:
                preSentencia.setInt(1,Integer.parseInt(values[0]));
                preSentencia.setString(2, values[1]);
                preSentencia.setString(3, values[2]);
                break;
            case 1:
                preSentencia.setInt(1,Integer.parseInt(values[0]));
                break;
            case 2:
                preSentencia.setString(1,values[1]);
                preSentencia.setString(2,values[2]);
                preSentencia.setInt(3,Integer.parseInt(values[0]));
                break;
            default:
                Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, "No se tomó una opción válida");
        }        
         if(preSentencia.executeUpdate() > 0)
                successQuery = true;
    }catch(SQLException e){
         Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, e);
    }
    return successQuery;
}  

public ResultSet executeQuery(int statementOption, int id){
    ResultSet rs = null;
    try{
        preSentencia = driverPostgres.prepareStatement(getStatement(statementOption));
        
        if(statementOption==3)
                preSentencia.setInt(1, id);
                
         rs = preSentencia.executeQuery();
    }catch(SQLException e){
        Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, e);
    }
    return rs;
}

    private String getStatement(int statementOption){
        String[] statements = new String[]{
                            "INSERT INTO empleados VALUES(?,?,?)",
                            "DELETE FROM empleados WHERE (id = ?)",
                            "UPDATE empleados SET nombre = ?, apellido = ? WHERE (id = ?)",
                            "SELECT * FROM empleados WHERE (id = ?)",
                            "SELECT * FROM empleados;"};
        
        return statements[statementOption];
    }
}