package com.magicbox.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public ShopExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    public void initPage(Integer pageNo, Integer pageSize) {
        this.limit = pageSize;
        this.offset = (pageNo - 1) * pageSize;
    }

    public Integer getPageNo() {
        return offset / limit + 1;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andShopCodeIsNull() {
            addCriterion("shop_code is null");
            return (Criteria) this;
        }

        public Criteria andShopCodeIsNotNull() {
            addCriterion("shop_code is not null");
            return (Criteria) this;
        }

        public Criteria andShopCodeEqualTo(String value) {
            addCriterion("shop_code =", value, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeNotEqualTo(String value) {
            addCriterion("shop_code <>", value, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeGreaterThan(String value) {
            addCriterion("shop_code >", value, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeGreaterThanOrEqualTo(String value) {
            addCriterion("shop_code >=", value, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeLessThan(String value) {
            addCriterion("shop_code <", value, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeLessThanOrEqualTo(String value) {
            addCriterion("shop_code <=", value, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeLike(String value) {
            addCriterion("shop_code like", value, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeNotLike(String value) {
            addCriterion("shop_code not like", value, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeIn(List<String> values) {
            addCriterion("shop_code in", values, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeNotIn(List<String> values) {
            addCriterion("shop_code not in", values, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeBetween(String value1, String value2) {
            addCriterion("shop_code between", value1, value2, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeNotBetween(String value1, String value2) {
            addCriterion("shop_code not between", value1, value2, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNull() {
            addCriterion("shop_name is null");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNotNull() {
            addCriterion("shop_name is not null");
            return (Criteria) this;
        }

        public Criteria andShopNameEqualTo(String value) {
            addCriterion("shop_name =", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotEqualTo(String value) {
            addCriterion("shop_name <>", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThan(String value) {
            addCriterion("shop_name >", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThanOrEqualTo(String value) {
            addCriterion("shop_name >=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThan(String value) {
            addCriterion("shop_name <", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThanOrEqualTo(String value) {
            addCriterion("shop_name <=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLike(String value) {
            addCriterion("shop_name like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotLike(String value) {
            addCriterion("shop_name not like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameIn(List<String> values) {
            addCriterion("shop_name in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotIn(List<String> values) {
            addCriterion("shop_name not in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameBetween(String value1, String value2) {
            addCriterion("shop_name between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotBetween(String value1, String value2) {
            addCriterion("shop_name not between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopDescIsNull() {
            addCriterion("shop_desc is null");
            return (Criteria) this;
        }

        public Criteria andShopDescIsNotNull() {
            addCriterion("shop_desc is not null");
            return (Criteria) this;
        }

        public Criteria andShopDescEqualTo(String value) {
            addCriterion("shop_desc =", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescNotEqualTo(String value) {
            addCriterion("shop_desc <>", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescGreaterThan(String value) {
            addCriterion("shop_desc >", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescGreaterThanOrEqualTo(String value) {
            addCriterion("shop_desc >=", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescLessThan(String value) {
            addCriterion("shop_desc <", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescLessThanOrEqualTo(String value) {
            addCriterion("shop_desc <=", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescLike(String value) {
            addCriterion("shop_desc like", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescNotLike(String value) {
            addCriterion("shop_desc not like", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescIn(List<String> values) {
            addCriterion("shop_desc in", values, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescNotIn(List<String> values) {
            addCriterion("shop_desc not in", values, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescBetween(String value1, String value2) {
            addCriterion("shop_desc between", value1, value2, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescNotBetween(String value1, String value2) {
            addCriterion("shop_desc not between", value1, value2, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopStatusIsNull() {
            addCriterion("shop_status is null");
            return (Criteria) this;
        }

        public Criteria andShopStatusIsNotNull() {
            addCriterion("shop_status is not null");
            return (Criteria) this;
        }

        public Criteria andShopStatusEqualTo(Integer value) {
            addCriterion("shop_status =", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusNotEqualTo(Integer value) {
            addCriterion("shop_status <>", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusGreaterThan(Integer value) {
            addCriterion("shop_status >", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("shop_status >=", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusLessThan(Integer value) {
            addCriterion("shop_status <", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusLessThanOrEqualTo(Integer value) {
            addCriterion("shop_status <=", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusIn(List<Integer> values) {
            addCriterion("shop_status in", values, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusNotIn(List<Integer> values) {
            addCriterion("shop_status not in", values, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusBetween(Integer value1, Integer value2) {
            addCriterion("shop_status between", value1, value2, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("shop_status not between", value1, value2, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andSellerIdIsNull() {
            addCriterion("seller_id is null");
            return (Criteria) this;
        }

        public Criteria andSellerIdIsNotNull() {
            addCriterion("seller_id is not null");
            return (Criteria) this;
        }

        public Criteria andSellerIdEqualTo(Long value) {
            addCriterion("seller_id =", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotEqualTo(Long value) {
            addCriterion("seller_id <>", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdGreaterThan(Long value) {
            addCriterion("seller_id >", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("seller_id >=", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdLessThan(Long value) {
            addCriterion("seller_id <", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdLessThanOrEqualTo(Long value) {
            addCriterion("seller_id <=", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdIn(List<Long> values) {
            addCriterion("seller_id in", values, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotIn(List<Long> values) {
            addCriterion("seller_id not in", values, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdBetween(Long value1, Long value2) {
            addCriterion("seller_id between", value1, value2, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotBetween(Long value1, Long value2) {
            addCriterion("seller_id not between", value1, value2, "sellerId");
            return (Criteria) this;
        }

        public Criteria andShopProvinceCodeIsNull() {
            addCriterion("shop_province_code is null");
            return (Criteria) this;
        }

        public Criteria andShopProvinceCodeIsNotNull() {
            addCriterion("shop_province_code is not null");
            return (Criteria) this;
        }

        public Criteria andShopProvinceCodeEqualTo(String value) {
            addCriterion("shop_province_code =", value, "shopProvinceCode");
            return (Criteria) this;
        }

        public Criteria andShopProvinceCodeNotEqualTo(String value) {
            addCriterion("shop_province_code <>", value, "shopProvinceCode");
            return (Criteria) this;
        }

        public Criteria andShopProvinceCodeGreaterThan(String value) {
            addCriterion("shop_province_code >", value, "shopProvinceCode");
            return (Criteria) this;
        }

        public Criteria andShopProvinceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("shop_province_code >=", value, "shopProvinceCode");
            return (Criteria) this;
        }

        public Criteria andShopProvinceCodeLessThan(String value) {
            addCriterion("shop_province_code <", value, "shopProvinceCode");
            return (Criteria) this;
        }

        public Criteria andShopProvinceCodeLessThanOrEqualTo(String value) {
            addCriterion("shop_province_code <=", value, "shopProvinceCode");
            return (Criteria) this;
        }

        public Criteria andShopProvinceCodeLike(String value) {
            addCriterion("shop_province_code like", value, "shopProvinceCode");
            return (Criteria) this;
        }

        public Criteria andShopProvinceCodeNotLike(String value) {
            addCriterion("shop_province_code not like", value, "shopProvinceCode");
            return (Criteria) this;
        }

        public Criteria andShopProvinceCodeIn(List<String> values) {
            addCriterion("shop_province_code in", values, "shopProvinceCode");
            return (Criteria) this;
        }

        public Criteria andShopProvinceCodeNotIn(List<String> values) {
            addCriterion("shop_province_code not in", values, "shopProvinceCode");
            return (Criteria) this;
        }

        public Criteria andShopProvinceCodeBetween(String value1, String value2) {
            addCriterion("shop_province_code between", value1, value2, "shopProvinceCode");
            return (Criteria) this;
        }

        public Criteria andShopProvinceCodeNotBetween(String value1, String value2) {
            addCriterion("shop_province_code not between", value1, value2, "shopProvinceCode");
            return (Criteria) this;
        }

        public Criteria andShopProvinceNameIsNull() {
            addCriterion("shop_province_name is null");
            return (Criteria) this;
        }

        public Criteria andShopProvinceNameIsNotNull() {
            addCriterion("shop_province_name is not null");
            return (Criteria) this;
        }

        public Criteria andShopProvinceNameEqualTo(String value) {
            addCriterion("shop_province_name =", value, "shopProvinceName");
            return (Criteria) this;
        }

        public Criteria andShopProvinceNameNotEqualTo(String value) {
            addCriterion("shop_province_name <>", value, "shopProvinceName");
            return (Criteria) this;
        }

        public Criteria andShopProvinceNameGreaterThan(String value) {
            addCriterion("shop_province_name >", value, "shopProvinceName");
            return (Criteria) this;
        }

        public Criteria andShopProvinceNameGreaterThanOrEqualTo(String value) {
            addCriterion("shop_province_name >=", value, "shopProvinceName");
            return (Criteria) this;
        }

        public Criteria andShopProvinceNameLessThan(String value) {
            addCriterion("shop_province_name <", value, "shopProvinceName");
            return (Criteria) this;
        }

        public Criteria andShopProvinceNameLessThanOrEqualTo(String value) {
            addCriterion("shop_province_name <=", value, "shopProvinceName");
            return (Criteria) this;
        }

        public Criteria andShopProvinceNameLike(String value) {
            addCriterion("shop_province_name like", value, "shopProvinceName");
            return (Criteria) this;
        }

        public Criteria andShopProvinceNameNotLike(String value) {
            addCriterion("shop_province_name not like", value, "shopProvinceName");
            return (Criteria) this;
        }

        public Criteria andShopProvinceNameIn(List<String> values) {
            addCriterion("shop_province_name in", values, "shopProvinceName");
            return (Criteria) this;
        }

        public Criteria andShopProvinceNameNotIn(List<String> values) {
            addCriterion("shop_province_name not in", values, "shopProvinceName");
            return (Criteria) this;
        }

        public Criteria andShopProvinceNameBetween(String value1, String value2) {
            addCriterion("shop_province_name between", value1, value2, "shopProvinceName");
            return (Criteria) this;
        }

        public Criteria andShopProvinceNameNotBetween(String value1, String value2) {
            addCriterion("shop_province_name not between", value1, value2, "shopProvinceName");
            return (Criteria) this;
        }

        public Criteria andShopCityCodeIsNull() {
            addCriterion("shop_city_code is null");
            return (Criteria) this;
        }

        public Criteria andShopCityCodeIsNotNull() {
            addCriterion("shop_city_code is not null");
            return (Criteria) this;
        }

        public Criteria andShopCityCodeEqualTo(String value) {
            addCriterion("shop_city_code =", value, "shopCityCode");
            return (Criteria) this;
        }

        public Criteria andShopCityCodeNotEqualTo(String value) {
            addCriterion("shop_city_code <>", value, "shopCityCode");
            return (Criteria) this;
        }

        public Criteria andShopCityCodeGreaterThan(String value) {
            addCriterion("shop_city_code >", value, "shopCityCode");
            return (Criteria) this;
        }

        public Criteria andShopCityCodeGreaterThanOrEqualTo(String value) {
            addCriterion("shop_city_code >=", value, "shopCityCode");
            return (Criteria) this;
        }

        public Criteria andShopCityCodeLessThan(String value) {
            addCriterion("shop_city_code <", value, "shopCityCode");
            return (Criteria) this;
        }

        public Criteria andShopCityCodeLessThanOrEqualTo(String value) {
            addCriterion("shop_city_code <=", value, "shopCityCode");
            return (Criteria) this;
        }

        public Criteria andShopCityCodeLike(String value) {
            addCriterion("shop_city_code like", value, "shopCityCode");
            return (Criteria) this;
        }

        public Criteria andShopCityCodeNotLike(String value) {
            addCriterion("shop_city_code not like", value, "shopCityCode");
            return (Criteria) this;
        }

        public Criteria andShopCityCodeIn(List<String> values) {
            addCriterion("shop_city_code in", values, "shopCityCode");
            return (Criteria) this;
        }

        public Criteria andShopCityCodeNotIn(List<String> values) {
            addCriterion("shop_city_code not in", values, "shopCityCode");
            return (Criteria) this;
        }

        public Criteria andShopCityCodeBetween(String value1, String value2) {
            addCriterion("shop_city_code between", value1, value2, "shopCityCode");
            return (Criteria) this;
        }

        public Criteria andShopCityCodeNotBetween(String value1, String value2) {
            addCriterion("shop_city_code not between", value1, value2, "shopCityCode");
            return (Criteria) this;
        }

        public Criteria andShopCityNameIsNull() {
            addCriterion("shop_city_name is null");
            return (Criteria) this;
        }

        public Criteria andShopCityNameIsNotNull() {
            addCriterion("shop_city_name is not null");
            return (Criteria) this;
        }

        public Criteria andShopCityNameEqualTo(String value) {
            addCriterion("shop_city_name =", value, "shopCityName");
            return (Criteria) this;
        }

        public Criteria andShopCityNameNotEqualTo(String value) {
            addCriterion("shop_city_name <>", value, "shopCityName");
            return (Criteria) this;
        }

        public Criteria andShopCityNameGreaterThan(String value) {
            addCriterion("shop_city_name >", value, "shopCityName");
            return (Criteria) this;
        }

        public Criteria andShopCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("shop_city_name >=", value, "shopCityName");
            return (Criteria) this;
        }

        public Criteria andShopCityNameLessThan(String value) {
            addCriterion("shop_city_name <", value, "shopCityName");
            return (Criteria) this;
        }

        public Criteria andShopCityNameLessThanOrEqualTo(String value) {
            addCriterion("shop_city_name <=", value, "shopCityName");
            return (Criteria) this;
        }

        public Criteria andShopCityNameLike(String value) {
            addCriterion("shop_city_name like", value, "shopCityName");
            return (Criteria) this;
        }

        public Criteria andShopCityNameNotLike(String value) {
            addCriterion("shop_city_name not like", value, "shopCityName");
            return (Criteria) this;
        }

        public Criteria andShopCityNameIn(List<String> values) {
            addCriterion("shop_city_name in", values, "shopCityName");
            return (Criteria) this;
        }

        public Criteria andShopCityNameNotIn(List<String> values) {
            addCriterion("shop_city_name not in", values, "shopCityName");
            return (Criteria) this;
        }

        public Criteria andShopCityNameBetween(String value1, String value2) {
            addCriterion("shop_city_name between", value1, value2, "shopCityName");
            return (Criteria) this;
        }

        public Criteria andShopCityNameNotBetween(String value1, String value2) {
            addCriterion("shop_city_name not between", value1, value2, "shopCityName");
            return (Criteria) this;
        }

        public Criteria andShopDistrictCodeIsNull() {
            addCriterion("shop_district_code is null");
            return (Criteria) this;
        }

        public Criteria andShopDistrictCodeIsNotNull() {
            addCriterion("shop_district_code is not null");
            return (Criteria) this;
        }

        public Criteria andShopDistrictCodeEqualTo(String value) {
            addCriterion("shop_district_code =", value, "shopDistrictCode");
            return (Criteria) this;
        }

        public Criteria andShopDistrictCodeNotEqualTo(String value) {
            addCriterion("shop_district_code <>", value, "shopDistrictCode");
            return (Criteria) this;
        }

        public Criteria andShopDistrictCodeGreaterThan(String value) {
            addCriterion("shop_district_code >", value, "shopDistrictCode");
            return (Criteria) this;
        }

        public Criteria andShopDistrictCodeGreaterThanOrEqualTo(String value) {
            addCriterion("shop_district_code >=", value, "shopDistrictCode");
            return (Criteria) this;
        }

        public Criteria andShopDistrictCodeLessThan(String value) {
            addCriterion("shop_district_code <", value, "shopDistrictCode");
            return (Criteria) this;
        }

        public Criteria andShopDistrictCodeLessThanOrEqualTo(String value) {
            addCriterion("shop_district_code <=", value, "shopDistrictCode");
            return (Criteria) this;
        }

        public Criteria andShopDistrictCodeLike(String value) {
            addCriterion("shop_district_code like", value, "shopDistrictCode");
            return (Criteria) this;
        }

        public Criteria andShopDistrictCodeNotLike(String value) {
            addCriterion("shop_district_code not like", value, "shopDistrictCode");
            return (Criteria) this;
        }

        public Criteria andShopDistrictCodeIn(List<String> values) {
            addCriterion("shop_district_code in", values, "shopDistrictCode");
            return (Criteria) this;
        }

        public Criteria andShopDistrictCodeNotIn(List<String> values) {
            addCriterion("shop_district_code not in", values, "shopDistrictCode");
            return (Criteria) this;
        }

        public Criteria andShopDistrictCodeBetween(String value1, String value2) {
            addCriterion("shop_district_code between", value1, value2, "shopDistrictCode");
            return (Criteria) this;
        }

        public Criteria andShopDistrictCodeNotBetween(String value1, String value2) {
            addCriterion("shop_district_code not between", value1, value2, "shopDistrictCode");
            return (Criteria) this;
        }

        public Criteria andShopDistrictNameIsNull() {
            addCriterion("shop_district_name is null");
            return (Criteria) this;
        }

        public Criteria andShopDistrictNameIsNotNull() {
            addCriterion("shop_district_name is not null");
            return (Criteria) this;
        }

        public Criteria andShopDistrictNameEqualTo(String value) {
            addCriterion("shop_district_name =", value, "shopDistrictName");
            return (Criteria) this;
        }

        public Criteria andShopDistrictNameNotEqualTo(String value) {
            addCriterion("shop_district_name <>", value, "shopDistrictName");
            return (Criteria) this;
        }

        public Criteria andShopDistrictNameGreaterThan(String value) {
            addCriterion("shop_district_name >", value, "shopDistrictName");
            return (Criteria) this;
        }

        public Criteria andShopDistrictNameGreaterThanOrEqualTo(String value) {
            addCriterion("shop_district_name >=", value, "shopDistrictName");
            return (Criteria) this;
        }

        public Criteria andShopDistrictNameLessThan(String value) {
            addCriterion("shop_district_name <", value, "shopDistrictName");
            return (Criteria) this;
        }

        public Criteria andShopDistrictNameLessThanOrEqualTo(String value) {
            addCriterion("shop_district_name <=", value, "shopDistrictName");
            return (Criteria) this;
        }

        public Criteria andShopDistrictNameLike(String value) {
            addCriterion("shop_district_name like", value, "shopDistrictName");
            return (Criteria) this;
        }

        public Criteria andShopDistrictNameNotLike(String value) {
            addCriterion("shop_district_name not like", value, "shopDistrictName");
            return (Criteria) this;
        }

        public Criteria andShopDistrictNameIn(List<String> values) {
            addCriterion("shop_district_name in", values, "shopDistrictName");
            return (Criteria) this;
        }

        public Criteria andShopDistrictNameNotIn(List<String> values) {
            addCriterion("shop_district_name not in", values, "shopDistrictName");
            return (Criteria) this;
        }

        public Criteria andShopDistrictNameBetween(String value1, String value2) {
            addCriterion("shop_district_name between", value1, value2, "shopDistrictName");
            return (Criteria) this;
        }

        public Criteria andShopDistrictNameNotBetween(String value1, String value2) {
            addCriterion("shop_district_name not between", value1, value2, "shopDistrictName");
            return (Criteria) this;
        }

        public Criteria andShopCountyCodeIsNull() {
            addCriterion("shop_county_code is null");
            return (Criteria) this;
        }

        public Criteria andShopCountyCodeIsNotNull() {
            addCriterion("shop_county_code is not null");
            return (Criteria) this;
        }

        public Criteria andShopCountyCodeEqualTo(String value) {
            addCriterion("shop_county_code =", value, "shopCountyCode");
            return (Criteria) this;
        }

        public Criteria andShopCountyCodeNotEqualTo(String value) {
            addCriterion("shop_county_code <>", value, "shopCountyCode");
            return (Criteria) this;
        }

        public Criteria andShopCountyCodeGreaterThan(String value) {
            addCriterion("shop_county_code >", value, "shopCountyCode");
            return (Criteria) this;
        }

        public Criteria andShopCountyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("shop_county_code >=", value, "shopCountyCode");
            return (Criteria) this;
        }

        public Criteria andShopCountyCodeLessThan(String value) {
            addCriterion("shop_county_code <", value, "shopCountyCode");
            return (Criteria) this;
        }

        public Criteria andShopCountyCodeLessThanOrEqualTo(String value) {
            addCriterion("shop_county_code <=", value, "shopCountyCode");
            return (Criteria) this;
        }

        public Criteria andShopCountyCodeLike(String value) {
            addCriterion("shop_county_code like", value, "shopCountyCode");
            return (Criteria) this;
        }

        public Criteria andShopCountyCodeNotLike(String value) {
            addCriterion("shop_county_code not like", value, "shopCountyCode");
            return (Criteria) this;
        }

        public Criteria andShopCountyCodeIn(List<String> values) {
            addCriterion("shop_county_code in", values, "shopCountyCode");
            return (Criteria) this;
        }

        public Criteria andShopCountyCodeNotIn(List<String> values) {
            addCriterion("shop_county_code not in", values, "shopCountyCode");
            return (Criteria) this;
        }

        public Criteria andShopCountyCodeBetween(String value1, String value2) {
            addCriterion("shop_county_code between", value1, value2, "shopCountyCode");
            return (Criteria) this;
        }

        public Criteria andShopCountyCodeNotBetween(String value1, String value2) {
            addCriterion("shop_county_code not between", value1, value2, "shopCountyCode");
            return (Criteria) this;
        }

        public Criteria andShopCountyNameIsNull() {
            addCriterion("shop_county_name is null");
            return (Criteria) this;
        }

        public Criteria andShopCountyNameIsNotNull() {
            addCriterion("shop_county_name is not null");
            return (Criteria) this;
        }

        public Criteria andShopCountyNameEqualTo(String value) {
            addCriterion("shop_county_name =", value, "shopCountyName");
            return (Criteria) this;
        }

        public Criteria andShopCountyNameNotEqualTo(String value) {
            addCriterion("shop_county_name <>", value, "shopCountyName");
            return (Criteria) this;
        }

        public Criteria andShopCountyNameGreaterThan(String value) {
            addCriterion("shop_county_name >", value, "shopCountyName");
            return (Criteria) this;
        }

        public Criteria andShopCountyNameGreaterThanOrEqualTo(String value) {
            addCriterion("shop_county_name >=", value, "shopCountyName");
            return (Criteria) this;
        }

        public Criteria andShopCountyNameLessThan(String value) {
            addCriterion("shop_county_name <", value, "shopCountyName");
            return (Criteria) this;
        }

        public Criteria andShopCountyNameLessThanOrEqualTo(String value) {
            addCriterion("shop_county_name <=", value, "shopCountyName");
            return (Criteria) this;
        }

        public Criteria andShopCountyNameLike(String value) {
            addCriterion("shop_county_name like", value, "shopCountyName");
            return (Criteria) this;
        }

        public Criteria andShopCountyNameNotLike(String value) {
            addCriterion("shop_county_name not like", value, "shopCountyName");
            return (Criteria) this;
        }

        public Criteria andShopCountyNameIn(List<String> values) {
            addCriterion("shop_county_name in", values, "shopCountyName");
            return (Criteria) this;
        }

        public Criteria andShopCountyNameNotIn(List<String> values) {
            addCriterion("shop_county_name not in", values, "shopCountyName");
            return (Criteria) this;
        }

        public Criteria andShopCountyNameBetween(String value1, String value2) {
            addCriterion("shop_county_name between", value1, value2, "shopCountyName");
            return (Criteria) this;
        }

        public Criteria andShopCountyNameNotBetween(String value1, String value2) {
            addCriterion("shop_county_name not between", value1, value2, "shopCountyName");
            return (Criteria) this;
        }

        public Criteria andShopAddressIsNull() {
            addCriterion("shop_address is null");
            return (Criteria) this;
        }

        public Criteria andShopAddressIsNotNull() {
            addCriterion("shop_address is not null");
            return (Criteria) this;
        }

        public Criteria andShopAddressEqualTo(String value) {
            addCriterion("shop_address =", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressNotEqualTo(String value) {
            addCriterion("shop_address <>", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressGreaterThan(String value) {
            addCriterion("shop_address >", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressGreaterThanOrEqualTo(String value) {
            addCriterion("shop_address >=", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressLessThan(String value) {
            addCriterion("shop_address <", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressLessThanOrEqualTo(String value) {
            addCriterion("shop_address <=", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressLike(String value) {
            addCriterion("shop_address like", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressNotLike(String value) {
            addCriterion("shop_address not like", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressIn(List<String> values) {
            addCriterion("shop_address in", values, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressNotIn(List<String> values) {
            addCriterion("shop_address not in", values, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressBetween(String value1, String value2) {
            addCriterion("shop_address between", value1, value2, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressNotBetween(String value1, String value2) {
            addCriterion("shop_address not between", value1, value2, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopLongitudeIsNull() {
            addCriterion("shop_longitude is null");
            return (Criteria) this;
        }

        public Criteria andShopLongitudeIsNotNull() {
            addCriterion("shop_longitude is not null");
            return (Criteria) this;
        }

        public Criteria andShopLongitudeEqualTo(String value) {
            addCriterion("shop_longitude =", value, "shopLongitude");
            return (Criteria) this;
        }

        public Criteria andShopLongitudeNotEqualTo(String value) {
            addCriterion("shop_longitude <>", value, "shopLongitude");
            return (Criteria) this;
        }

        public Criteria andShopLongitudeGreaterThan(String value) {
            addCriterion("shop_longitude >", value, "shopLongitude");
            return (Criteria) this;
        }

        public Criteria andShopLongitudeGreaterThanOrEqualTo(String value) {
            addCriterion("shop_longitude >=", value, "shopLongitude");
            return (Criteria) this;
        }

        public Criteria andShopLongitudeLessThan(String value) {
            addCriterion("shop_longitude <", value, "shopLongitude");
            return (Criteria) this;
        }

        public Criteria andShopLongitudeLessThanOrEqualTo(String value) {
            addCriterion("shop_longitude <=", value, "shopLongitude");
            return (Criteria) this;
        }

        public Criteria andShopLongitudeLike(String value) {
            addCriterion("shop_longitude like", value, "shopLongitude");
            return (Criteria) this;
        }

        public Criteria andShopLongitudeNotLike(String value) {
            addCriterion("shop_longitude not like", value, "shopLongitude");
            return (Criteria) this;
        }

        public Criteria andShopLongitudeIn(List<String> values) {
            addCriterion("shop_longitude in", values, "shopLongitude");
            return (Criteria) this;
        }

        public Criteria andShopLongitudeNotIn(List<String> values) {
            addCriterion("shop_longitude not in", values, "shopLongitude");
            return (Criteria) this;
        }

        public Criteria andShopLongitudeBetween(String value1, String value2) {
            addCriterion("shop_longitude between", value1, value2, "shopLongitude");
            return (Criteria) this;
        }

        public Criteria andShopLongitudeNotBetween(String value1, String value2) {
            addCriterion("shop_longitude not between", value1, value2, "shopLongitude");
            return (Criteria) this;
        }

        public Criteria andShopLatitudeIsNull() {
            addCriterion("shop_latitude is null");
            return (Criteria) this;
        }

        public Criteria andShopLatitudeIsNotNull() {
            addCriterion("shop_latitude is not null");
            return (Criteria) this;
        }

        public Criteria andShopLatitudeEqualTo(String value) {
            addCriterion("shop_latitude =", value, "shopLatitude");
            return (Criteria) this;
        }

        public Criteria andShopLatitudeNotEqualTo(String value) {
            addCriterion("shop_latitude <>", value, "shopLatitude");
            return (Criteria) this;
        }

        public Criteria andShopLatitudeGreaterThan(String value) {
            addCriterion("shop_latitude >", value, "shopLatitude");
            return (Criteria) this;
        }

        public Criteria andShopLatitudeGreaterThanOrEqualTo(String value) {
            addCriterion("shop_latitude >=", value, "shopLatitude");
            return (Criteria) this;
        }

        public Criteria andShopLatitudeLessThan(String value) {
            addCriterion("shop_latitude <", value, "shopLatitude");
            return (Criteria) this;
        }

        public Criteria andShopLatitudeLessThanOrEqualTo(String value) {
            addCriterion("shop_latitude <=", value, "shopLatitude");
            return (Criteria) this;
        }

        public Criteria andShopLatitudeLike(String value) {
            addCriterion("shop_latitude like", value, "shopLatitude");
            return (Criteria) this;
        }

        public Criteria andShopLatitudeNotLike(String value) {
            addCriterion("shop_latitude not like", value, "shopLatitude");
            return (Criteria) this;
        }

        public Criteria andShopLatitudeIn(List<String> values) {
            addCriterion("shop_latitude in", values, "shopLatitude");
            return (Criteria) this;
        }

        public Criteria andShopLatitudeNotIn(List<String> values) {
            addCriterion("shop_latitude not in", values, "shopLatitude");
            return (Criteria) this;
        }

        public Criteria andShopLatitudeBetween(String value1, String value2) {
            addCriterion("shop_latitude between", value1, value2, "shopLatitude");
            return (Criteria) this;
        }

        public Criteria andShopLatitudeNotBetween(String value1, String value2) {
            addCriterion("shop_latitude not between", value1, value2, "shopLatitude");
            return (Criteria) this;
        }

        public Criteria andShopPhotoIsNull() {
            addCriterion("shop_photo is null");
            return (Criteria) this;
        }

        public Criteria andShopPhotoIsNotNull() {
            addCriterion("shop_photo is not null");
            return (Criteria) this;
        }

        public Criteria andShopPhotoEqualTo(String value) {
            addCriterion("shop_photo =", value, "shopPhoto");
            return (Criteria) this;
        }

        public Criteria andShopPhotoNotEqualTo(String value) {
            addCriterion("shop_photo <>", value, "shopPhoto");
            return (Criteria) this;
        }

        public Criteria andShopPhotoGreaterThan(String value) {
            addCriterion("shop_photo >", value, "shopPhoto");
            return (Criteria) this;
        }

        public Criteria andShopPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("shop_photo >=", value, "shopPhoto");
            return (Criteria) this;
        }

        public Criteria andShopPhotoLessThan(String value) {
            addCriterion("shop_photo <", value, "shopPhoto");
            return (Criteria) this;
        }

        public Criteria andShopPhotoLessThanOrEqualTo(String value) {
            addCriterion("shop_photo <=", value, "shopPhoto");
            return (Criteria) this;
        }

        public Criteria andShopPhotoLike(String value) {
            addCriterion("shop_photo like", value, "shopPhoto");
            return (Criteria) this;
        }

        public Criteria andShopPhotoNotLike(String value) {
            addCriterion("shop_photo not like", value, "shopPhoto");
            return (Criteria) this;
        }

        public Criteria andShopPhotoIn(List<String> values) {
            addCriterion("shop_photo in", values, "shopPhoto");
            return (Criteria) this;
        }

        public Criteria andShopPhotoNotIn(List<String> values) {
            addCriterion("shop_photo not in", values, "shopPhoto");
            return (Criteria) this;
        }

        public Criteria andShopPhotoBetween(String value1, String value2) {
            addCriterion("shop_photo between", value1, value2, "shopPhoto");
            return (Criteria) this;
        }

        public Criteria andShopPhotoNotBetween(String value1, String value2) {
            addCriterion("shop_photo not between", value1, value2, "shopPhoto");
            return (Criteria) this;
        }

        public Criteria andShopPhoneIsNull() {
            addCriterion("shop_phone is null");
            return (Criteria) this;
        }

        public Criteria andShopPhoneIsNotNull() {
            addCriterion("shop_phone is not null");
            return (Criteria) this;
        }

        public Criteria andShopPhoneEqualTo(String value) {
            addCriterion("shop_phone =", value, "shopPhone");
            return (Criteria) this;
        }

        public Criteria andShopPhoneNotEqualTo(String value) {
            addCriterion("shop_phone <>", value, "shopPhone");
            return (Criteria) this;
        }

        public Criteria andShopPhoneGreaterThan(String value) {
            addCriterion("shop_phone >", value, "shopPhone");
            return (Criteria) this;
        }

        public Criteria andShopPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("shop_phone >=", value, "shopPhone");
            return (Criteria) this;
        }

        public Criteria andShopPhoneLessThan(String value) {
            addCriterion("shop_phone <", value, "shopPhone");
            return (Criteria) this;
        }

        public Criteria andShopPhoneLessThanOrEqualTo(String value) {
            addCriterion("shop_phone <=", value, "shopPhone");
            return (Criteria) this;
        }

        public Criteria andShopPhoneLike(String value) {
            addCriterion("shop_phone like", value, "shopPhone");
            return (Criteria) this;
        }

        public Criteria andShopPhoneNotLike(String value) {
            addCriterion("shop_phone not like", value, "shopPhone");
            return (Criteria) this;
        }

        public Criteria andShopPhoneIn(List<String> values) {
            addCriterion("shop_phone in", values, "shopPhone");
            return (Criteria) this;
        }

        public Criteria andShopPhoneNotIn(List<String> values) {
            addCriterion("shop_phone not in", values, "shopPhone");
            return (Criteria) this;
        }

        public Criteria andShopPhoneBetween(String value1, String value2) {
            addCriterion("shop_phone between", value1, value2, "shopPhone");
            return (Criteria) this;
        }

        public Criteria andShopPhoneNotBetween(String value1, String value2) {
            addCriterion("shop_phone not between", value1, value2, "shopPhone");
            return (Criteria) this;
        }

        public Criteria andShopCategoriesIsNull() {
            addCriterion("shop_categories is null");
            return (Criteria) this;
        }

        public Criteria andShopCategoriesIsNotNull() {
            addCriterion("shop_categories is not null");
            return (Criteria) this;
        }

        public Criteria andShopCategoriesEqualTo(String value) {
            addCriterion("shop_categories =", value, "shopCategories");
            return (Criteria) this;
        }

        public Criteria andShopCategoriesNotEqualTo(String value) {
            addCriterion("shop_categories <>", value, "shopCategories");
            return (Criteria) this;
        }

        public Criteria andShopCategoriesGreaterThan(String value) {
            addCriterion("shop_categories >", value, "shopCategories");
            return (Criteria) this;
        }

        public Criteria andShopCategoriesGreaterThanOrEqualTo(String value) {
            addCriterion("shop_categories >=", value, "shopCategories");
            return (Criteria) this;
        }

        public Criteria andShopCategoriesLessThan(String value) {
            addCriterion("shop_categories <", value, "shopCategories");
            return (Criteria) this;
        }

        public Criteria andShopCategoriesLessThanOrEqualTo(String value) {
            addCriterion("shop_categories <=", value, "shopCategories");
            return (Criteria) this;
        }

        public Criteria andShopCategoriesLike(String value) {
            addCriterion("shop_categories like", value, "shopCategories");
            return (Criteria) this;
        }

        public Criteria andShopCategoriesNotLike(String value) {
            addCriterion("shop_categories not like", value, "shopCategories");
            return (Criteria) this;
        }

        public Criteria andShopCategoriesIn(List<String> values) {
            addCriterion("shop_categories in", values, "shopCategories");
            return (Criteria) this;
        }

        public Criteria andShopCategoriesNotIn(List<String> values) {
            addCriterion("shop_categories not in", values, "shopCategories");
            return (Criteria) this;
        }

        public Criteria andShopCategoriesBetween(String value1, String value2) {
            addCriterion("shop_categories between", value1, value2, "shopCategories");
            return (Criteria) this;
        }

        public Criteria andShopCategoriesNotBetween(String value1, String value2) {
            addCriterion("shop_categories not between", value1, value2, "shopCategories");
            return (Criteria) this;
        }

        public Criteria andShopPropertiesIsNull() {
            addCriterion("shop_properties is null");
            return (Criteria) this;
        }

        public Criteria andShopPropertiesIsNotNull() {
            addCriterion("shop_properties is not null");
            return (Criteria) this;
        }

        public Criteria andShopPropertiesEqualTo(String value) {
            addCriterion("shop_properties =", value, "shopProperties");
            return (Criteria) this;
        }

        public Criteria andShopPropertiesNotEqualTo(String value) {
            addCriterion("shop_properties <>", value, "shopProperties");
            return (Criteria) this;
        }

        public Criteria andShopPropertiesGreaterThan(String value) {
            addCriterion("shop_properties >", value, "shopProperties");
            return (Criteria) this;
        }

        public Criteria andShopPropertiesGreaterThanOrEqualTo(String value) {
            addCriterion("shop_properties >=", value, "shopProperties");
            return (Criteria) this;
        }

        public Criteria andShopPropertiesLessThan(String value) {
            addCriterion("shop_properties <", value, "shopProperties");
            return (Criteria) this;
        }

        public Criteria andShopPropertiesLessThanOrEqualTo(String value) {
            addCriterion("shop_properties <=", value, "shopProperties");
            return (Criteria) this;
        }

        public Criteria andShopPropertiesLike(String value) {
            addCriterion("shop_properties like", value, "shopProperties");
            return (Criteria) this;
        }

        public Criteria andShopPropertiesNotLike(String value) {
            addCriterion("shop_properties not like", value, "shopProperties");
            return (Criteria) this;
        }

        public Criteria andShopPropertiesIn(List<String> values) {
            addCriterion("shop_properties in", values, "shopProperties");
            return (Criteria) this;
        }

        public Criteria andShopPropertiesNotIn(List<String> values) {
            addCriterion("shop_properties not in", values, "shopProperties");
            return (Criteria) this;
        }

        public Criteria andShopPropertiesBetween(String value1, String value2) {
            addCriterion("shop_properties between", value1, value2, "shopProperties");
            return (Criteria) this;
        }

        public Criteria andShopPropertiesNotBetween(String value1, String value2) {
            addCriterion("shop_properties not between", value1, value2, "shopProperties");
            return (Criteria) this;
        }

        public Criteria andShopOpenTimeIsNull() {
            addCriterion("shop_open_time is null");
            return (Criteria) this;
        }

        public Criteria andShopOpenTimeIsNotNull() {
            addCriterion("shop_open_time is not null");
            return (Criteria) this;
        }

        public Criteria andShopOpenTimeEqualTo(Integer value) {
            addCriterion("shop_open_time =", value, "shopOpenTime");
            return (Criteria) this;
        }

        public Criteria andShopOpenTimeNotEqualTo(Integer value) {
            addCriterion("shop_open_time <>", value, "shopOpenTime");
            return (Criteria) this;
        }

        public Criteria andShopOpenTimeGreaterThan(Integer value) {
            addCriterion("shop_open_time >", value, "shopOpenTime");
            return (Criteria) this;
        }

        public Criteria andShopOpenTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("shop_open_time >=", value, "shopOpenTime");
            return (Criteria) this;
        }

        public Criteria andShopOpenTimeLessThan(Integer value) {
            addCriterion("shop_open_time <", value, "shopOpenTime");
            return (Criteria) this;
        }

        public Criteria andShopOpenTimeLessThanOrEqualTo(Integer value) {
            addCriterion("shop_open_time <=", value, "shopOpenTime");
            return (Criteria) this;
        }

        public Criteria andShopOpenTimeIn(List<Integer> values) {
            addCriterion("shop_open_time in", values, "shopOpenTime");
            return (Criteria) this;
        }

        public Criteria andShopOpenTimeNotIn(List<Integer> values) {
            addCriterion("shop_open_time not in", values, "shopOpenTime");
            return (Criteria) this;
        }

        public Criteria andShopOpenTimeBetween(Integer value1, Integer value2) {
            addCriterion("shop_open_time between", value1, value2, "shopOpenTime");
            return (Criteria) this;
        }

        public Criteria andShopOpenTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("shop_open_time not between", value1, value2, "shopOpenTime");
            return (Criteria) this;
        }

        public Criteria andShopCloseTimeIsNull() {
            addCriterion("shop_close_time is null");
            return (Criteria) this;
        }

        public Criteria andShopCloseTimeIsNotNull() {
            addCriterion("shop_close_time is not null");
            return (Criteria) this;
        }

        public Criteria andShopCloseTimeEqualTo(Integer value) {
            addCriterion("shop_close_time =", value, "shopCloseTime");
            return (Criteria) this;
        }

        public Criteria andShopCloseTimeNotEqualTo(Integer value) {
            addCriterion("shop_close_time <>", value, "shopCloseTime");
            return (Criteria) this;
        }

        public Criteria andShopCloseTimeGreaterThan(Integer value) {
            addCriterion("shop_close_time >", value, "shopCloseTime");
            return (Criteria) this;
        }

        public Criteria andShopCloseTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("shop_close_time >=", value, "shopCloseTime");
            return (Criteria) this;
        }

        public Criteria andShopCloseTimeLessThan(Integer value) {
            addCriterion("shop_close_time <", value, "shopCloseTime");
            return (Criteria) this;
        }

        public Criteria andShopCloseTimeLessThanOrEqualTo(Integer value) {
            addCriterion("shop_close_time <=", value, "shopCloseTime");
            return (Criteria) this;
        }

        public Criteria andShopCloseTimeIn(List<Integer> values) {
            addCriterion("shop_close_time in", values, "shopCloseTime");
            return (Criteria) this;
        }

        public Criteria andShopCloseTimeNotIn(List<Integer> values) {
            addCriterion("shop_close_time not in", values, "shopCloseTime");
            return (Criteria) this;
        }

        public Criteria andShopCloseTimeBetween(Integer value1, Integer value2) {
            addCriterion("shop_close_time between", value1, value2, "shopCloseTime");
            return (Criteria) this;
        }

        public Criteria andShopCloseTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("shop_close_time not between", value1, value2, "shopCloseTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("create_user like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("create_user not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNull() {
            addCriterion("update_user is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNotNull() {
            addCriterion("update_user is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserEqualTo(String value) {
            addCriterion("update_user =", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotEqualTo(String value) {
            addCriterion("update_user <>", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThan(String value) {
            addCriterion("update_user >", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThanOrEqualTo(String value) {
            addCriterion("update_user >=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThan(String value) {
            addCriterion("update_user <", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThanOrEqualTo(String value) {
            addCriterion("update_user <=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLike(String value) {
            addCriterion("update_user like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotLike(String value) {
            addCriterion("update_user not like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIn(List<String> values) {
            addCriterion("update_user in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotIn(List<String> values) {
            addCriterion("update_user not in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserBetween(String value1, String value2) {
            addCriterion("update_user between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotBetween(String value1, String value2) {
            addCriterion("update_user not between", value1, value2, "updateUser");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}