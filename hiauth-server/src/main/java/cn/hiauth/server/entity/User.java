package cn.hiauth.server.entity;

import cn.webestar.scms.commons.entity.BasicDO;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value = "t_user", autoResultMap = true)
@Schema(name = "User", description = "用户")
public class User extends BasicDO<Long> {

    @TableField("name")
    @Schema(description = "名称")
    private String name;

    @TableField("gender")
    @Schema(description = "性别，0：未知，1：男，2：女")
    private Integer gender;

    @TableField("avatar_url")
    @Schema(description = "头像")
    private String avatarUrl;

    @TableField("phone_num")
    @Schema(description = "手机号码")
    private String phoneNum;

    @TableField("username")
    @Schema(description = "用户名")
    private String username;

    @TableField("pwd")
    @Schema(description = "密码")
    private String pwd;

    @TableField("wxmp_openid")
    @Schema(description = "微信小程序openid")
    private String wxmpOpenid;

    @TableField("wx_openid")
    @Schema(description = "微信openid")
    private String wxOpenid;

    @TableField("wx_unionid")
    @Schema(description = "微信unionid")
    private String wxUnionid;

    @TableField("status")
    @Schema(description = "状态，0：禁用，1：启用")
    private Integer status;

    @TableField("regtime")
    @Schema(description = "注册时间")
    private LocalDateTime regtime;

    @TableField("last_login_time")
    @Schema(description = "最后登录时间")
    private LocalDateTime lastLoginTime;

    @TableField("creator")
    @Schema(description = "创建人")
    private Long creator;

    @TableField("updater")
    @Schema(description = "更新人")
    private Long updater;

    @TableField("deleter")
    @Schema(description = "删除人")
    private Long deleter;

    @TableField("create_time")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @TableField("update_time")
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @TableField("delete_time")
    @Schema(description = "删除时间")
    private LocalDateTime deleteTime;

    // 使用起来太麻烦，去掉
    //@TableLogic(value = "false", delval = "true")
    @TableField("is_deleted")
    @Schema(description = "是否已删除")
    private Boolean isDeleted;

    @TableField("is_sys_admin")
    @Schema(description = "是否为系统管理员")
    private Boolean isSysAdmin;

    @Schema(description = "扩展字段")
    @TableField(value = "extend", typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> extend;

}