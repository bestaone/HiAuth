package cn.hiauth.mgr.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName(value = "user", autoResultMap = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class User extends Model<User> implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private Long updaterId;
    private Long createrId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private String avatarUrl;
    private String username;
    private String phoneNum;
    private String password;
    private LocalDateTime regtime;

    private Integer status;

}
