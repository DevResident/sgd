package fca.cifca.sgd;

import fca.cifca.sgd.model.dto.TitulacionDTO;
import fca.cifca.sgd.controller.TitulacionController;
import fca.cifca.sgd.service.PdfService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;

@SpringBootTest
public class GeneralTest {

    @Autowired
    private TitulacionController titulacionController;

    @Autowired
    private PdfService pdfService;

    private TitulacionDTO finalTitulacionDTO() {
        TitulacionDTO dto = new TitulacionDTO();
        dto.setNumeroCuenta("213456789");
        dto.setUrlFotografia("https://upload.wikimedia.org/wikipedia/commons/3/3d/Ana_de_Armas_by_Gage_Skidmore_2.jpg");
        dto.setNombre("Juan");
        dto.setPrimerApellido("Perez");
        dto.setSegundoApellido("");
        dto.setUniversidadProcedencia("Universidad Patito");
        dto.setPlantelProcedencia("Plantel Patito");
        dto.setLicenciatura("Administración");
        dto.setOpcionTitulacion("Alto nivel académico");
        dto.setModalidad("Escolarizado");
        dto.setFechaRegistro(LocalDate.of(2025, 8, 27));
        return dto;
    }

    @Test
    public void titulacionControllerTest() {
        TitulacionDTO dto = finalTitulacionDTO();
        var controller = titulacionController.generarComprobante(dto);
        assert (controller != null);
    }

    @Test
    public void pdfServiceTest() {
        String template = "comprobanteTitulacion";
        TitulacionDTO dto = finalTitulacionDTO();
        var service =  pdfService.renderHtmlToPdf(template, dto);
        assert (service != null);
    }
}
