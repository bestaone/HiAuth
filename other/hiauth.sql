
-- ----------------------------
-- Table structure for oauth2_authorization
-- ----------------------------
DROP TABLE IF EXISTS "oauth2_authorization";
CREATE TABLE "oauth2_authorization" (
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
COMMENT ON TABLE "oauth2_authorization" IS 'oauth2认证';

-- ----------------------------
-- Table structure for oauth2_authorization_consent
-- ----------------------------
DROP TABLE IF EXISTS "oauth2_authorization_consent";
CREATE TABLE "oauth2_authorization_consent" (
  "registered_client_id" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "principal_name" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "authorities" varchar(1000) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON TABLE "oauth2_authorization_consent" IS 'oauth2认证授权';

-- ----------------------------
-- Table structure for oauth2_registered_client
-- ----------------------------
DROP TABLE IF EXISTS "oauth2_registered_client";
CREATE TABLE "oauth2_registered_client" (
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
COMMENT ON COLUMN "oauth2_registered_client"."app_id" IS '应用id（自定义扩展字段）';
COMMENT ON COLUMN "oauth2_registered_client"."cid" IS '租户id（自定义扩展字段）';
COMMENT ON TABLE "oauth2_registered_client" IS 'oauth2客户端';

-- ----------------------------
-- Records of oauth2_registered_client
-- ----------------------------
BEGIN;
INSERT INTO "oauth2_registered_client" ("id", "client_id", "client_id_issued_at", "client_secret", "client_secret_expires_at", "client_name", "client_authentication_methods", "authorization_grant_types", "redirect_uris", "post_logout_redirect_uris", "scopes", "client_settings", "token_settings", "app_id", "cid") VALUES ('abcd-12', 'zhiyuan-admin', '2025-01-01 00:00:00', '{bcrypt}$2a$10$L/QEiqOTG3V8ktKMbBLR0..oaeGXHqAdT5aZ3tRdrMFqS87zCT.yu', NULL, '志远管理系统', 'client_secret_basic,client_secret_post', 'refresh_token,authorization_code,client_credentials,password', 'http://dcss-dev.vking.fun:31080/apisvc-admin/oauth2/token/redirect,http://127.0.0.1:9002/oauth2/token/redirect', 'http://127.0.0.1:8080/', 'openid,profile,user', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-authorization-consent":true,"settings.client.require-proof-key":false,"cid":"1","appId":"12"}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",36000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",2592000],"settings.token.authorization-code-time-to-live":["java.time.Duration",600],"settings.token.device-code-time-to-live":["java.time.Duration",300]}', 12, 1);
INSERT INTO "oauth2_registered_client" ("id", "client_id", "client_id_issued_at", "client_secret", "client_secret_expires_at", "client_name", "client_authentication_methods", "authorization_grant_types", "redirect_uris", "post_logout_redirect_uris", "scopes", "client_settings", "token_settings", "app_id", "cid") VALUES ('abcd-11', 'zhiyuan', '2025-01-01 00:00:00', '{bcrypt}$2a$10$L/QEiqOTG3V8ktKMbBLR0..oaeGXHqAdT5aZ3tRdrMFqS87zCT.yu', NULL, '志远业务系统', 'client_secret_basic,client_secret_post', 'refresh_token,authorization_code,client_credentials,password', 'http://dcss-dev.vking.fun:31080/apisvc-dcss/oauth2/token/redirect,http://127.0.0.1:9001/oauth2/token/redirect', 'http://127.0.0.1:8080/', 'openid,profile,user', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-authorization-consent":true,"settings.client.require-proof-key":false,"cid":"1","appId":"11"}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",36000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",2592000],"settings.token.authorization-code-time-to-live":["java.time.Duration",600],"settings.token.device-code-time-to-live":["java.time.Duration",300]}', 11, 1);
INSERT INTO "oauth2_registered_client" ("id", "client_id", "client_id_issued_at", "client_secret", "client_secret_expires_at", "client_name", "client_authentication_methods", "authorization_grant_types", "redirect_uris", "post_logout_redirect_uris", "scopes", "client_settings", "token_settings", "app_id", "cid") VALUES ('abcd-13', 'zhiyuan-oms', '2025-01-01 00:00:00', '{bcrypt}$2a$10$L/QEiqOTG3V8ktKMbBLR0..oaeGXHqAdT5aZ3tRdrMFqS87zCT.yu', NULL, '志远运营系统', 'client_secret_basic,client_secret_post', 'refresh_token,authorization_code,client_credentials,password', 'http://dcss-dev.vking.fun:31080/apisvc-oms/oauth2/token/redirect,http://127.0.0.1:4000/oauth2/token/redirect', 'http://127.0.0.1:8080/', 'openid,profile,user', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-authorization-consent":true,"settings.client.require-proof-key":false,"cid":"1","appId":"13"}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",36000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",2592000],"settings.token.authorization-code-time-to-live":["java.time.Duration",600],"settings.token.device-code-time-to-live":["java.time.Duration",300]}', 13, 1);
INSERT INTO "oauth2_registered_client" ("id", "client_id", "client_id_issued_at", "client_secret", "client_secret_expires_at", "client_name", "client_authentication_methods", "authorization_grant_types", "redirect_uris", "post_logout_redirect_uris", "scopes", "client_settings", "token_settings", "app_id", "cid") VALUES ('abcd-91', 'himall', '2025-01-01 00:00:00', '{bcrypt}$2a$10$L/QEiqOTG3V8ktKMbBLR0..oaeGXHqAdT5aZ3tRdrMFqS87zCT.yu', NULL, 'HIMALL', 'client_secret_basic,client_secret_post', 'refresh_token,authorization_code,client_credentials,password', 'http://himall.hiauth.com/login/oauth2/code/hiauth-code,http://127.0.0.1:9000/login/oauth2/code/hiauth-code,http://127.0.0.1:9000/oauth2/token/redirect,http://127.0.0.1:9000/home', 'http://127.0.0.1:8080/', 'openid,profile,user', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-authorization-consent":true,"settings.client.require-proof-key":false,"cid":"1","appId":"91"}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",36000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",2592000],"settings.token.authorization-code-time-to-live":["java.time.Duration",600],"settings.token.device-code-time-to-live":["java.time.Duration",300]}', 91, 1);
COMMIT;

-- ----------------------------
-- Table structure for t_app
-- ----------------------------
DROP TABLE IF EXISTS "t_app";
CREATE TABLE "t_app" (
  "id" int8 NOT NULL,
  "cid" int8,
  "name" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "icon" varchar(200) COLLATE "pg_catalog"."default",
  "create_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "creator" int8,
  "remark" varchar(200) COLLATE "pg_catalog"."default",
  "home" varchar(100) COLLATE "pg_catalog"."default",
  "extend" jsonb
)
;
COMMENT ON COLUMN "t_app"."id" IS '主键';
COMMENT ON COLUMN "t_app"."cid" IS '创建应用的企业CID';
COMMENT ON COLUMN "t_app"."name" IS '名称';
COMMENT ON COLUMN "t_app"."icon" IS '图标';
COMMENT ON COLUMN "t_app"."create_time" IS '创建时间';
COMMENT ON COLUMN "t_app"."creator" IS '创建人';
COMMENT ON COLUMN "t_app"."remark" IS '说明';
COMMENT ON COLUMN "t_app"."home" IS '主页地址';
COMMENT ON COLUMN "t_app"."extend" IS '扩展字段';
COMMENT ON TABLE "t_app" IS '应用';

-- ----------------------------
-- Records of t_app
-- ----------------------------
BEGIN;
INSERT INTO "t_app" ("id", "cid", "name", "icon", "create_time", "creator", "remark", "home", "extend") VALUES (11, NULL, '志远业务系统', '/unpapi/image/7af4deea5db94394b46690c1f14f9a0d.jpg', '2025-01-01 00:00:00', 1, '志远业务系统', NULL, NULL);
INSERT INTO "t_app" ("id", "cid", "name", "icon", "create_time", "creator", "remark", "home", "extend") VALUES (12, NULL, '志远管理系统', '/unpapi/image/ded337a3c4064cff8f7e6a2d607aac2e.jpeg', '2025-01-01 00:00:00', 1, '志远管理系统', NULL, NULL);
INSERT INTO "t_app" ("id", "cid", "name", "icon", "create_time", "creator", "remark", "home", "extend") VALUES (13, NULL, '志远运营系统', '/unpapi/image/de58d213858240ce83681e8194e6ece3.jpeg', '2025-01-01 00:00:00', 1, '志远运营系统', NULL, NULL);
INSERT INTO "t_app" ("id", "cid", "name", "icon", "create_time", "creator", "remark", "home", "extend") VALUES (91, NULL, 'HiMall', '/unpapi/image/f91626c1876c4ff293056efda7fef04c.jpg', '2025-01-01 00:00:00', 1, 'HiMall', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_app_resource
-- ----------------------------
DROP TABLE IF EXISTS "t_app_resource";
CREATE TABLE "t_app_resource" (
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
COMMENT ON COLUMN "t_app_resource"."id" IS '主键';
COMMENT ON COLUMN "t_app_resource"."pid" IS '父节点';
COMMENT ON COLUMN "t_app_resource"."app_id" IS '应用id';
COMMENT ON COLUMN "t_app_resource"."code" IS '编码';
COMMENT ON COLUMN "t_app_resource"."url" IS '前端地址';
COMMENT ON COLUMN "t_app_resource"."name" IS '名称';
COMMENT ON COLUMN "t_app_resource"."type" IS '资源类型，1：目录、菜单，2：页面，3：功能、接口';
COMMENT ON COLUMN "t_app_resource"."remark" IS '备注';
COMMENT ON COLUMN "t_app_resource"."extend" IS '扩展字段';
COMMENT ON COLUMN "t_app_resource"."sort" IS '排序';
COMMENT ON COLUMN "t_app_resource"."api" IS '接口地址';
COMMENT ON TABLE "t_app_resource" IS '系统资源';

-- ----------------------------
-- Records of t_app_resource
-- ----------------------------
BEGIN;
INSERT INTO "t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (12110000, NULL, 12, 'xtgl', '/xtgl', '系统管理', 1, NULL, NULL, 1, '/api/xtgl/*');
INSERT INTO "t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (12120000, NULL, 12, 'rzgl', '/rzgl', '日志管理', 1, NULL, NULL, 2, '/api/rzgl/*');
INSERT INTO "t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (13140000, NULL, 13, 'xtpz', '/xtpz', '系统配置', 1, NULL, NULL, 4, '/api/xtpz/*');
INSERT INTO "t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (13130000, NULL, 13, 'yjsbgl', '/yjsbgl', '硬件设备管理', 1, NULL, NULL, 3, '/api/yjsbgl/*');
INSERT INTO "t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (13120000, NULL, 13, 'zxtgl', '/zxtgl', '子系统管理', 1, NULL, NULL, 2, '/api/zxtgl/*');
INSERT INTO "t_app_resource" ("id", "pid", "app_id", "code", "url", "name", "type", "remark", "extend", "sort", "api") VALUES (13110000, NULL, 13, 'zsgl', '/zsgl', ' 站所管理', 1, NULL, NULL, 1, '/api/zsgl/*');
COMMIT;

-- ----------------------------
-- Table structure for t_area
-- ----------------------------
DROP TABLE IF EXISTS "t_area";
CREATE TABLE "t_area" (
  "id" int8 NOT NULL,
  "type" int4 NOT NULL,
  "code" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
  "p_code" varchar(10) COLLATE "pg_catalog"."default",
  "name" varchar(50) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "t_area"."id" IS '主键';
COMMENT ON COLUMN "t_area"."type" IS '类型：1-省，2-市，3-区';
COMMENT ON COLUMN "t_area"."code" IS '区域编码';
COMMENT ON COLUMN "t_area"."p_code" IS '父级区域编码';
COMMENT ON COLUMN "t_area"."name" IS '区域名称';
COMMENT ON TABLE "t_area" IS '行政区域';

-- ----------------------------
-- Records of t_area
-- ----------------------------
BEGIN;
INSERT INTO "t_area" ("id", "type", "code", "p_code", "name") VALUES (220000, 1, '220000', NULL, '吉林省');
INSERT INTO "t_area" ("id", "type", "code", "p_code", "name") VALUES (410000, 1, '410000', NULL, '河南省');
COMMIT;

-- ----------------------------
-- Table structure for t_config
-- ----------------------------
DROP TABLE IF EXISTS "t_config";
CREATE TABLE "t_config" (
  "id" int8 NOT NULL,
  "cid" int8 NOT NULL,
  "sort" int4 NOT NULL DEFAULT 0,
  "code" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "value" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "create_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "t_config"."id" IS '主键';
COMMENT ON COLUMN "t_config"."cid" IS 'cid';
COMMENT ON COLUMN "t_config"."sort" IS '排序';
COMMENT ON COLUMN "t_config"."code" IS '编码';
COMMENT ON COLUMN "t_config"."value" IS '值';
COMMENT ON COLUMN "t_config"."create_time" IS '创建时间';
COMMENT ON TABLE "t_config" IS '配置';

-- ----------------------------
-- Records of t_config
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_corp
-- ----------------------------
DROP TABLE IF EXISTS "t_corp";
CREATE TABLE "t_corp" (
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
COMMENT ON COLUMN "t_corp"."id" IS '主键';
COMMENT ON COLUMN "t_corp"."name" IS '名称';
COMMENT ON COLUMN "t_corp"."status" IS '状态，1：正常';
COMMENT ON COLUMN "t_corp"."creator" IS '创建人';
COMMENT ON COLUMN "t_corp"."updater" IS '更新人';
COMMENT ON COLUMN "t_corp"."deleter" IS '删除人';
COMMENT ON COLUMN "t_corp"."create_time" IS '创建时间';
COMMENT ON COLUMN "t_corp"."update_time" IS '更新时间';
COMMENT ON COLUMN "t_corp"."delete_time" IS '删除时间';
COMMENT ON COLUMN "t_corp"."is_deleted" IS '是否已删除';
COMMENT ON COLUMN "t_corp"."app_count" IS '应用数';
COMMENT ON COLUMN "t_corp"."dep_count" IS '部门数';
COMMENT ON COLUMN "t_corp"."emp_count" IS '员工数';
COMMENT ON COLUMN "t_corp"."icon" IS '图标';
COMMENT ON TABLE "t_corp" IS '租户';

-- ----------------------------
-- Records of t_corp
-- ----------------------------
BEGIN;
INSERT INTO "t_corp" ("id", "name", "status", "creator", "updater", "deleter", "create_time", "update_time", "delete_time", "is_deleted", "app_count", "dep_count", "emp_count", "icon") VALUES (2, '星途科技', 1, NULL, NULL, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', NULL, 'f', 0, 1, 1, 'https://foruda.gitee.com/avatar/1676910173490791781/395097_bestaone_1602213346.png');
INSERT INTO "t_corp" ("id", "name", "status", "creator", "updater", "deleter", "create_time", "update_time", "delete_time", "is_deleted", "app_count", "dep_count", "emp_count", "icon") VALUES (3, '天辉智能', 1, NULL, NULL, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', NULL, 'f', 0, 0, 0, NULL);
INSERT INTO "t_corp" ("id", "name", "status", "creator", "updater", "deleter", "create_time", "update_time", "delete_time", "is_deleted", "app_count", "dep_count", "emp_count", "icon") VALUES (1, '上海志远科技', 1, NULL, NULL, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', NULL, 'f', 0, 1, 1, 'https://foruda.gitee.com/avatar/1676910173490791781/395097_bestaone_1602213346.png');
COMMIT;

-- ----------------------------
-- Table structure for t_corp_app
-- ----------------------------
DROP TABLE IF EXISTS "t_corp_app";
CREATE TABLE "t_corp_app" (
  "id" int8 NOT NULL,
  "corp_id" int8 NOT NULL,
  "app_id" int8 NOT NULL
)
;
COMMENT ON COLUMN "t_corp_app"."id" IS '主键';
COMMENT ON COLUMN "t_corp_app"."corp_id" IS '租户id';
COMMENT ON COLUMN "t_corp_app"."app_id" IS '应用id';
COMMENT ON TABLE "t_corp_app" IS '租户添加的应用';

-- ----------------------------
-- Records of t_corp_app
-- ----------------------------
BEGIN;
INSERT INTO "t_corp_app" ("id", "corp_id", "app_id") VALUES (11, 1, 11);
INSERT INTO "t_corp_app" ("id", "corp_id", "app_id") VALUES (12, 1, 12);
INSERT INTO "t_corp_app" ("id", "corp_id", "app_id") VALUES (13, 1, 13);
INSERT INTO "t_corp_app" ("id", "corp_id", "app_id") VALUES (91, 1, 91);
COMMIT;

-- ----------------------------
-- Table structure for t_dep_emp
-- ----------------------------
DROP TABLE IF EXISTS "t_dep_emp";
CREATE TABLE "t_dep_emp" (
  "id" int8 NOT NULL,
  "cid" int8 NOT NULL,
  "emp_id" int8 NOT NULL,
  "dep_id" int8 NOT NULL
)
;
COMMENT ON COLUMN "t_dep_emp"."id" IS '主键';
COMMENT ON COLUMN "t_dep_emp"."cid" IS '租户id';
COMMENT ON COLUMN "t_dep_emp"."emp_id" IS '员工';
COMMENT ON COLUMN "t_dep_emp"."dep_id" IS '部门';
COMMENT ON TABLE "t_dep_emp" IS '部门员工关系';

-- ----------------------------
-- Records of t_dep_emp
-- ----------------------------
BEGIN;
INSERT INTO "t_dep_emp" ("id", "cid", "emp_id", "dep_id") VALUES (1, 1, 1, 21);
INSERT INTO "t_dep_emp" ("id", "cid", "emp_id", "dep_id") VALUES (2, 1, 2, 21);
INSERT INTO "t_dep_emp" ("id", "cid", "emp_id", "dep_id") VALUES (3, 1, 3, 21);
INSERT INTO "t_dep_emp" ("id", "cid", "emp_id", "dep_id") VALUES (4, 1, 4, 21);
COMMIT;

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS "t_department";
CREATE TABLE "t_department" (
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
COMMENT ON COLUMN "t_department"."id" IS '主键';
COMMENT ON COLUMN "t_department"."cid" IS '租户id';
COMMENT ON COLUMN "t_department"."pid" IS '父部门';
COMMENT ON COLUMN "t_department"."sort" IS '排序号';
COMMENT ON COLUMN "t_department"."no" IS '部门编码';
COMMENT ON COLUMN "t_department"."name" IS '部门名称';
COMMENT ON COLUMN "t_department"."creator" IS '创建人';
COMMENT ON COLUMN "t_department"."updater" IS '更新人';
COMMENT ON COLUMN "t_department"."create_time" IS '创建时间';
COMMENT ON COLUMN "t_department"."update_time" IS '更新时间';
COMMENT ON COLUMN "t_department"."status" IS '状态，0：禁用，1：启用';
COMMENT ON COLUMN "t_department"."remark" IS '备注';
COMMENT ON TABLE "t_department" IS '部门';

-- ----------------------------
-- Records of t_department
-- ----------------------------
BEGIN;
INSERT INTO "t_department" ("id", "cid", "pid", "sort", "no", "name", "creator", "updater", "create_time", "update_time", "status", "remark") VALUES (2, 1, 1, 1, 'NO02', '华东分公司', NULL, NULL, '2024-12-01 00:00:00', '2024-12-01 00:00:00', 1, NULL);
INSERT INTO "t_department" ("id", "cid", "pid", "sort", "no", "name", "creator", "updater", "create_time", "update_time", "status", "remark") VALUES (3, 1, 1, 1, 'NO03', '华北分公司', NULL, NULL, '2024-12-01 00:00:00', '2024-12-01 00:00:00', 1, NULL);
INSERT INTO "t_department" ("id", "cid", "pid", "sort", "no", "name", "creator", "updater", "create_time", "update_time", "status", "remark") VALUES (21, 1, 2, 1, 'NO0201', '研发部', NULL, NULL, '2024-12-01 00:00:00', '2024-12-01 00:00:00', 1, NULL);
INSERT INTO "t_department" ("id", "cid", "pid", "sort", "no", "name", "creator", "updater", "create_time", "update_time", "status", "remark") VALUES (22, 1, 2, 1, 'NO0202', '产品部', NULL, NULL, '2024-12-01 00:00:00', '2024-12-01 00:00:00', 1, NULL);
INSERT INTO "t_department" ("id", "cid", "pid", "sort", "no", "name", "creator", "updater", "create_time", "update_time", "status", "remark") VALUES (1, 1, NULL, 1, 'NO01', '上海志远科技', NULL, NULL, '2024-12-01 00:00:00', '2024-12-01 00:00:00', 1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_dict
-- ----------------------------
DROP TABLE IF EXISTS "t_dict";
CREATE TABLE "t_dict" (
  "id" int8 NOT NULL,
  "code" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "p_code" varchar(50) COLLATE "pg_catalog"."default",
  "value" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "sort" int4 NOT NULL DEFAULT 1,
  "is_enable" bool DEFAULT false,
  "cid" int8,
  "create_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "has_child" bool NOT NULL DEFAULT false
)
;
COMMENT ON COLUMN "t_dict"."id" IS '主键';
COMMENT ON COLUMN "t_dict"."code" IS '编码';
COMMENT ON COLUMN "t_dict"."name" IS '名称';
COMMENT ON COLUMN "t_dict"."p_code" IS '父编码';
COMMENT ON COLUMN "t_dict"."value" IS '值';
COMMENT ON COLUMN "t_dict"."sort" IS '排序字段';
COMMENT ON COLUMN "t_dict"."is_enable" IS '是否启用';
COMMENT ON COLUMN "t_dict"."create_time" IS '创建时间';
COMMENT ON COLUMN "t_dict"."has_child" IS '是否有子节点';
COMMENT ON TABLE "t_dict" IS '数据字典表';

-- ----------------------------
-- Records of t_dict
-- ----------------------------
BEGIN;
INSERT INTO "t_dict" ("id", "code", "name", "p_code", "value", "sort", "is_enable", "cid", "create_time", "has_child") VALUES (10000000002, 'pilot_ability', '飞手能力', NULL, '10200', 0, 't', 1, '2025-01-20 11:25:54', 'f');
INSERT INTO "t_dict" ("id", "code", "name", "p_code", "value", "sort", "is_enable", "cid", "create_time", "has_child") VALUES (10000000003, 'drone_license_type', '执照类型', NULL, '10300', 0, 't', 1, '2025-01-20 11:25:54', 'f');
INSERT INTO "t_dict" ("id", "code", "name", "p_code", "value", "sort", "is_enable", "cid", "create_time", "has_child") VALUES (10000000004, 'drone_license_level', '执照等级', NULL, '10400', 0, 't', 1, '2025-01-20 11:25:54', 'f');
INSERT INTO "t_dict" ("id", "code", "name", "p_code", "value", "sort", "is_enable", "cid", "create_time", "has_child") VALUES (10000000005, 'drone_license_model', '执照机型', NULL, '10500', 0, 't', 1, '2025-01-20 11:25:54', 'f');
INSERT INTO "t_dict" ("id", "code", "name", "p_code", "value", "sort", "is_enable", "cid", "create_time", "has_child") VALUES (10000000006, 'uav_type', '无人机类别', NULL, '10600', 0, 't', 1, '2025-01-20 11:25:54', 'f');
COMMIT;

-- ----------------------------
-- Table structure for t_emp_role
-- ----------------------------
DROP TABLE IF EXISTS "t_emp_role";
CREATE TABLE "t_emp_role" (
  "id" int8 NOT NULL,
  "cid" int8 NOT NULL,
  "role_id" int8 NOT NULL,
  "emp_id" int8 NOT NULL
)
;
COMMENT ON COLUMN "t_emp_role"."id" IS '主键';
COMMENT ON COLUMN "t_emp_role"."cid" IS '租户id';
COMMENT ON COLUMN "t_emp_role"."role_id" IS '角色';
COMMENT ON COLUMN "t_emp_role"."emp_id" IS '员工';
COMMENT ON TABLE "t_emp_role" IS '员工角色关系';

-- ----------------------------
-- Records of t_emp_role
-- ----------------------------
BEGIN;
INSERT INTO "t_emp_role" ("id", "cid", "role_id", "emp_id") VALUES (1, 1, 1, 1);
INSERT INTO "t_emp_role" ("id", "cid", "role_id", "emp_id") VALUES (2, 1, 2, 2);
INSERT INTO "t_emp_role" ("id", "cid", "role_id", "emp_id") VALUES (3, 1, 3, 3);
INSERT INTO "t_emp_role" ("id", "cid", "role_id", "emp_id") VALUES (4, 1, 4, 4);
COMMIT;

-- ----------------------------
-- Table structure for t_employee
-- ----------------------------
DROP TABLE IF EXISTS "t_employee";
CREATE TABLE "t_employee" (
  "id" int8 NOT NULL,
  "cid" int8 NOT NULL,
  "user_id" int8 NOT NULL,
  "no" varchar(10) COLLATE "pg_catalog"."default",
  "name" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "email" varchar(50) COLLATE "pg_catalog"."default",
  "creator" int8,
  "updater" int8,
  "deleter" int8,
  "create_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "update_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "delete_time" timestamp(0),
  "is_deleted" bool NOT NULL DEFAULT false,
  "is_corp_admin" bool NOT NULL DEFAULT false,
  "last_login_time" timestamp(0),
  "extend" jsonb
)
;
COMMENT ON COLUMN "t_employee"."id" IS '主键';
COMMENT ON COLUMN "t_employee"."cid" IS '租户ID';
COMMENT ON COLUMN "t_employee"."user_id" IS '用户id';
COMMENT ON COLUMN "t_employee"."no" IS '员工号';
COMMENT ON COLUMN "t_employee"."name" IS '姓名';
COMMENT ON COLUMN "t_employee"."email" IS '邮箱';
COMMENT ON COLUMN "t_employee"."creator" IS '创建人';
COMMENT ON COLUMN "t_employee"."updater" IS '更新人';
COMMENT ON COLUMN "t_employee"."deleter" IS '删除人';
COMMENT ON COLUMN "t_employee"."create_time" IS '创建时间';
COMMENT ON COLUMN "t_employee"."update_time" IS '更新时间';
COMMENT ON COLUMN "t_employee"."delete_time" IS '删除时间';
COMMENT ON COLUMN "t_employee"."is_deleted" IS '是否已删除';
COMMENT ON COLUMN "t_employee"."is_corp_admin" IS '是否为租户管理员';
COMMENT ON COLUMN "t_employee"."last_login_time" IS '最近一次登录时间';
COMMENT ON COLUMN "t_employee"."extend" IS '扩展字段';
COMMENT ON TABLE "t_employee" IS '员工';

-- ----------------------------
-- Records of t_employee
-- ----------------------------
BEGIN;
INSERT INTO "t_employee" ("id", "cid", "user_id", "no", "name", "email", "creator", "updater", "deleter", "create_time", "update_time", "delete_time", "is_deleted", "is_corp_admin", "last_login_time", "extend") VALUES (2, 1, 12, 'E002', '业务员', 'yw@163.com', NULL, NULL, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', NULL, 'f', 't', NULL, NULL);
INSERT INTO "t_employee" ("id", "cid", "user_id", "no", "name", "email", "creator", "updater", "deleter", "create_time", "update_time", "delete_time", "is_deleted", "is_corp_admin", "last_login_time", "extend") VALUES (3, 1, 13, 'E003', '运营', 'yy@163.com', NULL, NULL, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', NULL, 'f', 't', NULL, NULL);
INSERT INTO "t_employee" ("id", "cid", "user_id", "no", "name", "email", "creator", "updater", "deleter", "create_time", "update_time", "delete_time", "is_deleted", "is_corp_admin", "last_login_time", "extend") VALUES (4, 1, 14, 'E004', '审计员', 'sj@163.com', NULL, NULL, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', NULL, 'f', 't', NULL, NULL);
INSERT INTO "t_employee" ("id", "cid", "user_id", "no", "name", "email", "creator", "updater", "deleter", "create_time", "update_time", "delete_time", "is_deleted", "is_corp_admin", "last_login_time", "extend") VALUES (1, 1, 11, 'E001', '管理员', 'admin@163.com', NULL, NULL, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', NULL, 'f', 't', '2025-07-22 18:49:32', NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_file
-- ----------------------------
DROP TABLE IF EXISTS "t_file";
CREATE TABLE "t_file" (
  "id" int8 NOT NULL,
  "code" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "ext" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "data" bytea,
  "origin_name" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "create_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "t_file"."id" IS '主键';
COMMENT ON COLUMN "t_file"."code" IS '编码';
COMMENT ON COLUMN "t_file"."name" IS '名称';
COMMENT ON COLUMN "t_file"."ext" IS '扩展名';
COMMENT ON COLUMN "t_file"."data" IS '数据';
COMMENT ON COLUMN "t_file"."origin_name" IS '原始名称';
COMMENT ON COLUMN "t_file"."create_time" IS '创建时间';
COMMENT ON TABLE "t_file" IS '文件';

-- ----------------------------
-- Records of t_file
-- ----------------------------


-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS "t_role";
CREATE TABLE "t_role" (
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
COMMENT ON COLUMN "t_role"."id" IS '主键';
COMMENT ON COLUMN "t_role"."cid" IS 'cid';
COMMENT ON COLUMN "t_role"."name" IS '名称';
COMMENT ON COLUMN "t_role"."remark" IS '备注';
COMMENT ON COLUMN "t_role"."creator" IS '创建人';
COMMENT ON COLUMN "t_role"."updater" IS '更新人';
COMMENT ON COLUMN "t_role"."create_time" IS '创建时间';
COMMENT ON COLUMN "t_role"."update_time" IS '更新时间';
COMMENT ON COLUMN "t_role"."is_enable" IS '是否可用';
COMMENT ON COLUMN "t_role"."status" IS '启用状态，0：禁用，1：启用';
COMMENT ON TABLE "t_role" IS '角色';

-- ----------------------------
-- Records of t_role
-- ----------------------------
BEGIN;
INSERT INTO "t_role" ("id", "cid", "name", "remark", "creator", "updater", "create_time", "update_time", "is_enable", "status") VALUES (1, 1, '企业管理员', '企业管理员', NULL, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', 't', '1');
INSERT INTO "t_role" ("id", "cid", "name", "remark", "creator", "updater", "create_time", "update_time", "is_enable", "status") VALUES (2, 1, '业务员', '业务员', NULL, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', 't', '1');
INSERT INTO "t_role" ("id", "cid", "name", "remark", "creator", "updater", "create_time", "update_time", "is_enable", "status") VALUES (3, 1, '运营', '运营', NULL, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', 't', '1');
INSERT INTO "t_role" ("id", "cid", "name", "remark", "creator", "updater", "create_time", "update_time", "is_enable", "status") VALUES (4, 1, '审计员', '审计员', NULL, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', 't', '1');
COMMIT;

-- ----------------------------
-- Table structure for t_role_app_resource
-- ----------------------------
DROP TABLE IF EXISTS "t_role_app_resource";
CREATE TABLE "t_role_app_resource" (
  "id" int8 NOT NULL,
  "cid" int8 NOT NULL,
  "role_id" int8 NOT NULL,
  "app_resource_id" int8 NOT NULL,
  "app_id" int8 NOT NULL
)
;
COMMENT ON COLUMN "t_role_app_resource"."id" IS '主键';
COMMENT ON COLUMN "t_role_app_resource"."cid" IS '租户';
COMMENT ON COLUMN "t_role_app_resource"."role_id" IS '角色';
COMMENT ON COLUMN "t_role_app_resource"."app_resource_id" IS '资源';
COMMENT ON COLUMN "t_role_app_resource"."app_id" IS '应用id';
COMMENT ON TABLE "t_role_app_resource" IS '角色和应用资源关系';

-- ----------------------------
-- Records of t_role_app_resource
-- ----------------------------
BEGIN;
INSERT INTO "t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119424, 1, 3, 10111512, 11);
INSERT INTO "t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119425, 1, 3, 10131800, 11);
INSERT INTO "t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119426, 1, 3, 10111513, 11);
INSERT INTO "t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119427, 1, 3, 10121311, 11);
INSERT INTO "t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119428, 1, 3, 10131100, 11);
INSERT INTO "t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119429, 1, 3, 10130000, 11);
INSERT INTO "t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119430, 1, 3, 10131600, 11);
INSERT INTO "t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119431, 1, 3, 10170000, 11);
INSERT INTO "t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119432, 1, 3, 10210000, 11);
INSERT INTO "t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119433, 1, 3, 10111511, 11);
INSERT INTO "t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119434, 1, 3, 10121111, 11);
INSERT INTO "t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119435, 1, 3, 10111700, 11);
INSERT INTO "t_role_app_resource" ("id", "cid", "role_id", "app_resource_id", "app_id") VALUES (1856297430726119436, 1, 3, 10121300, 11);
COMMIT;

-- ----------------------------
-- Table structure for t_sys_log
-- ----------------------------
DROP TABLE IF EXISTS "t_sys_log";
CREATE TABLE "t_sys_log" (
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
COMMENT ON COLUMN "t_sys_log"."id" IS '主键';
COMMENT ON COLUMN "t_sys_log"."actor" IS '事件触发者';
COMMENT ON COLUMN "t_sys_log"."actor_ip" IS '触发者ip';
COMMENT ON COLUMN "t_sys_log"."actor_type" IS '触发者类型，1：系统，2：员工';
COMMENT ON COLUMN "t_sys_log"."event_time" IS '事件产生时间';
COMMENT ON COLUMN "t_sys_log"."event_desc" IS '事件描述';
COMMENT ON COLUMN "t_sys_log"."create_time" IS '创建时间';
COMMENT ON COLUMN "t_sys_log"."actor_user_id" IS '事件触发者用户id';
COMMENT ON COLUMN "t_sys_log"."event_type" IS '事件分类';
COMMENT ON COLUMN "t_sys_log"."event_level" IS '事件等级';
COMMENT ON COLUMN "t_sys_log"."event_result" IS '事件结果';
COMMENT ON COLUMN "t_sys_log"."source_sys" IS '事件产生的系统';
COMMENT ON COLUMN "t_sys_log"."username" IS '触发者的用户名';
COMMENT ON COLUMN "t_sys_log"."cid" IS '租户id';
COMMENT ON COLUMN "t_sys_log"."actor_emp_id" IS '触发者员工id';
COMMENT ON COLUMN "t_sys_log"."source_module" IS '事件产生的模块';
COMMENT ON TABLE "t_sys_log" IS '系统日志';

-- ----------------------------
-- Records of t_sys_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS "t_user";
CREATE TABLE "t_user" (
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
  "is_sys_admin" bool NOT NULL DEFAULT false,
  "registe_channel" varchar(50) COLLATE "pg_catalog"."default",
  "wx_openid" varchar(50) COLLATE "pg_catalog"."default",
  "wx_unionid" varchar(50) COLLATE "pg_catalog"."default",
  "wxmp_openid" varchar(50) COLLATE "pg_catalog"."default",
  "extend" jsonb
)
;
COMMENT ON COLUMN "t_user"."id" IS '主键';
COMMENT ON COLUMN "t_user"."name" IS '名称';
COMMENT ON COLUMN "t_user"."gender" IS '性别，0：未知，1：男，2：女';
COMMENT ON COLUMN "t_user"."avatar_url" IS '头像';
COMMENT ON COLUMN "t_user"."phone_num" IS '手机号码';
COMMENT ON COLUMN "t_user"."username" IS '用户名';
COMMENT ON COLUMN "t_user"."pwd" IS '密码';
COMMENT ON COLUMN "t_user"."status" IS '状态，0：禁用，1：启用';
COMMENT ON COLUMN "t_user"."regtime" IS '注册时间';
COMMENT ON COLUMN "t_user"."last_login_time" IS '最后登录时间';
COMMENT ON COLUMN "t_user"."creator" IS '创建人';
COMMENT ON COLUMN "t_user"."updater" IS '更新人';
COMMENT ON COLUMN "t_user"."deleter" IS '删除人';
COMMENT ON COLUMN "t_user"."create_time" IS '创建时间';
COMMENT ON COLUMN "t_user"."update_time" IS '更新时间';
COMMENT ON COLUMN "t_user"."delete_time" IS '删除时间';
COMMENT ON COLUMN "t_user"."is_deleted" IS '是否已删除';
COMMENT ON COLUMN "t_user"."is_sys_admin" IS '是否为系统管理员';
COMMENT ON COLUMN "t_user"."registe_channel" IS '注册去渠道';
COMMENT ON COLUMN "t_user"."wx_openid" IS '微信openid';
COMMENT ON COLUMN "t_user"."wx_unionid" IS '微信unionid';
COMMENT ON COLUMN "t_user"."wxmp_openid" IS '微信小程序openid';
COMMENT ON COLUMN "t_user"."extend" IS '扩展字段';
COMMENT ON TABLE "t_user" IS '用户';

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO "t_user" ("id", "name", "gender", "avatar_url", "phone_num", "username", "pwd", "status", "regtime", "last_login_time", "creator", "updater", "deleter", "create_time", "update_time", "delete_time", "is_deleted", "is_sys_admin", "registe_channel", "wx_openid", "wx_unionid", "wxmp_openid", "extend") VALUES (14, '审计员', 0, '/unpapi/image/f0531da94c93447a9f7398dabba6474c.jpg', '13400000004', 'usersj', '{bcrypt}$2a$10$YtvFLcaEdnv4kWM62eHpm.8C2xm9CSLUzsN53JzenyxcroaqeblWy', 1, '2025-01-01 00:00:00', '2025-01-01 00:00:00', NULL, 1, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', NULL, 'f', 'f', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "t_user" ("id", "name", "gender", "avatar_url", "phone_num", "username", "pwd", "status", "regtime", "last_login_time", "creator", "updater", "deleter", "create_time", "update_time", "delete_time", "is_deleted", "is_sys_admin", "registe_channel", "wx_openid", "wx_unionid", "wxmp_openid", "extend") VALUES (2, '系统管理员', 1, '/unpapi/image/090003179b164eeca6b7dae3bf619379.jpeg', '13488888888', 'admin', '{bcrypt}$2a$10$YtvFLcaEdnv4kWM62eHpm.8C2xm9CSLUzsN53JzenyxcroaqeblWy', 1, '2025-01-01 00:00:00', '2025-01-01 00:00:00', NULL, 1, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', NULL, 'f', 't', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "t_user" ("id", "name", "gender", "avatar_url", "phone_num", "username", "pwd", "status", "regtime", "last_login_time", "creator", "updater", "deleter", "create_time", "update_time", "delete_time", "is_deleted", "is_sys_admin", "registe_channel", "wx_openid", "wx_unionid", "wxmp_openid", "extend") VALUES (1, '超级管理员', 1, '/unpapi/image/090003179b164eeca6b7dae3bf619379.jpeg', '13466666666', 'superadmin', '{bcrypt}$2a$10$YtvFLcaEdnv4kWM62eHpm.8C2xm9CSLUzsN53JzenyxcroaqeblWy', 1, '2025-01-01 00:00:00', '2025-01-01 00:00:00', NULL, 1, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', NULL, 'f', 't', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "t_user" ("id", "name", "gender", "avatar_url", "phone_num", "username", "pwd", "status", "regtime", "last_login_time", "creator", "updater", "deleter", "create_time", "update_time", "delete_time", "is_deleted", "is_sys_admin", "registe_channel", "wx_openid", "wx_unionid", "wxmp_openid", "extend") VALUES (12, '业务员', 1, '/unpapi/image/23cc6f77dd9d490182be03ba393e672b.jpeg', '13400000002', 'useryw', '{bcrypt}$2a$10$YtvFLcaEdnv4kWM62eHpm.8C2xm9CSLUzsN53JzenyxcroaqeblWy', 1, '2025-01-01 00:00:00', '2025-01-01 00:00:00', NULL, 1, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', NULL, 'f', 'f', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "t_user" ("id", "name", "gender", "avatar_url", "phone_num", "username", "pwd", "status", "regtime", "last_login_time", "creator", "updater", "deleter", "create_time", "update_time", "delete_time", "is_deleted", "is_sys_admin", "registe_channel", "wx_openid", "wx_unionid", "wxmp_openid", "extend") VALUES (11, '企业管理员', 1, '/unpapi/image/2c924149ddfe4bd181959ee9bede10c0.jpeg', '13400000001', 'corpadmin', '{bcrypt}$2a$10$YtvFLcaEdnv4kWM62eHpm.8C2xm9CSLUzsN53JzenyxcroaqeblWy', 1, '2025-01-01 00:00:00', '2025-01-01 00:00:00', NULL, 1, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', NULL, 'f', 'f', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "t_user" ("id", "name", "gender", "avatar_url", "phone_num", "username", "pwd", "status", "regtime", "last_login_time", "creator", "updater", "deleter", "create_time", "update_time", "delete_time", "is_deleted", "is_sys_admin", "registe_channel", "wx_openid", "wx_unionid", "wxmp_openid", "extend") VALUES (13, '运营', 1, '/unpapi/image/23cc6f77dd9d490182be03ba393e672b.jpeg', '13400000003', 'useryy', '{bcrypt}$2a$10$YtvFLcaEdnv4kWM62eHpm.8C2xm9CSLUzsN53JzenyxcroaqeblWy', 1, '2025-01-01 00:00:00', '2025-01-01 00:00:00', NULL, 1, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00', NULL, 'f', 'f', NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Primary Key structure for table oauth2_authorization
-- ----------------------------
ALTER TABLE "oauth2_authorization" ADD CONSTRAINT "oauth2_authorization_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table oauth2_authorization_consent
-- ----------------------------
ALTER TABLE "oauth2_authorization_consent" ADD CONSTRAINT "oauth2_authorization_consent_pkey" PRIMARY KEY ("registered_client_id", "principal_name");

-- ----------------------------
-- Primary Key structure for table oauth2_registered_client
-- ----------------------------
ALTER TABLE "oauth2_registered_client" ADD CONSTRAINT "oauth2_registered_client_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_app
-- ----------------------------
ALTER TABLE "t_app" ADD CONSTRAINT "t_app_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table t_app_resource
-- ----------------------------
ALTER TABLE "t_app_resource" ADD CONSTRAINT "uk_app_id_code" UNIQUE ("app_id", "code");
COMMENT ON CONSTRAINT "uk_app_id_code" ON "t_app_resource" IS '资源编码唯一';

-- ----------------------------
-- Primary Key structure for table t_app_resource
-- ----------------------------
ALTER TABLE "t_app_resource" ADD CONSTRAINT "t_resource_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_area
-- ----------------------------
CREATE INDEX "area_idx_p_code" ON "t_area" USING btree (
  "p_code" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Uniques structure for table t_area
-- ----------------------------
ALTER TABLE "t_area" ADD CONSTRAINT "area_uk_code" UNIQUE ("code");

-- ----------------------------
-- Primary Key structure for table t_area
-- ----------------------------
ALTER TABLE "t_area" ADD CONSTRAINT "t_area_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table t_config
-- ----------------------------
ALTER TABLE "t_config" ADD CONSTRAINT "config_uk_cid_code" UNIQUE ("cid", "code");

-- ----------------------------
-- Primary Key structure for table t_config
-- ----------------------------
ALTER TABLE "t_config" ADD CONSTRAINT "t_config_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_corp
-- ----------------------------
ALTER TABLE "t_corp" ADD CONSTRAINT "t_corp_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_corp_app
-- ----------------------------
ALTER TABLE "t_corp_app" ADD CONSTRAINT "t_corp_app_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table t_dep_emp
-- ----------------------------
ALTER TABLE "t_dep_emp" ADD CONSTRAINT "uk_dep_emp_ed" UNIQUE ("emp_id", "dep_id");

-- ----------------------------
-- Primary Key structure for table t_dep_emp
-- ----------------------------
ALTER TABLE "t_dep_emp" ADD CONSTRAINT "t_emp_dep_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table t_department
-- ----------------------------
ALTER TABLE "t_department" ADD CONSTRAINT "uk_dep_cn" UNIQUE ("cid", "no");
COMMENT ON CONSTRAINT "uk_dep_cn" ON "t_department" IS '企业内部门编号唯一';

-- ----------------------------
-- Primary Key structure for table t_department
-- ----------------------------
ALTER TABLE "t_department" ADD CONSTRAINT "t_department_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table t_dict
-- ----------------------------
ALTER TABLE "t_dict" ADD CONSTRAINT "uk_t_dict_code" UNIQUE ("code", "p_code");

-- ----------------------------
-- Primary Key structure for table t_dict
-- ----------------------------
ALTER TABLE "t_dict" ADD CONSTRAINT "t_dict_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table t_emp_role
-- ----------------------------
ALTER TABLE "t_emp_role" ADD CONSTRAINT "uk_emp_role_re" UNIQUE ("role_id", "emp_id");

-- ----------------------------
-- Primary Key structure for table t_emp_role
-- ----------------------------
ALTER TABLE "t_emp_role" ADD CONSTRAINT "t_role_emp_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table t_employee
-- ----------------------------
ALTER TABLE "t_employee" ADD CONSTRAINT "uk_emp_cu" UNIQUE ("cid", "user_id");
COMMENT ON CONSTRAINT "uk_emp_cu" ON "t_employee" IS '企业下员工唯一';

-- ----------------------------
-- Primary Key structure for table t_employee
-- ----------------------------
ALTER TABLE "t_employee" ADD CONSTRAINT "t_employee_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table t_file
-- ----------------------------
ALTER TABLE "t_file" ADD CONSTRAINT "uk_code" UNIQUE ("code");

-- ----------------------------
-- Primary Key structure for table t_file
-- ----------------------------
ALTER TABLE "t_file" ADD CONSTRAINT "t_file_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_role
-- ----------------------------
ALTER TABLE "t_role" ADD CONSTRAINT "t_role_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table t_role_app_resource
-- ----------------------------
ALTER TABLE "t_role_app_resource" ADD CONSTRAINT "uk_role_app_resource_ra" UNIQUE ("role_id", "app_resource_id");
COMMENT ON CONSTRAINT "uk_role_app_resource_ra" ON "t_role_app_resource" IS '角色与资源关系绑定唯一';

-- ----------------------------
-- Primary Key structure for table t_role_app_resource
-- ----------------------------
ALTER TABLE "t_role_app_resource" ADD CONSTRAINT "t_role_app_resource_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_sys_log
-- ----------------------------
ALTER TABLE "t_sys_log" ADD CONSTRAINT "t_sys_log_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table t_user
-- ----------------------------
ALTER TABLE "t_user" ADD CONSTRAINT "uk_user_username" UNIQUE ("username", "is_deleted");
ALTER TABLE "t_user" ADD CONSTRAINT "uk_user_phone_num" UNIQUE ("phone_num", "is_deleted");
ALTER TABLE "t_user" ADD CONSTRAINT "uk_wx_unionid" UNIQUE ("wx_unionid");
ALTER TABLE "t_user" ADD CONSTRAINT "uk_wx_openid" UNIQUE ("wx_openid");
COMMENT ON CONSTRAINT "uk_user_username" ON "t_user" IS '用户名唯一';
COMMENT ON CONSTRAINT "uk_user_phone_num" ON "t_user" IS '手机号唯一';
COMMENT ON CONSTRAINT "uk_wx_unionid" ON "t_user" IS '微信unionid';
COMMENT ON CONSTRAINT "uk_wx_openid" ON "t_user" IS '微信openid';

-- ----------------------------
-- Primary Key structure for table t_user
-- ----------------------------
ALTER TABLE "t_user" ADD CONSTRAINT "t_user_pkey" PRIMARY KEY ("id");
