-- ----------------------------
-- Table structure for oauth2_authorization
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth2_authorization";
CREATE TABLE "public"."oauth2_authorization" (
                                                 "id" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
                                                 "registered_client_id" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
                                                 "principal_name" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
                                                 "authorization_grant_type" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
                                                 "authorized_scopes" varchar(1000) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
                                                 "attributes" text COLLATE "pg_catalog"."default",
                                                 "state" varchar(500) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
                                                 "authorization_code_value" text COLLATE "pg_catalog"."default",
                                                 "authorization_code_issued_at" timestamp(0),
                                                 "authorization_code_expires_at" timestamp(0),
                                                 "authorization_code_metadata" text COLLATE "pg_catalog"."default",
                                                 "access_token_value" text COLLATE "pg_catalog"."default",
                                                 "access_token_issued_at" timestamp(0),
                                                 "access_token_expires_at" timestamp(0),
                                                 "access_token_metadata" text COLLATE "pg_catalog"."default",
                                                 "access_token_type" varchar(100) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
                                                 "access_token_scopes" varchar(1000) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
                                                 "oidc_id_token_value" text COLLATE "pg_catalog"."default",
                                                 "oidc_id_token_issued_at" timestamp(0),
                                                 "oidc_id_token_expires_at" timestamp(0),
                                                 "oidc_id_token_metadata" text COLLATE "pg_catalog"."default",
                                                 "refresh_token_value" text COLLATE "pg_catalog"."default",
                                                 "refresh_token_issued_at" timestamp(0),
                                                 "refresh_token_expires_at" timestamp(0),
                                                 "refresh_token_metadata" text COLLATE "pg_catalog"."default",
                                                 "user_code_value" text COLLATE "pg_catalog"."default",
                                                 "user_code_issued_at" timestamp(0),
                                                 "user_code_expires_at" timestamp(0),
                                                 "user_code_metadata" text COLLATE "pg_catalog"."default",
                                                 "device_code_value" text COLLATE "pg_catalog"."default",
                                                 "device_code_issued_at" timestamp(0),
                                                 "device_code_expires_at" timestamp(0),
                                                 "device_code_metadata" text COLLATE "pg_catalog"."default"
)
;
COMMENT ON TABLE "public"."oauth2_authorization" IS 'oauth2认证';

-- ----------------------------
-- Records of oauth2_authorization
-- ----------------------------
BEGIN;
INSERT INTO "public"."oauth2_authorization" ("id", "registered_client_id", "principal_name", "authorization_grant_type", "authorized_scopes", "attributes", "state", "authorization_code_value", "authorization_code_issued_at", "authorization_code_expires_at", "authorization_code_metadata", "access_token_value", "access_token_issued_at", "access_token_expires_at", "access_token_metadata", "access_token_type", "access_token_scopes", "oidc_id_token_value", "oidc_id_token_issued_at", "oidc_id_token_expires_at", "oidc_id_token_metadata", "refresh_token_value", "refresh_token_issued_at", "refresh_token_expires_at", "refresh_token_metadata", "user_code_value", "user_code_issued_at", "user_code_expires_at", "user_code_metadata", "device_code_value", "device_code_issued_at", "device_code_expires_at", "device_code_metadata") VALUES ('c0cea6e9-313e-4f3a-81fb-752e2ee7c51a', 'abcd-91', 'corpadmin', 'authorization_code', 'openid,profile', '{"@class":"java.util.Collections$UnmodifiableMap","org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest":{"@class":"org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest","authorizationUri":"http://auth.hiauth.cn/oauth2/authorize","authorizationGrantType":{"value":"authorization_code"},"responseType":{"value":"code"},"clientId":"himall","redirectUri":"http://127.0.0.1:9000/login/oauth2/code/hiauth-code","scopes":["java.util.Collections$UnmodifiableSet",["openid","profile"]],"state":"h_UPwe2WxGfvrA8_hWpVBs9V5wSrYQ66zXRr6EMY758=","additionalParameters":{"@class":"java.util.Collections$UnmodifiableMap","nonce":"1TGm7Vj_A424ACvfEolyyhvFkBNF0c3uUmBqjrqkrBQ","continue":""},"authorizationRequestUri":"http://auth.hiauth.cn/oauth2/authorize?response_type=code&client_id=himall&scope=openid%20profile&state=h_UPwe2WxGfvrA8_hWpVBs9V5wSrYQ66zXRr6EMY758%3D&redirect_uri=http://127.0.0.1:9000/login/oauth2/code/hiauth-code&nonce=1TGm7Vj_A424ACvfEolyyhvFkBNF0c3uUmBqjrqkrBQ&continue=","attributes":{"@class":"java.util.Collections$UnmodifiableMap"}},"java.security.Principal":{"@class":"org.springframework.security.authentication.UsernamePasswordAuthenticationToken","authorities":["java.util.Collections$UnmodifiableRandomAccessList",[]],"details":{"@class":"org.springframework.security.web.authentication.WebAuthenticationDetails","remoteAddress":"127.0.0.1","sessionId":"85389c3c-1c21-4ce3-b53b-0452280d03dc"},"authenticated":true,"principal":{"@class":"cn.hiauth.server.config.web.auth.AuthUser","appId":91,"cid":1,"userId":11,"empId":1,"name":"企业管理员","username":"corpadmin","password":"","phoneNum":"13400000001","avatarUrl":"/unpapi/image/2c924149ddfe4bd181959ee9bede10c0.jpeg","isSysAdmin":false,"authorities":["java.util.HashSet",[]],"enabled":true,"accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true},"credentials":null}}', NULL, 'XE6zFn48bf4uk2qjiIs06H-7HpO2WpXlRIsiqAp27T2Id5qoct2j6iX6qw7d3FM0f0EzK3Cv8EnPsFYHRyIT7TSA8mAP0JUCMPqw_VK7UGGLjp1lnWurRfU13DKPndJx', '2025-03-17 14:42:20', '2025-03-17 14:52:20', '{"@class":"java.util.Collections$UnmodifiableMap","metadata.token.invalidated":false}', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."oauth2_authorization" ("id", "registered_client_id", "principal_name", "authorization_grant_type", "authorized_scopes", "attributes", "state", "authorization_code_value", "authorization_code_issued_at", "authorization_code_expires_at", "authorization_code_metadata", "access_token_value", "access_token_issued_at", "access_token_expires_at", "access_token_metadata", "access_token_type", "access_token_scopes", "oidc_id_token_value", "oidc_id_token_issued_at", "oidc_id_token_expires_at", "oidc_id_token_metadata", "refresh_token_value", "refresh_token_issued_at", "refresh_token_expires_at", "refresh_token_metadata", "user_code_value", "user_code_issued_at", "user_code_expires_at", "user_code_metadata", "device_code_value", "device_code_issued_at", "device_code_expires_at", "device_code_metadata") VALUES ('2c6b348c-51c4-46bd-961d-2309322acdcf', 'abcd-91', 'corpadmin', 'authorization_code', 'openid,profile', '{"@class":"java.util.Collections$UnmodifiableMap","org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest":{"@class":"org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest","authorizationUri":"http://auth.hiauth.cn/oauth2/authorize","authorizationGrantType":{"value":"authorization_code"},"responseType":{"value":"code"},"clientId":"himall","redirectUri":"http://127.0.0.1:9000/login/oauth2/code/hiauth-code","scopes":["java.util.Collections$UnmodifiableSet",["openid","profile"]],"state":"fW0pyAu6XD3qZmnVMkEizGXHDkKC_LS794lw32XGNoQ=","additionalParameters":{"@class":"java.util.Collections$UnmodifiableMap","nonce":"GRdLMRYmxsTKHRSptXFBwVay5D9RRqiJtmC1yjONZ-M"},"authorizationRequestUri":"http://auth.hiauth.cn/oauth2/authorize?response_type=code&client_id=himall&scope=openid%20profile&state=fW0pyAu6XD3qZmnVMkEizGXHDkKC_LS794lw32XGNoQ%3D&redirect_uri=http://127.0.0.1:9000/login/oauth2/code/hiauth-code&nonce=GRdLMRYmxsTKHRSptXFBwVay5D9RRqiJtmC1yjONZ-M","attributes":{"@class":"java.util.Collections$UnmodifiableMap"}},"java.security.Principal":{"@class":"org.springframework.security.authentication.UsernamePasswordAuthenticationToken","authorities":["java.util.Collections$UnmodifiableRandomAccessList",[]],"details":{"@class":"org.springframework.security.web.authentication.WebAuthenticationDetails","remoteAddress":"127.0.0.1","sessionId":"85389c3c-1c21-4ce3-b53b-0452280d03dc"},"authenticated":true,"principal":{"@class":"cn.hiauth.server.config.web.auth.AuthUser","appId":91,"cid":1,"userId":11,"empId":1,"name":"企业管理员","username":"corpadmin","password":"","phoneNum":"13400000001","avatarUrl":"/unpapi/image/2c924149ddfe4bd181959ee9bede10c0.jpeg","isSysAdmin":false,"authorities":["java.util.HashSet",[]],"enabled":true,"accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true},"credentials":null}}', NULL, 'RLP-ktPLd7jAROUySb5yLGKe77ctnqNj0A40ZFypKN6yOuf191FR11VveYfF_7UY5bwbfe8aa5vB2ONm90D6DYAm51C5fEKRhYqqHbN9l6lohMvTcr7obPLYTxrOqZHX', '2025-03-17 14:43:59', '2025-03-17 14:53:59', '{"@class":"java.util.Collections$UnmodifiableMap","metadata.token.invalidated":true}', 'eyJraWQiOiI5Y2QzNGQxYS03Y2E0LTQyZDQtOWUxNC04OWQxYzlhODdkYTMiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJjb3JwYWRtaW4iLCJhdWQiOiJoaW1hbGwiLCJlbXBJZCI6MSwibmJmIjoxNzQyMTkzODM4LCJzY29wZSI6WyJvcGVuaWQiLCJwcm9maWxlIl0sImlzcyI6Imh0dHA6Ly9hdXRoLmhpYXV0aC5jbiIsImV4cCI6MTc0MjIyOTgzOCwiaWF0IjoxNzQyMTkzODM4LCJ1c2VySWQiOjExLCJqdGkiOiI0ZGFjMDRkZi0zYmI5LTQ1YjQtYTRlZC1hMzZiYmEzODAxMDciLCJjaWQiOjF9.oV_aXcNajNWciDWB6pMqPMzP-92GbPoWZKr8mH5VhNME3BGm_KJYDwzEAdI-MUcH8Ul6a3FnmMUJ7J8_ttnRTw3F_OEOn3ZPH9BxKhfQJvfRVII6qUDnPRB9YKLzyBqj_E1eFTRfUKqvRFBYqDNwNXR99501PVUqNqstrYgtRD6vYSFEkqEo5BUf9C-kLFMWZVEqZ710fEAhK2z1aRWXXlO-ZehViP03mCvsyBSw1zsgtezgTfrcodlNUyFQkV39TkChfigcJr8sEAXhm3cabB4kbIKOrwevUOLb18FlWOuzNi47SK96bcFkgDNO1xrxMrTUdtJFGv9Eg5-MnSClhg', '2025-03-17 14:43:59', '2025-03-18 00:43:59', '{"@class":"java.util.Collections$UnmodifiableMap","org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat":"self-contained","metadata.token.claims":{"@class":"java.util.Collections$UnmodifiableMap","sub":"corpadmin","aud":["java.util.Collections$SingletonList",["himall"]],"empId":["java.lang.Long",1],"nbf":["java.time.Instant",1742193838.722545500],"scope":["java.util.Collections$UnmodifiableSet",["openid","profile"]],"iss":["java.net.URL","http://auth.hiauth.cn"],"exp":["java.time.Instant",1742229838.722545500],"iat":["java.time.Instant",1742193838.722545500],"userId":["java.lang.Long",11],"jti":"4dac04df-3bb9-45b4-a4ed-a36bba380107","cid":["java.lang.Long",1]},"metadata.token.invalidated":false}', 'Bearer', 'openid,profile', 'eyJraWQiOiI5Y2QzNGQxYS03Y2E0LTQyZDQtOWUxNC04OWQxYzlhODdkYTMiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJjb3JwYWRtaW4iLCJhdWQiOiJoaW1hbGwiLCJlbXBJZCI6MSwiYXpwIjoiaGltYWxsIiwiaXNzIjoiaHR0cDovL2F1dGguaGlhdXRoLmNuIiwiZXhwIjoxNzQyMTk1NjM4LCJpYXQiOjE3NDIxOTM4MzgsIm5vbmNlIjoiR1JkTE1SWW14c1RLSFJTcHRYRkJ3VmF5NUQ5UlJxaUp0bUMxeWpPTlotTSIsInVzZXJJZCI6MTEsImp0aSI6IjJjZDExMmNlLWU0MTktNDE2OC05YTliLTA2MWNhNzRhYTAyNSIsImNpZCI6MX0.MJYS55kOZ2B1GWUYdnhk_Od8liSsP2I71pFo_8LoI4w3n7W4Gk-lN500P3o4DOo2sKbd88qTd5mqbVvIwBANuyE3RfU8etY1CDmKeAXZ09pgTiFh1gm6aZS0l0Wst8C5DM0JuGrlupnjXfd2JbYuRpBfcFGMVYIq-hTpNF25Jl5mODFi-JBmETj5vRTvmUWvr72PwQM_xP4VZkPLMcA-IBrxXkNu6AH4qJR-DmWyyT1t9befh7mHtDVEDN0gNjlwayei7Q5jKoPQxgb3p74odpghbD1zgF7i9JFiIs7O0cIpGkdfwFUF7mJX1l0v-XGbqrq28y1AZxTveqhhY6z_9A', '2025-03-17 14:43:59', '2025-03-17 15:13:59', '{"@class":"java.util.Collections$UnmodifiableMap","metadata.token.claims":{"@class":"java.util.Collections$UnmodifiableMap","sub":"corpadmin","aud":["java.util.Collections$SingletonList",["himall"]],"empId":["java.lang.Long",1],"azp":"himall","iss":["java.net.URL","http://auth.hiauth.cn"],"exp":["java.time.Instant",1742195638.864477997],"iat":["java.time.Instant",1742193838.864477997],"nonce":"GRdLMRYmxsTKHRSptXFBwVay5D9RRqiJtmC1yjONZ-M","userId":["java.lang.Long",11],"jti":"2cd112ce-e419-4168-9a9b-061ca74aa025","cid":["java.lang.Long",1]},"metadata.token.invalidated":false}', 'qzGNW_e2ueHhH1QDQbnqcMy1G9vAfI1AFQlp9fMZiyisv7g9aGIev7OecF8cT48yGgIqu_7nt0EKmpj8Dxuq9vCzkVpM8zz_ruwd_15hvUrpEvkzdoqw7yBfYaT0q5z0', '2025-03-17 14:43:59', '2025-04-16 14:43:59', '{"@class":"java.util.Collections$UnmodifiableMap","metadata.token.invalidated":false}', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for oauth2_authorization_consent
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth2_authorization_consent";
CREATE TABLE "public"."oauth2_authorization_consent" (
                                                         "registered_client_id" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
                                                         "principal_name" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
                                                         "authorities" varchar(1000) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON TABLE "public"."oauth2_authorization_consent" IS 'oauth2认证授权';

-- ----------------------------
-- Records of oauth2_authorization_consent
-- ----------------------------
BEGIN;
INSERT INTO "public"."oauth2_authorization_consent" ("registered_client_id", "principal_name", "authorities") VALUES ('abcd-91', 'corpadmin', 'SCOPE_openid,SCOPE_profile');
COMMIT;

-- ----------------------------
-- Table structure for oauth2_registered_client
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth2_registered_client";
CREATE TABLE "public"."oauth2_registered_client" (
                                                     "id" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
                                                     "client_id" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
                                                     "client_id_issued_at" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                                     "client_secret" varchar(200) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
                                                     "client_secret_expires_at" timestamp(0),
                                                     "client_name" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
                                                     "client_authentication_methods" varchar(1000) COLLATE "pg_catalog"."default" NOT NULL,
                                                     "authorization_grant_types" varchar(1000) COLLATE "pg_catalog"."default" NOT NULL,
                                                     "redirect_uris" varchar(1000) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
                                                     "post_logout_redirect_uris" varchar(1000) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
                                                     "scopes" varchar(1000) COLLATE "pg_catalog"."default" NOT NULL,
                                                     "client_settings" varchar(2000) COLLATE "pg_catalog"."default" NOT NULL,
                                                     "token_settings" varchar(2000) COLLATE "pg_catalog"."default" NOT NULL,
                                                     "app_id" int8 NOT NULL,
                                                     "cid" int8 NOT NULL
)
;
COMMENT ON COLUMN "public"."oauth2_registered_client"."app_id" IS '应用id（自定义扩展字段）';
COMMENT ON COLUMN "public"."oauth2_registered_client"."cid" IS '租户id（自定义扩展字段）';
COMMENT ON TABLE "public"."oauth2_registered_client" IS 'oauth2客户端';

-- ----------------------------
-- Records of oauth2_registered_client
-- ----------------------------
BEGIN;
INSERT INTO "public"."oauth2_registered_client" ("id", "client_id", "client_id_issued_at", "client_secret", "client_secret_expires_at", "client_name", "client_authentication_methods", "authorization_grant_types", "redirect_uris", "post_logout_redirect_uris", "scopes", "client_settings", "token_settings", "app_id", "cid") VALUES ('abcd-11', 'zhiyuan', '2025-01-01 00:00:00', '{bcrypt}$2a$10$L/QEiqOTG3V8ktKMbBLR0..oaeGXHqAdT5aZ3tRdrMFqS87zCT.yu', NULL, '志远业务系统', 'client_secret_basic,client_secret_post', 'refresh_token,authorization_code,client_credentials,password', 'http://dcss-dev.vking.fun:31080/apisvc-dcss/oauth2/token/redirect,http://127.0.0.1:9001/oauth2/token/redirect', 'http://127.0.0.1:8080/', 'openid,profile,user', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-authorization-consent":true,"settings.client.require-proof-key":false,"cid":"1"}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",36000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",2592000],"settings.token.authorization-code-time-to-live":["java.time.Duration",600],"settings.token.device-code-time-to-live":["java.time.Duration",300]}', 11, 1);
INSERT INTO "public"."oauth2_registered_client" ("id", "client_id", "client_id_issued_at", "client_secret", "client_secret_expires_at", "client_name", "client_authentication_methods", "authorization_grant_types", "redirect_uris", "post_logout_redirect_uris", "scopes", "client_settings", "token_settings", "app_id", "cid") VALUES ('abcd-12', 'zhiyuan-admin', '2025-01-01 00:00:00', '{bcrypt}$2a$10$L/QEiqOTG3V8ktKMbBLR0..oaeGXHqAdT5aZ3tRdrMFqS87zCT.yu', NULL, '志远管理系统', 'client_secret_basic,client_secret_post', 'refresh_token,authorization_code,client_credentials,password', 'http://dcss-dev.vking.fun:31080/apisvc-admin/oauth2/token/redirect,http://127.0.0.1:9002/oauth2/token/redirect', 'http://127.0.0.1:8080/', 'openid,profile,user', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-authorization-consent":true,"settings.client.require-proof-key":false,"cid":"1"}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",36000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",2592000],"settings.token.authorization-code-time-to-live":["java.time.Duration",600],"settings.token.device-code-time-to-live":["java.time.Duration",300]}', 12, 1);
INSERT INTO "public"."oauth2_registered_client" ("id", "client_id", "client_id_issued_at", "client_secret", "client_secret_expires_at", "client_name", "client_authentication_methods", "authorization_grant_types", "redirect_uris", "post_logout_redirect_uris", "scopes", "client_settings", "token_settings", "app_id", "cid") VALUES ('abcd-91', 'himall', '2025-01-01 00:00:00', '{bcrypt}$2a$10$L/QEiqOTG3V8ktKMbBLR0..oaeGXHqAdT5aZ3tRdrMFqS87zCT.yu', NULL, 'HIMALL', 'client_secret_basic,client_secret_post', 'refresh_token,authorization_code,client_credentials,password', 'http://himall.hiauth.com/login/oauth2/code/hiauth-code,http://127.0.0.1:9000/login/oauth2/code/hiauth-code', 'http://127.0.0.1:8080/', 'openid,profile,user', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-authorization-consent":true,"settings.client.require-proof-key":false,"cid":"1","appId":"91"}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",36000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",2592000],"settings.token.authorization-code-time-to-live":["java.time.Duration",600],"settings.token.device-code-time-to-live":["java.time.Duration",300]}', 91, 1);
INSERT INTO "public"."oauth2_registered_client" ("id", "client_id", "client_id_issued_at", "client_secret", "client_secret_expires_at", "client_name", "client_authentication_methods", "authorization_grant_types", "redirect_uris", "post_logout_redirect_uris", "scopes", "client_settings", "token_settings", "app_id", "cid") VALUES ('abcd-13', 'zhiyuan-oms', '2025-01-01 00:00:00', '{bcrypt}$2a$10$L/QEiqOTG3V8ktKMbBLR0..oaeGXHqAdT5aZ3tRdrMFqS87zCT.yu', NULL, '志远运营系统', 'client_secret_basic,client_secret_post', 'refresh_token,authorization_code,client_credentials,password', 'http://dcss-dev.vking.fun:31080/apisvc-oms/oauth2/token/redirect,http://127.0.0.1:4000/oauth2/token/redirect', 'http://127.0.0.1:8080/', 'openid,profile,user', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-authorization-consent":true,"settings.client.require-proof-key":false,"cid":"1"}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",36000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",2592000],"settings.token.authorization-code-time-to-live":["java.time.Duration",600],"settings.token.device-code-time-to-live":["java.time.Duration",300]}', 13, 1);
COMMIT;

-- ----------------------------
-- Table structure for t_app
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_app";
CREATE TABLE "public"."t_app" (
                                  "id" int8 NOT NULL,
                                  "cid" int8,
                                  "name" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
                                  "icon" varchar(200) COLLATE "pg_catalog"."default",
                                  "create_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  "creator" int8,
                                  "remark" varchar(200) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."t_app"."id" IS '主键';
COMMENT ON COLUMN "public"."t_app"."cid" IS '创建应用的企业CID';
COMMENT ON COLUMN "public"."t_app"."name" IS '名称';
COMMENT ON COLUMN "public"."t_app"."icon" IS '图标';
COMMENT ON COLUMN "public"."t_app"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."t_app"."creator" IS '创建人';
COMMENT ON COLUMN "public"."t_app"."remark" IS '说明';
COMMENT ON TABLE "public"."t_app" IS '应用';

-- ----------------------------
-- Records of t_app
-- ----------------------------
BEGIN;
INSERT INTO "public"."t_app" ("id", "cid", "name", "icon", "create_time", "creator", "remark") VALUES (11, NULL, '志远业务系统', '/unpapi/image/7af4deea5db94394b46690c1f14f9a0d.jpg', '2025-01-01 00:00:00', 1, '志远业务系统');
INSERT INTO "public"."t_app" ("id", "cid", "name", "icon", "create_time", "creator", "remark") VALUES (12, NULL, '志远管理系统', '/unpapi/image/ded337a3c4064cff8f7e6a2d607aac2e.jpeg', '2025-01-01 00:00:00', 1, '志远管理系统');
INSERT INTO "public"."t_app" ("id", "cid", "name", "icon", "create_time", "creator", "remark") VALUES (13, NULL, '志远运营系统', '/unpapi/image/de58d213858240ce83681e8194e6ece3.jpeg', '2025-01-01 00:00:00', 1, '志远运营系统',);
INSERT INTO "public"."t_app" ("id", "cid", "name", "icon", "create_time", "creator", "remark") VALUES (91, NULL, 'HiMall', '/unpapi/image/f91626c1876c4ff293056efda7fef04c.jpg', '2025-01-01 00:00:00', 1, 'HiMall');
COMMIT;

-- ----------------------------
-- Table structure for t_app_resource
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_app_resource";
CREATE TABLE "public"."t_app_resource" (
                                           "id" int8 NOT NULL,
                                           "pid" int8,
                                           "app_id" int8 NOT NULL,
                                           "code" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                           "url" varchar(50) COLLATE "pg_catalog"."default",
                                           "name" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
                                           "type" int4 NOT NULL,
                                           "remark" varchar(50) COLLATE "pg_catalog"."default",
                                           "extend" jsonb,
                                           "sort" int2 NOT NULL DEFAULT 1,
                                           "api" varchar(50) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."t_app_resource"."id" IS '主键';
COMMENT ON COLUMN "public"."t_app_resource"."pid" IS '父节点';
COMMENT ON COLUMN "public"."t_app_resource"."app_id" IS '应用id';
COMMENT ON COLUMN "public"."t_app_resource"."code" IS '编码';
COMMENT ON COLUMN "public"."t_app_resource"."url" IS '前端地址';
COMMENT ON COLUMN "public"."t_app_resource"."name" IS '名称';
COMMENT ON COLUMN "public"."t_app_resource"."type" IS '资源类型，1：目录、菜单，2：页面，3：功能、接口';
COMMENT ON COLUMN "public"."t_app_resource"."remark" IS '备注';
COMMENT ON COLUMN "public"."t_app_resource"."extend" IS '扩展字段';
COMMENT ON COLUMN "public"."t_app_resource"."sort" IS '排序';
COMMENT ON COLUMN "public"."t_app_resource"."api" IS '接口地址';
COMMENT ON TABLE "public"."t_app_resource" IS '系统资源';

-- ----------------------------
-- Records of t_app_resource
-- ----------------------------
BEGIN;
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10210000, NULL, 11, 'sysmgr', '/sysMgr', '系统管理', 2, NULL, '{"isCustomLayout": true}', 11, '/api/sysMgr/**');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10111500, 10110000, 11, 'faulthandling', '/faulthandling', '故障处置', 1, NULL, NULL, 5, NULL);
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10121111, 10121100, 11, 'criticaldata', '/criticaldata', '关键数据', 2, NULL, '{"isCustomLayout": true}', 1, NULL);
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10131900, 10130000, 11, 'wrjgl', 'wrjgl', '无人机管理', 2, NULL, '{"isCustomLayout": true}', 1, NULL);
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10132000, 10130000, 11, 'fzxtjs', '/fzxtjs', '辅助系统监视', 2, NULL, '{"isCustomLayout": true}', 1, NULL);
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10120000, NULL, 11, 'device', NULL, '设备', 1, NULL, NULL, 2, NULL);
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10150000, NULL, 11, 'appmgr', '/appMgr', '应用管理', 2, NULL, NULL, 5, '/api/appMgr/**');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10121100, 10120000, 11, 'alldata', '/alldata', '全数据', 1, NULL, NULL, 1, NULL);
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10121211, 10121200, 11, 'jcpt', '/jcpt', '基础平台', 2, NULL, '{"isCustomLayout": true}', 1, '/api/jcpt/**');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10121200, 10120000, 11, 'allprocess', '/allprocess', '全过程', 1, NULL, NULL, 1, NULL);
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10121212, 10121200, 11, 'ztfx', '/ztfx', '状态分析', 2, NULL, '{"isCustomLayout": true}', 2, '/api/ztfx/**');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10121213, 10121200, 11, 'gzzd', '/gzzd', '故障诊断', 2, NULL, '{"isCustomLayout": true}', 3, '/api/gzzd/**');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10121311, 10121300, 11, 'yxt', '/yxt', '运行态', 2, NULL, '{"isCustomLayout": true}', 4, '/api/yxt/**');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10121300, 10120000, 11, 'allknowledge', '/allknowledge', '全知识', 1, NULL, NULL, 1, NULL);
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10121312, 10121300, 11, 'jst', '/jst', '建设态', 2, NULL, '{"isCustomLayout": true}', 5, '/api/jst/**');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10121313, 10121300, 11, 'sjt', '/sjt', '设计态', 2, NULL, '{"isCustomLayout": true}', 6, '/api/sjt/**');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10131100, 10130000, 11, 'szxfxt', '/szxfxt', '数字消防系统', 2, NULL, '{"isCustomLayout": true}', 1, '/api/szxfxt/**');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10131200, 10130000, 11, 'szafxt', '/szafxt', '数字安防系统', 2, NULL, '{"isCustomLayout": true}', 2, '/api/szafxt/**');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10160000, NULL, 11, 'layoutmgr', '/layoutMgr', '自定义布局', 2, NULL, NULL, 6, '/api/layoutMgr/**');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10110000, NULL, 11, 'zl', '', '总览', 1, NULL, NULL, 1, NULL);
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10170000, NULL, 11, 'xths', '/xths', '协同商会', 2, NULL, '{"isCustomLayout": true}', 7, '/api/xths/**');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10180000, NULL, 11, 'knowledgestore', '/knowledgeStore', '知识商店', 2, NULL, '{"isCustomLayout": true}', 8, '/api/knowledgeStore/**');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10190000, NULL, 11, 'appstore', '/appStore', '应用商店', 2, NULL, '{"isCustomLayout": true}', 9, '/api/appStore/**');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10111600, 10110000, 11, 'report', '/report', '智能报表', 2, NULL, '{"isCustomLayout": true}', 6, '/api/report/**');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10111700, 10110000, 11, 'xbzx', '/xbzx', '协办中心', 2, NULL, '{"isCustomLayout": true}', 7, '/api/xbzx/**');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10140000, NULL, 11, 'editstand', '/editStand', '编辑看台', 2, NULL, NULL, 4, '/api/editStand/**');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10131300, 10130000, 11, 'jyyjzcpt', '/jyyjzcpt', '精益运简支撑平台', 2, NULL, '{"isCustomLayout": true}', 1, NULL);
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10131400, 10130000, 11, 'ktdxsxt', '/ktdxsxt', '空天地巡视系统', 2, NULL, '{"isCustomLayout": true}', 2, NULL);
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10131500, 10130000, 11, 'qjjsxt', '/qjjsxt', '全景监视系统', 2, NULL, '{"isCustomLayout": true}', 3, NULL);
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10111300, 10110000, 11, 'statusmonitor', '/statusMonitor', '状态监测', 2, NULL, '{"isCustomLayout": true}', 3, '/api/statusMonitor/**');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10111400, 10110000, 11, 'videoinspection', '/videoInspection', '视频巡视', 2, NULL, '{"isCustomLayout": true}', 4, '/api/videoInspection/**');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10111512, 10111500, 11, 'glfx', '/glfx', '故录分析', 2, NULL, '{"isCustomLayout": true}', 2, '/api/glfx/**');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10111513, 10111500, 11, 'xfyj', '/xfyj', '消防应急', 2, NULL, '{"isCustomLayout": true}', 3, '/api/xfyj/**');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10130000, NULL, 11, 'subsystem', NULL, '系统', 1, NULL, NULL, 3, NULL);
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10131600, 10130000, 11, 'sbztfzsb', '/sbztfzsb', '设备状态辅助识别', 2, NULL, '{"isCustomLayout": true}', 1, NULL);
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10131700, 10130000, 11, 'sjzlgl', '/sjzlgl', '数据质量管理', 2, NULL, '{"isCustomLayout": true}', 1, NULL);
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10131800, 10130000, 11, 'xfsbgl', 'xfsbgl', '消防设备管理', 2, NULL, '{"isCustomLayout": true}', 1, NULL);
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10200000, NULL, 11, 'feedback', '/feedback', '意见反馈', 2, NULL, '{"isCustomLayout": true}', 10, '/api/feedback/**');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10220000, NULL, 11, 'gjts', '/gjts', '告警提示', 2, NULL, NULL, 1, '/api/gjts/**');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10111200, 10110000, 11, 'ywjj', '/ywjj', '运维交接', 2, NULL, '{"isCustomLayout": true}', 2, '/api/ywjj/**');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10111100, 10110000, 11, 'overview', '/overview', '全站总览', 2, NULL, '{"isCustomLayout": true}', 1, '/api/overview/**');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (10111511, 10111500, 11, 'glgl', '/glgl', '故录管理', 2, NULL, '{"isCustomLayout": true}', 1, '/api/glgl/**');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (12110000, NULL, 12, 'xtgl', '/xtgl', '系统管理', 1, NULL, NULL, 1, '/api/xtgl/*');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (12120000, NULL, 12, 'rzgl', '/rzgl', '日志管理', 1, NULL, NULL, 2, '/api/rzgl/*');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (13140000, NULL, 13, 'xtpz', '/xtpz', '系统配置', 1, NULL, NULL, 4, '/api/xtpz/*');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (13130000, NULL, 13, 'yjsbgl', '/yjsbgl', '硬件设备管理', 1, NULL, NULL, 3, '/api/yjsbgl/*');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (13120000, NULL, 13, 'zxtgl', '/zxtgl', '子系统管理', 1, NULL, NULL, 2, '/api/zxtgl/*');
INSERT INTO "public"."t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (13110000, NULL, 13, 'zsgl', '/zsgl', ' 站所管理', 1, NULL, NULL, 1, '/api/zsgl/*');
COMMIT;

-- ----------------------------
-- Table structure for t_area
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_area";
CREATE TABLE "public"."t_area" (
                                   "id" int8 NOT NULL,
                                   "type" int4 NOT NULL,
                                   "code" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
                                   "p_code" varchar(10) COLLATE "pg_catalog"."default",
                                   "name" varchar(50) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."t_area"."id" IS '主键';
COMMENT ON COLUMN "public"."t_area"."type" IS '类型：1-省，2-市，3-区';
COMMENT ON COLUMN "public"."t_area"."code" IS '区域编码';
COMMENT ON COLUMN "public"."t_area"."p_code" IS '父级区域编码';
COMMENT ON COLUMN "public"."t_area"."name" IS '区域名称';
COMMENT ON TABLE "public"."t_area" IS '行政区域';

-- ----------------------------
-- Records of t_area
-- ----------------------------
BEGIN;
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220000, 1, '220000', NULL, '吉林省');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410000, 1, '410000', NULL, '河南省');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511504, 3, '511504', '511500', '叙州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654200, 2, '654200', '650000', '塔城地区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654201, 3, '654201', '654200', '塔城市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654202, 3, '654202', '654200', '乌苏市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654203, 3, '654203', '654200', '沙湾市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654221, 3, '654221', '654200', '额敏县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654224, 3, '654224', '654200', '托里县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654225, 3, '654225', '654200', '裕民县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654226, 3, '654226', '654200', '和布克赛尔蒙古自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654300, 2, '654300', '650000', '阿勒泰地区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654301, 3, '654301', '654300', '阿勒泰市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654321, 3, '654321', '654300', '布尔津县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654322, 3, '654322', '654300', '富蕴县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654323, 3, '654323', '654300', '福海县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654324, 3, '654324', '654300', '哈巴河县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654325, 3, '654325', '654300', '青河县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654326, 3, '654326', '654300', '吉木乃县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (659001, 2, '659001', '650000', '石河子市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (659002, 2, '659002', '650000', '阿拉尔市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (659003, 2, '659003', '650000', '图木舒克市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (659004, 2, '659004', '650000', '五家渠市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (659005, 2, '659005', '650000', '北屯市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (659006, 2, '659006', '650000', '铁门关市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (659007, 2, '659007', '650000', '双河市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (659008, 2, '659008', '650000', '可克达拉市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (659009, 2, '659009', '650000', '昆玉市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (659010, 2, '659010', '650000', '胡杨河市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (659011, 2, '659011', '650000', '新星市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (810000, 1, '810000', NULL, '香港');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (820000, 1, '820000', NULL, '澳门');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500010, 2, '500010', '500000', '重庆市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (810010, 2, '810010', '810000', '香港');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (820010, 2, '820010', '820000', '澳门');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (110000, 1, '110000', NULL, '北京');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (110010, 2, '110010', '110000', '北京市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (120010, 2, '120010', '120000', '天津市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (310010, 2, '310010', '310000', '上海市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (120000, 1, '120000', NULL, '天津');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130000, 1, '130000', NULL, '河北省');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130100, 2, '130100', '130000', '石家庄市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130102, 3, '130102', '130100', '长安区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130104, 3, '130104', '130100', '桥西区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130105, 3, '130105', '130100', '新华区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130107, 3, '130107', '130100', '井陉矿区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130108, 3, '130108', '130100', '裕华区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130109, 3, '130109', '130100', '藁城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130110, 3, '130110', '130100', '鹿泉区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130111, 3, '130111', '130100', '栾城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130121, 3, '130121', '130100', '井陉县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130123, 3, '130123', '130100', '正定县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130125, 3, '130125', '130100', '行唐县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130126, 3, '130126', '130100', '灵寿县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130127, 3, '130127', '130100', '高邑县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130128, 3, '130128', '130100', '深泽县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130129, 3, '130129', '130100', '赞皇县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130130, 3, '130130', '130100', '无极县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130131, 3, '130131', '130100', '平山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130132, 3, '130132', '130100', '元氏县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130133, 3, '130133', '130100', '赵县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130181, 3, '130181', '130100', '辛集市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130183, 3, '130183', '130100', '晋州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130184, 3, '130184', '130100', '新乐市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130200, 2, '130200', '130000', '唐山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130202, 3, '130202', '130200', '路南区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (820100, 3, '820100', '820010', '澳门半岛');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (820200, 3, '820200', '820010', '氹仔岛');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (820300, 3, '820300', '820010', '路环岛');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (820400, 3, '820400', '820010', '路氹城');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (110101, 3, '110101', '110010', '东城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (110102, 3, '110102', '110010', '西城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (110105, 3, '110105', '110010', '朝阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (110106, 3, '110106', '110010', '丰台区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (110107, 3, '110107', '110010', '石景山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (110108, 3, '110108', '110010', '海淀区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (110109, 3, '110109', '110010', '门头沟区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (110111, 3, '110111', '110010', '房山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (110112, 3, '110112', '110010', '通州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (110113, 3, '110113', '110010', '顺义区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (110114, 3, '110114', '110010', '昌平区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (110115, 3, '110115', '110010', '大兴区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (110116, 3, '110116', '110010', '怀柔区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (120101, 3, '120101', '120010', '和平区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (120102, 3, '120102', '120010', '河东区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (120103, 3, '120103', '120010', '河西区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (120104, 3, '120104', '120010', '南开区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (120105, 3, '120105', '120010', '河北区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (120106, 3, '120106', '120010', '红桥区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (120110, 3, '120110', '120010', '东丽区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (120111, 3, '120111', '120010', '西青区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (120112, 3, '120112', '120010', '津南区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (120113, 3, '120113', '120010', '北辰区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (120114, 3, '120114', '120010', '武清区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (120115, 3, '120115', '120010', '宝坻区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (120116, 3, '120116', '120010', '滨海新区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (120117, 3, '120117', '120010', '宁河区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (120118, 3, '120118', '120010', '静海区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (120119, 3, '120119', '120010', '蓟州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130203, 3, '130203', '130200', '路北区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130204, 3, '130204', '130200', '古冶区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130205, 3, '130205', '130200', '开平区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130207, 3, '130207', '130200', '丰南区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130208, 3, '130208', '130200', '丰润区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130209, 3, '130209', '130200', '曹妃甸区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130224, 3, '130224', '130200', '滦南县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130225, 3, '130225', '130200', '乐亭县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130227, 3, '130227', '130200', '迁西县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130229, 3, '130229', '130200', '玉田县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130271, 3, '130271', '130200', '芦台经济开发区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130281, 3, '130281', '130200', '遵化市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130283, 3, '130283', '130200', '迁安市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130284, 3, '130284', '130200', '滦州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130300, 2, '130300', '130000', '秦皇岛市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130302, 3, '130302', '130300', '海港区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130303, 3, '130303', '130300', '山海关区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130304, 3, '130304', '130300', '北戴河区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130306, 3, '130306', '130300', '抚宁区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130321, 3, '130321', '130300', '青龙满族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130322, 3, '130322', '130300', '昌黎县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130324, 3, '130324', '130300', '卢龙县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130400, 2, '130400', '130000', '邯郸市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130402, 3, '130402', '130400', '邯山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130403, 3, '130403', '130400', '丛台区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130404, 3, '130404', '130400', '复兴区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130406, 3, '130406', '130400', '峰峰矿区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130407, 3, '130407', '130400', '肥乡区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130408, 3, '130408', '130400', '永年区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130423, 3, '130423', '130400', '临漳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130424, 3, '130424', '130400', '成安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130425, 3, '130425', '130400', '大名县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130426, 3, '130426', '130400', '涉县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130427, 3, '130427', '130400', '磁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130430, 3, '130430', '130400', '邱县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130431, 3, '130431', '130400', '鸡泽县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130432, 3, '130432', '130400', '广平县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130433, 3, '130433', '130400', '馆陶县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130434, 3, '130434', '130400', '魏县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130435, 3, '130435', '130400', '曲周县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130481, 3, '130481', '130400', '武安市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130500, 2, '130500', '130000', '邢台市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130502, 3, '130502', '130500', '襄都区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130503, 3, '130503', '130500', '信都区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130505, 3, '130505', '130500', '任泽区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130506, 3, '130506', '130500', '南和区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130522, 3, '130522', '130500', '临城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130523, 3, '130523', '130500', '内丘县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130524, 3, '130524', '130500', '柏乡县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130525, 3, '130525', '130500', '隆尧县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130528, 3, '130528', '130500', '宁晋县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130529, 3, '130529', '130500', '巨鹿县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130530, 3, '130530', '130500', '新河县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130531, 3, '130531', '130500', '广宗县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130532, 3, '130532', '130500', '平乡县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130533, 3, '130533', '130500', '威县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130534, 3, '130534', '130500', '清河县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130535, 3, '130535', '130500', '临西县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130581, 3, '130581', '130500', '南宫市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130582, 3, '130582', '130500', '沙河市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130600, 2, '130600', '130000', '保定市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130602, 3, '130602', '130600', '竞秀区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130606, 3, '130606', '130600', '莲池区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130607, 3, '130607', '130600', '满城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130608, 3, '130608', '130600', '清苑区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130609, 3, '130609', '130600', '徐水区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130623, 3, '130623', '130600', '涞水县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130624, 3, '130624', '130600', '阜平县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130626, 3, '130626', '130600', '定兴县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130627, 3, '130627', '130600', '唐县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130628, 3, '130628', '130600', '高阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130630, 3, '130630', '130600', '涞源县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130631, 3, '130631', '130600', '望都县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130633, 3, '130633', '130600', '易县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130634, 3, '130634', '130600', '曲阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130635, 3, '130635', '130600', '蠡县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130636, 3, '130636', '130600', '顺平县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130637, 3, '130637', '130600', '博野县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130681, 3, '130681', '130600', '涿州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130682, 3, '130682', '130600', '定州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130683, 3, '130683', '130600', '安国市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130684, 3, '130684', '130600', '高碑店市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130700, 2, '130700', '130000', '张家口市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130702, 3, '130702', '130700', '桥东区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130703, 3, '130703', '130700', '桥西区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130705, 3, '130705', '130700', '宣化区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130706, 3, '130706', '130700', '下花园区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130708, 3, '130708', '130700', '万全区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130709, 3, '130709', '130700', '崇礼区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130722, 3, '130722', '130700', '张北县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130723, 3, '130723', '130700', '康保县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130724, 3, '130724', '130700', '沽源县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130725, 3, '130725', '130700', '尚义县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130726, 3, '130726', '130700', '蔚县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130727, 3, '130727', '130700', '阳原县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130728, 3, '130728', '130700', '怀安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130730, 3, '130730', '130700', '怀来县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130731, 3, '130731', '130700', '涿鹿县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130732, 3, '130732', '130700', '赤城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130800, 2, '130800', '130000', '承德市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130802, 3, '130802', '130800', '双桥区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130803, 3, '130803', '130800', '双滦区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130804, 3, '130804', '130800', '鹰手营子矿区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130821, 3, '130821', '130800', '承德县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130822, 3, '130822', '130800', '兴隆县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130824, 3, '130824', '130800', '滦平县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130825, 3, '130825', '130800', '隆化县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130826, 3, '130826', '130800', '丰宁满族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130827, 3, '130827', '130800', '宽城满族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130828, 3, '130828', '130800', '围场满族蒙古族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130881, 3, '130881', '130800', '平泉市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130900, 2, '130900', '130000', '沧州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130902, 3, '130902', '130900', '新华区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130903, 3, '130903', '130900', '运河区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130921, 3, '130921', '130900', '沧县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130922, 3, '130922', '130900', '青县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130923, 3, '130923', '130900', '东光县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130924, 3, '130924', '130900', '海兴县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130925, 3, '130925', '130900', '盐山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130926, 3, '130926', '130900', '肃宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130927, 3, '130927', '130900', '南皮县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130928, 3, '130928', '130900', '吴桥县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130929, 3, '130929', '130900', '献县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130930, 3, '130930', '130900', '孟村回族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130981, 3, '130981', '130900', '泊头市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130982, 3, '130982', '130900', '任丘市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130983, 3, '130983', '130900', '黄骅市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (130984, 3, '130984', '130900', '河间市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (131000, 2, '131000', '130000', '廊坊市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (131002, 3, '131002', '131000', '安次区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (131003, 3, '131003', '131000', '广阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (131022, 3, '131022', '131000', '固安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (131023, 3, '131023', '131000', '永清县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (131024, 3, '131024', '131000', '香河县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (131025, 3, '131025', '131000', '大城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (131026, 3, '131026', '131000', '文安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (131028, 3, '131028', '131000', '大厂回族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (131081, 3, '131081', '131000', '霸州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (131082, 3, '131082', '131000', '三河市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (131100, 2, '131100', '130000', '衡水市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (131102, 3, '131102', '131100', '桃城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (131103, 3, '131103', '131100', '冀州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (131121, 3, '131121', '131100', '枣强县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (131122, 3, '131122', '131100', '武邑县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (131123, 3, '131123', '131100', '武强县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (131124, 3, '131124', '131100', '饶阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (131125, 3, '131125', '131100', '安平县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (131126, 3, '131126', '131100', '故城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (131127, 3, '131127', '131100', '景县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (131128, 3, '131128', '131100', '阜城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (131182, 3, '131182', '131100', '深州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (133100, 2, '133100', '130000', '雄安新区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (133101, 3, '133101', '133100', '容城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (133102, 3, '133102', '133100', '安新县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (133103, 3, '133103', '133100', '雄县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140000, 1, '140000', NULL, '山西省');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140100, 2, '140100', '140000', '太原市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140105, 3, '140105', '140100', '小店区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140106, 3, '140106', '140100', '迎泽区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140107, 3, '140107', '140100', '杏花岭区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140108, 3, '140108', '140100', '尖草坪区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140109, 3, '140109', '140100', '万柏林区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140110, 3, '140110', '140100', '晋源区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140121, 3, '140121', '140100', '清徐县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140122, 3, '140122', '140100', '阳曲县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140123, 3, '140123', '140100', '娄烦县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140181, 3, '140181', '140100', '古交市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140200, 2, '140200', '140000', '大同市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140212, 3, '140212', '140200', '新荣区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140213, 3, '140213', '140200', '平城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140214, 3, '140214', '140200', '云冈区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140215, 3, '140215', '140200', '云州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140221, 3, '140221', '140200', '阳高县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140222, 3, '140222', '140200', '天镇县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140223, 3, '140223', '140200', '广灵县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140224, 3, '140224', '140200', '灵丘县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140225, 3, '140225', '140200', '浑源县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140226, 3, '140226', '140200', '左云县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140300, 2, '140300', '140000', '阳泉市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140302, 3, '140302', '140300', '城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140303, 3, '140303', '140300', '矿区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140311, 3, '140311', '140300', '郊区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140321, 3, '140321', '140300', '平定县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140322, 3, '140322', '140300', '盂县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140400, 2, '140400', '140000', '长治市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140403, 3, '140403', '140400', '潞州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140404, 3, '140404', '140400', '上党区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140405, 3, '140405', '140400', '屯留区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140406, 3, '140406', '140400', '潞城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140423, 3, '140423', '140400', '襄垣县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140425, 3, '140425', '140400', '平顺县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140426, 3, '140426', '140400', '黎城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140427, 3, '140427', '140400', '壶关县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140428, 3, '140428', '140400', '长子县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140429, 3, '140429', '140400', '武乡县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140430, 3, '140430', '140400', '沁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140431, 3, '140431', '140400', '沁源县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140500, 2, '140500', '140000', '晋城市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140502, 3, '140502', '140500', '城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140521, 3, '140521', '140500', '沁水县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140522, 3, '140522', '140500', '阳城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140524, 3, '140524', '140500', '陵川县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140525, 3, '140525', '140500', '泽州县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140581, 3, '140581', '140500', '高平市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140600, 2, '140600', '140000', '朔州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140602, 3, '140602', '140600', '朔城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140603, 3, '140603', '140600', '平鲁区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140621, 3, '140621', '140600', '山阴县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140622, 3, '140622', '140600', '应县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140623, 3, '140623', '140600', '右玉县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140681, 3, '140681', '140600', '怀仁市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140700, 2, '140700', '140000', '晋中市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140702, 3, '140702', '140700', '榆次区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140703, 3, '140703', '140700', '太谷区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140721, 3, '140721', '140700', '榆社县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140722, 3, '140722', '140700', '左权县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140723, 3, '140723', '140700', '和顺县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140724, 3, '140724', '140700', '昔阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140725, 3, '140725', '140700', '寿阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140727, 3, '140727', '140700', '祁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140728, 3, '140728', '140700', '平遥县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140729, 3, '140729', '140700', '灵石县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140781, 3, '140781', '140700', '介休市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140800, 2, '140800', '140000', '运城市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140802, 3, '140802', '140800', '盐湖区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140821, 3, '140821', '140800', '临猗县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140822, 3, '140822', '140800', '万荣县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140823, 3, '140823', '140800', '闻喜县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140824, 3, '140824', '140800', '稷山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140825, 3, '140825', '140800', '新绛县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140826, 3, '140826', '140800', '绛县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140827, 3, '140827', '140800', '垣曲县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140828, 3, '140828', '140800', '夏县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140829, 3, '140829', '140800', '平陆县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140830, 3, '140830', '140800', '芮城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140881, 3, '140881', '140800', '永济市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140882, 3, '140882', '140800', '河津市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140900, 2, '140900', '140000', '忻州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140902, 3, '140902', '140900', '忻府区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140921, 3, '140921', '140900', '定襄县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140922, 3, '140922', '140900', '五台县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140923, 3, '140923', '140900', '代县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140924, 3, '140924', '140900', '繁峙县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140925, 3, '140925', '140900', '宁武县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140926, 3, '140926', '140900', '静乐县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140927, 3, '140927', '140900', '神池县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140928, 3, '140928', '140900', '五寨县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140929, 3, '140929', '140900', '岢岚县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140930, 3, '140930', '140900', '河曲县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140931, 3, '140931', '140900', '保德县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140932, 3, '140932', '140900', '偏关县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (140981, 3, '140981', '140900', '原平市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141000, 2, '141000', '140000', '临汾市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141002, 3, '141002', '141000', '尧都区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141021, 3, '141021', '141000', '曲沃县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141022, 3, '141022', '141000', '翼城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141023, 3, '141023', '141000', '襄汾县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141024, 3, '141024', '141000', '洪洞县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141025, 3, '141025', '141000', '古县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141026, 3, '141026', '141000', '安泽县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141027, 3, '141027', '141000', '浮山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141028, 3, '141028', '141000', '吉县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141029, 3, '141029', '141000', '乡宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141030, 3, '141030', '141000', '大宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141031, 3, '141031', '141000', '隰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141032, 3, '141032', '141000', '永和县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141033, 3, '141033', '141000', '蒲县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141034, 3, '141034', '141000', '汾西县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141081, 3, '141081', '141000', '侯马市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141082, 3, '141082', '141000', '霍州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141100, 2, '141100', '140000', '吕梁市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141102, 3, '141102', '141100', '离石区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141121, 3, '141121', '141100', '文水县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141122, 3, '141122', '141100', '交城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141123, 3, '141123', '141100', '兴县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141124, 3, '141124', '141100', '临县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141125, 3, '141125', '141100', '柳林县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141126, 3, '141126', '141100', '石楼县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141127, 3, '141127', '141100', '岚县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141128, 3, '141128', '141100', '方山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141129, 3, '141129', '141100', '中阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141130, 3, '141130', '141100', '交口县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141181, 3, '141181', '141100', '孝义市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (141182, 3, '141182', '141100', '汾阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150000, 1, '150000', NULL, '内蒙古自治区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150100, 2, '150100', '150000', '呼和浩特市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150102, 3, '150102', '150100', '新城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150103, 3, '150103', '150100', '回民区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150104, 3, '150104', '150100', '玉泉区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150105, 3, '150105', '150100', '赛罕区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150121, 3, '150121', '150100', '土默特左旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150122, 3, '150122', '150100', '托克托县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150123, 3, '150123', '150100', '和林格尔县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150124, 3, '150124', '150100', '清水河县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150125, 3, '150125', '150100', '武川县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150200, 2, '150200', '150000', '包头市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150202, 3, '150202', '150200', '东河区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150203, 3, '150203', '150200', '昆都仑区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150204, 3, '150204', '150200', '青山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150205, 3, '150205', '150200', '石拐区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150206, 3, '150206', '150200', '白云鄂博矿区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150207, 3, '150207', '150200', '九原区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150221, 3, '150221', '150200', '土默特右旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150222, 3, '150222', '150200', '固阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150223, 3, '150223', '150200', '达尔罕茂明安联合旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150300, 2, '150300', '150000', '乌海市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150302, 3, '150302', '150300', '海勃湾区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150303, 3, '150303', '150300', '海南区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150304, 3, '150304', '150300', '乌达区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150400, 2, '150400', '150000', '赤峰市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150402, 3, '150402', '150400', '红山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150403, 3, '150403', '150400', '元宝山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150404, 3, '150404', '150400', '松山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150421, 3, '150421', '150400', '阿鲁科尔沁旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150422, 3, '150422', '150400', '巴林左旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150423, 3, '150423', '150400', '巴林右旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150424, 3, '150424', '150400', '林西县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150425, 3, '150425', '150400', '克什克腾旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150426, 3, '150426', '150400', '翁牛特旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150428, 3, '150428', '150400', '喀喇沁旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150429, 3, '150429', '150400', '宁城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150430, 3, '150430', '150400', '敖汉旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150500, 2, '150500', '150000', '通辽市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150502, 3, '150502', '150500', '科尔沁区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150521, 3, '150521', '150500', '科尔沁左翼中旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150522, 3, '150522', '150500', '科尔沁左翼后旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150523, 3, '150523', '150500', '开鲁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150524, 3, '150524', '150500', '库伦旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150525, 3, '150525', '150500', '奈曼旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150526, 3, '150526', '150500', '扎鲁特旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150581, 3, '150581', '150500', '霍林郭勒市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150600, 2, '150600', '150000', '鄂尔多斯市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150602, 3, '150602', '150600', '东胜区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150603, 3, '150603', '150600', '康巴什区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150621, 3, '150621', '150600', '达拉特旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150622, 3, '150622', '150600', '准格尔旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150623, 3, '150623', '150600', '鄂托克前旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150624, 3, '150624', '150600', '鄂托克旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150625, 3, '150625', '150600', '杭锦旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150626, 3, '150626', '150600', '乌审旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150627, 3, '150627', '150600', '伊金霍洛旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150700, 2, '150700', '150000', '呼伦贝尔市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150702, 3, '150702', '150700', '海拉尔区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150703, 3, '150703', '150700', '扎赉诺尔区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150721, 3, '150721', '150700', '阿荣旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150722, 3, '150722', '150700', '莫力达瓦达斡尔族自治旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150723, 3, '150723', '150700', '鄂伦春自治旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150724, 3, '150724', '150700', '鄂温克族自治旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150725, 3, '150725', '150700', '陈巴尔虎旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150726, 3, '150726', '150700', '新巴尔虎左旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150727, 3, '150727', '150700', '新巴尔虎右旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150781, 3, '150781', '150700', '满洲里市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150782, 3, '150782', '150700', '牙克石市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150783, 3, '150783', '150700', '扎兰屯市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150784, 3, '150784', '150700', '额尔古纳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150785, 3, '150785', '150700', '根河市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150800, 2, '150800', '150000', '巴彦淖尔市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150802, 3, '150802', '150800', '临河区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150821, 3, '150821', '150800', '五原县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150822, 3, '150822', '150800', '磴口县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150823, 3, '150823', '150800', '乌拉特前旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150824, 3, '150824', '150800', '乌拉特中旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150825, 3, '150825', '150800', '乌拉特后旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150826, 3, '150826', '150800', '杭锦后旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150900, 2, '150900', '150000', '乌兰察布市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150902, 3, '150902', '150900', '集宁区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150921, 3, '150921', '150900', '卓资县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150922, 3, '150922', '150900', '化德县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150923, 3, '150923', '150900', '商都县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150924, 3, '150924', '150900', '兴和县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150925, 3, '150925', '150900', '凉城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150926, 3, '150926', '150900', '察哈尔右翼前旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150927, 3, '150927', '150900', '察哈尔右翼中旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150928, 3, '150928', '150900', '察哈尔右翼后旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150929, 3, '150929', '150900', '四子王旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (150981, 3, '150981', '150900', '丰镇市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (152200, 2, '152200', '150000', '兴安盟');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (152201, 3, '152201', '152200', '乌兰浩特市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (152202, 3, '152202', '152200', '阿尔山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (152221, 3, '152221', '152200', '科尔沁右翼前旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (152222, 3, '152222', '152200', '科尔沁右翼中旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (152223, 3, '152223', '152200', '扎赉特旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (152224, 3, '152224', '152200', '突泉县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (152500, 2, '152500', '150000', '锡林郭勒盟');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (152501, 3, '152501', '152500', '二连浩特市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (152502, 3, '152502', '152500', '锡林浩特市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (152522, 3, '152522', '152500', '阿巴嘎旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (152523, 3, '152523', '152500', '苏尼特左旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (152524, 3, '152524', '152500', '苏尼特右旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (152525, 3, '152525', '152500', '东乌珠穆沁旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (152526, 3, '152526', '152500', '西乌珠穆沁旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (152527, 3, '152527', '152500', '太仆寺旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (152528, 3, '152528', '152500', '镶黄旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (152529, 3, '152529', '152500', '正镶白旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (152530, 3, '152530', '152500', '正蓝旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (152531, 3, '152531', '152500', '多伦县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (152900, 2, '152900', '150000', '阿拉善盟');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (152921, 3, '152921', '152900', '阿拉善左旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (152922, 3, '152922', '152900', '阿拉善右旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (152923, 3, '152923', '152900', '额济纳旗');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210000, 1, '210000', NULL, '辽宁省');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210100, 2, '210100', '210000', '沈阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210102, 3, '210102', '210100', '和平区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210103, 3, '210103', '210100', '沈河区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210104, 3, '210104', '210100', '大东区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210105, 3, '210105', '210100', '皇姑区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210106, 3, '210106', '210100', '铁西区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210111, 3, '210111', '210100', '苏家屯区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210112, 3, '210112', '210100', '浑南区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210113, 3, '210113', '210100', '沈北新区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210114, 3, '210114', '210100', '于洪区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210115, 3, '210115', '210100', '辽中区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210123, 3, '210123', '210100', '康平县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210124, 3, '210124', '210100', '法库县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210181, 3, '210181', '210100', '新民市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210200, 2, '210200', '210000', '大连市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210202, 3, '210202', '210200', '中山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210203, 3, '210203', '210200', '西岗区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210204, 3, '210204', '210200', '沙河口区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210211, 3, '210211', '210200', '甘井子区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210212, 3, '210212', '210200', '旅顺口区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210213, 3, '210213', '210200', '金州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210214, 3, '210214', '210200', '普兰店区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210224, 3, '210224', '210200', '长海县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210281, 3, '210281', '210200', '瓦房店市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210283, 3, '210283', '210200', '庄河市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210300, 2, '210300', '210000', '鞍山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210302, 3, '210302', '210300', '铁东区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210303, 3, '210303', '210300', '铁西区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210304, 3, '210304', '210300', '立山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210311, 3, '210311', '210300', '千山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210321, 3, '210321', '210300', '台安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210323, 3, '210323', '210300', '岫岩满族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210381, 3, '210381', '210300', '海城市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210400, 2, '210400', '210000', '抚顺市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210402, 3, '210402', '210400', '新抚区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210403, 3, '210403', '210400', '东洲区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210404, 3, '210404', '210400', '望花区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210411, 3, '210411', '210400', '顺城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210421, 3, '210421', '210400', '抚顺县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210422, 3, '210422', '210400', '新宾满族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210423, 3, '210423', '210400', '清原满族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210500, 2, '210500', '210000', '本溪市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210502, 3, '210502', '210500', '平山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210503, 3, '210503', '210500', '溪湖区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210504, 3, '210504', '210500', '明山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210505, 3, '210505', '210500', '南芬区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210521, 3, '210521', '210500', '本溪满族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210522, 3, '210522', '210500', '桓仁满族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210600, 2, '210600', '210000', '丹东市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210602, 3, '210602', '210600', '元宝区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210603, 3, '210603', '210600', '振兴区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210604, 3, '210604', '210600', '振安区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210624, 3, '210624', '210600', '宽甸满族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210681, 3, '210681', '210600', '东港市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210682, 3, '210682', '210600', '凤城市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210700, 2, '210700', '210000', '锦州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210702, 3, '210702', '210700', '古塔区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210703, 3, '210703', '210700', '凌河区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210711, 3, '210711', '210700', '太和区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210726, 3, '210726', '210700', '黑山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210727, 3, '210727', '210700', '义县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210781, 3, '210781', '210700', '凌海市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210782, 3, '210782', '210700', '北镇市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210800, 2, '210800', '210000', '营口市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210802, 3, '210802', '210800', '站前区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210803, 3, '210803', '210800', '西市区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210804, 3, '210804', '210800', '鲅鱼圈区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210811, 3, '210811', '210800', '老边区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210881, 3, '210881', '210800', '盖州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210882, 3, '210882', '210800', '大石桥市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210900, 2, '210900', '210000', '阜新市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210902, 3, '210902', '210900', '海州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210903, 3, '210903', '210900', '新邱区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210904, 3, '210904', '210900', '太平区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210905, 3, '210905', '210900', '清河门区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210911, 3, '210911', '210900', '细河区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210921, 3, '210921', '210900', '阜新蒙古族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (210922, 3, '210922', '210900', '彰武县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211000, 2, '211000', '210000', '辽阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211002, 3, '211002', '211000', '白塔区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211003, 3, '211003', '211000', '文圣区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211004, 3, '211004', '211000', '宏伟区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211005, 3, '211005', '211000', '弓长岭区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211011, 3, '211011', '211000', '太子河区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211021, 3, '211021', '211000', '辽阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211081, 3, '211081', '211000', '灯塔市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211100, 2, '211100', '210000', '盘锦市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211102, 3, '211102', '211100', '双台子区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211103, 3, '211103', '211100', '兴隆台区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211104, 3, '211104', '211100', '大洼区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211122, 3, '211122', '211100', '盘山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211200, 2, '211200', '210000', '铁岭市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211202, 3, '211202', '211200', '银州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211204, 3, '211204', '211200', '清河区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211221, 3, '211221', '211200', '铁岭县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211223, 3, '211223', '211200', '西丰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211224, 3, '211224', '211200', '昌图县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211281, 3, '211281', '211200', '调兵山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211282, 3, '211282', '211200', '开原市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211300, 2, '211300', '210000', '朝阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211302, 3, '211302', '211300', '双塔区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211303, 3, '211303', '211300', '龙城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211321, 3, '211321', '211300', '朝阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211322, 3, '211322', '211300', '建平县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211324, 3, '211324', '211300', '喀喇沁左翼蒙古族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211381, 3, '211381', '211300', '北票市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211382, 3, '211382', '211300', '凌源市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211400, 2, '211400', '210000', '葫芦岛市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211402, 3, '211402', '211400', '连山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211403, 3, '211403', '211400', '龙港区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211404, 3, '211404', '211400', '南票区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211421, 3, '211421', '211400', '绥中县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211422, 3, '211422', '211400', '建昌县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (211481, 3, '211481', '211400', '兴城市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220100, 2, '220100', '220000', '长春市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220102, 3, '220102', '220100', '南关区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220103, 3, '220103', '220100', '宽城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220104, 3, '220104', '220100', '朝阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220105, 3, '220105', '220100', '二道区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220106, 3, '220106', '220100', '绿园区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220112, 3, '220112', '220100', '双阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220113, 3, '220113', '220100', '九台区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220122, 3, '220122', '220100', '农安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220182, 3, '220182', '220100', '榆树市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220183, 3, '220183', '220100', '德惠市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220184, 3, '220184', '220100', '公主岭市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220200, 2, '220200', '220000', '吉林市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220202, 3, '220202', '220200', '昌邑区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220203, 3, '220203', '220200', '龙潭区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220204, 3, '220204', '220200', '船营区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220211, 3, '220211', '220200', '丰满区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220221, 3, '220221', '220200', '永吉县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220281, 3, '220281', '220200', '蛟河市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220282, 3, '220282', '220200', '桦甸市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220283, 3, '220283', '220200', '舒兰市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220284, 3, '220284', '220200', '磐石市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220300, 2, '220300', '220000', '四平市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220302, 3, '220302', '220300', '铁西区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220303, 3, '220303', '220300', '铁东区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220322, 3, '220322', '220300', '梨树县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220323, 3, '220323', '220300', '伊通满族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220382, 3, '220382', '220300', '双辽市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220400, 2, '220400', '220000', '辽源市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220402, 3, '220402', '220400', '龙山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220403, 3, '220403', '220400', '西安区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220421, 3, '220421', '220400', '东丰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220422, 3, '220422', '220400', '东辽县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220500, 2, '220500', '220000', '通化市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220502, 3, '220502', '220500', '东昌区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220503, 3, '220503', '220500', '二道江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220521, 3, '220521', '220500', '通化县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220523, 3, '220523', '220500', '辉南县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220524, 3, '220524', '220500', '柳河县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220581, 3, '220581', '220500', '梅河口市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220582, 3, '220582', '220500', '集安市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220600, 2, '220600', '220000', '白山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220602, 3, '220602', '220600', '浑江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220605, 3, '220605', '220600', '江源区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220621, 3, '220621', '220600', '抚松县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220622, 3, '220622', '220600', '靖宇县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220623, 3, '220623', '220600', '长白朝鲜族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220681, 3, '220681', '220600', '临江市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220700, 2, '220700', '220000', '松原市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220702, 3, '220702', '220700', '宁江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220721, 3, '220721', '220700', '前郭尔罗斯蒙古族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220722, 3, '220722', '220700', '长岭县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220723, 3, '220723', '220700', '乾安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220781, 3, '220781', '220700', '扶余市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220800, 2, '220800', '220000', '白城市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220802, 3, '220802', '220800', '洮北区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220821, 3, '220821', '220800', '镇赉县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220822, 3, '220822', '220800', '通榆县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220881, 3, '220881', '220800', '洮南市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (220882, 3, '220882', '220800', '大安市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (222400, 2, '222400', '220000', '延边朝鲜族自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (222401, 3, '222401', '222400', '延吉市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (222402, 3, '222402', '222400', '图们市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (222403, 3, '222403', '222400', '敦化市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (222404, 3, '222404', '222400', '珲春市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (222405, 3, '222405', '222400', '龙井市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (222406, 3, '222406', '222400', '和龙市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (222424, 3, '222424', '222400', '汪清县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (222426, 3, '222426', '222400', '安图县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230000, 1, '230000', NULL, '黑龙江省');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230100, 2, '230100', '230000', '哈尔滨市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230102, 3, '230102', '230100', '道里区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230103, 3, '230103', '230100', '南岗区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230104, 3, '230104', '230100', '道外区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230108, 3, '230108', '230100', '平房区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230109, 3, '230109', '230100', '松北区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230110, 3, '230110', '230100', '香坊区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230111, 3, '230111', '230100', '呼兰区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230112, 3, '230112', '230100', '阿城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230113, 3, '230113', '230100', '双城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230123, 3, '230123', '230100', '依兰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230124, 3, '230124', '230100', '方正县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230125, 3, '230125', '230100', '宾县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230126, 3, '230126', '230100', '巴彦县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230127, 3, '230127', '230100', '木兰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230128, 3, '230128', '230100', '通河县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230129, 3, '230129', '230100', '延寿县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230183, 3, '230183', '230100', '尚志市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230184, 3, '230184', '230100', '五常市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230200, 2, '230200', '230000', '齐齐哈尔市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230202, 3, '230202', '230200', '龙沙区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230203, 3, '230203', '230200', '建华区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230204, 3, '230204', '230200', '铁锋区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230205, 3, '230205', '230200', '昂昂溪区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230206, 3, '230206', '230200', '富拉尔基区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230207, 3, '230207', '230200', '碾子山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230208, 3, '230208', '230200', '梅里斯达斡尔族区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230221, 3, '230221', '230200', '龙江县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230223, 3, '230223', '230200', '依安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230224, 3, '230224', '230200', '泰来县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230225, 3, '230225', '230200', '甘南县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230227, 3, '230227', '230200', '富裕县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230229, 3, '230229', '230200', '克山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230230, 3, '230230', '230200', '克东县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230231, 3, '230231', '230200', '拜泉县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230281, 3, '230281', '230200', '讷河市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230300, 2, '230300', '230000', '鸡西市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230302, 3, '230302', '230300', '鸡冠区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230303, 3, '230303', '230300', '恒山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230304, 3, '230304', '230300', '滴道区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230305, 3, '230305', '230300', '梨树区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230306, 3, '230306', '230300', '城子河区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230307, 3, '230307', '230300', '麻山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230321, 3, '230321', '230300', '鸡东县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230381, 3, '230381', '230300', '虎林市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230382, 3, '230382', '230300', '密山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230400, 2, '230400', '230000', '鹤岗市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230402, 3, '230402', '230400', '向阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230403, 3, '230403', '230400', '工农区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230404, 3, '230404', '230400', '南山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230405, 3, '230405', '230400', '兴安区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230406, 3, '230406', '230400', '东山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230407, 3, '230407', '230400', '兴山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230421, 3, '230421', '230400', '萝北县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230422, 3, '230422', '230400', '绥滨县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230500, 2, '230500', '230000', '双鸭山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230502, 3, '230502', '230500', '尖山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230503, 3, '230503', '230500', '岭东区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230505, 3, '230505', '230500', '四方台区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230506, 3, '230506', '230500', '宝山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230521, 3, '230521', '230500', '集贤县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230522, 3, '230522', '230500', '友谊县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230523, 3, '230523', '230500', '宝清县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230524, 3, '230524', '230500', '饶河县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230600, 2, '230600', '230000', '大庆市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230602, 3, '230602', '230600', '萨尔图区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230603, 3, '230603', '230600', '龙凤区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230604, 3, '230604', '230600', '让胡路区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230605, 3, '230605', '230600', '红岗区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230606, 3, '230606', '230600', '大同区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230621, 3, '230621', '230600', '肇州县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230622, 3, '230622', '230600', '肇源县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230623, 3, '230623', '230600', '林甸县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230624, 3, '230624', '230600', '杜尔伯特蒙古族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230700, 2, '230700', '230000', '伊春市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230717, 3, '230717', '230700', '伊美区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230718, 3, '230718', '230700', '乌翠区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230719, 3, '230719', '230700', '友好区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230722, 3, '230722', '230700', '嘉荫县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230723, 3, '230723', '230700', '汤旺县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230724, 3, '230724', '230700', '丰林县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230725, 3, '230725', '230700', '大箐山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230726, 3, '230726', '230700', '南岔县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230751, 3, '230751', '230700', '金林区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230781, 3, '230781', '230700', '铁力市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230800, 2, '230800', '230000', '佳木斯市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230803, 3, '230803', '230800', '向阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230804, 3, '230804', '230800', '前进区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230805, 3, '230805', '230800', '东风区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230811, 3, '230811', '230800', '郊区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230822, 3, '230822', '230800', '桦南县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230826, 3, '230826', '230800', '桦川县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230828, 3, '230828', '230800', '汤原县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230881, 3, '230881', '230800', '同江市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230882, 3, '230882', '230800', '富锦市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230883, 3, '230883', '230800', '抚远市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230900, 2, '230900', '230000', '七台河市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230902, 3, '230902', '230900', '新兴区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230903, 3, '230903', '230900', '桃山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230904, 3, '230904', '230900', '茄子河区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (230921, 3, '230921', '230900', '勃利县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231000, 2, '231000', '230000', '牡丹江市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231002, 3, '231002', '231000', '东安区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231003, 3, '231003', '231000', '阳明区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231004, 3, '231004', '231000', '爱民区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231005, 3, '231005', '231000', '西安区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231025, 3, '231025', '231000', '林口县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231081, 3, '231081', '231000', '绥芬河市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231083, 3, '231083', '231000', '海林市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231084, 3, '231084', '231000', '宁安市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231085, 3, '231085', '231000', '穆棱市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231086, 3, '231086', '231000', '东宁市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231100, 2, '231100', '230000', '黑河市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231102, 3, '231102', '231100', '爱辉区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231123, 3, '231123', '231100', '逊克县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231124, 3, '231124', '231100', '孙吴县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231181, 3, '231181', '231100', '北安市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231182, 3, '231182', '231100', '五大连池市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231183, 3, '231183', '231100', '嫩江市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231200, 2, '231200', '230000', '绥化市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231202, 3, '231202', '231200', '北林区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231221, 3, '231221', '231200', '望奎县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231222, 3, '231222', '231200', '兰西县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231223, 3, '231223', '231200', '青冈县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231224, 3, '231224', '231200', '庆安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231225, 3, '231225', '231200', '明水县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231226, 3, '231226', '231200', '绥棱县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231281, 3, '231281', '231200', '安达市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231282, 3, '231282', '231200', '肇东市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (231283, 3, '231283', '231200', '海伦市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (232700, 2, '232700', '230000', '大兴安岭地区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (232701, 3, '232701', '232700', '漠河市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (232721, 3, '232721', '232700', '呼玛县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (232722, 3, '232722', '232700', '塔河县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (232761, 3, '232761', '232700', '加格达奇区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (310000, 1, '310000', NULL, '上海');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320000, 1, '320000', NULL, '江苏省');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320100, 2, '320100', '320000', '南京市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320102, 3, '320102', '320100', '玄武区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320104, 3, '320104', '320100', '秦淮区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320105, 3, '320105', '320100', '建邺区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320106, 3, '320106', '320100', '鼓楼区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320111, 3, '320111', '320100', '浦口区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320113, 3, '320113', '320100', '栖霞区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320114, 3, '320114', '320100', '雨花台区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320115, 3, '320115', '320100', '江宁区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320116, 3, '320116', '320100', '六合区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320117, 3, '320117', '320100', '溧水区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320118, 3, '320118', '320100', '高淳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320200, 2, '320200', '320000', '无锡市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320205, 3, '320205', '320200', '锡山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320206, 3, '320206', '320200', '惠山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320211, 3, '320211', '320200', '滨湖区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320213, 3, '320213', '320200', '梁溪区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320214, 3, '320214', '320200', '新吴区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320281, 3, '320281', '320200', '江阴市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320282, 3, '320282', '320200', '宜兴市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320300, 2, '320300', '320000', '徐州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320302, 3, '320302', '320300', '鼓楼区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320303, 3, '320303', '320300', '云龙区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320305, 3, '320305', '320300', '贾汪区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320311, 3, '320311', '320300', '泉山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320312, 3, '320312', '320300', '铜山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320321, 3, '320321', '320300', '丰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320322, 3, '320322', '320300', '沛县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320324, 3, '320324', '320300', '睢宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320381, 3, '320381', '320300', '新沂市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320382, 3, '320382', '320300', '邳州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320400, 2, '320400', '320000', '常州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320402, 3, '320402', '320400', '天宁区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320404, 3, '320404', '320400', '钟楼区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320411, 3, '320411', '320400', '新北区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320412, 3, '320412', '320400', '武进区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320413, 3, '320413', '320400', '金坛区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320481, 3, '320481', '320400', '溧阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320500, 2, '320500', '320000', '苏州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320505, 3, '320505', '320500', '虎丘区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320506, 3, '320506', '320500', '吴中区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320507, 3, '320507', '320500', '相城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320508, 3, '320508', '320500', '姑苏区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320509, 3, '320509', '320500', '吴江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320581, 3, '320581', '320500', '常熟市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320582, 3, '320582', '320500', '张家港市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320583, 3, '320583', '320500', '昆山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320585, 3, '320585', '320500', '太仓市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320600, 2, '320600', '320000', '南通市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320612, 3, '320612', '320600', '通州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320613, 3, '320613', '320600', '崇川区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320614, 3, '320614', '320600', '海门区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320623, 3, '320623', '320600', '如东县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320681, 3, '320681', '320600', '启东市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320682, 3, '320682', '320600', '如皋市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320685, 3, '320685', '320600', '海安市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320700, 2, '320700', '320000', '连云港市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320703, 3, '320703', '320700', '连云区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320706, 3, '320706', '320700', '海州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320707, 3, '320707', '320700', '赣榆区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320722, 3, '320722', '320700', '东海县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320723, 3, '320723', '320700', '灌云县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320724, 3, '320724', '320700', '灌南县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320800, 2, '320800', '320000', '淮安市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320803, 3, '320803', '320800', '淮安区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320804, 3, '320804', '320800', '淮阴区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320812, 3, '320812', '320800', '清江浦区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320813, 3, '320813', '320800', '洪泽区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320826, 3, '320826', '320800', '涟水县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320830, 3, '320830', '320800', '盱眙县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320831, 3, '320831', '320800', '金湖县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320900, 2, '320900', '320000', '盐城市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320902, 3, '320902', '320900', '亭湖区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320903, 3, '320903', '320900', '盐都区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320904, 3, '320904', '320900', '大丰区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320921, 3, '320921', '320900', '响水县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320922, 3, '320922', '320900', '滨海县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320923, 3, '320923', '320900', '阜宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320924, 3, '320924', '320900', '射阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320925, 3, '320925', '320900', '建湖县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (320981, 3, '320981', '320900', '东台市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (321000, 2, '321000', '320000', '扬州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (321002, 3, '321002', '321000', '广陵区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (321003, 3, '321003', '321000', '邗江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (321012, 3, '321012', '321000', '江都区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (321023, 3, '321023', '321000', '宝应县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (321081, 3, '321081', '321000', '仪征市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (321084, 3, '321084', '321000', '高邮市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (321100, 2, '321100', '320000', '镇江市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (321102, 3, '321102', '321100', '京口区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (321111, 3, '321111', '321100', '润州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (321112, 3, '321112', '321100', '丹徒区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (321181, 3, '321181', '321100', '丹阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (321182, 3, '321182', '321100', '扬中市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (321183, 3, '321183', '321100', '句容市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (321200, 2, '321200', '320000', '泰州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (321202, 3, '321202', '321200', '海陵区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (321203, 3, '321203', '321200', '高港区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (321204, 3, '321204', '321200', '姜堰区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (321281, 3, '321281', '321200', '兴化市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (321282, 3, '321282', '321200', '靖江市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (321283, 3, '321283', '321200', '泰兴市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (321300, 2, '321300', '320000', '宿迁市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (321302, 3, '321302', '321300', '宿城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (321311, 3, '321311', '321300', '宿豫区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (321322, 3, '321322', '321300', '沭阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (321323, 3, '321323', '321300', '泗阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (321324, 3, '321324', '321300', '泗洪县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330000, 1, '330000', NULL, '浙江省');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330100, 2, '330100', '330000', '杭州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330102, 3, '330102', '330100', '上城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330105, 3, '330105', '330100', '拱墅区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330106, 3, '330106', '330100', '西湖区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330108, 3, '330108', '330100', '滨江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330109, 3, '330109', '330100', '萧山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330110, 3, '330110', '330100', '余杭区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330111, 3, '330111', '330100', '富阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330112, 3, '330112', '330100', '临安区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330113, 3, '330113', '330100', '临平区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330114, 3, '330114', '330100', '钱塘区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330122, 3, '330122', '330100', '桐庐县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330127, 3, '330127', '330100', '淳安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330182, 3, '330182', '330100', '建德市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330200, 2, '330200', '330000', '宁波市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330203, 3, '330203', '330200', '海曙区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330205, 3, '330205', '330200', '江北区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330206, 3, '330206', '330200', '北仑区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330211, 3, '330211', '330200', '镇海区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330212, 3, '330212', '330200', '鄞州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330213, 3, '330213', '330200', '奉化区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330225, 3, '330225', '330200', '象山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330226, 3, '330226', '330200', '宁海县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330281, 3, '330281', '330200', '余姚市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330282, 3, '330282', '330200', '慈溪市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330300, 2, '330300', '330000', '温州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330302, 3, '330302', '330300', '鹿城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330303, 3, '330303', '330300', '龙湾区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330304, 3, '330304', '330300', '瓯海区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330305, 3, '330305', '330300', '洞头区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330324, 3, '330324', '330300', '永嘉县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330326, 3, '330326', '330300', '平阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330327, 3, '330327', '330300', '苍南县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330328, 3, '330328', '330300', '文成县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330329, 3, '330329', '330300', '泰顺县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330381, 3, '330381', '330300', '瑞安市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330382, 3, '330382', '330300', '乐清市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330383, 3, '330383', '330300', '龙港市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330400, 2, '330400', '330000', '嘉兴市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330402, 3, '330402', '330400', '南湖区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330411, 3, '330411', '330400', '秀洲区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330421, 3, '330421', '330400', '嘉善县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330424, 3, '330424', '330400', '海盐县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330481, 3, '330481', '330400', '海宁市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330482, 3, '330482', '330400', '平湖市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330483, 3, '330483', '330400', '桐乡市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330500, 2, '330500', '330000', '湖州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330502, 3, '330502', '330500', '吴兴区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330503, 3, '330503', '330500', '南浔区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330521, 3, '330521', '330500', '德清县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330522, 3, '330522', '330500', '长兴县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330523, 3, '330523', '330500', '安吉县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330600, 2, '330600', '330000', '绍兴市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330602, 3, '330602', '330600', '越城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330603, 3, '330603', '330600', '柯桥区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330604, 3, '330604', '330600', '上虞区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330624, 3, '330624', '330600', '新昌县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330681, 3, '330681', '330600', '诸暨市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330683, 3, '330683', '330600', '嵊州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330700, 2, '330700', '330000', '金华市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330702, 3, '330702', '330700', '婺城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330703, 3, '330703', '330700', '金东区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330723, 3, '330723', '330700', '武义县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330726, 3, '330726', '330700', '浦江县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330727, 3, '330727', '330700', '磐安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330781, 3, '330781', '330700', '兰溪市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330782, 3, '330782', '330700', '义乌市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330783, 3, '330783', '330700', '东阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330784, 3, '330784', '330700', '永康市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330800, 2, '330800', '330000', '衢州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330802, 3, '330802', '330800', '柯城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330803, 3, '330803', '330800', '衢江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330822, 3, '330822', '330800', '常山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330824, 3, '330824', '330800', '开化县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330825, 3, '330825', '330800', '龙游县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330881, 3, '330881', '330800', '江山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330900, 2, '330900', '330000', '舟山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330902, 3, '330902', '330900', '定海区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330903, 3, '330903', '330900', '普陀区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330921, 3, '330921', '330900', '岱山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (330922, 3, '330922', '330900', '嵊泗县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (331000, 2, '331000', '330000', '台州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (331002, 3, '331002', '331000', '椒江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (331003, 3, '331003', '331000', '黄岩区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (331004, 3, '331004', '331000', '路桥区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (331022, 3, '331022', '331000', '三门县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (331023, 3, '331023', '331000', '天台县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (331024, 3, '331024', '331000', '仙居县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (331081, 3, '331081', '331000', '温岭市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (331082, 3, '331082', '331000', '临海市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (331083, 3, '331083', '331000', '玉环市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (331100, 2, '331100', '330000', '丽水市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (331102, 3, '331102', '331100', '莲都区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (331121, 3, '331121', '331100', '青田县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (331122, 3, '331122', '331100', '缙云县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (331123, 3, '331123', '331100', '遂昌县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (331124, 3, '331124', '331100', '松阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (331125, 3, '331125', '331100', '云和县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (331126, 3, '331126', '331100', '庆元县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (331127, 3, '331127', '331100', '景宁畲族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (331181, 3, '331181', '331100', '龙泉市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340000, 1, '340000', NULL, '安徽省');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340100, 2, '340100', '340000', '合肥市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340102, 3, '340102', '340100', '瑶海区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340103, 3, '340103', '340100', '庐阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340104, 3, '340104', '340100', '蜀山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340111, 3, '340111', '340100', '包河区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340121, 3, '340121', '340100', '长丰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340122, 3, '340122', '340100', '肥东县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340123, 3, '340123', '340100', '肥西县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340124, 3, '340124', '340100', '庐江县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340181, 3, '340181', '340100', '巢湖市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340200, 2, '340200', '340000', '芜湖市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340202, 3, '340202', '340200', '镜湖区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340207, 3, '340207', '340200', '鸠江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340209, 3, '340209', '340200', '弋江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340210, 3, '340210', '340200', '湾沚区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340212, 3, '340212', '340200', '繁昌区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340223, 3, '340223', '340200', '南陵县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340281, 3, '340281', '340200', '无为市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340300, 2, '340300', '340000', '蚌埠市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340302, 3, '340302', '340300', '龙子湖区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340303, 3, '340303', '340300', '蚌山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340304, 3, '340304', '340300', '禹会区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340311, 3, '340311', '340300', '淮上区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340321, 3, '340321', '340300', '怀远县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340322, 3, '340322', '340300', '五河县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340323, 3, '340323', '340300', '固镇县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340400, 2, '340400', '340000', '淮南市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340402, 3, '340402', '340400', '大通区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340403, 3, '340403', '340400', '田家庵区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340404, 3, '340404', '340400', '谢家集区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340405, 3, '340405', '340400', '八公山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340406, 3, '340406', '340400', '潘集区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340421, 3, '340421', '340400', '凤台县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340422, 3, '340422', '340400', '寿县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340500, 2, '340500', '340000', '马鞍山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340503, 3, '340503', '340500', '花山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340504, 3, '340504', '340500', '雨山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340506, 3, '340506', '340500', '博望区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340521, 3, '340521', '340500', '当涂县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340522, 3, '340522', '340500', '含山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340523, 3, '340523', '340500', '和县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340600, 2, '340600', '340000', '淮北市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340602, 3, '340602', '340600', '杜集区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340603, 3, '340603', '340600', '相山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340604, 3, '340604', '340600', '烈山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340621, 3, '340621', '340600', '濉溪县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340700, 2, '340700', '340000', '铜陵市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340705, 3, '340705', '340700', '铜官区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340706, 3, '340706', '340700', '义安区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340711, 3, '340711', '340700', '郊区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340722, 3, '340722', '340700', '枞阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340800, 2, '340800', '340000', '安庆市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340802, 3, '340802', '340800', '迎江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340803, 3, '340803', '340800', '大观区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340811, 3, '340811', '340800', '宜秀区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340822, 3, '340822', '340800', '怀宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340825, 3, '340825', '340800', '太湖县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340826, 3, '340826', '340800', '宿松县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340827, 3, '340827', '340800', '望江县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340828, 3, '340828', '340800', '岳西县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340881, 3, '340881', '340800', '桐城市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (340882, 3, '340882', '340800', '潜山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341000, 2, '341000', '340000', '黄山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341002, 3, '341002', '341000', '屯溪区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341003, 3, '341003', '341000', '黄山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341004, 3, '341004', '341000', '徽州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341021, 3, '341021', '341000', '歙县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341022, 3, '341022', '341000', '休宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341023, 3, '341023', '341000', '黟县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341024, 3, '341024', '341000', '祁门县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341100, 2, '341100', '340000', '滁州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341102, 3, '341102', '341100', '琅琊区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341103, 3, '341103', '341100', '南谯区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341122, 3, '341122', '341100', '来安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341124, 3, '341124', '341100', '全椒县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341125, 3, '341125', '341100', '定远县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341126, 3, '341126', '341100', '凤阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341181, 3, '341181', '341100', '天长市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341182, 3, '341182', '341100', '明光市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341200, 2, '341200', '340000', '阜阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341202, 3, '341202', '341200', '颍州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341203, 3, '341203', '341200', '颍东区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341204, 3, '341204', '341200', '颍泉区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341221, 3, '341221', '341200', '临泉县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341222, 3, '341222', '341200', '太和县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341225, 3, '341225', '341200', '阜南县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341226, 3, '341226', '341200', '颍上县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341282, 3, '341282', '341200', '界首市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341300, 2, '341300', '340000', '宿州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341302, 3, '341302', '341300', '埇桥区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341321, 3, '341321', '341300', '砀山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341322, 3, '341322', '341300', '萧县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341323, 3, '341323', '341300', '灵璧县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341324, 3, '341324', '341300', '泗县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341500, 2, '341500', '340000', '六安市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341502, 3, '341502', '341500', '金安区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341503, 3, '341503', '341500', '裕安区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341504, 3, '341504', '341500', '叶集区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341522, 3, '341522', '341500', '霍邱县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341523, 3, '341523', '341500', '舒城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341524, 3, '341524', '341500', '金寨县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341525, 3, '341525', '341500', '霍山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341600, 2, '341600', '340000', '亳州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341602, 3, '341602', '341600', '谯城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341621, 3, '341621', '341600', '涡阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341622, 3, '341622', '341600', '蒙城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341623, 3, '341623', '341600', '利辛县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341700, 2, '341700', '340000', '池州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341702, 3, '341702', '341700', '贵池区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341721, 3, '341721', '341700', '东至县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341722, 3, '341722', '341700', '石台县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341723, 3, '341723', '341700', '青阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341800, 2, '341800', '340000', '宣城市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341802, 3, '341802', '341800', '宣州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341821, 3, '341821', '341800', '郎溪县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341823, 3, '341823', '341800', '泾县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341824, 3, '341824', '341800', '绩溪县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341825, 3, '341825', '341800', '旌德县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341881, 3, '341881', '341800', '宁国市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (341882, 3, '341882', '341800', '广德市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350000, 1, '350000', NULL, '福建省');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350100, 2, '350100', '350000', '福州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350102, 3, '350102', '350100', '鼓楼区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350103, 3, '350103', '350100', '台江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350104, 3, '350104', '350100', '仓山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350105, 3, '350105', '350100', '马尾区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350111, 3, '350111', '350100', '晋安区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350112, 3, '350112', '350100', '长乐区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350121, 3, '350121', '350100', '闽侯县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350122, 3, '350122', '350100', '连江县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350123, 3, '350123', '350100', '罗源县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350124, 3, '350124', '350100', '闽清县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350125, 3, '350125', '350100', '永泰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350128, 3, '350128', '350100', '平潭县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350181, 3, '350181', '350100', '福清市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350200, 2, '350200', '350000', '厦门市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350203, 3, '350203', '350200', '思明区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350205, 3, '350205', '350200', '海沧区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350206, 3, '350206', '350200', '湖里区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350211, 3, '350211', '350200', '集美区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350212, 3, '350212', '350200', '同安区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350213, 3, '350213', '350200', '翔安区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350300, 2, '350300', '350000', '莆田市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350302, 3, '350302', '350300', '城厢区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350303, 3, '350303', '350300', '涵江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350304, 3, '350304', '350300', '荔城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350305, 3, '350305', '350300', '秀屿区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350322, 3, '350322', '350300', '仙游县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350400, 2, '350400', '350000', '三明市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350404, 3, '350404', '350400', '三元区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350405, 3, '350405', '350400', '沙县区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350421, 3, '350421', '350400', '明溪县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350423, 3, '350423', '350400', '清流县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350424, 3, '350424', '350400', '宁化县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350425, 3, '350425', '350400', '大田县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350426, 3, '350426', '350400', '尤溪县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350428, 3, '350428', '350400', '将乐县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350429, 3, '350429', '350400', '泰宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350430, 3, '350430', '350400', '建宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350481, 3, '350481', '350400', '永安市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350500, 2, '350500', '350000', '泉州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350502, 3, '350502', '350500', '鲤城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350503, 3, '350503', '350500', '丰泽区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350504, 3, '350504', '350500', '洛江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350505, 3, '350505', '350500', '泉港区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350521, 3, '350521', '350500', '惠安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350524, 3, '350524', '350500', '安溪县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350525, 3, '350525', '350500', '永春县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350526, 3, '350526', '350500', '德化县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350527, 3, '350527', '350500', '金门县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350581, 3, '350581', '350500', '石狮市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350582, 3, '350582', '350500', '晋江市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350583, 3, '350583', '350500', '南安市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350600, 2, '350600', '350000', '漳州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350602, 3, '350602', '350600', '芗城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350603, 3, '350603', '350600', '龙文区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350604, 3, '350604', '350600', '龙海区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350605, 3, '350605', '350600', '长泰区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350622, 3, '350622', '350600', '云霄县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350623, 3, '350623', '350600', '漳浦县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350624, 3, '350624', '350600', '诏安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350626, 3, '350626', '350600', '东山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350627, 3, '350627', '350600', '南靖县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350628, 3, '350628', '350600', '平和县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350629, 3, '350629', '350600', '华安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350700, 2, '350700', '350000', '南平市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350702, 3, '350702', '350700', '延平区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350703, 3, '350703', '350700', '建阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350721, 3, '350721', '350700', '顺昌县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350722, 3, '350722', '350700', '浦城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350723, 3, '350723', '350700', '光泽县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350724, 3, '350724', '350700', '松溪县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350725, 3, '350725', '350700', '政和县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350781, 3, '350781', '350700', '邵武市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350782, 3, '350782', '350700', '武夷山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350783, 3, '350783', '350700', '建瓯市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350800, 2, '350800', '350000', '龙岩市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350802, 3, '350802', '350800', '新罗区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350803, 3, '350803', '350800', '永定区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350821, 3, '350821', '350800', '长汀县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350823, 3, '350823', '350800', '上杭县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350824, 3, '350824', '350800', '武平县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350825, 3, '350825', '350800', '连城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350881, 3, '350881', '350800', '漳平市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350900, 2, '350900', '350000', '宁德市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350902, 3, '350902', '350900', '蕉城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350921, 3, '350921', '350900', '霞浦县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350922, 3, '350922', '350900', '古田县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350923, 3, '350923', '350900', '屏南县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350924, 3, '350924', '350900', '寿宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350925, 3, '350925', '350900', '周宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350926, 3, '350926', '350900', '柘荣县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350981, 3, '350981', '350900', '福安市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (350982, 3, '350982', '350900', '福鼎市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360000, 1, '360000', NULL, '江西省');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360100, 2, '360100', '360000', '南昌市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360102, 3, '360102', '360100', '东湖区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360103, 3, '360103', '360100', '西湖区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360104, 3, '360104', '360100', '青云谱区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360111, 3, '360111', '360100', '青山湖区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360112, 3, '360112', '360100', '新建区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360113, 3, '360113', '360100', '红谷滩区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360121, 3, '360121', '360100', '南昌县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360123, 3, '360123', '360100', '安义县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360124, 3, '360124', '360100', '进贤县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360200, 2, '360200', '360000', '景德镇市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360202, 3, '360202', '360200', '昌江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360203, 3, '360203', '360200', '珠山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360222, 3, '360222', '360200', '浮梁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360281, 3, '360281', '360200', '乐平市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360300, 2, '360300', '360000', '萍乡市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360302, 3, '360302', '360300', '安源区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360313, 3, '360313', '360300', '湘东区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360321, 3, '360321', '360300', '莲花县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360322, 3, '360322', '360300', '上栗县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360323, 3, '360323', '360300', '芦溪县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360400, 2, '360400', '360000', '九江市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360402, 3, '360402', '360400', '濂溪区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360403, 3, '360403', '360400', '浔阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360404, 3, '360404', '360400', '柴桑区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360423, 3, '360423', '360400', '武宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360424, 3, '360424', '360400', '修水县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360425, 3, '360425', '360400', '永修县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360426, 3, '360426', '360400', '德安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360428, 3, '360428', '360400', '都昌县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360429, 3, '360429', '360400', '湖口县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360430, 3, '360430', '360400', '彭泽县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360481, 3, '360481', '360400', '瑞昌市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360482, 3, '360482', '360400', '共青城市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360483, 3, '360483', '360400', '庐山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360500, 2, '360500', '360000', '新余市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360502, 3, '360502', '360500', '渝水区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360521, 3, '360521', '360500', '分宜县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360600, 2, '360600', '360000', '鹰潭市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360602, 3, '360602', '360600', '月湖区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360603, 3, '360603', '360600', '余江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360681, 3, '360681', '360600', '贵溪市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360700, 2, '360700', '360000', '赣州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360702, 3, '360702', '360700', '章贡区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360703, 3, '360703', '360700', '南康区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360704, 3, '360704', '360700', '赣县区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360722, 3, '360722', '360700', '信丰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360723, 3, '360723', '360700', '大余县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360724, 3, '360724', '360700', '上犹县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360725, 3, '360725', '360700', '崇义县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360726, 3, '360726', '360700', '安远县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360728, 3, '360728', '360700', '定南县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360729, 3, '360729', '360700', '全南县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360730, 3, '360730', '360700', '宁都县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360731, 3, '360731', '360700', '于都县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360732, 3, '360732', '360700', '兴国县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360733, 3, '360733', '360700', '会昌县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360734, 3, '360734', '360700', '寻乌县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360735, 3, '360735', '360700', '石城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360781, 3, '360781', '360700', '瑞金市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360783, 3, '360783', '360700', '龙南市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360800, 2, '360800', '360000', '吉安市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360802, 3, '360802', '360800', '吉州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360803, 3, '360803', '360800', '青原区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360821, 3, '360821', '360800', '吉安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360822, 3, '360822', '360800', '吉水县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360823, 3, '360823', '360800', '峡江县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360824, 3, '360824', '360800', '新干县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360825, 3, '360825', '360800', '永丰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360826, 3, '360826', '360800', '泰和县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360827, 3, '360827', '360800', '遂川县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360828, 3, '360828', '360800', '万安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360829, 3, '360829', '360800', '安福县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360830, 3, '360830', '360800', '永新县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360881, 3, '360881', '360800', '井冈山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360900, 2, '360900', '360000', '宜春市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360902, 3, '360902', '360900', '袁州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360921, 3, '360921', '360900', '奉新县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360922, 3, '360922', '360900', '万载县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360923, 3, '360923', '360900', '上高县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360924, 3, '360924', '360900', '宜丰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360925, 3, '360925', '360900', '靖安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360926, 3, '360926', '360900', '铜鼓县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360981, 3, '360981', '360900', '丰城市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360982, 3, '360982', '360900', '樟树市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (360983, 3, '360983', '360900', '高安市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (361000, 2, '361000', '360000', '抚州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (361002, 3, '361002', '361000', '临川区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (361003, 3, '361003', '361000', '东乡区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (361021, 3, '361021', '361000', '南城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (361022, 3, '361022', '361000', '黎川县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (361023, 3, '361023', '361000', '南丰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (361024, 3, '361024', '361000', '崇仁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (361025, 3, '361025', '361000', '乐安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (361026, 3, '361026', '361000', '宜黄县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (361027, 3, '361027', '361000', '金溪县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (361028, 3, '361028', '361000', '资溪县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (361030, 3, '361030', '361000', '广昌县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (361100, 2, '361100', '360000', '上饶市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (361102, 3, '361102', '361100', '信州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (361103, 3, '361103', '361100', '广丰区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (361104, 3, '361104', '361100', '广信区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (361123, 3, '361123', '361100', '玉山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (361124, 3, '361124', '361100', '铅山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (361125, 3, '361125', '361100', '横峰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (361126, 3, '361126', '361100', '弋阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (361127, 3, '361127', '361100', '余干县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (361128, 3, '361128', '361100', '鄱阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (361129, 3, '361129', '361100', '万年县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (361130, 3, '361130', '361100', '婺源县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (361181, 3, '361181', '361100', '德兴市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370000, 1, '370000', NULL, '山东省');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370100, 2, '370100', '370000', '济南市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370102, 3, '370102', '370100', '历下区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370103, 3, '370103', '370100', '市中区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370104, 3, '370104', '370100', '槐荫区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370105, 3, '370105', '370100', '天桥区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370112, 3, '370112', '370100', '历城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370113, 3, '370113', '370100', '长清区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370114, 3, '370114', '370100', '章丘区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370115, 3, '370115', '370100', '济阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370116, 3, '370116', '370100', '莱芜区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370117, 3, '370117', '370100', '钢城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370124, 3, '370124', '370100', '平阴县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370126, 3, '370126', '370100', '商河县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370200, 2, '370200', '370000', '青岛市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370202, 3, '370202', '370200', '市南区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370203, 3, '370203', '370200', '市北区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370211, 3, '370211', '370200', '黄岛区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370212, 3, '370212', '370200', '崂山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370213, 3, '370213', '370200', '李沧区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370214, 3, '370214', '370200', '城阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370215, 3, '370215', '370200', '即墨区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370281, 3, '370281', '370200', '胶州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370283, 3, '370283', '370200', '平度市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370285, 3, '370285', '370200', '莱西市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370300, 2, '370300', '370000', '淄博市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370302, 3, '370302', '370300', '淄川区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370303, 3, '370303', '370300', '张店区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370304, 3, '370304', '370300', '博山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370305, 3, '370305', '370300', '临淄区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370306, 3, '370306', '370300', '周村区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370321, 3, '370321', '370300', '桓台县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370322, 3, '370322', '370300', '高青县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370323, 3, '370323', '370300', '沂源县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370400, 2, '370400', '370000', '枣庄市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370402, 3, '370402', '370400', '市中区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370403, 3, '370403', '370400', '薛城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370404, 3, '370404', '370400', '峄城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370405, 3, '370405', '370400', '台儿庄区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370406, 3, '370406', '370400', '山亭区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370481, 3, '370481', '370400', '滕州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370500, 2, '370500', '370000', '东营市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370502, 3, '370502', '370500', '东营区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370503, 3, '370503', '370500', '河口区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370505, 3, '370505', '370500', '垦利区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370522, 3, '370522', '370500', '利津县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370523, 3, '370523', '370500', '广饶县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370600, 2, '370600', '370000', '烟台市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370602, 3, '370602', '370600', '芝罘区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370611, 3, '370611', '370600', '福山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370612, 3, '370612', '370600', '牟平区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370613, 3, '370613', '370600', '莱山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370614, 3, '370614', '370600', '蓬莱区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370681, 3, '370681', '370600', '龙口市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370682, 3, '370682', '370600', '莱阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370683, 3, '370683', '370600', '莱州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370685, 3, '370685', '370600', '招远市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370686, 3, '370686', '370600', '栖霞市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370687, 3, '370687', '370600', '海阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370700, 2, '370700', '370000', '潍坊市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370702, 3, '370702', '370700', '潍城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370703, 3, '370703', '370700', '寒亭区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370704, 3, '370704', '370700', '坊子区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370705, 3, '370705', '370700', '奎文区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370724, 3, '370724', '370700', '临朐县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370725, 3, '370725', '370700', '昌乐县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370781, 3, '370781', '370700', '青州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370782, 3, '370782', '370700', '诸城市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370783, 3, '370783', '370700', '寿光市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370784, 3, '370784', '370700', '安丘市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370785, 3, '370785', '370700', '高密市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370786, 3, '370786', '370700', '昌邑市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370800, 2, '370800', '370000', '济宁市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370811, 3, '370811', '370800', '任城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370812, 3, '370812', '370800', '兖州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370826, 3, '370826', '370800', '微山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370827, 3, '370827', '370800', '鱼台县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370828, 3, '370828', '370800', '金乡县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370829, 3, '370829', '370800', '嘉祥县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370830, 3, '370830', '370800', '汶上县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370831, 3, '370831', '370800', '泗水县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370832, 3, '370832', '370800', '梁山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370881, 3, '370881', '370800', '曲阜市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370883, 3, '370883', '370800', '邹城市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370900, 2, '370900', '370000', '泰安市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370902, 3, '370902', '370900', '泰山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370911, 3, '370911', '370900', '岱岳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370921, 3, '370921', '370900', '宁阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370923, 3, '370923', '370900', '东平县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370982, 3, '370982', '370900', '新泰市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (370983, 3, '370983', '370900', '肥城市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371000, 2, '371000', '370000', '威海市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371002, 3, '371002', '371000', '环翠区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371003, 3, '371003', '371000', '文登区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371082, 3, '371082', '371000', '荣成市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371083, 3, '371083', '371000', '乳山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371100, 2, '371100', '370000', '日照市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371102, 3, '371102', '371100', '东港区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371103, 3, '371103', '371100', '岚山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371121, 3, '371121', '371100', '五莲县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371122, 3, '371122', '371100', '莒县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371300, 2, '371300', '370000', '临沂市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371302, 3, '371302', '371300', '兰山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371311, 3, '371311', '371300', '罗庄区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371312, 3, '371312', '371300', '河东区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371321, 3, '371321', '371300', '沂南县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371322, 3, '371322', '371300', '郯城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371323, 3, '371323', '371300', '沂水县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371324, 3, '371324', '371300', '兰陵县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371325, 3, '371325', '371300', '费县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371326, 3, '371326', '371300', '平邑县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371327, 3, '371327', '371300', '莒南县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371328, 3, '371328', '371300', '蒙阴县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371329, 3, '371329', '371300', '临沭县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371400, 2, '371400', '370000', '德州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371402, 3, '371402', '371400', '德城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371403, 3, '371403', '371400', '陵城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371422, 3, '371422', '371400', '宁津县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371423, 3, '371423', '371400', '庆云县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371424, 3, '371424', '371400', '临邑县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371425, 3, '371425', '371400', '齐河县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371426, 3, '371426', '371400', '平原县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371427, 3, '371427', '371400', '夏津县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371428, 3, '371428', '371400', '武城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371481, 3, '371481', '371400', '乐陵市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371482, 3, '371482', '371400', '禹城市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371500, 2, '371500', '370000', '聊城市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371502, 3, '371502', '371500', '东昌府区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371503, 3, '371503', '371500', '茌平区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371521, 3, '371521', '371500', '阳谷县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371522, 3, '371522', '371500', '莘县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371524, 3, '371524', '371500', '东阿县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371525, 3, '371525', '371500', '冠县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371526, 3, '371526', '371500', '高唐县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371581, 3, '371581', '371500', '临清市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371600, 2, '371600', '370000', '滨州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371602, 3, '371602', '371600', '滨城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371603, 3, '371603', '371600', '沾化区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371621, 3, '371621', '371600', '惠民县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371622, 3, '371622', '371600', '阳信县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371623, 3, '371623', '371600', '无棣县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371625, 3, '371625', '371600', '博兴县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371681, 3, '371681', '371600', '邹平市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371700, 2, '371700', '370000', '菏泽市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371702, 3, '371702', '371700', '牡丹区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371703, 3, '371703', '371700', '定陶区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371721, 3, '371721', '371700', '曹县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371722, 3, '371722', '371700', '单县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371723, 3, '371723', '371700', '成武县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371724, 3, '371724', '371700', '巨野县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371725, 3, '371725', '371700', '郓城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371726, 3, '371726', '371700', '鄄城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (371728, 3, '371728', '371700', '东明县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410100, 2, '410100', '410000', '郑州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410102, 3, '410102', '410100', '中原区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410103, 3, '410103', '410100', '二七区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410104, 3, '410104', '410100', '管城回族区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410105, 3, '410105', '410100', '金水区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410106, 3, '410106', '410100', '上街区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410108, 3, '410108', '410100', '惠济区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410122, 3, '410122', '410100', '中牟县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410181, 3, '410181', '410100', '巩义市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410182, 3, '410182', '410100', '荥阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410183, 3, '410183', '410100', '新密市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410184, 3, '410184', '410100', '新郑市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410185, 3, '410185', '410100', '登封市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410200, 2, '410200', '410000', '开封市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410202, 3, '410202', '410200', '龙亭区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410203, 3, '410203', '410200', '顺河回族区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410204, 3, '410204', '410200', '鼓楼区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410205, 3, '410205', '410200', '禹王台区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410212, 3, '410212', '410200', '祥符区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410221, 3, '410221', '410200', '杞县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410222, 3, '410222', '410200', '通许县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410223, 3, '410223', '410200', '尉氏县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410225, 3, '410225', '410200', '兰考县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410300, 2, '410300', '410000', '洛阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410302, 3, '410302', '410300', '老城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410303, 3, '410303', '410300', '西工区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410304, 3, '410304', '410300', '瀍河回族区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410305, 3, '410305', '410300', '涧西区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410307, 3, '410307', '410300', '偃师区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410308, 3, '410308', '410300', '孟津区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410311, 3, '410311', '410300', '洛龙区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410323, 3, '410323', '410300', '新安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410324, 3, '410324', '410300', '栾川县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410325, 3, '410325', '410300', '嵩县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410326, 3, '410326', '410300', '汝阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410327, 3, '410327', '410300', '宜阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410328, 3, '410328', '410300', '洛宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410329, 3, '410329', '410300', '伊川县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410400, 2, '410400', '410000', '平顶山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410402, 3, '410402', '410400', '新华区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410403, 3, '410403', '410400', '卫东区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410404, 3, '410404', '410400', '石龙区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410411, 3, '410411', '410400', '湛河区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410421, 3, '410421', '410400', '宝丰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410422, 3, '410422', '410400', '叶县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410423, 3, '410423', '410400', '鲁山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410425, 3, '410425', '410400', '郏县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410481, 3, '410481', '410400', '舞钢市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410482, 3, '410482', '410400', '汝州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410500, 2, '410500', '410000', '安阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410502, 3, '410502', '410500', '文峰区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410503, 3, '410503', '410500', '北关区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410505, 3, '410505', '410500', '殷都区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410506, 3, '410506', '410500', '龙安区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410522, 3, '410522', '410500', '安阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410523, 3, '410523', '410500', '汤阴县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410526, 3, '410526', '410500', '滑县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410527, 3, '410527', '410500', '内黄县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410581, 3, '410581', '410500', '林州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410600, 2, '410600', '410000', '鹤壁市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410602, 3, '410602', '410600', '鹤山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410603, 3, '410603', '410600', '山城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410611, 3, '410611', '410600', '淇滨区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410621, 3, '410621', '410600', '浚县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410622, 3, '410622', '410600', '淇县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410700, 2, '410700', '410000', '新乡市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410702, 3, '410702', '410700', '红旗区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410703, 3, '410703', '410700', '卫滨区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410704, 3, '410704', '410700', '凤泉区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410711, 3, '410711', '410700', '牧野区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410721, 3, '410721', '410700', '新乡县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410724, 3, '410724', '410700', '获嘉县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410725, 3, '410725', '410700', '原阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410726, 3, '410726', '410700', '延津县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410727, 3, '410727', '410700', '封丘县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410781, 3, '410781', '410700', '卫辉市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410782, 3, '410782', '410700', '辉县市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410783, 3, '410783', '410700', '长垣市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410800, 2, '410800', '410000', '焦作市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410802, 3, '410802', '410800', '解放区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410803, 3, '410803', '410800', '中站区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410804, 3, '410804', '410800', '马村区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410811, 3, '410811', '410800', '山阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410821, 3, '410821', '410800', '修武县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410822, 3, '410822', '410800', '博爱县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410823, 3, '410823', '410800', '武陟县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410825, 3, '410825', '410800', '温县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410882, 3, '410882', '410800', '沁阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410883, 3, '410883', '410800', '孟州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410900, 2, '410900', '410000', '濮阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410902, 3, '410902', '410900', '华龙区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410922, 3, '410922', '410900', '清丰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410923, 3, '410923', '410900', '南乐县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410926, 3, '410926', '410900', '范县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410927, 3, '410927', '410900', '台前县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (410928, 3, '410928', '410900', '濮阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411000, 2, '411000', '410000', '许昌市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411002, 3, '411002', '411000', '魏都区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411003, 3, '411003', '411000', '建安区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411024, 3, '411024', '411000', '鄢陵县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411025, 3, '411025', '411000', '襄城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411081, 3, '411081', '411000', '禹州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411082, 3, '411082', '411000', '长葛市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411100, 2, '411100', '410000', '漯河市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411102, 3, '411102', '411100', '源汇区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411103, 3, '411103', '411100', '郾城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411104, 3, '411104', '411100', '召陵区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411121, 3, '411121', '411100', '舞阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411122, 3, '411122', '411100', '临颍县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411200, 2, '411200', '410000', '三门峡市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411202, 3, '411202', '411200', '湖滨区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411203, 3, '411203', '411200', '陕州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411221, 3, '411221', '411200', '渑池县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411224, 3, '411224', '411200', '卢氏县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411281, 3, '411281', '411200', '义马市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411282, 3, '411282', '411200', '灵宝市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411300, 2, '411300', '410000', '南阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411302, 3, '411302', '411300', '宛城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411303, 3, '411303', '411300', '卧龙区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411321, 3, '411321', '411300', '南召县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411322, 3, '411322', '411300', '方城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411323, 3, '411323', '411300', '西峡县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411324, 3, '411324', '411300', '镇平县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411325, 3, '411325', '411300', '内乡县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411326, 3, '411326', '411300', '淅川县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411327, 3, '411327', '411300', '社旗县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411328, 3, '411328', '411300', '唐河县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411329, 3, '411329', '411300', '新野县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411330, 3, '411330', '411300', '桐柏县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411381, 3, '411381', '411300', '邓州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411400, 2, '411400', '410000', '商丘市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411402, 3, '411402', '411400', '梁园区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411403, 3, '411403', '411400', '睢阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411421, 3, '411421', '411400', '民权县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411422, 3, '411422', '411400', '睢县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411423, 3, '411423', '411400', '宁陵县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411424, 3, '411424', '411400', '柘城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411425, 3, '411425', '411400', '虞城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411426, 3, '411426', '411400', '夏邑县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411481, 3, '411481', '411400', '永城市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411500, 2, '411500', '410000', '信阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411502, 3, '411502', '411500', '浉河区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411503, 3, '411503', '411500', '平桥区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411521, 3, '411521', '411500', '罗山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411522, 3, '411522', '411500', '光山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411523, 3, '411523', '411500', '新县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411524, 3, '411524', '411500', '商城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411525, 3, '411525', '411500', '固始县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411526, 3, '411526', '411500', '潢川县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411527, 3, '411527', '411500', '淮滨县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411528, 3, '411528', '411500', '息县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411600, 2, '411600', '410000', '周口市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411602, 3, '411602', '411600', '川汇区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411603, 3, '411603', '411600', '淮阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411621, 3, '411621', '411600', '扶沟县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411622, 3, '411622', '411600', '西华县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411623, 3, '411623', '411600', '商水县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411624, 3, '411624', '411600', '沈丘县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411625, 3, '411625', '411600', '郸城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411627, 3, '411627', '411600', '太康县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411628, 3, '411628', '411600', '鹿邑县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411681, 3, '411681', '411600', '项城市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411700, 2, '411700', '410000', '驻马店市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411702, 3, '411702', '411700', '驿城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411721, 3, '411721', '411700', '西平县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411722, 3, '411722', '411700', '上蔡县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411723, 3, '411723', '411700', '平舆县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411724, 3, '411724', '411700', '正阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411725, 3, '411725', '411700', '确山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411726, 3, '411726', '411700', '泌阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411727, 3, '411727', '411700', '汝南县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411728, 3, '411728', '411700', '遂平县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (411729, 3, '411729', '411700', '新蔡县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (419001, 2, '419001', '410000', '济源市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420000, 1, '420000', NULL, '湖北省');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420100, 2, '420100', '420000', '武汉市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420102, 3, '420102', '420100', '江岸区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420103, 3, '420103', '420100', '江汉区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420104, 3, '420104', '420100', '硚口区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420105, 3, '420105', '420100', '汉阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420106, 3, '420106', '420100', '武昌区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420107, 3, '420107', '420100', '青山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420111, 3, '420111', '420100', '洪山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420112, 3, '420112', '420100', '东西湖区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420113, 3, '420113', '420100', '汉南区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420114, 3, '420114', '420100', '蔡甸区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420115, 3, '420115', '420100', '江夏区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420116, 3, '420116', '420100', '黄陂区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420117, 3, '420117', '420100', '新洲区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420200, 2, '420200', '420000', '黄石市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420202, 3, '420202', '420200', '黄石港区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420203, 3, '420203', '420200', '西塞山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420204, 3, '420204', '420200', '下陆区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420205, 3, '420205', '420200', '铁山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420222, 3, '420222', '420200', '阳新县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420281, 3, '420281', '420200', '大冶市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420300, 2, '420300', '420000', '十堰市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420302, 3, '420302', '420300', '茅箭区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420303, 3, '420303', '420300', '张湾区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420304, 3, '420304', '420300', '郧阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420322, 3, '420322', '420300', '郧西县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420323, 3, '420323', '420300', '竹山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420324, 3, '420324', '420300', '竹溪县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420325, 3, '420325', '420300', '房县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420381, 3, '420381', '420300', '丹江口市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420500, 2, '420500', '420000', '宜昌市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420502, 3, '420502', '420500', '西陵区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420503, 3, '420503', '420500', '伍家岗区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420504, 3, '420504', '420500', '点军区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420505, 3, '420505', '420500', '猇亭区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420506, 3, '420506', '420500', '夷陵区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420525, 3, '420525', '420500', '远安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420526, 3, '420526', '420500', '兴山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420527, 3, '420527', '420500', '秭归县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420528, 3, '420528', '420500', '长阳土家族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420529, 3, '420529', '420500', '五峰土家族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420581, 3, '420581', '420500', '宜都市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420582, 3, '420582', '420500', '当阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420583, 3, '420583', '420500', '枝江市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420600, 2, '420600', '420000', '襄阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420602, 3, '420602', '420600', '襄城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420606, 3, '420606', '420600', '樊城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420607, 3, '420607', '420600', '襄州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420624, 3, '420624', '420600', '南漳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420625, 3, '420625', '420600', '谷城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420626, 3, '420626', '420600', '保康县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420682, 3, '420682', '420600', '老河口市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420683, 3, '420683', '420600', '枣阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420684, 3, '420684', '420600', '宜城市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420700, 2, '420700', '420000', '鄂州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420702, 3, '420702', '420700', '梁子湖区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420703, 3, '420703', '420700', '华容区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420704, 3, '420704', '420700', '鄂城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420800, 2, '420800', '420000', '荆门市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420802, 3, '420802', '420800', '东宝区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420804, 3, '420804', '420800', '掇刀区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420822, 3, '420822', '420800', '沙洋县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420881, 3, '420881', '420800', '钟祥市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420882, 3, '420882', '420800', '京山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420900, 2, '420900', '420000', '孝感市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420902, 3, '420902', '420900', '孝南区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420921, 3, '420921', '420900', '孝昌县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420922, 3, '420922', '420900', '大悟县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420923, 3, '420923', '420900', '云梦县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420981, 3, '420981', '420900', '应城市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420982, 3, '420982', '420900', '安陆市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (420984, 3, '420984', '420900', '汉川市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421000, 2, '421000', '420000', '荆州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421002, 3, '421002', '421000', '沙市区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421003, 3, '421003', '421000', '荆州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421022, 3, '421022', '421000', '公安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421024, 3, '421024', '421000', '江陵县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421081, 3, '421081', '421000', '石首市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421083, 3, '421083', '421000', '洪湖市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421087, 3, '421087', '421000', '松滋市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421088, 3, '421088', '421000', '监利市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421100, 2, '421100', '420000', '黄冈市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421102, 3, '421102', '421100', '黄州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421121, 3, '421121', '421100', '团风县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421122, 3, '421122', '421100', '红安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421123, 3, '421123', '421100', '罗田县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421124, 3, '421124', '421100', '英山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421125, 3, '421125', '421100', '浠水县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421126, 3, '421126', '421100', '蕲春县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421127, 3, '421127', '421100', '黄梅县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421181, 3, '421181', '421100', '麻城市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421182, 3, '421182', '421100', '武穴市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421200, 2, '421200', '420000', '咸宁市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421202, 3, '421202', '421200', '咸安区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421221, 3, '421221', '421200', '嘉鱼县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421222, 3, '421222', '421200', '通城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421223, 3, '421223', '421200', '崇阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421224, 3, '421224', '421200', '通山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421281, 3, '421281', '421200', '赤壁市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421300, 2, '421300', '420000', '随州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421303, 3, '421303', '421300', '曾都区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421321, 3, '421321', '421300', '随县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (421381, 3, '421381', '421300', '广水市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (422800, 2, '422800', '420000', '恩施土家族苗族自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (422801, 3, '422801', '422800', '恩施市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (422802, 3, '422802', '422800', '利川市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (422822, 3, '422822', '422800', '建始县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (422823, 3, '422823', '422800', '巴东县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (422825, 3, '422825', '422800', '宣恩县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (422826, 3, '422826', '422800', '咸丰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (422827, 3, '422827', '422800', '来凤县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (422828, 3, '422828', '422800', '鹤峰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (429004, 2, '429004', '420000', '仙桃市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (429005, 2, '429005', '420000', '潜江市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (429006, 2, '429006', '420000', '天门市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (429021, 2, '429021', '420000', '神农架林区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430000, 1, '430000', NULL, '湖南省');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430100, 2, '430100', '430000', '长沙市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430102, 3, '430102', '430100', '芙蓉区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430103, 3, '430103', '430100', '天心区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430104, 3, '430104', '430100', '岳麓区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430105, 3, '430105', '430100', '开福区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430111, 3, '430111', '430100', '雨花区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430112, 3, '430112', '430100', '望城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430121, 3, '430121', '430100', '长沙县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430181, 3, '430181', '430100', '浏阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430182, 3, '430182', '430100', '宁乡市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430200, 2, '430200', '430000', '株洲市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430202, 3, '430202', '430200', '荷塘区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430203, 3, '430203', '430200', '芦淞区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430204, 3, '430204', '430200', '石峰区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430211, 3, '430211', '430200', '天元区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430212, 3, '430212', '430200', '渌口区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430223, 3, '430223', '430200', '攸县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430224, 3, '430224', '430200', '茶陵县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430225, 3, '430225', '430200', '炎陵县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430281, 3, '430281', '430200', '醴陵市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430300, 2, '430300', '430000', '湘潭市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430302, 3, '430302', '430300', '雨湖区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430304, 3, '430304', '430300', '岳塘区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430321, 3, '430321', '430300', '湘潭县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430381, 3, '430381', '430300', '湘乡市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430382, 3, '430382', '430300', '韶山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430400, 2, '430400', '430000', '衡阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430405, 3, '430405', '430400', '珠晖区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430406, 3, '430406', '430400', '雁峰区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430407, 3, '430407', '430400', '石鼓区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430408, 3, '430408', '430400', '蒸湘区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430412, 3, '430412', '430400', '南岳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430421, 3, '430421', '430400', '衡阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430422, 3, '430422', '430400', '衡南县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430423, 3, '430423', '430400', '衡山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430424, 3, '430424', '430400', '衡东县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430426, 3, '430426', '430400', '祁东县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430481, 3, '430481', '430400', '耒阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430482, 3, '430482', '430400', '常宁市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430500, 2, '430500', '430000', '邵阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430502, 3, '430502', '430500', '双清区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430503, 3, '430503', '430500', '大祥区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430511, 3, '430511', '430500', '北塔区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430522, 3, '430522', '430500', '新邵县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430523, 3, '430523', '430500', '邵阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430524, 3, '430524', '430500', '隆回县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430525, 3, '430525', '430500', '洞口县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430527, 3, '430527', '430500', '绥宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430528, 3, '430528', '430500', '新宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430529, 3, '430529', '430500', '城步苗族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430581, 3, '430581', '430500', '武冈市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430582, 3, '430582', '430500', '邵东市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430600, 2, '430600', '430000', '岳阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430602, 3, '430602', '430600', '岳阳楼区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430603, 3, '430603', '430600', '云溪区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430611, 3, '430611', '430600', '君山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430621, 3, '430621', '430600', '岳阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430623, 3, '430623', '430600', '华容县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430624, 3, '430624', '430600', '湘阴县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430626, 3, '430626', '430600', '平江县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430681, 3, '430681', '430600', '汨罗市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430682, 3, '430682', '430600', '临湘市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430700, 2, '430700', '430000', '常德市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430702, 3, '430702', '430700', '武陵区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430703, 3, '430703', '430700', '鼎城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430721, 3, '430721', '430700', '安乡县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430722, 3, '430722', '430700', '汉寿县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430723, 3, '430723', '430700', '澧县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430724, 3, '430724', '430700', '临澧县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430725, 3, '430725', '430700', '桃源县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430726, 3, '430726', '430700', '石门县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430781, 3, '430781', '430700', '津市市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430800, 2, '430800', '430000', '张家界市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430802, 3, '430802', '430800', '永定区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430811, 3, '430811', '430800', '武陵源区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430821, 3, '430821', '430800', '慈利县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430822, 3, '430822', '430800', '桑植县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430900, 2, '430900', '430000', '益阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430902, 3, '430902', '430900', '资阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430903, 3, '430903', '430900', '赫山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430921, 3, '430921', '430900', '南县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430922, 3, '430922', '430900', '桃江县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430923, 3, '430923', '430900', '安化县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (430981, 3, '430981', '430900', '沅江市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431000, 2, '431000', '430000', '郴州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431002, 3, '431002', '431000', '北湖区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431003, 3, '431003', '431000', '苏仙区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431021, 3, '431021', '431000', '桂阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431022, 3, '431022', '431000', '宜章县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431023, 3, '431023', '431000', '永兴县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431024, 3, '431024', '431000', '嘉禾县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431025, 3, '431025', '431000', '临武县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431026, 3, '431026', '431000', '汝城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431027, 3, '431027', '431000', '桂东县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431028, 3, '431028', '431000', '安仁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431081, 3, '431081', '431000', '资兴市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431100, 2, '431100', '430000', '永州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431102, 3, '431102', '431100', '零陵区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431103, 3, '431103', '431100', '冷水滩区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431122, 3, '431122', '431100', '东安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431123, 3, '431123', '431100', '双牌县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431124, 3, '431124', '431100', '道县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431125, 3, '431125', '431100', '江永县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431126, 3, '431126', '431100', '宁远县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431127, 3, '431127', '431100', '蓝山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431128, 3, '431128', '431100', '新田县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431129, 3, '431129', '431100', '江华瑶族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431181, 3, '431181', '431100', '祁阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431200, 2, '431200', '430000', '怀化市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431202, 3, '431202', '431200', '鹤城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431221, 3, '431221', '431200', '中方县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431222, 3, '431222', '431200', '沅陵县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431223, 3, '431223', '431200', '辰溪县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431224, 3, '431224', '431200', '溆浦县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431225, 3, '431225', '431200', '会同县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431226, 3, '431226', '431200', '麻阳苗族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431227, 3, '431227', '431200', '新晃侗族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431228, 3, '431228', '431200', '芷江侗族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431229, 3, '431229', '431200', '靖州苗族侗族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431230, 3, '431230', '431200', '通道侗族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431281, 3, '431281', '431200', '洪江市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431300, 2, '431300', '430000', '娄底市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431302, 3, '431302', '431300', '娄星区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431321, 3, '431321', '431300', '双峰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431322, 3, '431322', '431300', '新化县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431381, 3, '431381', '431300', '冷水江市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (431382, 3, '431382', '431300', '涟源市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (433100, 2, '433100', '430000', '湘西土家族苗族自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (433101, 3, '433101', '433100', '吉首市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (433122, 3, '433122', '433100', '泸溪县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (433123, 3, '433123', '433100', '凤凰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (433124, 3, '433124', '433100', '花垣县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (433125, 3, '433125', '433100', '保靖县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (433126, 3, '433126', '433100', '古丈县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (433127, 3, '433127', '433100', '永顺县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (433130, 3, '433130', '433100', '龙山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440000, 1, '440000', NULL, '广东省');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440100, 2, '440100', '440000', '广州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440103, 3, '440103', '440100', '荔湾区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440104, 3, '440104', '440100', '越秀区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440105, 3, '440105', '440100', '海珠区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440106, 3, '440106', '440100', '天河区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440111, 3, '440111', '440100', '白云区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440112, 3, '440112', '440100', '黄埔区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440113, 3, '440113', '440100', '番禺区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440114, 3, '440114', '440100', '花都区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440115, 3, '440115', '440100', '南沙区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440117, 3, '440117', '440100', '从化区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440118, 3, '440118', '440100', '增城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440200, 2, '440200', '440000', '韶关市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440203, 3, '440203', '440200', '武江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440204, 3, '440204', '440200', '浈江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440205, 3, '440205', '440200', '曲江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440222, 3, '440222', '440200', '始兴县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440224, 3, '440224', '440200', '仁化县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440229, 3, '440229', '440200', '翁源县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440232, 3, '440232', '440200', '乳源瑶族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440233, 3, '440233', '440200', '新丰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440281, 3, '440281', '440200', '乐昌市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440282, 3, '440282', '440200', '南雄市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440300, 2, '440300', '440000', '深圳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440303, 3, '440303', '440300', '罗湖区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440304, 3, '440304', '440300', '福田区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440305, 3, '440305', '440300', '南山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440306, 3, '440306', '440300', '宝安区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440307, 3, '440307', '440300', '龙岗区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440308, 3, '440308', '440300', '盐田区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440309, 3, '440309', '440300', '龙华区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440310, 3, '440310', '440300', '坪山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440311, 3, '440311', '440300', '光明区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440400, 2, '440400', '440000', '珠海市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440402, 3, '440402', '440400', '香洲区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440403, 3, '440403', '440400', '斗门区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440404, 3, '440404', '440400', '金湾区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440500, 2, '440500', '440000', '汕头市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440507, 3, '440507', '440500', '龙湖区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440511, 3, '440511', '440500', '金平区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440512, 3, '440512', '440500', '濠江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440513, 3, '440513', '440500', '潮阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440514, 3, '440514', '440500', '潮南区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440515, 3, '440515', '440500', '澄海区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440523, 3, '440523', '440500', '南澳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440600, 2, '440600', '440000', '佛山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440604, 3, '440604', '440600', '禅城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440605, 3, '440605', '440600', '南海区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440606, 3, '440606', '440600', '顺德区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440607, 3, '440607', '440600', '三水区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440608, 3, '440608', '440600', '高明区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440700, 2, '440700', '440000', '江门市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440703, 3, '440703', '440700', '蓬江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440704, 3, '440704', '440700', '江海区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440705, 3, '440705', '440700', '新会区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440781, 3, '440781', '440700', '台山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440783, 3, '440783', '440700', '开平市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440784, 3, '440784', '440700', '鹤山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440785, 3, '440785', '440700', '恩平市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440800, 2, '440800', '440000', '湛江市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440802, 3, '440802', '440800', '赤坎区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440803, 3, '440803', '440800', '霞山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440804, 3, '440804', '440800', '坡头区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440811, 3, '440811', '440800', '麻章区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440823, 3, '440823', '440800', '遂溪县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440825, 3, '440825', '440800', '徐闻县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440881, 3, '440881', '440800', '廉江市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440882, 3, '440882', '440800', '雷州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440883, 3, '440883', '440800', '吴川市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440900, 2, '440900', '440000', '茂名市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440902, 3, '440902', '440900', '茂南区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440904, 3, '440904', '440900', '电白区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440981, 3, '440981', '440900', '高州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440982, 3, '440982', '440900', '化州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (440983, 3, '440983', '440900', '信宜市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441200, 2, '441200', '440000', '肇庆市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441202, 3, '441202', '441200', '端州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441203, 3, '441203', '441200', '鼎湖区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441204, 3, '441204', '441200', '高要区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441223, 3, '441223', '441200', '广宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441224, 3, '441224', '441200', '怀集县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441225, 3, '441225', '441200', '封开县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441226, 3, '441226', '441200', '德庆县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441284, 3, '441284', '441200', '四会市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441300, 2, '441300', '440000', '惠州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441302, 3, '441302', '441300', '惠城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441303, 3, '441303', '441300', '惠阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441322, 3, '441322', '441300', '博罗县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441323, 3, '441323', '441300', '惠东县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441324, 3, '441324', '441300', '龙门县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441400, 2, '441400', '440000', '梅州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441402, 3, '441402', '441400', '梅江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441403, 3, '441403', '441400', '梅县区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441422, 3, '441422', '441400', '大埔县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441423, 3, '441423', '441400', '丰顺县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441424, 3, '441424', '441400', '五华县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441426, 3, '441426', '441400', '平远县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441427, 3, '441427', '441400', '蕉岭县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441481, 3, '441481', '441400', '兴宁市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441500, 2, '441500', '440000', '汕尾市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441502, 3, '441502', '441500', '城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441521, 3, '441521', '441500', '海丰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441523, 3, '441523', '441500', '陆河县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441581, 3, '441581', '441500', '陆丰市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441600, 2, '441600', '440000', '河源市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441602, 3, '441602', '441600', '源城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441704, 3, '441704', '441700', '阳东区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441621, 3, '441621', '441600', '紫金县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441622, 3, '441622', '441600', '龙川县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441623, 3, '441623', '441600', '连平县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441624, 3, '441624', '441600', '和平县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441625, 3, '441625', '441600', '东源县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441700, 2, '441700', '440000', '阳江市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441702, 3, '441702', '441700', '江城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441721, 3, '441721', '441700', '阳西县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441781, 3, '441781', '441700', '阳春市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441800, 2, '441800', '440000', '清远市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441802, 3, '441802', '441800', '清城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441803, 3, '441803', '441800', '清新区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441821, 3, '441821', '441800', '佛冈县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441823, 3, '441823', '441800', '阳山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441825, 3, '441825', '441800', '连山壮族瑶族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441826, 3, '441826', '441800', '连南瑶族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441881, 3, '441881', '441800', '英德市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441882, 3, '441882', '441800', '连州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441900, 2, '441900', '440000', '东莞市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (441999, 3, '441999', '441900', '东莞市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (442000, 2, '442000', '440000', '中山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (442099, 3, '442099', '442000', '中山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (445100, 2, '445100', '440000', '潮州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (445102, 3, '445102', '445100', '湘桥区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (445103, 3, '445103', '445100', '潮安区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (445122, 3, '445122', '445100', '饶平县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (445200, 2, '445200', '440000', '揭阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (445202, 3, '445202', '445200', '榕城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (445203, 3, '445203', '445200', '揭东区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (445222, 3, '445222', '445200', '揭西县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (445224, 3, '445224', '445200', '惠来县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (445281, 3, '445281', '445200', '普宁市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (445300, 2, '445300', '440000', '云浮市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (445302, 3, '445302', '445300', '云城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (445303, 3, '445303', '445300', '云安区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (445321, 3, '445321', '445300', '新兴县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (445322, 3, '445322', '445300', '郁南县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (445381, 3, '445381', '445300', '罗定市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450000, 1, '450000', NULL, '广西壮族自治区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450100, 2, '450100', '450000', '南宁市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450102, 3, '450102', '450100', '兴宁区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450103, 3, '450103', '450100', '青秀区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450105, 3, '450105', '450100', '江南区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450107, 3, '450107', '450100', '西乡塘区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450108, 3, '450108', '450100', '良庆区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450109, 3, '450109', '450100', '邕宁区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450110, 3, '450110', '450100', '武鸣区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450123, 3, '450123', '450100', '隆安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450124, 3, '450124', '450100', '马山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450125, 3, '450125', '450100', '上林县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450126, 3, '450126', '450100', '宾阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450181, 3, '450181', '450100', '横州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450200, 2, '450200', '450000', '柳州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450202, 3, '450202', '450200', '城中区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450203, 3, '450203', '450200', '鱼峰区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450204, 3, '450204', '450200', '柳南区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450205, 3, '450205', '450200', '柳北区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450206, 3, '450206', '450200', '柳江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450222, 3, '450222', '450200', '柳城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450223, 3, '450223', '450200', '鹿寨县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450224, 3, '450224', '450200', '融安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450225, 3, '450225', '450200', '融水苗族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450226, 3, '450226', '450200', '三江侗族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450300, 2, '450300', '450000', '桂林市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450302, 3, '450302', '450300', '秀峰区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450303, 3, '450303', '450300', '叠彩区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450304, 3, '450304', '450300', '象山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450305, 3, '450305', '450300', '七星区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450311, 3, '450311', '450300', '雁山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450312, 3, '450312', '450300', '临桂区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450321, 3, '450321', '450300', '阳朔县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450323, 3, '450323', '450300', '灵川县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450324, 3, '450324', '450300', '全州县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450325, 3, '450325', '450300', '兴安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450326, 3, '450326', '450300', '永福县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450327, 3, '450327', '450300', '灌阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450328, 3, '450328', '450300', '龙胜各族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450329, 3, '450329', '450300', '资源县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450330, 3, '450330', '450300', '平乐县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450332, 3, '450332', '450300', '恭城瑶族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450381, 3, '450381', '450300', '荔浦市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450400, 2, '450400', '450000', '梧州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450403, 3, '450403', '450400', '万秀区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450405, 3, '450405', '450400', '长洲区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450406, 3, '450406', '450400', '龙圩区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450421, 3, '450421', '450400', '苍梧县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450422, 3, '450422', '450400', '藤县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450423, 3, '450423', '450400', '蒙山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450481, 3, '450481', '450400', '岑溪市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450500, 2, '450500', '450000', '北海市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450502, 3, '450502', '450500', '海城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450503, 3, '450503', '450500', '银海区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450512, 3, '450512', '450500', '铁山港区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450521, 3, '450521', '450500', '合浦县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450600, 2, '450600', '450000', '防城港市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450602, 3, '450602', '450600', '港口区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450603, 3, '450603', '450600', '防城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450621, 3, '450621', '450600', '上思县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450681, 3, '450681', '450600', '东兴市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450700, 2, '450700', '450000', '钦州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450702, 3, '450702', '450700', '钦南区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450703, 3, '450703', '450700', '钦北区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450721, 3, '450721', '450700', '灵山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450722, 3, '450722', '450700', '浦北县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450800, 2, '450800', '450000', '贵港市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450802, 3, '450802', '450800', '港北区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450803, 3, '450803', '450800', '港南区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450804, 3, '450804', '450800', '覃塘区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450821, 3, '450821', '450800', '平南县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450881, 3, '450881', '450800', '桂平市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450900, 2, '450900', '450000', '玉林市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450902, 3, '450902', '450900', '玉州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450903, 3, '450903', '450900', '福绵区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450921, 3, '450921', '450900', '容县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450922, 3, '450922', '450900', '陆川县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450923, 3, '450923', '450900', '博白县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450924, 3, '450924', '450900', '兴业县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (450981, 3, '450981', '450900', '北流市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451000, 2, '451000', '450000', '百色市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451002, 3, '451002', '451000', '右江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451003, 3, '451003', '451000', '田阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451022, 3, '451022', '451000', '田东县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451024, 3, '451024', '451000', '德保县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451026, 3, '451026', '451000', '那坡县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451027, 3, '451027', '451000', '凌云县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451028, 3, '451028', '451000', '乐业县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451029, 3, '451029', '451000', '田林县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451030, 3, '451030', '451000', '西林县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451031, 3, '451031', '451000', '隆林各族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451081, 3, '451081', '451000', '靖西市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451082, 3, '451082', '451000', '平果市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451100, 2, '451100', '450000', '贺州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451102, 3, '451102', '451100', '八步区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451103, 3, '451103', '451100', '平桂区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451121, 3, '451121', '451100', '昭平县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451122, 3, '451122', '451100', '钟山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451123, 3, '451123', '451100', '富川瑶族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451200, 2, '451200', '450000', '河池市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451202, 3, '451202', '451200', '金城江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451203, 3, '451203', '451200', '宜州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451221, 3, '451221', '451200', '南丹县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451222, 3, '451222', '451200', '天峨县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451223, 3, '451223', '451200', '凤山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451224, 3, '451224', '451200', '东兰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451225, 3, '451225', '451200', '罗城仫佬族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451226, 3, '451226', '451200', '环江毛南族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451227, 3, '451227', '451200', '巴马瑶族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451228, 3, '451228', '451200', '都安瑶族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451229, 3, '451229', '451200', '大化瑶族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451300, 2, '451300', '450000', '来宾市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451302, 3, '451302', '451300', '兴宾区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451321, 3, '451321', '451300', '忻城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451322, 3, '451322', '451300', '象州县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451323, 3, '451323', '451300', '武宣县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451324, 3, '451324', '451300', '金秀瑶族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451381, 3, '451381', '451300', '合山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451400, 2, '451400', '450000', '崇左市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451402, 3, '451402', '451400', '江州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451421, 3, '451421', '451400', '扶绥县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451422, 3, '451422', '451400', '宁明县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451423, 3, '451423', '451400', '龙州县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451424, 3, '451424', '451400', '大新县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451425, 3, '451425', '451400', '天等县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (451481, 3, '451481', '451400', '凭祥市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (460000, 1, '460000', NULL, '海南省');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (460100, 2, '460100', '460000', '海口市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (460105, 3, '460105', '460100', '秀英区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (460106, 3, '460106', '460100', '龙华区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (460107, 3, '460107', '460100', '琼山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (460108, 3, '460108', '460100', '美兰区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (460200, 2, '460200', '460000', '三亚市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (460202, 3, '460202', '460200', '海棠区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (460203, 3, '460203', '460200', '吉阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (460204, 3, '460204', '460200', '天涯区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (460205, 3, '460205', '460200', '崖州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (460300, 2, '460300', '460000', '三沙市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (460321, 3, '460321', '460300', '西沙群岛');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (460322, 3, '460322', '460300', '南沙群岛');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (460323, 3, '460323', '460300', '中沙群岛');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (460400, 2, '460400', '460000', '儋州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (460499, 3, '460499', '460400', '儋州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500000, 1, '500000', NULL, '重庆');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (469001, 2, '469001', '460000', '五指山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (469002, 2, '469002', '460000', '琼海市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (469005, 2, '469005', '460000', '文昌市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (469006, 2, '469006', '460000', '万宁市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (469007, 2, '469007', '460000', '东方市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (469021, 2, '469021', '460000', '定安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (469022, 2, '469022', '460000', '屯昌县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (469023, 2, '469023', '460000', '澄迈县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (469024, 2, '469024', '460000', '临高县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (469025, 2, '469025', '460000', '白沙黎族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (469026, 2, '469026', '460000', '昌江黎族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (469027, 2, '469027', '460000', '乐东黎族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (469028, 2, '469028', '460000', '陵水黎族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (469029, 2, '469029', '460000', '保亭黎族苗族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (469030, 2, '469030', '460000', '琼中黎族苗族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510000, 1, '510000', NULL, '四川省');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510100, 2, '510100', '510000', '成都市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510104, 3, '510104', '510100', '锦江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510105, 3, '510105', '510100', '青羊区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510106, 3, '510106', '510100', '金牛区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510107, 3, '510107', '510100', '武侯区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510108, 3, '510108', '510100', '成华区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510112, 3, '510112', '510100', '龙泉驿区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510113, 3, '510113', '510100', '青白江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510114, 3, '510114', '510100', '新都区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510115, 3, '510115', '510100', '温江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510116, 3, '510116', '510100', '双流区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510117, 3, '510117', '510100', '郫都区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510118, 3, '510118', '510100', '新津区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510121, 3, '510121', '510100', '金堂县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510129, 3, '510129', '510100', '大邑县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510131, 3, '510131', '510100', '蒲江县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510181, 3, '510181', '510100', '都江堰市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510182, 3, '510182', '510100', '彭州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510183, 3, '510183', '510100', '邛崃市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510184, 3, '510184', '510100', '崇州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510185, 3, '510185', '510100', '简阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510300, 2, '510300', '510000', '自贡市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510302, 3, '510302', '510300', '自流井区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510303, 3, '510303', '510300', '贡井区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510304, 3, '510304', '510300', '大安区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510311, 3, '510311', '510300', '沿滩区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510321, 3, '510321', '510300', '荣县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510322, 3, '510322', '510300', '富顺县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510400, 2, '510400', '510000', '攀枝花市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510402, 3, '510402', '510400', '东区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510403, 3, '510403', '510400', '西区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510411, 3, '510411', '510400', '仁和区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510421, 3, '510421', '510400', '米易县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510422, 3, '510422', '510400', '盐边县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510500, 2, '510500', '510000', '泸州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510502, 3, '510502', '510500', '江阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510503, 3, '510503', '510500', '纳溪区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510504, 3, '510504', '510500', '龙马潭区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510521, 3, '510521', '510500', '泸县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510522, 3, '510522', '510500', '合江县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510524, 3, '510524', '510500', '叙永县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510525, 3, '510525', '510500', '古蔺县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510600, 2, '510600', '510000', '德阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510603, 3, '510603', '510600', '旌阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510604, 3, '510604', '510600', '罗江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510623, 3, '510623', '510600', '中江县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510681, 3, '510681', '510600', '广汉市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510682, 3, '510682', '510600', '什邡市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510683, 3, '510683', '510600', '绵竹市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510700, 2, '510700', '510000', '绵阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510703, 3, '510703', '510700', '涪城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510704, 3, '510704', '510700', '游仙区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510705, 3, '510705', '510700', '安州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510722, 3, '510722', '510700', '三台县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510723, 3, '510723', '510700', '盐亭县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510725, 3, '510725', '510700', '梓潼县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510726, 3, '510726', '510700', '北川羌族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510727, 3, '510727', '510700', '平武县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510781, 3, '510781', '510700', '江油市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510800, 2, '510800', '510000', '广元市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510802, 3, '510802', '510800', '利州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510811, 3, '510811', '510800', '昭化区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510812, 3, '510812', '510800', '朝天区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510821, 3, '510821', '510800', '旺苍县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510822, 3, '510822', '510800', '青川县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510823, 3, '510823', '510800', '剑阁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510824, 3, '510824', '510800', '苍溪县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510900, 2, '510900', '510000', '遂宁市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510903, 3, '510903', '510900', '船山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510904, 3, '510904', '510900', '安居区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510921, 3, '510921', '510900', '蓬溪县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510923, 3, '510923', '510900', '大英县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (510981, 3, '510981', '510900', '射洪市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511000, 2, '511000', '510000', '内江市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511002, 3, '511002', '511000', '市中区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511011, 3, '511011', '511000', '东兴区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511024, 3, '511024', '511000', '威远县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511025, 3, '511025', '511000', '资中县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511083, 3, '511083', '511000', '隆昌市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511100, 2, '511100', '510000', '乐山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511102, 3, '511102', '511100', '市中区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511111, 3, '511111', '511100', '沙湾区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511112, 3, '511112', '511100', '五通桥区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511113, 3, '511113', '511100', '金口河区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511123, 3, '511123', '511100', '犍为县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511124, 3, '511124', '511100', '井研县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511126, 3, '511126', '511100', '夹江县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511129, 3, '511129', '511100', '沐川县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511132, 3, '511132', '511100', '峨边彝族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511133, 3, '511133', '511100', '马边彝族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511181, 3, '511181', '511100', '峨眉山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511300, 2, '511300', '510000', '南充市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511302, 3, '511302', '511300', '顺庆区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511303, 3, '511303', '511300', '高坪区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511304, 3, '511304', '511300', '嘉陵区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511321, 3, '511321', '511300', '南部县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511322, 3, '511322', '511300', '营山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511323, 3, '511323', '511300', '蓬安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511324, 3, '511324', '511300', '仪陇县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511325, 3, '511325', '511300', '西充县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511381, 3, '511381', '511300', '阆中市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511400, 2, '511400', '510000', '眉山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511402, 3, '511402', '511400', '东坡区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511403, 3, '511403', '511400', '彭山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511421, 3, '511421', '511400', '仁寿县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511423, 3, '511423', '511400', '洪雅县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511424, 3, '511424', '511400', '丹棱县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511425, 3, '511425', '511400', '青神县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511500, 2, '511500', '510000', '宜宾市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511502, 3, '511502', '511500', '翠屏区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511503, 3, '511503', '511500', '南溪区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511523, 3, '511523', '511500', '江安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511524, 3, '511524', '511500', '长宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511525, 3, '511525', '511500', '高县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511526, 3, '511526', '511500', '珙县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511527, 3, '511527', '511500', '筠连县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511528, 3, '511528', '511500', '兴文县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511529, 3, '511529', '511500', '屏山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511600, 2, '511600', '510000', '广安市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511602, 3, '511602', '511600', '广安区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511603, 3, '511603', '511600', '前锋区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511621, 3, '511621', '511600', '岳池县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511622, 3, '511622', '511600', '武胜县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511623, 3, '511623', '511600', '邻水县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511681, 3, '511681', '511600', '华蓥市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511700, 2, '511700', '510000', '达州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511702, 3, '511702', '511700', '通川区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511703, 3, '511703', '511700', '达川区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511722, 3, '511722', '511700', '宣汉县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511723, 3, '511723', '511700', '开江县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511724, 3, '511724', '511700', '大竹县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511725, 3, '511725', '511700', '渠县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511781, 3, '511781', '511700', '万源市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511800, 2, '511800', '510000', '雅安市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511802, 3, '511802', '511800', '雨城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511803, 3, '511803', '511800', '名山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511822, 3, '511822', '511800', '荥经县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511823, 3, '511823', '511800', '汉源县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511824, 3, '511824', '511800', '石棉县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511825, 3, '511825', '511800', '天全县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511826, 3, '511826', '511800', '芦山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511827, 3, '511827', '511800', '宝兴县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511900, 2, '511900', '510000', '巴中市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511902, 3, '511902', '511900', '巴州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511903, 3, '511903', '511900', '恩阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511921, 3, '511921', '511900', '通江县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511922, 3, '511922', '511900', '南江县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (511923, 3, '511923', '511900', '平昌县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (512000, 2, '512000', '510000', '资阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (512002, 3, '512002', '512000', '雁江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (512021, 3, '512021', '512000', '安岳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (512022, 3, '512022', '512000', '乐至县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513200, 2, '513200', '510000', '阿坝藏族羌族自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513201, 3, '513201', '513200', '马尔康市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513221, 3, '513221', '513200', '汶川县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513222, 3, '513222', '513200', '理县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513223, 3, '513223', '513200', '茂县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513224, 3, '513224', '513200', '松潘县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513225, 3, '513225', '513200', '九寨沟县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513226, 3, '513226', '513200', '金川县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513227, 3, '513227', '513200', '小金县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513228, 3, '513228', '513200', '黑水县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513230, 3, '513230', '513200', '壤塘县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513231, 3, '513231', '513200', '阿坝县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513232, 3, '513232', '513200', '若尔盖县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513233, 3, '513233', '513200', '红原县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513300, 2, '513300', '510000', '甘孜藏族自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513301, 3, '513301', '513300', '康定市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513322, 3, '513322', '513300', '泸定县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513323, 3, '513323', '513300', '丹巴县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513324, 3, '513324', '513300', '九龙县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513325, 3, '513325', '513300', '雅江县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513326, 3, '513326', '513300', '道孚县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513327, 3, '513327', '513300', '炉霍县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513328, 3, '513328', '513300', '甘孜县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513329, 3, '513329', '513300', '新龙县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513330, 3, '513330', '513300', '德格县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513331, 3, '513331', '513300', '白玉县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513332, 3, '513332', '513300', '石渠县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513333, 3, '513333', '513300', '色达县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513334, 3, '513334', '513300', '理塘县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513335, 3, '513335', '513300', '巴塘县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513336, 3, '513336', '513300', '乡城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513337, 3, '513337', '513300', '稻城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513338, 3, '513338', '513300', '得荣县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513400, 2, '513400', '510000', '凉山彝族自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513401, 3, '513401', '513400', '西昌市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513402, 3, '513402', '513400', '会理市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513422, 3, '513422', '513400', '木里藏族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513423, 3, '513423', '513400', '盐源县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513424, 3, '513424', '513400', '德昌县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513426, 3, '513426', '513400', '会东县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513427, 3, '513427', '513400', '宁南县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513428, 3, '513428', '513400', '普格县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513429, 3, '513429', '513400', '布拖县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513430, 3, '513430', '513400', '金阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513431, 3, '513431', '513400', '昭觉县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513432, 3, '513432', '513400', '喜德县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513433, 3, '513433', '513400', '冕宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513434, 3, '513434', '513400', '越西县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513435, 3, '513435', '513400', '甘洛县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513436, 3, '513436', '513400', '美姑县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (513437, 3, '513437', '513400', '雷波县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520000, 1, '520000', NULL, '贵州省');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520100, 2, '520100', '520000', '贵阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520102, 3, '520102', '520100', '南明区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520103, 3, '520103', '520100', '云岩区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520111, 3, '520111', '520100', '花溪区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520112, 3, '520112', '520100', '乌当区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520113, 3, '520113', '520100', '白云区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520115, 3, '520115', '520100', '观山湖区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520121, 3, '520121', '520100', '开阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520122, 3, '520122', '520100', '息烽县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520123, 3, '520123', '520100', '修文县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520181, 3, '520181', '520100', '清镇市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520200, 2, '520200', '520000', '六盘水市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520201, 3, '520201', '520200', '钟山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520203, 3, '520203', '520200', '六枝特区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520204, 3, '520204', '520200', '水城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520281, 3, '520281', '520200', '盘州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520300, 2, '520300', '520000', '遵义市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520302, 3, '520302', '520300', '红花岗区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520303, 3, '520303', '520300', '汇川区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520304, 3, '520304', '520300', '播州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520322, 3, '520322', '520300', '桐梓县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520323, 3, '520323', '520300', '绥阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520324, 3, '520324', '520300', '正安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520325, 3, '520325', '520300', '道真仡佬族苗族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520326, 3, '520326', '520300', '务川仡佬族苗族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520327, 3, '520327', '520300', '凤冈县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520328, 3, '520328', '520300', '湄潭县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520329, 3, '520329', '520300', '余庆县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520330, 3, '520330', '520300', '习水县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520381, 3, '520381', '520300', '赤水市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520382, 3, '520382', '520300', '仁怀市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520400, 2, '520400', '520000', '安顺市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520402, 3, '520402', '520400', '西秀区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520403, 3, '520403', '520400', '平坝区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520422, 3, '520422', '520400', '普定县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520423, 3, '520423', '520400', '镇宁布依族苗族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520424, 3, '520424', '520400', '关岭布依族苗族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520425, 3, '520425', '520400', '紫云苗族布依族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520500, 2, '520500', '520000', '毕节市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520502, 3, '520502', '520500', '七星关区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520521, 3, '520521', '520500', '大方县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520523, 3, '520523', '520500', '金沙县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520524, 3, '520524', '520500', '织金县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520525, 3, '520525', '520500', '纳雍县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520526, 3, '520526', '520500', '威宁彝族回族苗族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520527, 3, '520527', '520500', '赫章县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520581, 3, '520581', '520500', '黔西市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520600, 2, '520600', '520000', '铜仁市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520602, 3, '520602', '520600', '碧江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520603, 3, '520603', '520600', '万山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520621, 3, '520621', '520600', '江口县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520622, 3, '520622', '520600', '玉屏侗族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520623, 3, '520623', '520600', '石阡县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520624, 3, '520624', '520600', '思南县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520625, 3, '520625', '520600', '印江土家族苗族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520626, 3, '520626', '520600', '德江县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520627, 3, '520627', '520600', '沿河土家族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (520628, 3, '520628', '520600', '松桃苗族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522300, 2, '522300', '520000', '黔西南布依族苗族自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522301, 3, '522301', '522300', '兴义市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522302, 3, '522302', '522300', '兴仁市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522323, 3, '522323', '522300', '普安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522324, 3, '522324', '522300', '晴隆县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522325, 3, '522325', '522300', '贞丰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522326, 3, '522326', '522300', '望谟县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522327, 3, '522327', '522300', '册亨县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522328, 3, '522328', '522300', '安龙县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522600, 2, '522600', '520000', '黔东南苗族侗族自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522601, 3, '522601', '522600', '凯里市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522622, 3, '522622', '522600', '黄平县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522623, 3, '522623', '522600', '施秉县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522624, 3, '522624', '522600', '三穗县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522625, 3, '522625', '522600', '镇远县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522626, 3, '522626', '522600', '岑巩县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522627, 3, '522627', '522600', '天柱县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522628, 3, '522628', '522600', '锦屏县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522629, 3, '522629', '522600', '剑河县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522630, 3, '522630', '522600', '台江县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522631, 3, '522631', '522600', '黎平县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522632, 3, '522632', '522600', '榕江县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522633, 3, '522633', '522600', '从江县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522634, 3, '522634', '522600', '雷山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522635, 3, '522635', '522600', '麻江县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522636, 3, '522636', '522600', '丹寨县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522700, 2, '522700', '520000', '黔南布依族苗族自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522701, 3, '522701', '522700', '都匀市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522702, 3, '522702', '522700', '福泉市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522722, 3, '522722', '522700', '荔波县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522723, 3, '522723', '522700', '贵定县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522725, 3, '522725', '522700', '瓮安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522726, 3, '522726', '522700', '独山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522727, 3, '522727', '522700', '平塘县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522728, 3, '522728', '522700', '罗甸县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522729, 3, '522729', '522700', '长顺县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522730, 3, '522730', '522700', '龙里县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522731, 3, '522731', '522700', '惠水县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (522732, 3, '522732', '522700', '三都水族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530000, 1, '530000', NULL, '云南省');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530100, 2, '530100', '530000', '昆明市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530102, 3, '530102', '530100', '五华区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530103, 3, '530103', '530100', '盘龙区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530111, 3, '530111', '530100', '官渡区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530112, 3, '530112', '530100', '西山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530113, 3, '530113', '530100', '东川区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530114, 3, '530114', '530100', '呈贡区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530115, 3, '530115', '530100', '晋宁区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530124, 3, '530124', '530100', '富民县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530125, 3, '530125', '530100', '宜良县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530126, 3, '530126', '530100', '石林彝族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530127, 3, '530127', '530100', '嵩明县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530128, 3, '530128', '530100', '禄劝彝族苗族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530129, 3, '530129', '530100', '寻甸回族彝族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530181, 3, '530181', '530100', '安宁市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530300, 2, '530300', '530000', '曲靖市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530302, 3, '530302', '530300', '麒麟区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530303, 3, '530303', '530300', '沾益区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530304, 3, '530304', '530300', '马龙区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530322, 3, '530322', '530300', '陆良县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530323, 3, '530323', '530300', '师宗县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530324, 3, '530324', '530300', '罗平县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530325, 3, '530325', '530300', '富源县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530326, 3, '530326', '530300', '会泽县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530381, 3, '530381', '530300', '宣威市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530400, 2, '530400', '530000', '玉溪市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530402, 3, '530402', '530400', '红塔区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530403, 3, '530403', '530400', '江川区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530423, 3, '530423', '530400', '通海县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530424, 3, '530424', '530400', '华宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530425, 3, '530425', '530400', '易门县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530426, 3, '530426', '530400', '峨山彝族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530427, 3, '530427', '530400', '新平彝族傣族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530428, 3, '530428', '530400', '元江哈尼族彝族傣族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530481, 3, '530481', '530400', '澄江市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530500, 2, '530500', '530000', '保山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530502, 3, '530502', '530500', '隆阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530521, 3, '530521', '530500', '施甸县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530523, 3, '530523', '530500', '龙陵县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530524, 3, '530524', '530500', '昌宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530581, 3, '530581', '530500', '腾冲市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530600, 2, '530600', '530000', '昭通市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530602, 3, '530602', '530600', '昭阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530621, 3, '530621', '530600', '鲁甸县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530622, 3, '530622', '530600', '巧家县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530623, 3, '530623', '530600', '盐津县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530624, 3, '530624', '530600', '大关县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530625, 3, '530625', '530600', '永善县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530626, 3, '530626', '530600', '绥江县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530627, 3, '530627', '530600', '镇雄县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530628, 3, '530628', '530600', '彝良县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530629, 3, '530629', '530600', '威信县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530681, 3, '530681', '530600', '水富市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530700, 2, '530700', '530000', '丽江市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530702, 3, '530702', '530700', '古城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530721, 3, '530721', '530700', '玉龙纳西族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530722, 3, '530722', '530700', '永胜县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530723, 3, '530723', '530700', '华坪县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530724, 3, '530724', '530700', '宁蒗彝族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530800, 2, '530800', '530000', '普洱市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530802, 3, '530802', '530800', '思茅区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530821, 3, '530821', '530800', '宁洱哈尼族彝族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530822, 3, '530822', '530800', '墨江哈尼族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530823, 3, '530823', '530800', '景东彝族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530824, 3, '530824', '530800', '景谷傣族彝族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530825, 3, '530825', '530800', '镇沅彝族哈尼族拉祜族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530826, 3, '530826', '530800', '江城哈尼族彝族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530827, 3, '530827', '530800', '孟连傣族拉祜族佤族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530828, 3, '530828', '530800', '澜沧拉祜族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530829, 3, '530829', '530800', '西盟佤族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530900, 2, '530900', '530000', '临沧市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530902, 3, '530902', '530900', '临翔区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530921, 3, '530921', '530900', '凤庆县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530922, 3, '530922', '530900', '云县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530923, 3, '530923', '530900', '永德县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530924, 3, '530924', '530900', '镇康县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530925, 3, '530925', '530900', '双江拉祜族佤族布朗族傣族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530926, 3, '530926', '530900', '耿马傣族佤族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (530927, 3, '530927', '530900', '沧源佤族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532300, 2, '532300', '530000', '楚雄彝族自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532301, 3, '532301', '532300', '楚雄市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532302, 3, '532302', '532300', '禄丰市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532322, 3, '532322', '532300', '双柏县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532323, 3, '532323', '532300', '牟定县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532324, 3, '532324', '532300', '南华县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532325, 3, '532325', '532300', '姚安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532326, 3, '532326', '532300', '大姚县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532327, 3, '532327', '532300', '永仁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532328, 3, '532328', '532300', '元谋县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532329, 3, '532329', '532300', '武定县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532500, 2, '532500', '530000', '红河哈尼族彝族自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532501, 3, '532501', '532500', '个旧市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532502, 3, '532502', '532500', '开远市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532503, 3, '532503', '532500', '蒙自市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532504, 3, '532504', '532500', '弥勒市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532523, 3, '532523', '532500', '屏边苗族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532524, 3, '532524', '532500', '建水县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532525, 3, '532525', '532500', '石屏县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532527, 3, '532527', '532500', '泸西县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532528, 3, '532528', '532500', '元阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532529, 3, '532529', '532500', '红河县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532530, 3, '532530', '532500', '金平苗族瑶族傣族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532531, 3, '532531', '532500', '绿春县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532532, 3, '532532', '532500', '河口瑶族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532600, 2, '532600', '530000', '文山壮族苗族自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532601, 3, '532601', '532600', '文山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532622, 3, '532622', '532600', '砚山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532623, 3, '532623', '532600', '西畴县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532624, 3, '532624', '532600', '麻栗坡县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532625, 3, '532625', '532600', '马关县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532626, 3, '532626', '532600', '丘北县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532627, 3, '532627', '532600', '广南县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532628, 3, '532628', '532600', '富宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532800, 2, '532800', '530000', '西双版纳傣族自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532801, 3, '532801', '532800', '景洪市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532822, 3, '532822', '532800', '勐海县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532823, 3, '532823', '532800', '勐腊县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532900, 2, '532900', '530000', '大理白族自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532901, 3, '532901', '532900', '大理市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532922, 3, '532922', '532900', '漾濞彝族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532923, 3, '532923', '532900', '祥云县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532924, 3, '532924', '532900', '宾川县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532925, 3, '532925', '532900', '弥渡县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532926, 3, '532926', '532900', '南涧彝族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532927, 3, '532927', '532900', '巍山彝族回族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532928, 3, '532928', '532900', '永平县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532929, 3, '532929', '532900', '云龙县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532930, 3, '532930', '532900', '洱源县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532931, 3, '532931', '532900', '剑川县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (532932, 3, '532932', '532900', '鹤庆县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (533100, 2, '533100', '530000', '德宏傣族景颇族自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (533102, 3, '533102', '533100', '瑞丽市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (533103, 3, '533103', '533100', '芒市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (533122, 3, '533122', '533100', '梁河县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (533123, 3, '533123', '533100', '盈江县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (533124, 3, '533124', '533100', '陇川县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (533300, 2, '533300', '530000', '怒江傈僳族自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (533301, 3, '533301', '533300', '泸水市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (533323, 3, '533323', '533300', '福贡县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (533324, 3, '533324', '533300', '贡山独龙族怒族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (533325, 3, '533325', '533300', '兰坪白族普米族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (533400, 2, '533400', '530000', '迪庆藏族自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (533401, 3, '533401', '533400', '香格里拉市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (533422, 3, '533422', '533400', '德钦县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (533423, 3, '533423', '533400', '维西傈僳族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540000, 1, '540000', NULL, '西藏自治区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540100, 2, '540100', '540000', '拉萨市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540102, 3, '540102', '540100', '城关区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540103, 3, '540103', '540100', '堆龙德庆区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540104, 3, '540104', '540100', '达孜区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540121, 3, '540121', '540100', '林周县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540122, 3, '540122', '540100', '当雄县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540123, 3, '540123', '540100', '尼木县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540124, 3, '540124', '540100', '曲水县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540127, 3, '540127', '540100', '墨竹工卡县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540200, 2, '540200', '540000', '日喀则市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540202, 3, '540202', '540200', '桑珠孜区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540221, 3, '540221', '540200', '南木林县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540222, 3, '540222', '540200', '江孜县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540223, 3, '540223', '540200', '定日县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540224, 3, '540224', '540200', '萨迦县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540225, 3, '540225', '540200', '拉孜县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540226, 3, '540226', '540200', '昂仁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540227, 3, '540227', '540200', '谢通门县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540228, 3, '540228', '540200', '白朗县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540229, 3, '540229', '540200', '仁布县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540230, 3, '540230', '540200', '康马县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540231, 3, '540231', '540200', '定结县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540232, 3, '540232', '540200', '仲巴县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540233, 3, '540233', '540200', '亚东县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540234, 3, '540234', '540200', '吉隆县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540235, 3, '540235', '540200', '聂拉木县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540236, 3, '540236', '540200', '萨嘎县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540237, 3, '540237', '540200', '岗巴县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540300, 2, '540300', '540000', '昌都市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540302, 3, '540302', '540300', '卡若区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540321, 3, '540321', '540300', '江达县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540322, 3, '540322', '540300', '贡觉县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540323, 3, '540323', '540300', '类乌齐县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540324, 3, '540324', '540300', '丁青县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540325, 3, '540325', '540300', '察雅县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540326, 3, '540326', '540300', '八宿县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540327, 3, '540327', '540300', '左贡县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540328, 3, '540328', '540300', '芒康县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540329, 3, '540329', '540300', '洛隆县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540330, 3, '540330', '540300', '边坝县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540400, 2, '540400', '540000', '林芝市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540402, 3, '540402', '540400', '巴宜区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540421, 3, '540421', '540400', '工布江达县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540422, 3, '540422', '540400', '米林市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540423, 3, '540423', '540400', '墨脱县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540424, 3, '540424', '540400', '波密县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540425, 3, '540425', '540400', '察隅县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540426, 3, '540426', '540400', '朗县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540500, 2, '540500', '540000', '山南市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540502, 3, '540502', '540500', '乃东区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540521, 3, '540521', '540500', '扎囊县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540522, 3, '540522', '540500', '贡嘎县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540523, 3, '540523', '540500', '桑日县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540524, 3, '540524', '540500', '琼结县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540525, 3, '540525', '540500', '曲松县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540526, 3, '540526', '540500', '措美县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540527, 3, '540527', '540500', '洛扎县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540528, 3, '540528', '540500', '加查县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540529, 3, '540529', '540500', '隆子县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540530, 3, '540530', '540500', '错那市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540531, 3, '540531', '540500', '浪卡子县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540600, 2, '540600', '540000', '那曲市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540602, 3, '540602', '540600', '色尼区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540621, 3, '540621', '540600', '嘉黎县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540622, 3, '540622', '540600', '比如县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540623, 3, '540623', '540600', '聂荣县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540624, 3, '540624', '540600', '安多县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540625, 3, '540625', '540600', '申扎县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540626, 3, '540626', '540600', '索县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540627, 3, '540627', '540600', '班戈县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540628, 3, '540628', '540600', '巴青县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540629, 3, '540629', '540600', '尼玛县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (540630, 3, '540630', '540600', '双湖县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (542500, 2, '542500', '540000', '阿里地区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (542521, 3, '542521', '542500', '普兰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (542522, 3, '542522', '542500', '札达县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (542523, 3, '542523', '542500', '噶尔县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (542524, 3, '542524', '542500', '日土县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (542525, 3, '542525', '542500', '革吉县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (542526, 3, '542526', '542500', '改则县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (542527, 3, '542527', '542500', '措勤县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610000, 1, '610000', NULL, '陕西省');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610100, 2, '610100', '610000', '西安市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610102, 3, '610102', '610100', '新城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610103, 3, '610103', '610100', '碑林区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610104, 3, '610104', '610100', '莲湖区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610111, 3, '610111', '610100', '灞桥区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610112, 3, '610112', '610100', '未央区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610113, 3, '610113', '610100', '雁塔区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610114, 3, '610114', '610100', '阎良区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610115, 3, '610115', '610100', '临潼区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610116, 3, '610116', '610100', '长安区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610117, 3, '610117', '610100', '高陵区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610118, 3, '610118', '610100', '鄠邑区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610122, 3, '610122', '610100', '蓝田县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610124, 3, '610124', '610100', '周至县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610200, 2, '610200', '610000', '铜川市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610202, 3, '610202', '610200', '王益区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610203, 3, '610203', '610200', '印台区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610204, 3, '610204', '610200', '耀州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610222, 3, '610222', '610200', '宜君县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610300, 2, '610300', '610000', '宝鸡市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610302, 3, '610302', '610300', '渭滨区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610303, 3, '610303', '610300', '金台区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610304, 3, '610304', '610300', '陈仓区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610305, 3, '610305', '610300', '凤翔区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610323, 3, '610323', '610300', '岐山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610324, 3, '610324', '610300', '扶风县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610326, 3, '610326', '610300', '眉县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610327, 3, '610327', '610300', '陇县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610328, 3, '610328', '610300', '千阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610329, 3, '610329', '610300', '麟游县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610330, 3, '610330', '610300', '凤县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610331, 3, '610331', '610300', '太白县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610400, 2, '610400', '610000', '咸阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610402, 3, '610402', '610400', '秦都区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610403, 3, '610403', '610400', '杨陵区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610404, 3, '610404', '610400', '渭城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610422, 3, '610422', '610400', '三原县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610423, 3, '610423', '610400', '泾阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610424, 3, '610424', '610400', '乾县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610425, 3, '610425', '610400', '礼泉县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610426, 3, '610426', '610400', '永寿县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610428, 3, '610428', '610400', '长武县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610429, 3, '610429', '610400', '旬邑县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610430, 3, '610430', '610400', '淳化县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610431, 3, '610431', '610400', '武功县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610481, 3, '610481', '610400', '兴平市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610482, 3, '610482', '610400', '彬州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610500, 2, '610500', '610000', '渭南市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610502, 3, '610502', '610500', '临渭区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610503, 3, '610503', '610500', '华州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610522, 3, '610522', '610500', '潼关县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610523, 3, '610523', '610500', '大荔县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610524, 3, '610524', '610500', '合阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610525, 3, '610525', '610500', '澄城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610526, 3, '610526', '610500', '蒲城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610527, 3, '610527', '610500', '白水县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610528, 3, '610528', '610500', '富平县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610581, 3, '610581', '610500', '韩城市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610582, 3, '610582', '610500', '华阴市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610600, 2, '610600', '610000', '延安市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610602, 3, '610602', '610600', '宝塔区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610603, 3, '610603', '610600', '安塞区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610621, 3, '610621', '610600', '延长县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610622, 3, '610622', '610600', '延川县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610625, 3, '610625', '610600', '志丹县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610626, 3, '610626', '610600', '吴起县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610627, 3, '610627', '610600', '甘泉县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610628, 3, '610628', '610600', '富县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610629, 3, '610629', '610600', '洛川县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610630, 3, '610630', '610600', '宜川县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610631, 3, '610631', '610600', '黄龙县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610632, 3, '610632', '610600', '黄陵县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610681, 3, '610681', '610600', '子长市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610700, 2, '610700', '610000', '汉中市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610702, 3, '610702', '610700', '汉台区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610703, 3, '610703', '610700', '南郑区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610722, 3, '610722', '610700', '城固县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610723, 3, '610723', '610700', '洋县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610724, 3, '610724', '610700', '西乡县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610725, 3, '610725', '610700', '勉县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610726, 3, '610726', '610700', '宁强县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610727, 3, '610727', '610700', '略阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610728, 3, '610728', '610700', '镇巴县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610729, 3, '610729', '610700', '留坝县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610730, 3, '610730', '610700', '佛坪县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610800, 2, '610800', '610000', '榆林市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610802, 3, '610802', '610800', '榆阳区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610803, 3, '610803', '610800', '横山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610822, 3, '610822', '610800', '府谷县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610824, 3, '610824', '610800', '靖边县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610825, 3, '610825', '610800', '定边县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610826, 3, '610826', '610800', '绥德县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610827, 3, '610827', '610800', '米脂县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610828, 3, '610828', '610800', '佳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610829, 3, '610829', '610800', '吴堡县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610830, 3, '610830', '610800', '清涧县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610831, 3, '610831', '610800', '子洲县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610881, 3, '610881', '610800', '神木市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610900, 2, '610900', '610000', '安康市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610902, 3, '610902', '610900', '汉滨区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610921, 3, '610921', '610900', '汉阴县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610922, 3, '610922', '610900', '石泉县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610923, 3, '610923', '610900', '宁陕县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610924, 3, '610924', '610900', '紫阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610925, 3, '610925', '610900', '岚皋县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610926, 3, '610926', '610900', '平利县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610927, 3, '610927', '610900', '镇坪县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610929, 3, '610929', '610900', '白河县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (610981, 3, '610981', '610900', '旬阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (611000, 2, '611000', '610000', '商洛市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (611002, 3, '611002', '611000', '商州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (611021, 3, '611021', '611000', '洛南县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (611022, 3, '611022', '611000', '丹凤县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (611023, 3, '611023', '611000', '商南县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (611024, 3, '611024', '611000', '山阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (611025, 3, '611025', '611000', '镇安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (611026, 3, '611026', '611000', '柞水县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620000, 1, '620000', NULL, '甘肃省');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620100, 2, '620100', '620000', '兰州市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620102, 3, '620102', '620100', '城关区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620103, 3, '620103', '620100', '七里河区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620104, 3, '620104', '620100', '西固区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620105, 3, '620105', '620100', '安宁区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620111, 3, '620111', '620100', '红古区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620121, 3, '620121', '620100', '永登县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620122, 3, '620122', '620100', '皋兰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620123, 3, '620123', '620100', '榆中县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620200, 2, '620200', '620000', '嘉峪关市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620299, 3, '620299', '620200', '嘉峪关市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620300, 2, '620300', '620000', '金昌市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620302, 3, '620302', '620300', '金川区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620321, 3, '620321', '620300', '永昌县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620400, 2, '620400', '620000', '白银市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620402, 3, '620402', '620400', '白银区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620403, 3, '620403', '620400', '平川区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620421, 3, '620421', '620400', '靖远县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620422, 3, '620422', '620400', '会宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620423, 3, '620423', '620400', '景泰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620500, 2, '620500', '620000', '天水市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620502, 3, '620502', '620500', '秦州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620503, 3, '620503', '620500', '麦积区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620521, 3, '620521', '620500', '清水县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620522, 3, '620522', '620500', '秦安县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620523, 3, '620523', '620500', '甘谷县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620524, 3, '620524', '620500', '武山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620525, 3, '620525', '620500', '张家川回族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620600, 2, '620600', '620000', '武威市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620602, 3, '620602', '620600', '凉州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620621, 3, '620621', '620600', '民勤县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620622, 3, '620622', '620600', '古浪县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620623, 3, '620623', '620600', '天祝藏族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620700, 2, '620700', '620000', '张掖市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620702, 3, '620702', '620700', '甘州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620721, 3, '620721', '620700', '肃南裕固族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620722, 3, '620722', '620700', '民乐县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620723, 3, '620723', '620700', '临泽县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620724, 3, '620724', '620700', '高台县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620725, 3, '620725', '620700', '山丹县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620800, 2, '620800', '620000', '平凉市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620802, 3, '620802', '620800', '崆峒区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620821, 3, '620821', '620800', '泾川县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620822, 3, '620822', '620800', '灵台县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620823, 3, '620823', '620800', '崇信县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620825, 3, '620825', '620800', '庄浪县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620826, 3, '620826', '620800', '静宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620881, 3, '620881', '620800', '华亭市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620900, 2, '620900', '620000', '酒泉市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620902, 3, '620902', '620900', '肃州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620921, 3, '620921', '620900', '金塔县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620922, 3, '620922', '620900', '瓜州县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620923, 3, '620923', '620900', '肃北蒙古族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620924, 3, '620924', '620900', '阿克塞哈萨克族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620981, 3, '620981', '620900', '玉门市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (620982, 3, '620982', '620900', '敦煌市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (621000, 2, '621000', '620000', '庆阳市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (621002, 3, '621002', '621000', '西峰区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (621021, 3, '621021', '621000', '庆城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (621022, 3, '621022', '621000', '环县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (621023, 3, '621023', '621000', '华池县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (621024, 3, '621024', '621000', '合水县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (621025, 3, '621025', '621000', '正宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (621026, 3, '621026', '621000', '宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (621027, 3, '621027', '621000', '镇原县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (621100, 2, '621100', '620000', '定西市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (621102, 3, '621102', '621100', '安定区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (621121, 3, '621121', '621100', '通渭县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (621122, 3, '621122', '621100', '陇西县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (621123, 3, '621123', '621100', '渭源县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (621124, 3, '621124', '621100', '临洮县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (621125, 3, '621125', '621100', '漳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (621126, 3, '621126', '621100', '岷县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (621200, 2, '621200', '620000', '陇南市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (621202, 3, '621202', '621200', '武都区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (621221, 3, '621221', '621200', '成县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (621222, 3, '621222', '621200', '文县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (621223, 3, '621223', '621200', '宕昌县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (621224, 3, '621224', '621200', '康县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (621225, 3, '621225', '621200', '西和县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (621226, 3, '621226', '621200', '礼县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (621227, 3, '621227', '621200', '徽县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (621228, 3, '621228', '621200', '两当县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (622900, 2, '622900', '620000', '临夏回族自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (622901, 3, '622901', '622900', '临夏市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (622921, 3, '622921', '622900', '临夏县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (622922, 3, '622922', '622900', '康乐县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (622923, 3, '622923', '622900', '永靖县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (622924, 3, '622924', '622900', '广河县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (622925, 3, '622925', '622900', '和政县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (622926, 3, '622926', '622900', '东乡族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (622927, 3, '622927', '622900', '积石山保安族东乡族撒拉族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (623000, 2, '623000', '620000', '甘南藏族自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (623001, 3, '623001', '623000', '合作市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (623021, 3, '623021', '623000', '临潭县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (623022, 3, '623022', '623000', '卓尼县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (623023, 3, '623023', '623000', '舟曲县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (623024, 3, '623024', '623000', '迭部县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (623025, 3, '623025', '623000', '玛曲县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (623026, 3, '623026', '623000', '碌曲县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (623027, 3, '623027', '623000', '夏河县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (630000, 1, '630000', NULL, '青海省');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (630100, 2, '630100', '630000', '西宁市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (630102, 3, '630102', '630100', '城东区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (630103, 3, '630103', '630100', '城中区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (630104, 3, '630104', '630100', '城西区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (630105, 3, '630105', '630100', '城北区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (630106, 3, '630106', '630100', '湟中区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (630121, 3, '630121', '630100', '大通回族土族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (630123, 3, '630123', '630100', '湟源县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (630200, 2, '630200', '630000', '海东市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (630202, 3, '630202', '630200', '乐都区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (630203, 3, '630203', '630200', '平安区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (630222, 3, '630222', '630200', '民和回族土族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (630223, 3, '630223', '630200', '互助土族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (630224, 3, '630224', '630200', '化隆回族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (630225, 3, '630225', '630200', '循化撒拉族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632200, 2, '632200', '630000', '海北藏族自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632221, 3, '632221', '632200', '门源回族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632222, 3, '632222', '632200', '祁连县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632223, 3, '632223', '632200', '海晏县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632224, 3, '632224', '632200', '刚察县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632300, 2, '632300', '630000', '黄南藏族自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632301, 3, '632301', '632300', '同仁市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632322, 3, '632322', '632300', '尖扎县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632323, 3, '632323', '632300', '泽库县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632324, 3, '632324', '632300', '河南蒙古族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632500, 2, '632500', '630000', '海南藏族自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632521, 3, '632521', '632500', '共和县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632522, 3, '632522', '632500', '同德县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632523, 3, '632523', '632500', '贵德县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632524, 3, '632524', '632500', '兴海县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632525, 3, '632525', '632500', '贵南县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632600, 2, '632600', '630000', '果洛藏族自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632621, 3, '632621', '632600', '玛沁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632622, 3, '632622', '632600', '班玛县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632623, 3, '632623', '632600', '甘德县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632624, 3, '632624', '632600', '达日县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632625, 3, '632625', '632600', '久治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632626, 3, '632626', '632600', '玛多县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632700, 2, '632700', '630000', '玉树藏族自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632701, 3, '632701', '632700', '玉树市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632722, 3, '632722', '632700', '杂多县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632723, 3, '632723', '632700', '称多县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632724, 3, '632724', '632700', '治多县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632725, 3, '632725', '632700', '囊谦县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632726, 3, '632726', '632700', '曲麻莱县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632800, 2, '632800', '630000', '海西蒙古族藏族自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632801, 3, '632801', '632800', '格尔木市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632802, 3, '632802', '632800', '德令哈市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632803, 3, '632803', '632800', '茫崖市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632821, 3, '632821', '632800', '乌兰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632822, 3, '632822', '632800', '都兰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632823, 3, '632823', '632800', '天峻县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (632857, 3, '632857', '632800', '大柴旦行政委员会');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640000, 1, '640000', NULL, '宁夏回族自治区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640100, 2, '640100', '640000', '银川市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640104, 3, '640104', '640100', '兴庆区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640105, 3, '640105', '640100', '西夏区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640106, 3, '640106', '640100', '金凤区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640121, 3, '640121', '640100', '永宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640122, 3, '640122', '640100', '贺兰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640181, 3, '640181', '640100', '灵武市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640200, 2, '640200', '640000', '石嘴山市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640202, 3, '640202', '640200', '大武口区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640205, 3, '640205', '640200', '惠农区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640221, 3, '640221', '640200', '平罗县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640300, 2, '640300', '640000', '吴忠市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640302, 3, '640302', '640300', '利通区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640303, 3, '640303', '640300', '红寺堡区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640323, 3, '640323', '640300', '盐池县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640324, 3, '640324', '640300', '同心县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640381, 3, '640381', '640300', '青铜峡市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640400, 2, '640400', '640000', '固原市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640402, 3, '640402', '640400', '原州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640422, 3, '640422', '640400', '西吉县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640423, 3, '640423', '640400', '隆德县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640424, 3, '640424', '640400', '泾源县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640425, 3, '640425', '640400', '彭阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640500, 2, '640500', '640000', '中卫市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640502, 3, '640502', '640500', '沙坡头区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640521, 3, '640521', '640500', '中宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (640522, 3, '640522', '640500', '海原县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (650000, 1, '650000', NULL, '新疆维吾尔自治区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (650100, 2, '650100', '650000', '乌鲁木齐市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (650102, 3, '650102', '650100', '天山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (650103, 3, '650103', '650100', '沙依巴克区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (650104, 3, '650104', '650100', '新市区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (650105, 3, '650105', '650100', '水磨沟区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (650106, 3, '650106', '650100', '头屯河区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (650107, 3, '650107', '650100', '达坂城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (650109, 3, '650109', '650100', '米东区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (650121, 3, '650121', '650100', '乌鲁木齐县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (650200, 2, '650200', '650000', '克拉玛依市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (650202, 3, '650202', '650200', '独山子区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (650203, 3, '650203', '650200', '克拉玛依区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (650204, 3, '650204', '650200', '白碱滩区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (650205, 3, '650205', '650200', '乌尔禾区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (650400, 2, '650400', '650000', '吐鲁番市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (650402, 3, '650402', '650400', '高昌区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (650421, 3, '650421', '650400', '鄯善县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (650422, 3, '650422', '650400', '托克逊县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (650500, 2, '650500', '650000', '哈密市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (650502, 3, '650502', '650500', '伊州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (650521, 3, '650521', '650500', '巴里坤哈萨克自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (650522, 3, '650522', '650500', '伊吾县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652300, 2, '652300', '650000', '昌吉回族自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652301, 3, '652301', '652300', '昌吉市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652302, 3, '652302', '652300', '阜康市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652323, 3, '652323', '652300', '呼图壁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652324, 3, '652324', '652300', '玛纳斯县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652325, 3, '652325', '652300', '奇台县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652327, 3, '652327', '652300', '吉木萨尔县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652328, 3, '652328', '652300', '木垒哈萨克自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652700, 2, '652700', '650000', '博尔塔拉蒙古自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652701, 3, '652701', '652700', '博乐市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652702, 3, '652702', '652700', '阿拉山口市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652722, 3, '652722', '652700', '精河县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652723, 3, '652723', '652700', '温泉县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652800, 2, '652800', '650000', '巴音郭楞蒙古自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652801, 3, '652801', '652800', '库尔勒市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652822, 3, '652822', '652800', '轮台县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652823, 3, '652823', '652800', '尉犁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652824, 3, '652824', '652800', '若羌县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652825, 3, '652825', '652800', '且末县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652826, 3, '652826', '652800', '焉耆回族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652827, 3, '652827', '652800', '和静县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652828, 3, '652828', '652800', '和硕县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652829, 3, '652829', '652800', '博湖县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652900, 2, '652900', '650000', '阿克苏地区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652901, 3, '652901', '652900', '阿克苏市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652902, 3, '652902', '652900', '库车市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652922, 3, '652922', '652900', '温宿县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652924, 3, '652924', '652900', '沙雅县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652925, 3, '652925', '652900', '新和县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652926, 3, '652926', '652900', '拜城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652927, 3, '652927', '652900', '乌什县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652928, 3, '652928', '652900', '阿瓦提县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (652929, 3, '652929', '652900', '柯坪县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (653000, 2, '653000', '650000', '克孜勒苏柯尔克孜自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (653001, 3, '653001', '653000', '阿图什市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (653022, 3, '653022', '653000', '阿克陶县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (653023, 3, '653023', '653000', '阿合奇县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (653024, 3, '653024', '653000', '乌恰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (653100, 2, '653100', '650000', '喀什地区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (653101, 3, '653101', '653100', '喀什市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (653121, 3, '653121', '653100', '疏附县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (653122, 3, '653122', '653100', '疏勒县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (653123, 3, '653123', '653100', '英吉沙县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (653124, 3, '653124', '653100', '泽普县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (653125, 3, '653125', '653100', '莎车县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (653126, 3, '653126', '653100', '叶城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (653127, 3, '653127', '653100', '麦盖提县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (653128, 3, '653128', '653100', '岳普湖县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (653129, 3, '653129', '653100', '伽师县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (653130, 3, '653130', '653100', '巴楚县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (653131, 3, '653131', '653100', '塔什库尔干塔吉克自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (653200, 2, '653200', '650000', '和田地区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (653201, 3, '653201', '653200', '和田市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (653221, 3, '653221', '653200', '和田县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (653222, 3, '653222', '653200', '墨玉县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (653223, 3, '653223', '653200', '皮山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (653224, 3, '653224', '653200', '洛浦县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (653225, 3, '653225', '653200', '策勒县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (653226, 3, '653226', '653200', '于田县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (653227, 3, '653227', '653200', '民丰县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654000, 2, '654000', '650000', '伊犁哈萨克自治州');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654002, 3, '654002', '654000', '伊宁市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654003, 3, '654003', '654000', '奎屯市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654004, 3, '654004', '654000', '霍尔果斯市');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654021, 3, '654021', '654000', '伊宁县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654022, 3, '654022', '654000', '察布查尔锡伯自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654023, 3, '654023', '654000', '霍城县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654024, 3, '654024', '654000', '巩留县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654025, 3, '654025', '654000', '新源县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654026, 3, '654026', '654000', '昭苏县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654027, 3, '654027', '654000', '特克斯县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (654028, 3, '654028', '654000', '尼勒克县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (811100, 3, '811100', '810010', '中西区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (811200, 3, '811200', '810010', '湾仔区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (811300, 3, '811300', '810010', '东区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (811400, 3, '811400', '810010', '南区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (812100, 3, '812100', '810010', '油尖旺区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (812200, 3, '812200', '810010', '深水埗区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (812300, 3, '812300', '810010', '九龙城区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (812400, 3, '812400', '810010', '黄大仙区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (812500, 3, '812500', '810010', '观塘区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (813100, 3, '813100', '810010', '葵青区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (813200, 3, '813200', '810010', '荃湾区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (813300, 3, '813300', '810010', '屯门区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (813400, 3, '813400', '810010', '元朗区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (813500, 3, '813500', '810010', '北区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (813600, 3, '813600', '810010', '大埔区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (813700, 3, '813700', '810010', '沙田区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (813800, 3, '813800', '810010', '西贡区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (813900, 3, '813900', '810010', '离岛区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (110117, 3, '110117', '110010', '平谷区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (110118, 3, '110118', '110010', '密云区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (110119, 3, '110119', '110010', '延庆区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (310101, 3, '310101', '310010', '黄浦区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (310104, 3, '310104', '310010', '徐汇区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (310105, 3, '310105', '310010', '长宁区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (310106, 3, '310106', '310010', '静安区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (310107, 3, '310107', '310010', '普陀区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (310109, 3, '310109', '310010', '虹口区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (310110, 3, '310110', '310010', '杨浦区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (310112, 3, '310112', '310010', '闵行区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (310113, 3, '310113', '310010', '宝山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (310114, 3, '310114', '310010', '嘉定区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (310115, 3, '310115', '310010', '浦东新区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (310116, 3, '310116', '310010', '金山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (310117, 3, '310117', '310010', '松江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (310118, 3, '310118', '310010', '青浦区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (310120, 3, '310120', '310010', '奉贤区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (310151, 3, '310151', '310010', '崇明区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500101, 3, '500101', '500010', '万州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500102, 3, '500102', '500010', '涪陵区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500103, 3, '500103', '500010', '渝中区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500104, 3, '500104', '500010', '大渡口区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500105, 3, '500105', '500010', '江北区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500106, 3, '500106', '500010', '沙坪坝区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500107, 3, '500107', '500010', '九龙坡区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500108, 3, '500108', '500010', '南岸区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500109, 3, '500109', '500010', '北碚区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500110, 3, '500110', '500010', '綦江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500111, 3, '500111', '500010', '大足区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500112, 3, '500112', '500010', '渝北区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500113, 3, '500113', '500010', '巴南区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500114, 3, '500114', '500010', '黔江区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500115, 3, '500115', '500010', '长寿区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500116, 3, '500116', '500010', '江津区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500117, 3, '500117', '500010', '合川区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500118, 3, '500118', '500010', '永川区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500119, 3, '500119', '500010', '南川区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500120, 3, '500120', '500010', '璧山区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500151, 3, '500151', '500010', '铜梁区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500152, 3, '500152', '500010', '潼南区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500153, 3, '500153', '500010', '荣昌区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500154, 3, '500154', '500010', '开州区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500155, 3, '500155', '500010', '梁平区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500156, 3, '500156', '500010', '武隆区');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500229, 3, '500229', '500010', '城口县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500230, 3, '500230', '500010', '丰都县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500231, 3, '500231', '500010', '垫江县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500233, 3, '500233', '500010', '忠县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500235, 3, '500235', '500010', '云阳县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500236, 3, '500236', '500010', '奉节县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500237, 3, '500237', '500010', '巫山县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500238, 3, '500238', '500010', '巫溪县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500240, 3, '500240', '500010', '石柱土家族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500241, 3, '500241', '500010', '秀山土家族苗族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500242, 3, '500242', '500010', '酉阳土家族苗族自治县');
INSERT INTO "public"."t_area" ("id", "type", "code", "p_code", "name") VALUES (500243, 3, '500243', '500010', '彭水苗族土家族自治县');
COMMIT;

-- ----------------------------
-- Table structure for t_config
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_config";
CREATE TABLE "public"."t_config" (
                                     "id" int8 NOT NULL,
                                     "cid" int8 NOT NULL,
                                     "sort" int4 NOT NULL DEFAULT 0,
                                     "code" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                     "value" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
                                     "create_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "public"."t_config"."id" IS '主键';
COMMENT ON COLUMN "public"."t_config"."cid" IS 'cid';
COMMENT ON COLUMN "public"."t_config"."sort" IS '排序';
COMMENT ON COLUMN "public"."t_config"."code" IS '编码';
COMMENT ON COLUMN "public"."t_config"."value" IS '值';
COMMENT ON COLUMN "public"."t_config"."create_time" IS '创建时间';
COMMENT ON TABLE "public"."t_config" IS '配置';

-- ----------------------------
-- Records of t_config
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_corp
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_corp";
CREATE TABLE "public"."t_corp" (
                                   "id" int8 NOT NULL,
                                   "name" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
                                   "status" int4 NOT NULL,
                                   "creator" int8,
                                   "updater" int8,
                                   "deleter" int8,
                                   "create_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   "update_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   "delete_time" timestamp(0),
                                   "is_deleted" bool NOT NULL DEFAULT false,
                                   "app_count" int4 NOT NULL DEFAULT 0,
                                   "dep_count" int4 NOT NULL DEFAULT 1,
                                   "emp_count" int4 NOT NULL DEFAULT 1,
                                   "icon" varchar(200) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."t_corp"."id" IS '主键';
COMMENT ON COLUMN "public"."t_corp"."name" IS '名称';
COMMENT ON COLUMN "public"."t_corp"."status" IS '状态，1：正常';
COMMENT ON COLUMN "public"."t_corp"."creator" IS '创建人';
COMMENT ON COLUMN "public"."t_corp"."updater" IS '更新人';
COMMENT ON COLUMN "public"."t_corp"."deleter" IS '删除人';
COMMENT ON COLUMN "public"."t_corp"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."t_corp"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."t_corp"."delete_time" IS '删除时间';
COMMENT ON COLUMN "public"."t_corp"."is_deleted" IS '是否已删除';
COMMENT ON COLUMN "public"."t_corp"."app_count" IS '应用数';
COMMENT ON COLUMN "public"."t_corp"."dep_count" IS '部门数';
COMMENT ON COLUMN "public"."t_corp"."emp_count" IS '员工数';
COMMENT ON COLUMN "public"."t_corp"."icon" IS '图标';
COMMENT ON TABLE "public"."t_corp" IS '租户';

-- ----------------------------
-- Records of t_corp
-- ----------------------------
BEGIN;
INSERT INTO "public"."t_corp" ("id", "name", "status", "creator", "updater", "deleter", "create_time", "update_time", "delete_time", "is_deleted", "app_count", "dep_count", "emp_count", "icon") VALUES (2, '星途科技', 1, NULL, NULL, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', NULL, 'f', 0, 1, 1, 'https://foruda.gitee.com/avatar/1676910173490791781/395097_bestaone_1602213346.png');
INSERT INTO "public"."t_corp" ("id", "name", "status", "creator", "updater", "deleter", "create_time", "update_time", "delete_time", "is_deleted", "app_count", "dep_count", "emp_count", "icon") VALUES (3, '天辉智能', 1, NULL, NULL, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', NULL, 'f', 0, 0, 0, NULL);
INSERT INTO "public"."t_corp" ("id", "name", "status", "creator", "updater", "deleter", "create_time", "update_time", "delete_time", "is_deleted", "app_count", "dep_count", "emp_count", "icon") VALUES (1, '上海志远科技', 1, NULL, NULL, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', NULL, 'f', 0, 1, 1, 'https://foruda.gitee.com/avatar/1676910173490791781/395097_bestaone_1602213346.png');
COMMIT;

-- ----------------------------
-- Table structure for t_dep_emp
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_dep_emp";
CREATE TABLE "public"."t_dep_emp" (
                                      "id" int8 NOT NULL,
                                      "cid" int8 NOT NULL,
                                      "emp_id" int8 NOT NULL,
                                      "dep_id" int8 NOT NULL
)
;
COMMENT ON COLUMN "public"."t_dep_emp"."id" IS '主键';
COMMENT ON COLUMN "public"."t_dep_emp"."cid" IS '租户id';
COMMENT ON COLUMN "public"."t_dep_emp"."emp_id" IS '员工';
COMMENT ON COLUMN "public"."t_dep_emp"."dep_id" IS '部门';
COMMENT ON TABLE "public"."t_dep_emp" IS '部门员工关系';

-- ----------------------------
-- Records of t_dep_emp
-- ----------------------------
BEGIN;
INSERT INTO "public"."t_dep_emp" ("id", "cid", "emp_id", "dep_id") VALUES (1, 1, 1, 21);
INSERT INTO "public"."t_dep_emp" ("id", "cid", "emp_id", "dep_id") VALUES (2, 1, 2, 21);
INSERT INTO "public"."t_dep_emp" ("id", "cid", "emp_id", "dep_id") VALUES (3, 1, 3, 21);
INSERT INTO "public"."t_dep_emp" ("id", "cid", "emp_id", "dep_id") VALUES (4, 1, 4, 21);
COMMIT;

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_department";
CREATE TABLE "public"."t_department" (
                                         "id" int8 NOT NULL,
                                         "cid" int8 NOT NULL,
                                         "pid" int8,
                                         "sort" int4 NOT NULL DEFAULT 0,
                                         "no" varchar(10) COLLATE "pg_catalog"."default",
                                         "name" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
                                         "creator" int8,
                                         "updater" int8,
                                         "create_time" timestamp(0),
                                         "update_time" timestamp(0),
                                         "status" int4 NOT NULL DEFAULT 1,
                                         "remark" varchar(100) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."t_department"."id" IS '主键';
COMMENT ON COLUMN "public"."t_department"."cid" IS '租户id';
COMMENT ON COLUMN "public"."t_department"."pid" IS '父部门';
COMMENT ON COLUMN "public"."t_department"."sort" IS '排序号';
COMMENT ON COLUMN "public"."t_department"."no" IS '部门编码';
COMMENT ON COLUMN "public"."t_department"."name" IS '部门名称';
COMMENT ON COLUMN "public"."t_department"."creator" IS '创建人';
COMMENT ON COLUMN "public"."t_department"."updater" IS '更新人';
COMMENT ON COLUMN "public"."t_department"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."t_department"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."t_department"."status" IS '状态，0：禁用，1：启用';
COMMENT ON COLUMN "public"."t_department"."remark" IS '备注';
COMMENT ON TABLE "public"."t_department" IS '部门';

-- ----------------------------
-- Records of t_department
-- ----------------------------
BEGIN;
INSERT INTO "public"."t_department" ("id", "cid", "pid", "sort", "no", "name", "creator", "updater", "create_time", "update_time", "status", "remark") VALUES (2, 1, 1, 1, 'NO02', '华东分公司', NULL, NULL, '2024-12-01 00:00:00', '2024-12-01 00:00:00', 1, NULL);
INSERT INTO "public"."t_department" ("id", "cid", "pid", "sort", "no", "name", "creator", "updater", "create_time", "update_time", "status", "remark") VALUES (3, 1, 1, 1, 'NO03', '华北分公司', NULL, NULL, '2024-12-01 00:00:00', '2024-12-01 00:00:00', 1, NULL);
INSERT INTO "public"."t_department" ("id", "cid", "pid", "sort", "no", "name", "creator", "updater", "create_time", "update_time", "status", "remark") VALUES (21, 1, 2, 1, 'NO0201', '研发部', NULL, NULL, '2024-12-01 00:00:00', '2024-12-01 00:00:00', 1, NULL);
INSERT INTO "public"."t_department" ("id", "cid", "pid", "sort", "no", "name", "creator", "updater", "create_time", "update_time", "status", "remark") VALUES (22, 1, 2, 1, 'NO0202', '产品部', NULL, NULL, '2024-12-01 00:00:00', '2024-12-01 00:00:00', 1, NULL);
INSERT INTO "public"."t_department" ("id", "cid", "pid", "sort", "no", "name", "creator", "updater", "create_time", "update_time", "status", "remark") VALUES (1, 1, NULL, 1, 'NO01', '上海志远科技', NULL, NULL, '2024-12-01 00:00:00', '2024-12-01 00:00:00', 1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_dict
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_dict";
CREATE TABLE "public"."t_dict" (
                                   "id" int8 NOT NULL,
                                   "code" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                   "name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
                                   "p_code" varchar(50) COLLATE "pg_catalog"."default",
                                   "value" varchar(50) COLLATE "pg_catalog"."default",
                                   "sort" int4,
                                   "is_enable" bool DEFAULT false,
                                   "cid" int8,
                                   "create_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "public"."t_dict"."id" IS '主键';
COMMENT ON COLUMN "public"."t_dict"."code" IS '编码';
COMMENT ON COLUMN "public"."t_dict"."name" IS '名称';
COMMENT ON COLUMN "public"."t_dict"."p_code" IS '父编码';
COMMENT ON COLUMN "public"."t_dict"."value" IS '值';
COMMENT ON COLUMN "public"."t_dict"."sort" IS '排序字段';
COMMENT ON COLUMN "public"."t_dict"."is_enable" IS '是否启用';
COMMENT ON COLUMN "public"."t_dict"."create_time" IS '创建时间';
COMMENT ON TABLE "public"."t_dict" IS '数据字典表';

-- ----------------------------
-- Records of t_dict
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_emp_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_emp_role";
CREATE TABLE "public"."t_emp_role" (
                                       "id" int8 NOT NULL,
                                       "cid" int8 NOT NULL,
                                       "role_id" int8 NOT NULL,
                                       "emp_id" int8 NOT NULL
)
;
COMMENT ON COLUMN "public"."t_emp_role"."id" IS '主键';
COMMENT ON COLUMN "public"."t_emp_role"."cid" IS '租户id';
COMMENT ON COLUMN "public"."t_emp_role"."role_id" IS '角色';
COMMENT ON COLUMN "public"."t_emp_role"."emp_id" IS '员工';
COMMENT ON TABLE "public"."t_emp_role" IS '员工角色关系';

-- ----------------------------
-- Records of t_emp_role
-- ----------------------------
BEGIN;
INSERT INTO "public"."t_emp_role" ("id", "cid", "role_id", "emp_id") VALUES (1, 1, 1, 1);
INSERT INTO "public"."t_emp_role" ("id", "cid", "role_id", "emp_id") VALUES (2, 1, 2, 2);
INSERT INTO "public"."t_emp_role" ("id", "cid", "role_id", "emp_id") VALUES (3, 1, 3, 3);
INSERT INTO "public"."t_emp_role" ("id", "cid", "role_id", "emp_id") VALUES (4, 1, 4, 4);
COMMIT;

-- ----------------------------
-- Table structure for t_employee
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_employee";
CREATE TABLE "public"."t_employee" (
                                       "id" int8 NOT NULL,
                                       "cid" int8 NOT NULL,
                                       "user_id" int8,
                                       "no" varchar(10) COLLATE "pg_catalog"."default",
                                       "name" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
                                       "email" varchar(20) COLLATE "pg_catalog"."default",
                                       "creator" int8,
                                       "updater" int8,
                                       "deleter" int8,
                                       "create_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                       "update_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                       "delete_time" timestamp(0),
                                       "is_deleted" bool NOT NULL DEFAULT false,
                                       "is_corp_admin" bool NOT NULL DEFAULT false
)
;
COMMENT ON COLUMN "public"."t_employee"."id" IS '主键';
COMMENT ON COLUMN "public"."t_employee"."cid" IS '租户ID';
COMMENT ON COLUMN "public"."t_employee"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."t_employee"."no" IS '员工号';
COMMENT ON COLUMN "public"."t_employee"."name" IS '姓名';
COMMENT ON COLUMN "public"."t_employee"."email" IS '邮箱';
COMMENT ON COLUMN "public"."t_employee"."creator" IS '创建人';
COMMENT ON COLUMN "public"."t_employee"."updater" IS '更新人';
COMMENT ON COLUMN "public"."t_employee"."deleter" IS '删除人';
COMMENT ON COLUMN "public"."t_employee"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."t_employee"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."t_employee"."delete_time" IS '删除时间';
COMMENT ON COLUMN "public"."t_employee"."is_deleted" IS '是否已删除';
COMMENT ON COLUMN "public"."t_employee"."is_corp_admin" IS '是否为租户管理员';
COMMENT ON TABLE "public"."t_employee" IS '员工';

-- ----------------------------
-- Records of t_employee
-- ----------------------------
BEGIN;
INSERT INTO "public"."t_employee" ("id", "cid", "user_id", "no", "name", "email", "creator", "updater", "deleter", "create_time", "update_time", "delete_time", "is_deleted", "is_corp_admin") VALUES (1, 1, 11, 'E001', '管理员', 'admin@163.com', NULL, NULL, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', NULL, 'f', 't');
INSERT INTO "public"."t_employee" ("id", "cid", "user_id", "no", "name", "email", "creator", "updater", "deleter", "create_time", "update_time", "delete_time", "is_deleted", "is_corp_admin") VALUES (2, 1, 12, 'E002', '业务员', 'yw@163.com', NULL, NULL, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', NULL, 'f', 't');
INSERT INTO "public"."t_employee" ("id", "cid", "user_id", "no", "name", "email", "creator", "updater", "deleter", "create_time", "update_time", "delete_time", "is_deleted", "is_corp_admin") VALUES (3, 1, 13, 'E003', '运营', 'yy@163.com', NULL, NULL, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', NULL, 'f', 't');
INSERT INTO "public"."t_employee" ("id", "cid", "user_id", "no", "name", "email", "creator", "updater", "deleter", "create_time", "update_time", "delete_time", "is_deleted", "is_corp_admin") VALUES (4, 1, 14, 'E004', '审计员', 'sj@163.com', NULL, NULL, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', NULL, 'f', 't');
COMMIT;

-- ----------------------------
-- Table structure for t_file
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_file";
CREATE TABLE "public"."t_file" (
                                   "id" int8 NOT NULL,
                                   "code" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                   "name" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                   "ext" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
                                   "data" bytea,
                                   "origin_name" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                   "create_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "public"."t_file"."id" IS '主键';
COMMENT ON COLUMN "public"."t_file"."code" IS '编码';
COMMENT ON COLUMN "public"."t_file"."name" IS '名称';
COMMENT ON COLUMN "public"."t_file"."ext" IS '扩展名';
COMMENT ON COLUMN "public"."t_file"."data" IS '数据';
COMMENT ON COLUMN "public"."t_file"."origin_name" IS '原始名称';
COMMENT ON COLUMN "public"."t_file"."create_time" IS '创建时间';
COMMENT ON TABLE "public"."t_file" IS '文件';

-- ----------------------------
-- Records of t_file
-- ----------------------------


-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_role";
CREATE TABLE "public"."t_role" (
                                   "id" int8 NOT NULL,
                                   "cid" int8 NOT NULL,
                                   "name" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
                                   "remark" varchar(100) COLLATE "pg_catalog"."default",
                                   "creator" int8,
                                   "updater" int8,
                                   "create_time" timestamp(0) NOT NULL,
                                   "update_time" timestamp(0) NOT NULL,
                                   "is_enable" bool DEFAULT true,
                                   "status" varchar COLLATE "pg_catalog"."default" DEFAULT 1
)
;
COMMENT ON COLUMN "public"."t_role"."id" IS '主键';
COMMENT ON COLUMN "public"."t_role"."cid" IS 'cid';
COMMENT ON COLUMN "public"."t_role"."name" IS '名称';
COMMENT ON COLUMN "public"."t_role"."remark" IS '备注';
COMMENT ON COLUMN "public"."t_role"."creator" IS '创建人';
COMMENT ON COLUMN "public"."t_role"."updater" IS '更新人';
COMMENT ON COLUMN "public"."t_role"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."t_role"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."t_role"."is_enable" IS '是否可用';
COMMENT ON COLUMN "public"."t_role"."status" IS '启用状态，0：禁用，1：启用';
COMMENT ON TABLE "public"."t_role" IS '角色';

-- ----------------------------
-- Records of t_role
-- ----------------------------
BEGIN;
INSERT INTO "public"."t_role" ("id", "cid", "name", "remark", "creator", "updater", "create_time", "update_time", "is_enable", "status") VALUES (1, 1, '企业管理员', '企业管理员', NULL, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', 't', '1');
INSERT INTO "public"."t_role" ("id", "cid", "name", "remark", "creator", "updater", "create_time", "update_time", "is_enable", "status") VALUES (2, 1, '业务员', '业务员', NULL, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', 't', '1');
INSERT INTO "public"."t_role" ("id", "cid", "name", "remark", "creator", "updater", "create_time", "update_time", "is_enable", "status") VALUES (3, 1, '运营', '运营', NULL, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', 't', '1');
INSERT INTO "public"."t_role" ("id", "cid", "name", "remark", "creator", "updater", "create_time", "update_time", "is_enable", "status") VALUES (4, 1, '审计员', '审计员', NULL, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', 't', '1');
COMMIT;

-- ----------------------------
-- Table structure for t_role_app_resource
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_role_app_resource";
CREATE TABLE "public"."t_role_app_resource" (
                                                "id" int8 NOT NULL,
                                                "cid" int8 NOT NULL,
                                                "role_id" int8 NOT NULL,
                                                "app_resource_id" int8 NOT NULL,
                                                "app_id" int8 NOT NULL
)
;
COMMENT ON COLUMN "public"."t_role_app_resource"."id" IS '主键';
COMMENT ON COLUMN "public"."t_role_app_resource"."cid" IS '租户';
COMMENT ON COLUMN "public"."t_role_app_resource"."role_id" IS '角色';
COMMENT ON COLUMN "public"."t_role_app_resource"."app_resource_id" IS '资源';
COMMENT ON COLUMN "public"."t_role_app_resource"."app_id" IS '应用id';
COMMENT ON TABLE "public"."t_role_app_resource" IS '角色和应用资源关系';

-- ----------------------------
-- Records of t_role_app_resource
-- ----------------------------
BEGIN;
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119424, 1, 3, 10111512, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119425, 1, 3, 10131800, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119426, 1, 3, 10111513, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119427, 1, 3, 10121311, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119428, 1, 3, 10131100, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119429, 1, 3, 10130000, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119430, 1, 3, 10131600, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119431, 1, 3, 10170000, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119432, 1, 3, 10210000, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119433, 1, 3, 10111511, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119434, 1, 3, 10121111, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119435, 1, 3, 10111700, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119436, 1, 3, 10121300, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119437, 1, 3, 10131400, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119438, 1, 3, 10111500, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119439, 1, 3, 10121100, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119440, 1, 3, 10120000, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119441, 1, 3, 10131200, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119442, 1, 3, 10160000, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119443, 1, 3, 10200000, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119444, 1, 3, 10111300, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119445, 1, 3, 10121211, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119446, 1, 3, 10111100, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119447, 1, 3, 10121212, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119448, 1, 3, 10131900, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119449, 1, 3, 10121213, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119450, 1, 3, 10110000, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119451, 1, 3, 10111600, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119452, 1, 3, 10121200, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119453, 1, 3, 10150000, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119454, 1, 3, 10190000, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119455, 1, 3, 10131700, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119456, 1, 3, 10111400, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119457, 1, 3, 10131500, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119458, 1, 3, 10111200, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119459, 1, 3, 10121312, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119460, 1, 3, 10132000, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119461, 1, 3, 10140000, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119462, 1, 3, 10220000, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119463, 1, 3, 10121313, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119464, 1, 3, 10180000, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119465, 1, 3, 10131300, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264852598784, 1, 1, 10210000, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264860987392, 1, 1, 10111500, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264860987393, 1, 1, 10121111, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264860987394, 1, 1, 10131900, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264865181696, 1, 1, 10132000, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264865181697, 1, 1, 10120000, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264869376000, 1, 1, 10150000, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264869376001, 1, 1, 10121100, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264869376002, 1, 1, 10121211, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264869376003, 1, 1, 10121200, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264869376004, 1, 1, 10121212, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264873570304, 1, 1, 10121213, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854127321214193664, 1, 1851579091070750720, 1853276499281575936, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854127321214193670, 1, 1851579091070750720, 1853279166070390784, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264873570305, 1, 1, 10121311, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264873570306, 1, 1, 10121300, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264873570307, 1, 1, 10121312, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264877764608, 1, 1, 10121313, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264877764609, 1, 1, 10131100, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264877764610, 1, 1, 10131200, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264881958912, 1, 1, 10160000, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264881958913, 1, 1, 10110000, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264881958914, 1, 1, 10170000, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264881958915, 1, 1, 10180000, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264881958916, 1, 1, 10190000, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264886153216, 1, 1, 10111600, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264886153217, 1, 1, 10111700, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264886153218, 1, 1, 10140000, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264886153219, 1, 1, 10131300, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264886153220, 1, 1, 10131400, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264886153221, 1, 1, 10131500, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264890347520, 1, 1, 10111300, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264890347521, 1, 1, 10111400, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264890347522, 1, 1, 10111512, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264890347523, 1, 1, 10111513, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264894541824, 1, 1, 10130000, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264894541825, 1, 1, 10131600, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264894541826, 1, 1, 10131700, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264894541827, 1, 1, 10131800, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264894541828, 1, 1, 10200000, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264898736128, 1, 1, 10111200, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264898736129, 1, 1, 10111100, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264898736130, 1, 1, 10111511, 11);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264898736131, 1, 1, 1849705342231085056, 21);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264898736132, 1, 1, 20130000, 21);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264898736133, 1, 1, 1849707326849908736, 21);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264902930432, 1, 1, 1849705663569297408, 21);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264902930433, 1, 1, 1849704675026370560, 21);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264902930434, 1, 1, 1849704802948448256, 21);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264902930435, 1, 1, 1849704978136137728, 21);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264902930436, 1, 1, 1849705202313297920, 21);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264907124736, 1, 1, 20110000, 21);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264907124737, 1, 1, 1849709734313295872, 21);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264907124738, 1, 1, 1849708988217917440, 21);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264907124739, 1, 1, 20120000, 21);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264907124740, 1, 1, 1851149525986803712, 21);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264907124741, 1, 1, 1851150425149112320, 21);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264911319040, 1, 1, 1853282926414921728, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264911319041, 1, 1, 1853695191703068672, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264911319042, 1, 1, 1853301729546862592, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264911319043, 1, 1, 1853302150814367744, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264911319044, 1, 1, 1853302362878377984, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264911319045, 1, 1, 1853304531161579520, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264915513344, 1, 1, 1853305984953483264, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264915513345, 1, 1, 1853306224016228352, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264915513346, 1, 1, 1853695911827316736, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264915513347, 1, 1, 1853302623453708288, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264915513348, 1, 1, 1853311758983364608, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264915513349, 1, 1, 1853312131890544640, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264919707648, 1, 1, 1853313930332602368, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264919707649, 1, 1, 1853314097744052224, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856300701662482432, 1, 4, 12120000, 12);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856300832851922944, 1, 2, 12110000, 12);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264919707650, 1, 1, 1853314303130730496, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264919707651, 1, 1, 1853314584279121920, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264919707652, 1, 1, 1853696467316744192, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264919707653, 1, 1, 1853313570826223616, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264923901952, 1, 1, 1853315267816456192, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264923901953, 1, 1, 1853315453607346176, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264923901954, 1, 1, 1853696735718645760, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264923901955, 1, 1, 1853315775176245248, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264923901956, 1, 1, 1853315627624824832, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854704264923901957, 1, 1, 1853270447349694464, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854115558192181248, 1, 1851579091070750720, 1853269879050862592, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854115558192181249, 1, 1851579091070750720, 1853270281762766848, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854116133130596356, 1, 1851579091070750720, 1853270447349694464, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854119142157840384, 1, 1851579091070750720, 1853695191703068672, 41);
INSERT INTO "public"."t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1854119142157840387, 1, 1851579091070750720, 1853282926414921728, 41);
COMMIT;

-- ----------------------------
-- Table structure for t_sys_log
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sys_log";
CREATE TABLE "public"."t_sys_log" (
                                      "id" int8 NOT NULL,
                                      "actor" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
                                      "actor_ip" varchar(20) COLLATE "pg_catalog"."default",
                                      "actor_type" int4 NOT NULL,
                                      "event_time" timestamp(0) NOT NULL,
                                      "event_desc" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
                                      "create_time" timestamp(0) NOT NULL,
                                      "actor_user_id" int8,
                                      "event_type" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
                                      "event_level" int4 NOT NULL DEFAULT 1,
                                      "event_result" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
                                      "source_sys" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
                                      "username" varchar(20) COLLATE "pg_catalog"."default",
                                      "cid" int8,
                                      "actor_emp_id" int8,
                                      "source_module" varchar(20) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."t_sys_log"."id" IS '主键';
COMMENT ON COLUMN "public"."t_sys_log"."actor" IS '事件触发者';
COMMENT ON COLUMN "public"."t_sys_log"."actor_ip" IS '触发者ip';
COMMENT ON COLUMN "public"."t_sys_log"."actor_type" IS '触发者类型，1：系统，2：员工';
COMMENT ON COLUMN "public"."t_sys_log"."event_time" IS '事件产生时间';
COMMENT ON COLUMN "public"."t_sys_log"."event_desc" IS '事件描述';
COMMENT ON COLUMN "public"."t_sys_log"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."t_sys_log"."actor_user_id" IS '事件触发者用户id';
COMMENT ON COLUMN "public"."t_sys_log"."event_type" IS '事件分类';
COMMENT ON COLUMN "public"."t_sys_log"."event_level" IS '事件等级';
COMMENT ON COLUMN "public"."t_sys_log"."event_result" IS '事件结果';
COMMENT ON COLUMN "public"."t_sys_log"."source_sys" IS '事件产生的系统';
COMMENT ON COLUMN "public"."t_sys_log"."username" IS '触发者的用户名';
COMMENT ON COLUMN "public"."t_sys_log"."cid" IS '租户id';
COMMENT ON COLUMN "public"."t_sys_log"."actor_emp_id" IS '触发者员工id';
COMMENT ON COLUMN "public"."t_sys_log"."source_module" IS '事件产生的模块';
COMMENT ON TABLE "public"."t_sys_log" IS '系统日志';

-- ----------------------------
-- Records of t_sys_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_user";
CREATE TABLE "public"."t_user" (
                                   "id" int8 NOT NULL,
                                   "name" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
                                   "gender" int4 NOT NULL DEFAULT 0,
                                   "avatar_url" varchar(100) COLLATE "pg_catalog"."default",
                                   "phone_num" varchar(20) COLLATE "pg_catalog"."default",
                                   "username" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
                                   "pwd" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
                                   "status" int4 NOT NULL DEFAULT 1,
                                   "regtime" timestamp(0) NOT NULL,
                                   "last_login_time" timestamp(0),
                                   "creator" int8,
                                   "updater" int8,
                                   "deleter" int8,
                                   "create_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   "update_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   "delete_time" timestamp(0),
                                   "is_deleted" bool NOT NULL DEFAULT false,
                                   "is_sys_admin" bool NOT NULL DEFAULT false
)
;
COMMENT ON COLUMN "public"."t_user"."id" IS '主键';
COMMENT ON COLUMN "public"."t_user"."name" IS '名称';
COMMENT ON COLUMN "public"."t_user"."gender" IS '性别，0：未知，1：男，2：女';
COMMENT ON COLUMN "public"."t_user"."avatar_url" IS '头像';
COMMENT ON COLUMN "public"."t_user"."phone_num" IS '手机号码';
COMMENT ON COLUMN "public"."t_user"."username" IS '用户名';
COMMENT ON COLUMN "public"."t_user"."pwd" IS '密码';
COMMENT ON COLUMN "public"."t_user"."status" IS '状态，0：禁用，1：启用';
COMMENT ON COLUMN "public"."t_user"."regtime" IS '注册时间';
COMMENT ON COLUMN "public"."t_user"."last_login_time" IS '最后登录时间';
COMMENT ON COLUMN "public"."t_user"."creator" IS '创建人';
COMMENT ON COLUMN "public"."t_user"."updater" IS '更新人';
COMMENT ON COLUMN "public"."t_user"."deleter" IS '删除人';
COMMENT ON COLUMN "public"."t_user"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."t_user"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."t_user"."delete_time" IS '删除时间';
COMMENT ON COLUMN "public"."t_user"."is_deleted" IS '是否已删除';
COMMENT ON COLUMN "public"."t_user"."is_sys_admin" IS '是否为系统管理员';
COMMENT ON TABLE "public"."t_user" IS '用户';

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO "public"."t_user" ("id", "name", "gender", "avatar_url", "phone_num", "username", "pwd", "status", "regtime", "last_login_time", "creator", "updater", "deleter", "create_time", "update_time", "delete_time", "is_deleted", "is_sys_admin") VALUES (2, '系统管理员', 1, '/unpapi/image/090003179b164eeca6b7dae3bf619379.jpeg', '13488888888', 'admin', '{bcrypt}$2a$10$YtvFLcaEdnv4kWM62eHpm.8C2xm9CSLUzsN53JzenyxcroaqeblWy', 1, '2024-12-01 00:00:00', '2024-12-01 00:00:00', NULL, 1, NULL, '2024-12-01 00:00:00', '2024-12-01 00:00:00', NULL, 'f', 't');
INSERT INTO "public"."t_user" ("id", "name", "gender", "avatar_url", "phone_num", "username", "pwd", "status", "regtime", "last_login_time", "creator", "updater", "deleter", "create_time", "update_time", "delete_time", "is_deleted", "is_sys_admin") VALUES (1, '超级管理员', 1, '/unpapi/image/090003179b164eeca6b7dae3bf619379.jpeg', '13466666666', 'superadmin', '{bcrypt}$2a$10$YtvFLcaEdnv4kWM62eHpm.8C2xm9CSLUzsN53JzenyxcroaqeblWy', 1, '2024-12-01 00:00:00', '2024-12-01 00:00:00', NULL, 1, NULL, '2024-12-01 00:00:00', '2024-12-01 00:00:00', NULL, 'f', 't');
INSERT INTO "public"."t_user" ("id", "name", "gender", "avatar_url", "phone_num", "username", "pwd", "status", "regtime", "last_login_time", "creator", "updater", "deleter", "create_time", "update_time", "delete_time", "is_deleted", "is_sys_admin") VALUES (12, '业务员', 1, '/unpapi/image/23cc6f77dd9d490182be03ba393e672b.jpeg', '13400000002', 'useryw', '{bcrypt}$2a$10$YtvFLcaEdnv4kWM62eHpm.8C2xm9CSLUzsN53JzenyxcroaqeblWy', 1, '2024-12-01 00:00:00', '2024-12-01 00:00:00', NULL, 1, NULL, '2024-12-01 00:00:00', '2024-12-01 00:00:00', NULL, 'f', 'f');
INSERT INTO "public"."t_user" ("id", "name", "gender", "avatar_url", "phone_num", "username", "pwd", "status", "regtime", "last_login_time", "creator", "updater", "deleter", "create_time", "update_time", "delete_time", "is_deleted", "is_sys_admin") VALUES (13, '运营', 1, '/unpapi/image/23cc6f77dd9d490182be03ba393e672b.jpeg', '13400000003', 'useryy', '{bcrypt}$2a$10$YtvFLcaEdnv4kWM62eHpm.8C2xm9CSLUzsN53JzenyxcroaqeblWy', 1, '2024-12-01 00:00:00', '2024-12-01 00:00:00', NULL, 1, NULL, '2024-12-01 00:00:00', '2024-12-01 00:00:00', NULL, 'f', 'f');
INSERT INTO "public"."t_user" ("id", "name", "gender", "avatar_url", "phone_num", "username", "pwd", "status", "regtime", "last_login_time", "creator", "updater", "deleter", "create_time", "update_time", "delete_time", "is_deleted", "is_sys_admin") VALUES (14, '审计员', 0, '/unpapi/image/f0531da94c93447a9f7398dabba6474c.jpg', '13400000004', 'usersj', '{bcrypt}$2a$10$YtvFLcaEdnv4kWM62eHpm.8C2xm9CSLUzsN53JzenyxcroaqeblWy', 1, '2024-12-01 00:00:00', '2024-12-01 00:00:00', NULL, 1, NULL, '2024-12-01 00:00:00', '2024-12-01 00:00:00', NULL, 'f', 'f');
INSERT INTO "public"."t_user" ("id", "name", "gender", "avatar_url", "phone_num", "username", "pwd", "status", "regtime", "last_login_time", "creator", "updater", "deleter", "create_time", "update_time", "delete_time", "is_deleted", "is_sys_admin") VALUES (11, '企业管理员', 1, '/unpapi/image/2c924149ddfe4bd181959ee9bede10c0.jpeg', '13400000001', 'corpadmin', '{bcrypt}$2a$10$YtvFLcaEdnv4kWM62eHpm.8C2xm9CSLUzsN53JzenyxcroaqeblWy', 1, '2024-12-01 00:00:00', '2024-12-01 00:00:00', NULL, 1, NULL, '2024-12-01 00:00:00', '2024-12-01 00:00:00', NULL, 'f', 'f');
COMMIT;

-- ----------------------------
-- Primary Key structure for table oauth2_authorization
-- ----------------------------
ALTER TABLE "public"."oauth2_authorization" ADD CONSTRAINT "oauth2_authorization_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table oauth2_authorization_consent
-- ----------------------------
ALTER TABLE "public"."oauth2_authorization_consent" ADD CONSTRAINT "oauth2_authorization_consent_pkey" PRIMARY KEY ("registered_client_id", "principal_name");

-- ----------------------------
-- Primary Key structure for table oauth2_registered_client
-- ----------------------------
ALTER TABLE "public"."oauth2_registered_client" ADD CONSTRAINT "oauth2_registered_client_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_app
-- ----------------------------
ALTER TABLE "public"."t_app" ADD CONSTRAINT "t_app_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table t_app_resource
-- ----------------------------
ALTER TABLE "public"."t_app_resource" ADD CONSTRAINT "uk_app_id_code" UNIQUE ("app_id", "code");
COMMENT ON CONSTRAINT "uk_app_id_code" ON "public"."t_app_resource" IS '资源编码唯一';

-- ----------------------------
-- Primary Key structure for table t_app_resource
-- ----------------------------
ALTER TABLE "public"."t_app_resource" ADD CONSTRAINT "t_resource_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_area
-- ----------------------------
CREATE INDEX "area_idx_p_code" ON "public"."t_area" USING btree (
                                                                 "p_code" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
    );

-- ----------------------------
-- Uniques structure for table t_area
-- ----------------------------
ALTER TABLE "public"."t_area" ADD CONSTRAINT "area_uk_code" UNIQUE ("code");

-- ----------------------------
-- Primary Key structure for table t_area
-- ----------------------------
ALTER TABLE "public"."t_area" ADD CONSTRAINT "t_area_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table t_config
-- ----------------------------
ALTER TABLE "public"."t_config" ADD CONSTRAINT "config_uk_cid_code" UNIQUE ("cid", "code");

-- ----------------------------
-- Primary Key structure for table t_config
-- ----------------------------
ALTER TABLE "public"."t_config" ADD CONSTRAINT "t_config_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_corp
-- ----------------------------
ALTER TABLE "public"."t_corp" ADD CONSTRAINT "t_corp_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table t_dep_emp
-- ----------------------------
ALTER TABLE "public"."t_dep_emp" ADD CONSTRAINT "uk_dep_emp_ed" UNIQUE ("emp_id", "dep_id");

-- ----------------------------
-- Primary Key structure for table t_dep_emp
-- ----------------------------
ALTER TABLE "public"."t_dep_emp" ADD CONSTRAINT "t_emp_dep_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table t_department
-- ----------------------------
ALTER TABLE "public"."t_department" ADD CONSTRAINT "uk_dep_cn" UNIQUE ("cid", "no");
COMMENT ON CONSTRAINT "uk_dep_cn" ON "public"."t_department" IS '企业内部门编号唯一';

-- ----------------------------
-- Primary Key structure for table t_department
-- ----------------------------
ALTER TABLE "public"."t_department" ADD CONSTRAINT "t_department_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table t_dict
-- ----------------------------
ALTER TABLE "public"."t_dict" ADD CONSTRAINT "uk_t_dict_code" UNIQUE ("code");

-- ----------------------------
-- Primary Key structure for table t_dict
-- ----------------------------
ALTER TABLE "public"."t_dict" ADD CONSTRAINT "t_dict_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table t_emp_role
-- ----------------------------
ALTER TABLE "public"."t_emp_role" ADD CONSTRAINT "uk_emp_role_re" UNIQUE ("role_id", "emp_id");

-- ----------------------------
-- Primary Key structure for table t_emp_role
-- ----------------------------
ALTER TABLE "public"."t_emp_role" ADD CONSTRAINT "t_role_emp_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table t_employee
-- ----------------------------
ALTER TABLE "public"."t_employee" ADD CONSTRAINT "uk_emp_cu" UNIQUE ("cid", "user_id");
COMMENT ON CONSTRAINT "uk_emp_cu" ON "public"."t_employee" IS '企业下员工唯一';

-- ----------------------------
-- Primary Key structure for table t_employee
-- ----------------------------
ALTER TABLE "public"."t_employee" ADD CONSTRAINT "t_employee_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table t_file
-- ----------------------------
ALTER TABLE "public"."t_file" ADD CONSTRAINT "uk_code" UNIQUE ("code");

-- ----------------------------
-- Primary Key structure for table t_file
-- ----------------------------
ALTER TABLE "public"."t_file" ADD CONSTRAINT "t_file_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_role
-- ----------------------------
ALTER TABLE "public"."t_role" ADD CONSTRAINT "t_role_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table t_role_app_resource
-- ----------------------------
ALTER TABLE "public"."t_role_app_resource" ADD CONSTRAINT "uk_role_app_resource_ra" UNIQUE ("role_id", "app_resource_id");
COMMENT ON CONSTRAINT "uk_role_app_resource_ra" ON "public"."t_role_app_resource" IS '角色与资源关系绑定唯一';

-- ----------------------------
-- Primary Key structure for table t_role_app_resource
-- ----------------------------
ALTER TABLE "public"."t_role_app_resource" ADD CONSTRAINT "t_role_app_resource_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_sys_log
-- ----------------------------
ALTER TABLE "public"."t_sys_log" ADD CONSTRAINT "t_sys_log_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table t_user
-- ----------------------------
ALTER TABLE "public"."t_user" ADD CONSTRAINT "uk_user_username" UNIQUE ("username", "is_deleted");
ALTER TABLE "public"."t_user" ADD CONSTRAINT "uk_user_phone_num" UNIQUE ("phone_num", "is_deleted");
COMMENT ON CONSTRAINT "uk_user_username" ON "public"."t_user" IS '用户名唯一';
COMMENT ON CONSTRAINT "uk_user_phone_num" ON "public"."t_user" IS '手机号唯一';

-- ----------------------------
-- Primary Key structure for table t_user
-- ----------------------------
ALTER TABLE "public"."t_user" ADD CONSTRAINT "t_user_pkey" PRIMARY KEY ("id");
