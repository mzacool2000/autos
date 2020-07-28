
package com.example.autos.controladores;

import com.example.autos.entidades.Marca;
import com.example.autos.repositorio.MarcaRepositorio;
import com.example.autos.repositorio.UsuarioRepositorio;
import com.example.autos.servicio.MarcaServicio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

@Controller
public class TableroControlador {
    
    @Autowired
    private MarcaServicio marcaServicio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private MarcaRepositorio marcaRepositorio;
    
     @GetMapping("/tablero")
     public String tablero(ModelMap modelo){
         
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        System.out.println(name);
        
        modelo.put("sesion", usuarioRepositorio.buscarPorEmail(name));
        modelo.put("usuarios", usuarioRepositorio.findAll());
        
     return "usuariotablero.html";
     }
    
    
    
    
    
    
    
    @RequestMapping("/agregarmarca")
    public String agregarMarca(ModelMap modelo){
            
        modelo.put("marcas", marcaRepositorio.findAll());
        
        return "marcacrea.html";
        }
    
       @PostMapping("/agregarM")
       public String agregarM(ModelMap modelo,@RequestParam(required = false) String id, @RequestParam String nombre,
                              @RequestParam(required = false) String habilitado){
           System.out.println("id es " +id);
           System.out.println("nombre es " + nombre);
           System.out.println("habilitado es" +habilitado);
           boolean chk = !(habilitado == null);
           System.out.println(chk);
           
        if ( id == null || id.isEmpty()) {
            System.out.println("estoy agregando");
            marcaServicio.agregarMarca(nombre, chk);
            modelo.put("titulo", "Felicidades");
            modelo.put("mensaje", "La marca " + nombre + " fue agregada correctamente");
            return "exito.html";

        } else {
            System.out.println("estoy editando");
            marcaServicio.modificar(id, nombre, chk);
            modelo.put("titulo", "Felicidades");
            modelo.put("mensaje", "La marca " + nombre + " fue modificada correctamente");
            return "exito.html";
        }
    }
       
       @GetMapping("/agregarUsuario")
       public String agregaruser(){
       
       return "Registro.html";
       }
       
       
       
       
       @GetMapping("/editarmarca{id}")
public String editarm(ModelMap modelo , @PathVariable String id){

    Optional<Marca> respuesta = marcaRepositorio.findById(id);
    if (respuesta.isPresent()) {
        modelo.put("marcas", marcaRepositorio.findAll());
        modelo.put("marcaEd", respuesta.get());

    return "marcacrea.html";
    }
  return "redirect:/agregarM";
}
@PostMapping("/marcadelet")
public String autodelet(@RequestParam String id, ModelMap modelo){

    try {
        marcaServicio.eliminar(id);
        modelo.put("titulo", "Eliminada");
        modelo.put("mensaje", "La Marca fue eliminada con extio");
        
    } catch (Exception e) {
         marcaServicio.eliminar(id);
        modelo.put("error", "oke");
        modelo.put("mensaje", "La marca fue eliminada con extio");
        return "exito,html";
    }
    
return "exito.html";    
}
}