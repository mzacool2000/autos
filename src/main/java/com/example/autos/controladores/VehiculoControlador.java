
package com.example.autos.controladores;

import com.example.autos.entidades.Vehiculo;
import com.example.autos.enums.TipoCombustible;
import com.example.autos.repositorio.MarcaRepositorio;
import com.example.autos.repositorio.VehiculoRepositorio;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VehiculoControlador {

@Autowired
private MarcaRepositorio marcaRepositorio;
@Autowired
private VehiculoRepositorio vehiculoRepositorio;

    
@GetMapping("/agregarvehiculo")
public String agregavehiculo(ModelMap modelo){
    
   
    
    
    modelo.put("vehiculos",vehiculoRepositorio.findAll());

    modelo.put("marcas", marcaRepositorio.findAll());
    
return "autonuevo.html";
}
@GetMapping("/editarvehiculo{id}")
public String editarv(ModelMap modelo , @PathVariable String id){

    Optional<Vehiculo> respuesta = vehiculoRepositorio.findById(id);
    if (respuesta.isPresent()) {
        System.out.println("tengo el vehiculo");
        modelo.put("vehiculoEd", respuesta.get());
        modelo.put("vehiculos",vehiculoRepositorio.findAll());
        modelo.put("marcas", marcaRepositorio.findAll());
        System.out.println("le mande todo al modelo");
    return "autonuevo.html";
    }

return "/agregarvehiculo";
}
}