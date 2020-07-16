
package com.example.autos.repositorio;

import com.example.autos.entidades.Comparaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComparacionesRepositorio extends JpaRepository<Comparaciones, String> {
    
}
