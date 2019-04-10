package com.magicbox.controller.cms;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.magicbox.base.exception.ErrorCodes;
import com.magicbox.base.support.Page;
import com.magicbox.base.support.ResponseWrapper;
import com.magicbox.controller.BaseController;
import com.magicbox.dto.FrameDTO;
import com.magicbox.mapper.BoxMapper;
import com.magicbox.mapper.FrameMapper;
import com.magicbox.model.Box;
import com.magicbox.model.Frame;
import com.magicbox.service.BoxService;
import com.magicbox.service.FrameService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("CMS盒子API")
@RestController
@RequestMapping("/cms/box")
public class CmsBoxController extends BaseController {
	
	@Autowired
	private BoxService boxService;
	@Autowired
	private FrameService frameService;
	@Autowired
	private BoxMapper boxMapper;
	@Autowired
	private FrameMapper frameMapper;
	
	@ApiOperation("创建货架")
	@PostMapping("/createFrame")
	public ResponseWrapper<Frame> createFrame(
			@RequestParam String token,
			@RequestParam String frameCode,
			@RequestParam(required = false) String frameModel,
			@RequestParam(required = false) String internetFlow,
			@RequestParam(required = false) Date productTime
			) {
		
		Long userId = getUserId(token);
		if (null == userId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		Frame frame = new Frame();
		frame.setFrameCode(frameCode);
		frame.setFrameStatus(1);
		frame.setFrameModel(frameModel);
		frame.setInternetFlow(internetFlow);
		frame.setProductTime(productTime);
		frameMapper.insertSelective(frame);
		
		return ResponseWrapper.succeed(frame);
	}
	
	@ApiOperation("更新货架")
	@PostMapping("/updateFrame")
	public ResponseWrapper<Frame> updateFrame(
			@RequestParam String token,
			@RequestParam Long frameId,
			@RequestParam(required = false) String frameModel,
			@RequestParam(required = false) String internetFlow,
			@RequestParam(required = false) Date productTime
			) {
		
		Long userId = getUserId(token);
		if (null == userId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		Frame frame = new Frame();
		frame.setId(frameId);
		frame.setFrameModel(frameModel);
		frame.setInternetFlow(internetFlow);
		frame.setProductTime(productTime);
		frameMapper.updateByPrimaryKeySelective(frame);
		
		return ResponseWrapper.succeed(frame);
	}
	
	@ApiOperation("查询框架列表")
	@PostMapping("/listFrames")
	public ResponseWrapper<Page<Frame>> listFrames(
			@RequestParam String token,
			@RequestParam Integer pageNo,
			@RequestParam Integer pageSize
			) {
		
		Long userId = getUserId(token);
		if (null == userId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		Page<Frame> framePage = frameService.selectPage(pageNo, pageSize);
		return ResponseWrapper.succeed(framePage);
	}
	
	@ApiOperation("查询店铺下的框架列表")
	@PostMapping("/listFrameByShopCode")
	public ResponseWrapper<List<Frame>> listFrameByShopCode(
			@RequestParam String token,
			@RequestParam String shopCode
			) {
		
		Long userId = getUserId(token);
		if (null == userId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		List<Frame> frames = frameService.selectListByShopCode(shopCode);
		return ResponseWrapper.succeed(frames);
	}
	
	@ApiOperation("查询框架详情")
	@PostMapping("/frameDetail")
	public ResponseWrapper<FrameDTO> frameDetail(
			@RequestParam String token,
			@RequestParam Long frameId
			) {
		
		Long userId = getUserId(token);
		if (null == userId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		Frame frame = frameMapper.selectByPrimaryKey(frameId);
		List<Box> boxes = boxService.selectListByFrameCode(frame.getFrameCode());
		
		FrameDTO frameDTO = new FrameDTO();
		BeanUtils.copyProperties(frame, frameDTO);
		frameDTO.setBoxes(boxes);
		
		return ResponseWrapper.succeed(frameDTO);
	}
	
	@ApiOperation("创建盒子")
	@PostMapping("/createBox")
	public ResponseWrapper<Box> createBox(
			@RequestParam String token,
			@RequestParam String frameCode,
			@RequestParam String boxCode,
			@RequestParam Integer boxPosition,
			@RequestParam Integer capacity
			) {
		
		Long userId = getUserId(token);
		if (null == userId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		Box box = new Box();
		box.setFrameCode(frameCode);
		box.setBoxCode(boxCode);
		box.setBoxPosition(boxPosition);
		box.setCapacity(capacity);
		box.setBoxStatus(1);
		boxMapper.insertSelective(box);
		
		return ResponseWrapper.succeed(box);
	}
	
	@ApiOperation("更新盒子")
	@PostMapping("/updateBox")
	public ResponseWrapper<Box> updateBox(
			@RequestParam String token,
			@RequestParam Long boxId,
			@RequestParam String frameCode,
			@RequestParam Integer boxPosition,
			@RequestParam Integer capacity
			) {
		
		Long userId = getUserId(token);
		if (null == userId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		Box box = new Box();
		box.setId(boxId);
		box.setFrameCode(frameCode);
		box.setBoxPosition(boxPosition);
		box.setCapacity(capacity);
		boxMapper.updateByPrimaryKeySelective(box);
		
		return ResponseWrapper.succeed(box);
	}
	
	@ApiOperation("查询盒子列表")
	@PostMapping("/listBoxes")
	public ResponseWrapper<List<Box>> listBoxes(
			@RequestParam String token,
			@RequestParam String frameCode
			) {
		
		Long userId = getUserId(token);
		if (null == userId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		List<Box> boxes = boxService.selectListByFrameCode(frameCode);
		
		return ResponseWrapper.succeed(boxes);
	}
	
	@ApiOperation("查询盒子详情")
	@PostMapping("/boxDetail")
	public ResponseWrapper<Box> boxDetail(
			@RequestParam String token,
			@RequestParam Long boxId
			) {
		
		Long userId = getUserId(token);
		if (null == userId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		Box box = boxMapper.selectByPrimaryKey(boxId);
		
		return ResponseWrapper.succeed(box);
	}
}
