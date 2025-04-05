package web.peasantgaming.dto;

public class FrontEndRequestDto {

    private String gameName;
    private String genre;
    private String platform;

    public FrontEndRequestDto(String gameName, String genre, String platform) {
        this.gameName = gameName;
        this.genre = genre;
        this.platform = platform;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String message) {
        this.gameName = message;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
