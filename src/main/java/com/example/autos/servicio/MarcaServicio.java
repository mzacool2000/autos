
package com.example.autos.servicio;

import com.example.autos.entidades.Marca;
import com.example.autos.repositorio.MarcaRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarcaServicio {
    
    @Autowired
    private MarcaRepositorio marcaRepositorio;
    
    @Transactional
    public void agregarMarca(String nombre, Boolean habilitado)throws Error{
    
        
        
        Marca marca = new Marca();
        
        marca.setNombre(nombre);
        marca.setHabilitado(habilitado);
        
        marcaRepositorio.save(marca);
    
    }
    @Transactional
    public void modificar(String idmarca, String nombre , Boolean habilitado){
    
<<<<<<< HEAD
        if (idmarca.isEmpty() || nombre.isEmpty() || habilitado == null) {
            throw new Error("los campos no pueden estar vacios");//tambein esta la baja de la marca
        }
=======
      
>>>>>>> 27ae095a99d1f9c21274fe016326e0977cc50d8c
       
        Optional<Marca> respuesta = marcaRepositorio.findById(idmarca);
        if (respuesta.isPresent()) {
            
            Marca marca = respuesta.get();
            marca.setNombre(nombre);
            marca.setHabilitado(habilitado);
            
            marcaRepositorio.save(marca);
            
        }
         
    }
    @Transactional
    public void eliminar(String id){
    
      marcaRepositorio.deleteById(id);
    
    }
    
}
