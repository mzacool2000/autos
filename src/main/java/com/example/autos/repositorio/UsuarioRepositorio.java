/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.autos.repositorio;

import com.example.autos.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author chiri
 */
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
    
}
