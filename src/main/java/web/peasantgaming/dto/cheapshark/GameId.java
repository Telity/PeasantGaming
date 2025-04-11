
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
    "gameID",
    "steamAppID",
    "cheapest",
    "cheapestDealID",
    "external",
    "internalName",
    "thumb"
})
@Generated("jsonschema2pojo")
public class GameId {

    @JsonProperty("gameID")
    private String gameID;
    @JsonProperty("steamAppID")
    private Object steamAppID;
    @JsonProperty("cheapest")
    private String cheapest;
    @JsonProperty("cheapestDealID")
    private String cheapestDealID;
    @JsonProperty("external")
    private String external;
    @JsonProperty("internalName")
    private String internalName;
    @JsonProperty("thumb")
    private String thumb;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("gameID")
    public String getGameID() {
        return gameID;
    }

    @JsonProperty("gameID")
    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    @JsonProperty("steamAppID")
    public Object getSteamAppID() {
        return steamAppID;
    }

    @JsonProperty("steamAppID")
    public void setSteamAppID(Object steamAppID) {
        this.steamAppID = steamAppID;
    }

    @JsonProperty("cheapest")
    public String getCheapest() {
        return cheapest;
    }

    @JsonProperty("cheapest")
    public void setCheapest(String cheapest) {
        this.cheapest = cheapest;
    }

    @JsonProperty("cheapestDealID")
    public String getCheapestDealID() {
        return cheapestDealID;
    }

    @JsonProperty("cheapestDealID")
    public void setCheapestDealID(String cheapestDealID) {
        this.cheapestDealID = cheapestDealID;
    }

    @JsonProperty("external")
    public String getExternal() {
        return external;
    }

    @JsonProperty("external")
    public void setExternal(String external) {
        this.external = external;
    }

    @JsonProperty("internalName")
    public String getInternalName() {
        return internalName;
    }

    @JsonProperty("internalName")
    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    @JsonProperty("thumb")
    public String getThumb() {
        return thumb;
    }

    @JsonProperty("thumb")
    public void setThumb(String thumb) {
        this.thumb = thumb;
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
