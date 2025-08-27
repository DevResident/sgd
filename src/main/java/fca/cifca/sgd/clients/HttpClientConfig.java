package fca.cifca.sgd.clients;

import io.netty.channel.ChannelOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class HttpClientConfig {

    @Bean
    WebClient externalWebClient(@Value("${external.api.base-url}") String baseUrl) {
        return WebClient.builder()
            .baseUrl(baseUrl)
            .clientConnector(
                new ReactorClientHttpConnector(
                    HttpClient.create()
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                        .responseTimeout(Duration.ofSeconds(10))
                )
            )
            .build();
    }
}
