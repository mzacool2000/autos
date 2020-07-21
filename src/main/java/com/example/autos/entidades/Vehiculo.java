
package com.example.autos.entidades;

import com.example.autos.enums.TipoCombustible;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Vehiculo {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
        
    private String modelo;
    @ManyToOne
    private Marca marca;
    
    private String motor;
    
    private TipoCombustible tipo;
    private Integer cilindrada;
    private Double emision;
    private Double consumoRuta;
    private Double consumoUrbano;
    private Double consumoMixto;
    private Boolean habilitado;
    @OneToOne
    private Foto foto;

    public Vehiculo() {
    }

    
    
    
    
    /**
     * @return the id
     */
    
    
    
    public Vehiculo() {
    }

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
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the marca
     */
    public Marca getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    /**
     * @return the motor
     */
    public String getMotor() {
        return motor;
    }

    /**
     * @param motor the motor to set
     */
    public void setMotor(String motor) {
        this.motor = motor;
    }

    /**
     * @return the tipo
     */
    public TipoCombustible getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoCombustible tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the cilindrada
     */
    public Integer getCilindrada() {
        return cilindrada;
    }

    /**
     * @param cilindrada the cilindrada to set
     */
    public void setCilindrada(Integer cilindrada) {
        this.cilindrada = cilindrada;
    }

    /**
     * @return the emision
     */
    public Double getEmision() {
        return emision;
    }

    /**
     * @param emision the emision to set
     */
    public void setEmision(Double emision) {
        this.emision = emision;
    }

    /**
     * @return the consumoRuta
     */
    public Double getConsumoRuta() {
        return consumoRuta;
    }

    /**
     * @param consumoRuta the consumoRuta to set
     */
    public void setConsumoRuta(Double consumoRuta) {
        this.consumoRuta = consumoRuta;
    }

    /**
     * @return the consumoUrbano
     */
    public Double getConsumoUrbano() {
        return consumoUrbano;
    }

    /**
     * @param consumoUrbano the consumoUrbano to set
     */
    public void setConsumoUrbano(Double consumoUrbano) {
        this.consumoUrbano = consumoUrbano;
    }

    /**
     * @return the consumoMixto
     */
    public Double getConsumoMixto() {
        return consumoMixto;
    }

    /**
     * @param consumoMixto the consumoMixto to set
     */
    public void setConsumoMixto(Double consumoMixto) {
        this.consumoMixto = consumoMixto;
    }

    /**
     * @return the habilitado
     */
    public Boolean getHabilitado() {
        return habilitado;
    }

    /**
     * @param habilitado the habilitado to set
     */
    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    /**
     * @return the foto
     */
    public Foto getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(Foto foto) {
        this.foto = foto;
    }
    
    
    
    
}
