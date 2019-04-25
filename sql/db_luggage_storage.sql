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

 Date: 24/04/2019 09:29:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for galaxy_admin
-- ----------------------------
DROP TABLE IF EXISTS `galaxy_admin`;
CREATE TABLE `galaxy_admin` (
  `admin_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '管理员id(主键)',
  `admin_no` varchar(10) NOT NULL COMMENT '管理员编号(随机生成)',
  `admin_name` varchar(20) NOT NULL COMMENT '管理员姓名',
  `admin_phone` varchar(11) NOT NULL COMMENT '管理员电话',
  `admin_type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '管理员类型(1-普通管理员，2-超级管理员,3-系统)',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '记录状态(1-删除、0-正常)',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员表 - 按照编号前缀的不同具有不同权限';

-- ----------------------------
-- Table structure for galaxy_fixed_charge_calculation_detail
-- ----------------------------
DROP TABLE IF EXISTS `galaxy_fixed_charge_calculation_detail`;
CREATE TABLE `galaxy_fixed_charge_calculation_detail` (
  `fixed_rule_id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '固定计费主键id',
  `calculation_units_id` tinyint(1) NOT NULL COMMENT '计费单位(1-元/件/天，2-元/件/次，3-元/件)',
  `fee_per_unit` varchar(5) NOT NULL COMMENT '单位金额',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '记录状态(1-删除、0-正常)',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`fixed_rule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='固定计费表';

-- ----------------------------
-- Table structure for galaxy_luggage_lost_compensation_record
-- ----------------------------
DROP TABLE IF EXISTS `galaxy_luggage_lost_compensation_record`;
CREATE TABLE `galaxy_luggage_lost_compensation_record` (
  `luggage_lost_compensation_record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '赔偿记录主键id',
  `lost_registration_record_id` bigint(20) NOT NULL COMMENT '行李遗失登记主键id',
  `admin_id` bigint(20) NOT NULL COMMENT '管理员id(冗余)',
  `admin_name` varchar(20) NOT NULL COMMENT '管理员姓名(冗余)',
  `depositor_name` varchar(20) NOT NULL COMMENT '赔偿对象姓名(冗余)',
  `depositor_phone` varchar(11) NOT NULL COMMENT '赔偿对象电话(冗余)',
  `luggage_type_id` bigint(10) NOT NULL COMMENT '行李类型主键id(冗余)',
  `compensation_fee` varchar(6) NOT NULL COMMENT '赔偿金额',
  `remark` varchar(31) NOT NULL DEFAULT '' COMMENT '备注',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '记录状态(1-删除、0-正常)',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`luggage_lost_compensation_record_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='行李遗失赔偿记录表';

-- ----------------------------
-- Table structure for galaxy_luggage_lost_registration_record
-- ----------------------------
DROP TABLE IF EXISTS `galaxy_luggage_lost_registration_record`;
CREATE TABLE `galaxy_luggage_lost_registration_record` (
  `lost_registration_record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '行李遗失登记主键id',
  `admin_id` bigint(20) NOT NULL COMMENT '管理员id',
  `admin_name` varchar(20) NOT NULL COMMENT '管理员姓名',
  `luggage_id` bigint(20) NOT NULL COMMENT '行李寄存主键id',
  `luggage_record_no` varchar(20) NOT NULL COMMENT '行李寄存记录编号(冗余)',
  `luggage_type_id` bigint(10) NOT NULL COMMENT '行李类型主键id(冗余)',
  `depositor_name` varchar(20) NOT NULL COMMENT '行李遗失登记者姓名(冗余)',
  `depositor_phone` varchar(11) NOT NULL COMMENT '行李遗失登记者联系方式(冗余)',
  `remark` varchar(63) NOT NULL DEFAULT '' COMMENT '行李遗失登记备注',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '记录状态(1-删除、0-正常)',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`lost_registration_record_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='行李遗失登记记录表';

-- ----------------------------
-- Table structure for galaxy_luggage_overdue_record
-- ----------------------------
DROP TABLE IF EXISTS `galaxy_luggage_overdue_record`;
CREATE TABLE `galaxy_luggage_overdue_record` (
  `luggage_overdue_record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '行李逾期未取记录主键id',
  `admin_id` bigint(20) NOT NULL COMMENT '管理员主键id',
  `admin_name` varchar(20) NOT NULL COMMENT '管理员姓名',
  `luggage_id` bigint(20) NOT NULL COMMENT '行李寄存主键id',
  `luggage_record_no` varchar(20) NOT NULL COMMENT '行李寄存记录编号(冗余)',
  `depositor_name` varchar(20) NOT NULL COMMENT '行李寄存者姓名(冗余)',
  `depositor_phone` varchar(11) NOT NULL COMMENT '行李寄存者联系方式(冗余)',
  `remark` varchar(63) NOT NULL DEFAULT '' COMMENT '行李逾期未取备注',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '逾期清理状态(1-逾期，2-已清理作废)',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '记录状态(1-删除、0-正常)',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`luggage_overdue_record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='行李逾期未取记录表';

-- ----------------------------
-- Table structure for galaxy_luggage_storage_record
-- ----------------------------
DROP TABLE IF EXISTS `galaxy_luggage_storage_record`;
CREATE TABLE `galaxy_luggage_storage_record` (
  `luggage_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '行李寄存主键id',
  `luggage_record_no` varchar(20) NOT NULL COMMENT '行李寄存记录编号',
  `luggage_type_id` bigint(11) NOT NULL COMMENT '行李类型主键id',
  `admin_id` bigint(20) NOT NULL COMMENT '管理员id',
  `admin_name` varchar(20) NOT NULL COMMENT '管理员姓名(冗余)',
  `admin_phone` varchar(11) NOT NULL COMMENT '管理员电话(冗余)',
  `depositor_name` varchar(20) NOT NULL COMMENT '寄存人姓名',
  `depositor_phone` varchar(11) NOT NULL COMMENT '寄存人电话',
  `remark` varchar(125) NOT NULL DEFAULT '' COMMENT '备注',
  `storage_start_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始寄存时间',
  `storage_end_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '寄存结束时间',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '行李寄存状态(0-寄存中，1-已取件，2-已逾期)\n，3-已逾期，4-已作废)',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '记录状态(1-删除、0-正常)',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`luggage_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='行李寄存-取件表';

-- ----------------------------
-- Table structure for galaxy_luggage_type
-- ----------------------------
DROP TABLE IF EXISTS `galaxy_luggage_type`;
CREATE TABLE `galaxy_luggage_type` (
  `luggage_type_id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '行李类型主键id',
  `luggage_type` tinyint(2) NOT NULL DEFAULT '1' COMMENT '行李类型(1-普通物件、2-易碎物件、3-贵重物件)',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '记录状态(1-删除、0-正常)',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`luggage_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='行李类型表';

-- ----------------------------
-- Table structure for galaxy_pickup_luggage_record
-- ----------------------------
DROP TABLE IF EXISTS `galaxy_pickup_luggage_record`;
CREATE TABLE `galaxy_pickup_luggage_record` (
  `pickup_luggage_record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '行李取件记录主键id',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='行李取件记录表';

-- ----------------------------
-- Table structure for galaxy_turnover_record
-- ----------------------------
DROP TABLE IF EXISTS `galaxy_turnover_record`;
CREATE TABLE `galaxy_turnover_record` (
  `turnover_record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '营业额记录表主键id',
  `luggage_id` bigint(20) NOT NULL COMMENT '行李寄存主键id',
  `admin_id` bigint(20) NOT NULL COMMENT '管理员主键id(冗余)',
  `fee` varchar(10) NOT NULL COMMENT '费用',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '记录状态(1-删除、0-正常)',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`turnover_record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='营业额记录表';

SET FOREIGN_KEY_CHECKS = 1;
