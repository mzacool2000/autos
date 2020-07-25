package com.example.autos.servicio;

import com.example.autos.entidades.Foto;
import com.example.autos.entidades.Marca;
import com.example.autos.entidades.Vehiculo;
import com.example.autos.enums.TipoCombustible;
import com.example.autos.repositorio.MarcaRepositorio;
import com.example.autos.repositorio.UsuarioRepositorio;
import com.example.autos.repositorio.VehiculoRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VehiculoServicio {

    @Autowired
    private VehiculoRepositorio vehiculoRepositorio;

    @Autowired
    private MarcaRepositorio marcaRepositorio;

    @Autowired
    private FotoServicio fotoServicio;




    @Transactional
    public void AgregarVehiculo(MultipartFile archivo, String idMarca, String modelo, String motor, TipoCombustible tipo, Integer cilindrada, Double emision, Double consumoRuta, Double consumoUrbano, Double consumoMixto, boolean habilitado) throws Error {

        
        validar(modelo, idMarca, motor, tipo, cilindrada, emision, consumoRuta, consumoUrbano, consumoMixto);
        
      
        Optional<Marca> respuestaM = marcaRepositorio.findById(idMarca);

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setMarca(respuestaM.get());
        vehiculo.setModelo(modelo);
        vehiculo.setMotor(motor);
        vehiculo.setTipo(tipo);
        vehiculo.setCilindrada(cilindrada);
        vehiculo.setEmision(emision);
        vehiculo.setConsumoUrbano(consumoUrbano);
        vehiculo.setConsumoRuta(consumoRuta);
        vehiculo.setConsumoMixto(consumoMixto);
        vehiculo.setHabilitado(habilitado);

        Foto foto = fotoServicio.guardar(archivo);
        vehiculo.setFoto(foto);
        vehiculoRepositorio.save(vehiculo);
    }

    @Transactional
    public void modificar(MultipartFile archivo, String id, String idMarca, String modelo, String motor, TipoCombustible tipo, Integer cilindrada, Double emision, Double consumoRuta, Double consumoUrbano, Double consumoMixto) throws Error {
        if (id.isEmpty() || id == null) {
           throw new Error("El Id del vehiculo no puede ser nulo."); 
        }
        validar(modelo, idMarca, motor, tipo, cilindrada, emision, consumoRuta, consumoUrbano, consumoMixto);

        Optional<Vehiculo> respuestaV = vehiculoRepositorio.findById(id);

        Optional<Marca> respuestaM = marcaRepositorio.findById(idMarca);
        if (respuestaV.isPresent()) {
            Vehiculo vehiculo = respuestaV.get();
            if (respuestaM.isPresent()) {
                if (archivo != null) {
                    vehiculo.setFoto(fotoServicio.guardar(archivo));
                }
                vehiculo.setMarca(respuestaM.get());
                vehiculo.setCilindrada(cilindrada);
                vehiculo.setConsumoMixto(consumoMixto);
                vehiculo.setConsumoRuta(consumoRuta);
                vehiculo.setConsumoUrbano(consumoUrbano);
                vehiculo.setEmision(emision);
                vehiculo.setId(id);
                vehiculo.setModelo(modelo);
                vehiculo.setMotor(motor);
                vehiculo.setTipo(tipo);
                vehiculoRepositorio.save(vehiculo);
            } else {
                throw new Error("No se ha encotrado la marca");
            }
        } else {
            throw new Error("No se encontro el vehiculo");
        }

    }

    public void eliminar(String id) throws Error {

        Optional<Vehiculo> respuestaV = vehiculoRepositorio.findById(id);

        if (respuestaV.isPresent()) {
            Vehiculo vehiculo = respuestaV.get();
            vehiculo.setHabilitado(false);
            vehiculoRepositorio.save(vehiculo);

        } else {
            throw new Error("No existe un ningun vehiculo con ese indicador");
        }
    }

    public void validar(String idMarca, String modelo, String motor, TipoCombustible tipo, Integer cilindrada, Double emision, Double consumoRuta, Double consumoUrbano, Double consumoMixto) throws Error {
        
        if (modelo == null || modelo.isEmpty()) {
            throw new Error("El nombre del modelo no puede ser vacio");
        }
        if (idMarca == null || idMarca.isEmpty()) {
            throw new Error("El id de la marca no puede ser vacio");
        }
        if (motor == null || motor.isEmpty()) {
            throw new Error("El motor no puede ser vacio");
        }
        if (tipo == null) {
            throw new Error("El tipo de combustible no puede ser vacio");
        }
        if (cilindrada == null) {
            throw new Error("La cilindrada no puede ser vacio");
        }
        if (emision == null) {
            throw new Error("La emision no puede ser vacio");
        }
        if (consumoRuta == null) {
            throw new Error("El consumo de ruta no puede ser vacio");
        }
        if (consumoUrbano == null) {
            throw new Error("El consumo urbano no puede ser vacio");
        }
        if (consumoMixto == null) {
            throw new Error("El consumo mixto no puede ser vacio");
        }

    }

}
