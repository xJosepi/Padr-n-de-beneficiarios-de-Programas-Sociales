package pe.edu.upeu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Beneficiario {
    private String folio;
    private String nombre;
    private String programa;
    private double montoMensual;
    private LocalDate fechaAlta;
    private String dni;
}