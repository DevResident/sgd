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
        assertThrows(ConstraintViolationException.class,
                () -> titulacionController.generarComprobante(dto));
    }
}
