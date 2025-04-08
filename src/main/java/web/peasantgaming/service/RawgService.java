package web.peasantgaming.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import web.peasantgaming.dto.rawg.GameInfo;
import web.peasantgaming.dto.rawg.Genre;
import web.peasantgaming.dto.rawg.ParentPlatform;
import web.peasantgaming.dto.reccomandation.RecomandationDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class RawgService {

    WebClient webClient;

    public RawgService(WebClient.Builder webClient) {
        this.webClient = webClient.build();
    }

    @Value("${api.key}")
    String api_key;

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
            //Laver en Liste her så vi kun få genre navn med og ikke resten af tingene i objektet.
            List<String> genres = new ArrayList<>();
            for(Genre genre : gameInfo.getGenres()){
                genres.add(genre.getName());
            }
            recomandation.setGenre(genres);

            //gør det samme som med genre her, så vi slipper for det i frontend.
            List<String> platforms = new ArrayList<>();
            for(ParentPlatform platform : gameInfo.getParentPlatforms()){
                platforms.add(platform.getPlatform().getName());
            }
            recomandation.setPlatform(platforms);
            //recomandation.setDealList();
            //recomandation.setStorenames();
            recomandations.add(recomandation);
        }
        return recomandations;
    }

}
