

package com.example.autos.controladores;





import com.example.autos.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

    @Controller
    @RequestMapping("/")
    public class PortalControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;
    
    
    
   
   
    
    @GetMapping("/login")
    public String login(@RequestParam(required = false)String error, @RequestParam(required = false) String logout,ModelMap model) {
        if(error != null){
        model.put("error", "Usuario o Clave incorrecta");}
       
        
        return "login.html";
    }

    @GetMapping("/Registro")
    public String Registro() {
        return "Registro.html";
    }

    @PostMapping("/registrar")
    public String registrar(
    ModelMap modelo,
    MultipartFile archivo,
    @RequestParam String nombre,
    @RequestParam String apellido,
    @RequestParam String email,
    @RequestParam String clave,
    @RequestParam String habilitado) {
    
    
    System.out.println("Nombre:+nombre");
    System.out.println("Apellido:+apellido");
    System.out.println("Email:+email");
    System.out.println("Clave:+clave");
    System.out.println("Habilitado:+habilitado"); 
 
     boolean chk = !(habilitado == null);
    
        try {
            usuarioServicio.crearUsuario(nombre, apellido, email, clave, chk);
        } catch (Error e) {
         modelo.put("error",e.getMessage()); 
         modelo.put("nombre", nombre);
         modelo.put("apellido", apellido); 
         modelo.put("email", email);
         modelo.put("clave", clave);
         modelo.put("habilitado", habilitado);
           return "Resgistro.html";
        }
        modelo.put("titulo","Bienvenido a Atmosfera Autos");
        modelo.put("descripcion","Tu usuario fue registrado exitosamente");
            return "exito.html";
    
    }
   }
    
   

    
    
     
    
     
   
   





        
        
        

        
    

