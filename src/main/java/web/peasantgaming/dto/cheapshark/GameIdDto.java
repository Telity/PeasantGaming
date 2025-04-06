package web.peasantgaming.dto.cheapshark;

public class GameIdDto {
    private String gameID;
    private String steamAppID;
    private String cheapest;
    private String cheapestDealID;
    private String external;
    private String internalName;
    private String thumb;

    // Default constructor (required for Jackson)
    public GameIdDto() {}

    // Getters and setters
    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public String getSteamAppID() {
        return steamAppID;
    }

    public void setSteamAppID(String steamAppID) {
        this.steamAppID = steamAppID;
    }

    public String getCheapest() {
        return cheapest;
    }

    public void setCheapest(String cheapest) {
        this.cheapest = cheapest;
    }

    public String getCheapestDealID() {
        return cheapestDealID;
    }

    public void setCheapestDealID(String cheapestDealID) {
        this.cheapestDealID = cheapestDealID;
    }

    public String getExternal() {
        return external;
    }

    public void setExternal(String external) {
        this.external = external;
    }

    public String getInternalName() {
        return internalName;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
