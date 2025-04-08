package web.peasantgaming.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import web.peasantgaming.dto.cheapshark.GameId;
import web.peasantgaming.dto.cheapshark.SharkDTO;
import web.peasantgaming.dto.cheapshark.SimplifiedCheapSharkDTO;
import web.peasantgaming.dto.cheapshark.StoreInfo;
import web.peasantgaming.dto.rawg.Store;

import java.util.List;
import java.util.Map;

@Service
public class CheapSharkService {

    private final WebClient webClient;

    @Autowired
    public CheapSharkService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://www.cheapshark.com/api/1.0").build();
    }

    public String getCheapSharkIdFromTitel(String title){
        GameId[] gameId = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("games")
                        .queryParam("title", title)
                        .build())
                .retrieve()
                .bodyToMono(GameId[].class)
                .block();

        if(gameId != null && gameId.length > 0){
            return gameId[0].getGameID();
        }
        return null;
    }

    public Mono<Map<String, String>> getActivesStoresMap(){
        return webClient.get()
                .uri("/stores")
                .retrieve()
                .bodyToFlux(StoreInfo.class)
                .filter(store -> store.getIsActive() == 1)
                .collectMap(StoreInfo::getStoreID, StoreInfo::getStoreName);
    }


    public Mono<List<SimplifiedCheapSharkDTO>> get5CheapestStoresByTitel(String titel){

        String gameId = getCheapSharkIdFromTitel(titel);


        Mono<List<StoreInfo>> stores = webClient.get()
                .uri("/stores")
                .retrieve()
                .bodyToFlux(StoreInfo.class)
                .collectList();


        Mono<String> specificGameID = webClient.get()
                .uri("games?id=" + gameId)
                .retrieve()
                .bodyToMono(String.class);


    }
}
