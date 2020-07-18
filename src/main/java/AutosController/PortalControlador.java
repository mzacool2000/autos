package AutosController;

import com.example.autos.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;
    
    
    
   
    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/Registro")
    public String Registro() {
        return "Registro.html";
    }

    @PostMapping("/registrar")
    public String registrar(@RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String email,
            @RequestParam String clave,
            @RequestParam boolean habilitado) {
        
        System.out.println("Nombre:+nombre");
        System.out.println("Apellido:+apellido");
        System.out.println("Email:+email");
        System.out.println("Clave:+clave");
        System.out.println("Habilitado:+habilitado");        
        return "Resgistro.html";
        
        
        usuarioServicio.registrar(nombre,apellido,email,clave,habilitado);
        
    

