/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytry.restapimaven;

import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author alsorc
 */
@Path("generic")
public class GenericResource {
    private IDao myDao;
    private Empleado empleado;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
            myDao = new DaoEmpleado();
            empleado = new Empleado();
    }

    /**
     * Retrieves representation of an instance of com.mytry.restapimaven.GenericResource
     * @return an instance of java.lang.String
     */
     @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Empleado> getAll() {
        
        return myDao.readAllRecords();
        
    }
 
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Empleado getOneByID(@PathParam("id")String id) {
        empleado.setId(Integer.parseInt(id));
        return (Empleado) myDao.readOneRecord(empleado);
        
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Empleado empleado) {
        if(myDao.updateRecord(empleado))
            return "OK";
        else
            return "FAIL";
        
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String insert(Empleado empleado){
        if (myDao.insertRecord(empleado))
            return "OK";
        else
            return "FAIL";
        
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public String deleteOne(@PathParam("id") String id){
        empleado.setId(Integer.parseInt(id));
        if(myDao.deleteRecord(empleado))
            return "OK";
        else
            return "FAIL";
    }
    
}
