package cn.hiauth.server.config.web.auth;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;

import java.io.IOException;
import java.util.Set;

public class AuthUserDeserializer extends JsonDeserializer<AuthUser> {

    private static final TypeReference<Set<AuthGrantedAuthority>> AUTHORITY_SET = new TypeReference<>() {
    };

    @Override
    public AuthUser deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);
        Long appId = readJsonNode(jsonNode, "appId").asLong();
        Long cid = readJsonNode(jsonNode, "cid").asLong();
        Long userId = readJsonNode(jsonNode, "userId").asLong();
        Long empId = readJsonNode(jsonNode, "empId").asLong();
        String name = readJsonNode(jsonNode, "name").asText();
        String username = readJsonNode(jsonNode, "username").asText();
        String phoneNum = readJsonNode(jsonNode, "phoneNum").asText();
        String avatarUrl = readJsonNode(jsonNode, "avatarUrl").asText();
        Boolean isSysAdmin = readJsonNode(jsonNode, "isSysAdmin").asBoolean();
        Boolean isCorpAdmin = readJsonNode(jsonNode, "isCorpAdmin").asBoolean();

        Set<AuthGrantedAuthority> authorities = mapper.convertValue(jsonNode.get("authorities"), AUTHORITY_SET);

        JsonNode passwordNode = readJsonNode(jsonNode, "password");
        String password = passwordNode.asText("");

        return new AuthUser(appId, cid, userId, empId, name, username, password, phoneNum, avatarUrl, isSysAdmin, authorities, isCorpAdmin);
    }

    private JsonNode readJsonNode(JsonNode jsonNode, String field) {
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }

}