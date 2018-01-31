package com.magicbox.mapper;

import com.magicbox.model.Box;
import com.magicbox.model.BoxExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BoxMapper {
    long countByExample(BoxExample example);

    int deleteByExample(BoxExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Box record);

    int insertSelective(Box record);

    List<Box> selectByExample(BoxExample example);

    Box selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Box record, @Param("example") BoxExample example);

    int updateByExample(@Param("record") Box record, @Param("example") BoxExample example);

    int updateByPrimaryKeySelective(Box record);

    int updateByPrimaryKey(Box record);
}