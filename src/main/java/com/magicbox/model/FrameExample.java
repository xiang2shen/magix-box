package com.magicbox.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FrameExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public FrameExample() {
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

        public Criteria andFrameCodeIsNull() {
            addCriterion("frame_code is null");
            return (Criteria) this;
        }

        public Criteria andFrameCodeIsNotNull() {
            addCriterion("frame_code is not null");
            return (Criteria) this;
        }

        public Criteria andFrameCodeEqualTo(String value) {
            addCriterion("frame_code =", value, "frameCode");
            return (Criteria) this;
        }

        public Criteria andFrameCodeNotEqualTo(String value) {
            addCriterion("frame_code <>", value, "frameCode");
            return (Criteria) this;
        }

        public Criteria andFrameCodeGreaterThan(String value) {
            addCriterion("frame_code >", value, "frameCode");
            return (Criteria) this;
        }

        public Criteria andFrameCodeGreaterThanOrEqualTo(String value) {
            addCriterion("frame_code >=", value, "frameCode");
            return (Criteria) this;
        }

        public Criteria andFrameCodeLessThan(String value) {
            addCriterion("frame_code <", value, "frameCode");
            return (Criteria) this;
        }

        public Criteria andFrameCodeLessThanOrEqualTo(String value) {
            addCriterion("frame_code <=", value, "frameCode");
            return (Criteria) this;
        }

        public Criteria andFrameCodeLike(String value) {
            addCriterion("frame_code like", value, "frameCode");
            return (Criteria) this;
        }

        public Criteria andFrameCodeNotLike(String value) {
            addCriterion("frame_code not like", value, "frameCode");
            return (Criteria) this;
        }

        public Criteria andFrameCodeIn(List<String> values) {
            addCriterion("frame_code in", values, "frameCode");
            return (Criteria) this;
        }

        public Criteria andFrameCodeNotIn(List<String> values) {
            addCriterion("frame_code not in", values, "frameCode");
            return (Criteria) this;
        }

        public Criteria andFrameCodeBetween(String value1, String value2) {
            addCriterion("frame_code between", value1, value2, "frameCode");
            return (Criteria) this;
        }

        public Criteria andFrameCodeNotBetween(String value1, String value2) {
            addCriterion("frame_code not between", value1, value2, "frameCode");
            return (Criteria) this;
        }

        public Criteria andFrameStatusIsNull() {
            addCriterion("frame_status is null");
            return (Criteria) this;
        }

        public Criteria andFrameStatusIsNotNull() {
            addCriterion("frame_status is not null");
            return (Criteria) this;
        }

        public Criteria andFrameStatusEqualTo(Integer value) {
            addCriterion("frame_status =", value, "frameStatus");
            return (Criteria) this;
        }

        public Criteria andFrameStatusNotEqualTo(Integer value) {
            addCriterion("frame_status <>", value, "frameStatus");
            return (Criteria) this;
        }

        public Criteria andFrameStatusGreaterThan(Integer value) {
            addCriterion("frame_status >", value, "frameStatus");
            return (Criteria) this;
        }

        public Criteria andFrameStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("frame_status >=", value, "frameStatus");
            return (Criteria) this;
        }

        public Criteria andFrameStatusLessThan(Integer value) {
            addCriterion("frame_status <", value, "frameStatus");
            return (Criteria) this;
        }

        public Criteria andFrameStatusLessThanOrEqualTo(Integer value) {
            addCriterion("frame_status <=", value, "frameStatus");
            return (Criteria) this;
        }

        public Criteria andFrameStatusIn(List<Integer> values) {
            addCriterion("frame_status in", values, "frameStatus");
            return (Criteria) this;
        }

        public Criteria andFrameStatusNotIn(List<Integer> values) {
            addCriterion("frame_status not in", values, "frameStatus");
            return (Criteria) this;
        }

        public Criteria andFrameStatusBetween(Integer value1, Integer value2) {
            addCriterion("frame_status between", value1, value2, "frameStatus");
            return (Criteria) this;
        }

        public Criteria andFrameStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("frame_status not between", value1, value2, "frameStatus");
            return (Criteria) this;
        }

        public Criteria andFrameModelIsNull() {
            addCriterion("frame_model is null");
            return (Criteria) this;
        }

        public Criteria andFrameModelIsNotNull() {
            addCriterion("frame_model is not null");
            return (Criteria) this;
        }

        public Criteria andFrameModelEqualTo(String value) {
            addCriterion("frame_model =", value, "frameModel");
            return (Criteria) this;
        }

        public Criteria andFrameModelNotEqualTo(String value) {
            addCriterion("frame_model <>", value, "frameModel");
            return (Criteria) this;
        }

        public Criteria andFrameModelGreaterThan(String value) {
            addCriterion("frame_model >", value, "frameModel");
            return (Criteria) this;
        }

        public Criteria andFrameModelGreaterThanOrEqualTo(String value) {
            addCriterion("frame_model >=", value, "frameModel");
            return (Criteria) this;
        }

        public Criteria andFrameModelLessThan(String value) {
            addCriterion("frame_model <", value, "frameModel");
            return (Criteria) this;
        }

        public Criteria andFrameModelLessThanOrEqualTo(String value) {
            addCriterion("frame_model <=", value, "frameModel");
            return (Criteria) this;
        }

        public Criteria andFrameModelLike(String value) {
            addCriterion("frame_model like", value, "frameModel");
            return (Criteria) this;
        }

        public Criteria andFrameModelNotLike(String value) {
            addCriterion("frame_model not like", value, "frameModel");
            return (Criteria) this;
        }

        public Criteria andFrameModelIn(List<String> values) {
            addCriterion("frame_model in", values, "frameModel");
            return (Criteria) this;
        }

        public Criteria andFrameModelNotIn(List<String> values) {
            addCriterion("frame_model not in", values, "frameModel");
            return (Criteria) this;
        }

        public Criteria andFrameModelBetween(String value1, String value2) {
            addCriterion("frame_model between", value1, value2, "frameModel");
            return (Criteria) this;
        }

        public Criteria andFrameModelNotBetween(String value1, String value2) {
            addCriterion("frame_model not between", value1, value2, "frameModel");
            return (Criteria) this;
        }

        public Criteria andCardCodeIsNull() {
            addCriterion("card_code is null");
            return (Criteria) this;
        }

        public Criteria andCardCodeIsNotNull() {
            addCriterion("card_code is not null");
            return (Criteria) this;
        }

        public Criteria andCardCodeEqualTo(String value) {
            addCriterion("card_code =", value, "cardCode");
            return (Criteria) this;
        }

        public Criteria andCardCodeNotEqualTo(String value) {
            addCriterion("card_code <>", value, "cardCode");
            return (Criteria) this;
        }

        public Criteria andCardCodeGreaterThan(String value) {
            addCriterion("card_code >", value, "cardCode");
            return (Criteria) this;
        }

        public Criteria andCardCodeGreaterThanOrEqualTo(String value) {
            addCriterion("card_code >=", value, "cardCode");
            return (Criteria) this;
        }

        public Criteria andCardCodeLessThan(String value) {
            addCriterion("card_code <", value, "cardCode");
            return (Criteria) this;
        }

        public Criteria andCardCodeLessThanOrEqualTo(String value) {
            addCriterion("card_code <=", value, "cardCode");
            return (Criteria) this;
        }

        public Criteria andCardCodeLike(String value) {
            addCriterion("card_code like", value, "cardCode");
            return (Criteria) this;
        }

        public Criteria andCardCodeNotLike(String value) {
            addCriterion("card_code not like", value, "cardCode");
            return (Criteria) this;
        }

        public Criteria andCardCodeIn(List<String> values) {
            addCriterion("card_code in", values, "cardCode");
            return (Criteria) this;
        }

        public Criteria andCardCodeNotIn(List<String> values) {
            addCriterion("card_code not in", values, "cardCode");
            return (Criteria) this;
        }

        public Criteria andCardCodeBetween(String value1, String value2) {
            addCriterion("card_code between", value1, value2, "cardCode");
            return (Criteria) this;
        }

        public Criteria andCardCodeNotBetween(String value1, String value2) {
            addCriterion("card_code not between", value1, value2, "cardCode");
            return (Criteria) this;
        }

        public Criteria andInternetFlowIsNull() {
            addCriterion("internet_flow is null");
            return (Criteria) this;
        }

        public Criteria andInternetFlowIsNotNull() {
            addCriterion("internet_flow is not null");
            return (Criteria) this;
        }

        public Criteria andInternetFlowEqualTo(String value) {
            addCriterion("internet_flow =", value, "internetFlow");
            return (Criteria) this;
        }

        public Criteria andInternetFlowNotEqualTo(String value) {
            addCriterion("internet_flow <>", value, "internetFlow");
            return (Criteria) this;
        }

        public Criteria andInternetFlowGreaterThan(String value) {
            addCriterion("internet_flow >", value, "internetFlow");
            return (Criteria) this;
        }

        public Criteria andInternetFlowGreaterThanOrEqualTo(String value) {
            addCriterion("internet_flow >=", value, "internetFlow");
            return (Criteria) this;
        }

        public Criteria andInternetFlowLessThan(String value) {
            addCriterion("internet_flow <", value, "internetFlow");
            return (Criteria) this;
        }

        public Criteria andInternetFlowLessThanOrEqualTo(String value) {
            addCriterion("internet_flow <=", value, "internetFlow");
            return (Criteria) this;
        }

        public Criteria andInternetFlowLike(String value) {
            addCriterion("internet_flow like", value, "internetFlow");
            return (Criteria) this;
        }

        public Criteria andInternetFlowNotLike(String value) {
            addCriterion("internet_flow not like", value, "internetFlow");
            return (Criteria) this;
        }

        public Criteria andInternetFlowIn(List<String> values) {
            addCriterion("internet_flow in", values, "internetFlow");
            return (Criteria) this;
        }

        public Criteria andInternetFlowNotIn(List<String> values) {
            addCriterion("internet_flow not in", values, "internetFlow");
            return (Criteria) this;
        }

        public Criteria andInternetFlowBetween(String value1, String value2) {
            addCriterion("internet_flow between", value1, value2, "internetFlow");
            return (Criteria) this;
        }

        public Criteria andInternetFlowNotBetween(String value1, String value2) {
            addCriterion("internet_flow not between", value1, value2, "internetFlow");
            return (Criteria) this;
        }

        public Criteria andProductTimeIsNull() {
            addCriterion("product_time is null");
            return (Criteria) this;
        }

        public Criteria andProductTimeIsNotNull() {
            addCriterion("product_time is not null");
            return (Criteria) this;
        }

        public Criteria andProductTimeEqualTo(Date value) {
            addCriterion("product_time =", value, "productTime");
            return (Criteria) this;
        }

        public Criteria andProductTimeNotEqualTo(Date value) {
            addCriterion("product_time <>", value, "productTime");
            return (Criteria) this;
        }

        public Criteria andProductTimeGreaterThan(Date value) {
            addCriterion("product_time >", value, "productTime");
            return (Criteria) this;
        }

        public Criteria andProductTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("product_time >=", value, "productTime");
            return (Criteria) this;
        }

        public Criteria andProductTimeLessThan(Date value) {
            addCriterion("product_time <", value, "productTime");
            return (Criteria) this;
        }

        public Criteria andProductTimeLessThanOrEqualTo(Date value) {
            addCriterion("product_time <=", value, "productTime");
            return (Criteria) this;
        }

        public Criteria andProductTimeIn(List<Date> values) {
            addCriterion("product_time in", values, "productTime");
            return (Criteria) this;
        }

        public Criteria andProductTimeNotIn(List<Date> values) {
            addCriterion("product_time not in", values, "productTime");
            return (Criteria) this;
        }

        public Criteria andProductTimeBetween(Date value1, Date value2) {
            addCriterion("product_time between", value1, value2, "productTime");
            return (Criteria) this;
        }

        public Criteria andProductTimeNotBetween(Date value1, Date value2) {
            addCriterion("product_time not between", value1, value2, "productTime");
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