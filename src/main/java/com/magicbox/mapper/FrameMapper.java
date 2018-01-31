package com.magicbox.mapper;

import com.magicbox.model.Frame;
import com.magicbox.model.FrameExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FrameMapper {
    long countByExample(FrameExample example);

    int deleteByExample(FrameExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Frame record);

    int insertSelective(Frame record);

    List<Frame> selectByExample(FrameExample example);

    Frame selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Frame record, @Param("example") FrameExample example);

    int updateByExample(@Param("record") Frame record, @Param("example") FrameExample example);

    int updateByPrimaryKeySelective(Frame record);

    int updateByPrimaryKey(Frame record);
}