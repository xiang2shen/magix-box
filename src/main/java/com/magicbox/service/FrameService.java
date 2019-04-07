package com.magicbox.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbox.base.constants.FrameStatusEnum;
import com.magicbox.base.support.Page;
import com.magicbox.base.utilities.XCollectionUtils;
import com.magicbox.mapper.FrameMapper;
import com.magicbox.model.Frame;
import com.magicbox.model.FrameExample;
import com.magicbox.model.FrameExample.Criteria;

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
	
	public Page<Frame> selectPage(String frameCode, Integer pageNo, Integer pageSize) {
		FrameExample example = new FrameExample();
		example.initPage(pageNo, pageSize);
		example.setOrderByClause("create_time desc");
		Criteria criteria = example.or();
		if (StringUtils.isNotBlank(frameCode)) {
			criteria.andFrameCodeLike("%" + frameCode + "%");
		}
		
		return selectPageByExample(example);
	}

	public void updateFrameStatus(Long frameId, FrameStatusEnum frameStatus) {
		Frame frame = new Frame();
		frame.setId(frameId);
		frame.setFrameStatus(frameStatus.getCode());
		frameMapper.updateByPrimaryKeySelective(frame);
	}

	public List<Frame> selectListByShopCode(String shopCode) {
		FrameExample example = new FrameExample();
		example.setOrderByClause("create_time desc");
		example.or().andShopCodeEqualTo(shopCode);
		return frameMapper.selectByExample(example);
	}

	public void updateShopCode(Long id, String shopCode) {
		Frame frame = new Frame();
		frame.setId(id);
		frame.setShopCode(shopCode);
		frameMapper.updateByPrimaryKeySelective(frame);
	}
}