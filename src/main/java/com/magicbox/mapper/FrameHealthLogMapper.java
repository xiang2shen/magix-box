package com.magicbox.mapper;

import com.magicbox.model.FrameHealthLog;
import com.magicbox.model.FrameHealthLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FrameHealthLogMapper {
    long countByExample(FrameHealthLogExample example);

    int deleteByExample(FrameHealthLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FrameHealthLog record);

    int insertSelective(FrameHealthLog record);

    List<FrameHealthLog> selectByExample(FrameHealthLogExample example);

    FrameHealthLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FrameHealthLog record, @Param("example") FrameHealthLogExample example);

    int updateByExample(@Param("record") FrameHealthLog record, @Param("example") FrameHealthLogExample example);

    int updateByPrimaryKeySelective(FrameHealthLog record);

    int updateByPrimaryKey(FrameHealthLog record);
}