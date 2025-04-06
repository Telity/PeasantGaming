package web.peasantgaming.controller;

import org.springframework.web.bind.annotation.*;
import web.peasantgaming.dto.FrontEndRequestDto;
import web.peasantgaming.dto.GameInfo;
import web.peasantgaming.dto.RecommandationsDto;
import web.peasantgaming.service.PeasentGamingService;

import java.util.List;

@RestController
@CrossOrigin("*")
public class PeasentGamingController {

    PeasentGamingService peasentGamingService;

    public PeasentGamingController(PeasentGamingService peasentGamingService) {
        this.peasentGamingService = peasentGamingService;
    }

    @PostMapping("/game")
    public List<RecommandationsDto> game(@RequestBody FrontEndRequestDto frontEndRequestDto) {
        return peasentGamingService.recommendGames(frontEndRequestDto);
    }
}
