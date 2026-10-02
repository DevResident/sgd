package fca.cifca.sgd.model.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Titulacion dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TitulacionDTO {

    @NotBlank
    private String numeroCuenta;
    @NotBlank
    private String urlFotografia;
    @NotBlank
    private String nombre;
    @NotBlank
    private String primerApellido;
    //No es obligatorio en segundo apellido
    private String segundoApellido;
    @NotBlank
    private String universidadProcedencia;
    @NotBlank
    private String plantelProcedencia;
    @NotBlank
    private String licenciatura;
    @NotBlank
    private String opcionTitulacion;
    @NotBlank
    private String modalidad;
    @NotNull
    private LocalDate fechaRegistro;
}
