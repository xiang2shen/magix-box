package com.magicbox.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.magicbox.dto.ShopTagRelDTO;

@Repository
public interface ShopTagDAO {

	@Select("<script>SELECT r.*, t.tag_name "
			+ "FROM t_shop_shop_tag t "
			+ "JOIN t_shop_shop_tag_rel r ON r.tag_id = t.id "
			+ "WHERE r.shop_code IN "
			+ "<foreach collection='shopCodeList' item='item' index='index' open='(' close=')' separator=','>"
			+ "#{item}" 
			+ "</foreach> "
			+ "ORDER BY r.id DESC</script>")
	List<ShopTagRelDTO> selectDTOListByShopCodeList(@Param("shopCodeList")List<String> shopCodeList);
	
}
