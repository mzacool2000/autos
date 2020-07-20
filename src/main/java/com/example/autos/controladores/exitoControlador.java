
package com.example.autos.controladores;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inicio")
public class exitoControlador {
    
    @PreAuthorize("hasAnyRole('MODULO_TABLERO')")
    @GetMapping("/inicio")
    public String inicio(){
        return"exito.html";
    }
    
    
}
