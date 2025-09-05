package fca.cifca.sgd.controller;

import lombok.extern.slf4j.Slf4j;
import fca.cifca.sgd.model.dto.TitulacionDTO;
import fca.cifca.sgd.service.PdfService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.NotNull;

@Slf4j
@Validated
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

    public ResponseEntity<byte[]> generarComprobante(@NotNull @Valid @RequestBody TitulacionDTO dto){
         try{
            log.info("Los datos del DTO fueron recibidos: {}", dto);

            byte[] pdf = pdfService.renderHtmlToPdf("comprobanteTitulacion", dto);

            log.info("El PDF se generó exitosamente para el número de cuenta {}", dto.getNumeroCuenta());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(
                    ContentDisposition.inline()
                            .filename(dto.getNumeroCuenta() + "-comprobante-titulacion" + ".pdf")
                            .build()
            );

            return ResponseEntity.ok().headers(headers).body(pdf);

        } catch (Exception e){
            log.error("Error al generar comprobante titulacion", e);
            throw new RuntimeException("Ocurrió un error al generar el comprobante titulacion", e);
        }
    }
}