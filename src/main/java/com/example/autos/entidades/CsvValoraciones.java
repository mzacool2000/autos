/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.autos.entidades;

/**
 *
 * @author chiri
 */
public class CsvValoraciones {

    private String vehiculo;
    private String marca;
    private String opinion;

    /**
     * @return the vehiculo
     */
    public String getVehiculo() {
        return vehiculo;
    }

    public CsvValoraciones(String vehiculo, String marca, String opinion) {
        this.vehiculo = vehiculo;
        this.marca = marca;
        this.opinion = opinion;
    }

    /**
     * @param vehiculo the vehiculo to set
     */
    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the opinion
     */
    public String getOpinion() {
        return opinion;
    }

    /**
     * @param opinion the opinion to set
     */
    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

}
