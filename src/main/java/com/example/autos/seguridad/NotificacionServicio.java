/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.autos.seguridad;


import com.example.autos.entidades.Usuario;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author chiri
 */
@Service
public class NotificacionServicio {
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Async("enviar")
    public void enviar(String cuerpo,String titulo, String mail){
         
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setFrom("no-reply@artrans.com.ar");
        mensaje.setTo(mail);
        mensaje.setSubject(titulo);
        mensaje.setText(cuerpo);
        mailSender.send(mensaje);
    }
        @Async("enviar")
    public void enviar(SimpleMailMessage mensaje){
        mailSender.send(mensaje);
    }
    
 public SimpleMailMessage constructResetTokenEmail(String contextPath, Locale locale, String token, Usuario usuario) {
    String url = contextPath + "/user/changePassword" + token;
    String message = "message.resetPassword";
    return constructEmail("Reset Password", message + " \r\n" + url, usuario);
}
 
private SimpleMailMessage constructEmail(String subject, String body, Usuario usuario) {
    SimpleMailMessage email = new SimpleMailMessage();
    email.setSubject(subject);
    email.setText(body);
    email.setTo(usuario.getEmail());
    email.setFrom("no-reply@autos.com.ar");
    return email;
}
    
}
