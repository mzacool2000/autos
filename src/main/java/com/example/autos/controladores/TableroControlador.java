
package com.example.autos.controladores;

import com.example.autos.servicio.MarcaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TableroControlador {
    
    @Autowired
    private MarcaServicio marcaServicio;
    
    @RequestMapping("/agregarmarca")
    public String agregarMarca(){
            
        return "marcacrea.html";
        }
    
       @PostMapping("/agregarM")
       public String agregarM(ModelMap modelo, @RequestParam String nombre, @RequestParam String habilitado){
       Boolean chek = false;
           if (habilitado.equals("on")) {
               chek = true;
           }
           System.out.println(habilitado);
           
           
        marcaServicio.agregarMarca(nombre, chek);
        
        modelo.put("titulo", "Felicidades");
        modelo.put("mensaje", "La marca "+nombre+" fue agregada correctamente");
       
       return "exito.html";
       }
}
