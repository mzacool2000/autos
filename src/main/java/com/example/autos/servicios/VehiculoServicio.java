package com.example.autos.servicios;

import com.example.autos.entidades.Vehiculo;
import com.example.autos.enums.TipoCombustible;
import com.example.autos.repositorios.VehiculoRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VehiculoServicio {

    @Autowired
    private VehiculoRepositorio vehiculoRepositorio;
    
    

    //AGREGAR -usuarioRepositorio-
    //AGREGAR -fotoServicio-
    @Transactional
    public void AgregarVehiculo( MultipartFile archivo ,String id, Marca marca, String modelo, String motor, TipoCombustible tipo, Integer cilindrada, Double emision, Double consumoRuta, Double consumoUrbano, Double consumoMixto, boolean habilitado) throws Error {

        //AGREGAR USUARIOREPOSITORIO
        validar(modelo, marca);
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setMarca(marca);
        vehiculo.setModelo(modelo);
        vehiculo.setMotor(motor);
        vehiculo.setTipo(tipo);
        vehiculo.setCilindrada(cilindrada);
        vehiculo.setEmision(emision);
        vehiculo.setConsumoUrbano(consumoUrbano);
        vehiculo.setConsumoRuta(consumoRuta);
        vehiculo.setConsumoMixto(consumoMixto);
        vehiculo.setHabilitado(habilitado);

//        Foto foto = fotoServicio.guardar(archivo);
        vehiculo.setFoto(foto);
        vehiculoRepositorio.save(vehiculo);
    }
    public void modificar(MultipartFile archivo ,String id, Marca marca, String modelo, String motor, TipoCombustible tipo, Integer cilindrada, Double emision, Double consumoRuta, Double consumoUrbano, Double consumoMixto, boolean habilitado )throws Error{
         validar(modelo, marca);
         
         Optional<Vehiculo> respuesta = vehiculoRepositorio.findById(id);
         if (respuesta.isPresent()) {
            Vehiculo vehiculo = respuesta.get();
             if (vehiculo.) {
                 
             }
        }
         
    }
    
}
