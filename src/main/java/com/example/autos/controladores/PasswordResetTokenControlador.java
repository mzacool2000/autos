
package com.example.autos.controladores;

import com.example.autos.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class PasswordResetTokenControlador {
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    
}
