package fca.cifca.sgd;

import fca.cifca.sgd.model.dto.TitulacionDTO;
import fca.cifca.sgd.controller.TitulacionController;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TitulacionControllerTest {

    @Autowired
    private TitulacionController titulacionController;

    @Test
    public void titulacionControllerTest() {
        TitulacionDTO dto = new TitulacionDTO();
        dto.setNumeroCuenta("213456789");
        dto.setNombre("Juan");
        dto.setPrimerApellido("Perez");
        dto.setSegundoApellido("");
        dto.setUniversidadProcedencia("Universidad Patito");
        dto.setPlantelProcedencia("Plantel Patito");
        dto.setLicenciatura("Administración");
        dto.setOpcionTitulacion("Alto nivel académico");
        dto.setModalidad("Escolarizado");
        dto.setFechaRegistro(LocalDate.ofEpochDay(20250827));
        dto.setFechaAplicacion(LocalDate.ofEpochDay(20260214));
        var result = titulacionController.generarComprobante(dto);
        assert(result != null);
    }

    @Test
    public void titulacionControllerTestNull() {
        TitulacionDTO titulacionDTO = null;
        assertThrows(ConstraintViolationException.class, () -> titulacionController.generarComprobante(titulacionDTO));
    }

    @Test
    public void titulacionControllerTestEmpty() {
        TitulacionDTO dto = new TitulacionDTO();
        assertThrows(ConstraintViolationException.class, () -> titulacionController.generarComprobante(dto));
    }

    @Test
    public void titulacionControllerTestPartial() {
        TitulacionDTO dto = new TitulacionDTO();
        dto.setNumeroCuenta("123456789");
        assertThrows(ConstraintViolationException.class, () -> titulacionController.generarComprobante(dto));
    }
}
