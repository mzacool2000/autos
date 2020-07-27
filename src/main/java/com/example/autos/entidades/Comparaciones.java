/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.autos.entidades;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Comparaciones {
        
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @OneToOne
    private Vehiculo vehiculo1;
    
    @OneToOne
    private Vehiculo vehiculo2;
    
    @OneToOne
    private Vehiculo vehiculoganador;
    private Calendar fechaComparacion;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the vehiculo1
     */
    public Vehiculo getVehiculo1() {
        return vehiculo1;
    }

    /**
     * @param vehiculo1 the vehiculo1 to set
     */
    public void setVehiculo1(Vehiculo vehiculo1) {
        this.vehiculo1 = vehiculo1;
    }

    /**
     * @return the vehiculo2
     */
    public Vehiculo getVehiculo2() {
        return vehiculo2;
    }

    /**
     * @param vehiculo2 the vehiculo2 to set
     */
    public void setVehiculo2(Vehiculo vehiculo2) {
        this.vehiculo2 = vehiculo2;
    }

 

    /**
     * @return the fechaComparacion
     */
    public Calendar getFechaComparacion() {
        return fechaComparacion;
    }

    /**
     * @param fechaComparacion the fechaComparacion to set
     */
    public void setFechaComparacion(Calendar fechaComparacion) {
        this.fechaComparacion = fechaComparacion;
    }

    /**
     * @return the vehiculoganador
     */
    public Vehiculo getVehiculoganador() {
        return vehiculoganador;
    }

    /**
     * @param vehiculoganador the vehiculoganador to set
     */
    public void setVehiculoganador(Vehiculo vehiculoganador) {
        this.vehiculoganador = vehiculoganador;
    }
    

        
    
}
