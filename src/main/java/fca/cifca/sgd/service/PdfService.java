package fca.cifca.sgd.service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;
import java.util.Locale;

@Service
public class PdfService {

    private final SpringTemplateEngine templateEngine;

    public PdfService(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public byte[] renderHtmlToPdf(String template, Object data) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            Locale mx = Locale.forLanguageTag("es-MX");
            Context ctx = new Context(mx);
            ctx.setVariable("documento", data);

            String html = templateEngine.process(template, ctx);

            String baseUrl = new ClassPathResource("static/").getURL().toExternalForm();

            PdfRendererBuilder builder = new PdfRendererBuilder();

            builder.withHtmlContent(html, baseUrl);
            builder.toStream(baos);
            builder.run();

            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Ha ocurrido un error al generar el PDF", e);
        }
    }
}