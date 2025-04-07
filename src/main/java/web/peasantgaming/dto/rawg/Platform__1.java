
package web.peasantgaming.dto.rawg;

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
    "platform",
    "released_at",
    "requirements"
})
@Generated("jsonschema2pojo")
public class Platform__1 {

    @JsonProperty("platform")
    private Platform__2 platform;
    @JsonProperty("released_at")
    private String releasedAt;
    @JsonProperty("requirements")
    private Requirements requirements;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("platform")
    public Platform__2 getPlatform() {
        return platform;
    }

    @JsonProperty("platform")
    public void setPlatform(Platform__2 platform) {
        this.platform = platform;
    }

    @JsonProperty("released_at")
    public String getReleasedAt() {
        return releasedAt;
    }

    @JsonProperty("released_at")
    public void setReleasedAt(String releasedAt) {
        this.releasedAt = releasedAt;
    }

    @JsonProperty("requirements")
    public Requirements getRequirements() {
        return requirements;
    }

    @JsonProperty("requirements")
    public void setRequirements(Requirements requirements) {
        this.requirements = requirements;
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
