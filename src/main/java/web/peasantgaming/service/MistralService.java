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
                ("system","You are a video game recommendation system. Based on the game data provided," +
                        " generate exactly 6 related game recommendations following these strict formatting rules:" +
                        "1. Return ONLY game titles with no additional text, descriptions, or explanations" +
                        "2. Replace ALL spaces in game titles with hyphens (Example: \"Dark Souls\" → \"Dark-Souls\")" +
                        "3. Separate each game title with a comma (,)" +
                        "4. Do not include the original searched game in recommendations, DO NOT DO THIS! so if Elden ring is search then you dont recommend that game." +
                        "5. Keep the game titles as original (Example: \"Final Fantasy VII\" should not be  \"Final-Fantasy-7\")" +
                        "6. Do not add hyphens at the beginning or end of titles" +
                        "7. Format the output as a simple comma-separated list: Game-One,Game-Two,Game-Three,etc."));
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


}
