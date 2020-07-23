/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.autos.controladores;

import com.example.autos.entidades.Marca;
import com.example.autos.entidades.Vehiculo;
import com.example.autos.repositorio.MarcaRepositorio;
import com.example.autos.repositorio.VehiculoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexControlador {
    @Autowired
    private VehiculoRepositorio vehiculoRepositorio;
    @Autowired
    private MarcaRepositorio marcaRepositorio;
            
    
   @GetMapping("/")
   public String inicio(ModelMap modelo){
       modelo.put("marcas",marcaRepositorio.findAll());
        
   Marca marca = new Marca();
       for (Vehiculo object : marca.getVehiculos()) {
           
       }
  
           
       
   return "index.html";
   }
}
