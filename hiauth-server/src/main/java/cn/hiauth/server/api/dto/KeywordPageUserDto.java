package cn.hiauth.server.api.dto;

import cn.hiauth.server.entity.User;
import cn.webestar.scms.commons.api.PageBody;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class KeywordPageUserDto extends PageBody {

    private String keyword;

    @Override
    public LambdaQueryWrapper<User> toQueryWapper() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(keyword)) {
            queryWrapper.like(User::getName, keyword);
        }
        return queryWrapper;
    }

}
