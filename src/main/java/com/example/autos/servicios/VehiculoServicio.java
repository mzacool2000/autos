
package com.example.autos.servicios;

import com.example.autos.repositorios.VehiculoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehiculoServicio {
    
    @Autowired
    private VehiculoRepositorio vehiculoRepositorio;
    
    
}
