package cn.hiauth.mgr.api.vo.user;

//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CurrentUserVO {

//    @ApiModelProperty(value = "userId")
    private Long userId;

//    @ApiModelProperty(value = "昵称")
    private String nickname;

//    @ApiModelProperty(value = "姓名")
    private String realName;

//    @ApiModelProperty(value = "用户名")
    private String username;

//    @ApiModelProperty(value = "头像")
    private String avatar;

//    @ApiModelProperty(value = "状态")
    private Integer status;

//    @ApiModelProperty(value = "描述")
    private String desc;

//    @ApiModelProperty(value = "未读消息数")
    private Integer unreadCount;

}
