
package com.example.autos.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private String apellido;
    private String email;
    private boolean habilitado;
    private String clave;

   @OneToOne
   private Foto foto;

    public Usuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }
   
  
   
   
    public Usuario(String id, String nombre, String apellido, String email, boolean habilitado, String clave) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.habilitado = habilitado;
        this.clave = clave;
    }
    

   
    public String getId() {
        return id;
    }

    
    public void setId(String id) {
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

   
    public String getEmail() {
        return email;
    }

  
    public void setEmail(String email) {
        this.email = email;
    }

    
    public boolean isHabilitado() {
        return habilitado;
    }

    
    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

   
    public String getClave() {
        return clave;
    }

   
    public void setClave(String clave) {
        this.clave = clave;
    }
    
}
