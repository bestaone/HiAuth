package cn.hiauth.server.api.vo;

import cn.hiauth.server.entity.User;
import cn.webestar.scms.commons.api.PageVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserVo {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "手机号码")
    private String phoneNum;

    @Schema(description = "性别，0：未知，1：男，2：女")
    private Integer gender;

    @Schema(description = "状态，0：禁用，1：启用")
    private Integer status;

    @Schema(description = "注册时间")
    private LocalDateTime regtime;

    @Schema(description = "最后登录时间")
    private LocalDateTime lastLoginTime;

    @Schema(description = "是否为系统管理员")
    private Integer isSysAdmin;

    public static UserVo convert(User o) {
        UserVo vo = new UserVo();
        vo.setId(o.getId());
        vo.setName(o.getName());
        vo.setAvatar(o.getAvatarUrl());
        vo.setUsername(o.getUsername());
        vo.setPhoneNum(o.getPhoneNum());
        vo.setGender(o.getGender());
        vo.setStatus(o.getStatus());
        vo.setRegtime(o.getRegtime());
        vo.setLastLoginTime(o.getLastLoginTime());
        if (o.getIsSysAdmin() != null && o.getIsSysAdmin()) {
            vo.setIsSysAdmin(1);
        } else {
            vo.setIsSysAdmin(0);
        }
        return vo;
    }

    public static List<UserVo> convert(List<User> os) {
        List<UserVo> vos = new ArrayList<>();
        os.forEach(i -> vos.add(convert(i)));
        return vos;
    }

    public static PageVO<UserVo> toPageVo(IPage<User> page) {
        List<UserVo> vos = convert(page.getRecords());
        PageVO<UserVo> pageVo = new PageVO<>(page.getCurrent(), page.getSize(), page.getTotal(), vos);
        return pageVo;
    }

}
