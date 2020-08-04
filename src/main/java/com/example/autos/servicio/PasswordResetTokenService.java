


package com.example.autos.servicio;

import com.example.autos.entidades.PasswordResetToken;
import com.example.autos.entidades.Usuario;
import com.example.autos.repositorio.PasswordResetTokenRepositorio;
import com.example.autos.repositorio.UsuarioRepositorio;
import java.util.Calendar;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;




@Service
public class PasswordResetTokenService {
    
@Autowired
private PasswordResetTokenRepositorio passwordResetTokenRepositorio;

@Autowired
private UsuarioRepositorio usuarioRepositorio;
    
    
    @Transactional
    public String validatePasswordResetToken(String token) {
     
        Optional<PasswordResetToken> respuesta = passwordResetTokenRepositorio.findById(token);

          if (respuesta.isPresent()) {
            final  PasswordResetToken passToken = respuesta.get(); //ifternario 
            return !isTokenFound(passToken) ? "invalidToken" //devuelve esta el token? 
            : isTokenExpired(passToken) ? "expired" // es valido el token
            : null; // si todo ok null
        }
    
 return null;
}

        @Transactional
    public Usuario buscarToken(String token) { //busca el token que le madne al usuario por mail y me devuelve el usuario
        
       
         Usuario usuario = passwordResetTokenRepositorio.buscarEmpleadoPorTokenl(token);
         if (usuario == null) {
           
             return null;
        }
       
        return usuario; // como lo encontre lo devuelvo
    }

    
private boolean isTokenFound(PasswordResetToken passToken) { // si el token no es null
    return passToken != null;
}
 
private boolean isTokenExpired(PasswordResetToken passToken) { // si no expiro
    final Calendar cal = Calendar.getInstance();
    return passToken.getExpiryDate().before(cal.getTime());
}
    
    public void changeUserPassword(Usuario usuario, String password) { // cambia la contraseña
     
         String encriptada = new BCryptPasswordEncoder().encode(password);
        usuario.setClave(encriptada);
        usuarioRepositorio.save(usuario);
        
}
    
    
    public void createPasswordResetTokenForUser(Usuario usuario, String token) { // crea un token para mandar al mail
    PasswordResetToken myToken = new PasswordResetToken(token, usuario);
    passwordResetTokenRepositorio.save(myToken);
}
    
    
}
