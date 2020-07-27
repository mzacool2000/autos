
package com.example.autos.controladores;

import com.example.autos.entidades.Foto;
import com.example.autos.repositorio.FotoRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class FotoControlador {
    @Autowired
    private FotoRepositorio fotoRepositorio;
    
    
    @GetMapping("/cargar/{id}")
    public ResponseEntity<byte[]> cargarfoto(@PathVariable String id){
        Optional<Foto> foto = fotoRepositorio.findById(id);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(foto.get().getMime()));
        return new ResponseEntity<>(foto.get().getContenido(), headers, HttpStatus.OK);
    }
    
    
}
