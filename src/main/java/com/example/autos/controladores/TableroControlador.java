
package com.example.autos.controladores;

import com.example.autos.repositorio.UsuarioRepositorio;
import com.example.autos.servicio.MarcaServicio;
import com.example.autos.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TableroControlador {
    
    @Autowired
    private MarcaServicio marcaServicio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
     @GetMapping("/tablero")
     public String tablero(ModelMap modelo){
         
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        System.out.println(name);
        
        modelo.put("sesion", usuarioRepositorio.buscarPorEmail(name));
        modelo.put("usuarios", usuarioRepositorio.findAll());
        
     return "usuariotablero.html";
     }
    
    
    
    
    
    
    
    @RequestMapping("/agregarmarca")
    public String agregarMarca(){
            
        return "marcacrea.html";
        }
    
       @PostMapping("/agregarM")
       public String agregarM(ModelMap modelo, @RequestParam String nombre, @RequestParam String habilitado){
       Boolean chek = false;
           if (habilitado.equals("on")) {
               chek = true; //se fija si esta tildado el check box 
           }
           System.out.println(habilitado);
           
           
        marcaServicio.agregarMarca(nombre, chek);
        
        modelo.put("titulo", "Felicidades");
        modelo.put("mensaje", "La marca "+nombre+" fue agregada correctamente");
       
       return "exito.html";
       }
       
       @GetMapping("/agregarUsuario")
       public String agregaruser(){
       
       return "Registro.html";
       }
       
       
}
