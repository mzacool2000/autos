
package com.example.autos.entidades;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class Marca {
    
        @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
        
        private String nombre;
        private boolean habilitado;
        
        
        
    @OneToMany(mappedBy = "marca")
    private final List<Vehiculo> vehiculos = new ArrayList<>();

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    public Marca( boolean habilitado,String nombre) {
        this.nombre = nombre;
        this.habilitado = habilitado;
    }

    public Marca() {
    }
    
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the habilitado
     */
    public boolean isHabilitado() {
        return habilitado;
    }

    /**
     * @param habilitado the habilitado to set
     */
    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    /**
     * @return the vehiculos
     */
    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }
    
}
