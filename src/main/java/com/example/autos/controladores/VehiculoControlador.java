package com.example.autos.controladores;

import com.example.autos.entidades.Vehiculo;
import com.example.autos.enums.TipoCombustible;
import com.example.autos.repositorio.MarcaRepositorio;
import com.example.autos.repositorio.VehiculoRepositorio;
import com.example.autos.servicio.VehiculoServicio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class VehiculoControlador {

@Autowired
private MarcaRepositorio marcaRepositorio;
@Autowired
private VehiculoRepositorio vehiculoRepositorio;
@Autowired
private VehiculoServicio vehiculoServicio;
    
@GetMapping("/agregarvehiculo")
public String agregavehiculo(ModelMap modelo){
    
    
    modelo.put("vehiculos",vehiculoRepositorio.findAll());

    modelo.put("marcas", marcaRepositorio.findAll());
    
return "autonuevo.html";
}




 


    @GetMapping("/editarvehiculo{id}")
    public String editarv(ModelMap modelo, @PathVariable String id) {

        Optional<Vehiculo> respuesta = vehiculoRepositorio.findById(id);
        if (respuesta.isPresent()) {
            System.out.println("Tengo el vehiculo");
            modelo.put("vehiculoEd", respuesta.get());
            modelo.put("vehiculos", vehiculoRepositorio.findAll());

            modelo.put("marcas", marcaRepositorio.findAll());

            System.out.println("Le mande todo al modelo");



            return "autonuevo.html";
        }
        return null;
    }

    

    @PostMapping("/adminvehiculo")
    private String adminvehiculo(ModelMap model, @RequestParam(required = false) String id, @RequestParam String modelo, @RequestParam String marcaid,
            @RequestParam String motor, @RequestParam int cilindrada, @RequestParam double emision, @RequestParam double consumoRuta, @RequestParam double consumoCiudad,
            @RequestParam double consumoMixto, @RequestParam TipoCombustible combustible, @RequestParam(required = false) String habilitado, @RequestParam(required = false) MultipartFile archivo) {



        try {
            boolean chk = !(habilitado == null);

            if (id.isEmpty() || id == null) {
                vehiculoServicio.AgregarVehiculo(archivo, marcaid, modelo, motor, combustible, cilindrada, emision, consumoRuta, consumoCiudad, consumoMixto, chk);
            } else {
                if (archivo != null) {

                    System.out.println("RECIBI LA IMAGEN!!!!!!!!!!!!!!");

                }
                vehiculoServicio.modificar(archivo, id, marcaid, modelo, motor, combustible, cilindrada, emision, consumoRuta, consumoCiudad, consumoMixto, chk);
                return "redirect:/agregarvehiculo";
            }
        } catch (Exception e) {
            model.put("error", e.getMessage());
            return "redirect:/agregarvehiculo";
        }
        return "redirect:/agregarvehiculo";
    }

    @PostMapping("/autodelet")
    public String autodelet(@RequestParam String id, ModelMap modelo) {

        try {
            vehiculoServicio.eliminar(id);
            modelo.put("titulo", "Eliminado");

            modelo.put("mensaje", "El vehiculo fue eliminado con exito");

            modelo.put("mensaje", "El vehiculo fue eliminado con extio");


        } catch (Exception e) {
            vehiculoServicio.eliminar(id);
            modelo.put("error", "oke");

            modelo.put("mensaje", "El vehiculo fue eliminado con exito");

            modelo.put("mensaje", "El vehiculo fue eliminado con extio");

            return "exito,html";
        }

        return "exito.html";

    }
}
