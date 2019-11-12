/*
Navicat MySQL Data Transfer

Source Server         : 19.201.248.55
Source Server Version : 50725
Source Host           : 19.201.248.55:3306
Source Database       : ry

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-07-31 17:44:49
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of config_data
-- ----------------------------
INSERT INTO `config_data` VALUES ('1', 'center_url', 'http://localhost:9999/public/weightData/add');
INSERT INTO `config_data` VALUES ('2', 'ftp_path', 'E:\\ftp_copy\\');
INSERT INTO `config_data` VALUES ('3', 'static_file_path', 'E:\\ftp_copy');
INSERT INTO `config_data` VALUES ('4', 'do_upload_tag', '1');
INSERT INTO `config_data` VALUES ('5', 'foshan.tcp.host', '19.128.109.103');
INSERT INTO `config_data` VALUES ('6', 'foshan.tcp.port', '10100');
INSERT INTO `config_data` VALUES ('7', 'site_id', '158');
INSERT INTO `config_data` VALUES ('8', 'nginx_url', 'http://19.201.248.55/');
INSERT INTO `config_data` VALUES ('9', 'yhl.db.host', 'jdbc:oracle:thin:@19.201.248.51:1521:orcl');
INSERT INTO `config_data` VALUES ('10', 'yhl.db.user', 'tocc_zhichao');
INSERT INTO `config_data` VALUES ('11', 'yhl.db.password', 'tocc_zhichao');
INSERT INTO `config_data` VALUES ('12', 'upload_yhl', '1');
INSERT INTO `config_data` VALUES ('13', 'yhl_mq_tag', '0');
INSERT INTO `config_data` VALUES ('14', 'yhl_mq_user', 'zhichao');
INSERT INTO `config_data` VALUES ('15', 'yhl_mq_password', 'zhichao');
INSERT INTO `config_data` VALUES ('16', 'yhl_mq_url', 'tcp://19.201.248.52:61616');

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
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='设备信息';

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
  `ip` varchar(50) DEFAULT NULL COMMENT 'ip',
  `port` int(10) DEFAULT NULL COMMENT '端口',
  `state` int(2) DEFAULT '0' COMMENT '连通状态，0不通，1通',
  `site_id` int(4) DEFAULT NULL COMMENT '上传市局站点Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='站点信息';

-- ----------------------------
-- Records of duge_station_info
-- ----------------------------
INSERT INTO `ry`.`duge_station_info`(`id`, `name`, `abbreviation`, `latitude`, `longitude`, `user_id`, `bureau`, `province`, `city`, `county`, `address`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `device_code`, `longitude_WGS84`, `latitude_WGS84`, `longitude_GCJ02`, `latitude_GCJ02`, `longitude_BD09`, `latitude_BD09`, `ip`, `port`, `state`, `site_id`) VALUES (1, '五沙大桥-东侧（往大良方向）', 'WSD', '113.355856', '22.821707', 1, '广东公路局', '广东省', '佛山市', '顺德区', 'X501  K5＋900顺德区五沙大桥西侧上桥位（五沙方向）', '', NULL, '', NULL, '', '000003e9', '113.3282575600', '22.8109135700', '113.3336510000', '22.8081800000', '113.3402110884', '22.8139434571', '19.201.27.225', 10000, 0, 163);
INSERT INTO `ry`.`duge_station_info`(`id`, `name`, `abbreviation`, `latitude`, `longitude`, `user_id`, `bureau`, `province`, `city`, `county`, `address`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `device_code`, `longitude_WGS84`, `latitude_WGS84`, `longitude_GCJ02`, `latitude_GCJ02`, `longitude_BD09`, `latitude_BD09`, `ip`, `port`, `state`, `site_id`) VALUES (2, '五沙大桥-西侧（往五沙方向）', 'WSX', '113.355856', '22.821707', 1, '广东公路局', '广东省', '佛山市', '顺德区', 'X501 K8＋100左顺德区五沙大桥东侧上桥位（往大良方向）', '', NULL, '', NULL, '', NULL, '113.3418746300', '22.8185526000', '113.3473090000', '22.8158600000', '113.3538941319', '22.8215446312', '19.201.27.227', 10000, 0, 161);
INSERT INTO `ry`.`duge_station_info`(`id`, `name`, `abbreviation`, `latitude`, `longitude`, `user_id`, `bureau`, `province`, `city`, `county`, `address`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `device_code`, `longitude_WGS84`, `latitude_WGS84`, `longitude_GCJ02`, `latitude_GCJ02`, `longitude_BD09`, `latitude_BD09`, `ip`, `port`, `state`, `site_id`) VALUES (3, '三善大桥-下桥位（往顺德方向）', 'SSX', '113.355856', '22.821707', 1, '广东公路局', '广东省', '佛山市', '顺德区', 'G325  K14＋500顺德区三善大桥下桥位附近（往顺德方向）', '', NULL, '', NULL, '', NULL, '113.2808478400', '22.8855975700', '113.2861830000', '22.8828340000', '113.2925990518', '22.8891588412', '19.201.27.229', 10000, 0, 167);
INSERT INTO `ry`.`duge_station_info`(`id`, `name`, `abbreviation`, `latitude`, `longitude`, `user_id`, `bureau`, `province`, `city`, `county`, `address`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `device_code`, `longitude_WGS84`, `latitude_WGS84`, `longitude_GCJ02`, `latitude_GCJ02`, `longitude_BD09`, `latitude_BD09`, `ip`, `port`, `state`, `site_id`) VALUES (4, '三善大桥-上桥位（往广州方向）', 'SSS', '113.355856', '22.821707', 1, '广东公路局', '广东省', '佛山市', '顺德区', 'G325  K14＋500顺德区三善大桥上桥位附近（往广州方向）', '', NULL, '', NULL, '', NULL, '113.2809018400', '22.8855555700', '113.2862370000', '22.8827920000', '113.2926530518', '22.8891168412', '19.201.27.253', 10000, 0, 168);
INSERT INTO `ry`.`duge_station_info`(`id`, `name`, `abbreviation`, `latitude`, `longitude`, `user_id`, `bureau`, `province`, `city`, `county`, `address`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `device_code`, `longitude_WGS84`, `latitude_WGS84`, `longitude_GCJ02`, `latitude_GCJ02`, `longitude_BD09`, `latitude_BD09`, `ip`, `port`, `state`, `site_id`) VALUES (5, '德胜大桥-上桥位（往容桂方向）', 'DSS', '113.316719', '22.80082', 1, '广东公路局', '广东省', '佛山市', '顺德区', 'X520  K6顺德区德胜大桥上桥位旧收费站附近（往容桂方向）', '', NULL, '', NULL, '', '000003e9', '113.2997091300', '22.8049471900', '113.3050660000', '22.8021770000', '113.3115476422', '22.8082631319', '19.201.27.233', 10000, 0, 159);
INSERT INTO `ry`.`duge_station_info`(`id`, `name`, `abbreviation`, `latitude`, `longitude`, `user_id`, `bureau`, `province`, `city`, `county`, `address`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `device_code`, `longitude_WGS84`, `latitude_WGS84`, `longitude_GCJ02`, `latitude_GCJ02`, `longitude_BD09`, `latitude_BD09`, `ip`, `port`, `state`, `site_id`) VALUES (6, '德胜大桥-下桥位（往大良方向）', 'DSX', '113.316719', '22.80082', 1, '广东公路局', '广东省', '佛山市', '顺德区', 'X520  K6顺德区德胜大桥上桥位旧收费站附近（往容桂方向）', '', NULL, '', NULL, '', '000003e9', '113.2995294000', '22.8048919500', '113.3048740000', '22.8021110000', '113.3113203215', '22.8083471618', '19.201.27.231', 10000, 0, 160);
INSERT INTO `ry`.`duge_station_info`(`id`, `name`, `abbreviation`, `latitude`, `longitude`, `user_id`, `bureau`, `province`, `city`, `county`, `address`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `device_code`, `longitude_WGS84`, `latitude_WGS84`, `longitude_GCJ02`, `latitude_GCJ02`, `longitude_BD09`, `latitude_BD09`, `ip`, `port`, `state`, `site_id`) VALUES (7, '乐龙大桥-南侧（往乐从方向）', 'LLN', '113.355856', '22.821707', 1, '广东公路局', '广东省', '佛山市', '顺德区', '乐龙路乐龙大桥南侧上桥位附近（往乐从方向）', '', NULL, '', NULL, '', NULL, '113.0942771500', '22.8864718300', '113.0997530000', '22.8838660000', '113.1063098779', '22.8895422900', '19.201.27.237', 10000, 0, 169);
INSERT INTO `ry`.`duge_station_info`(`id`, `name`, `abbreviation`, `latitude`, `longitude`, `user_id`, `bureau`, `province`, `city`, `county`, `address`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `device_code`, `longitude_WGS84`, `latitude_WGS84`, `longitude_GCJ02`, `latitude_GCJ02`, `longitude_BD09`, `latitude_BD09`, `ip`, `port`, `state`, `site_id`) VALUES (8, '乐龙大桥-北侧（往龙江方向）', 'LLB', '113.355856', '22.821707', 1, '广东公路局', '广东省', '佛山市', '顺德区', '乐龙路乐龙大桥北侧上桥位附近（往龙江方向）', '', NULL, '', NULL, '', NULL, '113.0977189500', '22.8912152500', '113.1031970000', '22.8886150000', '113.1097485908', '22.8942889788', '19.201.27.235', 10000, 0, 170);
INSERT INTO `ry`.`duge_station_info`(`id`, `name`, `abbreviation`, `latitude`, `longitude`, `user_id`, `bureau`, `province`, `city`, `county`, `address`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `device_code`, `longitude_WGS84`, `latitude_WGS84`, `longitude_GCJ02`, `latitude_GCJ02`, `longitude_BD09`, `latitude_BD09`, `ip`, `port`, `state`, `site_id`) VALUES (9, '三洪奇大桥-上桥位（往北滘方向）', 'SHQS', '113.355856', '22.821707', 1, '广东公路局', '广东省', '佛山市', '顺德区', 'G105  K2954＋500顺德区三洪奇大桥南下桥位附近（往伦教方向）', '', NULL, '', NULL, '', NULL, '113.2071029900', '22.8971377700', '113.2124540000', '22.8944000000', '113.2189867613', '22.9001573624', '19.201.27.245', 10000, 0, 164);
INSERT INTO `ry`.`duge_station_info`(`id`, `name`, `abbreviation`, `latitude`, `longitude`, `user_id`, `bureau`, `province`, `city`, `county`, `address`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `device_code`, `longitude_WGS84`, `latitude_WGS84`, `longitude_GCJ02`, `latitude_GCJ02`, `longitude_BD09`, `latitude_BD09`, `ip`, `port`, `state`, `site_id`) VALUES (10, '三洪奇大桥-下桥位（往伦教方向）', 'SHQX', '113.355856', '22.821707', 1, '广东公路局', '广东省', '佛山市', '顺德区', 'G105  K2954＋500顺德区三洪奇大桥南上桥位附近（往北滘方向）', '', NULL, '', NULL, '', NULL, '113.2073119900', '22.8971037700', '113.2126630000', '22.8943660000', '113.2191957613', '22.9001233624', '19.201.27.247', 10000, 0, 166);
INSERT INTO `ry`.`duge_station_info`(`id`, `name`, `abbreviation`, `latitude`, `longitude`, `user_id`, `bureau`, `province`, `city`, `county`, `address`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`, `device_code`, `longitude_WGS84`, `latitude_WGS84`, `longitude_GCJ02`, `latitude_GCJ02`, `longitude_BD09`, `latitude_BD09`, `ip`, `port`, `state`, `site_id`) VALUES (11, '顺番公路-甘竹滩大桥（往龙江方向）', 'SFGL', '113.082437', '22.806989', 1, '广东公路局', '广东省', '佛山市', '顺德区', '顺番公路沙涌路口公交站路段（往龙江方向）', '', NULL, '', NULL, '', NULL, '113.082437', '22.806989', '113.082437', '22.806989', '113.082437', '22.806989', '19.201.27.225', 10000, 0, 171);

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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='重量数据';

-- ----------------------------
-- Records of exception_data
-- ----------------------------
INSERT INTO `exception_data` VALUES ('33', null, '1', '125', '1', '2019-03-18 11:26:28', '1', '进程方向', '川B12345', '黄', '55.5', '4', '1222', '48.00', '40.00', '8.00', '10.00', '12.00', '12.00', '14.00', '0.00', '0.00', '0.00', '0.00', '000003e912f6037400000003', null, null, null, null, null, null, '', null, '', null, '', '000003e9', null, null, null, null, null, null, null, null, null, null, '长宽高数据缺失。');
INSERT INTO `exception_data` VALUES ('34', null, '0', null, '0', null, '0', null, null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '', null, '', null, '', '', null, '2019-03-18 10:27:53', '川B12345', '1700', '2400', '15000', '1', '1', '2', '1000', '衡器称重数据缺失。');

-- ----------------------------
-- Table structure for ftp_server
-- ----------------------------
DROP TABLE IF EXISTS `ftp_server`;
CREATE TABLE `ftp_server` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) DEFAULT '' COMMENT 'name',
  `url` varchar(255) DEFAULT '' COMMENT 'url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ftp_server
-- ----------------------------
INSERT INTO `ftp_server` VALUES ('1', 'local', 'ftp://127.0.0.1:2121?recursive=true&delete=true&binary=true&delay=5s&passiveMode=true&ftpClient=#ftpClient&ftpClientConfig=#ftpClientConfig');

-- ----------------------------
-- Table structure for led_table_info
-- ----------------------------
DROP TABLE IF EXISTS `led_table_info`;
CREATE TABLE `led_table_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `led_id` int(8) DEFAULT NULL COMMENT '序号',
  `ip_address` varchar(64) DEFAULT NULL COMMENT 'Led设备IP地址',
  `led_content` varchar(1024) DEFAULT NULL COMMENT '固定文本内容',
  `led_color` varchar(12) DEFAULT NULL COMMENT '字体颜色',
  `led_index` int(4) DEFAULT NULL COMMENT '显示顺序',
  `led_start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `led_end_time` datetime DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of led_table_info
-- ----------------------------
INSERT INTO `led_table_info` VALUES ('1', '1', '192.168.1.100', '你好', '红', '1', '2018-11-01 16:59:40', '2018-12-31 16:59:45');
INSERT INTO `led_table_info` VALUES ('2', '2', '192.168.1.100', '佛山市', '红', '2', '2018-11-01 16:59:40', '2018-12-31 16:59:45');

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `blob_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `sched_name` varchar(120) NOT NULL,
  `calendar_name` varchar(200) NOT NULL,
  `calendar` blob NOT NULL,
  PRIMARY KEY (`sched_name`,`calendar_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `cron_expression` varchar(200) NOT NULL,
  `time_zone_id` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', '0/10 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME100', 'DEFAULT', '0/10 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME101', 'DEFAULT', '0/10 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME102', 'DEFAULT', '0 * 1 * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', '0/20 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', '__TASK_CLASS_NAME__1', 'DEFAULT', '0/10 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', '__TASK_CLASS_NAME__2', 'DEFAULT', '0/20 * * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `entry_id` varchar(95) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `instance_name` varchar(200) NOT NULL,
  `fired_time` bigint(13) NOT NULL,
  `sched_time` bigint(13) NOT NULL,
  `priority` int(11) NOT NULL,
  `state` varchar(16) NOT NULL,
  `job_name` varchar(200) DEFAULT NULL,
  `job_group` varchar(200) DEFAULT NULL,
  `is_nonconcurrent` varchar(1) DEFAULT NULL,
  `requests_recovery` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `sched_name` varchar(120) NOT NULL,
  `job_name` varchar(200) NOT NULL,
  `job_group` varchar(200) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `job_class_name` varchar(250) NOT NULL,
  `is_durable` varchar(1) NOT NULL,
  `is_nonconcurrent` varchar(1) NOT NULL,
  `is_update_data` varchar(1) NOT NULL,
  `requests_recovery` varchar(1) NOT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`job_name`,`job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', null, 'com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F504552544945537372001E636F6D2E72756F79692E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200094C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000A6D6574686F644E616D6571007E00094C000C6D6574686F64506172616D7371007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720027636F6D2E72756F79692E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707400007070707400013174000E302F3130202A202A202A202A203F740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E697A0E58F82EFBC897372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000174000672795461736B74000A72794E6F506172616D7374000074000131740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME100', 'DEFAULT', null, 'com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F504552544945537372001E636F6D2E72756F79692E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200094C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000A6D6574686F644E616D6571007E00094C000C6D6574686F64506172616D7371007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720027636F6D2E72756F79692E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001678BC863A878707400007070707400013174000E302F3130202A202A202A202A203F7400047363616E7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B020000787000000000000000647400087363616E5461736B74000E7363616E4465766963654461746174000074000130740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME101', 'DEFAULT', null, 'com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F504552544945537372001E636F6D2E72756F79692E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200094C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000A6D6574686F644E616D6571007E00094C000C6D6574686F64506172616D7371007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720027636F6D2E72756F79692E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B597419030000787077080000016840AF071878707400007070707400013174000E302F3130202A202A202A202A203F740008736974655363616E7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000006574000C736974655363616E5461736B74000F7363616E56656869636C654461746174000074000130740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME102', 'DEFAULT', null, 'com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F504552544945537372001E636F6D2E72756F79692E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200094C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000A6D6574686F644E616D6571007E00094C000C6D6574686F64506172616D7371007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720027636F6D2E72756F79692E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000168433F1C2078707400007070707400013174000B30202A2031202A202A203F7400036674707372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B020000787000000000000000667400076674705461736B7400077363616E46747074000074000132740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', null, 'com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F504552544945537372001E636F6D2E72756F79692E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200094C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000A6D6574686F644E616D6571007E00094C000C6D6574686F64506172616D7371007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720027636F6D2E72756F79692E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707400007070707400013174000E302F3230202A202A202A202A203F740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E69C89E58F82EFBC897372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000274000672795461736B7400087279506172616D73740002727974000131740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', '__TASK_CLASS_NAME__1', 'DEFAULT', null, 'com.ruoyi.quartz.util.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C770800000010000000017400135F5F5441534B5F50524F504552544945535F5F7372001E636F6D2E72756F79692E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200084C000E63726F6E45787072657373696F6E7400124C6A6176612F6C616E672F537472696E673B4C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000A6D6574686F644E616D6571007E00094C000C6D6574686F64506172616D7371007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720020636F6D2E72756F79692E636F6D6D6F6E2E626173652E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E0787074000070707074000E302F3130202A202A202A202A203F740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E697A0E58F82EFBC897372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000174000672795461736B74000A72794E6F506172616D7374000074000131740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', '__TASK_CLASS_NAME__2', 'DEFAULT', null, 'com.ruoyi.quartz.util.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C770800000010000000017400135F5F5441534B5F50524F504552544945535F5F7372001E636F6D2E72756F79692E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200084C000E63726F6E45787072657373696F6E7400124C6A6176612F6C616E672F537472696E673B4C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000A6D6574686F644E616D6571007E00094C000C6D6574686F64506172616D7371007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720020636F6D2E72756F79692E636F6D6D6F6E2E626173652E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E0787074000070707074000E302F3230202A202A202A202A203F740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E69C89E58F82EFBC897372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000274000672795461736B7400087279506172616D73740002727974000131740001317800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `sched_name` varchar(120) NOT NULL,
  `lock_name` varchar(40) NOT NULL,
  PRIMARY KEY (`sched_name`,`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('RuoyiScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('RuoyiScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `sched_name` varchar(120) NOT NULL,
  `instance_name` varchar(200) NOT NULL,
  `last_checkin_time` bigint(13) NOT NULL,
  `checkin_interval` bigint(13) NOT NULL,
  PRIMARY KEY (`sched_name`,`instance_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('RuoyiScheduler', 'localhost.localdomain1564466625960', '1564566291465', '15000');

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `repeat_count` bigint(7) NOT NULL,
  `repeat_interval` bigint(12) NOT NULL,
  `times_triggered` bigint(10) NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `str_prop_1` varchar(512) DEFAULT NULL,
  `str_prop_2` varchar(512) DEFAULT NULL,
  `str_prop_3` varchar(512) DEFAULT NULL,
  `int_prop_1` int(11) DEFAULT NULL,
  `int_prop_2` int(11) DEFAULT NULL,
  `long_prop_1` bigint(20) DEFAULT NULL,
  `long_prop_2` bigint(20) DEFAULT NULL,
  `dec_prop_1` decimal(13,4) DEFAULT NULL,
  `dec_prop_2` decimal(13,4) DEFAULT NULL,
  `bool_prop_1` varchar(1) DEFAULT NULL,
  `bool_prop_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `job_name` varchar(200) NOT NULL,
  `job_group` varchar(200) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `next_fire_time` bigint(13) DEFAULT NULL,
  `prev_fire_time` bigint(13) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `trigger_state` varchar(16) NOT NULL,
  `trigger_type` varchar(8) NOT NULL,
  `start_time` bigint(13) NOT NULL,
  `end_time` bigint(13) DEFAULT NULL,
  `calendar_name` varchar(200) DEFAULT NULL,
  `misfire_instr` smallint(2) DEFAULT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  KEY `sched_name` (`sched_name`,`job_name`,`job_group`),
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', 'TASK_CLASS_NAME1', 'DEFAULT', null, '1556193570000', '-1', '5', 'PAUSED', 'CRON', '1556193562000', '0', null, '-1', '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME100', 'DEFAULT', 'TASK_CLASS_NAME100', 'DEFAULT', null, '1556193570000', '-1', '5', 'PAUSED', 'CRON', '1556193563000', '0', null, '0', '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME101', 'DEFAULT', 'TASK_CLASS_NAME101', 'DEFAULT', null, '1556193570000', '-1', '5', 'PAUSED', 'CRON', '1556193564000', '0', null, '0', '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME102', 'DEFAULT', 'TASK_CLASS_NAME102', 'DEFAULT', null, '1556211600000', '-1', '5', 'PAUSED', 'CRON', '1556193564000', '0', null, '1', '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', 'TASK_CLASS_NAME2', 'DEFAULT', null, '1556193580000', '-1', '5', 'PAUSED', 'CRON', '1556193562000', '0', null, '-1', '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', '__TASK_CLASS_NAME__1', 'DEFAULT', '__TASK_CLASS_NAME__1', 'DEFAULT', null, '1553089690000', '-1', '5', 'PAUSED', 'CRON', '1553089686000', '0', null, '-1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C770800000010000000017400135F5F5441534B5F50524F504552544945535F5F7372001E636F6D2E72756F79692E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200084C000E63726F6E45787072657373696F6E7400124C6A6176612F6C616E672F537472696E673B4C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000A6D6574686F644E616D6571007E00094C000C6D6574686F64506172616D7371007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720020636F6D2E72756F79692E636F6D6D6F6E2E626173652E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E0787074000070707074000E302F3130202A202A202A202A203F740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E697A0E58F82EFBC897372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000174000672795461736B74000A72794E6F506172616D7374000074000131740001317800);
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', '__TASK_CLASS_NAME__2', 'DEFAULT', '__TASK_CLASS_NAME__2', 'DEFAULT', null, '1553089700000', '-1', '5', 'PAUSED', 'CRON', '1553089687000', '0', null, '-1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C770800000010000000017400135F5F5441534B5F50524F504552544945535F5F7372001E636F6D2E72756F79692E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200084C000E63726F6E45787072657373696F6E7400124C6A6176612F6C616E672F537472696E673B4C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000A6D6574686F644E616D6571007E00094C000C6D6574686F64506172616D7371007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720020636F6D2E72756F79692E636F6D6D6F6E2E626173652E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E0787074000070707074000E302F3230202A202A202A202A203F740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E69C89E58F82EFBC897372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000274000672795461736B7400087279506172616D73740002727974000131740001317800);

-- ----------------------------
-- Table structure for rs_air_quality_record
-- ----------------------------
DROP TABLE IF EXISTS `rs_air_quality_record`;
CREATE TABLE `rs_air_quality_record` (
  `JLSJ` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '记录时间YYYYMMDD24HHMMSS',
  `DWBH` varchar(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PM25` decimal(3,2) DEFAULT NULL,
  `PM10` decimal(3,2) DEFAULT NULL,
  `CO` decimal(3,2) DEFAULT NULL,
  `SO2` decimal(3,2) DEFAULT NULL,
  `O3` decimal(3,2) DEFAULT NULL COMMENT 'O3',
  `NO2` decimal(3,2) DEFAULT NULL COMMENT 'NO2',
  PRIMARY KEY (`JLSJ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='点位环境空气质量记录表';

-- ----------------------------
-- Records of rs_air_quality_record
-- ----------------------------

-- ----------------------------
-- Table structure for rs_device_check_log
-- ----------------------------
DROP TABLE IF EXISTS `rs_device_check_log`;
CREATE TABLE `rs_device_check_log` (
  `ID` int(19) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `DWBH` varchar(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '点位编号',
  `YCXBH` varchar(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '遥测线编号',
  `JCLX` int(1) DEFAULT NULL COMMENT '检查类型',
  `JCKSSJ` date DEFAULT NULL COMMENT '检查开始时间',
  `SFTG` varchar(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否通过',
  `BZ` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `JCDW` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '检查单位',
  `JCRY` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '检查人员',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='遥测设备检查信息表';

-- ----------------------------
-- Records of rs_device_check_log
-- ----------------------------

-- ----------------------------
-- Table structure for rs_device_dynamic_check
-- ----------------------------
DROP TABLE IF EXISTS `rs_device_dynamic_check`;
CREATE TABLE `rs_device_dynamic_check` (
  `ID` int(19) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `DWBH` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '点位编号',
  `YCXBH` varchar(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '遥测线编号',
  `JCKSSJ` date DEFAULT NULL COMMENT '检查开始时间',
  `BQLB` varchar(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标气类别',
  `XSSD` decimal(3,1) DEFAULT NULL COMMENT '行驶速度',
  `CO2BZZ` decimal(3,1) DEFAULT NULL COMMENT 'CO2标准值',
  `COBZZ` decimal(3,1) DEFAULT NULL COMMENT 'CO标准值',
  `NOBZZ` decimal(4,0) DEFAULT NULL COMMENT 'NO标准值',
  `CO2CLZ` decimal(3,1) DEFAULT NULL COMMENT 'CO2测量值',
  `COCLZ` decimal(3,1) DEFAULT NULL COMMENT 'CO测量值',
  `NOCLZ` decimal(4,0) DEFAULT NULL COMMENT 'NO测量值',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='遥测设备动态检查过程数据';

-- ----------------------------
-- Records of rs_device_dynamic_check
-- ----------------------------

-- ----------------------------
-- Table structure for rs_device_self_check
-- ----------------------------
DROP TABLE IF EXISTS `rs_device_self_check`;
CREATE TABLE `rs_device_self_check` (
  `ID` int(19) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `DWBH` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '点位编号',
  `YCXBH` varchar(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '遥测线编号',
  `ZJKSRQ` date DEFAULT NULL COMMENT '自检开始时间',
  `CO2BZZ` decimal(3,1) DEFAULT NULL COMMENT 'CO2标准值',
  `COBZZ` decimal(3,1) DEFAULT NULL COMMENT 'CO标准值',
  `DEXBZZ` decimal(4,0) DEFAULT NULL COMMENT '1,3-丁二烯标准值',
  `BWBZZ` decimal(4,0) DEFAULT NULL COMMENT '丙烷标准值',
  `NOBZZ` decimal(4,0) DEFAULT NULL COMMENT 'NO标准值',
  `CO2CLZ` decimal(3,1) DEFAULT NULL COMMENT 'CO2测量值',
  `COCLZ` decimal(3,1) DEFAULT NULL COMMENT 'CO测量值',
  `DEXCLZ` decimal(4,0) DEFAULT NULL COMMENT '1,3-丁二烯测量值',
  `BWCLZ` decimal(4,0) DEFAULT NULL COMMENT '丙烷测量值',
  `NOCLZ` decimal(4,0) DEFAULT NULL COMMENT 'NO测量值',
  `YDP1BZZ` decimal(3,1) DEFAULT NULL COMMENT '烟度片1标准值',
  `YDP2BZZ` decimal(3,1) DEFAULT NULL COMMENT '烟度片2标准值',
  `YDP3BZZ` decimal(3,1) DEFAULT NULL COMMENT '烟度片3标准值',
  `YDP4BZZ` decimal(3,1) DEFAULT NULL COMMENT '烟度片4标准值',
  `YDP5BZZ` decimal(3,1) DEFAULT NULL COMMENT '烟度片5标准值',
  `YDP1CLZ` decimal(3,1) DEFAULT NULL COMMENT '烟度片1测量值',
  `YDP2CLZ` decimal(3,1) DEFAULT NULL COMMENT '烟度片2测量值',
  `YDP3CLZ` decimal(3,1) DEFAULT NULL COMMENT '烟度片3测量值',
  `YDP4CLZ` decimal(3,1) DEFAULT NULL COMMENT '烟度片4测量值',
  `YDP5CLZ` decimal(3,1) DEFAULT NULL COMMENT '烟度片5测量值',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='遥测设备自检过程数据';

-- ----------------------------
-- Records of rs_device_self_check
-- ----------------------------

-- ----------------------------
-- Table structure for rs_device_static_check
-- ----------------------------
DROP TABLE IF EXISTS `rs_device_static_check`;
CREATE TABLE `rs_device_static_check` (
  `ID` int(19) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `JZBH` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '点位编号',
  `YCXBH` varchar(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '遥测线编号',
  `JCKSSJ` date DEFAULT NULL COMMENT '检查开始时间',
  `BQLB` varchar(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标气类别',
  `CO2BZZ` decimal(3,1) DEFAULT NULL COMMENT 'CO2标准值',
  `COBZZ` decimal(3,1) DEFAULT NULL COMMENT 'CO标准值',
  `NOBZZ` decimal(4,0) DEFAULT NULL COMMENT 'NO标准值',
  `CO2CLZ` decimal(3,1) DEFAULT NULL COMMENT 'CO2测量值',
  `COCLZ` decimal(3,1) DEFAULT NULL COMMENT 'CO测量值',
  `NOCLZ` decimal(4,0) DEFAULT NULL COMMENT 'NO测量值',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='遥测设备静态检查过程数据';

-- ----------------------------
-- Records of rs_device_static_check
-- ----------------------------

-- ----------------------------
-- Table structure for rs_line
-- ----------------------------
DROP TABLE IF EXISTS `rs_line`;
CREATE TABLE `rs_line` (
  `ID` int(19) NOT NULL AUTO_INCREMENT,
  `DWBH` varchar(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '点位编号',
  `YCXBH` varchar(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '遥感线编号',
  `CDXH` varchar(6) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `JCXTXH` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '检测系统型号',
  `JCXTMC` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '检测系统名称',
  `JCXTBH` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '检测系统编号',
  `JCXTZZC` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '检测系统制作厂',
  `CSYXH` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '测速仪型号',
  `CSYSCC` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '测速仪生产厂',
  `CSYXQ` date DEFAULT NULL COMMENT '测速仪有效期',
  `QTCSYXH` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '气体测试仪型号',
  `QTCSYSCC` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '气体测试仪生产厂',
  `QTCSYYXQ` date DEFAULT NULL COMMENT '气体测试仪有效期',
  `YDJXH` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '烟度计型号',
  `YDJSCC` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '烟度计生产厂',
  `YDJYXQ` date DEFAULT NULL COMMENT '烟度计有效期',
  `SXXTXH` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '摄像系统型号',
  `SXXTSCC` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '摄像系统生产厂',
  `SXXTYXQ` date DEFAULT NULL COMMENT '摄像系统有效期',
  `PDJXH` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '坡度计型号',
  `PDJSCC` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '坡度计生产厂',
  `PDJYXQ` date DEFAULT NULL COMMENT '坡度计有效期',
  `QXZXH` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '气象站型号',
  `QXZSCC` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '气象站生产厂',
  `QXZYXQ` date DEFAULT NULL COMMENT '气象站有效期',
  `uploaded` int(1) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='点位遥测线信息表';

-- ----------------------------
-- Records of rs_line
-- ----------------------------

-- ----------------------------
-- Table structure for rs_mobile_station
-- ----------------------------
DROP TABLE IF EXISTS `rs_mobile_station`;
CREATE TABLE `rs_mobile_station` (
  `ID` int(19) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `DWBH` varchar(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '点位编号',
  `YCXBH` varchar(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '遥测线编号',
  `JCDWRZH` varchar(8) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '检测点位日志号',
  `CDXH` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '车道序号',
  `CLFX` varchar(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '车流方向',
  `YXDZ` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '运行地址',
  `DDJD` decimal(10,5) DEFAULT NULL COMMENT '地点经度',
  `DDWD` decimal(10,5) DEFAULT NULL COMMENT '地点维度',
  `CDPD` decimal(3,2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='移动式点位运行记录表';

-- ----------------------------
-- Records of rs_mobile_station
-- ----------------------------

-- ----------------------------
-- Table structure for rs_monitor_data_log
-- ----------------------------
DROP TABLE IF EXISTS `rs_monitor_data_log`;
CREATE TABLE `rs_monitor_data_log` (
  `JLBH` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '记录编号',
  `DWBH` varchar(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '点位编号',
  `YCXBH` varchar(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '遥测线编号',
  `JCDWRZH` varchar(8) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '监测点位日志号',
  `CDXH` varchar(6) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '车道序号',
  `JCSJ` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '监测时间',
  `DDJD` decimal(10,5) DEFAULT NULL COMMENT '地点经度',
  `DDWD` decimal(10,5) DEFAULT NULL COMMENT '地点维度',
  `CDPD` decimal(3,2) DEFAULT NULL COMMENT '车道坡度',
  `PDJG` varchar(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '判定结果',
  `HPHM` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '号牌号码',
  `CPYS` varchar(5) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `HPZL` varchar(5) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '号牌种类',
  `RLZL` varchar(3) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '燃料种类',
  `CO2JG` decimal(3,2) DEFAULT NULL COMMENT 'CO2结果',
  `COCO2` decimal(3,2) DEFAULT NULL COMMENT 'CO/CO2比率',
  `HCCO2` decimal(3,2) DEFAULT NULL COMMENT 'HC/CO2比率',
  `NOCO2` decimal(3,2) unsigned DEFAULT NULL COMMENT 'NO/CO2比率',
  `COJG` decimal(3,2) DEFAULT NULL COMMENT 'CO结果',
  `HCJG` decimal(3,2) DEFAULT NULL COMMENT 'HC结果',
  `NOJG` decimal(3,2) DEFAULT NULL COMMENT 'NO结果',
  `BTGDJG` decimal(3,2) DEFAULT NULL COMMENT '不透光度结果',
  `LGMHD` int(1) DEFAULT NULL COMMENT '林格曼黑度',
  `COXZ` decimal(3,2) DEFAULT NULL,
  `NOXZ` decimal(3,2) DEFAULT NULL COMMENT 'NO限值',
  `BTGDXZ` decimal(3,2) DEFAULT NULL COMMENT '不透光度限值',
  `HDXZ` int(1) DEFAULT NULL COMMENT '黑度限值',
  `CLSD` decimal(10,3) DEFAULT NULL COMMENT '车辆速度',
  `CLJSD` decimal(10,3) DEFAULT NULL COMMENT '车辆加速度',
  `VSP` decimal(3,2) DEFAULT NULL COMMENT 'VSP',
  `FS` decimal(3,2) DEFAULT NULL COMMENT '风速',
  `FX` varchar(8) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `HJWD` decimal(3,2) DEFAULT NULL,
  `SD` decimal(3,2) DEFAULT NULL,
  `DQY` decimal(3,2) DEFAULT NULL COMMENT '大气压',
  `GJXXBH` varchar(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '轨迹信息编号',
  `TP1` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图像1文件名',
  `TP2` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图像2文件名',
  `SP1` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '视频1文件名',
  `uploaded` int(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='遥感监测数据信息表';

-- ----------------------------
-- Records of rs_monitor_data_log
-- ----------------------------

-- ----------------------------
-- Table structure for rs_station
-- ----------------------------
DROP TABLE IF EXISTS `rs_station`;
CREATE TABLE `rs_station` (
  `DWBH` varchar(12) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '点位编号',
  `DWMC` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '点位名称',
  `DWLX` varchar(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '点位类型',
  `YXRQ` date DEFAULT NULL COMMENT '运行日期',
  `DWZT` varchar(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '点位状态',
  `DWDZ` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '点位地址',
  `DDJD` decimal(10,5) DEFAULT NULL COMMENT '地点经度',
  `DDWD` decimal(10,5) DEFAULT NULL COMMENT '地点维度',
  `CLFX` varchar(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '车流方向',
  `CDSL` int(2) DEFAULT NULL COMMENT '车道数量',
  `CDPD` decimal(3,2) DEFAULT NULL COMMENT '车道坡度',
  `YCXS` int(2) DEFAULT NULL COMMENT '遥测线数',
  `HPHM` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '号牌号码',
  `CLXH` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '装载车型号',
  PRIMARY KEY (`DWBH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='点位信息表';

-- ----------------------------
-- Records of rs_station
-- ----------------------------

-- ----------------------------
-- Table structure for rs_station_check_log
-- ----------------------------
DROP TABLE IF EXISTS `rs_station_check_log`;
CREATE TABLE `rs_station_check_log` (
  `ID` int(19) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `DWBH` varchar(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `YCXBH` varchar(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '遥测线编号',
  `ZJKSRQ` date DEFAULT NULL COMMENT '自检开始时间',
  `SFTG` varchar(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否通过 Y/N',
  `BZ` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='遥测设备自检信息表';

-- ----------------------------
-- Records of rs_station_check_log
-- ----------------------------

-- ----------------------------
-- Table structure for rs_traffic_flow
-- ----------------------------
DROP TABLE IF EXISTS `rs_traffic_flow`;
CREATE TABLE `rs_traffic_flow` (
  `id` bigint(19) NOT NULL,
  `LLBH` varchar(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `DWBH` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '点位编号',
  `JCDWRZH` varchar(8) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '监测点位日志号',
  `SSDL` varchar(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `LLFL` varchar(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '流量分类',
  `TJSC` varchar(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '统计时长',
  `CJSD` int(2) DEFAULT NULL,
  `TJRQ` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '统计日期',
  `CDXH` varchar(6) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '车道序号',
  `WXXKCS` int(5) DEFAULT NULL COMMENT '为小型客车数',
  `ZXKCS` int(5) DEFAULT NULL COMMENT '中型客车数',
  `DXKCS` int(5) DEFAULT NULL COMMENT '大型客车数',
  `XXHCS` int(5) DEFAULT NULL COMMENT '小型货车数',
  `ZXHCS` int(5) DEFAULT NULL COMMENT '中型货车数',
  `ZXHCS1` int(5) DEFAULT NULL,
  `TXCLS` int(6) DEFAULT NULL COMMENT '通行车辆数',
  `PJSD` int(3) DEFAULT NULL COMMENT '平均速度',
  `PJPDCD` int(4) DEFAULT NULL COMMENT '平均排队长度',
  `uploaded` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='交通流量信息表';

-- ----------------------------
-- Records of rs_traffic_flow
-- ----------------------------

-- ----------------------------
-- Table structure for rs_vehicle_info
-- ----------------------------
DROP TABLE IF EXISTS `rs_vehicle_info`;
CREATE TABLE `rs_vehicle_info` (
  `ID` int(19) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `XZQHDM` varchar(4) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '行政区划代码',
  `HPHM` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '号牌号码',
  `HPYS` varchar(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '车牌颜色',
  `HPZL` varchar(8) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '号牌种类',
  `CLXH` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '车辆型号',
  `SCQY` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '生产企业',
  `RLZL` varchar(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '燃料种类',
  `SYXZ` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '使用性质',
  `CCDJRQ` date DEFAULT NULL COMMENT '初次登记日期',
  `CLSBDH` varchar(17) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PFBZJD` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '排放标准阶段',
  `SCJYRQ` date DEFAULT NULL COMMENT '上次环保定期检验日期',
  `SCJYJG` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '上次环保定期检验机构',
  `uploaded` int(1) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='车辆数据信息表';

-- ----------------------------
-- Records of rs_vehicle_info
-- ----------------------------

-- ----------------------------
-- Table structure for rs_vehicle_trajectory
-- ----------------------------
DROP TABLE IF EXISTS `rs_vehicle_trajectory`;
CREATE TABLE `rs_vehicle_trajectory` (
  `GJXXBH` varchar(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '轨迹信息编号',
  `DWBH` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '点位编号',
  `JCDWRZH` varchar(8) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '监测点位日志号',
  `CDBH` varchar(6) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '车道编号',
  `TGSJ` date DEFAULT NULL COMMENT '通过日期',
  `CLSD` decimal(2,0) DEFAULT NULL COMMENT '车辆速度',
  `HPHM` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '号牌号码',
  `CSYS` varchar(5) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '车身颜色',
  `HPZL` varchar(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '号牌种类',
  `SBZXD` int(2) DEFAULT NULL COMMENT '识别置信度',
  `uploaded` int(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='机动车轨迹信息表';

-- ----------------------------
-- Records of rs_vehicle_trajectory
-- ----------------------------

-- ----------------------------
-- Table structure for slave_user
-- ----------------------------
DROP TABLE IF EXISTS `slave_user`;
CREATE TABLE `slave_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(45) DEFAULT NULL COMMENT '账号',
  `password` varchar(45) DEFAULT NULL COMMENT '密码',
  `name` varchar(45) DEFAULT NULL COMMENT '名字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of slave_user
-- ----------------------------
INSERT INTO `slave_user` VALUES ('1', 'admin', 'admin', '管理员');

-- ----------------------------
-- Table structure for station_audit_record
-- ----------------------------
DROP TABLE IF EXISTS `station_audit_record`;
CREATE TABLE `station_audit_record` (
  `id` int(13) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `station_id` int(13) DEFAULT NULL COMMENT '站点ID',
  `audit_date` datetime DEFAULT NULL COMMENT '保养日期',
  `site_condition` text COLLATE utf8mb4_unicode_ci COMMENT '现场状况描述',
  `audition_log` text COLLATE utf8mb4_unicode_ci COMMENT '审查记录',
  `auditor_company` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '审查人单位',
  `auditor` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '审查人',
  `auditor_phone` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '审查人手机号',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `create_by` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新日期',
  `update_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='站点审查记录';

-- ----------------------------
-- Records of station_audit_record
-- ----------------------------
INSERT INTO `station_audit_record` VALUES ('1', '3', '2019-04-08 17:53:08', '合符条件', '标定通过', '广东省计量院', '张三', null, null, '2019-04-08 17:54:07', 'admin', '2019-04-08 17:54:27', 'admin');

-- ----------------------------
-- Table structure for station_maintenance_record
-- ----------------------------
DROP TABLE IF EXISTS `station_maintenance_record`;
CREATE TABLE `station_maintenance_record` (
  `id` int(13) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `station_id` int(13) DEFAULT NULL COMMENT '站点ID',
  `maintenance_date` datetime DEFAULT NULL COMMENT '保养日期',
  `site_condition` text COLLATE utf8mb4_unicode_ci COMMENT '现场状况描述',
  `operation_log` text COLLATE utf8mb4_unicode_ci COMMENT '操作记录',
  `operator` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '操作人',
  `operator_phone` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '操作人手机号',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `create_by` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新日期',
  `update_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='站点维护记录';

-- ----------------------------
-- Records of station_maintenance_record
-- ----------------------------
INSERT INTO `station_maintenance_record` VALUES ('1', '3', '2019-03-19 18:00:46', '设备正常', '日常维护', '李四', '13122422974', null, '2019-03-20 18:01:34', 'admin', null, null);
INSERT INTO `station_maintenance_record` VALUES ('2', '3', '2019-04-19 18:00:46', '设备正常', '日常维护', '李四', '13122422974', '', '2019-04-20 18:01:34', 'admin', '2019-04-19 18:02:20', '');

-- ----------------------------
-- Table structure for station_statistics
-- ----------------------------
DROP TABLE IF EXISTS `station_statistics`;
CREATE TABLE `station_statistics` (
  `id` int(19) NOT NULL AUTO_INCREMENT,
  `station_id` int(19) DEFAULT NULL,
  `date_time` datetime DEFAULT NULL,
  `year` int(10) DEFAULT NULL,
  `month` int(10) DEFAULT NULL,
  `day` int(10) DEFAULT NULL,
  `traffic_flow` int(19) DEFAULT NULL,
  `over_count` int(19) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2380 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='定时任务调度表';

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES ('1', 'ryTask', '系统默认（无参）', 'ryNoParams', '', '0/10 * * * * ?', '1', '1', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_job` VALUES ('2', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', '0/20 * * * * ?', '1', '1', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');

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
) ENGINE=InnoDB AUTO_INCREMENT=695 DEFAULT CHARSET=utf8 COMMENT='系统访问记录';


-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '请求地址',
  `menu_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2171 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 5, '#', 'M', '0', '', 'fa fa-gear', 'admin', '2018-03-16 11:33:00', 'admin', '2018-11-21 08:51:30', '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 7, '#', 'M', '0', '', 'fa fa-video-camera', 'admin', '2018-03-16 11:33:00', 'admin', '2018-11-26 21:32:12', '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 8, '#', 'M', '0', '', 'fa fa-bars', 'admin', '2018-03-16 11:33:00', 'admin', '2018-11-26 21:32:21', '系统工具目录');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, '/system/user', 'C', '0', 'system:user:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, '/system/role', 'C', '0', 'system:role:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, '/system/menu', 'C', '0', 'system:menu:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, '/system/dept', 'C', '0', 'system:dept:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, '/system/post', 'C', '0', 'system:post:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, '/system/dict', 'C', '0', 'system:dict:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, '/system/config', 'C', '0', 'system:config:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '参数设置菜单');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, '/system/notice', 'C', '0', 'system:notice:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知公告菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, '#', 'M', '0', '', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '日志管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, '/monitor/online', 'C', '0', 'monitor:online:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '在线用户菜单');
INSERT INTO `sys_menu` VALUES (110, '定时任务', 2, 2, '/monitor/job', 'C', '0', 'monitor:job:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '定时任务菜单');
INSERT INTO `sys_menu` VALUES (111, '数据监控', 2, 3, '/monitor/data', 'C', '0', 'monitor:data:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '数据监控菜单');
INSERT INTO `sys_menu` VALUES (112, '表单构建', 3, 1, '/tool/build', 'C', '0', 'tool:build:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '表单构建菜单');
INSERT INTO `sys_menu` VALUES (113, '代码生成', 3, 2, '/tool/gen', 'C', '0', 'tool:gen:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '代码生成菜单');
INSERT INTO `sys_menu` VALUES (114, '系统接口', 3, 3, '/tool/swagger', 'C', '0', 'tool:swagger:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统接口菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, '/monitor/operlog', 'C', '0', 'monitor:operlog:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, '/monitor/logininfor', 'C', '0', 'monitor:logininfor:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1000, '用户查询', 100, 1, '#', 'F', '0', 'system:user:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1001, '用户新增', 100, 2, '#', 'F', '0', 'system:user:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1002, '用户修改', 100, 3, '#', 'F', '0', 'system:user:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1003, '用户删除', 100, 4, '#', 'F', '0', 'system:user:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1004, '用户导出', 100, 5, '#', 'F', '0', 'system:user:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1005, '重置密码', 100, 5, '#', 'F', '0', 'system:user:resetPwd', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1006, '角色查询', 101, 1, '#', 'F', '0', 'system:role:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1007, '角色新增', 101, 2, '#', 'F', '0', 'system:role:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1008, '角色修改', 101, 3, '#', 'F', '0', 'system:role:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1009, '角色删除', 101, 4, '#', 'F', '0', 'system:role:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1010, '角色导出', 101, 4, '#', 'F', '0', 'system:role:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1011, '菜单查询', 102, 1, '#', 'F', '0', 'system:menu:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1012, '菜单新增', 102, 2, '#', 'F', '0', 'system:menu:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1013, '菜单修改', 102, 3, '#', 'F', '0', 'system:menu:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1014, '菜单删除', 102, 4, '#', 'F', '0', 'system:menu:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1015, '部门查询', 103, 1, '#', 'F', '0', 'system:dept:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1016, '部门新增', 103, 2, '#', 'F', '0', 'system:dept:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1017, '部门修改', 103, 3, '#', 'F', '0', 'system:dept:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1018, '部门删除', 103, 4, '#', 'F', '0', 'system:dept:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1019, '岗位查询', 104, 1, '#', 'F', '0', 'system:post:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1020, '岗位新增', 104, 2, '#', 'F', '0', 'system:post:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1021, '岗位修改', 104, 3, '#', 'F', '0', 'system:post:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1022, '岗位删除', 104, 4, '#', 'F', '0', 'system:post:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1023, '岗位导出', 104, 4, '#', 'F', '0', 'system:post:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1024, '字典查询', 105, 1, '#', 'F', '0', 'system:dict:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1025, '字典新增', 105, 2, '#', 'F', '0', 'system:dict:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1026, '字典修改', 105, 3, '#', 'F', '0', 'system:dict:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1027, '字典删除', 105, 4, '#', 'F', '0', 'system:dict:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1028, '字典导出', 105, 4, '#', 'F', '0', 'system:dict:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1029, '参数查询', 106, 1, '#', 'F', '0', 'system:config:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1030, '参数新增', 106, 2, '#', 'F', '0', 'system:config:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1031, '参数修改', 106, 3, '#', 'F', '0', 'system:config:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1032, '参数删除', 106, 4, '#', 'F', '0', 'system:config:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1033, '参数导出', 106, 4, '#', 'F', '0', 'system:config:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1034, '公告查询', 107, 1, '#', 'F', '0', 'system:notice:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1035, '公告新增', 107, 2, '#', 'F', '0', 'system:notice:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1036, '公告修改', 107, 3, '#', 'F', '0', 'system:notice:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1037, '公告删除', 107, 4, '#', 'F', '0', 'system:notice:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1038, '操作查询', 500, 1, '#', 'F', '0', 'monitor:operlog:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1039, '操作删除', 500, 2, '#', 'F', '0', 'monitor:operlog:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1040, '详细信息', 500, 3, '#', 'F', '0', 'monitor:operlog:detail', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1041, '日志导出', 500, 3, '#', 'F', '0', 'monitor:operlog:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1042, '登录查询', 501, 1, '#', 'F', '0', 'monitor:logininfor:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1043, '登录删除', 501, 2, '#', 'F', '0', 'monitor:logininfor:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1044, '日志导出', 501, 2, '#', 'F', '0', 'monitor:logininfor:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1045, '在线查询', 109, 1, '#', 'F', '0', 'monitor:online:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1046, '批量强退', 109, 2, '#', 'F', '0', 'monitor:online:batchForceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1047, '单条强退', 109, 3, '#', 'F', '0', 'monitor:online:forceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1048, '任务查询', 110, 1, '#', 'F', '0', 'monitor:job:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1049, '任务新增', 110, 2, '#', 'F', '0', 'monitor:job:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1050, '任务修改', 110, 3, '#', 'F', '0', 'monitor:job:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1051, '任务删除', 110, 4, '#', 'F', '0', 'monitor:job:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1052, '状态修改', 110, 5, '#', 'F', '0', 'monitor:job:changeStatus', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1053, '任务导出', 110, 5, '#', 'F', '0', 'monitor:job:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1054, '生成查询', 113, 1, '#', 'F', '0', 'tool:gen:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1055, '生成代码', 113, 2, '#', 'F', '0', 'tool:gen:code', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (2000, '站点管理', 0, 3, '#', 'M', '0', '', 'fa fa-exchange', 'admin', '2018-11-14 14:32:15', 'admin', '2019-04-13 17:06:29', '');
INSERT INTO `sys_menu` VALUES (2001, '站点管理', 2000, 2, '/module/stationInfo', 'C', '0', 'stationInfo:manager', '#', 'admin', '2018-11-14 14:34:20', 'admin', '2018-11-26 21:43:35', '');
INSERT INTO `sys_menu` VALUES (2002, '车辆信息管理', 0, 12, '#', 'M', '1', '', 'fa fa-automobile', 'admin', '2018-11-14 17:20:52', 'admin', '2019-04-13 17:06:53', '');
INSERT INTO `sys_menu` VALUES (2003, '车辆管理', 2002, 1, '/report/reportCar', 'C', '0', '', '#', 'admin', '2018-11-14 17:21:10', 'admin', '2018-12-01 18:18:49', '');
INSERT INTO `sys_menu` VALUES (2004, '报表中心', 0, 2, '#', 'M', '0', '', 'fa fa-dashboard', 'admin', '2018-11-14 17:22:18', 'admin', '2019-04-13 17:06:37', '');
INSERT INTO `sys_menu` VALUES (2006, '站点比较', 2004, 2, '/report', 'C', '0', '', 'fa fa-map-signs', 'admin', '2018-11-19 22:34:40', 'admin', '2019-04-13 17:42:15', '');
INSERT INTO `sys_menu` VALUES (2008, '通行记录管理', 0, 1, '#', 'M', '0', '', 'fa fa-road', 'admin', '2018-11-21 08:46:24', 'admin', '2018-11-21 08:50:19', '');
INSERT INTO `sys_menu` VALUES (2009, '实时通行记录', 2008, 1, '/duge/weightData', 'C', '0', '', '#', 'admin', '2018-11-21 08:47:15', 'admin', '2018-11-21 08:49:25', '');
INSERT INTO `sys_menu` VALUES (2010, '站点信息', 2000, 1, '/module/stationInfo/stationDashboard', 'C', '0', 'stationInfo:view', '#', 'admin', '2018-11-26 21:13:34', 'admin', '2018-11-26 21:45:13', '');
INSERT INTO `sys_menu` VALUES (2011, '我的工作', 0, 11, '#', 'M', '1', '', 'fa fa-gavel', 'admin', '2018-11-26 21:31:33', 'admin', '2019-04-08 15:38:53', '');
INSERT INTO `sys_menu` VALUES (2012, '罚单处理', 2011, 1, '#', 'C', '0', 'system:user:list', '#', 'admin', '2018-11-26 21:35:59', 'admin', '2018-11-26 21:37:17', '');
INSERT INTO `sys_menu` VALUES (2013, '违章查询', 2011, 2, '#', 'C', '0', 'system:user:list', '#', 'admin', '2018-11-26 21:36:24', 'admin', '2018-11-26 21:37:48', '');
INSERT INTO `sys_menu` VALUES (2014, '设备', 3, 1, '/module/deviceData', 'C', '0', 'module:deviceData:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '设备菜单');
INSERT INTO `sys_menu` VALUES (2015, '设备查询', 2014, 1, '#', 'F', '0', 'module:deviceData:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2016, '设备新增', 2014, 2, '#', 'F', '0', 'module:deviceData:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2017, '设备修改', 2014, 3, '#', 'F', '0', 'module:deviceData:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2018, '设备删除', 2014, 4, '#', 'F', '0', 'module:deviceData:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2084, '遥感监测平台', 0, 4, '#', 'M', '1', '', 'fa fa-dashboard', 'admin', '2019-04-08 15:37:48', 'admin', '2019-04-11 18:10:12', '');
INSERT INTO `sys_menu` VALUES (2085, '点位环境空气质量记录', 2084, 1, '/yaogan/airQualityRecord', 'C', '0', 'yaogan:airQualityRecord:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '点位环境空气质量记录菜单');
INSERT INTO `sys_menu` VALUES (2086, '点位环境空气质量记录查询', 2085, 1, '#', 'F', '0', 'yaogan:airQualityRecord:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2087, '点位环境空气质量记录新增', 2085, 2, '#', 'F', '0', 'yaogan:airQualityRecord:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2088, '点位环境空气质量记录修改', 2085, 3, '#', 'F', '0', 'yaogan:airQualityRecord:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2089, '点位环境空气质量记录删除', 2085, 4, '#', 'F', '0', 'yaogan:airQualityRecord:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2090, '遥测设备检查', 2084, 1, '/yaogan/deviceCheckLog', 'C', '0', 'yaogan:deviceCheckLog:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '遥测设备检查菜单');
INSERT INTO `sys_menu` VALUES (2091, '遥测设备检查查询', 2090, 1, '#', 'F', '0', 'yaogan:deviceCheckLog:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2092, '遥测设备检查新增', 2090, 2, '#', 'F', '0', 'yaogan:deviceCheckLog:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2093, '遥测设备检查修改', 2090, 3, '#', 'F', '0', 'yaogan:deviceCheckLog:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2094, '遥测设备检查删除', 2090, 4, '#', 'F', '0', 'yaogan:deviceCheckLog:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2095, '遥测设备动态检查过程数据', 2084, 1, '/yaogan/deviceDynamicCheck', 'C', '0', 'yaogan:deviceDynamicCheck:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '遥测设备动态检查过程数据菜单');
INSERT INTO `sys_menu` VALUES (2096, '遥测设备动态检查过程数据查询', 2095, 1, '#', 'F', '0', 'yaogan:deviceDynamicCheck:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2097, '遥测设备动态检查过程数据新增', 2095, 2, '#', 'F', '0', 'yaogan:deviceDynamicCheck:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2098, '遥测设备动态检查过程数据修改', 2095, 3, '#', 'F', '0', 'yaogan:deviceDynamicCheck:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2099, '遥测设备动态检查过程数据删除', 2095, 4, '#', 'F', '0', 'yaogan:deviceDynamicCheck:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2100, '遥测设备自检过程数据', 2084, 1, '/yaogan/deviceSelfCheck', 'C', '0', 'yaogan:deviceSelfCheck:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '遥测设备自检过程数据菜单');
INSERT INTO `sys_menu` VALUES (2101, '遥测设备自检过程数据查询', 2100, 1, '#', 'F', '0', 'yaogan:deviceSelfCheck:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2102, '遥测设备自检过程数据新增', 2100, 2, '#', 'F', '0', 'yaogan:deviceSelfCheck:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2103, '遥测设备自检过程数据修改', 2100, 3, '#', 'F', '0', 'yaogan:deviceSelfCheck:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2104, '遥测设备自检过程数据删除', 2100, 4, '#', 'F', '0', 'yaogan:deviceSelfCheck:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2105, '遥测设备静态检查过程数据', 2084, 1, '/yaogan/deviceStaticCheck', 'C', '0', 'yaogan:deviceStaticCheck:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '遥测设备静态检查过程数据菜单');
INSERT INTO `sys_menu` VALUES (2106, '遥测设备静态检查过程数据查询', 2105, 1, '#', 'F', '0', 'yaogan:deviceStaticCheck:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2107, '遥测设备静态检查过程数据新增', 2105, 2, '#', 'F', '0', 'yaogan:deviceStaticCheck:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2108, '遥测设备静态检查过程数据修改', 2105, 3, '#', 'F', '0', 'yaogan:deviceStaticCheck:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2109, '遥测设备静态检查过程数据删除', 2105, 4, '#', 'F', '0', 'yaogan:deviceStaticCheck:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2110, '点位遥测线', 2084, 1, '/yaogan/line', 'C', '0', 'yaogan:line:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '点位遥测线菜单');
INSERT INTO `sys_menu` VALUES (2111, '点位遥测线查询', 2110, 1, '#', 'F', '0', 'yaogan:line:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2112, '点位遥测线新增', 2110, 2, '#', 'F', '0', 'yaogan:line:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2113, '点位遥测线修改', 2110, 3, '#', 'F', '0', 'yaogan:line:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2114, '点位遥测线删除', 2110, 4, '#', 'F', '0', 'yaogan:line:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2115, '移动式点位运行记录', 2084, 1, '/yaogan/mobileStation', 'C', '0', 'yaogan:mobileStation:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '移动式点位运行记录菜单');
INSERT INTO `sys_menu` VALUES (2116, '移动式点位运行记录查询', 2115, 1, '#', 'F', '0', 'yaogan:mobileStation:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2117, '移动式点位运行记录新增', 2115, 2, '#', 'F', '0', 'yaogan:mobileStation:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2118, '移动式点位运行记录修改', 2115, 3, '#', 'F', '0', 'yaogan:mobileStation:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2119, '移动式点位运行记录删除', 2115, 4, '#', 'F', '0', 'yaogan:mobileStation:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2120, '遥感监测数据', 2084, 1, '/yaogan/monitorDataLog', 'C', '0', 'yaogan:monitorDataLog:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '遥感监测数据菜单');
INSERT INTO `sys_menu` VALUES (2121, '遥感监测数据查询', 2120, 1, '#', 'F', '0', 'yaogan:monitorDataLog:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2122, '遥感监测数据新增', 2120, 2, '#', 'F', '0', 'yaogan:monitorDataLog:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2123, '遥感监测数据修改', 2120, 3, '#', 'F', '0', 'yaogan:monitorDataLog:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2124, '遥感监测数据删除', 2120, 4, '#', 'F', '0', 'yaogan:monitorDataLog:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2125, '遥测设备自检', 2084, 1, '/yaogan/stationCheckLog', 'C', '0', 'yaogan:stationCheckLog:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '遥测设备自检菜单');
INSERT INTO `sys_menu` VALUES (2126, '遥测设备自检查询', 2125, 1, '#', 'F', '0', 'yaogan:stationCheckLog:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2127, '遥测设备自检新增', 2125, 2, '#', 'F', '0', 'yaogan:stationCheckLog:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2128, '遥测设备自检修改', 2125, 3, '#', 'F', '0', 'yaogan:stationCheckLog:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2129, '遥测设备自检删除', 2125, 4, '#', 'F', '0', 'yaogan:stationCheckLog:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2130, '点位', 2084, 1, '/yaogan/station', 'C', '0', 'yaogan:station:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '点位菜单');
INSERT INTO `sys_menu` VALUES (2131, '点位查询', 2130, 1, '#', 'F', '0', 'yaogan:station:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2132, '点位新增', 2130, 2, '#', 'F', '0', 'yaogan:station:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2133, '点位修改', 2130, 3, '#', 'F', '0', 'yaogan:station:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2134, '点位删除', 2130, 4, '#', 'F', '0', 'yaogan:station:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2135, '交通流量', 2084, 1, '/yaogan/trafficFlow', 'C', '0', 'yaogan:trafficFlow:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '交通流量菜单');
INSERT INTO `sys_menu` VALUES (2136, '交通流量查询', 2135, 1, '#', 'F', '0', 'yaogan:trafficFlow:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2137, '交通流量新增', 2135, 2, '#', 'F', '0', 'yaogan:trafficFlow:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2138, '交通流量修改', 2135, 3, '#', 'F', '0', 'yaogan:trafficFlow:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2139, '交通流量删除', 2135, 4, '#', 'F', '0', 'yaogan:trafficFlow:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2140, '车辆数据', 2084, 1, '/yaogan/vehicleInfo', 'C', '0', 'yaogan:vehicleInfo:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '车辆数据菜单');
INSERT INTO `sys_menu` VALUES (2141, '车辆数据查询', 2140, 1, '#', 'F', '0', 'yaogan:vehicleInfo:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2142, '车辆数据新增', 2140, 2, '#', 'F', '0', 'yaogan:vehicleInfo:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2143, '车辆数据修改', 2140, 3, '#', 'F', '0', 'yaogan:vehicleInfo:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2144, '车辆数据删除', 2140, 4, '#', 'F', '0', 'yaogan:vehicleInfo:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2145, '机动车轨迹', 2084, 1, '/yaogan/vehicleTrajectory', 'C', '0', 'yaogan:vehicleTrajectory:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '机动车轨迹菜单');
INSERT INTO `sys_menu` VALUES (2146, '机动车轨迹查询', 2145, 1, '#', 'F', '0', 'yaogan:vehicleTrajectory:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2147, '机动车轨迹新增', 2145, 2, '#', 'F', '0', 'yaogan:vehicleTrajectory:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2148, '机动车轨迹修改', 2145, 3, '#', 'F', '0', 'yaogan:vehicleTrajectory:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2149, '机动车轨迹删除', 2145, 4, '#', 'F', '0', 'yaogan:vehicleTrajectory:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2150, '违章记录', 2008, 2, '/duge/weightData/over', 'C', '0', '', '#', 'admin', '2019-04-12 14:27:25', 'admin', '2019-04-12 14:29:22', '');
INSERT INTO `sys_menu` VALUES (2151, '站点汇总', 2004, 1, '/report/total', 'C', '0', '', '#', 'admin', '2019-04-13 17:43:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2152, '站点明细', 2004, 3, '/report/single', 'C', '0', '', '#', 'admin', '2019-04-13 17:43:43', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2153, '站点审查记录', 2000, 3, '/station/auditRecord', 'C', '0', 'station:auditRecord:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '站点审查记录菜单');
INSERT INTO `sys_menu` VALUES (2154, '站点审查记录查询', 2153, 1, '#', 'F', '0', 'station:auditRecord:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2155, '站点审查记录新增', 2153, 2, '#', 'F', '0', 'station:auditRecord:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2156, '站点审查记录修改', 2153, 3, '#', 'F', '0', 'station:auditRecord:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2157, '站点审查记录删除', 2153, 4, '#', 'F', '0', 'station:auditRecord:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2158, '站点维护记录', 2000, 4, '/station/maintenanceRecord', 'C', '0', 'station:maintenanceRecord:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '站点维护记录菜单');
INSERT INTO `sys_menu` VALUES (2159, '站点维护记录查询', 2158, 1, '#', 'F', '0', 'station:maintenanceRecord:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2160, '站点维护记录新增', 2158, 2, '#', 'F', '0', 'station:maintenanceRecord:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2161, '站点维护记录修改', 2158, 3, '#', 'F', '0', 'station:maintenanceRecord:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2162, '站点维护记录删除', 2158, 4, '#', 'F', '0', 'station:maintenanceRecord:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2163, '实时监控', 2008, 3, '/duge/weightData/realtime', 'C', '0', '', '#', 'admin', '2019-06-13 15:56:22', 'admin', '2019-06-13 17:22:49', '');
INSERT INTO `sys_menu` VALUES (2164, '交通流量', 0, 8, '#', 'M', '0', '', 'fa fa-map-marker', 'admin', '2019-07-02 15:20:49', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2165, '概况', 2164, 1, '/tic/main', 'C', '0', '', '#', 'admin', '2019-07-02 15:21:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2167, '站点', 2164, 2, '/tic/station', 'C', '0', '', '#', 'admin', '2019-07-02 15:22:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2168, '维护记录', 2164, 3, '/tic/record', 'C', '0', '', '#', 'admin', '2019-07-02 15:23:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2170, '站点分类', 2000, 3, '/module/stationType', 'C', '0', '', '#', 'admin', '2019-11-04 14:59:58', '', NULL, '');

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
INSERT INTO `sys_user` VALUES ('1', '103', 'admin', '管理员', '00', '***@qq.com', '15888888888', '1', 'fbcef9d424acd462e9cd9e3fd50662ae.jpg', 'b640f9d2330eedc994e85434b1b607d0', 'c86cbd', '0', '0', '19.200.242.36', '2019-07-29 17:21:55', 'admin', '2018-03-16 11:33:00', 'ry', '2019-07-29 17:21:54', '管理员');
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
-- Table structure for tic_station
-- ----------------------------
DROP TABLE IF EXISTS `tic_station`;
CREATE TABLE `tic_station` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '站点id',
  `name` varchar(128) DEFAULT NULL COMMENT '站点名称',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `longitude_WGS84` varchar(30) DEFAULT NULL COMMENT '经度(WGS84坐标系)',
  `latitude_WGS84` varchar(30) DEFAULT NULL COMMENT '纬度(WGS84坐标系)',
  `longitude_GCJ02` varchar(30) DEFAULT NULL COMMENT '经度(GCJ02坐标系)',
  `latitude_GCJ02` varchar(30) DEFAULT NULL COMMENT '纬度(GCJ02坐标系)',
  `longitude_BD09` varchar(30) DEFAULT NULL COMMENT '经度(BD09坐标系)',
  `latitude_BD09` varchar(30) DEFAULT NULL COMMENT '纬度(BD09坐标系)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tic_station
-- ----------------------------
INSERT INTO `tic_station` VALUES ('1', '五沙大桥东侧', 'X501  K5＋900顺德区五沙大桥西侧上桥位（五沙方向）', '113.3282575600', '22.8109135700', '113.3336510000', '22.8081800000', '113.3402110884', '22.8139434571');
INSERT INTO `tic_station` VALUES ('2', '五沙大桥西侧', 'X501  K8＋100左顺德区五沙大桥东侧上桥位（往大良方向）', '113.3418746300', '22.8185526000', '113.3473090000', '22.8158600000', '113.3538941319', '22.8215446312');
INSERT INTO `tic_station` VALUES ('3', '三善大桥顺德方向（下桥位）', 'G325  K14＋500顺德区三善大桥下桥位附近（往顺德方向）', '113.2808478400', '22.8855975700', '113.2861830000', '22.8828340000', '113.2925990518', '22.8891588412');
INSERT INTO `tic_station` VALUES ('4', '德胜大桥（上桥位）', 'X520  K6顺德区德胜大桥上桥位旧收费站附近（往容桂方向）', '113.2997091300', '22.8049471900', '113.3050660000', '22.8021770000', '113.3115476422', '22.8082631319');
INSERT INTO `tic_station` VALUES ('5', '德胜大桥南侧（下桥位）', 'X520  K6顺德区德胜大桥上桥位旧收费站附近（往容桂方向）', '113.2995294000', '22.8048919500', '113.3048740000', '22.8021110000', '113.3113203215', '22.8083471618');
INSERT INTO `tic_station` VALUES ('6', '三洪奇大桥南（下桥位）', 'G105  K2954＋500顺德区三洪奇大桥南下桥位附近（往伦教方向）', '113.2071029900', '22.8971377700', '113.2124540000', '22.8944000000', '113.2189867613', '22.9001573624');
INSERT INTO `tic_station` VALUES ('7', '三善大桥广州方向（上桥位）', 'G325  K14＋500顺德区三善大桥上桥位附近（往广州方向）', '113.2809018400', '22.8855555700', '113.2862370000', '22.8827920000', '113.2926530518', '22.8891168412');
INSERT INTO `tic_station` VALUES ('8', '乐龙大桥南侧', '乐龙路乐龙大桥南侧上桥位附近（往乐从方向）', '113.0942771500', '22.8864718300', '113.0997530000', '22.8838660000', '113.1063098779', '22.8895422900');
INSERT INTO `tic_station` VALUES ('9', '乐龙大桥北侧', '乐龙路乐龙大桥北侧上桥位附近（往龙江方向）', '113.0977189500', '22.8912152500', '113.1031970000', '22.8886150000', '113.1097485908', '22.8942889788');
INSERT INTO `tic_station` VALUES ('10', '安利大桥北侧', '伦桂路安利特大桥北侧上桥位附近（往容桂方向）', '113.2257166156', '22.8074851723', '113.2310297956', '22.8046827223', '113.2375850000', '22.8104520000');
INSERT INTO `tic_station` VALUES ('11', '安利大桥南侧', '伦桂路安利特大桥南侧上桥位附近（往勒流方向）', '113.2296795337', '22.8003490115', '113.2349908937', '22.7975420415', '113.2415370000', '22.8033100000');
INSERT INTO `tic_station` VALUES ('12', '三洪奇大桥南（上桥位）', 'G105  K2954＋500顺德区三洪奇大桥南上桥位附近（往北滘方向）', '113.2073119900', '22.8971037700', '113.2126630000', '22.8943660000', '113.2191957613', '22.9001233624');

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
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
  `upload_yhl` tinyint(3) DEFAULT '0',
  `upload_sj` tinyint(3) DEFAULT '0',
  `upload_jj` tinyint(3) DEFAULT '0',
  `mark_del` int(2) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `index_station` (`station_id`) USING BTREE,
  KEY `index_yhl_tag` (`upload_yhl`) USING BTREE,
  KEY `index_sj_tag` (`upload_sj`) USING BTREE,
  KEY `index_plate` (`truck_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2356860 DEFAULT CHARSET=utf8 COMMENT='重量数据';


CREATE TABLE `weight_data_before_40days` (
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
  `upload_yhl` tinyint(3) DEFAULT '0',
  `upload_sj` tinyint(3) DEFAULT '0',
  `upload_jj` tinyint(3) DEFAULT '0',
  `mark_del` int(2) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_station` (`station_id`) USING BTREE,
  KEY `index_yhl_tag` (`upload_yhl`) USING BTREE,
  KEY `index_sj_tag` (`upload_sj`) USING BTREE,
  KEY `index_plate` (`truck_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2267767 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='重量数据';

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
-- Table structure for refitted_vehicle_info
-- ----------------------------
DROP TABLE IF EXISTS `refitted_vehicle_info`;
CREATE TABLE `refitted_vehicle_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `vehicle_length` int(11) DEFAULT NULL COMMENT '车辆长度',
  `vehicle_width` int(11) DEFAULT NULL COMMENT '车辆宽度',
  `vehicle_height` int(11) DEFAULT NULL COMMENT '车辆高度',
  `vehicle_rail_height` int(11) DEFAULT NULL COMMENT '车辆栏板高度',
  `original_vehicle_rail_height` int(11) DEFAULT NULL COMMENT '原车辆栏板高度',
  `lane_number` int(11) DEFAULT NULL COMMENT '车道号',
  `truck_number` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '号牌号码',
  `truck_color` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '号牌颜色',
  `time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '时间',
  `scene_picture_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '场景图片名称（年月日+时分秒+号牌号码+scene.jpg）',
  `plate_picture_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '车牌图片名称（年月日+时分秒+号牌号码+plate.jpg）',
  `graphic_model_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '三维图名称（年月日+时分秒+号牌号码+left.png）',
  `limit_over_tag` int(3) DEFAULT NULL COMMENT '超限标记（0正常 1超限）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `refitted_vehicle_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `vehicle_length` int(11) DEFAULT NULL COMMENT '车辆长度',
  `vehicle_width` int(11) DEFAULT NULL COMMENT '车辆宽度',
  `vehicle_height` int(11) DEFAULT NULL COMMENT '车辆高度',
  `vehicle_rail_height` int(11) DEFAULT NULL COMMENT '车辆栏板高度',
  `original_vehicle_rail_height` int(11) DEFAULT NULL COMMENT '原车辆栏板高度',
  `lane_number` int(11) DEFAULT NULL COMMENT '车道号',
  `truck_number` varchar(32) DEFAULT NULL COMMENT '号牌号码',
  `truck_color` varchar(12) DEFAULT NULL COMMENT '号牌颜色',
  `time` varchar(50) DEFAULT NULL COMMENT '时间',
  `scene_picture_name` varchar(100) DEFAULT NULL COMMENT '场景图片名称（年月日+时分秒+号牌号码+scene.jpg）',
  `plate_picture_name` varchar(100) DEFAULT NULL COMMENT '车牌图片名称（年月日+时分秒+号牌号码+plate.jpg）',
  `graphic_model_name` varchar(100) DEFAULT NULL COMMENT '三维图名称（年月日+时分秒+号牌号码+left.png）',
  `limit_over_tag` int(3) DEFAULT NULL COMMENT '超限标记（0正常 1超限）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;