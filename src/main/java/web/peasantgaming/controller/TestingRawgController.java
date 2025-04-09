package web.peasantgaming.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import web.peasantgaming.dto.rawg.GameInfoDto;
import web.peasantgaming.dto.reccomandation.RecomandationDto;
import web.peasantgaming.service.CheapSharkService;
import web.peasantgaming.service.RawgService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class TestingRawgController {

    RawgService rawgService;

    CheapSharkService cheapSharkService;

    public TestingRawgController(RawgService rawgService, CheapSharkService cheapSharkService) {
        this.rawgService = rawgService;
        this.cheapSharkService = cheapSharkService;
    }

    @GetMapping("/test")
    public List<RecomandationDto> test() {
        List<String> gameList = new ArrayList<>();
        gameList.add("elden-ring");
        gameList.add("the-witcher-3-wild-hunt");
        return rawgService.recomandations(gameList);
    }

    @GetMapping("/shark")
    public List<SimplifiedCheapSharkDTO> simplifiedCheapShark() {
        return cheapSharkService.get5CheapestStoresByTitle("elden-ring");
    }
}

