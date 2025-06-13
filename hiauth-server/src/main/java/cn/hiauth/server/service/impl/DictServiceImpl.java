package cn.hiauth.server.service.impl;

import cn.hiauth.server.entity.Dict;
import cn.hiauth.server.mapper.DictMapper;
import cn.hiauth.server.service.DictService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 字典
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    public IPage<Dict> pageByPcode(Page<Dict> p, LambdaQueryWrapper<Dict> queryWapper, String pCode, boolean isRoot) {
        return baseMapper.pageByPcode(p, queryWapper, pCode, isRoot);
    }

    @Override
    public int childCount(Set<Long> ids) {
        return baseMapper.childCount(ids);
    }

//    @Override
//    public Map<String, List<Dict>> findByPCodes(Set<String> codes) {
//        Map<String, List<Dict>> resMap = new HashMap<>();
//        if (CollUtil.isEmpty(codes)) {
//            return resMap;
//        }
//        LambdaQueryWrapper<Dict> wrapper = new LambdaQueryWrapper<>();
//        wrapper.in(Dict::getPCode, codes);
//        wrapper.eq(Dict::getIsEnable, Boolean.TRUE);
//        List<Dict> list = list(wrapper);
//        if (CollUtil.isEmpty(list)) {
//            return resMap;
//        }
//        //按照父类编码分类
//        return list.stream().collect(Collectors.groupingBy(Dict::getPCode));
//    }
//
//    @Override
//    public List<Dict> getByPCodes(Set<String> codes) {
//        Map<Dict, List<Dict>> resMap = new HashMap<>();
//        LambdaQueryWrapper<Dict> wrapper = new LambdaQueryWrapper<>();
//        wrapper.in(Dict::getPCode, codes);
//        wrapper.or();
//        wrapper.in(Dict::getCode, codes);
//        wrapper.eq(Dict::getIsEnable, Boolean.TRUE);
//        return list(wrapper);
//    }
//
//    @Override
//    public Map<String, Dict> findByCodes(List<String> codes) {
//        LambdaQueryWrapper<Dict> wrapper = new LambdaQueryWrapper<>();
//        wrapper.in(Dict::getCode, codes);
//        wrapper.eq(Dict::getIsEnable, Boolean.TRUE);
//        List<Dict> list = list(wrapper);
//        Map<String, Dict> map = list.stream().collect(Collectors.toMap(Dict::getCode, dict -> dict));
//        return map;
//    }
//
//    @Override
//    public Map<String, String> findNameByCodes(Set<String> codes) {
//        LambdaQueryWrapper<Dict> wrapper = new LambdaQueryWrapper<>();
//        wrapper.in(Dict::getCode, codes);
//        wrapper.eq(Dict::getIsEnable, Boolean.TRUE);
//        List<Dict> list = list(wrapper);
//        Map<String, String> map = list.stream().collect(Collectors.toMap(Dict::getCode, Dict::getName));
//        return map;
//    }

}