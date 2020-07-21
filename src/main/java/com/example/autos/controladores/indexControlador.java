/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.autos.controladores;

import com.example.autos.repositorio.VehiculoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexControlador {
    @Autowired
    private VehiculoRepositorio vehiculoRepositorio;
            
    
   @GetMapping("/")
   public String inicio(ModelMap modelo){
   
       modelo.put("vehiculos", vehiculoRepositorio.findAll());
               
       
   return "index.html";
   }
}
