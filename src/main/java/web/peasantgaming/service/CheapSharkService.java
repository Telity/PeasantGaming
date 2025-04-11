package web.peasantgaming.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import web.peasantgaming.dto.cheapshark.*;
import web.peasantgaming.dto.rawg.Store;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CheapSharkService {

    private final WebClient webClient;

    @Autowired
    public CheapSharkService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://www.cheapshark.com/api/1.0").build();
    }

    public String getCheapSharkIdFromTitle(String title){
        GameId[] gameId = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/games")
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


    public List<SimplifiedCheapSharkDTO> get5CheapestStoresByTitle(String title){

        String gameId = getCheapSharkIdFromTitle(title);
        if(gameId == null){
            return Collections.emptyList();
        }

        StoreInfo[] allStores = webClient.get()
                .uri("/stores")
                .retrieve()
                .bodyToMono(StoreInfo[].class)
                .block();

        Map<String, String> activeStoreMap = Arrays.stream(allStores)
                .filter(store -> store.getIsActive() != null && store.getIsActive() == 1)
                .collect(Collectors.toMap(StoreInfo::getStoreID, StoreInfo::getStoreName));

        SharkDTO sharkDTO = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/games")
                        .queryParam("id", gameId)
                        .build())
                .retrieve()
                .bodyToMono(SharkDTO.class)
                .block();


        if(sharkDTO == null || sharkDTO.getDeals() == null || sharkDTO.getDeals().isEmpty()){
            return Collections.emptyList();
        }

        List<Deal> cheapestDeals= sharkDTO.getDeals().stream()
                .filter(deal -> activeStoreMap.containsKey(deal.getStoreID()))
                .sorted(Comparator.comparingDouble(deal -> Double.parseDouble(deal.getPrice())))
                .limit(5)
                .toList();

        return cheapestDeals.stream()
                .map(deal -> new SimplifiedCheapSharkDTO(
                        title,
                        deal.getPrice(),
                        activeStoreMap.get(deal.getStoreID())
                ))
                .collect(Collectors.toList());
    }
}
