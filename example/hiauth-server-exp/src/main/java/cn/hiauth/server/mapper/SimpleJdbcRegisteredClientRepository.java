package cn.hiauth.server.mapper;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.util.List;
import java.util.Set;

public class SimpleJdbcRegisteredClientRepository extends JdbcRegisteredClientRepository {

    private static final String TABLE_NAME = "oauth2_registered_client";

    private static final String COLUMN_NAMES = "id, "
            + "client_id, "
            + "client_id_issued_at, "
            + "client_secret, "
            + "client_secret_expires_at, "
            + "client_name, "
            + "client_authentication_methods, "
            + "authorization_grant_types, "
            + "redirect_uris, "
            + "post_logout_redirect_uris, "
            + "scopes, "
            + "client_settings,"
            + "token_settings";

    private static final String LOAD_REGISTERED_CLIENT_SQL = "SELECT " + COLUMN_NAMES + " FROM " + TABLE_NAME + " WHERE ";

    public SimpleJdbcRegisteredClientRepository(JdbcOperations jdbcOperations) {
        super(jdbcOperations);
    }

    public List<RegisteredClient> findByClientIds(Set<String> clientIds) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("clientIds", clientIds);
        StringBuilder sb = new StringBuilder();
        sb.append(LOAD_REGISTERED_CLIENT_SQL).append("client_id IN (");
        clientIds.forEach(i -> sb.append("'").append(i).append("',"));
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        List<RegisteredClient> result = this.getJdbcOperations().query(sb.toString(), this.getRegisteredClientRowMapper());
        return !result.isEmpty() ? result : null;
    }

}
