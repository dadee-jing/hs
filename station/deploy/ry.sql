/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : ry

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-06-14 15:56:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for alex_table_info
-- ----------------------------
DROP TABLE IF EXISTS `alex_table_info`;
CREATE TABLE `alex_table_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `alex_id` int(8) DEFAULT NULL COMMENT '序号',
  `alex_num` int(8) DEFAULT NULL COMMENT '轴数',
  `alex_limit` decimal(12,3) DEFAULT NULL COMMENT '限重单位吨，最多3位小数',
  `alex_start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `alex_end_time` datetime DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alex_table_info
-- ----------------------------
INSERT INTO `alex_table_info` VALUES ('1', '1', '1', '10.000', '2018-11-05 08:20:54', '2018-12-31 08:20:58');
INSERT INTO `alex_table_info` VALUES ('2', '2', '2', '20.000', '2018-11-09 08:21:18', '2018-12-31 08:21:25');
INSERT INTO `alex_table_info` VALUES ('3', '3', '6', '55.000', '2018-11-05 08:21:49', '2018-12-31 08:21:54');

-- ----------------------------
-- Table structure for basic_table_info
-- ----------------------------
DROP TABLE IF EXISTS `basic_table_info`;
CREATE TABLE `basic_table_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `table_type` int(2) DEFAULT NULL COMMENT '1=轴数限重；2=视频录制时间；3=Led固定文本；4=车型限重；5=限重模式',
  `table_version` int(2) DEFAULT NULL COMMENT '版本号',
  `table_date` timestamp NULL DEFAULT NULL COMMENT '版本时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of basic_table_info
-- ----------------------------
INSERT INTO `basic_table_info` VALUES ('1', '1', '31', '2018-11-01 20:23:39');
INSERT INTO `basic_table_info` VALUES ('2', '2', '41', '2018-11-01 20:23:41');
INSERT INTO `basic_table_info` VALUES ('3', '3', '51', '2018-11-01 20:23:45');

-- ----------------------------
-- Table structure for config_data
-- ----------------------------
DROP TABLE IF EXISTS `config_data`;
CREATE TABLE `config_data` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `key` varchar(255) DEFAULT '' COMMENT 'key',
  `value` varchar(255) DEFAULT '' COMMENT 'value',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of config_data
-- ----------------------------
INSERT INTO `config_data` VALUES ('1', 'center_url', 'http://localhost:9999/public/weightData/add');
INSERT INTO `config_data` VALUES ('2', 'ftp_path', 'E:\\ftp_copy\\');
INSERT INTO `config_data` VALUES ('3', 'static_file_path', 'E:\\ftp_copy');
INSERT INTO `config_data` VALUES ('4', 'do_upload_tag', '0');
INSERT INTO `config_data` VALUES ('5', 'foshan.tcp.host', '19.128.109.103');
INSERT INTO `config_data` VALUES ('6', 'foshan.tcp.port', '10101');

-- ----------------------------
-- Table structure for device_data
-- ----------------------------
DROP TABLE IF EXISTS `device_data`;
CREATE TABLE `device_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `index_code` varchar(50) DEFAULT NULL COMMENT '设备编号',
  `check_date` datetime DEFAULT NULL COMMENT '检测时间',
  `device_type` char(2) DEFAULT NULL COMMENT '设备类型，0称重、1抓拍、2大屏',
  `check_id` int(8) DEFAULT NULL COMMENT '序号',
  `ip_address` char(32) DEFAULT NULL COMMENT '设备IP',
  `check_state` int(2) DEFAULT NULL COMMENT '设备状态',
  `exception_desc` varchar(255) DEFAULT NULL COMMENT '异常描述',
  `upload_tag` tinyint(3) DEFAULT NULL COMMENT '上传标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8 COMMENT='设备信息';

-- ----------------------------
-- Records of device_data
-- ----------------------------
INSERT INTO `device_data` VALUES ('1', null, '2018-11-16 19:06:30', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('2', null, '2018-11-16 19:06:54', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('3', null, '2018-11-16 19:07:30', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('4', null, '2018-11-16 21:05:22', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('5', null, '2018-11-16 21:08:31', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('6', null, '2018-11-16 21:08:34', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('7', null, '2018-11-19 08:49:58', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('8', null, '2018-11-19 12:50:42', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('9', null, '2018-11-19 12:50:48', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('10', null, '2018-11-19 14:23:28', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('11', null, '2018-11-19 16:41:09', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('12', null, '2018-11-19 18:20:55', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('13', null, '2018-11-19 18:49:41', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('14', null, '2018-11-19 18:56:24', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('15', null, '2018-11-19 19:04:49', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('16', null, '2018-11-19 19:06:32', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('17', null, '2018-11-20 08:23:30', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('18', null, '2018-11-20 09:07:24', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('19', null, '2018-11-20 09:43:02', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('20', null, '2018-11-20 09:43:04', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('21', null, '2018-11-20 20:37:05', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('22', null, '2018-11-20 20:37:23', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('23', null, '2018-11-20 20:37:24', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('24', null, '2018-11-20 20:37:24', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('25', null, '2018-11-20 20:45:17', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('26', null, '2018-11-20 20:49:33', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('27', null, '2018-11-21 08:10:47', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('28', null, '2018-11-22 07:59:52', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('29', null, '2018-11-22 20:57:40', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('30', null, '2018-11-23 20:03:42', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('31', null, '2018-11-26 08:24:14', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('32', null, '2018-11-27 08:20:38', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('33', null, '2018-11-29 08:50:01', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('34', null, '2018-12-04 19:17:06', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('35', null, null, null, '0', null, '0', null, null);
INSERT INTO `device_data` VALUES ('36', null, null, null, '0', null, '0', null, null);
INSERT INTO `device_data` VALUES ('37', null, null, null, '0', null, '0', null, null);
INSERT INTO `device_data` VALUES ('38', null, '2018-12-06 21:01:02', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('39', null, '2018-12-07 07:47:16', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('40', null, '2018-12-07 10:31:58', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('41', null, '2018-12-07 10:32:00', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('42', null, '2018-12-07 10:35:13', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('43', null, '2018-12-07 10:35:15', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('44', null, '2018-12-07 10:35:15', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('45', null, '2018-12-07 10:53:03', '1', '1', '192.168.1.100', '1', null, null);
INSERT INTO `device_data` VALUES ('46', null, '2019-05-31 11:47:29', '1', '1', '192.168.1.32', '0', null, null);
INSERT INTO `device_data` VALUES ('47', null, '2019-05-31 11:47:29', '1', '2', '192.168.1.33', '0', null, null);
INSERT INTO `device_data` VALUES ('48', null, '2019-05-31 11:47:29', '1', '2', '192.168.1.33', '0', null, null);
INSERT INTO `device_data` VALUES ('49', null, '2019-06-06 09:08:06', '1', '1', '192.168.1.34', '0', null, null);
INSERT INTO `device_data` VALUES ('50', null, '2019-06-06 09:08:08', '0', '1', '192.168.1.200', '0', null, null);
INSERT INTO `device_data` VALUES ('51', null, '2019-06-06 09:08:08', '1', '2', '192.168.1.25', '0', null, null);
INSERT INTO `device_data` VALUES ('52', null, '2019-06-07 10:21:13', '1', '1', '192.168.1.33', '0', null, null);
INSERT INTO `device_data` VALUES ('53', null, '2019-06-07 10:21:13', '1', '2', '192.168.1.34', '0', null, null);
INSERT INTO `device_data` VALUES ('54', null, '2019-06-07 10:21:15', '0', '1', '192.168.1.200', '0', null, null);
INSERT INTO `device_data` VALUES ('55', null, '2019-06-07 10:21:15', '1', '2', '192.168.1.25', '0', null, null);
INSERT INTO `device_data` VALUES ('56', null, '2019-06-08 22:30:30', '1', '1', '192.168.1.34', '0', null, null);
INSERT INTO `device_data` VALUES ('57', null, '2019-06-08 22:30:32', '0', '1', '192.168.1.200', '0', null, null);
INSERT INTO `device_data` VALUES ('58', null, '2019-06-08 22:30:32', '1', '2', '192.168.1.25', '0', null, null);
INSERT INTO `device_data` VALUES ('59', null, '2019-06-09 10:41:38', '1', '1', '192.168.1.34', '0', null, null);
INSERT INTO `device_data` VALUES ('60', null, '2019-06-09 10:41:40', '0', '1', '192.168.1.200', '0', null, null);
INSERT INTO `device_data` VALUES ('61', null, '2019-06-09 10:41:40', '1', '2', '192.168.1.25', '0', null, null);
INSERT INTO `device_data` VALUES ('62', null, '2019-06-10 14:55:03', '1', '1', '192.168.1.33', '0', null, null);
INSERT INTO `device_data` VALUES ('63', null, '2019-06-10 14:55:03', '1', '2', '192.168.1.34', '0', null, null);
INSERT INTO `device_data` VALUES ('64', null, '2019-06-10 14:55:26', '1', '1', '192.168.1.25', '0', null, null);
INSERT INTO `device_data` VALUES ('65', null, '2019-06-10 15:20:03', '1', '1', '192.168.1.32', '0', null, null);
INSERT INTO `device_data` VALUES ('66', null, '2019-06-10 15:20:03', '1', '2', '192.168.1.33', '0', null, null);
INSERT INTO `device_data` VALUES ('67', null, '2019-06-10 15:20:03', '1', '2', '192.168.1.33', '0', null, null);
INSERT INTO `device_data` VALUES ('68', null, '2019-06-10 15:20:27', '1', '1', '192.168.1.25', '0', null, null);
INSERT INTO `device_data` VALUES ('69', null, '2019-06-10 15:20:28', '1', '1', '192.168.1.26', '0', null, null);
INSERT INTO `device_data` VALUES ('70', null, '2019-06-11 09:26:34', '1', '1', '192.168.1.32', '0', null, null);
INSERT INTO `device_data` VALUES ('71', null, '2019-06-11 09:26:34', '1', '2', '192.168.1.33', '0', null, null);
INSERT INTO `device_data` VALUES ('72', null, '2019-06-11 09:26:34', '1', '2', '192.168.1.33', '0', null, null);
INSERT INTO `device_data` VALUES ('73', null, '2019-06-11 09:26:58', '1', '1', '192.168.1.25', '0', null, null);
INSERT INTO `device_data` VALUES ('74', null, '2019-06-11 09:26:59', '1', '1', '192.168.1.26', '0', null, null);
INSERT INTO `device_data` VALUES ('75', null, '2019-06-12 10:25:33', '1', '1', '192.168.1.33', '0', null, null);
INSERT INTO `device_data` VALUES ('76', null, '2019-06-12 10:25:33', '1', '2', '192.168.1.34', '0', null, null);
INSERT INTO `device_data` VALUES ('77', null, '2019-06-13 09:08:37', '1', '1', '192.168.1.34', '0', null, null);
INSERT INTO `device_data` VALUES ('78', null, '2019-06-13 14:44:55', '1', '1', '192.168.1.33', '0', null, null);
INSERT INTO `device_data` VALUES ('79', null, '2019-06-13 14:44:55', '1', '2', '192.168.1.34', '0', null, null);
INSERT INTO `device_data` VALUES ('80', null, '2019-06-13 14:45:19', '1', '1', '192.168.1.25', '0', null, null);
INSERT INTO `device_data` VALUES ('81', null, '2019-06-13 14:45:20', '1', '1', '192.168.1.26', '0', null, null);
INSERT INTO `device_data` VALUES ('82', null, '2019-06-13 14:45:20', '1', '2', '192.168.1.27', '0', null, null);

-- ----------------------------
-- Table structure for duge_station_info
-- ----------------------------
DROP TABLE IF EXISTS `duge_station_info`;
CREATE TABLE `duge_station_info` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(128) DEFAULT NULL COMMENT '站点名称',
  `abbreviation` varchar(128) DEFAULT NULL COMMENT '缩写',
  `latitude` varchar(30) DEFAULT NULL COMMENT '纬度',
  `longitude` varchar(30) DEFAULT NULL COMMENT '经度',
  `user_id` int(20) DEFAULT NULL COMMENT '负责人',
  `bureau` varchar(255) DEFAULT NULL COMMENT '公路局名称',
  `province` varchar(30) DEFAULT NULL COMMENT '省',
  `city` varchar(30) DEFAULT NULL COMMENT '市',
  `county` varchar(30) DEFAULT NULL COMMENT '区',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  `device_code` varchar(8) DEFAULT NULL COMMENT '设备编码',
  `longitude_WGS84` varchar(50) DEFAULT NULL,
  `latitude_WGS84` varchar(50) DEFAULT NULL,
  `longitude_GCJ02` varchar(50) DEFAULT NULL,
  `latitude_GCJ02` varchar(50) DEFAULT NULL,
  `longitude_BD09` varchar(50) DEFAULT NULL,
  `latitude_BD09` varchar(50) DEFAULT NULL,
  `station_code` varchar(30) DEFAULT NULL COMMENT '站点编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='站点信息';

-- ----------------------------
-- Records of duge_station_info
-- ----------------------------
INSERT  INTO `duge_station_info` VALUES (1,'五沙大桥-东侧（往大良方向）','WSD','113.355856','22.821707',1,'广东公路局','广东省','佛山市','顺德区','X501  K5＋900顺德区五沙大桥西侧上桥位（五沙方向）','',NULL,'',NULL,'','000003e9','113.3282575600','22.8109135700','113.3336510000','22.8081800000','113.3402110884','22.8139434571','OL0202');
INSERT  INTO `duge_station_info` VALUES (2,'五沙大桥-西侧（往五沙方向）','WSX','113.355856','22.821707',1,'广东公路局','广东省','佛山市','顺德区','X501 K8＋100左顺德区五沙大桥东侧上桥位（往大良方向）','',NULL,'',NULL,'',NULL,'113.3418746300','22.8185526000','113.3473090000','22.8158600000','113.3538941319','22.8215446312','OL0201');
INSERT  INTO `duge_station_info` VALUES (3,'三善大桥-下桥位（往顺德方向）','SSX','113.355856','22.821707',1,'广东公路局','广东省','佛山市','顺德区','G325  K14＋500顺德区三善大桥下桥位附近（往顺德方向）','',NULL,'',NULL,'',NULL,'113.2808478400','22.8855975700','113.2861830000','22.8828340000','113.2925990518','22.8891588412','OL0402');
INSERT  INTO `duge_station_info` VALUES (4,'三善大桥-上桥位（往广州方向）','SSS','113.355856','22.821707',1,'广东公路局','广东省','佛山市','顺德区','G325  K14＋500顺德区三善大桥上桥位附近（往广州方向）','',NULL,'',NULL,'',NULL,'113.2809018400','22.8855555700','113.2862370000','22.8827920000','113.2926530518','22.8891168412','OL0401');
INSERT  INTO `duge_station_info` VALUES (5,'德胜大桥-上桥位（往容桂方向）','DSS','113.316719','22.80082',1,'广东公路局','广东省','佛山市','顺德区','X520  K6顺德区德胜大桥上桥位旧收费站附近（往容桂方向）','',NULL,'',NULL,'','000003e9','113.2997091300','22.8049471900','113.3050660000','22.8021770000','113.3115476422','22.8082631319','OL0102');
INSERT  INTO `duge_station_info` VALUES (6,'德胜大桥-下桥位（往大良方向）','DSX','113.316719','22.80082',1,'广东公路局','广东省','佛山市','顺德区','X520  K6顺德区德胜大桥上桥位旧收费站附近（往容桂方向）','',NULL,'',NULL,'','000003e9','113.2995294000','22.8048919500','113.3048740000','22.8021110000','113.3113203215','22.8083471618','OL0101');
INSERT  INTO `duge_station_info` VALUES (7,'乐龙大桥-南侧（往乐从方向）','LLN','113.355856','22.821707',1,'广东公路局','广东省','佛山市','顺德区','乐龙路乐龙大桥南侧上桥位附近（往乐从方向）','',NULL,'',NULL,'',NULL,'113.0942771500','22.8864718300','113.0997530000','22.8838660000','113.1063098779','22.8895422900','OL0502');
INSERT  INTO `duge_station_info` VALUES (8,'乐龙大桥-北侧（往龙江方向）','LLB','113.355856','22.821707',1,'广东公路局','广东省','佛山市','顺德区','乐龙路乐龙大桥北侧上桥位附近（往龙江方向）','',NULL,'',NULL,'',NULL,'113.0977189500','22.8912152500','113.1031970000','22.8886150000','113.1097485908','22.8942889788','OL0501');
INSERT  INTO `duge_station_info` VALUES (9,'三洪奇大桥-上桥位（往北滘方向）','SHQX','113.355856','22.821707',1,'广东公路局','广东省','佛山市','顺德区','G105  K2954＋500顺德区三洪奇大桥南下桥位附近（往伦教方向）','',NULL,'',NULL,'',NULL,'113.2071029900','22.8971377700','113.2124540000','22.8944000000','113.2189867613','22.9001573624','OL0301');
INSERT  INTO `duge_station_info` VALUES (10,'三洪奇大桥-下桥位（往伦教方向）','SHQS','113.355856','22.821707',1,'广东公路局','广东省','佛山市','顺德区','G105  K2954＋500顺德区三洪奇大桥南上桥位附近（往北滘方向）','',NULL,'',NULL,'',NULL,'113.2073119900','22.8971037700','113.2126630000','22.8943660000','113.2191957613','22.9001233624','OL0302');

-- ----------------------------
-- Table structure for exception_data
-- ----------------------------
DROP TABLE IF EXISTS `exception_data`;
CREATE TABLE `exception_data` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `limit_over_level` int(8) DEFAULT NULL COMMENT '超限等级',
  `limit_mode` int(1) DEFAULT NULL COMMENT '1=轴数限重；2=车型限重',
  `vehicle_type` varchar(24) DEFAULT NULL COMMENT '车型',
  `weighting_id` int(8) DEFAULT NULL COMMENT '称重序号',
  `weighting_date` datetime DEFAULT NULL COMMENT '称重时间',
  `lane` int(2) DEFAULT NULL COMMENT '车道号',
  `direction` varchar(24) DEFAULT NULL COMMENT '行驶方向',
  `truck_number` varchar(32) DEFAULT NULL COMMENT '车牌号',
  `truck_corlor` varchar(12) DEFAULT NULL COMMENT '车牌颜色',
  `speed` decimal(5,1) DEFAULT NULL COMMENT '车速',
  `axle_count` int(2) DEFAULT NULL COMMENT '轴数',
  `axle_type` varchar(24) DEFAULT NULL COMMENT '轴型序列',
  `weight` decimal(12,2) DEFAULT NULL COMMENT '总重',
  `limit_weight` decimal(12,2) DEFAULT NULL COMMENT '限重',
  `over_weight` decimal(12,2) DEFAULT NULL COMMENT '超重',
  `axle_weight1` decimal(12,2) DEFAULT NULL,
  `axle_weight2` decimal(12,2) DEFAULT NULL,
  `axle_weight3` decimal(12,2) DEFAULT NULL,
  `axle_weight4` decimal(12,2) DEFAULT NULL,
  `axle_weight5` decimal(12,2) DEFAULT NULL,
  `axle_weight6` decimal(12,2) DEFAULT NULL,
  `axle_weight7` decimal(12,2) DEFAULT NULL,
  `axle_weight8` decimal(12,2) DEFAULT NULL,
  `sequence_tag` char(24) DEFAULT NULL COMMENT '唯一序列号',
  `ftp_head` varchar(128) DEFAULT NULL COMMENT '车头图片地址（秤上）',
  `ftp_axle` varchar(128) DEFAULT NULL COMMENT '车轴图片地址（秤上）',
  `ftp_tail` varchar(128) DEFAULT NULL COMMENT '车尾图片地址（秤上）',
  `ftp_prior_head` varchar(128) DEFAULT NULL COMMENT '秤前车头图片地址',
  `ftp_plate` varchar(128) DEFAULT NULL COMMENT '车牌特征图',
  `ftp_full_view` varchar(128) DEFAULT NULL COMMENT '车头视频文件地址',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  `device_code` varchar(8) DEFAULT NULL COMMENT '设备编码',
  `station_id` bigint(20) DEFAULT NULL COMMENT '站点ID',
  `lwh_date` timestamp NULL DEFAULT NULL COMMENT '超限采集时间',
  `plate` varchar(16) DEFAULT NULL COMMENT '超限车牌号',
  `width` varchar(16) DEFAULT NULL COMMENT '车宽',
  `height` varchar(16) DEFAULT NULL COMMENT '车高',
  `length` varchar(16) DEFAULT NULL COMMENT '车长',
  `lane_mid` varchar(16) DEFAULT NULL COMMENT '中间车道号',
  `lane_min` varchar(16) DEFAULT NULL COMMENT '小车道号',
  `lane_max` varchar(16) DEFAULT NULL COMMENT '大车道号',
  `pass_time` varchar(8) DEFAULT NULL COMMENT '通过时间',
  `remark_info` varchar(512) DEFAULT NULL COMMENT '异常信息备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100392 DEFAULT CHARSET=utf8 COMMENT='重量数据';



-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(100) DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='参数配置表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2018-03-16 11:33:00', 'admin', '2018-11-16 09:31:29', '默认 skin-default、蓝色 skin-blue、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES ('2', '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '初始化密码 123456');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` int(11) DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) DEFAULT '' COMMENT '负责人',
  `phone` varchar(11) DEFAULT '' COMMENT '联系电话',
  `email` varchar(50) DEFAULT '' COMMENT '邮箱',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('100', '0', '0', '杜格科技', '0', '杜格科技管理员', '15888888888', 'imi-bes@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-11-16 09:30:20');
INSERT INTO `sys_dept` VALUES ('101', '100', '0,100', '深圳总公司', '1', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('102', '100', '0,100', '长沙分公司', '2', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('103', '101', '0,100,101', '执法大队', '1', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('104', '101', '0,100,101', '市场部门', '2', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('105', '101', '0,100,101', '测试部门', '3', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('106', '101', '0,100,101', '财务部门', '4', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('107', '101', '0,100,101', '运维部门', '5', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('108', '102', '0,100,102', '市场部门', '1', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('109', '102', '0,100,102', '财务部门', '2', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
  `dict_code` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT '' COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT '' COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='字典数据表';

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES ('1', '1', '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别男');
INSERT INTO `sys_dict_data` VALUES ('2', '2', '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别女');
INSERT INTO `sys_dict_data` VALUES ('3', '3', '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别未知');
INSERT INTO `sys_dict_data` VALUES ('4', '1', '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '显示菜单');
INSERT INTO `sys_dict_data` VALUES ('5', '2', '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES ('6', '1', '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('7', '2', '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES ('8', '1', '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('9', '2', '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES ('10', '1', '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认是');
INSERT INTO `sys_dict_data` VALUES ('11', '2', '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认否');
INSERT INTO `sys_dict_data` VALUES ('12', '1', '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知');
INSERT INTO `sys_dict_data` VALUES ('13', '2', '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '公告');
INSERT INTO `sys_dict_data` VALUES ('14', '1', '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('15', '2', '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '关闭状态');
INSERT INTO `sys_dict_data` VALUES ('16', '1', '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '新增操作');
INSERT INTO `sys_dict_data` VALUES ('17', '2', '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '修改操作');
INSERT INTO `sys_dict_data` VALUES ('18', '3', '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '删除操作');
INSERT INTO `sys_dict_data` VALUES ('19', '4', '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '授权操作');
INSERT INTO `sys_dict_data` VALUES ('20', '5', '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导出操作');
INSERT INTO `sys_dict_data` VALUES ('21', '6', '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导入操作');
INSERT INTO `sys_dict_data` VALUES ('22', '7', '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '强退操作');
INSERT INTO `sys_dict_data` VALUES ('23', '8', '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '生成操作');
INSERT INTO `sys_dict_data` VALUES ('24', '8', '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '清空操作');
INSERT INTO `sys_dict_data` VALUES ('25', '1', '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('26', '2', '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `dict_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='字典类型表';

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES ('1', '用户性别', 'sys_user_sex', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户性别列表');
INSERT INTO `sys_dict_type` VALUES ('2', '菜单状态', 'sys_show_hide', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES ('3', '系统开关', 'sys_normal_disable', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统开关列表');
INSERT INTO `sys_dict_type` VALUES ('4', '任务状态', 'sys_job_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '任务状态列表');
INSERT INTO `sys_dict_type` VALUES ('5', '系统是否', 'sys_yes_no', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统是否列表');
INSERT INTO `sys_dict_type` VALUES ('6', '通知类型', 'sys_notice_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知类型列表');
INSERT INTO `sys_dict_type` VALUES ('7', '通知状态', 'sys_notice_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知状态列表');
INSERT INTO `sys_dict_type` VALUES ('8', '操作类型', 'sys_oper_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作类型列表');
INSERT INTO `sys_dict_type` VALUES ('9', '系统状态', 'sys_common_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录状态列表');

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job` (
  `job_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL DEFAULT '' COMMENT '任务组名',
  `method_name` varchar(500) DEFAULT '' COMMENT '任务方法',
  `method_params` varchar(200) DEFAULT '' COMMENT '方法参数',
  `cron_expression` varchar(255) DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) DEFAULT '1' COMMENT '计划执行错误策略（1继续 2等待 3放弃）',
  `concurrent` char(1) DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`,`job_name`,`job_group`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8 COMMENT='定时任务调度表';

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES ('1', 'ryTask', '系统默认（无参）', 'ryNoParams', '', '0/10 * * * * ?', '1', '1', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_job` VALUES ('2', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', '0/20 * * * * ?', '1', '1', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_job` VALUES ('100', 'scanTask', 'scan', 'scanDeviceData', '', '0/10 * * * * ?', '0', '1', '1', 'admin', '2018-12-08 11:07:05', 'admin', '2018-12-08 11:13:02', '');
INSERT INTO `sys_job` VALUES ('101', 'siteScanTask', 'siteScan', 'scanVehicleData', '', '0/10 * * * * ?', '0', '1', '1', 'admin', '2019-01-12 14:10:39', 'admin', '2019-01-13 02:06:02', '');
INSERT INTO `sys_job` VALUES ('102', 'ftpTask', 'ftp', 'scanFtp', '', '0 * 1 * * ?', '2', '1', '1', 'admin', '2019-01-13 02:07:16', 'admin', '2019-01-13 02:21:49', '');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log` (
  `job_log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL COMMENT '任务组名',
  `method_name` varchar(500) DEFAULT NULL COMMENT '任务方法',
  `method_params` varchar(200) DEFAULT '' COMMENT '方法参数',
  `job_message` varchar(500) DEFAULT NULL COMMENT '日志信息',
  `status` char(1) DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` text COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17444 DEFAULT CHARSET=utf8 COMMENT='定时任务调度日志表';


-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor` (
  `info_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `login_name` varchar(50) DEFAULT '' COMMENT '登录账号',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=692 DEFAULT CHARSET=utf8 COMMENT='系统访问记录';



-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` int(11) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `url` varchar(200) DEFAULT '#' COMMENT '请求地址',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) DEFAULT '' COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2164 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '0', '5', '#', 'M', '0', '', 'fa fa-gear', 'admin', '2018-03-16 11:33:00', 'admin', '2018-11-21 08:51:30', '系统管理目录');
INSERT INTO `sys_menu` VALUES ('2', '系统监控', '0', '7', '#', 'M', '0', '', 'fa fa-video-camera', 'admin', '2018-03-16 11:33:00', 'admin', '2018-11-26 21:32:12', '系统监控目录');
INSERT INTO `sys_menu` VALUES ('3', '系统工具', '0', '8', '#', 'M', '0', '', 'fa fa-bars', 'admin', '2018-03-16 11:33:00', 'admin', '2018-11-26 21:32:21', '系统工具目录');
INSERT INTO `sys_menu` VALUES ('100', '用户管理', '1', '1', '/system/user', 'C', '0', 'system:user:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户管理菜单');
INSERT INTO `sys_menu` VALUES ('101', '角色管理', '1', '2', '/system/role', 'C', '0', 'system:role:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '角色管理菜单');
INSERT INTO `sys_menu` VALUES ('102', '菜单管理', '1', '3', '/system/menu', 'C', '0', 'system:menu:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES ('103', '部门管理', '1', '4', '/system/dept', 'C', '0', 'system:dept:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '部门管理菜单');
INSERT INTO `sys_menu` VALUES ('104', '岗位管理', '1', '5', '/system/post', 'C', '0', 'system:post:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '岗位管理菜单');
INSERT INTO `sys_menu` VALUES ('105', '字典管理', '1', '6', '/system/dict', 'C', '0', 'system:dict:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '字典管理菜单');
INSERT INTO `sys_menu` VALUES ('106', '参数设置', '1', '7', '/system/config', 'C', '0', 'system:config:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '参数设置菜单');
INSERT INTO `sys_menu` VALUES ('107', '通知公告', '1', '8', '/system/notice', 'C', '0', 'system:notice:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知公告菜单');
INSERT INTO `sys_menu` VALUES ('108', '日志管理', '1', '9', '#', 'M', '0', '', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '日志管理菜单');
INSERT INTO `sys_menu` VALUES ('109', '在线用户', '2', '1', '/monitor/online', 'C', '0', 'monitor:online:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '在线用户菜单');
INSERT INTO `sys_menu` VALUES ('110', '定时任务', '2', '2', '/monitor/job', 'C', '0', 'monitor:job:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '定时任务菜单');
INSERT INTO `sys_menu` VALUES ('111', '数据监控', '2', '3', '/monitor/data', 'C', '0', 'monitor:data:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '数据监控菜单');
INSERT INTO `sys_menu` VALUES ('112', '表单构建', '3', '1', '/tool/build', 'C', '0', 'tool:build:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '表单构建菜单');
INSERT INTO `sys_menu` VALUES ('113', '代码生成', '3', '2', '/tool/gen', 'C', '0', 'tool:gen:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '代码生成菜单');
INSERT INTO `sys_menu` VALUES ('114', '系统接口', '3', '3', '/tool/swagger', 'C', '0', 'tool:swagger:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统接口菜单');
INSERT INTO `sys_menu` VALUES ('500', '操作日志', '108', '1', '/monitor/operlog', 'C', '0', 'monitor:operlog:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作日志菜单');
INSERT INTO `sys_menu` VALUES ('501', '登录日志', '108', '2', '/monitor/logininfor', 'C', '0', 'monitor:logininfor:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录日志菜单');
INSERT INTO `sys_menu` VALUES ('1000', '用户查询', '100', '1', '#', 'F', '0', 'system:user:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1001', '用户新增', '100', '2', '#', 'F', '0', 'system:user:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1002', '用户修改', '100', '3', '#', 'F', '0', 'system:user:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1003', '用户删除', '100', '4', '#', 'F', '0', 'system:user:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1004', '用户导出', '100', '5', '#', 'F', '0', 'system:user:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1005', '重置密码', '100', '5', '#', 'F', '0', 'system:user:resetPwd', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1006', '角色查询', '101', '1', '#', 'F', '0', 'system:role:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1007', '角色新增', '101', '2', '#', 'F', '0', 'system:role:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1008', '角色修改', '101', '3', '#', 'F', '0', 'system:role:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1009', '角色删除', '101', '4', '#', 'F', '0', 'system:role:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1010', '角色导出', '101', '4', '#', 'F', '0', 'system:role:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1011', '菜单查询', '102', '1', '#', 'F', '0', 'system:menu:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1012', '菜单新增', '102', '2', '#', 'F', '0', 'system:menu:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1013', '菜单修改', '102', '3', '#', 'F', '0', 'system:menu:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1014', '菜单删除', '102', '4', '#', 'F', '0', 'system:menu:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1015', '部门查询', '103', '1', '#', 'F', '0', 'system:dept:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1016', '部门新增', '103', '2', '#', 'F', '0', 'system:dept:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1017', '部门修改', '103', '3', '#', 'F', '0', 'system:dept:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1018', '部门删除', '103', '4', '#', 'F', '0', 'system:dept:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1019', '岗位查询', '104', '1', '#', 'F', '0', 'system:post:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1020', '岗位新增', '104', '2', '#', 'F', '0', 'system:post:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1021', '岗位修改', '104', '3', '#', 'F', '0', 'system:post:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1022', '岗位删除', '104', '4', '#', 'F', '0', 'system:post:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1023', '岗位导出', '104', '4', '#', 'F', '0', 'system:post:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1024', '字典查询', '105', '1', '#', 'F', '0', 'system:dict:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1025', '字典新增', '105', '2', '#', 'F', '0', 'system:dict:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1026', '字典修改', '105', '3', '#', 'F', '0', 'system:dict:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1027', '字典删除', '105', '4', '#', 'F', '0', 'system:dict:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1028', '字典导出', '105', '4', '#', 'F', '0', 'system:dict:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1029', '参数查询', '106', '1', '#', 'F', '0', 'system:config:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1030', '参数新增', '106', '2', '#', 'F', '0', 'system:config:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1031', '参数修改', '106', '3', '#', 'F', '0', 'system:config:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1032', '参数删除', '106', '4', '#', 'F', '0', 'system:config:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1033', '参数导出', '106', '4', '#', 'F', '0', 'system:config:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1034', '公告查询', '107', '1', '#', 'F', '0', 'system:notice:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1035', '公告新增', '107', '2', '#', 'F', '0', 'system:notice:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1036', '公告修改', '107', '3', '#', 'F', '0', 'system:notice:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1037', '公告删除', '107', '4', '#', 'F', '0', 'system:notice:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1038', '操作查询', '500', '1', '#', 'F', '0', 'monitor:operlog:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1039', '操作删除', '500', '2', '#', 'F', '0', 'monitor:operlog:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1040', '详细信息', '500', '3', '#', 'F', '0', 'monitor:operlog:detail', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1041', '日志导出', '500', '3', '#', 'F', '0', 'monitor:operlog:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1042', '登录查询', '501', '1', '#', 'F', '0', 'monitor:logininfor:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1043', '登录删除', '501', '2', '#', 'F', '0', 'monitor:logininfor:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1044', '日志导出', '501', '2', '#', 'F', '0', 'monitor:logininfor:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1045', '在线查询', '109', '1', '#', 'F', '0', 'monitor:online:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1046', '批量强退', '109', '2', '#', 'F', '0', 'monitor:online:batchForceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1047', '单条强退', '109', '3', '#', 'F', '0', 'monitor:online:forceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1048', '任务查询', '110', '1', '#', 'F', '0', 'monitor:job:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1049', '任务新增', '110', '2', '#', 'F', '0', 'monitor:job:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1050', '任务修改', '110', '3', '#', 'F', '0', 'monitor:job:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1051', '任务删除', '110', '4', '#', 'F', '0', 'monitor:job:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1052', '状态修改', '110', '5', '#', 'F', '0', 'monitor:job:changeStatus', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1053', '任务导出', '110', '5', '#', 'F', '0', 'monitor:job:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1054', '生成查询', '113', '1', '#', 'F', '0', 'tool:gen:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1055', '生成代码', '113', '2', '#', 'F', '0', 'tool:gen:code', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('2000', '站点管理', '0', '3', '#', 'M', '0', '', 'fa fa-exchange', 'admin', '2018-11-14 14:32:15', 'admin', '2019-04-13 17:06:29', '');
INSERT INTO `sys_menu` VALUES ('2001', '站点管理', '2000', '2', '/module/stationInfo', 'C', '0', 'stationInfo:manager', '#', 'admin', '2018-11-14 14:34:20', 'admin', '2018-11-26 21:43:35', '');
INSERT INTO `sys_menu` VALUES ('2002', '车辆信息管理', '0', '12', '#', 'M', '1', '', 'fa fa-automobile', 'admin', '2018-11-14 17:20:52', 'admin', '2019-04-13 17:06:53', '');
INSERT INTO `sys_menu` VALUES ('2003', '车辆管理', '2002', '1', '/report/reportCar', 'C', '0', '', '#', 'admin', '2018-11-14 17:21:10', 'admin', '2018-12-01 18:18:49', '');
INSERT INTO `sys_menu` VALUES ('2004', '报表中心', '0', '2', '#', 'M', '0', '', 'fa fa-dashboard', 'admin', '2018-11-14 17:22:18', 'admin', '2019-04-13 17:06:37', '');
INSERT INTO `sys_menu` VALUES ('2006', '站点比较', '2004', '2', '/report', 'C', '0', '', 'fa fa-map-signs', 'admin', '2018-11-19 22:34:40', 'admin', '2019-04-13 17:42:15', '');
INSERT INTO `sys_menu` VALUES ('2008', '通行记录管理', '0', '1', '#', 'M', '0', '', 'fa fa-road', 'admin', '2018-11-21 08:46:24', 'admin', '2018-11-21 08:50:19', '');
INSERT INTO `sys_menu` VALUES ('2009', '实时通行记录', '2008', '1', '/duge/weightData', 'C', '0', '', '#', 'admin', '2018-11-21 08:47:15', 'admin', '2018-11-21 08:49:25', '');
INSERT INTO `sys_menu` VALUES ('2010', '站点信息', '2000', '1', '/module/stationInfo/stationDashboard', 'C', '0', 'stationInfo:view', '#', 'admin', '2018-11-26 21:13:34', 'admin', '2018-11-26 21:45:13', '');
INSERT INTO `sys_menu` VALUES ('2011', '我的工作', '0', '11', '#', 'M', '1', '', 'fa fa-gavel', 'admin', '2018-11-26 21:31:33', 'admin', '2019-04-08 15:38:53', '');
INSERT INTO `sys_menu` VALUES ('2012', '罚单处理', '2011', '1', '#', 'C', '0', 'system:user:list', '#', 'admin', '2018-11-26 21:35:59', 'admin', '2018-11-26 21:37:17', '');
INSERT INTO `sys_menu` VALUES ('2013', '违章查询', '2011', '2', '#', 'C', '0', 'system:user:list', '#', 'admin', '2018-11-26 21:36:24', 'admin', '2018-11-26 21:37:48', '');
INSERT INTO `sys_menu` VALUES ('2014', '设备', '3', '1', '/module/deviceData', 'C', '0', 'module:deviceData:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '设备菜单');
INSERT INTO `sys_menu` VALUES ('2015', '设备查询', '2014', '1', '#', 'F', '0', 'module:deviceData:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2016', '设备新增', '2014', '2', '#', 'F', '0', 'module:deviceData:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2017', '设备修改', '2014', '3', '#', 'F', '0', 'module:deviceData:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2018', '设备删除', '2014', '4', '#', 'F', '0', 'module:deviceData:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2084', '遥感监测平台', '0', '4', '#', 'M', '1', '', 'fa fa-dashboard', 'admin', '2019-04-08 15:37:48', 'admin', '2019-04-11 18:10:12', '');
INSERT INTO `sys_menu` VALUES ('2085', '点位环境空气质量记录', '2084', '1', '/yaogan/airQualityRecord', 'C', '0', 'yaogan:airQualityRecord:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '点位环境空气质量记录菜单');
INSERT INTO `sys_menu` VALUES ('2086', '点位环境空气质量记录查询', '2085', '1', '#', 'F', '0', 'yaogan:airQualityRecord:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2087', '点位环境空气质量记录新增', '2085', '2', '#', 'F', '0', 'yaogan:airQualityRecord:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2088', '点位环境空气质量记录修改', '2085', '3', '#', 'F', '0', 'yaogan:airQualityRecord:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2089', '点位环境空气质量记录删除', '2085', '4', '#', 'F', '0', 'yaogan:airQualityRecord:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2090', '遥测设备检查', '2084', '1', '/yaogan/deviceCheckLog', 'C', '0', 'yaogan:deviceCheckLog:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '遥测设备检查菜单');
INSERT INTO `sys_menu` VALUES ('2091', '遥测设备检查查询', '2090', '1', '#', 'F', '0', 'yaogan:deviceCheckLog:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2092', '遥测设备检查新增', '2090', '2', '#', 'F', '0', 'yaogan:deviceCheckLog:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2093', '遥测设备检查修改', '2090', '3', '#', 'F', '0', 'yaogan:deviceCheckLog:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2094', '遥测设备检查删除', '2090', '4', '#', 'F', '0', 'yaogan:deviceCheckLog:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2095', '遥测设备动态检查过程数据', '2084', '1', '/yaogan/deviceDynamicCheck', 'C', '0', 'yaogan:deviceDynamicCheck:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '遥测设备动态检查过程数据菜单');
INSERT INTO `sys_menu` VALUES ('2096', '遥测设备动态检查过程数据查询', '2095', '1', '#', 'F', '0', 'yaogan:deviceDynamicCheck:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2097', '遥测设备动态检查过程数据新增', '2095', '2', '#', 'F', '0', 'yaogan:deviceDynamicCheck:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2098', '遥测设备动态检查过程数据修改', '2095', '3', '#', 'F', '0', 'yaogan:deviceDynamicCheck:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2099', '遥测设备动态检查过程数据删除', '2095', '4', '#', 'F', '0', 'yaogan:deviceDynamicCheck:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2100', '遥测设备自检过程数据', '2084', '1', '/yaogan/deviceSelfCheck', 'C', '0', 'yaogan:deviceSelfCheck:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '遥测设备自检过程数据菜单');
INSERT INTO `sys_menu` VALUES ('2101', '遥测设备自检过程数据查询', '2100', '1', '#', 'F', '0', 'yaogan:deviceSelfCheck:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2102', '遥测设备自检过程数据新增', '2100', '2', '#', 'F', '0', 'yaogan:deviceSelfCheck:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2103', '遥测设备自检过程数据修改', '2100', '3', '#', 'F', '0', 'yaogan:deviceSelfCheck:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2104', '遥测设备自检过程数据删除', '2100', '4', '#', 'F', '0', 'yaogan:deviceSelfCheck:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2105', '遥测设备静态检查过程数据', '2084', '1', '/yaogan/deviceStaticCheck', 'C', '0', 'yaogan:deviceStaticCheck:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '遥测设备静态检查过程数据菜单');
INSERT INTO `sys_menu` VALUES ('2106', '遥测设备静态检查过程数据查询', '2105', '1', '#', 'F', '0', 'yaogan:deviceStaticCheck:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2107', '遥测设备静态检查过程数据新增', '2105', '2', '#', 'F', '0', 'yaogan:deviceStaticCheck:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2108', '遥测设备静态检查过程数据修改', '2105', '3', '#', 'F', '0', 'yaogan:deviceStaticCheck:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2109', '遥测设备静态检查过程数据删除', '2105', '4', '#', 'F', '0', 'yaogan:deviceStaticCheck:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2110', '点位遥测线', '2084', '1', '/yaogan/line', 'C', '0', 'yaogan:line:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '点位遥测线菜单');
INSERT INTO `sys_menu` VALUES ('2111', '点位遥测线查询', '2110', '1', '#', 'F', '0', 'yaogan:line:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2112', '点位遥测线新增', '2110', '2', '#', 'F', '0', 'yaogan:line:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2113', '点位遥测线修改', '2110', '3', '#', 'F', '0', 'yaogan:line:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2114', '点位遥测线删除', '2110', '4', '#', 'F', '0', 'yaogan:line:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2115', '移动式点位运行记录', '2084', '1', '/yaogan/mobileStation', 'C', '0', 'yaogan:mobileStation:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '移动式点位运行记录菜单');
INSERT INTO `sys_menu` VALUES ('2116', '移动式点位运行记录查询', '2115', '1', '#', 'F', '0', 'yaogan:mobileStation:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2117', '移动式点位运行记录新增', '2115', '2', '#', 'F', '0', 'yaogan:mobileStation:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2118', '移动式点位运行记录修改', '2115', '3', '#', 'F', '0', 'yaogan:mobileStation:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2119', '移动式点位运行记录删除', '2115', '4', '#', 'F', '0', 'yaogan:mobileStation:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2120', '遥感监测数据', '2084', '1', '/yaogan/monitorDataLog', 'C', '0', 'yaogan:monitorDataLog:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '遥感监测数据菜单');
INSERT INTO `sys_menu` VALUES ('2121', '遥感监测数据查询', '2120', '1', '#', 'F', '0', 'yaogan:monitorDataLog:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2122', '遥感监测数据新增', '2120', '2', '#', 'F', '0', 'yaogan:monitorDataLog:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2123', '遥感监测数据修改', '2120', '3', '#', 'F', '0', 'yaogan:monitorDataLog:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2124', '遥感监测数据删除', '2120', '4', '#', 'F', '0', 'yaogan:monitorDataLog:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2125', '遥测设备自检', '2084', '1', '/yaogan/stationCheckLog', 'C', '0', 'yaogan:stationCheckLog:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '遥测设备自检菜单');
INSERT INTO `sys_menu` VALUES ('2126', '遥测设备自检查询', '2125', '1', '#', 'F', '0', 'yaogan:stationCheckLog:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2127', '遥测设备自检新增', '2125', '2', '#', 'F', '0', 'yaogan:stationCheckLog:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2128', '遥测设备自检修改', '2125', '3', '#', 'F', '0', 'yaogan:stationCheckLog:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2129', '遥测设备自检删除', '2125', '4', '#', 'F', '0', 'yaogan:stationCheckLog:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2130', '点位', '2084', '1', '/yaogan/station', 'C', '0', 'yaogan:station:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '点位菜单');
INSERT INTO `sys_menu` VALUES ('2131', '点位查询', '2130', '1', '#', 'F', '0', 'yaogan:station:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2132', '点位新增', '2130', '2', '#', 'F', '0', 'yaogan:station:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2133', '点位修改', '2130', '3', '#', 'F', '0', 'yaogan:station:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2134', '点位删除', '2130', '4', '#', 'F', '0', 'yaogan:station:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2135', '交通流量', '2084', '1', '/yaogan/trafficFlow', 'C', '0', 'yaogan:trafficFlow:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '交通流量菜单');
INSERT INTO `sys_menu` VALUES ('2136', '交通流量查询', '2135', '1', '#', 'F', '0', 'yaogan:trafficFlow:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2137', '交通流量新增', '2135', '2', '#', 'F', '0', 'yaogan:trafficFlow:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2138', '交通流量修改', '2135', '3', '#', 'F', '0', 'yaogan:trafficFlow:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2139', '交通流量删除', '2135', '4', '#', 'F', '0', 'yaogan:trafficFlow:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2140', '车辆数据', '2084', '1', '/yaogan/vehicleInfo', 'C', '0', 'yaogan:vehicleInfo:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '车辆数据菜单');
INSERT INTO `sys_menu` VALUES ('2141', '车辆数据查询', '2140', '1', '#', 'F', '0', 'yaogan:vehicleInfo:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2142', '车辆数据新增', '2140', '2', '#', 'F', '0', 'yaogan:vehicleInfo:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2143', '车辆数据修改', '2140', '3', '#', 'F', '0', 'yaogan:vehicleInfo:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2144', '车辆数据删除', '2140', '4', '#', 'F', '0', 'yaogan:vehicleInfo:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2145', '机动车轨迹', '2084', '1', '/yaogan/vehicleTrajectory', 'C', '0', 'yaogan:vehicleTrajectory:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '机动车轨迹菜单');
INSERT INTO `sys_menu` VALUES ('2146', '机动车轨迹查询', '2145', '1', '#', 'F', '0', 'yaogan:vehicleTrajectory:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2147', '机动车轨迹新增', '2145', '2', '#', 'F', '0', 'yaogan:vehicleTrajectory:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2148', '机动车轨迹修改', '2145', '3', '#', 'F', '0', 'yaogan:vehicleTrajectory:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2149', '机动车轨迹删除', '2145', '4', '#', 'F', '0', 'yaogan:vehicleTrajectory:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2150', '违章记录', '2008', '2', '/duge/weightData/over', 'C', '0', '', '#', 'admin', '2019-04-12 14:27:25', 'admin', '2019-04-12 14:29:22', '');
INSERT INTO `sys_menu` VALUES ('2151', '站点汇总', '2004', '1', '/report/total', 'C', '0', '', '#', 'admin', '2019-04-13 17:43:00', '', null, '');
INSERT INTO `sys_menu` VALUES ('2152', '站点明细', '2004', '3', '/report/single', 'C', '0', '', '#', 'admin', '2019-04-13 17:43:43', '', null, '');
INSERT INTO `sys_menu` VALUES ('2153', '站点审查记录', '2000', '3', '/station/auditRecord', 'C', '0', 'station:auditRecord:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '站点审查记录菜单');
INSERT INTO `sys_menu` VALUES ('2154', '站点审查记录查询', '2153', '1', '#', 'F', '0', 'station:auditRecord:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2155', '站点审查记录新增', '2153', '2', '#', 'F', '0', 'station:auditRecord:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2156', '站点审查记录修改', '2153', '3', '#', 'F', '0', 'station:auditRecord:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2157', '站点审查记录删除', '2153', '4', '#', 'F', '0', 'station:auditRecord:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2158', '站点维护记录', '2000', '4', '/station/maintenanceRecord', 'C', '0', 'station:maintenanceRecord:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '站点维护记录菜单');
INSERT INTO `sys_menu` VALUES ('2159', '站点维护记录查询', '2158', '1', '#', 'F', '0', 'station:maintenanceRecord:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2160', '站点维护记录新增', '2158', '2', '#', 'F', '0', 'station:maintenanceRecord:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2161', '站点维护记录修改', '2158', '3', '#', 'F', '0', 'station:maintenanceRecord:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2162', '站点维护记录删除', '2158', '4', '#', 'F', '0', 'station:maintenanceRecord:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2163', '实时监控', '2008', '3', '/duge/weightData/realtime', 'C', '0', '', '#', 'admin', '2019-06-13 15:56:22', 'admin', '2019-06-13 17:22:49', '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
  `notice_type` char(2) NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` varchar(500) NOT NULL COMMENT '公告内容',
  `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='通知公告表';

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES ('1', '温馨提醒：2018-07-01 杜格新版本发布啦', '2', '新版本内容', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-11-16 09:32:00', '管理员');
INSERT INTO `sys_notice` VALUES ('2', '维护通知：2018-07-01 杜格系统凌晨维护', '1', '维护内容', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-11-16 09:32:08', '管理员');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log` (
  `oper_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(30) DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(255) DEFAULT '' COMMENT '请求参数',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=321 DEFAULT CHARSET=utf8 COMMENT='操作日志记录';


-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='岗位信息表';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES ('1', 'ceo', '董事长', '1', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES ('2', 'se', '项目经理', '2', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES ('3', 'hr', '人力资源', '3', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES ('4', 'user', '普通员工', '4', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限）',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', 'admin', '1', '1', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '管理员');
INSERT INTO `sys_role` VALUES ('2', '普通角色', 'common', '2', '2', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '普通角色');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `dept_id` int(11) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和部门关联表';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES ('2', '100');
INSERT INTO `sys_role_dept` VALUES ('2', '101');
INSERT INTO `sys_role_dept` VALUES ('2', '105');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('2', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '2');
INSERT INTO `sys_role_menu` VALUES ('2', '3');
INSERT INTO `sys_role_menu` VALUES ('2', '100');
INSERT INTO `sys_role_menu` VALUES ('2', '101');
INSERT INTO `sys_role_menu` VALUES ('2', '102');
INSERT INTO `sys_role_menu` VALUES ('2', '103');
INSERT INTO `sys_role_menu` VALUES ('2', '104');
INSERT INTO `sys_role_menu` VALUES ('2', '105');
INSERT INTO `sys_role_menu` VALUES ('2', '106');
INSERT INTO `sys_role_menu` VALUES ('2', '107');
INSERT INTO `sys_role_menu` VALUES ('2', '108');
INSERT INTO `sys_role_menu` VALUES ('2', '109');
INSERT INTO `sys_role_menu` VALUES ('2', '110');
INSERT INTO `sys_role_menu` VALUES ('2', '111');
INSERT INTO `sys_role_menu` VALUES ('2', '112');
INSERT INTO `sys_role_menu` VALUES ('2', '113');
INSERT INTO `sys_role_menu` VALUES ('2', '114');
INSERT INTO `sys_role_menu` VALUES ('2', '500');
INSERT INTO `sys_role_menu` VALUES ('2', '501');
INSERT INTO `sys_role_menu` VALUES ('2', '1000');
INSERT INTO `sys_role_menu` VALUES ('2', '1001');
INSERT INTO `sys_role_menu` VALUES ('2', '1002');
INSERT INTO `sys_role_menu` VALUES ('2', '1003');
INSERT INTO `sys_role_menu` VALUES ('2', '1004');
INSERT INTO `sys_role_menu` VALUES ('2', '1005');
INSERT INTO `sys_role_menu` VALUES ('2', '1006');
INSERT INTO `sys_role_menu` VALUES ('2', '1007');
INSERT INTO `sys_role_menu` VALUES ('2', '1008');
INSERT INTO `sys_role_menu` VALUES ('2', '1009');
INSERT INTO `sys_role_menu` VALUES ('2', '1010');
INSERT INTO `sys_role_menu` VALUES ('2', '1011');
INSERT INTO `sys_role_menu` VALUES ('2', '1012');
INSERT INTO `sys_role_menu` VALUES ('2', '1013');
INSERT INTO `sys_role_menu` VALUES ('2', '1014');
INSERT INTO `sys_role_menu` VALUES ('2', '1015');
INSERT INTO `sys_role_menu` VALUES ('2', '1016');
INSERT INTO `sys_role_menu` VALUES ('2', '1017');
INSERT INTO `sys_role_menu` VALUES ('2', '1018');
INSERT INTO `sys_role_menu` VALUES ('2', '1019');
INSERT INTO `sys_role_menu` VALUES ('2', '1020');
INSERT INTO `sys_role_menu` VALUES ('2', '1021');
INSERT INTO `sys_role_menu` VALUES ('2', '1022');
INSERT INTO `sys_role_menu` VALUES ('2', '1023');
INSERT INTO `sys_role_menu` VALUES ('2', '1024');
INSERT INTO `sys_role_menu` VALUES ('2', '1025');
INSERT INTO `sys_role_menu` VALUES ('2', '1026');
INSERT INTO `sys_role_menu` VALUES ('2', '1027');
INSERT INTO `sys_role_menu` VALUES ('2', '1028');
INSERT INTO `sys_role_menu` VALUES ('2', '1029');
INSERT INTO `sys_role_menu` VALUES ('2', '1030');
INSERT INTO `sys_role_menu` VALUES ('2', '1031');
INSERT INTO `sys_role_menu` VALUES ('2', '1032');
INSERT INTO `sys_role_menu` VALUES ('2', '1033');
INSERT INTO `sys_role_menu` VALUES ('2', '1034');
INSERT INTO `sys_role_menu` VALUES ('2', '1035');
INSERT INTO `sys_role_menu` VALUES ('2', '1036');
INSERT INTO `sys_role_menu` VALUES ('2', '1037');
INSERT INTO `sys_role_menu` VALUES ('2', '1038');
INSERT INTO `sys_role_menu` VALUES ('2', '1039');
INSERT INTO `sys_role_menu` VALUES ('2', '1040');
INSERT INTO `sys_role_menu` VALUES ('2', '1041');
INSERT INTO `sys_role_menu` VALUES ('2', '1042');
INSERT INTO `sys_role_menu` VALUES ('2', '1043');
INSERT INTO `sys_role_menu` VALUES ('2', '1044');
INSERT INTO `sys_role_menu` VALUES ('2', '1045');
INSERT INTO `sys_role_menu` VALUES ('2', '1046');
INSERT INTO `sys_role_menu` VALUES ('2', '1047');
INSERT INTO `sys_role_menu` VALUES ('2', '1048');
INSERT INTO `sys_role_menu` VALUES ('2', '1049');
INSERT INTO `sys_role_menu` VALUES ('2', '1050');
INSERT INTO `sys_role_menu` VALUES ('2', '1051');
INSERT INTO `sys_role_menu` VALUES ('2', '1052');
INSERT INTO `sys_role_menu` VALUES ('2', '1053');
INSERT INTO `sys_role_menu` VALUES ('2', '1054');
INSERT INTO `sys_role_menu` VALUES ('2', '1055');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(30) NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像路径',
  `password` varchar(50) DEFAULT '' COMMENT '密码',
  `salt` varchar(20) DEFAULT '' COMMENT '盐加密',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(20) DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '103', 'admin', '管理员', '00', '***@qq.com', '15888888888', '1', 'fbcef9d424acd462e9cd9e3fd50662ae.jpg', 'b640f9d2330eedc994e85434b1b607d0', 'c86cbd', '0', '0', '192.168.1.51', '2019-06-14 14:54:02', 'admin', '2018-03-16 11:33:00', 'ry', '2019-06-14 14:54:02', '管理员');
INSERT INTO `sys_user` VALUES ('2', '105', 'ry', 'admin', '00', 'ry@qq.com', '15666666666', '1', '', '8e6d98b90472783cc73c17047ddccf36', '222222', '0', '0', '127.0.0.1', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', 'admin', '2018-11-16 09:30:44', '测试员');
INSERT INTO `sys_user` VALUES ('3', '103', 'crc', '小陈', '00', 'ry@163.com', '15888888888', '1', '', '29c67a30398638269fe600f73a054934', '111111', '0', '0', '127.0.0.1', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '管理员');

-- ----------------------------
-- Table structure for sys_user_online
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_online`;
CREATE TABLE `sys_user_online` (
  `sessionId` varchar(50) NOT NULL DEFAULT '' COMMENT '用户会话id',
  `login_name` varchar(50) DEFAULT '' COMMENT '登录账号',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` varchar(10) DEFAULT '' COMMENT '在线状态on_line在线off_line离线',
  `start_timestamp` datetime DEFAULT NULL COMMENT 'session创建时间',
  `last_access_time` datetime DEFAULT NULL COMMENT 'session最后访问时间',
  `expire_time` int(5) DEFAULT '0' COMMENT '超时时间，单位为分钟',
  PRIMARY KEY (`sessionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='在线用户记录';

-- ----------------------------
-- Records of sys_user_online
-- ----------------------------
INSERT INTO `sys_user_online` VALUES ('c9c9ef92-a413-4f88-bb30-22c5c6366b6e', 'admin', '执法大队', '192.168.1.51', '内网IP', 'Internet Explorer 11', 'Windows 7', 'on_line', '2019-06-14 14:53:59', '2019-06-14 15:56:19', '1800000');

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `post_id` int(11) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与岗位关联表';

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES ('1', '1');
INSERT INTO `sys_user_post` VALUES ('2', '2');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2');

-- ----------------------------
-- Table structure for vehicle_table_info
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_table_info`;
CREATE TABLE `vehicle_table_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vehicle_id` int(8) DEFAULT NULL COMMENT '序号',
  `vehicle_type` varchar(24) DEFAULT NULL COMMENT '车型',
  `alex_num` int(8) DEFAULT NULL COMMENT '轴数',
  `vehicle_limit` decimal(14,3) DEFAULT NULL COMMENT '限重',
  `vehicle_starttime` datetime DEFAULT NULL COMMENT '开始时间',
  `vehicle_endtime` datetime DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vehicle_table_info
-- ----------------------------
INSERT INTO `vehicle_table_info` VALUES ('1', '1', '15', '3', '30.000', '2018-11-01 13:11:04', '2018-12-31 13:11:09');

-- ----------------------------
-- Table structure for vehicle_weight_limit
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_weight_limit`;
CREATE TABLE `vehicle_weight_limit` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `alex_count` int(10) DEFAULT NULL COMMENT '轴数',
  `weight_limit` bigint(19) DEFAULT NULL COMMENT '限重 kg',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(255)  DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vehicle_weight_limit
-- ----------------------------
INSERT INTO `vehicle_weight_limit` VALUES ('1', '2', '18', null, null, null);
INSERT INTO `vehicle_weight_limit` VALUES ('2', '3', '27', null, null, null);
INSERT INTO `vehicle_weight_limit` VALUES ('3', '4', '36', null, null, null);
INSERT INTO `vehicle_weight_limit` VALUES ('4', '5', '43', null, null, null);
INSERT INTO `vehicle_weight_limit` VALUES ('5', '6', '49', null, null, null);

-- ----------------------------
-- Table structure for weight_data
-- ----------------------------
DROP TABLE IF EXISTS `weight_data`;
CREATE TABLE `weight_data` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `limit_over_level` int(8) DEFAULT NULL COMMENT '超限等级',
  `limit_mode` int(1) DEFAULT NULL COMMENT '1=轴数限重；2=车型限重',
  `vehicle_type` varchar(24) DEFAULT NULL COMMENT '车型',
  `weighting_id` int(8) DEFAULT NULL COMMENT '称重序号',
  `weighting_date` datetime DEFAULT NULL COMMENT '称重时间',
  `lane` int(2) DEFAULT NULL COMMENT '车道号',
  `direction` varchar(24) DEFAULT NULL COMMENT '行驶方向',
  `truck_number` varchar(32) DEFAULT NULL COMMENT '车牌号',
  `truck_corlor` varchar(12) DEFAULT NULL COMMENT '车牌颜色',
  `speed` decimal(5,1) DEFAULT NULL COMMENT '车速',
  `axle_count` int(2) DEFAULT NULL COMMENT '轴数',
  `axle_type` varchar(24) DEFAULT NULL COMMENT '轴型序列',
  `weight` decimal(12,2) DEFAULT '0.00' COMMENT '总重',
  `limit_weight` decimal(12,2) DEFAULT '0.00' COMMENT '限重',
  `over_weight` decimal(12,2) DEFAULT '0.00' COMMENT '超重',
  `axle_weight1` decimal(12,2) DEFAULT '0.00',
  `axle_weight2` decimal(12,2) DEFAULT '0.00',
  `axle_weight3` decimal(12,2) DEFAULT '0.00',
  `axle_weight4` decimal(12,2) DEFAULT '0.00',
  `axle_weight5` decimal(12,2) DEFAULT '0.00',
  `axle_weight6` decimal(12,2) DEFAULT '0.00',
  `axle_weight7` decimal(12,2) DEFAULT '0.00',
  `axle_weight8` decimal(12,2) DEFAULT '0.00',
  `sequence_tag` char(24) DEFAULT NULL COMMENT '唯一序列号',
  `ftp_head` varchar(128) DEFAULT NULL COMMENT '车头图片地址（秤上）',
  `ftp_axle` varchar(128) DEFAULT NULL COMMENT '车轴图片地址（秤上）',
  `ftp_tail` varchar(128) DEFAULT NULL COMMENT '车尾图片地址（秤上）',
  `ftp_prior_head` varchar(128) DEFAULT NULL COMMENT '秤前车头图片地址',
  `ftp_plate` varchar(128) DEFAULT NULL COMMENT '车牌特征图',
  `ftp_full_view` varchar(128) DEFAULT NULL COMMENT '车头视频文件地址',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  `device_code` varchar(8) DEFAULT NULL COMMENT '设备编码',
  `station_id` bigint(20) DEFAULT NULL COMMENT '站点ID',
  `lwh_date` timestamp NULL DEFAULT NULL COMMENT '超限采集时间',
  `plate` varchar(16) DEFAULT NULL COMMENT '超限车牌号',
  `width` varchar(16) DEFAULT '0' COMMENT '车宽',
  `height` varchar(16) DEFAULT '0' COMMENT '车高',
  `length` varchar(16) DEFAULT '0' COMMENT '车长',
  `lane_mid` varchar(16) DEFAULT NULL COMMENT '中间车道号',
  `lane_min` varchar(16) DEFAULT NULL COMMENT '小车道号',
  `lane_max` varchar(16) DEFAULT NULL COMMENT '大车道号',
  `pass_time` varchar(8) DEFAULT NULL COMMENT '通过时间',
  `upload_tag` tinyint(3) DEFAULT '0' COMMENT '上传标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=603752 DEFAULT CHARSET=utf8 COMMENT='重量数据';


-- ----------------------------
-- Table structure for weight_type_table_info
-- ----------------------------
DROP TABLE IF EXISTS `weight_type_table_info`;
CREATE TABLE `weight_type_table_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `weight_type` int(2) DEFAULT NULL COMMENT '1=轴数限重；2=车型限重',
  `weight_type_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weight_type_table_info
-- ----------------------------
INSERT INTO `weight_type_table_info` VALUES ('1', '1', '2018-11-01 16:10:55');

-- ----------------------------
-- Table structure for device_name
-- ----------------------------
DROP TABLE IF EXISTS `device_name`;
CREATE TABLE `device_name` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `device_name` varchar(50) DEFAULT NULL COMMENT '设备名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device_name
-- ----------------------------
INSERT INTO `device_name` VALUES ('1', '宽高雷达1');
INSERT INTO `device_name` VALUES ('2', '宽高雷达2');
INSERT INTO `device_name` VALUES ('3', '宽高雷达3');
INSERT INTO `device_name` VALUES ('4', '宽高雷达4');
INSERT INTO `device_name` VALUES ('5', '宽高雷达5');
INSERT INTO `device_name` VALUES ('6', '宽高雷达6');
INSERT INTO `device_name` VALUES ('7', '长雷达1');
INSERT INTO `device_name` VALUES ('8', '长雷达2');
INSERT INTO `device_name` VALUES ('9', '长雷达3');
INSERT INTO `device_name` VALUES ('10', '长雷达4');
INSERT INTO `device_name` VALUES ('11', '长雷达5');
INSERT INTO `device_name` VALUES ('12', '长雷达6');
INSERT INTO `device_name` VALUES ('13', '长雷达7');
INSERT INTO `device_name` VALUES ('14', '长雷达8');
INSERT INTO `device_name` VALUES ('15', '长雷达9');
INSERT INTO `device_name` VALUES ('16', '车道1前抓拍');
INSERT INTO `device_name` VALUES ('17', '车道2前抓拍');
INSERT INTO `device_name` VALUES ('18', '车道3前抓拍');
INSERT INTO `device_name` VALUES ('19', '车道4前抓拍');
INSERT INTO `device_name` VALUES ('20', '车道5前抓拍');
INSERT INTO `device_name` VALUES ('21', '车道1后抓拍');
INSERT INTO `device_name` VALUES ('22', '车道2后抓拍');
INSERT INTO `device_name` VALUES ('23', '车道3后抓拍');
INSERT INTO `device_name` VALUES ('24', '车道4后抓拍');
INSERT INTO `device_name` VALUES ('25', '车道5后抓拍');
INSERT INTO `device_name` VALUES ('26', '右前侧抓拍');
INSERT INTO `device_name` VALUES ('27', '左前侧抓拍');
INSERT INTO `device_name` VALUES ('28', '高清球机');
INSERT INTO `device_name` VALUES ('29', '称重工控机');
INSERT INTO `device_name` VALUES ('30', '称重仪表');
INSERT INTO `device_name` VALUES ('31', '称重自启服务');

-- ----------------------------
-- Table structure for device_type
-- ----------------------------
DROP TABLE IF EXISTS `device_type`;
CREATE TABLE `device_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '设备类型编号',
  `device_type_name` varchar(30) DEFAULT NULL COMMENT '设备类型名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device_type
-- ----------------------------
INSERT INTO `device_type` VALUES ('1', '工控机');
INSERT INTO `device_type` VALUES ('2', '摄像机');
INSERT INTO `device_type` VALUES ('3', '雷达');

-- ----------------------------
-- Table structure for station_device_info
-- ----------------------------
DROP TABLE IF EXISTS `station_device_info`;
CREATE TABLE `station_device_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `station_id` int(11) DEFAULT NULL COMMENT '站点编号',
  `deviceType_id` int(11) DEFAULT NULL COMMENT '设备类型编号',
  `deviceName_id` int(11) DEFAULT NULL COMMENT '设备名称编号',
  `ipAddress` varchar(30) DEFAULT NULL COMMENT '设备IP地址',
  `port` varchar(30) DEFAULT NULL COMMENT '设备端口号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of station_device_info
-- ----------------------------
INSERT INTO `station_device_info` VALUES ('1', '3', '3', '1', '192.168.1.79', '10001');
INSERT INTO `station_device_info` VALUES ('2', '3', '3', '2', '192.168.1.80', '10001');
INSERT INTO `station_device_info` VALUES ('3', '3', '3', '3', '192.168.1.81', '10001');
INSERT INTO `station_device_info` VALUES ('4', '3', '3', '4', '192.168.1.82', '10001');
INSERT INTO `station_device_info` VALUES ('5', '3', '3', '5', '192.168.1.83', '10001');
INSERT INTO `station_device_info` VALUES ('6', '3', '3', '7', '192.168.1.84', '10001');
INSERT INTO `station_device_info` VALUES ('7', '3', '3', '8', '192.168.1.85', '10001');
INSERT INTO `station_device_info` VALUES ('8', '3', '3', '9', '192.168.1.86', '10001');
INSERT INTO `station_device_info` VALUES ('9', '3', '3', '10', '192.168.1.87', '10001');
INSERT INTO `station_device_info` VALUES ('10', '3', '3', '11', '192.168.1.88', '10001');
INSERT INTO `station_device_info` VALUES ('11', '3', '3', '12', '192.168.1.89', '10001');
INSERT INTO `station_device_info` VALUES ('12', '3', '3', '13', '192.168.1.90', '10001');
INSERT INTO `station_device_info` VALUES ('13', '3', '2', '16', '192.168.1.91', '37777');
INSERT INTO `station_device_info` VALUES ('14', '3', '2', '17', '192.168.1.92', '37777');
INSERT INTO `station_device_info` VALUES ('15', '3', '2', '18', '192.168.1.93', '37777');
INSERT INTO `station_device_info` VALUES ('16', '3', '2', '19', '192.168.1.94', '37777');
INSERT INTO `station_device_info` VALUES ('17', '3', '2', '21', '192.168.1.95', '37777');
INSERT INTO `station_device_info` VALUES ('18', '3', '2', '22', '192.168.1.96', '37777');
INSERT INTO `station_device_info` VALUES ('19', '3', '2', '23', '192.168.1.97', '37777');
INSERT INTO `station_device_info` VALUES ('20', '3', '2', '24', '192.168.1.98', '37777');
INSERT INTO `station_device_info` VALUES ('21', '3', '2', '26', '192.168.1.99', '37777');
INSERT INTO `station_device_info` VALUES ('22', '3', '2', '27', '192.168.1.100', '37777');
INSERT INTO `station_device_info` VALUES ('23', '3', '2', '28', '19.201.27.230', null);
INSERT INTO `station_device_info` VALUES ('24', '3', '1', '29', '192.168.1.102', null);
INSERT INTO `station_device_info` VALUES ('25', '3', '1', '30', '192.168.1.103', null);
INSERT INTO `station_device_info` VALUES ('27', '2', '3', '1', '192.168.1.41', '10001');
INSERT INTO `station_device_info` VALUES ('28', '2', '3', '2', '192.168.1.42', '10001');
INSERT INTO `station_device_info` VALUES ('29', '2', '3', '3', '192.168.1.43', '10001');
INSERT INTO `station_device_info` VALUES ('30', '2', '3', '4', '192.168.1.44', '10001');
INSERT INTO `station_device_info` VALUES ('31', '2', '3', '5', '192.168.1.45', '10001');
INSERT INTO `station_device_info` VALUES ('32', '2', '3', '7', '192.168.1.46', '10001');
INSERT INTO `station_device_info` VALUES ('33', '2', '3', '8', '192.168.1.47', '10001');
INSERT INTO `station_device_info` VALUES ('34', '2', '3', '9', '192.168.1.48', '10001');
INSERT INTO `station_device_info` VALUES ('35', '2', '3', '10', '192.168.1.49', '10001');
INSERT INTO `station_device_info` VALUES ('36', '2', '3', '11', '192.168.1.50', '10001');
INSERT INTO `station_device_info` VALUES ('37', '2', '3', '12', '192.168.1.63', '10001');
INSERT INTO `station_device_info` VALUES ('38', '2', '3', '13', '192.168.1.64', '10001');
INSERT INTO `station_device_info` VALUES ('39', '2', '2', '16', '192.168.1.65', '37777');
INSERT INTO `station_device_info` VALUES ('40', '2', '2', '17', '192.168.1.66', '37777');
INSERT INTO `station_device_info` VALUES ('41', '2', '2', '18', '192.168.1.67', '37777');
INSERT INTO `station_device_info` VALUES ('42', '2', '2', '19', '192.168.1.68', '37777');
INSERT INTO `station_device_info` VALUES ('43', '2', '2', '21', '192.168.1.69', '37777');
INSERT INTO `station_device_info` VALUES ('44', '2', '2', '22', '192.168.1.70', '37777');
INSERT INTO `station_device_info` VALUES ('45', '2', '2', '23', '192.168.1.71', '37777');
INSERT INTO `station_device_info` VALUES ('46', '2', '2', '24', '192.168.1.72', '37777');
INSERT INTO `station_device_info` VALUES ('47', '2', '2', '26', '192.168.1.73', '37777');
INSERT INTO `station_device_info` VALUES ('48', '2', '2', '27', '192.168.1.74', '37777');
INSERT INTO `station_device_info` VALUES ('49', '2', '2', '28', '19.201.27.228', null);
INSERT INTO `station_device_info` VALUES ('50', '2', '1', '29', '192.168.1.76', null);
INSERT INTO `station_device_info` VALUES ('51', '2', '1', '30', '192.168.1.77', null);
INSERT INTO `station_device_info` VALUES ('53', '1', '3', '1', '192.168.1.22', '10001');
INSERT INTO `station_device_info` VALUES ('54', '1', '3', '2', '192.168.1.24', '10001');
INSERT INTO `station_device_info` VALUES ('55', '1', '3', '3', '192.168.1.23', '10001');
INSERT INTO `station_device_info` VALUES ('56', '1', '3', '4', '192.168.1.20', '10001');
INSERT INTO `station_device_info` VALUES ('57', '1', '3', '5', '192.168.1.19', '10001');
INSERT INTO `station_device_info` VALUES ('58', '1', '3', '6', '192.168.1.21', '10001');
INSERT INTO `station_device_info` VALUES ('59', '1', '3', '7', '192.168.1.10', '10001');
INSERT INTO `station_device_info` VALUES ('60', '1', '3', '8', '192.168.1.18', '10001');
INSERT INTO `station_device_info` VALUES ('61', '1', '3', '9', '192.168.1.16', '10001');
INSERT INTO `station_device_info` VALUES ('62', '1', '3', '10', '192.168.1.17', '10001');
INSERT INTO `station_device_info` VALUES ('63', '1', '3', '11', '192.168.1.15', '10001');
INSERT INTO `station_device_info` VALUES ('64', '1', '3', '12', '192.168.1.14', '10001');
INSERT INTO `station_device_info` VALUES ('65', '1', '3', '13', '192.168.1.11', '10001');
INSERT INTO `station_device_info` VALUES ('66', '1', '3', '14', '192.168.1.13', '10001');
INSERT INTO `station_device_info` VALUES ('67', '1', '3', '15', '192.168.1.12', '10001');
INSERT INTO `station_device_info` VALUES ('68', '1', '2', '16', '192.168.1.25', '37777');
INSERT INTO `station_device_info` VALUES ('69', '1', '2', '17', '192.168.1.26', '37777');
INSERT INTO `station_device_info` VALUES ('70', '1', '2', '18', '192.168.1.27', '37777');
INSERT INTO `station_device_info` VALUES ('71', '1', '2', '19', '192.168.1.28', '37777');
INSERT INTO `station_device_info` VALUES ('72', '1', '2', '20', '192.168.1.29', '37777');
INSERT INTO `station_device_info` VALUES ('73', '1', '2', '21', '192.168.1.30', '37777');
INSERT INTO `station_device_info` VALUES ('74', '1', '2', '22', '192.168.1.31', '37777');
INSERT INTO `station_device_info` VALUES ('75', '1', '2', '23', '192.168.1.32', '37777');
INSERT INTO `station_device_info` VALUES ('76', '1', '2', '24', '192.168.1.33', '37777');
INSERT INTO `station_device_info` VALUES ('77', '1', '2', '25', '192.168.1.34', '37777');
INSERT INTO `station_device_info` VALUES ('78', '1', '2', '26', '192.168.1.35', '37777');
INSERT INTO `station_device_info` VALUES ('79', '1', '2', '27', '192.168.1.36', '37777');
INSERT INTO `station_device_info` VALUES ('80', '1', '2', '28', '19.201.27.226', null);
INSERT INTO `station_device_info` VALUES ('81', '1', '1', '29', '192.168.1.38', null);
INSERT INTO `station_device_info` VALUES ('82', '1', '1', '30', '192.168.1.39', null);
