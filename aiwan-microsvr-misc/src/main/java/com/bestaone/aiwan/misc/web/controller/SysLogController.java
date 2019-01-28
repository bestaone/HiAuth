package com.bestaone.aiwan.misc.web.controller;

import com.bestaone.aiwan.api.misc.SysLogApi;
import com.bestaone.aiwan.api.misc.dto.SysLogDto;
import com.bestaone.aiwan.api.misc.vo.SysLogVo;
import com.bestaone.aiwan.common.api.ApiResponse;
import com.bestaone.aiwan.common.exception.CommonException;
import com.bestaone.aiwan.core.exception.Assert;
import com.bestaone.aiwan.misc.domain.SysLog;
import com.bestaone.aiwan.misc.service.SysLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/api/sys_log")
public class SysLogController implements SysLogApi {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	SysLogService sysLogService;

	@Override
	@PostMapping
	public ApiResponse<String> create(@Valid @RequestBody SysLogDto sysLogDto) throws CommonException {
		Assert.notNull(sysLogDto.getContent(),50000, "content不存在");
		SysLog sysLog = new SysLog();
		sysLog.setContent(sysLogDto.getContent());
		sysLog.setCreateTime(new Date());
		sysLogService.save(sysLog);
		return ApiResponse.sucess(sysLog.getId().toString());
	}

	@Override
	@GetMapping("/{id:\\d+}")
	public ApiResponse<SysLogVo> getInfo(@PathVariable Long id) throws CommonException{
		SysLog sysLog = sysLogService.findById(id);
		Assert.notNull(sysLog,20001,"数据不存");
		SysLogVo sysLogVo = new SysLogVo();
		sysLogVo.setId(sysLog.getId());
		sysLogVo.setContent(sysLog.getContent());
		sysLogVo.setCreateTime(sysLog.getCreateTime());
		return ApiResponse.sucess(sysLogVo);
	}

}
