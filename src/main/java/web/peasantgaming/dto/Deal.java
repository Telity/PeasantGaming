
package web.peasantgaming.dto;

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
    "dealID",
    "price",
    "retailPrice",
    "savings"
})
@Generated("jsonschema2pojo")
public class Deal {

    @JsonProperty("storeID")
    private String storeID;
    @JsonProperty("dealID")
    private String dealID;
    @JsonProperty("price")
    private String price;
    @JsonProperty("retailPrice")
    private String retailPrice;
    @JsonProperty("savings")
    private String savings;
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

    @JsonProperty("dealID")
    public String getDealID() {
        return dealID;
    }

    @JsonProperty("dealID")
    public void setDealID(String dealID) {
        this.dealID = dealID;
    }

    @JsonProperty("price")
    public String getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(String price) {
        this.price = price;
    }

    @JsonProperty("retailPrice")
    public String getRetailPrice() {
        return retailPrice;
    }

    @JsonProperty("retailPrice")
    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    @JsonProperty("savings")
    public String getSavings() {
        return savings;
    }

    @JsonProperty("savings")
    public void setSavings(String savings) {
        this.savings = savings;
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
