package fca.cifca.sgd.controller;

import fca.cifca.sgd.model.dto.TitulacionDTO;
import fca.cifca.sgd.service.PdfService;
import static fca.cifca.sgd.util.ConstantesUtil.*;

import fca.cifca.sgd.util.ConstantesUtil;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@RestController
@RequestMapping("/titulacion")
public class TitulacionController {

    private final PdfService pdfService;

    public TitulacionController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    /**
     * Generar comprobante response entity.
     *
     * @param dto the dto
     * @return the response entity
     */
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
                            .filename(dto.getNumeroCuenta() + ConstantesUtil.TITULACION + ConstantesUtil.PDF)
                            .build()
            );

            return ResponseEntity.ok().headers(headers).body(pdf);

        } catch (Exception e){
            log.error("Error al generar el comprobante titulacion", e);
            throw new RuntimeException("Ocurrió un error al generar el comprobante titulacion", e);
        }
    }

}