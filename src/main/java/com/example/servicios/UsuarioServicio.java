package com.example.servicios;

import com.example.autos.entidades.Usuario;
import com.example.repositorios.UsuarioRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio{

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Transactional
    public void crearUsuario(String nombre, String apellido, String email, String clave, boolean habilitado) throws Error {
        
        validar( nombre, apellido, email, clave);
        
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setClave(clave);
        usuario.setHabilitado(habilitado);
        usuarioRepo.save(usuario);

    }
    @Transactional
    public void modificarUsuario(Usuario usuario, String nombre, String apellido, String email, String clave, boolean habilitado) throws Error {
        
        validar( nombre, apellido, email, clave);
        if(usuario != null){
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setEmail(email);
            usuario.setClave(clave);
            usuario.setHabilitado(habilitado);
            usuarioRepo.save(usuario);
        }else{
            throw new Error ("El usuario no puede ser nulo");
        }
    }
    
    
    @Transactional
    public void eliminarUsuario(String id)throws Error{
        Optional<Usuario> respuesta = usuarioRepo.findById(id);
        
        if(respuesta.isPresent()){
        usuarioRepo.delete(respuesta.get());
        }else{
            throw new Error ("El usuario no puede ser nulo");
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
    
    

}