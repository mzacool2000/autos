
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
public class UsuarioControlador {
    
   @Autowired
   private UsuarioServicio usuarioServicio;
   
    @GetMapping("/usuario")
    public String usuario(){
        return "vista del usuario";
    }
    
    @PostMapping("/usarioadd")
    public String login(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido,@RequestParam String email, @RequestParam String clave1,@RequestParam String clave2, @RequestParam Boolean habilitado ){
        try{
            boolean chk = (habilitado.equals("on")) ? true : false;
            if (clave1.equals(clave2)) {
                
                 usuarioServicio.crearUsuario(nombre, apellido, email, clave1, chk);
            }
           
        }catch(Error e){
            modelo.put("Error", e.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            modelo.put("email", email);
            modelo.put("clave", clave1);
            modelo.put("habilitado", habilitado);
        }
        modelo.put("titulo", "Cuenta creada Con exito");
        modelo.put("mensaje", "El usuario recibira un mail pronto");
        return "exito.html";
    }
    
}
