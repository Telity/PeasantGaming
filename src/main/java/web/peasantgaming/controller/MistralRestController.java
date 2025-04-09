package web.peasantgaming.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.peasantgaming.dto.reccomandation.RecomandationDto;
import web.peasantgaming.dto.request.Message;
import web.peasantgaming.dto.response.Choice;
import web.peasantgaming.service.MistralService;

import java.util.List;
import java.util.Scanner;

@RestController
public class MistralRestController {

    @Autowired
    MistralService mistralService;

    @GetMapping("/mistralChoice")
    public ResponseEntity<List<RecomandationDto>> postContent(){
        Message message = new Message();
        Scanner scanner = new Scanner(System.in);
        message.setContent(scanner.nextLine());
        List<RecomandationDto> choice = mistralService.getChoice(message);

        return ResponseEntity.ok().body(choice);
    }


}
