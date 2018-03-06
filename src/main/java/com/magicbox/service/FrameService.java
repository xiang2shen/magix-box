package com.magicbox.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbox.base.support.Page;
import com.magicbox.base.utilities.XCollectionUtils;
import com.magicbox.mapper.FrameMapper;
import com.magicbox.model.Frame;
import com.magicbox.model.FrameExample;

@Service
public class FrameService {
	
	@Autowired
    private FrameMapper frameMapper;

    protected Page<Frame> selectPageByExample(FrameExample example) {
        return new Page<>(example.getPageNo(), example.getLimit(), frameMapper.selectByExample(example), frameMapper.countByExample(example));
    }

	public Frame selectOneByFrameCode(String frameCode) {
		if (StringUtils.isBlank(frameCode)) {
			return null;
		}
		
		FrameExample example = new FrameExample();
		example.or().andFrameCodeEqualTo(frameCode);
		
		return XCollectionUtils.getFirstElement(frameMapper.selectByExample(example));
	}
}