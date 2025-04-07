package web.peasantgaming.dto.cheapshark;

public class CsStore {
    private int storeID;  // Note: this is a String in the API, not an int
    private String storeName;
    private int isActive;
    private Images images;

    // Nested class for images
    public static class Images {
        private String banner;
        private String logo;
        private String icon;

        // Add getters and setters
        public String getBanner() { return banner; }
        public void setBanner(String banner) { this.banner = banner; }

        public String getLogo() { return logo; }
        public void setLogo(String logo) { this.logo = logo; }

        public String getIcon() { return icon; }
        public void setIcon(String icon) { this.icon = icon; }
    }

    // Update your getters/setters
    public int getStoreID() { return storeID; }
    public void setStoreID(int storeID) { this.storeID = storeID; }

    public String getStoreName() { return storeName; }
    public void setStoreName(String storeName) { this.storeName = storeName; }

    public int getIsActive() { return isActive; }
    public void setIsActive(int isActive) { this.isActive = isActive; }

    public Images getImages() { return images; }
    public void setImages(Images images) { this.images = images; }
}