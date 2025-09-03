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

    public ResponseEntity<byte[]> generarComprobante(@Valid @RequestBody TitulacionDTO dto){
        if (dto == null) {
            throw new IllegalArgumentException("El DTO no puede ser null");
        }
        if (isEmpty(dto)) {
            throw new IllegalArgumentException("El DTO no puede estar vacío");
        }

        log.info("DTO recibido: {}", dto);

        byte[] pdf = pdfService.renderHtmlToPdf("comprobanteTitulacion", dto);
        log.info("PDF generado exitosamente para la cuenta {}", dto.getNumeroCuenta());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(
                ContentDisposition.inline()
                        .filename(dto.getNumeroCuenta() + "-comprobante-titulacion" + ".pdf")
                        .build()
        );

        return ResponseEntity.ok().headers(headers).body(pdf);
    }

    private boolean isEmpty(TitulacionDTO dto) {
        return isBlank(dto.getNumeroCuenta())
                && isBlank(dto.getNombre())
                && isBlank(dto.getPrimerApellido())
                && isBlank(dto.getSegundoApellido())
                && isBlank(dto.getUniversidadProcedencia())
                && isBlank(dto.getPlantelProcedencia())
                && isBlank(dto.getLicenciatura())
                && isBlank(dto.getOpcionTitulacion())
                && isBlank(dto.getModalidad())
                && dto.getFechaRegistro() == null
                && dto.getFechaAplicacion() == null;
    }

    private boolean isBlank(String s) { return s == null || s.isBlank(); }
}
