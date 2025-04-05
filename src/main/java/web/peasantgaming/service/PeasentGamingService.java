package web.peasantgaming.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import web.peasantgaming.dto.*;
import web.peasantgaming.dto.cheapshark.GameIdDto;
import web.peasantgaming.dto.cheapshark.GameInfoDto;
import web.peasantgaming.dto.request.Message;
import web.peasantgaming.dto.request.RequestDto;
import web.peasantgaming.dto.response.ResponseDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeasentGamingService {

    WebClient webClient;

    public PeasentGamingService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.mistral.ai/v1/chat/completions").build();
    }

    @Value("${api.key}")
    private String apikey;
    public List<RecommandationsDto> recommendGames(FrontEndRequestDto frontEndRequestDto) {

        Mono<GameInfo> gameData = webClient.get()
                .uri("https://api.rawg.io/api/games/"+ frontEndRequestDto.getGameName() + "?&page=1&page_size=20&key=" + apikey)
                .retrieve()
                .bodyToMono(GameInfo.class);

        RequestDto requestDto = new RequestDto();
        requestDto.setModel("mistral-small-latest");
        requestDto.setTemperature(1.0);
        requestDto.setMaxTokens(200);

        List<Message> messages = new ArrayList<>();
        messages.add(new Message("system", "Give 5 game recommendation based on the data you get here, and give back the games with only the titles and seprated by ,"));
        messages.add(new Message("system", gameData.block()));
        requestDto.setMessages(messages);

        ResponseDto response = webClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .headers(h -> h.setBearerAuth(apikey))
                .bodyValue(requestDto)
                .retrieve()
                .bodyToMono(ResponseDto.class)
                .block();

        String[] games;
         String list = response.getChoices().stream()
                .toString();
         games = list.split(",");

         List<RecommandationsDto> recommandations = new ArrayList<>();
         for(String string : games){
             RecommandationsDto reco = new RecommandationsDto();
             Mono<GameIdDto> cheap = webClient.get()
                     .uri("https://www.cheapshark.com/api/1.0/games?title=" +string)
                     .retrieve()
                     .bodyToMono(GameIdDto.class);

             Mono<GameInfoDto> game = webClient.get()
                     .uri("https://www.cheapshark.com/api/1.0/games?id=" + cheap.block().getGameID())
                     .retrieve()
                     .bodyToMono(GameInfoDto.class);

             Mono<GameInfo> gameInfo = webClient.get()
                             .uri("https://api.rawg.io/api/games/"+ string + "?&page=1&page_size=20&key=" + apikey)
                             .retrieve()
                             .bodyToMono(GameInfo.class);
             GameInfo gameInfo1 = gameInfo.block();
             List<String> listOfGenres = new ArrayList<>();
             for(Genre genre : gameInfo1.getGenres()){
                 listOfGenres.add(genre.getName());
             }

             reco.setPrice(game.block().getDeals());
             reco.setDescription(gameInfo1.getDescription());
             reco.setGenre(listOfGenres);
             reco.setPictureUrl(gameInfo1.getBackgroundImage());
             reco.setName(string);
             //reco.setStoreName(game.);
             //reco.setPlatform();



         }
        return recommandations;
    }


}
