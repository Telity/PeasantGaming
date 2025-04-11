package web.peasantgaming.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import web.peasantgaming.dto.rawg.Genre;
import web.peasantgaming.service.RawgService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class RawgRestController {

    @Autowired
    RawgService rawgService;

    @GetMapping("/genres")
    public List<String> getGenre() {
        List<String> genres = new ArrayList<>();
        genres = rawgService.getGenres();
        return genres;

    }
    @GetMapping("/platforms")
    public List<String> getPlatform() {
        List<String> platforms = new ArrayList<>();
        platforms = rawgService.getPlatforms();
        return platforms;
    }
}
