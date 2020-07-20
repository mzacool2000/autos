
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
    
        if (nombre.isEmpty() || nombre == null) {
            throw new Error("El nombre de la marca no debe estar vacio");
        }
        
        Marca marca = new Marca();
        
        marca.setNombre(nombre);
        marca.setHabilitado(habilitado);
        
        marcaRepositorio.save(marca);
    
    }
    @Transactional
    public void modificar(String idmarca, String nombre , Boolean habilitado){
    
        if (idmarca.isEmpty() || nombre.isEmpty() || habilitado == null) {
            throw new Error("los campos no pueden estar vacios");
        }
       
        Optional<Marca> respuesta = marcaRepositorio.findById(idmarca);
        if (respuesta.isPresent()) {
            
            Marca marca = respuesta.get();
            
            marca.setNombre(nombre);
            marca.setHabilitado(habilitado);
            
            marcaRepositorio.save(marca);
            
        }
    
    }
    
}
