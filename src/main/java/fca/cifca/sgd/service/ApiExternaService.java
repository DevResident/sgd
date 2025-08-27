package fca.cifca.sgd.service;

import fca.cifca.sgd.model.dto.DocumentoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class ApiExternaService {

    private final WebClient webClient;

    public ApiExternaService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }
    public DocumentoDTO obtenerDocumento(String idDocumento) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/obtener/{id}").build(idDocumento))
                .retrieve()
                .bodyToMono(DocumentoDTO.class)
                .block();
    }
}
