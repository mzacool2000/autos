


package com.example.autos.seguridad;

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
            final  PasswordResetToken passToken = respuesta.get();
            return !isTokenFound(passToken) ? "invalidToken"
            : isTokenExpired(passToken) ? "expired"
            : null;
        }
    
 return null;
}

        @Transactional
    public Usuario buscarToken(String token) {
        
        System.out.println("llegue al empleado");
         Usuario usuario = passwordResetTokenRepositorio.buscarEmpleadoPorTokenl(token);
         if (usuario == null) {
             System.out.println("no se encontro empleado");
             return null;
        }
         System.out.println("encontre el empleado");
        return usuario;
    }

    
private boolean isTokenFound(PasswordResetToken passToken) {
    return passToken != null;
}
 
private boolean isTokenExpired(PasswordResetToken passToken) {
    final Calendar cal = Calendar.getInstance();
    return passToken.getExpiryDate().before(cal.getTime());
}
    
    public void changeUserPassword(Usuario usuario, String password) {
     
         String encriptada = new BCryptPasswordEncoder().encode(password);
        usuario.setClave(encriptada);
        usuarioRepositorio.save(usuario);
        
}
    
    
    public void createPasswordResetTokenForUser(Usuario usuario, String token) {
    PasswordResetToken myToken = new PasswordResetToken(token, usuario);
    passwordResetTokenRepositorio.save(myToken);
}
    
    
}
