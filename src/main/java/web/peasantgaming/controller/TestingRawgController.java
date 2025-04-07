package web.peasantgaming.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import web.peasantgaming.dto.GameInfo;
import web.peasantgaming.service.RawgService;

@RestController
@CrossOrigin("*")
public class TestingRawgController {

    RawgService rawgService;

    public TestingRawgController(RawgService rawgService) {
        this.rawgService = rawgService;
    }

    @GetMapping("/test")
    public GameInfo test() {
        return rawgService.getRawgGame("elden-ring").block();
    }
}

