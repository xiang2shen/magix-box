package com.magicbox.mapper;

import com.magicbox.model.ShopAssistant;
import com.magicbox.model.ShopAssistantExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopAssistantMapper {
    long countByExample(ShopAssistantExample example);

    int deleteByExample(ShopAssistantExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShopAssistant record);

    int insertSelective(ShopAssistant record);

    List<ShopAssistant> selectByExample(ShopAssistantExample example);

    ShopAssistant selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShopAssistant record, @Param("example") ShopAssistantExample example);

    int updateByExample(@Param("record") ShopAssistant record, @Param("example") ShopAssistantExample example);

    int updateByPrimaryKeySelective(ShopAssistant record);

    int updateByPrimaryKey(ShopAssistant record);
}