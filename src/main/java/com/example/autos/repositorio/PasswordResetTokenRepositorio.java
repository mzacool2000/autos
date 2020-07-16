
package com.example.autos.repositorio;

import com.example.autos.entidades.PasswordResetToken;
import com.example.autos.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PasswordResetTokenRepositorio extends JpaRepository<PasswordResetToken, String>  {
      @Query("SELECT u FROM PasswordResetToken t, IN(t.usuario) u WHERE t.token = :token")
      public Usuario buscarEmpleadoPorTokenl(@Param("token") String token);
    
}
