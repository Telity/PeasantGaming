package web.peasantgaming.dto.reccomandation;

import web.peasantgaming.dto.rawg.Genre;
import web.peasantgaming.dto.rawg.ParentPlatform;
import web.peasantgaming.dto.rawg.Platform;

import java.util.List;

public class RecomandationDto {

    private String name;
    private List<String> genre;
    private List<Object> dealList; // put deal på når toby har lavet det.
    private String picture;
    private String description;
    private List<String> platform;
    private List<String> storeNames;

    public RecomandationDto() {}

    public RecomandationDto(String name,List<String> genre,List<Object> dealList,String picture,String description,List<String> platform,List<String> storenames) {
        this.name = name;
        this.genre = genre;
        this.dealList = dealList;
        this.picture = picture;
        this.description = description;
        this.platform = platform;
        this.storeNames = storeNames;
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

    public List<Object> getDealList() {
        return dealList;
    }

    public void setDealList(List<Object> dealList) {
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

    public List<String> getStoreNames() {
        return storeNames;
    }

    public void setStoreNames(List<String> storeNames) {
        this.storeNames = storeNames;
    }
}
