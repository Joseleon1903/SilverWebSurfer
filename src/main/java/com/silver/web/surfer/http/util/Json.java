package com.silver.web.surfer.http.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

public class Json {

    private static final ObjectMapper objectMapper = defaultObjectMapper();

    private static ObjectMapper defaultObjectMapper(){
        ObjectMapper objM = new ObjectMapper();
        objM.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        return objM;
    }

    public static JsonNode parse(String jsonSrc) throws JsonProcessingException {
        return objectMapper.readTree(jsonSrc);
    }

    public static Object fromJson(JsonNode jsonNose , Class<?> clazz ) throws JsonProcessingException {
        return objectMapper.treeToValue(jsonNose , clazz);
    }

    public static JsonNode toJosn(Object json){
        return  objectMapper.valueToTree(json);
    }

    public static String stringgify(Object json) throws JsonProcessingException {
        return generateJson(json, false);
    }

    public static String stringgifyPretty(Object json) throws JsonProcessingException {
        return generateJson(json, true);
    }

    private static String generateJson(Object obj, boolean pretty) throws JsonProcessingException {
        ObjectWriter objectWriter = objectMapper.writer();
        if(pretty){
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }
        return objectWriter.writeValueAsString(obj);
    }

}
