package web.peasantgaming.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import web.peasantgaming.dto.rawg.GameInfoDto;
import web.peasantgaming.dto.rawg.Genre;
import web.peasantgaming.dto.rawg.ParentPlatform;
import web.peasantgaming.dto.reccomandation.RecomandationDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class RawgService {

    WebClient webClient;

    //CheapSharkService cheapSharkService;

    public RawgService(WebClient.Builder webClient/*, CheapSharkService service*/) {
        this.webClient = webClient.build();
        //this.cheapSharkService = service;
    }

    @Value("${rawg.api.key1}")
    String api_key;

    public List<GameInfoDto> getRawgGame(List<String> games){

        List<GameInfoDto> rawgGames = new ArrayList<>();

        for(String game : games) {
            Mono<GameInfoDto> gameInfo = webClient.get()
                    .uri("https://api.rawg.io/api/games/" + game + "?key=" + api_key)
                    .retrieve()
                    .bodyToMono(GameInfoDto.class);
            rawgGames.add(gameInfo.block());
        }

        return rawgGames;
    }

    public List<RecomandationDto> recomandations(List<String> games){

        List<GameInfoDto> gameInfos = getRawgGame(games);
        List<RecomandationDto> recomandations = new ArrayList<>();


        for(GameInfoDto gameInfo : gameInfos) {

            //List<SimplifiedCheapSharkDTO> cheapSharkData = cheapSharkService.
            //List<String> storeNames = new Arraylist<>();
            //List<Object> prices = new ArrayList<>();
            //for(SimplifiedCheapSharkDTO Csdto : cheapSharkData){
            //    storeNames.add(Csdto.getStoreName());
            //    prices.add(Csdto.getPrice());
            //}
            //Toby service metode til id
            //Toby service metode til deals
            //Service metode til storeNames
            RecomandationDto recomandation = new RecomandationDto();
            recomandation.setDescription(gameInfo.getDescriptionRaw());
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
            //recomandation.setDealList(prices);
            //recomandation.setStorenames(storeNames);
            recomandations.add(recomandation);
        }
        return recomandations;
    }

}
