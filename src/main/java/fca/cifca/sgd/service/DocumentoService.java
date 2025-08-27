package fca.cifca.sgd.service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;

@Service
public class DocumentoService {

    private final SpringTemplateEngine templateEngine;

    public DocumentoService(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public byte[] renderOrderHtmlToPdf(Object model) {

        Context ctx = new Context();
        ctx.setVariable("obtener", model);
        String html = templateEngine.process("obtener", ctx);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(html, null);
            builder.toStream(baos);
            builder.run();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al generar el PDF", e);
        }
    }
}
