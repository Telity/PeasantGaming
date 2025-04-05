package web.peasantgaming.dto.cheapshark;

import java.util.List;

public class GameInfoDto {

    private Info info;
    private CheapestPriceEver cheapestPriceEver;
    private List<Deal> deals;

    // Getters and Setters
    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public CheapestPriceEver getCheapestPriceEver() {
        return cheapestPriceEver;
    }

    public void setCheapestPriceEver(CheapestPriceEver cheapestPriceEver) {
        this.cheapestPriceEver = cheapestPriceEver;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }

    // Nested Classes
    public static class Info {
        private String title;
        private String steamAppID;
        private String thumb;

        // Getters and Setters
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSteamAppID() {
            return steamAppID;
        }

        public void setSteamAppID(String steamAppID) {
            this.steamAppID = steamAppID;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }
    }
}