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
public class CvsComparaciones {
    
    private String modelo1;
    private String marca1;
    private String modelo2;
    private String marca2;
    private String ganadorModelo;
    private String ganadorMarca;
    private String fecha;

    public CvsComparaciones(String modelo1, String marca1, String modelo2, String marca2, String ganadorModelo, String ganadorMarca, String fecha) {
        this.modelo1 = modelo1;
        this.marca1 = marca1;
        this.modelo2 = modelo2;
        this.marca2 = marca2;
        this.ganadorModelo = ganadorModelo;
        this.ganadorMarca = ganadorMarca;
        this.fecha = fecha;
    }

    /**
     * @return the modelo1
     */
    public String getModelo1() {
        return modelo1;
    }

    /**
     * @param modelo1 the modelo1 to set
     */
    public void setModelo1(String modelo1) {
        this.modelo1 = modelo1;
    }

    /**
     * @return the marca1
     */
    public String getMarca1() {
        return marca1;
    }

    /**
     * @param marca1 the marca1 to set
     */
    public void setMarca1(String marca1) {
        this.marca1 = marca1;
    }

    /**
     * @return the modelo2
     */
    public String getModelo2() {
        return modelo2;
    }

    /**
     * @param modelo2 the modelo2 to set
     */
    public void setModelo2(String modelo2) {
        this.modelo2 = modelo2;
    }

    /**
     * @return the marca2
     */
    public String getMarca2() {
        return marca2;
    }

    /**
     * @param marca2 the marca2 to set
     */
    public void setMarca2(String marca2) {
        this.marca2 = marca2;
    }

    /**
     * @return the ganadorModelo
     */
    public String getGanadorModelo() {
        return ganadorModelo;
    }

    /**
     * @param ganadorModelo the ganadorModelo to set
     */
    public void setGanadorModelo(String ganadorModelo) {
        this.ganadorModelo = ganadorModelo;
    }

    /**
     * @return the ganadorMarca
     */
    public String getGanadorMarca() {
        return ganadorMarca;
    }

    /**
     * @param ganadorMarca the ganadorMarca to set
     */
    public void setGanadorMarca(String ganadorMarca) {
        this.ganadorMarca = ganadorMarca;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
