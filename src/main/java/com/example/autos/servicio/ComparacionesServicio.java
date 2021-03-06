
package com.example.autos.servicio;

import com.example.autos.entidades.Comparaciones;
import com.example.autos.entidades.Vehiculo;
import com.example.autos.repositorio.ComparacionesRepositorio;
import com.example.autos.repositorio.VehiculoRepositorio;
import java.util.Calendar;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComparacionesServicio {
    @Autowired
    private  ComparacionesRepositorio ComparacionesRepositorio;
    @Autowired
    private VehiculoRepositorio vehiculoRepositorio;
    @Transactional
    public void guardar(String idVehiculo1, String idVehiculo2, String idGanador)throws Error{
    
        validar(idVehiculo1);
        validar(idVehiculo2);
        validar(idGanador);
        
        Optional<Vehiculo> respuesta1 = vehiculoRepositorio.findById(idVehiculo1);
        Optional<Vehiculo> respuesta2 = vehiculoRepositorio.findById(idVehiculo2);
        Optional<Vehiculo> ganador = vehiculoRepositorio.findById(idGanador);
        if (respuesta1.isPresent() && respuesta2.isPresent() ) {
            Comparaciones comparacion = new Comparaciones();
            comparacion.setFechaComparacion(Calendar.getInstance());
            comparacion.setVehiculo1(respuesta1.get());
            comparacion.setVehiculo2(respuesta2.get());
            comparacion.setVehiculoganador(ganador.get());
            ComparacionesRepositorio.save(comparacion);
        }else{
        throw new Error("No se encontraron los vehiculos");
        }
    }
    public void validar(String texto)throws Error{
        if (texto.isEmpty() || texto == null){
            throw new Error("El id esta vacio");
        }
    }
}
