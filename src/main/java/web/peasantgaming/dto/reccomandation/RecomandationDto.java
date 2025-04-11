package web.peasantgaming.dto.reccomandation;

import web.peasantgaming.dto.rawg.Genre;
import web.peasantgaming.dto.rawg.ParentPlatform;
import web.peasantgaming.dto.rawg.Platform;

import java.util.List;
import java.util.Map;

public class RecomandationDto {

    private String name;
    private List<String> genre;
    private Map<String,String> dealList; //
    private String picture;
    private String description;
    private List<String> platform;

    public RecomandationDto() {}

    public RecomandationDto(String name,List<String> genre,Map<String,String> dealList,String picture,String description,List<String> platform) {
        this.name = name;
        this.genre = genre;
        this.dealList = dealList;
        this.picture = picture;
        this.description = description;
        this.platform = platform;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public Map<String,String> getDealList() {
        return dealList;
    }

    public void setDealList(Map<String,String> dealList) {
        this.dealList = dealList;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPlatform() {
        return platform;
    }

    public void setPlatform(List<String> platform) {
        this.platform = platform;
    }
}
