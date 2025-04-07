package web.peasantgaming.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CheapSharkService {

    private final WebClient webClient;

    @Autowired
    public CheapSharkService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://www.cheapshark.com/api/1.0").build();
    }

    public Mono<String> fetchFromCheapShark(Integer id){
        String Url = "games?id=" + id + "?limit=5";

        return webClient.get()
                .uri(Url)
                .retrieve()
                .bodyToMono(String.class);

    }

}
