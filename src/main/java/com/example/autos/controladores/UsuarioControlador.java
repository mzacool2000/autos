
package com.example.autos.controladores;

import com.example.autos.entidades.Usuario;
import com.example.autos.enums.TipoCombustible;
import com.example.autos.repositorio.UsuarioRepositorio;
import com.example.autos.servicio.UsuarioServicio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class UsuarioControlador {
    
   @Autowired
   private UsuarioServicio usuarioServicio;
   @Autowired
   private UsuarioRepositorio usuarioRepositorio;
   
    @GetMapping("/usuario")
    public String usuario(){
        return "Registro.html";
    }
    
    @PostMapping("/usarioadd")
    public String login(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido,@RequestParam String email, @RequestParam String clave1,@RequestParam String clave2, @RequestParam(required = false) String habilitado ){
        try{
            boolean chk = !(habilitado == null);
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
    
    
    @PostMapping("/usarioedit")
    public String editar(ModelMap modelo, @RequestParam String id,@RequestParam String nombre, @RequestParam String apellido,@RequestParam String email, @RequestParam String clave1,@RequestParam String clave2, @RequestParam(required = false) String habilitado ){
        try{
            boolean chk = !(habilitado == null);
            if (clave1.equals(clave2)) {
                
                 usuarioServicio.modificarUsuario(id, nombre, apellido, email, clave2, chk);
            }
           
        }catch(Error e){
            modelo.put("Error", e.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            modelo.put("email", email);
            modelo.put("clave1", clave1);
            modelo.put("clave2", clave1);
            modelo.put("habilitado", habilitado);
            return "usuarioedit.html";
        }
        modelo.put("titulo", "Cuenta fue modificada Con exito");
        modelo.put("mensaje", "Los datos fueron actualizados");
        return "exito.html";
    }
    
    @PostMapping("/editu2")
    public String editaru(ModelMap modelo, @RequestParam String id){
       
          
            Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
            if (respuesta.isPresent()) {
            modelo.put("usuario", respuesta.get());
            return "usuarioedit.html";
            }else{
            modelo.put("error", "no se encotro al usuario");
            modelo.put("mensaje", "Los datos no fueron actualizados");
            return "usuariotablero.html";
        }
        
    }
        @GetMapping("/editarusuario{id}")
    public String editarus(ModelMap modelo, @PathVariable String id){
       
            
            Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
            if (respuesta.isPresent()) {
            modelo.put("usuario", respuesta.get());
            return "usuarioedit.html";
            }else{
            modelo.put("error", "no se encotro al usuario");
            modelo.put("mensaje", "Los datos no fueron actualizados");
            return "usuariotablero.html";
        }
        
    }
    @PostMapping("/eliminaru")
    public String eliminaru(ModelMap modelo, @RequestParam String id){
    
        try {
             usuarioServicio.eliminarUsuario(id);
        } catch (Exception e) {
            modelo.put("error", "no se pudo  borrar el usuario");
         return "exito.html";
        }
        
        modelo.put("titulo", "Exito");  
        modelo.put("mensaje", "El usuario fue eliminado");  
          
    return "exito.html"; 
    }
    
    
}
