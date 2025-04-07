package web.peasantgaming.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import web.peasantgaming.dto.rawg.GameInfo;
import web.peasantgaming.dto.reccomandation.RecomandationDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class RawgService {

    WebClient webClient;

    public RawgService(WebClient.Builder webClient) {
        this.webClient = webClient.build();
    }

    String api_key = "9086de6197a5495e9f1d60fa0725534b";

    public List<GameInfo> getRawgGame(List<String> games){

        List<GameInfo> rawgGames = new ArrayList<>();

        for(String game : games) {
            Mono<GameInfo> gameInfo = webClient.get()
                    .uri("https://api.rawg.io/api/games/" + game + "?key=" + api_key)
                    .retrieve()
                    .bodyToMono(GameInfo.class);
            rawgGames.add(gameInfo.block());
        }

        return rawgGames;
    }

    public List<RecomandationDto> recomandations(List<String> games){

        List<GameInfo> gameInfos = getRawgGame(games);
        List<RecomandationDto> recomandations = new ArrayList<>();

        for(GameInfo gameInfo : gameInfos) {
            //Toby service metode til id
            //Toby service metode til deals
            //Service metode til storeNames
            RecomandationDto recomandation = new RecomandationDto();
            recomandation.setDescription(gameInfo.getDescription());
            recomandation.setName(gameInfo.getName());
            recomandation.setPicture(gameInfo.getBackgroundImage());
            recomandation.setGenre(gameInfo.getGenres());
            recomandation.setPlatform(gameInfo.getParentPlatforms());
            //recomandation.setDealList();
            //recomandation.setStorenames();
            recomandations.add(recomandation);
        }
        return recomandations;
    }

}
