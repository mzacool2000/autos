
package com.example.autos.servicio;

import com.example.autos.entidades.Valoraciones;
import com.example.autos.entidades.Vehiculo;
import com.example.autos.repositorio.ValoracionesRepositorio;
import com.example.autos.repositorio.VehiculoRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;

@Service
public class ValoracionesServicio  {
    
    @Autowired
    private ValoracionesRepositorio valoracionesRepositorio;
    @Autowired
    private VehiculoRepositorio vehiculoRepositorio;
    
        @Transactional
    public void guardar(String idVehiculo1, String opinion)throws Error{
    
            if (idVehiculo1.isEmpty() || idVehiculo1 == null) {
              throw new Error("debe elegir un vehuculo");
            }
        
        Optional<Vehiculo> respuesta1 = vehiculoRepositorio.findById(idVehiculo1);
       
        if (respuesta1.isPresent()) {
            Valoraciones valoraciones = new Valoraciones();
            valoraciones.setOpinion(opinion);
            valoraciones.setVehiculo(respuesta1.get());
            valoracionesRepositorio.save(valoraciones);
                    
        }else{
        throw new Error("No se encontraron los vehiculos");
        }
    }
    
    
    
    
    public List<Valoraciones> resultados() throws Error{
         
      List<Valoraciones> valoraciones = new ArrayList<>();
      valoraciones =  valoracionesRepositorio.findAll();
        
     return valoraciones;   
    }
    
     public CellProcessor[] getProcessors() {
        
        final CellProcessor[] processors = new CellProcessor[] { 
                new NotNull(), // modelo
                new NotNull(), // marca
                new NotNull(), // opinion
  
        };
        
        return processors;
    
    
    
}
}