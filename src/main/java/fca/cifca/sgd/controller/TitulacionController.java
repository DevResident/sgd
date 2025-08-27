package fca.cifca.sgd.controller;

import fca.cifca.sgd.model.dto.TitulacionDTO;
import fca.cifca.sgd.service.PdfService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/titulacion")
public class TitulacionController {

    private final PdfService pdfService;

    public TitulacionController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping(
            value = "/comprobante",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_PDF_VALUE
    )

    public ResponseEntity<byte[]> generarComprobante(@Valid @RequestBody TitulacionDTO dto) {

        byte[] pdf = pdfService.renderHtmlToPdf("comprobanteTitulacion", dto);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(
                ContentDisposition
                        .inline()
                        .filename("Comprobante-" + dto.getNumeroCuenta() + ".pdf")
                        .build()
        );

        return ResponseEntity.ok().headers(headers).body(pdf);
    }
}