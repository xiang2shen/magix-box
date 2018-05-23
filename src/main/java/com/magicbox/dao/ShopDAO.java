package com.magicbox.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.magicbox.dto.ShopDistanceDTO;

@Repository
public interface ShopDAO {

	@Select("<script>SELECT *, "
			+ "ROUND(6378.138*2*ASIN(SQRT(POW(SIN((#{lat}*PI()/180-shop_latitude*PI()/180)/2),2)+COS(#{lat}*PI()/180)*COS(shop_latitude*PI()/180)*POW(SIN((#{lon}*PI()/180-shop_longitude*PI()/180)/2),2)))*1000) AS distance "
			+ "FROM t_shop_shop t WHERE t.id IN ( "
			+ "SELECT DISTINCT s.id "
			+ "FROM t_shop_shop s "
			+ "JOIN t_prd_product p ON s.shop_code = p.shop_code "
			+ "WHERE (s.shop_name LIKE CONCAT('%',#{text},'%') "
			+ "OR p.product_name LIKE CONCAT('%',#{text},'%')) "
			+ "<if test='null != shopCodeList and shopCodeList.size &gt; 0'>"
			+ "AND s.shop_code IN "
			+ "<foreach collection='shopCodeList' item='item' index='index' open='(' close=')' separator=','>"
			+ "#{item}" 
			+ "</foreach> "
			+ "</if> "
			+ ") "
			+ "ORDER BY distance ASC "
			+ "LIMIT #{limit},#{pageSize} "
			+ "</script>")
	List<ShopDistanceDTO> selectShopListBySearchText(@Param("text")String text, @Param("shopCodeList")List<String> shopCodeList, @Param("lon")Double lon, @Param("lat")Double lat, 
			@Param("limit")Integer limit, @Param("pageSize")Integer pageSize);
	
	@Select("<script>SELECT COUNT(1) FROM t_shop_shop t WHERE t.id IN ("
			+ "SELECT DISTINCT s.id "
			+ "FROM t_shop_shop s "
			+ "JOIN t_prd_product p ON s.shop_code = p.shop_code "
			+ "WHERE (s.shop_name LIKE CONCAT('%',#{text},'%') "
			+ "OR p.product_name LIKE CONCAT('%',#{text},'%')) "
			+ "<if test='null != shopCodeList and shopCodeList.size &gt; 0'>"
			+ "AND s.shop_code IN "
			+ "<foreach collection='shopCodeList' item='item' index='index' open='(' close=')' separator=','>"
			+ "#{item}" 
			+ "</foreach> "
			+ "</if> "
			+ ") "
			+ "</script>")
	Long selectShopCountBySearchText(@Param("text")String text, @Param("shopCodeList")List<String> shopCodeList);

}
