package web.peasantgaming.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MistralService {

    private final WebClient webClient;

    public MistralService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Value("${mistral.api.key}")
    private String MistralApiKey;


}
