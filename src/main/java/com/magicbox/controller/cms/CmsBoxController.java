package com.magicbox.controller.cms;

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
			@RequestParam(required = false) String frameModel
			) {
		
		Long memberId = getMemberId(token);
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		Frame frame = new Frame();
		frame.setFrameCode(frameCode);
		frame.setFrameModel(frameModel);
		frameMapper.insertSelective(frame);
		
		return ResponseWrapper.succeed(frame);
	}
	
	@ApiOperation("更新货架")
	@PostMapping("/updateFrame")
	public ResponseWrapper<Frame> updateFrame(
			@RequestParam String token,
			@RequestParam Long frameId,
			@RequestParam(required = false) String frameModel
			) {
		
		Long memberId = getMemberId(token);
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		Frame frame = new Frame();
		frame.setId(frameId);
		frame.setFrameModel(frameModel);
		frameMapper.updateByPrimaryKeySelective(frame);
		
		return ResponseWrapper.succeed(frame);
	}
	
	@ApiOperation("查询框架列表")
	@PostMapping("/listFrame")
	public ResponseWrapper<Page<Frame>> listFrame(
			@RequestParam String token,
			@RequestParam(required = false) String frameCode,
			@RequestParam Integer pageNo,
			@RequestParam Integer pageSize
			) {
		
		Long memberId = getMemberId(token);
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		Page<Frame> page = frameService.selectPage(frameCode, pageNo, pageSize);
		
		return ResponseWrapper.succeed(page);
	}
	
	@ApiOperation("查询框架详情")
	@PostMapping("/frameDetail")
	public ResponseWrapper<FrameDTO> frameDetail(
			@RequestParam String token,
			@RequestParam Long frameId
			) {
		
		Long memberId = getMemberId(token);
		if (null == memberId) {
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
		
		Long memberId = getMemberId(token);
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		Box box = new Box();
		box.setFrameCode(frameCode);
		box.setBoxCode(boxCode);
		box.setBoxPosition(boxPosition);
		box.setCapacity(capacity);
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
		
		Long memberId = getMemberId(token);
		if (null == memberId) {
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
	
	@ApiOperation("查询盒子详情")
	@PostMapping("/boxDetail")
	public ResponseWrapper<Box> boxDetail(
			@RequestParam String token,
			@RequestParam Long boxId
			) {
		
		Long memberId = getMemberId(token);
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		Box box = boxMapper.selectByPrimaryKey(boxId);
		
		return ResponseWrapper.succeed(box);
	}
}
