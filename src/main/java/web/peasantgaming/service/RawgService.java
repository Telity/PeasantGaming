package web.peasantgaming.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import web.peasantgaming.dto.GameInfo;

@Service
public class RawgService {

    WebClient webClient;

    public RawgService(WebClient.Builder webClient) {
        this.webClient = webClient.build();
    }

    String api_key = "9086de6197a5495e9f1d60fa0725534b";

    public Mono<GameInfo> getRawgGame(String gameTitel){

        Mono<GameInfo> gameInfo = webClient.get()
                .uri("https://api.rawg.io/api/games/"+ gameTitel + "?key=" + api_key)
                .retrieve()
                .bodyToMono(GameInfo.class);

        return gameInfo;
    }

}
