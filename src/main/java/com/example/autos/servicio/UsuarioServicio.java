package com.example.autos.servicio;

import com.example.autos.entidades.Usuario;
import com.example.autos.repositorio.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioServicio  implements UserDetailsService  {

    
    @Autowired
    private UsuarioRepositorio usuarioRepo;
    @Autowired
    private NotificacionServicio notificacionServicio;

    @Transactional
    public void crearUsuario(String nombre, String apellido, String email, String clave, boolean habilitado) throws Error {
        
        validar( nombre, apellido, email, clave);
        
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        String encriptada = new BCryptPasswordEncoder().encode(clave);
        usuario.setClave(encriptada);
        usuario.setHabilitado(habilitado);
        usuarioRepo.save(usuario);
        notificacionServicio.enviar("Felicidades creaste tu cuenta en autos", "Autos", email);

    }
    @Transactional
    public void modificarUsuario(String id, String nombre, String apellido, String email, String clave, boolean habilitado) throws Error {
        
        validar( nombre, apellido, email, clave);
        Optional<Usuario> respuesta = usuarioRepo.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setEmail(email);
            String encriptada = new BCryptPasswordEncoder().encode(clave);
            usuario.setClave(encriptada);
            usuario.setHabilitado(habilitado);
            usuarioRepo.save(usuario);
            
        }else{
            throw new Error ("El usuario no puede ser nulo");
        }
    
    }
    
    @Transactional
    public void eliminarUsuario(String id)throws Error{
       
        Optional<Usuario> respuesta = usuarioRepo.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            usuarioRepo.delete(usuario);
        }else{
        throw new Error("No se encontro el id de usuario");
        }
    }
    
    public void validar(String nombre, String apellido, String email, String clave)throws Error{
        
        
        if(nombre == null || nombre.isEmpty()){
            throw new Error("El nombre del usuario no puede ser nulo");
        }
        if(apellido == null || apellido.isEmpty()){
            throw new Error("El apellido del usuario no puede ser nulo");
        }
        if(email == null || email.isEmpty()){
            throw new Error("El email del usuario no puede ser nulo");
        }
        if(clave == null || clave.isEmpty() || clave.length() < 6){
            throw new Error("la clave del usuario no puede ser nulo");
        }
        
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.buscarPorEmail(email);
        if (usuario != null) {

            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority p2 = new SimpleGrantedAuthority("MODULO_TABLERO");
            permisos.add(p2);
            
            ServletRequestAttributes attr =(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuario", usuario);
            User user = new User(usuario.getEmail(), usuario.getClave(), permisos);
            return user;

        } else {
            return null;
        }
    }

}
