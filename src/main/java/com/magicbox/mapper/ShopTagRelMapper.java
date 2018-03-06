package com.magicbox.mapper;

import com.magicbox.model.ShopTagRel;
import com.magicbox.model.ShopTagRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopTagRelMapper {
    long countByExample(ShopTagRelExample example);

    int deleteByExample(ShopTagRelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShopTagRel record);

    int insertSelective(ShopTagRel record);

    List<ShopTagRel> selectByExample(ShopTagRelExample example);

    ShopTagRel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShopTagRel record, @Param("example") ShopTagRelExample example);

    int updateByExample(@Param("record") ShopTagRel record, @Param("example") ShopTagRelExample example);

    int updateByPrimaryKeySelective(ShopTagRel record);

    int updateByPrimaryKey(ShopTagRel record);
}