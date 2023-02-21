
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `id` bigint NOT NULL COMMENT '主键',
  `avatar_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `phone_num` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号码',
  `regtime` datetime NOT NULL COMMENT '注册时间',
  `updater_id` bigint NOT NULL DEFAULT '0' COMMENT '更新人',
  `creater_id` bigint NOT NULL DEFAULT '0' COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_username` (`username`) USING BTREE,
  UNIQUE KEY `uk_phone_num` (`phone_num`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='管理端用户';

-- ----------------------------
-- Records of manager
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for oauth2_authorization
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_authorization`;
CREATE TABLE `oauth2_authorization` (
  `id` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `registered_client_id` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `principal_name` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `authorization_grant_type` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `authorized_scopes` varchar(1000) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `attributes` blob,
  `state` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `authorization_code_value` blob,
  `authorization_code_issued_at` timestamp NULL DEFAULT NULL,
  `authorization_code_expires_at` timestamp NULL DEFAULT NULL,
  `authorization_code_metadata` blob,
  `access_token_value` blob,
  `access_token_issued_at` timestamp NULL DEFAULT NULL,
  `access_token_expires_at` timestamp NULL DEFAULT NULL,
  `access_token_metadata` blob,
  `access_token_type` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `access_token_scopes` varchar(1000) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `oidc_id_token_value` blob,
  `oidc_id_token_issued_at` timestamp NULL DEFAULT NULL,
  `oidc_id_token_expires_at` timestamp NULL DEFAULT NULL,
  `oidc_id_token_metadata` blob,
  `refresh_token_value` blob,
  `refresh_token_issued_at` timestamp NULL DEFAULT NULL,
  `refresh_token_expires_at` timestamp NULL DEFAULT NULL,
  `refresh_token_metadata` blob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of oauth2_authorization
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for oauth2_authorization_consent
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_authorization_consent`;
CREATE TABLE `oauth2_authorization_consent` (
  `registered_client_id` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `principal_name` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `authorities` varchar(1000) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`registered_client_id`,`principal_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of oauth2_authorization_consent
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for oauth2_registered_client
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_registered_client`;
CREATE TABLE `oauth2_registered_client` (
  `id` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `client_id` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `client_id_issued_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `client_secret` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `client_secret_expires_at` timestamp NULL DEFAULT NULL,
  `client_name` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `client_authentication_methods` varchar(1000) COLLATE utf8mb4_general_ci NOT NULL,
  `authorization_grant_types` varchar(1000) COLLATE utf8mb4_general_ci NOT NULL,
  `redirect_uris` varchar(1000) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `scopes` varchar(1000) COLLATE utf8mb4_general_ci NOT NULL,
  `client_settings` varchar(2000) COLLATE utf8mb4_general_ci NOT NULL,
  `token_settings` varchar(2000) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of oauth2_registered_client
-- ----------------------------
BEGIN;
INSERT INTO `oauth2_registered_client` (`id`, `client_id`, `client_id_issued_at`, `client_secret`, `client_secret_expires_at`, `client_name`, `client_authentication_methods`, `authorization_grant_types`, `redirect_uris`, `scopes`, `client_settings`, `token_settings`) VALUES ('c8003fa7-e533-488b-b823-cd1415ef96e2', 'demo-client-id', '2023-02-20 23:04:53', '{bcrypt}$2a$10$/njvohEwiQt9IrDc9F5XDucl.xhCC.Lssu1vgnR/4iRhig6ifeR8y', NULL, 'demo-client', 'client_secret_basic', 'refresh_token,client_credentials,authorization_code', 'http://www.baidu.com,http://127.0.0.1:8081/authorize/oauth2/code/hiauth-authorization-code,http://himall.hiauth.cn/login/oauth2/code/hiauth-authorization-code,http://127.0.0.1:8081/login/oauth2/code/hiauth-authorization-code,http://himall.hiauth.cn/authorize/oauth2/code/hiauth-authorization-code', 'pull_requests,user_info,openid,profile,message.read,message.write', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":true}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",7200.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",2592000.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000]}');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL COMMENT '主键',
  `avatar_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `phone_num` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '手机号码',
  `regtime` datetime NOT NULL COMMENT '注册时间',
  `updater_id` bigint NOT NULL DEFAULT '0' COMMENT '更新人',
  `creater_id` bigint NOT NULL DEFAULT '0' COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_username` (`username`) USING BTREE,
  UNIQUE KEY `uk_phone_num` (`phone_num`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `avatar_url`, `username`, `password`, `phone_num`, `regtime`, `updater_id`, `creater_id`, `update_time`, `create_time`, `status`) VALUES (843766124899412260, NULL, 'user', '{bcrypt}$2a$10$xBnlqkBLPFtJ3R5rqi4ateGjQH9WBnNfbIRhKJfVT/1Sicr1QPs6m', '13912345678', '2023-02-17 13:41:30', 0, 0, '2023-02-20 16:14:10', '2023-02-17 13:41:30', 1);
INSERT INTO `user` (`id`, `avatar_url`, `username`, `password`, `phone_num`, `regtime`, `updater_id`, `creater_id`, `update_time`, `create_time`, `status`) VALUES (8437661765160597135, NULL, 'admin', '{bcrypt}$2a$10$xBnlqkBLPFtJ3R5rqi4ateGjQH9WBnNfbIRhKJfVT/1Sicr1QPs6m', '13712345678', '2023-02-17 15:07:32', 0, 0, '2023-02-17 15:07:32', '2023-02-17 15:07:32', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
