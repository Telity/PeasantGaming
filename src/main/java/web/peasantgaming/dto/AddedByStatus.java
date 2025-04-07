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
        "owned",
        "beaten",
        "dropped",
        "playing"
})
@Generated("jsonschema2pojo")
public class AddedByStatus {

    @JsonProperty("owned")
    private Integer owned;
    @JsonProperty("beaten")
    private Integer beaten;
    @JsonProperty("dropped")
    private Integer dropped;
    @JsonProperty("playing")
    private Integer playing;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("owned")
    public Integer getOwned() {
        return owned;
    }

    @JsonProperty("owned")
    public void setOwned(Integer owned) {
        this.owned = owned;
    }

    @JsonProperty("beaten")
    public Integer getBeaten() {
        return beaten;
    }

    @JsonProperty("beaten")
    public void setBeaten(Integer beaten) {
        this.beaten = beaten;
    }

    @JsonProperty("dropped")
    public Integer getDropped() {
        return dropped;
    }

    @JsonProperty("dropped")
    public void setDropped(Integer dropped) {
        this.dropped = dropped;
    }

    @JsonProperty("playing")
    public Integer getPlaying() {
        return playing;
    }

    @JsonProperty("playing")
    public void setPlaying(Integer playing) {
        this.playing = playing;
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