package com.example.prueba_tecnica.application.usecases;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.prueba_tecnica.application.service.PersonaService;
import com.example.prueba_tecnica.domain.models.Persona;

@Service
public class ProcessExcelUploadUseCase {

    private final PersonaService personaService;

    public ProcessExcelUploadUseCase(PersonaService personaService) {
        this.personaService = personaService;
    }

    @Transactional
    public List<String> execute(List<Persona> personasDesdeExcel) {
        List<String> reporteProceso = new ArrayList<>();

        for (Persona p : personasDesdeExcel) {
            try {
                // El servicio ya valida: nombres (solo letras), id (10 dígitos) 
                // y la regla de no 4 números repetidos
                String resultado = personaService.registrarPersona(p);
                reporteProceso.add("ID " + p.getIdentificacion() + ": " + resultado);
            } catch (Exception e) {
                // Captura errores de duplicados o fallos de validación
                reporteProceso.add("ID " + p.getIdentificacion() + ": Error - " + e.getMessage());
            }
        }
        
        return reporteProceso;
    }
}
