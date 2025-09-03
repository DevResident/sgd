package fca.cifca.sgd;

import fca.cifca.sgd.model.dto.TitulacionDTO;
import fca.cifca.sgd.controller.TitulacionController;
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
        dto.setFechaRegistro(LocalDate.of(2025, 8, 27));
        dto.setFechaAplicacion(LocalDate.of(2026, 2, 14));
        var result =  titulacionController.generarComprobante(dto);
        assert(result != null);
    }

    @Test
    public void titulacionControllerTestNull() {
        assertThrows(IllegalArgumentException.class, () -> titulacionController.generarComprobante(null));
    }

    @Test
    public void titulacionControllerTestEmpty() {
        TitulacionDTO dtoEmpty = new TitulacionDTO();
        assertThrows(IllegalArgumentException.class, () -> titulacionController.generarComprobante(dtoEmpty));
    }
}
