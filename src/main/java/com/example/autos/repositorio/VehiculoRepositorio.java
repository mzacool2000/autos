
package com.example.autos.repositorio;

import com.example.autos.entidades.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface VehiculoRepositorio  extends JpaRepository<Vehiculo, String> {
    
    @Query("SELECT c FROM Vehiculo c Where c.id = :id")
    public Vehiculo BuscarVehiculo(@Param("id") String id);
    
}
