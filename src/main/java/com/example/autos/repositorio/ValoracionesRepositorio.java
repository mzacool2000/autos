
package com.example.autos.repositorio;

import com.example.autos.entidades.Valoraciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ValoracionesRepositorio extends JpaRepository<Valoraciones, String>  {
    
}
