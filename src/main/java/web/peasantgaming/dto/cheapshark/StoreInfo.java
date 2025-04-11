
package web.peasantgaming.dto.cheapshark;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "storeID",
    "storeName",
    "isActive",
    "images"
})
@Generated("jsonschema2pojo")
public class StoreInfo {

    @JsonProperty("storeID")
    private String storeID;
    @JsonProperty("storeName")
    private String storeName;
    @JsonProperty("isActive")
    private Integer isActive;
    @JsonProperty("images")
    private Images images;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("storeID")
    public String getStoreID() {
        return storeID;
    }

    @JsonProperty("storeID")
    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    @JsonProperty("storeName")
    public String getStoreName() {
        return storeName;
    }

    @JsonProperty("storeName")
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @JsonProperty("isActive")
    public Integer getIsActive() {
        return isActive;
    }

    @JsonProperty("isActive")
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    @JsonProperty("images")
    public Images getImages() {
        return images;
    }

    @JsonProperty("images")
    public void setImages(Images images) {
        this.images = images;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
