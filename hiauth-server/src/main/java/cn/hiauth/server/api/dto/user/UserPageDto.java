package cn.hiauth.server.api.dto.user;

import cn.hiauth.server.entity.User;
import cn.webestar.scms.commons.api.PageBody;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class UserPageDto extends PageBody {

    @Schema(description = "关键字")
    private String keyword;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "手机号码")
    private String phoneNum;

    @Schema(description = "性别，0：未知，1：男，2：女")
    private Integer gender;

    @Schema(description = "状态，0：禁用，1：启用")
    private Integer status;

    @Schema(description = "注册时间")
    private String[] regtime;

    @Schema(description = "是否为系统管理员")
    private Boolean isSysAdmin;

    @Override
    public LambdaQueryWrapper<User> toQueryWapper() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getIsDeleted, false);
        if (StringUtils.hasText(keyword)) {
            queryWrapper.and(wrapper -> wrapper.like(User::getName, keyword)
                    .or().like(User::getUsername, keyword)
                    .or().like(User::getPhoneNum, keyword)
            );
        }
        if (StringUtils.hasText(name)) {
            queryWrapper.like(User::getName, name);
        }
        if (StringUtils.hasText(username)) {
            queryWrapper.like(User::getUsername, username);
        }
        if (StringUtils.hasText(phoneNum)) {
            queryWrapper.like(User::getPhoneNum, phoneNum);
        }
        if (gender != null) {
            queryWrapper.eq(User::getGender, gender);
        }
        if (status != null) {
            queryWrapper.eq(User::getStatus, status);
        }
        if (isSysAdmin != null) {
            queryWrapper.eq(User::getIsSysAdmin, isSysAdmin);
        }
        if (regtime != null && regtime.length >= 2) {
            queryWrapper.ge(User::getRegtime, regtime[0]);
            queryWrapper.le(User::getRegtime, regtime[1]);
        }
        return queryWrapper;
    }

}
