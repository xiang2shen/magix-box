package com.magicbox.mapper;

import com.magicbox.model.ProductTagRel;
import com.magicbox.model.ProductTagRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductTagRelMapper {
    long countByExample(ProductTagRelExample example);

    int deleteByExample(ProductTagRelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductTagRel record);

    int insertSelective(ProductTagRel record);

    List<ProductTagRel> selectByExample(ProductTagRelExample example);

    ProductTagRel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProductTagRel record, @Param("example") ProductTagRelExample example);

    int updateByExample(@Param("record") ProductTagRel record, @Param("example") ProductTagRelExample example);

    int updateByPrimaryKeySelective(ProductTagRel record);

    int updateByPrimaryKey(ProductTagRel record);
}