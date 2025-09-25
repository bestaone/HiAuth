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
        Long appId = readJsonNode(jsonNode, CustomAuthUserAttrs.APP_ID).asLong();
        Long cid = readJsonNode(jsonNode, CustomAuthUserAttrs.CID).asLong();
        Long userId = readJsonNode(jsonNode, CustomAuthUserAttrs.USER_ID).asLong();
        Long empId = readJsonNode(jsonNode, CustomAuthUserAttrs.EMP_ID).asLong();
        String name = readJsonNode(jsonNode, CustomAuthUserAttrs.NAME).asText();
        String username = readJsonNode(jsonNode, CustomAuthUserAttrs.USERNAME).asText();
        String phoneNum = readJsonNode(jsonNode, CustomAuthUserAttrs.PHONE_NUM).asText();
        String avatarUrl = readJsonNode(jsonNode, CustomAuthUserAttrs.AVATAR_URL).asText();
        Boolean isSysAdmin = readJsonNode(jsonNode, CustomAuthUserAttrs.IS_SYS_ADMIN).asBoolean();
        Boolean isCorpAdmin = readJsonNode(jsonNode, CustomAuthUserAttrs.IS_CORP_ADMIN).asBoolean();

        Set<AuthGrantedAuthority> authorities = mapper.convertValue(jsonNode.get(CustomAuthUserAttrs.AUTHORITIES), AUTHORITY_SET);

        JsonNode passwordNode = readJsonNode(jsonNode, "password");
        String password = passwordNode.asText("");

        return new AuthUser(appId, cid, userId, empId, name, username, password, phoneNum, avatarUrl, isSysAdmin, authorities, isCorpAdmin);
    }

    private JsonNode readJsonNode(JsonNode jsonNode, String field) {
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }

}