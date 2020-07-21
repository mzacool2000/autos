

package com.example.autos.controladores;

import com.example.autos.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class loginControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    
    @GetMapping("/login")
    public String login(@RequestParam (required = false) String error, ModelMap modelo) throws Error{
        if(error != null){
            modelo.put("error","Usuario o clave incorrectos.");
        }
        return "login.html";
    }
    
    @PostMapping("/registro")
    public String registro(ModelMap modelo, @RequestParam String email, @RequestParam String clave) throws Error{
       
        try{
           
        }catch(Error e){
            modelo.put("Error", e.getMessage());
            modelo.put("email", email);
        }
        
        
        
        return "login.html";
    }
}
  
    


