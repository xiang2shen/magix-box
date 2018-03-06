package com.magicbox.mapper;

import com.magicbox.model.ShopTag;
import com.magicbox.model.ShopTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopTagMapper {
    long countByExample(ShopTagExample example);

    int deleteByExample(ShopTagExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShopTag record);

    int insertSelective(ShopTag record);

    List<ShopTag> selectByExample(ShopTagExample example);

    ShopTag selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShopTag record, @Param("example") ShopTagExample example);

    int updateByExample(@Param("record") ShopTag record, @Param("example") ShopTagExample example);

    int updateByPrimaryKeySelective(ShopTag record);

    int updateByPrimaryKey(ShopTag record);
}