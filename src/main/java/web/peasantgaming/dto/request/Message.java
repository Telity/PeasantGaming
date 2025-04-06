package web.peasantgaming.dto.request;


import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import web.peasantgaming.dto.GameInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "role",
        "tool_calls",
        "content"
})
@Generated("jsonschema2pojo")
public class Message {

    @JsonProperty("role")
    private String role;
    @JsonProperty("tool_calls")
    private Object toolCalls;
    @JsonProperty("content")
    private String content;

    private GameInfo gameinfoContent;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    public Message(String role, GameInfo gameinfoContent) {
        this.role = role;
        this.gameinfoContent = gameinfoContent;
    }

    public Message(String role, String content) {
        this.role = role;
        this.content = content;
    }

    public Message() {}
    @JsonProperty("content")
    public String getContent() {
        return content;
    }

    @JsonProperty("content")
    public void setContent(String content) {
        this.content = content;
    }

    @JsonProperty("role")
    public String getRole() {
        return role;
    }

    @JsonProperty("role")
    public void setRole(String role) {
        this.role = role;
    }

    @JsonProperty("tool_calls")
    public Object getToolCalls() {
        return toolCalls;
    }

    @JsonProperty("tool_calls")
    public void setToolCalls(Object toolCalls) {
        this.toolCalls = toolCalls;
    }


    public GameInfo getGameinfoContentContent() {
        return gameinfoContent;
    }


    public void setGameinfoContentContent(GameInfo gameinfoContent) {
        this.gameinfoContent = gameinfoContent;
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

