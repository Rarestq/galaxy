/*
 Navicat Premium Data Transfer

 Source Server         : galaxy
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : db_luggage_storage

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 23/05/2019 20:10:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for galaxy_admin
-- ----------------------------
DROP TABLE IF EXISTS `galaxy_admin`;
CREATE TABLE `galaxy_admin` (
  `admin_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '管理员id(主键)',
  `admin_no` varchar(30) NOT NULL COMMENT '管理员编号(随机生成)',
  `admin_name` varchar(20) NOT NULL COMMENT '管理员姓名',
  `admin_phone` varchar(11) NOT NULL COMMENT '管理员电话',
  `admin_type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '管理员类型(1-普通管理员，2-超级管理员,3-系统)',
  `password` varchar(20) NOT NULL COMMENT '登录密码',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '记录状态(1-删除、0-正常)',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COMMENT='管理员表 - 按照编号前缀的不同具有不同权限';

-- ----------------------------
-- Table structure for galaxy_charge_calculation_rule
-- ----------------------------
DROP TABLE IF EXISTS `galaxy_charge_calculation_rule`;
CREATE TABLE `galaxy_charge_calculation_rule` (
  `calculation_rule_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '计费规则主键id',
  `luggage_type_id` bigint(20) NOT NULL COMMENT '行李类型主键id',
  `calculation_units_id` tinyint(1) NOT NULL COMMENT '计费单位(1-元/件/天，2-元/件/次，3-元/件)',
  `fee_per_unit` varchar(5) NOT NULL COMMENT '单位金额',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '记录状态(1-删除、0-正常)',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`calculation_rule_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='计费规则表';

-- ----------------------------
-- Table structure for galaxy_common_calculate_rule_detail
-- ----------------------------
DROP TABLE IF EXISTS `galaxy_common_calculate_rule_detail`;
CREATE TABLE `galaxy_common_calculate_rule_detail` (
  `common_rule_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '普通物件计费规则的主键id',
  `calculation_rule_id` bigint(20) NOT NULL COMMENT '计费规则主键id',
  `calculation_units_id` tinyint(1) NOT NULL COMMENT '计费单位(1-元/件/小时，2-元/件/次，3-元/件)',
  `fee_per_unit` varchar(20) NOT NULL COMMENT '单位金额',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '记录状态(1-删除、0-正常)',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`common_rule_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='普通物价计费规则表';

-- ----------------------------
-- Table structure for galaxy_fee_type
-- ----------------------------
DROP TABLE IF EXISTS `galaxy_fee_type`;
CREATE TABLE `galaxy_fee_type` (
  `fee_type_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '费用类型主键id',
  `fee_type_desc` varchar(12) NOT NULL COMMENT '费用类型描述',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '记录状态(1-删除、0-正常)',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`fee_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='费用类型表';

-- ----------------------------
-- Table structure for galaxy_fragile_calculate_rule_detail
-- ----------------------------
DROP TABLE IF EXISTS `galaxy_fragile_calculate_rule_detail`;
CREATE TABLE `galaxy_fragile_calculate_rule_detail` (
  `fragile_rule_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '易碎行李计费规则主键id',
  `calculation_rule_id` bigint(20) NOT NULL COMMENT '计费规则主键id',
  `calculation_units_id` tinyint(1) NOT NULL COMMENT '计费单位(1-元/件/天，2-元/件/次，3-元/件)',
  `fee_per_unit` varchar(20) NOT NULL COMMENT '单位金额',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '记录状态(1-删除、0-正常)',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`fragile_rule_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='易碎行李计费规则表';

-- ----------------------------
-- Table structure for galaxy_luggage_cabinet
-- ----------------------------
DROP TABLE IF EXISTS `galaxy_luggage_cabinet`;
CREATE TABLE `galaxy_luggage_cabinet` (
  `luggage_cabinet_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '行李柜主键id',
  `luggage_cabinet_no` varchar(30) NOT NULL COMMENT '行李柜编号',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '行李柜状态0-空闲、1-已占用、2-逾期占用、3-维修中',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`luggage_cabinet_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COMMENT='行李寄存柜表';

-- ----------------------------
-- Table structure for galaxy_luggage_lost_compensation_record
-- ----------------------------
DROP TABLE IF EXISTS `galaxy_luggage_lost_compensation_record`;
CREATE TABLE `galaxy_luggage_lost_compensation_record` (
  `luggage_lost_compensation_record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '赔偿记录主键id',
  `lost_compensate_record_no` varchar(30) NOT NULL COMMENT '遗失赔偿记录编号',
  `lost_registration_record_id` bigint(20) NOT NULL COMMENT '行李遗失登记主键id',
  `admin_id` bigint(20) NOT NULL COMMENT '管理员id(冗余)',
  `admin_name` varchar(20) NOT NULL COMMENT '管理员姓名(冗余)',
  `depositor_name` varchar(20) NOT NULL COMMENT '赔偿对象姓名(冗余)',
  `depositor_phone` varchar(11) NOT NULL COMMENT '赔偿对象电话(冗余)',
  `luggage_type_id` bigint(10) NOT NULL COMMENT '行李类型主键id(冗余)',
  `compensation_fee` varchar(6) NOT NULL COMMENT '赔偿金额',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '记录状态(1-删除、0-正常)',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`luggage_lost_compensation_record_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='行李遗失赔偿记录表';

-- ----------------------------
-- Table structure for galaxy_luggage_lost_registration_record
-- ----------------------------
DROP TABLE IF EXISTS `galaxy_luggage_lost_registration_record`;
CREATE TABLE `galaxy_luggage_lost_registration_record` (
  `lost_registration_record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '行李遗失登记主键id',
  `register_record_no` varchar(30) NOT NULL COMMENT '行李遗失登记记录编号',
  `admin_id` bigint(20) NOT NULL COMMENT '管理员id',
  `admin_name` varchar(20) NOT NULL COMMENT '管理员姓名',
  `luggage_id` bigint(20) NOT NULL COMMENT '行李寄存主键id',
  `luggage_record_no` varchar(30) NOT NULL COMMENT '行李寄存记录编号(冗余)',
  `luggage_type_id` bigint(10) NOT NULL COMMENT '行李类型主键id(冗余)',
  `depositor_name` varchar(20) NOT NULL COMMENT '行李遗失登记者姓名(冗余)',
  `depositor_phone` varchar(11) NOT NULL COMMENT '行李遗失登记者联系方式(冗余)',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '遗失记录状态(0-遗失，1-已赔偿)',
  `remark` varchar(63) NOT NULL DEFAULT '' COMMENT '行李遗失登记备注',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '记录状态(1-删除、0-正常)',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`lost_registration_record_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='行李遗失登记记录表';

-- ----------------------------
-- Table structure for galaxy_luggage_overdue_record
-- ----------------------------
DROP TABLE IF EXISTS `galaxy_luggage_overdue_record`;
CREATE TABLE `galaxy_luggage_overdue_record` (
  `luggage_overdue_record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '行李逾期未取记录主键id',
  `overdue_record_no` varchar(30) NOT NULL COMMENT '逾期记录编号',
  `admin_id` bigint(20) NOT NULL COMMENT '管理员主键id',
  `admin_name` varchar(20) NOT NULL COMMENT '管理员姓名',
  `luggage_id` bigint(20) NOT NULL COMMENT '行李寄存主键id',
  `luggage_record_no` varchar(30) NOT NULL COMMENT '行李寄存记录编号(冗余)',
  `depositor_name` varchar(20) NOT NULL COMMENT '行李寄存者姓名(冗余)',
  `depositor_phone` varchar(11) NOT NULL COMMENT '行李寄存者联系方式(冗余)',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '行李逾期未取备注',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '逾期清理状态(1-逾期，2-已清理作废)',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '记录状态(1-删除、0-正常)',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`luggage_overdue_record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT='行李逾期未取记录表';

-- ----------------------------
-- Table structure for galaxy_luggage_storage_record
-- ----------------------------
DROP TABLE IF EXISTS `galaxy_luggage_storage_record`;
CREATE TABLE `galaxy_luggage_storage_record` (
  `luggage_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '行李寄存主键id',
  `luggage_record_no` varchar(30) NOT NULL COMMENT '行李寄存记录编号',
  `luggage_type_id` bigint(11) NOT NULL COMMENT '行李类型主键id',
  `cabinet_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '寄存柜主键id',
  `cabinet_no` varchar(30) NOT NULL DEFAULT '-' COMMENT '行李寄存柜编号',
  `admin_id` bigint(20) NOT NULL COMMENT '管理员id',
  `admin_name` varchar(20) NOT NULL COMMENT '管理员姓名(冗余)',
  `admin_phone` varchar(11) NOT NULL COMMENT '管理员电话(冗余)',
  `depositor_name` varchar(20) NOT NULL COMMENT '寄存人姓名',
  `depositor_phone` varchar(11) NOT NULL COMMENT '寄存人电话',
  `remark` varchar(125) NOT NULL DEFAULT '' COMMENT '备注',
  `storage_start_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始寄存时间',
  `storage_end_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '寄存结束时间',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '行李寄存状态(0-寄存中，1-已取件，2-已逾期，3-逾期取件，4-行李已遗失)\n，3-已逾期，4-已作废)',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '记录状态(1-删除、0-正常)',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`luggage_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COMMENT='行李寄存-取件表';

-- ----------------------------
-- Table structure for galaxy_luggage_type
-- ----------------------------
DROP TABLE IF EXISTS `galaxy_luggage_type`;
CREATE TABLE `galaxy_luggage_type` (
  `luggage_type_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '行李类型主键id',
  `luggage_type` varchar(15) NOT NULL COMMENT '行李类型(1-普通物件、2-易碎物件、3-贵重物件)',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '记录状态(1-删除、0-正常)',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`luggage_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='行李类型表';

-- ----------------------------
-- Table structure for galaxy_pickup_luggage_record
-- ----------------------------
DROP TABLE IF EXISTS `galaxy_pickup_luggage_record`;
CREATE TABLE `galaxy_pickup_luggage_record` (
  `pickup_luggage_record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '行李取件记录主键id',
  `pickup_record_no` varchar(30) NOT NULL COMMENT '取件记录编号',
  `luggage_id` bigint(20) NOT NULL COMMENT '行李寄存主键id',
  `admin_id` bigint(20) NOT NULL COMMENT '管理员id(冗余)',
  `admin_name` varchar(20) NOT NULL COMMENT '管理员姓名(冗余)',
  `picker_name` varchar(20) NOT NULL COMMENT '取件人姓名(冗余)',
  `picker_phone` varchar(11) NOT NULL COMMENT '取件人电话(冗余)',
  `pickup_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '取件类型(0-正常取件，1-行李有遗失，2-逾期取件)',
  `pick_up_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '取件时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '记录状态(1-删除、0-正常)',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pickup_luggage_record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='行李取件记录表';

-- ----------------------------
-- Table structure for galaxy_turnover_record
-- ----------------------------
DROP TABLE IF EXISTS `galaxy_turnover_record`;
CREATE TABLE `galaxy_turnover_record` (
  `turnover_record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '营业额记录表主键id',
  `luggage_id` bigint(20) NOT NULL COMMENT '行李寄存主键id',
  `admin_id` bigint(20) NOT NULL COMMENT '管理员主键id(冗余)',
  `admin_name` varchar(20) NOT NULL COMMENT '管理员姓名',
  `calculation_rule_id` bigint(20) NOT NULL COMMENT '计费规则主键id',
  `fee` varchar(10) NOT NULL COMMENT '费用',
  `fee_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '费用类型(0-寄存费用,1-逾期费用,2-赔偿费用)',
  `fee_type_desc` varchar(12) NOT NULL DEFAULT '寄存费用' COMMENT '费用类型描述',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '记录状态(1-删除、0-正常)',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`turnover_record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COMMENT='营业额记录表';

-- ----------------------------
-- Table structure for galaxy_valuable_calculate_rule_detail
-- ----------------------------
DROP TABLE IF EXISTS `galaxy_valuable_calculate_rule_detail`;
CREATE TABLE `galaxy_valuable_calculate_rule_detail` (
  `valuable_rule_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '贵重行李规则主键id',
  `calculation_rule_id` bigint(20) NOT NULL COMMENT '计费规则主键id',
  `calculation_units_id` tinyint(1) NOT NULL COMMENT '计费单位(1-元/件/天，2-元/件/次，3-元/件)',
  `fee_per_unit` varchar(20) NOT NULL COMMENT '单位金额',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '记录状态(1-删除、0-正常)',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`valuable_rule_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='贵重行李计费规则表';

SET FOREIGN_KEY_CHECKS = 1;
