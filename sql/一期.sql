DROP TABLE IF EXISTS `t_mem_seller`;
CREATE TABLE `t_mem_seller` (
  `id` bigint(18) NOT NULL AUTO_INCREMENT,
  `seller_mobile` varchar(32) NULL COMMENT '手机号',
  `seller_status` tinyint(4) NOT NULL COMMENT '状态(1-正常,0-冻结)',
  
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(32) DEFAULT NULL,
  `update_user` varchar(32) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_seller_mobile` (`seller_mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='卖家表';

DROP TABLE IF EXISTS `t_mem_member`;
CREATE TABLE `t_mem_member` (
  `id` bigint(18) NOT NULL AUTO_INCREMENT,
  `seller_id` bigint(18) NULL COMMENT '卖家ID',
  `open_id` varchar(64) NULL COMMENT '微信/支付宝openId',
  
  `id_card` varchar(32) NULL COMMENT '身份证号',
  `realname` varchar(32) NULL COMMENT '真实姓名',
  `nickname` varchar(32) NULL COMMENT '昵称',
  `gender` tinyint(4) NULL COMMENT '性别',
  `birth_date` date NULL COMMENT '出生日期',
  `email` varchar(64) NULL COMMENT '邮箱',
  `photo` varchar(256) NULL COMMENT '头像照片',
  
  `register_time` datetime NOT NULL COMMENT '注册时间',
  `last_login_time` datetime NULL COMMENT '最近登录时间',
  
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(32) DEFAULT NULL,
  `update_user` varchar(32) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_seller_id` (`seller_id`),
  UNIQUE KEY `uk_open_id` (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='会员表';

DROP TABLE IF EXISTS `t_shop_shop`;
CREATE TABLE `t_shop_shop` (
  `id` bigint(18) NOT NULL AUTO_INCREMENT,
  `shop_code` varchar(32) NOT NULL COMMENT '店铺编号',
  `shop_name` varchar(64) NOT NULL COMMENT '店铺名称',
  `shop_desc` varchar(256) NULL COMMENT '店铺描述',
  `shop_status` tinyint(4) NOT NULL COMMENT '店铺状态(1-正常,2-待开业,3-已关店)',
  `seller_id` bigint(18) NOT NULL COMMENT '店主ID',
  
  `shop_province_code` varchar(16) NULL COMMENT '店铺省份编号',
  `shop_province_name` varchar(16) NULL COMMENT '店铺省份名称',
  `shop_city_code` varchar(16) NULL COMMENT '店铺城市编号',
  `shop_city_name` varchar(16) NULL COMMENT '店铺城市名称',
  `shop_district_code` varchar(16) NULL COMMENT '店铺区编号',
  `shop_district_name` varchar(16) NULL COMMENT '店铺区名称',
  `shop_county_code` varchar(16) NULL COMMENT '店铺街道编号',
  `shop_county_name` varchar(16) NULL COMMENT '店铺街道名称',
  `shop_address` varchar(128) NULL COMMENT '店铺地址',
  `shop_longitude` varchar(16) NULL COMMENT '店铺经度',
  `shop_latitude` varchar(16) NULL COMMENT '店铺纬度',
  `shop_photo` varchar(256) NULL COMMENT '店铺图片',
  
  `shop_phone` varchar(32) NULL COMMENT '店铺电话',
  `shop_categories` varchar(128) NULL COMMENT '店铺分类csv',
  `shop_properties` varchar(128) NULL COMMENT '店铺特性csv',
  `shop_open_time` int(11) NULL COMMENT '店铺开门时间(单位：分钟)',
  `shop_close_time` int(11) NULL COMMENT '店铺关门时间(单位：分钟)',
  
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(32) DEFAULT NULL,
  `update_user` varchar(32) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  KEY `idx_shop_status` (`shop_status`),
  KEY `idx_shop_province_code` (`shop_province_code`),
  KEY `idx_shop_city_code` (`shop_city_code`),
  KEY `idx_shop_district_code` (`shop_district_code`),
  KEY `idx_seller_id` (`seller_id`),
  UNIQUE KEY `idx_shop_code` (`shop_code`),
  KEY `idx_shop_name` (`shop_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='店铺表';

DROP TABLE IF EXISTS `t_shop_frame`;
CREATE TABLE `t_shop_frame` (
  `id` bigint(18) NOT NULL AUTO_INCREMENT,
  `shop_code` varchar(32) NULL COMMENT '店铺编号',
  `frame_code` varchar(32) NOT NULL COMMENT '框架编号',
  `frame_status` tinyint(4) NOT NULL COMMENT '框架状态(1-正常)',
  `frame_model` varchar(32) NULL COMMENT '框架型号',
  `card_code` varchar(32) NULL COMMENT '框架卡号',
  `internet_flow` varchar(32) NULL COMMENT '网卡流量',
  `product_time` datetime NULL COMMENT '出厂时间',
  
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(32) DEFAULT NULL,
  `update_user` varchar(32) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  KEY `idx_shop_code` (`shop_code`),
  UNIQUE KEY `uk_frame_code` (`frame_code`),
  KEY `idx_frame_status` (`frame_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='框架表';

DROP TABLE IF EXISTS `t_shop_box`;
CREATE TABLE `t_shop_box` (
  `id` bigint(18) NOT NULL AUTO_INCREMENT,
  `shop_code` varchar(32) NULL COMMENT '店铺编号',
  `frame_code` varchar(32) NOT NULL COMMENT '框架编号',
  `box_code` varchar(32) NOT NULL COMMENT '盒子编号',
  `product_code` varchar(32) NULL COMMENT '商品编号',
  `box_position` int(11) NULL COMMENT '盒子位置',
  `box_status` tinyint(4) NOT NULL COMMENT '盒子状态(1-正常)',
  `box_model` varchar(32) NULL COMMENT '盒子型号',
  `capacity` int(11) NULL COMMENT '盒子容量',
  `product_stock` int(11) NOT NULL COMMENT '盒子里商品的库存',
  
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(32) DEFAULT NULL,
  `update_user` varchar(32) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  KEY `idx_shop_code` (`shop_code`),
  KEY `idx_frame_code` (`frame_code`),
  KEY `idx_product_code` (`product_code`),
  UNIQUE KEY `uk_box_code` (`box_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='盒子表';

DROP TABLE IF EXISTS `t_prd_product`;
CREATE TABLE `t_prd_product` (
  `id` bigint(18) NOT NULL AUTO_INCREMENT,
  `shop_code` varchar(32) NOT NULL COMMENT '店铺编号',
  
  `product_code` varchar(32) NOT NULL COMMENT '商品编号',
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_desc` varchar(256) NULL COMMENT '商品描述',
  `product_price` int(11) NOT NULL COMMENT '商品价格',
  `product_stock` int(11) NULL COMMENT '商品库存',
  
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(32) DEFAULT NULL,
  `update_user` varchar(32) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  KEY `idx_product_code` (`product_code`),
  KEY `idx_product_name` (`product_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='商品表';

DROP TABLE IF EXISTS `t_prd_product_image`;
CREATE TABLE `t_prd_product_image` (
  `id` bigint(18) NOT NULL AUTO_INCREMENT,
  
  `product_code` varchar(32) NOT NULL COMMENT '商品编号',
  `img_url` varchar(512) NOT NULL COMMENT '商品图片',
  `sort` int(11) NOT NULL COMMENT '排序',
  
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(32) DEFAULT NULL,
  `update_user` varchar(32) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  KEY `idx_product_code` (`product_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='商品图片表';

DROP TABLE IF EXISTS `t_ord_order`;
CREATE TABLE `t_ord_order` (
  `id` bigint(18) NOT NULL AUTO_INCREMENT,
  
  `order_code` varchar(32) NOT NULL COMMENT '订单编号',
  `order_status` tinyint(4) NOT NULL COMMENT '订单状态',
  
  `member_id` bigint(18) NOT NULL COMMENT '买家会员ID',
  `member_open_id` varchar(64) NULL COMMENT '微信/支付宝openId',
  `seller_id` bigint(18) NOT NULL COMMENT '卖家ID',
  
  `shop_code` varchar(32) NOT NULL COMMENT '店铺编号',
  `frame_code` varchar(32) NOT NULL COMMENT '设备编号',
  `box_code` varchar(32) NOT NULL COMMENT '盒子编号',
  
  `product_code` varchar(32) NOT NULL COMMENT '商品编号',
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` int(11) NOT NULL COMMENT '商品价格',
  `product_quantity` int(11) NOT NULL COMMENT '商品数量',
  `product_img` varchar(512) NOT NULL COMMENT '商品图片',
  
  `due_total` int(11) NOT NULL COMMENT '应付金额',
  `real_total` int(11) NOT NULL COMMENT '实付金额',
  `discount` int(11) NOT NULL COMMENT '优惠金额',
  
  `pay_way` tinyint(4) NOT NULL COMMENT '支付方式',
  `pay_time` datetime NULL COMMENT '支付时间',
  `pay_code` varchar(64) NULL COMMENT '支付编号',
  
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(32) DEFAULT NULL,
  `update_user` varchar(32) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_code` (`order_code`),
  KEY `idx_member_id` (`member_id`),
  KEY `idx_seller_id` (`seller_id`),
  KEY `idx_shop_code` (`shop_code`),
  KEY `idx_product_code` (`product_code`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='订单表';

DROP TABLE IF EXISTS `t_prd_product_tag`;
CREATE TABLE `t_prd_product_tag` (
  `id` bigint(18) NOT NULL AUTO_INCREMENT,
  
  `tag_name` varchar(32) NOT NULL COMMENT '标签名称',
  `shop_code` varchar(32) NOT NULL COMMENT '店铺编号',
  
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(32) DEFAULT NULL,
  `update_user` varchar(32) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  KEY `idx_tag_name` (`tag_name`),
  KEY `idx_shop_code` (`shop_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='商品标签表';

DROP TABLE IF EXISTS `t_prd_product_tag_rel`;
CREATE TABLE `t_prd_product_tag_rel` (
  `id` bigint(18) NOT NULL AUTO_INCREMENT,
  
  `tag_id` bigint(18) NOT NULL COMMENT '标签id',
  `product_code` varchar(32) NOT NULL COMMENT '商品编号',
  
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(32) DEFAULT NULL,
  `update_user` varchar(32) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  KEY `idx_tag_id` (`tag_id`),
  KEY `idx_product_code` (`product_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='商品标签关联表';

DROP TABLE IF EXISTS `t_shop_frame_health_log`;
CREATE TABLE `t_shop_frame_health_log` (
  `id` bigint(18) NOT NULL AUTO_INCREMENT,
  `frame_code` varchar(32) NOT NULL COMMENT '框架编号',
  `box_code` varchar(32) NULL COMMENT '盒子编号',
  `log_content` varchar(256) NOT NULL COMMENT '日志内容',
  
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(32) DEFAULT NULL,
  `update_user` varchar(32) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  KEY `idx_frame_code` (`frame_code`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='框架健康日志表';

DROP TABLE IF EXISTS `t_shop_shop_tag`;
CREATE TABLE `t_shop_shop_tag` (
  `id` bigint(18) NOT NULL AUTO_INCREMENT,
  
  `tag_name` varchar(32) NOT NULL COMMENT '标签名称',
  
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(32) DEFAULT NULL,
  `update_user` varchar(32) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  KEY `idx_tag_name` (`tag_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='店铺标签表';

DROP TABLE IF EXISTS `t_shop_shop_tag_rel`;
CREATE TABLE `t_shop_shop_tag_rel` (
  `id` bigint(18) NOT NULL AUTO_INCREMENT,
  
  `tag_id` bigint(18) NOT NULL COMMENT '标签id',
  `shop_code` varchar(32) NOT NULL COMMENT '店铺编号',
  
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(32) DEFAULT NULL,
  `update_user` varchar(32) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  KEY `idx_tag_id` (`tag_id`),
  KEY `idx_shop_code` (`shop_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='店铺标签关联表';

INSERT INTO `t_shop_shop_tag` (`tag_name`) VALUES ('超市');
INSERT INTO `t_shop_shop_tag` (`tag_name`) VALUES ('酒店');