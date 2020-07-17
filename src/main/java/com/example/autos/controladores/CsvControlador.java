
package com.example.autos.controladores;

import com.example.autos.entidades.Comparaciones;
import com.example.autos.entidades.Valoraciones;
import com.example.autos.servicio.ComparacionesServicio;
import com.example.autos.servicio.ValoracionesServicio;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;





@Controller
public class CsvControlador {
    @Autowired
    private ComparacionesServicio comparacionesServicio;
    @Autowired
    private ValoracionesServicio valoracionesServicio;
    
    
    @RequestMapping("/comparacionesCSV")
     public void downloadCSV(HttpServletResponse response) throws IOException {

        String csvFileName = "comparaciones.csv";

        response.setContentType("text/csv");

        // creates mock data
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
            csvFileName);
        response.setHeader(headerKey, headerValue);


        // uses the Super CSV API to generate CSV data from the model data
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
            CsvPreference.STANDARD_PREFERENCE);

        String[] header = { "nombre del modelo del vehiculo1 ","marca del vehiculo1","vehiculo 2 mdelo","vehiculo 2 marca","vehiculo ganador modelo","vehiculo ganador marca"
                ,"fecha de comparacion"};

        csvWriter.writeHeader(header);

        for (Comparaciones obj : comparacionesServicio.resultados()) {
            csvWriter.write(obj.getArray(), header);
        }

      
        csvWriter.close();
    }
     
         @RequestMapping("/valoracionesCSV")
     public void downloadVCSV(HttpServletResponse response) throws IOException {

        String csvFileName = "valoraciones.csv";

        response.setContentType("text/csv");

        // creates mock data
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
            csvFileName);
        response.setHeader(headerKey, headerValue);


        // uses the Super CSV API to generate CSV data from the model data
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
            CsvPreference.STANDARD_PREFERENCE);

        String[] header = { "Vehiculo","marca", "Opinion", "Valoracion" };

        csvWriter.writeHeader(header);

        for (Valoraciones obj : valoracionesServicio.resultados()) {
            csvWriter.write(obj.getArray(), header);
        }

      
        csvWriter.close();
    }
     
     @GetMapping("/tablero")
     public String tablero(){
     return "usuariotablero.html";
     }
}
    

