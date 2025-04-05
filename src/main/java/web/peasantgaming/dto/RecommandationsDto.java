package web.peasantgaming.dto;

import web.peasantgaming.dto.cheapshark.Deal;

import java.util.List;

public class RecommandationsDto {

    private String name;
    private List<Deal> price;
    private List<String> genre;
    private String pictureUrl;
    private String description;
    private String platform;
    private String storeName;

    public RecommandationsDto(String name, List<Deal> price, List<String> genre, String pictureUrl, String description, String platform, String storeName) {
        this.name = name;
        this.price = price;
        this.genre = genre;
        this.pictureUrl = pictureUrl;
        this.description = description;
        this.platform = platform;
        this.storeName = storeName;
    }

    public RecommandationsDto(){}

    public List<Deal> getPrice() {
        return price;
    }

    public void setPrice(List<Deal> price) {
        this.price = price;
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

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
