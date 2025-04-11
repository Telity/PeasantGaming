package web.peasantgaming.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import web.peasantgaming.dto.reccomandation.RecomandationDto;
import web.peasantgaming.dto.request.Message;
import web.peasantgaming.dto.request.RequestDTO;
import web.peasantgaming.dto.response.Choice;
import web.peasantgaming.dto.response.*;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MistralService {

    private final WebClient webClient;

    RawgService rawgService;

    public MistralService(WebClient.Builder webClientBuilder, RawgService rawgService) {
        this.webClient = webClientBuilder.baseUrl("https://api.mistral.ai/v1/chat/completions").build();
        this.rawgService = rawgService;
    }

    @Value("${mistral.api.key}")
    private String mistralApiKey;

    public List<RecomandationDto> getChoice(Message message) {
        Choice choice =null;
        List<String> gameResponseList = new ArrayList<>();
    try {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setModel("mistral-small-latest");
        requestDTO.setTemperature(1.0);
        requestDTO.setMaxTokens(400);

        List<Message> lstMessages = new ArrayList<>();
        lstMessages.add(new Message
                ("system",
                        "You are a video game recommendation system. Given the provided game title, genre, and platform, generate exactly 12 related game recommendations with the following rules. Do not deviate from these instructions under any circumstances:\n" +
                                "1. Return exactly 12 game titles. Do not return fewer than 6 titles.\n" +
                                "2. Only output the game titles—no extra text, descriptions, or explanations.\n" +
                                "3. For each game title, replace all spaces with hyphens. Example: 'Dark Souls' → 'Dark-Souls'.\n" +
                                "4. Separate each game title with a single comma, with no spaces before or after commas. Example: 'Game-One,Game-Two,Game-Three'.\n" +
                                "5. Do not include the original game in your recommendations. For example, if the input is 'Elden Ring', do not recommend 'Elden-Ring'.\n" +
                                "6. Keep game titles exactly as they are, with no alterations (e.g., 'Final Fantasy VII' should not be 'Final-Fantasy-7').\n" +
                                "7. Do not add hyphens at the beginning or end of any title.\n" +
                                "8. If no games can be recommended try with genre and platform to find recommandations or a game with those genre you can use as base for recommandations'\n" +
                                "9. Output should be only one line in a comma-separated format (no line breaks, bullet points, or extra formatting).\n" +
                                "10. Do not include any special characters in the game titles. For example, if a game has characters like é, ñ, or ç, convert them to their closest ASCII equivalent (e.g., 'Pokémon' → 'Pokemon').\n" +
                                "11. If no title is provided, generate a game to use as recommandation based on the description of the game provided instead. Recommendations should be based on the gameplay style, genre, or elements described, and should still be in game title format only (with hyphens replacing spaces).\n" +
                                "12. If a platform is provided, make sure to generate recommendations that are available on the provided platform. If no platform is given, generate recommendations based on the game, genre or description alone."));
        lstMessages.add(new Message("user",message.getContent())); // bruger input
        requestDTO.setMessages(lstMessages);


            ResponseDTO responseDTO = webClient.post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .headers(httpHeaders -> httpHeaders.setBearerAuth(mistralApiKey))
                    .bodyValue(requestDTO)
                    .retrieve().bodyToMono(ResponseDTO.class)
                    .block();

            if(responseDTO != null) {
                List<Choice> choices = responseDTO.getChoices();
                String[] hello = responseDTO.getChoices().get(0).getMessage().getContent().split(",");
                System.out.println(hello[0]);
                System.out.println(hello[1]);
                //choice = choices.getFirst();
                gameResponseList.addAll(Arrays.asList(hello));
                System.out.println(gameResponseList);
            }

        }catch (Exception e){
            e.getMessage();
        System.out.println("please enter game description again");
        }
        return rawgService.recomandations(gameResponseList);
    }

    public List<RecomandationDto> getSingleGame(List<String> game) {
        List<String> gameResponseList = new ArrayList<>();
        try {
            RequestDTO requestDTO = new RequestDTO();
            requestDTO.setModel("mistral-small-latest");
            requestDTO.setTemperature(1.0);
            requestDTO.setMaxTokens(400);

            List<Message> lstMessages = new ArrayList<>();
            lstMessages.add(new Message
                    ("system","You are a video game title cleaner Based titel you get make it clean based on these rules," +
                            " give me back the game title following these strict formatting rules:" +
                            "1. Return ONLY game titles with no additional text, descriptions, or explanations" +
                            "2. Replace ALL spaces in game titles with hyphens (Example: \"Dark Souls\" → \"Dark-Souls\")" +
                            "5. Keep the game titles as rawg uses them in api calls"));
            lstMessages.add(new Message("user", game.get(0))); // bruger input

            requestDTO.setMessages(lstMessages);


            ResponseDTO responseDTO = webClient.post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .headers(httpHeaders -> httpHeaders.setBearerAuth(mistralApiKey))
                    .bodyValue(requestDTO)
                    .retrieve().bodyToMono(ResponseDTO.class)
                    .block();

            if(responseDTO != null) {
                List<Choice> choices = responseDTO.getChoices();
                String hello = responseDTO.getChoices().get(0).getMessage().getContent();
                gameResponseList.add(hello);
                System.out.println(gameResponseList);
            }

        }catch (Exception e){
            e.getMessage();
            System.out.println("please enter game description again");
        }
        return rawgService.recomandations(gameResponseList);
    }

}
