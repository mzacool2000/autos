package com.example.autos.controladores;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErroresControlador extends Error{
    
    
    @RequestMapping(value = "/error", method = {RequestMethod.GET, RequestMethod.POST})
    public String renderErrorPage(HttpServletRequest httpRequest){
        ModelAndView errorPage = new ModelAndView("error");
        String errorMsg = "";
        int httpErrorCode = getErroresCode(httpRequest);
        
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
        
        errorPage.addObject("error", httpErrorCode);
        errorPage.addObject("mensaje", errorMsg);
        return"exito.html";
        }

    private int getErroresCode(HttpServletRequest httpRequest) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
    
   
        
       
