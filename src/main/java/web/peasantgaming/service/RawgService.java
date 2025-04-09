package web.peasantgaming.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import web.peasantgaming.dto.cheapshark.SimplifiedCheapSharkDTO;
import web.peasantgaming.dto.rawg.GameInfoDto;
import web.peasantgaming.dto.rawg.Genre;
import web.peasantgaming.dto.rawg.ParentPlatform;
import web.peasantgaming.dto.reccomandation.RecomandationDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RawgService {

    WebClient webClient;

    CheapSharkService cheapSharkService;

    public RawgService(WebClient.Builder webClient, CheapSharkService service) {
        this.webClient = webClient.build();
        this.cheapSharkService = service;
    }

    @Value("${rawg.api.key1}")
    String api_key;

    public List<GameInfoDto> getRawgGame(List<String> games){

        List<GameInfoDto> rawgGames = new ArrayList<>();

        for(String game : games) {
            try {
                Mono<GameInfoDto> gameInfo = webClient.get()
                        .uri("https://api.rawg.io/api/games/" + game.trim() + "?key=" + api_key)
                        .retrieve()
                        .bodyToMono(GameInfoDto.class);
                rawgGames.add(gameInfo.block());
            }
            catch(WebClientResponseException e){
                e.printStackTrace();
                System.out.println("No game found with title" + game.trim());
                continue;
            }
        }

        return rawgGames;
    }

    public List<RecomandationDto> recomandations(List<String> games){

        List<GameInfoDto> gameInfos = getRawgGame(games);
        List<RecomandationDto> recomandations = new ArrayList<>();


        for(GameInfoDto gameInfo : gameInfos) {

            List<SimplifiedCheapSharkDTO> cheapSharkData = cheapSharkService.get5CheapestStoresByTitle(gameInfo.getName());
            Map<String,String> dealInfo = new HashMap<>();
            for(SimplifiedCheapSharkDTO Csdto : cheapSharkData){
                dealInfo.put(Csdto.getStoreName(),Csdto.getPrice());
            }

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

            if(dealInfo.isEmpty()){
                SimplifiedCheapSharkDTO raw = new SimplifiedCheapSharkDTO();
                if(!gameInfo.getStores().isEmpty()) {
                    raw.setStoreName(gameInfo.getStores().get(0).getStore().getName());
                }
                dealInfo.put(raw.getStoreName(), raw.getStoreName());
                recomandation.setDealList(dealInfo);
            }
            else {
                recomandation.setDealList(dealInfo);
            }
            recomandations.add(recomandation);
        }
        return recomandations;
    }

}
