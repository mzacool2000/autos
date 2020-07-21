
package com.example.autos.controladores;

import com.example.autos.enums.TipoCombustible;
import com.example.autos.repositorio.MarcaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VehiculoControlador {

@Autowired
private MarcaRepositorio marcaRepositorio;

    
@GetMapping("/agregarvehiculo")
public String agregavehiculo(ModelMap modelo){

    modelo.put("marcas", marcaRepositorio.findAll());
    
return "autonuevo.html";
}
}