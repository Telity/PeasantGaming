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
                ("system","you are helpful assistent, on a videogame site. " +
                        "Be precise and short. Give 6 game recommendations based on the information you get, give only titles, seperate the game titles with, and replace spaces in title with - but dont put - after the , and no spaces, dont use apostofess, make titles work in rawg api calls, so like replace dark soul 3 with dark soul iii you know, thank you"));
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
