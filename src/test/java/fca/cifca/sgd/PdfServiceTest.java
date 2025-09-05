package fca.cifca.sgd;

import fca.cifca.sgd.model.dto.TitulacionDTO;
import fca.cifca.sgd.service.PdfService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PdfServiceTest {

    @Autowired
    private PdfService pdfService;

    //Valida si la plantilla llega null y el DTO llega con null
    @Test
    public void pdfServiceTestNull() {
        String template = null;
        TitulacionDTO dto = null;
        assertThrows(IllegalArgumentException.class, () -> pdfService.renderHtmlToPdf(template, dto));
    }

    //Valida si la plantilla no tiene nombre y el DTO llega con vacio
    @Test
    public void pdfServiceTestEmpty() {
        String template = "";
        TitulacionDTO dto = new TitulacionDTO();
        assertThrows(IllegalArgumentException.class, () -> pdfService.renderHtmlToPdf(template, dto));
    }

    //Valida si la plantilla llega null y el DTO llega con vacio
    @Test
    public void pdfServiceTestNullEmpty() {
        String template = null;
        TitulacionDTO dto = new TitulacionDTO();
        assertThrows(IllegalArgumentException.class, () -> pdfService.renderHtmlToPdf(template, dto));
    }

    //Valida si la plantilla no tiene nombre y el DTO llega con null
    @Test
    public void pdfServiceTestEmptyNull() {
        String template = "";
        TitulacionDTO dto = null;
        assertThrows(IllegalArgumentException.class, () -> pdfService.renderHtmlToPdf(template, dto));
    }
}
