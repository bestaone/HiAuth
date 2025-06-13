package cn.hiauth.server.service;

import cn.hiauth.server.entity.Dict;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * 字典
 */
public interface DictService extends IService<Dict> {

    IPage<Dict> pageByPcode(Page<Dict> p, LambdaQueryWrapper<Dict> queryWapper, String pCode, boolean isRoot);

    int childCount(Set<Long> ids);

}
