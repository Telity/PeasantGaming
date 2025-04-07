package web.peasantgaming.dto.cheapshark;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CsStoreList {

    @JsonProperty(value = "")
    private List<CsStore> storeList;

    public CsStoreList(List<CsStore> storeList) {
        this.storeList = storeList;
    }
    public List<CsStore> getStoreList() {
        return storeList;
    }
    public void setStoreList(List<CsStore> storeList) {
        this.storeList = storeList;
    }
}
