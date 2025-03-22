package cn.hiauth.server.service.impl;

import cn.hiauth.server.entity.File;
import cn.hiauth.server.mapper.FileMapper;
import cn.hiauth.server.service.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 文件
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

}