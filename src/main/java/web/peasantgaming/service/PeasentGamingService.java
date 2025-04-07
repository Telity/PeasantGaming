package web.peasantgaming.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
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
            /*
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
            */
            RequestDto requestDto = new RequestDto();
            requestDto.setModel("mistral-small-latest");
            requestDto.setTemperature(1.0);
            requestDto.setMaxTokens(200);

            List<Message> messages = new ArrayList<>();
            messages.add(new Message("system","Give 8 game recommendation based on the data you get here, give back only the game titles, and replace spaces in their name with - in their names and seperate each game with , so i can split them in backend, and try to base names so they can be used to searh in the rawg api, and only games titles no additioanl details or descriptions"));
            messages.add(new Message("user",frontEndRequestDto.getGameName()));
            requestDto.setMessages(messages);


            ResponseDto response = webClient.post()
                    .uri("https://api.mistral.ai/v1/chat/completions")
                    .contentType(MediaType.APPLICATION_JSON)
                    .headers(h -> h.setBearerAuth(mistralkey))
                    .bodyValue(requestDto)
                    .retrieve()
                    .bodyToMono(ResponseDto.class)
                    .block();

            System.out.println(response.getChoices().get(0).getMessage().getContent());

            String[] games;
            String list = response.getChoices().get(0).getMessage().getContent();
            games = list.split(",");

            List<RecommandationsDto> recommandations = new ArrayList<>();
            for (String string : games) {
                RecommandationsDto reco = new RecommandationsDto();
                System.out.println("Game title being searched: " + string.trim());
                Mono<List<GameIdDto>> cheap = webClient.get()
                        .uri("https://www.cheapshark.com/api/1.0/games?title=" + string.trim())
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<List<GameIdDto>>() {});

                List<GameIdDto> cheapGames = cheap.block();
                // Check if the API found any games with this title
                if (cheapGames == null || cheapGames.isEmpty()) {
                    System.out.println("No results found on CheapShark for: " + string.trim());
                    continue; // Skip to the next game
                }

                Mono<GameInfoDto> game = webClient.get()
                        .uri("https://www.cheapshark.com/api/1.0/games?id=" + cheap.block().get(0).getGameID())
                        .retrieve()
                        .bodyToMono(GameInfoDto.class);

                Mono<GameInfo> gameInfo = webClient.get()
                        .uri("https://api.rawg.io/api/games/" + string + "?&page=1&page_size=20&key=" + apikey)
                        .retrieve()
                        .bodyToMono(GameInfo.class);
                try {
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
                Mono<List<CsStore>> storename = webClient.get()
                        .uri("https://www.cheapshark.com/api/1.0/stores")
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<List<CsStore>>() {
                        });

                List<CsStore> stores = storename.block();

                for (CsStore store : stores) {
                    if (storeIds.contains((store.getStoreID()))) {
                        storeNames.add(store.getStoreName());
                    }
                }


                reco.setStoreName(storeNames);

                List<String> platforms = new ArrayList<>();
                for (ParentPlatform platform : gameInfo1.getParentPlatforms()) {
                    platforms.add(platform.getPlatform().getName());
                }
                reco.setPlatform(platforms);
                }
                catch(WebClientResponseException e){
                    e.printStackTrace();
                    System.out.println("Game not found");
                    continue;
                }

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
