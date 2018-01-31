package com.magicbox.mapper;

import com.magicbox.model.ProductTag;
import com.magicbox.model.ProductTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductTagMapper {
    long countByExample(ProductTagExample example);

    int deleteByExample(ProductTagExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductTag record);

    int insertSelective(ProductTag record);

    List<ProductTag> selectByExample(ProductTagExample example);

    ProductTag selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProductTag record, @Param("example") ProductTagExample example);

    int updateByExample(@Param("record") ProductTag record, @Param("example") ProductTagExample example);

    int updateByPrimaryKeySelective(ProductTag record);

    int updateByPrimaryKey(ProductTag record);
}