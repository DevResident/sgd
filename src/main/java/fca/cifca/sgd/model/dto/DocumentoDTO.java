package fca.cifca.sgd.model.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DocumentoDTO {

    private String idDocumento;
    private String numeroCuenta;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String universidadProcedencia;
    private String plantelProcedencia;
    private String licenciatura;
    private String opcionTitulacion;
    private String modalidad;
    private LocalDate fechaRegistro;
    private LocalDate fechaAplicacion;;
}
