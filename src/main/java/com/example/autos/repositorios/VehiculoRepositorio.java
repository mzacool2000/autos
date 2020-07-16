
package com.example.autos.repositorios;

import com.example.autos.entidades.Vehiculo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepositorio extends JpaRepository<Vehiculo, String> {
    @Query("SELECT c FROM Vehiculo c Where c.vhehiculo.id = :id")
    public List<Vehiculo> BuscarVehiculo(@Param("id")String id);
}
