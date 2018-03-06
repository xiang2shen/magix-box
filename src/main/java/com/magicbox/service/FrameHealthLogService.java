package com.magicbox.service;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbox.base.support.Page;
import com.magicbox.base.utilities.DateUtils;
import com.magicbox.base.utilities.XCollectionUtils;
import com.magicbox.mapper.FrameHealthLogMapper;
import com.magicbox.model.FrameHealthLog;
import com.magicbox.model.FrameHealthLogExample;

@Service
public class FrameHealthLogService {
	
	@Autowired
    private FrameHealthLogMapper frameHealthLogMapper;

    protected Page<FrameHealthLog> selectPageByExample(FrameHealthLogExample example) {
        return new Page<>(example.getPageNo(), example.getLimit(), frameHealthLogMapper.selectByExample(example), frameHealthLogMapper.countByExample(example));
    }

	public FrameHealthLog selectOneByFrameCodeAndRecentSecond(String frameCode, Integer seconds) {
		if (StringUtils.isBlank(frameCode) || null == seconds) {
			return null;
		}
		
		FrameHealthLogExample example = new FrameHealthLogExample();
		example.or().andFrameCodeEqualTo(frameCode).andCreateTimeGreaterThanOrEqualTo(DateUtils.addSeconds(new Date(), - seconds));
		
		return XCollectionUtils.getFirstElement(frameHealthLogMapper.selectByExample(example));
	}
}