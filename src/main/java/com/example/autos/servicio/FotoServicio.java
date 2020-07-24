/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.autos.servicio;

import com.example.autos.entidades.Foto;
import com.example.autos.repositorio.FotoRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoServicio {

    @Autowired
    private FotoRepositorio fotoRepositorio;

    @Transactional
    public Foto guardar(MultipartFile archivo) throws Error, IOException {
        if (archivo != null) {
            try {

                Foto foto = new Foto();
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());

                return fotoRepositorio.save(foto);
            } catch (Error e) {
                System.err.println(e.getMessage());
            }

        }
        return null;

    }

    public Foto actualizar(String idFoto, MultipartFile archivo) throws Error, IOException {

        if (archivo != null) {
            try {
                Foto foto = new Foto();

                if (idFoto != null) {
                    Optional<Foto> respuesta = fotoRepositorio.findById(idFoto);
                    if (respuesta.isPresent()) {
                        foto = respuesta.get();

                        foto.setMime(archivo.getContentType());
                        foto.setNombre(archivo.getName());
                        foto.setContenido(archivo.getBytes());

                        return fotoRepositorio.save(foto);

                    }
                }
            } catch (Error ex) {
                System.err.println(ex.getMessage());
            }
        }
        return null;
    }

}
