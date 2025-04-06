package web.peasantgaming.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import web.peasantgaming.dto.*;
import web.peasantgaming.dto.cheapshark.*;
import web.peasantgaming.dto.request.Message;
import web.peasantgaming.dto.request.RequestDto;
import web.peasantgaming.dto.response.ResponseDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeasentGamingService {

    WebClient webClient;

    public PeasentGamingService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Value("${api.key}")
    private String apikey;

    @Value("${mistral.key}")
    private String mistralkey;

    public List<RecommandationsDto> recommendGames(FrontEndRequestDto frontEndRequestDto) {
        try {
            RequestDto requestDtoGame = new RequestDto();
            requestDtoGame.setModel("mistral-small-latest");
            requestDtoGame.setTemperature(1.0);
            requestDtoGame.setMaxTokens(200);

            List<Message> messages1 = new ArrayList<>();
            messages1.add(new Message("system","Use the string to find the game titel, only return game titel nothing else, and replace spaces with -"));
            messages1.add(new Message("user", frontEndRequestDto.getGameName()));
            requestDtoGame.setMessages(messages1);

            Mono<ResponseDto> gameIsolated = webClient.post()
                    .uri("https://api.mistral.ai/v1/chat/completions")
                    .contentType(MediaType.APPLICATION_JSON)
                    .headers(h -> h.setBearerAuth(mistralkey))
                    .bodyValue(requestDtoGame)
                    .retrieve()
                    .bodyToMono(ResponseDto.class);


            Mono<GameInfo> gameData = webClient.get()
                    .uri("https://api.rawg.io/api/games/" + gameIsolated.block().getChoices().get(0).getMessage().getContent() + "?&page=1&page_size=20&key=" + apikey)
                    .retrieve()
                    .bodyToMono(GameInfo.class);

            GameInfo gameInfo2 = gameData.block();
            if (gameInfo2 == null) {
                throw new RuntimeException("Failed to retrieve game information");
            }

            RequestDto requestDto = new RequestDto();
            requestDto.setModel("mistral-small-latest");
            requestDto.setTemperature(1.0);
            requestDto.setMaxTokens(200);

            List<Message> messages = new ArrayList<>();
            messages.add(new Message("system","Give 5 game recommendation based on the data you get here, and give back the games with only the titles and seprated by ,"));
            messages.add(new Message("user",gameInfo2.getDescription()));
            requestDto.setMessages(messages);


            ResponseDto response = webClient.post()
                    .uri("https://api.mistral.ai/v1/chat/completions")
                    .contentType(MediaType.APPLICATION_JSON)
                    .headers(h -> h.setBearerAuth(mistralkey))
                    .bodyValue(requestDto)
                    .retrieve()
                    .bodyToMono(ResponseDto.class)
                    .block();

            String[] games;
            String list = response.getChoices().stream()
                    .map(value -> toString())
                    .toString();
            games = list.split(",");

            List<RecommandationsDto> recommandations = new ArrayList<>();
            for (String string : games) {
                RecommandationsDto reco = new RecommandationsDto();

                Mono<List<GameIdDto>> cheap = webClient.get()
                        .uri("https://www.cheapshark.com/api/1.0/games?title=" + string.trim())
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<List<GameIdDto>>() {});

                Mono<GameInfoDto> game = webClient.get()
                        .uri("https://www.cheapshark.com/api/1.0/games?id=" + cheap.block().get(0).getGameID())
                        .retrieve()
                        .bodyToMono(GameInfoDto.class);

                Mono<GameInfo> gameInfo = webClient.get()
                        .uri("https://api.rawg.io/api/games/" + string + "?&page=1&page_size=20&key=" + apikey)
                        .retrieve()
                        .bodyToMono(GameInfo.class);
                GameInfo gameInfo1 = gameInfo.block();

                List<String> listOfGenres = new ArrayList<>();

                for (Genre genre : gameInfo1.getGenres()) {
                    listOfGenres.add(genre.getName());
                }

                reco.setDeals(game.block().getDeals());
                reco.setDescription(gameInfo1.getDescription());
                reco.setGenre(listOfGenres);
                reco.setPictureUrl(gameInfo1.getBackgroundImage());
                reco.setName(string);

                List<Integer> storeIds = new ArrayList<>();

                for (Deal deal : reco.getDeals()) {
                    storeIds.add(deal.getStoreID());
                }

                List<String> storeNames = new ArrayList<>();
                Mono<CsStoreList> storename = webClient.get()
                        .uri("https://www.cheapshark.com/api/1.0/stores")
                        .retrieve()
                        .bodyToMono(CsStoreList.class);

                for (int storeId : storeIds) {
                    for (CsStore store : storename.block().getStoreList()) {
                        if (storeIds.contains((store.getStoreId()))) {
                            storeNames.add(store.getStoreName());
                        }
                    }

                }
                reco.setStoreName(storeNames);

                List<String> platforms = new ArrayList<>();
                for (ParentPlatform platform : gameInfo1.getParentPlatforms()) {
                    platforms.add(platform.getPlatform().getName());
                }
                reco.setPlatform(platforms);

                recommandations.add(reco);

            }
            return recommandations;
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("error");
        }
        return null;
    }


}
