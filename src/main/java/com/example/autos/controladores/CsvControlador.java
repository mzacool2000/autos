
package com.example.autos.controladores;

import com.example.autos.entidades.Comparaciones;
import com.example.autos.entidades.CsvValoraciones;
import com.example.autos.entidades.CvsComparaciones;
import com.example.autos.entidades.Valoraciones;
import com.example.autos.servicio.ComparacionesServicio;
import com.example.autos.servicio.ValoracionesServicio;
import com.example.autos.servicio.VehiculoServicio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;





@Controller
public class CsvControlador {
    @Autowired
    private ComparacionesServicio comparacionesServicio;
    @Autowired
    private ValoracionesServicio valoracionesServicio;
    @Autowired
    private VehiculoServicio vehiculoServicio;
            
    
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

           String[] header = { "modelo1","marca1","modelo2","marca2","ganadorModelo","ganadorMarca","fecha" };
            
            csvWriter.writeHeader(header);
            final CellProcessor[] processors = comparacionesServicio.getProcessors();
            for (Comparaciones obj : comparacionesServicio.resultados()) {
                
                CvsComparaciones cvs= new CvsComparaciones(obj.getVehiculo1().getModelo(), obj.getVehiculo1().getMarca().getNombre(), obj.getVehiculo2().getModelo(),
                        obj.getVehiculo2().getMarca().getNombre(), obj.getVehiculoganador().getModelo(), obj.getVehiculoganador().getMarca().getNombre(),obj.getFechaComparacion().toString());
                
                csvWriter.write(cvs,header,processors);

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


        try ( // uses the Super CSV API to generate CSV data from the model data
            ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                        CsvPreference.STANDARD_PREFERENCE)) {
            String[] header = {"vehiculo","marca", "opinion"};
            
            csvWriter.writeHeader(header);
            final CellProcessor[] processors = valoracionesServicio.getProcessors();

            
            
            for (Valoraciones obj : valoracionesServicio.resultados()) {
                CsvValoraciones valoracion = new CsvValoraciones(obj.getVehiculo().getModelo(),obj.getVehiculo().getMarca().getNombre(),obj.getOpinion());
                csvWriter.write(valoracion,header,processors);
            }
            csvWriter.close();
        }
    }
     

}
    

