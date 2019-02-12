package com.magicbox.dto;

import java.util.List;

import com.magicbox.model.Box;
import com.magicbox.model.Frame;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FrameDTO extends Frame {
	
	private static final long serialVersionUID = 1L;

	private List<Box> boxes;
}
