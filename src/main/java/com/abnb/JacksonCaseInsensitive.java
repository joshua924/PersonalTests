package com.abnb;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class JacksonCaseInsensitive {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @JsonProperty("timeStamp")
    @JsonAlias("timestamp")
    long vendorTimestamp;

    @JsonProperty("lock_id")
    String lockId;

    public static void main(String[] args) throws JsonProcessingException {
        String json1 = "{\"timeStamp\":23545654756, \"lock_id\": \"47X\"}";
        String json2 = "{\"timestamp\":23545654756, \"lock_id\": \"47X\"}";

        JacksonCaseInsensitive p1 = MAPPER.readValue(json1, JacksonCaseInsensitive.class);
        System.out.println(p1);
        JacksonCaseInsensitive p2 = MAPPER.readValue(json2, JacksonCaseInsensitive.class);
        System.out.println(p2);
        System.out.println(MAPPER.writeValueAsString(p2));
    }
}
