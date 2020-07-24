
package com.example.autos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.autos.entidades.Foto;


@Repository
public interface FotoRepositorio extends JpaRepository<Foto, String> {

}
