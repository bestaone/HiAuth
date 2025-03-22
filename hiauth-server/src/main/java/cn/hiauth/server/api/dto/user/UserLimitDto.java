package cn.hiauth.server.api.dto.user;

import cn.hiauth.server.entity.User;
import cn.webestar.scms.commons.api.LimitBody;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class UserLimitDto extends LimitBody {

    @Schema(description = "关键字")
    private String keyword;

    @Override
    public LambdaQueryWrapper<User> toQueryWapper() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getIsDeleted, false);
        if (StringUtils.hasText(keyword)) {
            queryWrapper.and(wrapper -> wrapper.like(User::getUsername, keyword)
                    .or().like(User::getPhoneNum, keyword)
                    .or().like(User::getName, keyword)
            );
        }
        queryWrapper.last("OFFSET " + this.getOffset() + " LIMIT " + this.getLimit());
        return queryWrapper;
    }

}
