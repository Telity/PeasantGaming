package web.peasantgaming.dto.cheapshark;



public class SimplifiedCheapSharkDTO {
    private String titel;
    private String price;
    private String storeName;

    public SimplifiedCheapSharkDTO(String titel, String price, String storeName) {
        this.titel = titel;
        this.price = price;
        this.storeName = storeName;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
