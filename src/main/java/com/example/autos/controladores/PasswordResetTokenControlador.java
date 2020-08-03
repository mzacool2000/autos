
package com.example.autos.controladores;

import com.example.autos.entidades.Usuario;
import com.example.autos.repositorio.UsuarioRepositorio;
import com.example.autos.servicio.NotificacionServicio;
import com.example.autos.servicio.PasswordResetTokenService;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PasswordResetTokenControlador {
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    private NotificacionServicio notificacionServicio;
    @Autowired
    private PasswordResetTokenService passwordResetTokenService;
    
    @GetMapping("/restoreP") // pagina para poner mail y restablecer contraseña
    public String restorep(){
    
    
    return "rer-contrase.html";
    }

    @PostMapping("/user/resetPassword") // controlador que verica que el mail esta en la base de datos y le mando el token
    public String resetPassword(ModelMap modelo, HttpServletRequest request, @RequestParam("email") String userEmail) throws Error {

        Usuario usuario = usuarioRepositorio.buscarPorEmail(userEmail); // hay un usuario con ese mail?
        if (usuario == null) {
            throw new Error("no se encontro el email"); // no hay un usuario con ese mail
        }
        String token = UUID.randomUUID().toString();
        passwordResetTokenService.createPasswordResetTokenForUser(usuario, token);
        notificacionServicio.enviar(notificacionServicio.constructResetTokenEmail("http://127.0.0.1:8080", request.getLocale(), token, usuario)); // contruyo el tojken y lo mando al usuario por mail
        modelo.put("titulo","link enviado");
        modelo.put("mensaje","pronto recibira un mail con el link de restablecer contraseña");
        return "exito.html";
    }

    @GetMapping("/user/changePassword{id}") // el uaurio recibio el mail y le dio click al vinculo
    public String editar(ModelMap modelo, @PathVariable String id) {

        Usuario usuario = passwordResetTokenService.buscarToken(id);// bysca ek toeken por id
        modelo.put("usuario", usuario);
        return "rpass.html"; // devuelve el usuario a la pagina para restablecer contraseña
    }

    @PostMapping("/rpassw")
    public String npasword(ModelMap modelo, @RequestParam String clave, @RequestParam String clave2, @RequestParam String id) {

        if (clave.equals(clave2)) { // me fijoo que las claves sean iguales
            Optional<Usuario> respuesta = usuarioRepositorio.findById(id); // busco el suario
            if (respuesta.isPresent()) {
                Usuario usuario = respuesta.get();
                String encriptada = new BCryptPasswordEncoder().encode(clave); // encripoto la lave y se la asigno
                usuario.setClave(encriptada);
                usuarioRepositorio.save(usuario);
                notificacionServicio.enviar("Felicidades su password cambio exitosamente", "Cambio de password", usuario.getEmail());
                modelo.put("titulo", "Felicidades");
                modelo.put("mensaje", "Su contraseña fue cambiada exitosamente");

                return "exito.html";
            }

            modelo.put("titulo", "Algo Salio mal");
            modelo.put("error", "intente mas tarde");

            return "exito.html";

        }
        modelo.put("titulo", "Algo Salio mal");
        modelo.put("error", "Las contraseñas no coinciden");
        return "exito.html";
    }

    private static class GenericResponse {

        private String message;
        private String error;

        public GenericResponse(String message) {
            super();
            this.message = message;
        }

        public GenericResponse(String message, String error) {
            super();
            this.message = message;
            this.error = error;
        }
    }
}
