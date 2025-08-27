package fca.cifca.sgd.controller;

import fca.cifca.sgd.model.dto.DocumentoDTO;
import fca.cifca.sgd.service.ApiExternaService;
import fca.cifca.sgd.service.DocumentoService;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/reports")
public class DocumentoController {

    private final ApiExternaService client;
    private final DocumentoService documentoService;

    public DocumentoController (ApiExternaService client, DocumentoService documentoService) {
        this.client = client;
        this.documentoService = documentoService;
    }

    @GetMapping(value = "/obtener/{idDocumento}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getDocumento(@PathVariable String idDocumento) {
        DocumentoDTO obtener= client.obtenerDocumento(idDocumento);
        byte[] documento = documentoService.renderOrderHtmlToPdf(obtener);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(
            ContentDisposition.inline().filename("Comprobante-Titulacion" + idDocumento + ".pdf").build());

    return ResponseEntity.ok().headers(headers).body(documento);
    }
}
