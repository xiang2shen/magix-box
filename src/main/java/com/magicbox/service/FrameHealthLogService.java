package com.magicbox.service;

import java.util.Date;

import com.magicbox.base.constants.MqttConstants;
import com.magicbox.model.Box;
import com.magicbox.mqtt.MqttClient;
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
	@Autowired
    private MqttClient mqttClient;

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

	public boolean checkFrameHealth(Box box) {
		FrameHealthLog frameHealthLog;
		int loopTimes = 0;
		do {

			frameHealthLog = selectOneByFrameCodeAndRecentSecond(box.getFrameCode(), 60);
			if (null == frameHealthLog) {
				mqttClient.publish(MqttConstants.TOPIC_PING + box.getFrameCode(), box.getBoxCode());

				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {}
			}
		} while (null == frameHealthLog && loopTimes++ < 10);

		return null != frameHealthLog;
	}
}