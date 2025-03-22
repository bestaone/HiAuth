package cn.hiauth.server.config.web.auth;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;

import java.io.IOException;

public class AuthGrantedAuthorityDeserializer extends JsonDeserializer<AuthGrantedAuthority> {

    @Override
    public AuthGrantedAuthority deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);
        String code = readJsonNode(jsonNode, "code").asText();
        String page = readJsonNode(jsonNode, "page").asText();
        String api = readJsonNode(jsonNode, "api").asText();
        return new AuthGrantedAuthority(code, page, api);
    }

    private JsonNode readJsonNode(JsonNode jsonNode, String field) {
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }

}