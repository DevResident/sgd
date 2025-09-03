package fca.cifca.sgd;

import fca.cifca.sgd.model.dto.TitulacionDTO;
import fca.cifca.sgd.service.PdfService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

@SpringBootTest
public class PdfServiceTest {

    @Autowired
    private PdfService pdfService;

    @Test
    public void pdfServiceTest() {
        String template = "comprobanteTitulacion";
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
        var result =  pdfService.renderHtmlToPdf(template, dto);
        assert(result != null);
    }

    @Test
    public void pdfServiceTestNull() {
        assertThrows(IllegalArgumentException.class, () -> pdfService.renderHtmlToPdf(null, null));
    }

    @Test
    public void pdfServiceTestEmpty() {
        assertThrows(IllegalArgumentException.class, () -> pdfService.renderHtmlToPdf("", ""));
    }

    @Test
    public void pdfServiceTestEmptyNull() {
        assertThrows(IllegalArgumentException.class, () -> pdfService.renderHtmlToPdf(null, null));
    }

    @Test
    public void pdfServiceTestEmptyTemplate() {
        assertThrows(IllegalArgumentException.class, () -> pdfService.renderHtmlToPdf("", ""));
    }
}
