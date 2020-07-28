package com.example.autos.controladores;

import java.util.Map;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErroresControlador implements ErrorController{
    
    
    
    @RequestMapping(value = "/error", method = {RequestMethod.GET, RequestMethod.POST})
    public String renderErrorPage(HttpServletRequest httpRequest){
        ModelMap modelo = new ModelMap();
        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);
        
        switch(httpErrorCode){
            case 400:{
                errorMsg = "El recurso solicitado no existe.";
                break;
            }
            case 403:{
                errorMsg = "No tiene permisos para acceder al recurso.";
                break;
            }
            case 401:{
                errorMsg = "No se encuentra autorizado.";
                break;
            }
            case 404:{
                errorMsg = "El recurso solicitado no fue encontrado.";
                break;
            }
            case 500:{
                errorMsg = "Ocurrió un error interno.";
                break;
            }
        }
       
        modelo.put("error", httpErrorCode);
        modelo.put("mensaje", errorMsg);
        return "exito.html";
        }



  
    public int getErrorCode(HttpServletRequest httpRequest) {
        Map mapa = httpRequest.getParameterMap();
        for (Object Key : mapa.keySet()) {
            String[] valores = (String[]) mapa.get(Key);
            for (String valor : valores) {
                System.out.println(Key.toString()+":"+ valor);
                
            }
            
        }
    return mapa.hashCode();
    }

    @Override
    public String getErrorPath() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }

 
    
    
   
        
       
