/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.autos.controladores;

import com.example.autos.entidades.Marca;
import com.example.autos.entidades.Vehiculo;
import com.example.autos.repositorio.ComparacionesRepositorio;
import com.example.autos.repositorio.MarcaRepositorio;
import com.example.autos.repositorio.VehiculoRepositorio;
import com.example.autos.servicio.ComparacionesServicio;
import com.example.autos.servicio.ValoracionesServicio;
import java.util.Optional;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class indexControlador {
    @Autowired
    private VehiculoRepositorio vehiculoRepositorio;
    @Autowired
    private MarcaRepositorio marcaRepositorio;
    @Autowired
    private ValoracionesServicio valoracionesServicio;
    @Autowired
    private ComparacionesServicio comparacionesServicio;
    @Autowired
    private ComparacionesRepositorio comparacionesRepositorio;
            
    
   @GetMapping("/")
   public String inicio(ModelMap modelo){
      
      
       modelo.put("vehiculos",vehiculoRepositorio.findAll()); 
   return "index.html";
   }
  

   
   @PostMapping("/valora")
   public String valora(ModelMap modelo, @RequestParam String id){
   
       Optional<Vehiculo> respuesta = vehiculoRepositorio.findById(id);
       if (respuesta.isPresent()) {
           
           modelo.put("autov", respuesta.get());
           return "index.html";
       }
       
   return "/";
   }
   
   @PostMapping("/valoraok")
   public String valoraok(@RequestParam String opinion, @RequestParam String id){
   
      valoracionesServicio.guardar(id, opinion);
   
      return "redirect:/";
   }
   
   @PostMapping("/valoras")
   public String valoras(ModelMap modelo, @RequestParam String id1, @RequestParam String id2){
   
       
       Optional<Vehiculo> respuesta1 = vehiculoRepositorio.findById(id1);
       Optional<Vehiculo> respuesta2 = vehiculoRepositorio.findById(id2);
       if (respuesta1.isPresent() && respuesta2.isPresent()) {
           modelo.put("autoc1", respuesta1.get());
           modelo.put("autoc2", respuesta2.get());
           return "index.html";
                   
       }
      return "redirect:/";
   }
   
   @PostMapping("/comparaok")
   public String comparaok(@RequestParam String id1,@RequestParam String id2, @RequestParam String ganador ){
   
      
       
      Optional<Vehiculo> respuesta = (ganador.equals(1)) ? vehiculoRepositorio.findById(id1) : vehiculoRepositorio.findById(id2);
       
      Vehiculo vehiculo = respuesta.get();
      comparacionesServicio.guardar(id1, id2, vehiculo.getId());
       
       
   return "redirect:/";
   }
   
   
}
