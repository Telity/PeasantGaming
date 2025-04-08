package web.peasantgaming.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import web.peasantgaming.dto.request.Message;
import web.peasantgaming.dto.request.RequestDTO;
import web.peasantgaming.dto.response.Choice;
import web.peasantgaming.dto.response.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class MistralService {

    private final WebClient webClient;

    public MistralService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.mistral.ai/v1/chat/completions").build();
    }

    @Value("${mistral.api.key}")
    private String mistralApiKey;

    public Choice getChoice() {
        Choice choice =new Choice();
    try {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setModel("mistral-small-latest");
        requestDTO.setTemperature(1.0);
        requestDTO.setMaxTokens(400);

        Message message = new Message();
        List<Message> lstMessages = new ArrayList<>();
        lstMessages.add(new Message("system","you are helpful assistent, on a videogame site"));
        lstMessages.add(new Message("user",message.getContent())); //Bruger input

        requestDTO.setMessages(lstMessages);


            ResponseDTO responseDTO = webClient.post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .headers(httpHeaders -> httpHeaders.setBearerAuth(mistralApiKey))
                    .bodyValue(requestDTO)
                    .retrieve().bodyToMono(ResponseDTO.class)
                    .block();

            if(responseDTO != null) {
                List<Choice> choices = responseDTO.getChoices();
                choice = choices.getFirst();
            }

        }catch (Exception e){
            e.getMessage();
        System.out.println("please enter game description again");
        }
        return choice;
    }


}
