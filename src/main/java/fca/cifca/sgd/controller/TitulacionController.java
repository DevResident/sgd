package fca.cifca.sgd.controller;

import lombok.extern.slf4j.Slf4j;
import fca.cifca.sgd.model.dto.TitulacionDTO;
import fca.cifca.sgd.service.PdfService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Slf4j
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
        log.info("DTO recibido: {}", dto);

        byte[] pdf = pdfService.renderHtmlToPdf("comprobanteTitulacion", dto);
        log.info("PDF generado exitosamente para la cuenta {}", dto.getNumeroCuenta());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(
                ContentDisposition
                        .inline()
                        .filename(dto.getNumeroCuenta() + "-comprobante-titulacion" + ".pdf")
                        .build()
        );

        return ResponseEntity.ok().headers(headers).body(pdf);
    }
}
