package com.magicbox.service.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbox.base.constants.FrameStatusEnum;
import com.magicbox.base.support.ResponseWrapper;
import com.magicbox.base.utilities.BeanChecker;
import com.magicbox.mapper.FrameMapper;
import com.magicbox.model.Frame;
import com.magicbox.service.FrameService;

@Service
public class FrameApiService {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(FrameApiService.class);
	
	@Autowired
	private FrameMapper frameMapper;
	@Autowired
	private FrameService frameService;
	
	public ResponseWrapper<Frame> createFrameWhenAbsent(String frameCode) {
		BeanChecker.getInstance().notBlank(frameCode);
		
		Frame frame = frameService.selectOneByFrameCode(frameCode);
		if (null == frame) {
			
			frame = new Frame();
			frame.setFrameCode(frameCode);
			frame.setFrameStatus(FrameStatusEnum.OFFLINE.getCode());
			
			frameMapper.insertSelective(frame);
		}
		
		
		return ResponseWrapper.succeed(frame);
	}
}
