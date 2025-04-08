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

//    public Mono<Map<String, String>> getActivesStoresMap(){
//        return webClient.get()
//                .uri("/stores")
//                .retrieve()
//                .bodyToFlux(StoreInfo.class)
//                .filter(store -> store.getIsActive() == 1)
//                .collectMap(StoreInfo::getStoreID, StoreInfo::getStoreName);
//    }

    public String getCheapSharkStoreNameFromStoreId(String storeId){
        StoreInfo storename = webClient.get()
    }

    public List<SimplifiedCheapSharkDTO> get5CheapestStoresByTitel(String titel){

        String gameId = getCheapSharkIdFromTitel(titel);
        if(gameId == null){
            return Collections.emptyList();
        }

        StoreInfo[] stores = webClient.get()
                .uri("/stores")
                .retrieve()
                .bodyToMono(StoreInfo[].class)
                .block();

        Set<String> activeStoreIds = Arrays.stream(stores)
                .filter(s -> s.getIsActive() != null && s.getIsActive() == 1)
                .map(StoreInfo::getStoreID)
                .collect(Collectors.toSet());

        SharkDTO sharkDTO = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/deals")
                        .queryParam("id", gameId)
                        .build())
                .retrieve()
                .bodyToMono(SharkDTO.class)
                .block();


        if(sharkDTO == null || sharkDTO.getDeals() == null || sharkDTO.getDeals().isEmpty()){
            return Collections.emptyList();
        }

        List<Deal> cheapestDeals= sharkDTO.getDeals().stream()
                .filter(deal -> activeStoreIds.contains(deal.getStoreID()))
                .sorted(Comparator.comparingDouble(deal -> Double.parseDouble(deal.getPrice())))
                .limit(5)
                .toList();

        return

//        Mono<List<StoreInfo>> stores = webClient.get()
//                .uri("/stores")
//                .retrieve()
//                .bodyToFlux(StoreInfo.class)
//                .collectList();
//
//
//        Mono<String> specificGameID = webClient.get()
//                .uri("games?id=" + gameId)
//                .retrieve()
//                .bodyToMono(String.class);


    }
}
