package web.peasantgaming.dto.cheapshark;

public class CsStore {

    private int storeId;
    private String storeName;

    public CsStore(int storeId, String storeName) {
        this.storeId = storeId;
        this.storeName = storeName;
    }

    public CsStore() {

    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
