package com.magicbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbox.base.support.Page;
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
}