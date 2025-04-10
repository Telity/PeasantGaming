package web.peasantgaming.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.peasantgaming.dto.reccomandation.RecomandationDto;
import web.peasantgaming.dto.request.Message;
import web.peasantgaming.dto.response.Choice;
import web.peasantgaming.service.MistralService;
import web.peasantgaming.service.RawgService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
@CrossOrigin("*")
public class MistralRestController {


    MistralService mistralService;

    RawgService rawgService;

    public MistralRestController(MistralService mistralService, RawgService rawgService) {
        this.mistralService = mistralService;
        this.rawgService = rawgService;
    }

    @PostMapping("/mistralChoice")
    public ResponseEntity<List<RecomandationDto>> postContent(@RequestBody String content) {
        Message message = new Message();
        System.out.println(content);
        message.setContent(content);
        List<RecomandationDto> choice = mistralService.getChoice(message);

        return ResponseEntity.ok().body(choice);
    }

    @PostMapping("/singleGame")
    public ResponseEntity<List<RecomandationDto>> getSingleGame(@RequestBody String content) {
        List<String> game = new ArrayList<>();
        game.add(content);

        return ResponseEntity.ok(rawgService.recomandations(game));
    }

}
