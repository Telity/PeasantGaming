package web.peasantgaming.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import web.peasantgaming.dto.rawg.GameInfo;
import web.peasantgaming.dto.reccomandation.RecomandationDto;
import web.peasantgaming.service.RawgService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class TestingRawgController {

    RawgService rawgService;

    public TestingRawgController(RawgService rawgService) {
        this.rawgService = rawgService;
    }

    @GetMapping("/test")
    public List<RecomandationDto> test() {
        List<String> gameList = new ArrayList<>();
        gameList.add("elden-ring");
        gameList.add("the-witcher-3-wild-hunt");
        return rawgService.recomandations(gameList);
    }
}

