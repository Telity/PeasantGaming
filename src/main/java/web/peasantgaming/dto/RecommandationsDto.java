package web.peasantgaming.dto;

import web.peasantgaming.dto.cheapshark.Deal;

import java.util.List;

public class RecommandationsDto {

    private String name;
    private List<Deal> deals;
    private List<String> genre;
    private String pictureUrl;
    private String description;
    private List<String> platform;
    private List<String> storeName;

    public RecommandationsDto(String name, List<Deal> deals, List<String> genre, String pictureUrl, String description, List<String> platform, List<String> storeName) {
        this.name = name;
        this.deals = deals;
        this.genre = genre;
        this.pictureUrl = pictureUrl;
        this.description = description;
        this.platform = platform;
        this.storeName = storeName;
    }

    public RecommandationsDto(){}

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
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

    public List<String> getStoreName() {
        return storeName;
    }

    public void setStoreName(List<String> storeName) {
        this.storeName = storeName;
    }
}
