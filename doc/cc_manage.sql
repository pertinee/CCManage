/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : cc_manage

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-02-09 21:20:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('CCScheduler', 'TASK_1', 'DEFAULT', '*/5 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('CCScheduler', 'TASK_2', 'DEFAULT', '0 0/30 * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`) USING BTREE,
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`) USING BTREE,
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`) USING BTREE,
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('CCScheduler', 'TASK_1', 'DEFAULT', null, 'com.lcz.manage.util.schedule.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597400AA7B226265616E4E616D65223A22746573745461736B222C2263726561746554696D65223A313438303630353430363030302C2263726F6E45787072657373696F6E223A223020302F3330202A202A202A203F222C226A6F624964223A312C226D6574686F644E616D65223A2274657374222C22706172616D73223A2272656E72656E222C2272656D61726B223A22E69C89E58F82E695B0E6B58BE8AF95222C22737461747573223A307D7800);
INSERT INTO `qrtz_job_details` VALUES ('CCScheduler', 'TASK_2', 'DEFAULT', null, 'com.lcz.manage.util.schedule.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597400997B226265616E4E616D65223A22746573745461736B222C2263726561746554696D65223A313438303734383135363030302C2263726F6E45787072657373696F6E223A223020302F3330202A202A202A203F222C226A6F624964223A322C226D6574686F644E616D65223A227465737432222C2272656D61726B223A22E697A0E58F82E695B0E6B58BE8AF95222C22737461747573223A317D7800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('CCScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('CCScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('CCScheduler', 'LCZ-PC1518181667146', '1518182439398', '15000');

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`) USING BTREE,
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('CCScheduler', 'TASK_1', 'DEFAULT', 'TASK_1', 'DEFAULT', null, '1514295125000', '-1', '5', 'PAUSED', 'CRON', '1514295123000', '0', null, '2', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597400A97B226265616E4E616D65223A22746573745461736B222C2263726561746554696D65223A313438303630353430363030302C2263726F6E45787072657373696F6E223A222A2F35202A202A202A202A203F222C226A6F624964223A312C226D6574686F644E616D65223A2274657374222C22706172616D73223A2272656E72656E222C2272656D61726B223A22E69C89E58F82E695B0E6B58BE8AF95222C22737461747573223A317D7800);
INSERT INTO `qrtz_triggers` VALUES ('CCScheduler', 'TASK_2', 'DEFAULT', 'TASK_2', 'DEFAULT', null, '1514296800000', '-1', '5', 'PAUSED', 'CRON', '1514295123000', '0', null, '2', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597400997B226265616E4E616D65223A22746573745461736B222C2263726561746554696D65223A313438303734383135363030302C2263726F6E45787072657373696F6E223A223020302F3330202A202A202A203F222C226A6F624964223A322C226D6574686F644E616D65223A227465737432222C2272656D61726B223A22E697A0E58F82E695B0E6B58BE8AF95222C22737461747573223A317D7800);

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) DEFAULT NULL COMMENT '任务状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='定时任务';

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES ('1', 'testTask', 'test', 'cc', '*/5 * * * * ?', '1', '有参数测试', '2016-12-01 23:16:46');
INSERT INTO `schedule_job` VALUES ('2', 'testTask', 'test2', null, '0 0/30 * * * ?', '1', '无参数测试', '2016-12-03 14:55:56');

-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`),
  KEY `job_id` (`job_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------
INSERT INTO `schedule_job_log` VALUES ('26', '1', 'testTask', 'test', 'cc', '0', null, '1001', '2018-01-22 11:00:40');
INSERT INTO `schedule_job_log` VALUES ('27', '1', 'testTask', 'test', 'cc', '0', null, '1002', '2018-01-22 11:00:45');
INSERT INTO `schedule_job_log` VALUES ('28', '1', 'testTask', 'test', 'cc', '0', null, '1001', '2018-01-22 11:00:50');
INSERT INTO `schedule_job_log` VALUES ('29', '1', 'testTask', 'test', 'cc', '0', null, '1003', '2018-01-22 11:00:55');
INSERT INTO `schedule_job_log` VALUES ('30', '1', 'testTask', 'test', 'cc', '1', 'org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named \'testTask\' available', '5', '2018-01-28 13:52:15');
INSERT INTO `schedule_job_log` VALUES ('31', '1', 'testTask', 'test', 'cc', '1', 'org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named \'testTask\' available', '6', '2018-01-28 13:55:24');
INSERT INTO `schedule_job_log` VALUES ('32', '2', 'testTask', 'test2', null, '1', 'org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named \'testTask\' available', '1', '2018-01-28 13:55:36');
INSERT INTO `schedule_job_log` VALUES ('33', '1', 'testTask', 'test', 'cc', '0', null, '3902', '2018-01-28 13:56:59');
INSERT INTO `schedule_job_log` VALUES ('34', '1', 'testTask', 'test', 'cc', '0', null, '1002', '2018-01-28 13:58:42');
INSERT INTO `schedule_job_log` VALUES ('35', '2', 'testTask', 'test2', null, '0', null, '0', '2018-01-28 13:59:10');
INSERT INTO `schedule_job_log` VALUES ('36', '1', 'testTask', 'test', 'cc', '0', null, '1001', '2018-01-28 13:59:10');
INSERT INTO `schedule_job_log` VALUES ('37', '1', 'testTask', 'test', 'cc', '0', null, '5761', '2018-01-28 14:00:35');
INSERT INTO `schedule_job_log` VALUES ('38', '1', 'testTask', 'test', 'cc', '0', null, '1002', '2018-01-28 14:00:40');
INSERT INTO `schedule_job_log` VALUES ('39', '1', 'testTask', 'test', 'cc', '0', null, '1002', '2018-01-28 14:00:45');
INSERT INTO `schedule_job_log` VALUES ('40', '1', 'testTask', 'test', 'cc', '0', null, '1001', '2018-01-28 14:00:50');
INSERT INTO `schedule_job_log` VALUES ('41', '1', 'testTask', 'test', 'cc', '0', null, '1050', '2018-02-01 20:49:15');
INSERT INTO `schedule_job_log` VALUES ('42', '1', 'testTask', 'test', 'cc', '0', null, '1014', '2018-02-01 20:49:20');
INSERT INTO `schedule_job_log` VALUES ('43', '1', 'testTask', 'test', 'cc', '0', null, '1013', '2018-02-01 20:49:25');
INSERT INTO `schedule_job_log` VALUES ('44', '1', 'testTask', 'test', 'cc', '0', null, '1003', '2018-02-01 20:49:30');
INSERT INTO `schedule_job_log` VALUES ('45', '1', 'testTask', 'test', 'cc', '0', null, '1120', '2018-02-01 20:49:35');
INSERT INTO `schedule_job_log` VALUES ('46', '1', 'testTask', 'test', 'cc', '0', null, '1015', '2018-02-01 20:49:40');
INSERT INTO `schedule_job_log` VALUES ('47', '1', 'testTask', 'test', 'cc', '0', null, '1003', '2018-02-01 20:49:45');
INSERT INTO `schedule_job_log` VALUES ('48', '1', 'testTask', 'test', 'cc', '0', null, '1003', '2018-02-01 20:49:50');
INSERT INTO `schedule_job_log` VALUES ('49', '1', 'testTask', 'test', 'cc', '0', null, '1002', '2018-02-01 20:49:55');
INSERT INTO `schedule_job_log` VALUES ('50', '1', 'testTask', 'test', 'cc', '0', null, '1002', '2018-02-01 20:50:00');
INSERT INTO `schedule_job_log` VALUES ('51', '1', 'testTask', 'test', 'cc', '0', null, '1029', '2018-02-01 20:50:05');
INSERT INTO `schedule_job_log` VALUES ('52', '1', 'testTask', 'test', 'cc', '0', null, '1003', '2018-02-01 20:50:10');
INSERT INTO `schedule_job_log` VALUES ('53', '1', 'testTask', 'test', 'cc', '0', null, '1003', '2018-02-01 20:50:15');
INSERT INTO `schedule_job_log` VALUES ('54', '1', 'testTask', 'test', 'cc', '0', null, '1003', '2018-02-01 20:50:20');
INSERT INTO `schedule_job_log` VALUES ('55', '1', 'testTask', 'test', 'cc', '0', null, '1011', '2018-02-01 20:50:25');
INSERT INTO `schedule_job_log` VALUES ('56', '1', 'testTask', 'test', 'cc', '0', null, '1013', '2018-02-01 20:53:04');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` varchar(255) NOT NULL,
  `key` varchar(50) DEFAULT NULL COMMENT 'key',
  `value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key` (`key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置信息表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', 'CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudRegion\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"xwmlqT9V1GuzFMRj2i920lmLR8Dj0gNohDS_nkrS\",\"qiniuBucketName\":\"common-zone\",\"qiniuDomain\":\"http://p20dkvms1.bkt.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"eZUIG2UueNgIPd3aNtzdycr1Xjk7Umw2HmOFr-XS\",\"type\":1}', '1', '云存储配置信息');
INSERT INTO `sys_config` VALUES ('2', 'LOST_WISDOM', '{\"id\":\"1\",\"name\":\"lost_wisdom\",\"remark\":\"失了智\"}	', '0', '失了智');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` varchar(255) NOT NULL,
  `parent_id` varchar(255) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '0', 'CC有限公司', '0', '0');
INSERT INTO `sys_dept` VALUES ('2', '1', 'G分公司', '1', '0');
INSERT INTO `sys_dept` VALUES ('3', '1', 'H分公司', '2', '0');
INSERT INTO `sys_dept` VALUES ('4', '3', '技术部', '0', '0');
INSERT INTO `sys_dept` VALUES ('5', '3', '销售部', '1', '0');
INSERT INTO `sys_dept` VALUES ('6', '2', '测试部', '1', '0');
INSERT INTO `sys_dept` VALUES ('75e9c399-325b-4f04-b648-24d968af3220', null, '市场部', '1', '-1');
INSERT INTO `sys_dept` VALUES ('a3d71c3c-a2c0-42de-a459-cda3e552e5e0', '2', '市场部', '2', '0');
INSERT INTO `sys_dept` VALUES ('a5f36eb2-ea7d-466f-aded-66613bb49006', null, '市场', '2', '-1');
INSERT INTO `sys_dept` VALUES ('d635fa28-a031-4f0a-9420-aa42d056d062', '6', '测试-前端', '1', '-1');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` varchar(255) NOT NULL,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('000ad69c-4dc7-44c6-90ed-0097702c6cb6', 'admin', '保存菜单', 'com.lcz.manage.sys.controller.SysMenuController.save()', '{\"icon\":\"\",\"menuId\":\"\",\"name\":\"c1\",\"orderNum\":0,\"parentId\":\"2874aee5-5420-4bc3-b8e0-e9fde18ae255\",\"parentName\":\"cc\",\"perms\":\"c1:list\",\"type\":1,\"url\":\"/sys/c1\"}', '127.0.0.1', '2018-01-09 13:31:15');
INSERT INTO `sys_log` VALUES ('02d40550-2447-4a45-a46c-b8f9f69083eb', 'admin', '保存菜单', 'com.lcz.manage.base.controller.SysMenuController.save()', '{\"icon\":\"fa fa-comment\",\"menuId\":\"\",\"name\":\"关于我\",\"orderNum\":1,\"parentId\":\"69f0cfdc-11fd-43d2-b699-06fc7ab76a36\",\"parentName\":\"关于\",\"perms\":\"\",\"type\":1,\"url\":\"/sys/about\"}', '127.0.0.1', '2017-12-31 11:08:54');
INSERT INTO `sys_log` VALUES ('0328361a-507a-4f66-a068-b96cfb1cff3b', 'admin', '删除菜单', 'com.lcz.manage.base.controller.SysMenuController.delete()', '\"9\"', '127.0.0.1', '2018-01-02 14:31:51');
INSERT INTO `sys_log` VALUES ('03a4dfc4-cc46-4589-a4de-704e8cfc89f3', 'admin', '删除菜单', 'com.lcz.manage.sys.controller.SysMenuController.delete()', '\"f3c0c085-0701-484a-b96f-4c3d963729b1\"', '127.0.0.1', '2018-02-07 09:46:06');
INSERT INTO `sys_log` VALUES ('03a53b11-0ec8-45a8-a450-bfb92d835382', 'admin', '保存定时任务', 'com.lcz.manage.sys.controller.ScheduleJobController.save()', '{\"beanName\":\"测试\",\"cronExpression\":\"0 0/1 * * * ? *\",\"methodName\":\"test_lcz\",\"params\":\"lcz\",\"remark\":\"每分钟执行\"}', '127.0.0.1', '2018-01-22 10:12:35');
INSERT INTO `sys_log` VALUES ('067a5276-571f-43da-8f2e-6ffee6a8290a', 'admin', '修改角色', 'com.lcz.manage.sys.controller.SysRoleController.update()', '{\"menuIdList\":[\"7\",\"5\",\"6\"],\"remark\":\"查看系统日志、SQL监控。\",\"roleId\":\"d15422bd-780b-45dc-a67e-57c804cb35f1\",\"roleName\":\"系统管理员\"}', '127.0.0.1', '2018-01-09 11:05:54');
INSERT INTO `sys_log` VALUES ('068b31ec-ae0f-4288-8d97-55d76166b1c6', 'admin', '修改菜单', 'com.lcz.manage.sys.controller.SysMenuController.update()', '{\"icon\":\"\",\"menuId\":\"0571031d-16f7-4dfe-a158-a9f09ecfc4a8\",\"name\":\"c1-1\",\"orderNum\":0,\"parentId\":\"2874aee5-5420-4bc3-b8e0-e9fde18ae255\",\"parentName\":\"cc\",\"perms\":\"c1:list\",\"type\":1,\"url\":\"/sys/c1\"}', '127.0.0.1', '2018-01-09 13:31:27');
INSERT INTO `sys_log` VALUES ('07cd757e-5a8d-4cbf-b9da-54e5cd581cfc', 'admin', '修改角色', 'com.lcz.manage.base.controller.SysRoleController.update()', '{\"menuIdList\":[\"1\",\"2\",\"21\",\"22\",\"23\",\"24\",\"3\",\"31\",\"32\",\"33\",\"34\",\"4\",\"41\",\"42\",\"43\",\"44\",\"5\",\"6\"],\"remark\":\"此管理员拥有最高权限\",\"roleId\":\"1\",\"roleName\":\"超级管理员\"}', '127.0.0.1', '2017-12-30 16:01:20');
INSERT INTO `sys_log` VALUES ('09591dc4-9a54-4677-847e-9dba6bd8e74b', 'admin', '暂停定时任务', 'com.lcz.manage.sys.controller.ScheduleJobController.pause()', '[1]', '127.0.0.1', '2018-02-01 20:53:27');
INSERT INTO `sys_log` VALUES ('0a82aa25-772e-465f-964c-668de52b40a7', 'admin', '修改角色', 'com.lcz.manage.base.controller.SysRoleController.update()', '{\"menuIdList\":[\"1\",\"2\",\"21\",\"3\",\"31\",\"4\",\"41\",\"5\",\"6\"],\"remark\":\"只拥有查看权限\",\"roleId\":\"2\",\"roleName\":\"普通用户\"}', '127.0.0.1', '2017-12-30 16:02:49');
INSERT INTO `sys_log` VALUES ('0b221c6e-f148-4881-b0d6-eda8983396fb', 'admin', '修改菜单', 'com.lcz.manage.sys.controller.SysMenuController.update()', '{\"icon\":\"\",\"menuId\":\"2874aee5-5420-4bc3-b8e0-e9fde18ae255\",\"name\":\"cc\",\"orderNum\":2,\"parentId\":\"0\",\"parentName\":\"一级菜单\",\"perms\":\"\",\"type\":0,\"url\":\"\"}', '127.0.0.1', '2018-01-09 13:30:29');
INSERT INTO `sys_log` VALUES ('0b3d9b5c-c842-48a4-8e80-1a6c533b7fb4', 'admin', '修改角色', 'com.lcz.manage.base.controller.SysRoleController.update()', '{\"menuIdList\":[\"1\",\"2\",\"15\",\"16\",\"17\",\"18\"],\"remark\":\"只拥有管理员列表管理的权限\",\"roleId\":\"3\",\"roleName\":\"用户管理员\"}', '127.0.0.1', '2017-12-29 15:04:38');
INSERT INTO `sys_log` VALUES ('0c2d89b1-ab31-41d9-b917-3c1257e11fab', 'admin', '保存配置', 'com.lcz.manage.sys.controller.SysConfigController.save()', '{\"id\":\"\",\"key\":\"sex\",\"remark\":\"男\",\"status\":1,\"value\":\"1\"}', '127.0.0.1', '2018-02-07 09:44:50');
INSERT INTO `sys_log` VALUES ('0eaa8f4d-f7e7-431d-b942-2638799a701d', 'admin', '修改角色', 'com.lcz.manage.base.controller.SysRoleController.update()', '{\"menuIdList\":[],\"remark\":\"测试用户的权限2\",\"roleId\":\"3\",\"roleName\":\"测试用户2\"}', '127.0.0.1', '2017-12-27 19:18:57');
INSERT INTO `sys_log` VALUES ('0fb7cfed-3b79-4db2-a37b-ad556940c52c', 'admin', '保存菜单', 'com.lcz.manage.sys.controller.SysMenuController.save()', '{\"icon\":\"\",\"menuId\":\"\",\"name\":\"cc\",\"orderNum\":2,\"parentId\":\"0\",\"parentName\":\"一级菜单\",\"perms\":\"\",\"type\":0,\"url\":\"\"}', '127.0.0.1', '2018-01-09 11:13:28');
INSERT INTO `sys_log` VALUES ('110a12a2-3ad3-41f1-a3dd-ea3cf8fbd597', 'admin', '保存用户', 'com.lcz.manage.sys.controller.SysUserController.save()', '{\"createUserId\":\"\",\"deptId\":\"6\",\"deptName\":\"测试部\",\"email\":\"hebbyok@gmail.com\",\"mobile\":\"13346335116\",\"roleIdList\":[\"2\"],\"status\":1,\"userId\":\"\",\"username\":\"hebby\"}', '127.0.0.1', '2018-01-26 23:24:51');
INSERT INTO `sys_log` VALUES ('13d0d8ba-84e2-46c1-a3af-e073a3b39674', 'admin', '修改用户', 'com.lcz.manage.base.controller.SysUserController.update()', '{\"email\":\"jarrys@gmail.com\",\"mobile\":\"15367668711\",\"password\":\"123\",\"status\":0,\"userId\":\"3698a1be-d9aa-41e7-880f-6e9a0f44a3ff\",\"username\":\"jarrys\"}', '127.0.0.1', '2017-12-26 08:47:35');
INSERT INTO `sys_log` VALUES ('13fb2a2f-5753-4727-8884-71477a60a2d6', 'admin', '删除配置', 'com.lcz.manage.sys.controller.SysConfigController.delete()', '[\"0e2526f7-45f8-4d9c-b0fc-32a1b4dd79ee\"]', '127.0.0.1', '2018-02-07 09:45:18');
INSERT INTO `sys_log` VALUES ('16b8122b-4061-4b2a-8f27-1a1ec308e4a4', 'admin', '删除定时任务', 'com.lcz.manage.sys.controller.ScheduleJobController.delete()', '[3]', '127.0.0.1', '2018-01-22 10:15:23');
INSERT INTO `sys_log` VALUES ('1909370a-6f17-40bf-92ea-df433dba90cb', 'admin', '删除部门', 'com.lcz.manage.sys.controller.SysDeptController.delete()', '\"d635fa28-a031-4f0a-9420-aa42d056d062\"', '127.0.0.1', '2018-01-28 13:40:45');
INSERT INTO `sys_log` VALUES ('1a498fe7-3590-40dc-a236-dfb9d87381d2', 'admin', '修改用户', 'com.lcz.manage.base.controller.SysUserController.update()', '{\"createUserId\":\"1\",\"email\":\"jarrys@gmail.com\",\"mobile\":\"15367668711\",\"password\":\"\",\"roleIdList\":[\"3\"],\"status\":1,\"userId\":\"3\",\"username\":\"jarrys\"}', '127.0.0.1', '2017-12-28 16:27:16');
INSERT INTO `sys_log` VALUES ('1b5f4d6b-4509-4d94-898a-f6c5014c9a46', 'admin', '保存角色', 'com.lcz.manage.sys.controller.SysRoleController.save()', '{\"menuIdList\":[\"1\",\"4\",\"41\",\"42\",\"43\",\"44\"],\"remark\":\"管理菜单\",\"roleId\":\"\",\"roleName\":\"菜单管理员\"}', '127.0.0.1', '2018-01-09 11:06:15');
INSERT INTO `sys_log` VALUES ('1b84b5c4-6170-4e61-aa1d-34e8c6b1ec8f', 'admin', '暂停定时任务', 'com.lcz.manage.sys.controller.ScheduleJobController.pause()', '[1]', '127.0.0.1', '2018-01-22 10:57:12');
INSERT INTO `sys_log` VALUES ('1d177384-1416-43e0-9e81-cb1c737f7a74', 'admin', '修改角色', 'com.lcz.manage.base.controller.SysRoleController.update()', '{\"menuIdList\":[\"1\",\"2\",\"15\"],\"remark\":\"测试用户的权限2\",\"roleId\":\"3\",\"roleName\":\"测试用户2\"}', '127.0.0.1', '2017-12-27 20:59:58');
INSERT INTO `sys_log` VALUES ('1d7b601d-c29e-4b84-929d-13a5805d92eb', 'admin', '删除菜单', 'com.lcz.manage.sys.controller.SysMenuController.delete()', '\"d1acde2c-01c9-4d4e-9033-70a2476052f6\"', '127.0.0.1', '2018-01-09 13:32:01');
INSERT INTO `sys_log` VALUES ('1dda9fa0-66e8-4a75-9999-3b4abaa40535', 'admin', '修改密码', 'com.lcz.manage.base.controller.SysUserController.password()', '\"1234\"', '127.0.0.1', '2017-12-27 08:55:07');
INSERT INTO `sys_log` VALUES ('1e5270aa-60cc-4415-98a5-2680edcbb6b0', 'admin', '修改用户', 'com.lcz.manage.base.controller.SysUserController.update()', '{\"email\":\"jarrys@gmail.com\",\"mobile\":\"15367668711\",\"password\":\"\",\"roleIdList\":[\"1\",\"2\"],\"status\":0,\"userId\":\"3\",\"username\":\"jarrys\"}', '127.0.0.1', '2017-12-27 13:36:32');
INSERT INTO `sys_log` VALUES ('1eda20a6-f8c7-43f3-95e7-d1980a9943bd', 'admin', '删除菜单', 'com.lcz.manage.sys.controller.SysMenuController.delete()', '\"2986b3ad-3de9-4422-9927-8d8eed7964dd\"', '127.0.0.1', '2018-02-07 09:46:03');
INSERT INTO `sys_log` VALUES ('1fb0a41d-afc5-422f-b6ba-072b36a40123', 'admin', '保存菜单', 'com.lcz.manage.sys.controller.SysMenuController.save()', '{\"icon\":\"\",\"menuId\":\"\",\"name\":\"贴吧\",\"orderNum\":0,\"parentId\":\"0f3a5ee1-58b5-4ec3-966b-6c49d5eef45e\",\"parentName\":\"百度\",\"perms\":\"\",\"type\":1,\"url\":\"/tieba\"}', '127.0.0.1', '2018-01-28 13:38:45');
INSERT INTO `sys_log` VALUES ('22570bf9-96a8-49be-8410-076ad12dff9b', 'admin', '暂停定时任务', 'com.lcz.manage.sys.controller.ScheduleJobController.pause()', '[1]', '127.0.0.1', '2018-01-22 10:51:07');
INSERT INTO `sys_log` VALUES ('23a172f0-debe-4fad-b7f6-f9ad229e783d', 'admin', '修改用户', 'com.lcz.manage.base.controller.SysUserController.update()', '{\"email\":\"jarrys@gmail.com\",\"mobile\":\"15367668711\",\"password\":\"\",\"roleIdList\":[\"on\",\"1\",\"2\"],\"status\":0,\"userId\":\"3\",\"username\":\"jarrys\"}', '127.0.0.1', '2017-12-27 13:31:35');
INSERT INTO `sys_log` VALUES ('23ee3e6b-ebfc-474a-a3b9-e1274ea72804', 'admin', '修改用户', 'com.lcz.manage.base.controller.SysUserController.update()', '{\"email\":\"jarrys@gmail.com\",\"mobile\":\"15367668711\",\"password\":\"\",\"roleIdList\":[\"1\",\"2\"],\"status\":0,\"userId\":\"3\",\"username\":\"jarrys\"}', '127.0.0.1', '2017-12-27 13:37:21');
INSERT INTO `sys_log` VALUES ('264d02d0-5c8c-4603-a8cb-4fea83b62178', 'admin', '保存菜单', 'com.lcz.manage.sys.controller.SysMenuController.save()', '{\"icon\":\"\",\"menuId\":\"\",\"name\":\"cc\",\"orderNum\":2,\"parentId\":\"0\",\"parentName\":\"一级菜单\",\"perms\":\"\",\"type\":0,\"url\":\"\"}', '127.0.0.1', '2018-01-09 11:27:56');
INSERT INTO `sys_log` VALUES ('26e9d8dd-48b5-4ba0-a56b-96db7c9d9775', 'admin', '保存角色', 'com.lcz.manage.sys.controller.SysRoleController.save()', '{\"menuIdList\":[\"7\",\"6\"],\"remark\":\"\",\"roleId\":\"\",\"roleName\":\"\"}', '127.0.0.1', '2018-01-09 13:28:27');
INSERT INTO `sys_log` VALUES ('28de5a97-d6e3-4c55-b3d9-bbc57efabae8', 'admin', '保存菜单', 'com.lcz.manage.sys.controller.SysMenuController.save()', '{\"icon\":\"\",\"menuId\":\"\",\"name\":\"cc\",\"orderNum\":2,\"parentId\":\"0\",\"parentName\":\"一级菜单\",\"perms\":\"\",\"type\":0,\"url\":\"\"}', '127.0.0.1', '2018-01-09 13:30:19');
INSERT INTO `sys_log` VALUES ('291f98e6-4d1c-43f6-a9bb-32ba1e8f1684', 'admin', '修改密码', 'com.lcz.manage.base.controller.SysUserController.password()', '\"1234\"', '127.0.0.1', '2017-12-27 08:53:14');
INSERT INTO `sys_log` VALUES ('2ab5c13a-41ff-48c3-84f2-724e45c6b976', 'admin', '保存角色', 'com.lcz.manage.base.controller.SysRoleController.save()', '{\"menuIdList\":[\"1\",\"2\",\"15\",\"16\",\"17\",\"18\"],\"remark\":\"测试用户的权限2\",\"roleId\":\"\",\"roleName\":\"测试用户2\"}', '127.0.0.1', '2017-12-27 18:53:46');
INSERT INTO `sys_log` VALUES ('2bd361e7-6a39-413c-a9b6-b8088a93c855', 'admin', '暂停定时任务', 'com.lcz.manage.sys.controller.ScheduleJobController.pause()', '[1,2]', '127.0.0.1', '2018-02-01 20:50:29');
INSERT INTO `sys_log` VALUES ('2c274149-4292-464c-af8c-f7e5e5b7bc29', 'admin', '修改角色', 'com.lcz.manage.sys.controller.SysRoleController.update()', '{\"menuIdList\":[\"7\",\"5\"],\"remark\":\"查看系统日志、sql监控。\",\"roleId\":\"d15422bd-780b-45dc-a67e-57c804cb35f1\",\"roleName\":\"系统管理员\"}', '127.0.0.1', '2018-01-04 21:40:18');
INSERT INTO `sys_log` VALUES ('2d5e024e-b3ac-415c-8c53-90a9329788d1', 'admin', '保存用户', 'com.lcz.manage.sys.controller.SysUserController.save()', '{\"createUserId\":\"\",\"email\":\"ss@qq.com\",\"mobile\":\"13367115166\",\"roleIdList\":[\"e47e920c-1e21-4592-a03d-ca48a6c3509c\"],\"status\":1,\"userId\":\"\",\"username\":\"c\"}', '127.0.0.1', '2018-01-09 13:27:53');
INSERT INTO `sys_log` VALUES ('308a8b9b-1b92-4c3a-8ba2-12dc2e6b25b4', 'admin', '删除菜单', 'com.lcz.manage.sys.controller.SysMenuController.delete()', '\"6\"', '127.0.0.1', '2018-01-20 14:07:08');
INSERT INTO `sys_log` VALUES ('327a5101-9a33-41f3-85f7-2225f6905cbf', 'admin', '修改用户', 'com.lcz.manage.sys.controller.SysUserController.update()', '{\"createUserId\":\"1\",\"email\":\"sophia@beautiful.com\",\"mobile\":\"01064887524\",\"roleIdList\":[\"d15422bd-780b-45dc-a67e-57c804cb35f1\"],\"status\":1,\"userId\":\"381d6d76-dfff-411d-871a-c5d76c5d63ef\",\"username\":\"sophia\"}', '127.0.0.1', '2018-01-09 11:03:18');
INSERT INTO `sys_log` VALUES ('333a6083-7c89-4363-a301-75125fddc03a', 'admin', '修改用户', 'com.lcz.manage.base.controller.SysUserController.update()', '{\"email\":\"jarrys@gmail.com\",\"mobile\":\"15367668711\",\"password\":\"\",\"roleIdList\":[\"1\",\"2\"],\"status\":0,\"userId\":\"3\",\"username\":\"jarrys\"}', '127.0.0.1', '2017-12-27 13:34:38');
INSERT INTO `sys_log` VALUES ('3362ab54-174a-41cc-b93e-5095bc264710', 'admin', '保存配置', 'com.lcz.manage.sys.controller.SysConfigController.save()', '{\"id\":\"\",\"key\":\"2\",\"remark\":\"2\",\"status\":1,\"value\":\"2\"}', '127.0.0.1', '2018-01-09 13:33:51');
INSERT INTO `sys_log` VALUES ('3488e11e-e172-4c54-813c-66c55a6d01ef', 'admin', '保存角色', 'com.lcz.manage.sys.controller.SysRoleController.save()', '{\"menuIdList\":[\"7\",\"5\",\"6\"],\"remark\":\"查看系统日志、sql监控。\",\"roleId\":\"\",\"roleName\":\"系统管理员\"}', '127.0.0.1', '2018-01-04 21:40:12');
INSERT INTO `sys_log` VALUES ('37947bea-2ff9-4135-9742-9ee330c38b9b', 'admin', '删除菜单', 'com.lcz.manage.sys.controller.SysMenuController.delete()', '\"0571031d-16f7-4dfe-a158-a9f09ecfc4a8\"', '127.0.0.1', '2018-01-09 13:32:06');
INSERT INTO `sys_log` VALUES ('37c2c578-f681-4e33-9baf-0eac80fa7ad8', 'admin', '保存菜单', 'com.lcz.manage.sys.controller.SysMenuController.save()', '{\"icon\":\"\",\"menuId\":\"\",\"name\":\"cc\",\"orderNum\":2,\"parentId\":\"0\",\"parentName\":\"一级菜单\",\"perms\":\"\",\"type\":0,\"url\":\"\"}', '127.0.0.1', '2018-01-09 11:12:45');
INSERT INTO `sys_log` VALUES ('3910bf4f-bd5d-4fc0-b56d-c0658c35762c', 'admin', '保存菜单', 'com.lcz.manage.base.controller.SysMenuController.save()', '{\"icon\":\"fa fa-comment\",\"menuId\":\"\",\"name\":\"关于我\",\"orderNum\":0,\"parentId\":\"bc9dd436-5239-423c-99ec-a44253b8668b\",\"parentName\":\"关于\",\"perms\":\"\",\"type\":1,\"url\":\"/sys/aboutme\"}', '127.0.0.1', '2017-12-31 10:52:46');
INSERT INTO `sys_log` VALUES ('39242bf2-fb85-4acb-a8b9-742546f6747e', 'admin', '立即执行任务', 'com.lcz.manage.sys.controller.ScheduleJobController.run()', '[1]', '127.0.0.1', '2018-01-28 13:56:55');
INSERT INTO `sys_log` VALUES ('39532419-298f-4bca-ba00-5c3ccbe89e76', 'admin', '修改用户', 'com.lcz.manage.sys.controller.SysUserController.update()', '{\"createUserId\":\"1\",\"email\":\"sophia@beautiful.com\",\"mobile\":\"01064887524\",\"roleIdList\":[\"d15422bd-780b-45dc-a67e-57c804cb35f1\"],\"status\":0,\"userId\":\"381d6d76-dfff-411d-871a-c5d76c5d63ef\",\"username\":\"sophia\"}', '127.0.0.1', '2018-01-09 11:03:30');
INSERT INTO `sys_log` VALUES ('3b0736bd-423d-4a4b-84eb-a0c2eb29e5de', 'admin', '修改角色', 'com.lcz.manage.sys.controller.SysRoleController.update()', '{\"menuIdList\":[\"7\",\"5\"],\"remark\":\"查看系统日志\",\"roleId\":\"d15422bd-780b-45dc-a67e-57c804cb35f1\",\"roleName\":\"日志管理员\"}', '127.0.0.1', '2018-01-09 11:05:09');
INSERT INTO `sys_log` VALUES ('3b83c954-b984-4d44-994b-436b639ce099', 'admin', '修改密码', 'com.lcz.manage.sys.controller.SysUserController.password()', '\"123\"', '127.0.0.1', '2018-01-16 08:56:53');
INSERT INTO `sys_log` VALUES ('3bdcf493-7aae-4710-83cb-1f93d8981fb6', 'admin', '修改密码', 'com.lcz.manage.sys.controller.SysUserController.password()', '\"123\"', '127.0.0.1', '2018-01-16 08:47:07');
INSERT INTO `sys_log` VALUES ('3c0ab163-4f97-401b-ad26-b30f468fcfdd', 'admin', '修改用户', 'com.lcz.manage.sys.controller.SysUserController.update()', '{\"createUserId\":\"1\",\"email\":\"Sophia@gmail.com\",\"mobile\":\"01074663211\",\"roleIdList\":[\"d15422bd-780b-45dc-a67e-57c804cb35f1\"],\"status\":0,\"userId\":\"381d6d76-dfff-411d-871a-c5d76c5d63ef\",\"username\":\"Sophia\"}', '127.0.0.1', '2018-01-04 22:17:39');
INSERT INTO `sys_log` VALUES ('3c7be737-05a9-4f53-90ef-a3fc5f06e805', 'admin', '修改角色', 'com.lcz.manage.base.controller.SysRoleController.update()', '{\"menuIdList\":[\"1\",\"2\",\"21\",\"3\",\"31\",\"4\",\"41\",\"5\",\"6\",\"7\"],\"remark\":\"只拥有查看权限\",\"roleId\":\"2\",\"roleName\":\"普通用户\"}', '127.0.0.1', '2017-12-30 16:44:22');
INSERT INTO `sys_log` VALUES ('3cca420d-5c2f-4b68-aea2-668190550540', 'admin', '保存菜单', 'com.lcz.manage.sys.controller.SysMenuController.save()', '{\"icon\":\"\",\"menuId\":\"\",\"name\":\"新增\",\"orderNum\":0,\"parentId\":\"0571031d-16f7-4dfe-a158-a9f09ecfc4a8\",\"parentName\":\"c1-1\",\"perms\":\"c1:save\",\"type\":2,\"url\":\"\"}', '127.0.0.1', '2018-01-09 13:31:55');
INSERT INTO `sys_log` VALUES ('3ce6b195-5884-4dd4-89f6-9608efdb048c', 'admin', '保存角色', 'com.lcz.manage.base.controller.SysRoleController.save()', '{\"menuIdList\":[],\"remark\":\"测试用户的权限\",\"roleId\":\"\",\"roleName\":\"测试用户\"}', '127.0.0.1', '2017-12-27 18:59:01');
INSERT INTO `sys_log` VALUES ('3d849239-90a9-4f29-b93c-73eb61132395', 'admin', '修改角色', 'com.lcz.manage.base.controller.SysRoleController.update()', '{\"menuIdList\":[\"1\",\"2\",\"21\",\"22\",\"23\",\"24\"],\"remark\":\"只拥有管理员列表管理的权限\",\"roleId\":\"3\",\"roleName\":\"用户管理员\"}', '127.0.0.1', '2017-12-30 16:44:30');
INSERT INTO `sys_log` VALUES ('3f1bf3fb-842e-43f7-bedc-291f573356fc', 'admin', '修改用户', 'com.lcz.manage.sys.controller.SysUserController.update()', '{\"createUserId\":\"1\",\"email\":\"herry@cc.com\",\"mobile\":\"13655437717\",\"roleIdList\":[\"e47e920c-1e21-4592-a03d-ca48a6c3509c\"],\"status\":1,\"userId\":\"6e36f4c5-c6e4-4bfc-9f88-b64acdb12858\",\"username\":\"herry\"}', '127.0.0.1', '2018-01-09 13:42:01');
INSERT INTO `sys_log` VALUES ('40619f41-32bd-40d8-a549-4a6ac7da5ffa', 'admin', '修改用户', 'com.lcz.manage.sys.controller.SysUserController.update()', '{\"createUserId\":\"1\",\"deptId\":\"a3d71c3c-a2c0-42de-a459-cda3e552e5e0\",\"deptName\":\"市场部\",\"email\":\"sophia@beautiful.com\",\"mobile\":\"01064887524\",\"roleIdList\":[\"d15422bd-780b-45dc-a67e-57c804cb35f1\"],\"status\":1,\"userId\":\"381d6d76-dfff-411d-871a-c5d76c5d63ef\",\"username\":\"sophia\"}', '127.0.0.1', '2018-01-27 21:30:14');
INSERT INTO `sys_log` VALUES ('416f3d7d-1e34-4b8c-84fb-2ac528506cfc', 'admin', '删除用户', 'com.lcz.manage.base.controller.SysUserController.deleteBatch()', '[\"3698a1be-d9aa-41e7-880f-6e9a0f44a3ff\"]', '127.0.0.1', '2017-12-26 11:40:05');
INSERT INTO `sys_log` VALUES ('4782ac9e-c201-41e0-8e1a-42e8c1f099c2', 'admin', '保存菜单', 'com.lcz.manage.base.controller.SysMenuController.save()', '{\"icon\":\"fa fa-cc\",\"menuId\":\"\",\"name\":\"关于\",\"orderNum\":1,\"parentId\":\"0\",\"parentName\":\"一级菜单\",\"perms\":\"\",\"type\":0}', '127.0.0.1', '2017-12-31 10:48:24');
INSERT INTO `sys_log` VALUES ('491239ce-d4f5-4859-a55c-1f6cc556e692', 'admin', '删除菜单', 'com.lcz.manage.sys.controller.SysMenuController.delete()', '\"53567e9c-00b7-48df-b2ab-611543a4250d\"', '127.0.0.1', '2018-01-09 13:33:27');
INSERT INTO `sys_log` VALUES ('4a790ceb-1fb7-4127-a939-baa13026023b', 'admin', '修改用户', 'com.lcz.manage.base.controller.SysUserController.update()', '{\"createUserId\":\"1\",\"email\":\"jarry@qq.com\",\"mobile\":\"13577487265\",\"roleIdList\":[\"2\",\"3\"],\"status\":1,\"userId\":\"3\",\"username\":\"jarry\"}', '127.0.0.1', '2017-12-29 15:03:39');
INSERT INTO `sys_log` VALUES ('4c302e90-4878-47fb-b28f-719b485aa4c8', 'admin', '删除配置', 'com.lcz.manage.sys.controller.SysConfigController.delete()', '[\"9b7f4b64-322c-46c1-9879-c5cdd7d94624\"]', '127.0.0.1', '2018-01-28 13:42:32');
INSERT INTO `sys_log` VALUES ('4f44c042-11d3-4c6d-b8ab-964a21d97f0e', 'admin', '保存菜单', 'com.lcz.manage.sys.controller.SysMenuController.save()', '{\"icon\":\"\",\"menuId\":\"\",\"name\":\"cc\",\"orderNum\":2,\"parentId\":\"0\",\"parentName\":\"一级菜单\",\"perms\":\"\",\"type\":0,\"url\":\"\"}', '127.0.0.1', '2018-01-09 11:23:21');
INSERT INTO `sys_log` VALUES ('4f6865c9-d4cf-49ce-8cb2-bcba6afd4bd1', 'admin', '修改角色', 'com.lcz.manage.base.controller.SysRoleController.update()', '{\"menuIdList\":[\"1\",\"2\",\"15\",\"16\",\"17\",\"18\"],\"remark\":\"测试用户的权限2\",\"roleId\":\"3\",\"roleName\":\"测试用户2\"}', '127.0.0.1', '2017-12-27 19:02:09');
INSERT INTO `sys_log` VALUES ('5048da69-7eb7-447f-99b8-c70d6640981d', 'admin', '暂停定时任务', 'com.lcz.manage.sys.controller.ScheduleJobController.pause()', '[1]', '127.0.0.1', '2018-01-28 13:52:40');
INSERT INTO `sys_log` VALUES ('516955e1-43cc-4bff-af80-604c6a244054', 'admin', '删除角色', 'com.lcz.manage.base.controller.SysRoleController.delete()', '[\"3\"]', '127.0.0.1', '2017-12-27 18:49:57');
INSERT INTO `sys_log` VALUES ('51d3d6a4-3156-415a-b9c6-ceac1e3864d7', 'admin', '保存菜单', 'com.lcz.manage.sys.controller.SysMenuController.save()', '{\"icon\":\"\",\"menuId\":\"\",\"name\":\"百度\",\"orderNum\":3,\"parentId\":\"7\",\"parentName\":\"系统管理\",\"perms\":\"\",\"type\":0,\"url\":\"\"}', '127.0.0.1', '2018-01-28 13:37:47');
INSERT INTO `sys_log` VALUES ('5420ac1d-ed26-40fb-ada4-f302672f06db', 'admin', '修改用户', 'com.lcz.manage.sys.controller.SysUserController.update()', '{\"createUserId\":\"1\",\"email\":\"ss@qq.com\",\"mobile\":\"13367115166\",\"roleIdList\":[\"d15422bd-780b-45dc-a67e-57c804cb35f1\"],\"status\":1,\"userId\":\"ced646ad-733f-4446-8562-97bf019ca564\",\"username\":\"cc\"}', '127.0.0.1', '2018-01-09 13:28:04');
INSERT INTO `sys_log` VALUES ('54c6ddc6-9724-4c78-8361-f0b801045f93', 'admin', '修改菜单', 'com.lcz.manage.base.controller.SysMenuController.update()', '{\"icon\":\"fa fa-heart-o\",\"menuId\":\"7\",\"name\":\"关于\",\"orderNum\":6,\"parentId\":\"0\",\"parentName\":\"一级菜单\",\"perms\":\"\",\"type\":0,\"url\":\"\"}', '127.0.0.1', '2017-12-31 20:44:32');
INSERT INTO `sys_log` VALUES ('5595b457-7aad-4f84-802e-278f83d23737', 'admin', '修改密码', 'com.lcz.manage.base.controller.SysUserController.password()', '\"1234\"', '127.0.0.1', '2017-12-27 08:54:47');
INSERT INTO `sys_log` VALUES ('55cc0fb9-1b86-4f72-8ee6-171591eb2357', 'admin', '修改菜单', 'com.lcz.manage.sys.controller.SysMenuController.update()', '{\"icon\":\"\",\"menuId\":\"431ff605-5662-4f67-acd6-b494e312906f\",\"name\":\"cc\",\"orderNum\":32,\"parentId\":\"0\",\"parentName\":\"一级菜单\",\"perms\":\"\",\"type\":0,\"url\":\"\"}', '127.0.0.1', '2018-01-09 13:21:11');
INSERT INTO `sys_log` VALUES ('5698e985-6336-416d-8a45-8cc2e083218a', 'admin', '修改角色', 'com.lcz.manage.base.controller.SysRoleController.update()', '{\"menuIdList\":[\"1\",\"2\",\"21\",\"3\",\"31\",\"4\",\"41\",\"5\",\"6\",\"7\",\"8\"],\"remark\":\"只拥有查看权限\",\"roleId\":\"2\",\"roleName\":\"普通用户\"}', '127.0.0.1', '2017-12-31 11:11:13');
INSERT INTO `sys_log` VALUES ('5781bcb9-1d08-4b1e-803c-da281da5fb60', 'admin', '修改角色', 'com.lcz.manage.base.controller.SysRoleController.update()', '{\"menuIdList\":[\"1\",\"2\",\"15\",\"3\",\"19\"],\"remark\":\"测试用户的权限\",\"roleId\":\"3\",\"roleName\":\"测试用户\"}', '127.0.0.1', '2017-12-28 09:36:34');
INSERT INTO `sys_log` VALUES ('59320a20-6bd7-4e0b-b695-71a805ae461a', 'admin', '暂停定时任务', 'com.lcz.manage.sys.controller.ScheduleJobController.pause()', '[1]', '127.0.0.1', '2018-01-22 10:51:07');
INSERT INTO `sys_log` VALUES ('5aa4f20a-c45a-4d12-9436-38faef8b6518', 'admin', '立即执行任务', 'com.lcz.manage.sys.controller.ScheduleJobController.run()', '[1]', '127.0.0.1', '2018-01-22 10:54:51');
INSERT INTO `sys_log` VALUES ('5b197050-284b-4a31-9a5a-df02353a0acc', 'admin', '立即执行任务', 'com.lcz.manage.sys.controller.ScheduleJobController.run()', '[1]', '127.0.0.1', '2018-01-22 10:56:53');
INSERT INTO `sys_log` VALUES ('5b9a5306-2ffe-48a5-8740-c94793008bac', 'admin', '保存菜单', 'com.lcz.manage.sys.controller.SysMenuController.save()', '{\"icon\":\"\",\"menuId\":\"\",\"name\":\"c1\",\"orderNum\":0,\"parentId\":\"2874aee5-5420-4bc3-b8e0-e9fde18ae255\",\"parentName\":\"cc\",\"perms\":\"c1:list\",\"type\":1,\"url\":\"/cc/c1\"}', '127.0.0.1', '2018-01-09 13:32:37');
INSERT INTO `sys_log` VALUES ('5ccb4088-ee59-4369-815d-75b7e9609cbe', 'admin', '删除菜单', 'com.lcz.manage.sys.controller.SysMenuController.delete()', '\"f1f653f8-f95e-460f-8c16-94c1df58ff2a\"', '127.0.0.1', '2018-01-09 13:33:23');
INSERT INTO `sys_log` VALUES ('5d12e652-bab6-41ac-9710-f575d93bd89b', 'admin', '暂停定时任务', 'com.lcz.manage.sys.controller.ScheduleJobController.pause()', '[1]', '127.0.0.1', '2018-01-22 11:01:00');
INSERT INTO `sys_log` VALUES ('5f5b2629-dbc5-48e3-92be-45f3c1ccca85', 'admin', '立即执行任务', 'com.lcz.manage.sys.controller.ScheduleJobController.run()', '[1]', '127.0.0.1', '2018-01-28 13:52:15');
INSERT INTO `sys_log` VALUES ('5f64febc-39fe-42ca-b962-0e29b3aac62a', 'admin', '修改用户', 'com.lcz.manage.sys.controller.SysUserController.update()', '{\"createUserId\":\"1\",\"email\":\"herry@cc.com\",\"mobile\":\"13655437717\",\"roleIdList\":[\"e47e920c-1e21-4592-a03d-ca48a6c3509c\"],\"status\":0,\"userId\":\"6e36f4c5-c6e4-4bfc-9f88-b64acdb12858\",\"username\":\"herry\"}', '127.0.0.1', '2018-01-09 13:42:18');
INSERT INTO `sys_log` VALUES ('60285545-e65d-4be6-b915-3ded2deff006', 'admin', '删除菜单', 'com.lcz.manage.base.controller.SysMenuController.delete()', '\"10\"', '127.0.0.1', '2018-01-02 14:37:12');
INSERT INTO `sys_log` VALUES ('6155367b-e8ec-40e3-ae5d-ed416ae1f997', 'admin', '删除菜单', 'com.lcz.manage.sys.controller.SysMenuController.delete()', '\"2ba1d9fa-4d5e-4181-901c-e9d5f6cbaa17\"', '127.0.0.1', '2018-02-07 09:45:51');
INSERT INTO `sys_log` VALUES ('62da3d30-e28f-4ecf-b1d0-9d8833e677d8', 'admin', '修改用户', 'com.lcz.manage.base.controller.SysUserController.update()', '{\"createUserId\":\"1\",\"email\":\"jarrys@gmail.com\",\"mobile\":\"15367668711\",\"password\":\"\",\"roleIdList\":[\"1\",\"2\",\"3\"],\"status\":0,\"userId\":\"3\",\"username\":\"jarrys\"}', '127.0.0.1', '2017-12-28 16:13:37');
INSERT INTO `sys_log` VALUES ('631d938b-9ce1-4270-b6cf-56c30ddcfdc8', 'admin', '删除用户', 'com.lcz.manage.base.controller.SysUserController.deleteBatch()', '[\"2\"]', '127.0.0.1', '2017-12-25 15:32:46');
INSERT INTO `sys_log` VALUES ('63bdc78e-d5ff-4296-bef3-32b821cc1413', 'admin', '立即执行任务', 'com.lcz.manage.sys.controller.ScheduleJobController.run()', '[3]', '127.0.0.1', '2018-01-22 10:12:43');
INSERT INTO `sys_log` VALUES ('643b5557-5b2b-49f6-9587-79714f542649', 'admin', '修改用户', 'com.lcz.manage.base.controller.SysUserController.update()', '{\"email\":\"jarrys@gmail.com\",\"mobile\":\"15367668711\",\"password\":\"\",\"roleIdList\":[\"on\",\"1\",\"2\"],\"status\":0,\"userId\":\"3\",\"username\":\"jarrys\"}', '127.0.0.1', '2017-12-27 13:25:32');
INSERT INTO `sys_log` VALUES ('65275732-d0c5-4e97-9805-a2973e592dc3', 'admin', '修改用户', 'com.lcz.manage.sys.controller.SysUserController.update()', '{\"createUserId\":\"1\",\"email\":\"herry@cc.com\",\"mobile\":\"13655437717\",\"roleIdList\":[\"e47e920c-1e21-4592-a03d-ca48a6c3509c\"],\"status\":0,\"userId\":\"6e36f4c5-c6e4-4bfc-9f88-b64acdb12858\",\"username\":\"herry\"}', '127.0.0.1', '2018-01-16 08:34:12');
INSERT INTO `sys_log` VALUES ('66718b45-f299-499a-98bf-a4977ccffef8', 'admin', '修改配置', 'com.lcz.manage.sys.controller.SysConfigController.update()', '{\"id\":\"2\",\"key\":\"LOST_WISDOM\",\"remark\":\"失了智\",\"status\":0,\"value\":\"{\\\"id\\\":\\\"1\\\",\\\"name\\\":\\\"lost_wisdom\\\",\\\"remark\\\":\\\"失了智\\\"}\\t\"}', '127.0.0.1', '2018-01-07 15:44:16');
INSERT INTO `sys_log` VALUES ('67b3e9d7-9a9e-4208-9fb8-6d83921ce2df', 'admin', '修改用户', 'com.lcz.manage.sys.controller.SysUserController.update()', '{\"createUserId\":\"1\",\"email\":\"sophia@beautiful.com\",\"mobile\":\"01064887524\",\"roleIdList\":[\"d15422bd-780b-45dc-a67e-57c804cb35f1\"],\"status\":0,\"userId\":\"381d6d76-dfff-411d-871a-c5d76c5d63ef\",\"username\":\"sophia\"}', '127.0.0.1', '2018-01-16 08:35:45');
INSERT INTO `sys_log` VALUES ('693d42da-86f3-4363-ae13-90f37c8151d0', 'admin', '修改密码', 'com.lcz.manage.sys.controller.SysUserController.password()', '\"123\"', '127.0.0.1', '2018-01-16 09:04:35');
INSERT INTO `sys_log` VALUES ('6a2c1cd9-5e03-4944-b48c-be70a8baa785', 'admin', '修改用户', 'com.lcz.manage.sys.controller.SysUserController.update()', '{\"createUserId\":\"1\",\"deptId\":\"4\",\"deptName\":\"技术部\",\"email\":\"sophia@beautiful.com\",\"mobile\":\"01064887524\",\"roleIdList\":[\"d15422bd-780b-45dc-a67e-57c804cb35f1\"],\"status\":0,\"userId\":\"381d6d76-dfff-411d-871a-c5d76c5d63ef\",\"username\":\"sophia\"}', '127.0.0.1', '2018-01-26 23:19:17');
INSERT INTO `sys_log` VALUES ('6a507e9d-6646-4d5b-9541-16aa44a76e40', 'admin', '修改用户', 'com.lcz.manage.base.controller.SysUserController.update()', '{\"createUserId\":\"1\",\"email\":\"jarry@qq.com\",\"mobile\":\"13577487265\",\"roleIdList\":[\"3\"],\"status\":1,\"userId\":\"3\",\"username\":\"\"}', '127.0.0.1', '2017-12-28 16:35:26');
INSERT INTO `sys_log` VALUES ('6af6376c-7bdf-4a27-8742-6c0d100b4c54', 'admin', '修改角色', 'com.lcz.manage.base.controller.SysRoleController.update()', '{\"menuIdList\":[\"1\",\"2\",\"15\",\"16\",\"17\",\"18\"],\"remark\":\"测试用户的权限2\",\"roleId\":\"3\",\"roleName\":\"测试用户2\"}', '127.0.0.1', '2017-12-27 19:19:57');
INSERT INTO `sys_log` VALUES ('6c43694c-5ac1-402a-ae03-beac3ed13676', 'admin', '删除用户', 'com.lcz.manage.base.controller.SysUserController.deleteBatch()', '[\"3698a1be-d9aa-41e7-880f-6e9a0f44a3ff\"]', '127.0.0.1', '2017-12-26 11:41:11');
INSERT INTO `sys_log` VALUES ('6cd94e9d-dbfa-411e-9d83-675fd866f0f4', 'admin', '保存用户', 'com.lcz.manage.sys.controller.SysUserController.save()', '{\"createUserId\":\"\",\"email\":\"Sophia@gmail.com\",\"mobile\":\"01074663211\",\"roleIdList\":[\"2\"],\"status\":1,\"userId\":\"\",\"username\":\"Sophia\"}', '127.0.0.1', '2018-01-04 21:39:15');
INSERT INTO `sys_log` VALUES ('6cf96ffa-a8a5-48aa-9bfe-a9871ccea14b', 'admin', '保存配置', 'com.lcz.manage.sys.controller.SysConfigController.save()', '{\"id\":\"\",\"key\":\"sex\",\"remark\":\"女\",\"status\":1,\"value\":\"0\"}', '127.0.0.1', '2018-02-07 09:44:38');
INSERT INTO `sys_log` VALUES ('6d8552af-8cab-464c-8158-44691ba0f513', 'admin', '修改密码', 'com.lcz.manage.base.controller.SysUserController.password()', '\"1234\"', '127.0.0.1', '2017-12-27 08:52:14');
INSERT INTO `sys_log` VALUES ('6df613e1-b9c4-4a24-a6ac-e3eee0e8dc96', 'admin', '修改配置', 'com.lcz.manage.sys.controller.SysConfigController.update()', '{\"id\":\"d4285677-467b-49be-ac88-ea0decfaf3cb\",\"key\":\"22\",\"remark\":\"22\",\"status\":0,\"value\":\"22\"}', '127.0.0.1', '2018-01-09 13:33:57');
INSERT INTO `sys_log` VALUES ('6df6e08c-c2a6-4271-a5cc-e06b0a357f11', 'admin', '修改角色', 'com.lcz.manage.base.controller.SysRoleController.update()', '{\"menuIdList\":[\"1\",\"2\",\"15\",\"3\",\"19\",\"4\",\"23\"],\"remark\":\"只拥有查看权限\",\"roleId\":\"2\",\"roleName\":\"普通管理员\"}', '127.0.0.1', '2017-12-28 09:41:40');
INSERT INTO `sys_log` VALUES ('6e0574e0-20c9-4861-a361-bfe23e10dd20', 'admin', '保存菜单', 'com.lcz.manage.sys.controller.SysMenuController.save()', '{\"icon\":\"\",\"menuId\":\"\",\"name\":\"c1\",\"orderNum\":0,\"parentId\":\"bb6ce871-3ada-40bf-8545-aa6ab458d97a\",\"parentName\":\"cc\",\"perms\":\"\",\"type\":1,\"url\":\"2\"}', '127.0.0.1', '2018-01-09 11:28:19');
INSERT INTO `sys_log` VALUES ('6f680344-647b-4ebb-8672-b5982bb30d90', 'admin', '修改角色', 'com.lcz.manage.sys.controller.SysRoleController.update()', '{\"menuIdList\":[\"7\",\"6\"],\"remark\":\"1\",\"roleId\":\"0798fa1c-3a04-4d6a-9778-ef2bad11f738\",\"roleName\":\"1\"}', '127.0.0.1', '2018-01-09 13:28:35');
INSERT INTO `sys_log` VALUES ('6ff6ffa8-8268-4036-9959-9c73595a0eca', 'admin', '修改角色', 'com.lcz.manage.base.controller.SysRoleController.update()', '{\"menuIdList\":[\"1\",\"2\",\"15\",\"3\",\"19\",\"4\",\"23\"],\"remark\":\"只拥有查看权限\",\"roleId\":\"2\",\"roleName\":\"普通用户\"}', '127.0.0.1', '2017-12-28 09:41:50');
INSERT INTO `sys_log` VALUES ('70cfe7af-ff02-4b12-8032-db7b72b648f4', 'admin', '暂停定时任务', 'com.lcz.manage.sys.controller.ScheduleJobController.pause()', '[1,2]', '127.0.0.1', '2018-01-28 14:00:08');
INSERT INTO `sys_log` VALUES ('7111da0d-0b7d-4515-bc7f-f9eaf2057ce4', 'admin', '保存用户', 'com.lcz.manage.base.controller.SysUserController.save()', '{}', '127.0.0.1', '2017-12-25 16:29:31');
INSERT INTO `sys_log` VALUES ('72020375-753b-4622-9bc1-e51d200e1329', 'admin', '修改菜单', 'com.lcz.manage.sys.controller.SysMenuController.update()', '{\"icon\":\"\",\"menuId\":\"2874aee5-5420-4bc3-b8e0-e9fde18ae255\",\"name\":\"cc\",\"orderNum\":2,\"parentId\":\"0\",\"parentName\":\"一级菜单\",\"perms\":\"\",\"type\":0,\"url\":\"\"}', '127.0.0.1', '2018-01-09 13:30:22');
INSERT INTO `sys_log` VALUES ('73e68c6d-925d-4c63-aa95-fa2c3bbb70e0', 'admin', '删除用户', 'com.lcz.manage.base.controller.SysUserController.deleteBatch()', '[\"2\"]', '127.0.0.1', '2017-12-25 15:39:36');
INSERT INTO `sys_log` VALUES ('75341313-e499-44f2-be1f-491805de5fa5', 'admin', '保存用户', 'com.lcz.manage.base.controller.SysUserController.save()', '{\"email\":\"jarryok@gmail.com\",\"mobile\":\"15367668799\",\"password\":\"123\",\"status\":1,\"userId\":\"\",\"username\":\"jarry\"}', '127.0.0.1', '2017-12-26 08:44:41');
INSERT INTO `sys_log` VALUES ('755608da-5d34-436c-a349-2ed94282aefe', 'admin', '保存用户', 'com.lcz.manage.base.controller.SysUserController.save()', '{}', '127.0.0.1', '2017-12-25 16:27:46');
INSERT INTO `sys_log` VALUES ('76fecb85-aab1-4cb0-8673-d0fb8392cc4f', 'admin', '保存用户', 'com.lcz.manage.sys.controller.SysUserController.save()', '{\"createUserId\":\"\",\"deptId\":\"2\",\"deptName\":\"长沙分公司\",\"email\":\"10776@xiaoming.com\",\"mobile\":\"13345336277\",\"roleIdList\":[\"34fc27bc-2ec1-43b0-b9c7-6cf44f441fc8\"],\"status\":1,\"userId\":\"\",\"username\":\"小明\"}', '127.0.0.1', '2018-01-28 13:44:43');
INSERT INTO `sys_log` VALUES ('7759d7e2-d17f-4ae5-8b6a-e38634c4382c', 'admin', '修改用户', 'com.lcz.manage.sys.controller.SysUserController.update()', '{\"createUserId\":\"1\",\"email\":\"ss@qq.com\",\"mobile\":\"13367115166\",\"roleIdList\":[\"d15422bd-780b-45dc-a67e-57c804cb35f1\"],\"status\":0,\"userId\":\"ced646ad-733f-4446-8562-97bf019ca564\",\"username\":\"cc\"}', '127.0.0.1', '2018-01-09 13:28:11');
INSERT INTO `sys_log` VALUES ('77d1c2e6-cca4-45e8-b514-08d8a7cbebf0', 'admin', '删除用户', 'com.lcz.manage.sys.controller.SysUserController.deleteBatch()', '[\"381d6d76-dfff-411d-871a-c5d76c5d63ef\"]', '127.0.0.1', '2018-01-09 11:03:50');
INSERT INTO `sys_log` VALUES ('7a3a1b49-f5c0-4f74-ab14-a9d15d0da5b4', 'admin', '修改用户', 'com.lcz.manage.base.controller.SysUserController.update()', '{\"email\":\"jarrys@gmail.com\",\"mobile\":\"15367668711\",\"password\":\"\",\"roleIdList\":[\"on\",\"1\",\"2\"],\"status\":0,\"userId\":\"3\",\"username\":\"jarrys\"}', '127.0.0.1', '2017-12-27 13:32:16');
INSERT INTO `sys_log` VALUES ('7c31481c-5afa-4180-9841-ddecdeb65681', 'admin', '保存用户', 'com.lcz.manage.base.controller.SysUserController.save()', '{\"email\":\"1\",\"mobile\":\"1\",\"password\":\"1\",\"status\":1,\"userId\":\"\",\"username\":\"1\"}', '127.0.0.1', '2017-12-26 08:37:36');
INSERT INTO `sys_log` VALUES ('7c984b08-71a7-479d-a05e-0330bec88a38', 'admin', '修改菜单', 'com.lcz.manage.sys.controller.SysMenuController.update()', '{\"icon\":\"\",\"menuId\":\"f3c0c085-0701-484a-b96f-4c3d963729b1\",\"name\":\"查询\",\"orderNum\":0,\"parentId\":\"2986b3ad-3de9-4422-9927-8d8eed7964dd\",\"parentName\":\"首页\",\"perms\":\"index:query\",\"type\":2,\"url\":\"\"}', '127.0.0.1', '2018-01-28 13:39:34');
INSERT INTO `sys_log` VALUES ('7d5d927c-ae9c-43cb-ad60-0141eb24bfdd', 'admin', '保存菜单', 'com.lcz.manage.sys.controller.SysMenuController.save()', '{\"icon\":\"\",\"menuId\":\"\",\"name\":\"新增\",\"orderNum\":0,\"parentId\":\"53567e9c-00b7-48df-b2ab-611543a4250d\",\"parentName\":\"c1\",\"perms\":\"c1:save\",\"type\":2,\"url\":\"\"}', '127.0.0.1', '2018-01-09 13:33:01');
INSERT INTO `sys_log` VALUES ('7e986437-9ec0-4c9d-86e8-577018d4d442', 'admin', '保存菜单', 'com.lcz.manage.sys.controller.SysMenuController.save()', '{\"icon\":\"\",\"menuId\":\"\",\"name\":\"查询\",\"orderNum\":0,\"parentId\":\"2986b3ad-3de9-4422-9927-8d8eed7964dd\",\"parentName\":\"首页\",\"perms\":\"\",\"type\":2,\"url\":\"\"}', '127.0.0.1', '2018-01-28 13:39:13');
INSERT INTO `sys_log` VALUES ('80a47afb-f4d8-4a55-92e7-a5228191253d', 'admin', '保存菜单', 'com.lcz.manage.sys.controller.SysMenuController.save()', '{\"icon\":\"\",\"menuId\":\"\",\"name\":\"c1\",\"orderNum\":0,\"parentId\":\"27510a5b98564b948535cf71f4a9bd9a\",\"parentName\":\"cc\",\"perms\":\"\",\"type\":1,\"url\":\"/sys/log\"}', '127.0.0.1', '2018-01-09 11:21:16');
INSERT INTO `sys_log` VALUES ('81a8cc44-d854-400a-b8dc-4cbd36d6f385', 'admin', '修改用户', 'com.lcz.manage.base.controller.SysUserController.update()', '{\"createUserId\":\"1\",\"email\":\"jarrys@gmail.com\",\"mobile\":\"15367668711\",\"password\":\"\",\"roleIdList\":[\"2\"],\"status\":1,\"userId\":\"3\",\"username\":\"jarrys\"}', '127.0.0.1', '2017-12-28 16:26:54');
INSERT INTO `sys_log` VALUES ('875a417e-6a89-4e19-bf9b-c96afc2536fc', 'admin', '保存菜单', 'com.lcz.manage.sys.controller.SysMenuController.save()', '{\"icon\":\"\",\"menuId\":\"\",\"name\":\"cc\",\"orderNum\":2,\"parentId\":\"0\",\"parentName\":\"一级菜单\",\"perms\":\"\",\"type\":7,\"url\":\"\"}', '127.0.0.1', '2018-01-09 11:11:17');
INSERT INTO `sys_log` VALUES ('8a19db03-d8f3-4099-a942-5d56bf59a64b', 'admin', '修改角色', 'com.lcz.manage.base.controller.SysRoleController.update()', '{\"menuIdList\":[\"1\",\"2\",\"21\",\"22\",\"23\",\"24\",\"3\",\"31\",\"32\",\"33\",\"34\",\"4\",\"41\",\"42\",\"43\",\"44\",\"5\",\"6\",\"7\"],\"remark\":\"此管理员拥有最高权限\",\"roleId\":\"1\",\"roleName\":\"超级管理员\"}', '127.0.0.1', '2017-12-30 16:30:09');
INSERT INTO `sys_log` VALUES ('8f197eb6-9f9f-4249-a7ee-19b164deb8e9', 'admin', '修改菜单', 'com.lcz.manage.sys.controller.SysMenuController.update()', '{\"icon\":\"\",\"menuId\":\"2874aee5-5420-4bc3-b8e0-e9fde18ae255\",\"name\":\"cc\",\"orderNum\":2,\"parentId\":\"0\",\"parentName\":\"一级菜单\",\"perms\":\"\",\"type\":0,\"url\":\"\"}', '127.0.0.1', '2018-01-09 13:30:43');
INSERT INTO `sys_log` VALUES ('8f6b206d-50af-45ed-a181-492801e086ae', 'admin', '删除用户', 'com.lcz.manage.base.controller.SysUserController.deleteBatch()', '[\"2\"]', '127.0.0.1', '2017-12-25 15:38:28');
INSERT INTO `sys_log` VALUES ('9086562d-24cc-4146-9c2e-2a042df17ddd', 'admin', '修改用户', 'com.lcz.manage.sys.controller.SysUserController.update()', '{\"createUserId\":\"1\",\"deptId\":\"a3d71c3c-a2c0-42de-a459-cda3e552e5e0\",\"deptName\":\"市场部\",\"email\":\"sophia@beautiful.com\",\"mobile\":\"01064887524\",\"roleIdList\":[\"d15422bd-780b-45dc-a67e-57c804cb35f1\"],\"status\":0,\"userId\":\"381d6d76-dfff-411d-871a-c5d76c5d63ef\",\"username\":\"sophia\"}', '127.0.0.1', '2018-01-27 21:31:10');
INSERT INTO `sys_log` VALUES ('909bbb4f-781d-47a3-b7be-0897de84b8a6', 'admin', '立即执行任务', 'com.lcz.manage.sys.controller.ScheduleJobController.run()', '[1]', '127.0.0.1', '2018-02-01 20:53:00');
INSERT INTO `sys_log` VALUES ('91d226c9-b13c-488a-9146-a9f6d52d1438', 'admin', '删除菜单', 'com.lcz.manage.sys.controller.SysMenuController.delete()', '\"2874aee5-5420-4bc3-b8e0-e9fde18ae255\"', '127.0.0.1', '2018-01-09 13:33:30');
INSERT INTO `sys_log` VALUES ('92329012-08d0-4017-b1bc-361bb6a49055', 'admin', '立即执行任务', 'com.lcz.manage.sys.controller.ScheduleJobController.run()', '[1]', '127.0.0.1', '2018-01-22 10:59:09');
INSERT INTO `sys_log` VALUES ('95765a88-a335-46e4-a3c3-1b703b8b1ab0', 'admin', '修改角色', 'com.lcz.manage.base.controller.SysRoleController.update()', '{\"menuIdList\":[\"1\",\"2\",\"15\",\"16\",\"17\",\"18\"],\"remark\":\"只拥有管理员列表管理的权限\",\"roleId\":\"3\",\"roleName\":\"用户管理员\"}', '127.0.0.1', '2017-12-28 09:41:02');
INSERT INTO `sys_log` VALUES ('970ee8db-0865-46d2-b6f9-9dd4741ca1a8', 'admin', '立即执行任务', 'com.lcz.manage.sys.controller.ScheduleJobController.run()', '[1]', '127.0.0.1', '2018-01-22 10:50:31');
INSERT INTO `sys_log` VALUES ('97f05a8b-b6b8-46f5-8663-a91c805291c3', 'admin', '立即执行任务', 'com.lcz.manage.sys.controller.ScheduleJobController.run()', '[1]', '127.0.0.1', '2018-01-28 13:55:24');
INSERT INTO `sys_log` VALUES ('97f89590-5126-4e2f-9e1c-a26392aaf660', 'admin', '暂停定时任务', 'com.lcz.manage.sys.controller.ScheduleJobController.pause()', '[1,2]', '127.0.0.1', '2018-02-07 09:45:25');
INSERT INTO `sys_log` VALUES ('982b9c3d-0c67-4e8c-a52c-14c0b872b318', 'admin', '保存部门', 'com.lcz.manage.sys.controller.SysDeptController.save()', '{\"deptId\":\"\",\"name\":\"测试-前端\",\"orderNum\":1,\"parentId\":\"6\",\"parentName\":\"测试部\"}', '127.0.0.1', '2018-01-28 13:40:32');
INSERT INTO `sys_log` VALUES ('98524c85-e643-49c8-8472-eac8f79a603e', 'admin', '修改角色', 'com.lcz.manage.base.controller.SysRoleController.update()', '{\"menuIdList\":[\"1\",\"2\",\"15\"],\"remark\":\"测试用户的权限2\",\"roleId\":\"3\",\"roleName\":\"测试用户2\"}', '127.0.0.1', '2017-12-27 19:24:37');
INSERT INTO `sys_log` VALUES ('99112ecf-9ede-41a9-9cb9-c27ae6f37496', 'admin', '删除菜单', 'com.lcz.manage.sys.controller.SysMenuController.delete()', '\"53567e9c-00b7-48df-b2ab-611543a4250d\"', '127.0.0.1', '2018-01-09 13:33:18');
INSERT INTO `sys_log` VALUES ('9a1d6d90-2523-4af8-8143-90afd56dce19', 'admin', '保存角色', 'com.lcz.manage.sys.controller.SysRoleController.save()', '{\"menuIdList\":[\"1\",\"2\",\"21\"],\"remark\":\"用户查看\",\"roleId\":\"\",\"roleName\":\"用户查看\"}', '127.0.0.1', '2018-01-28 13:37:07');
INSERT INTO `sys_log` VALUES ('9bf1db90-88bb-47b8-8fc1-f784ad81e6be', 'admin', '修改角色', 'com.lcz.manage.base.controller.SysRoleController.update()', '{\"menuIdList\":[\"1\",\"2\",\"15\",\"16\",\"17\",\"18\"],\"remark\":\"测试用户的权限2\",\"roleId\":\"3\",\"roleName\":\"测试用户2\"}', '127.0.0.1', '2017-12-27 21:10:47');
INSERT INTO `sys_log` VALUES ('9ce96229-3d93-4de8-a3e4-7769712edb22', 'admin', '保存配置', 'com.lcz.manage.sys.controller.SysConfigController.save()', '{\"id\":\"\",\"key\":\"LOST_WISDOM\",\"remark\":\"失了智\",\"status\":0,\"value\":\"{\\\"id\\\":\\\"1\\\",\\\"name\\\":\\\"lost_wisdom\\\",\\\"remark\\\":\\\"失了智\\\"}\\t\"}', '127.0.0.1', '2018-01-07 15:43:46');
INSERT INTO `sys_log` VALUES ('9e51546e-af77-406a-8c23-446254d4f331', 'admin', '恢复定时任务', 'com.lcz.manage.sys.controller.ScheduleJobController.resume()', '[1]', '127.0.0.1', '2018-01-28 14:00:19');
INSERT INTO `sys_log` VALUES ('a0ec1033-fffd-4ca9-a378-c5604afb10f4', 'admin', '修改用户', 'com.lcz.manage.base.controller.SysUserController.update()', '{\"email\":\"jarryok@gmail.com\",\"mobile\":\"15367668799\",\"password\":\"123\",\"status\":1,\"userId\":\"3698a1be-d9aa-41e7-880f-6e9a0f44a3ff\",\"username\":\"jarry\"}', '127.0.0.1', '2017-12-26 08:46:59');
INSERT INTO `sys_log` VALUES ('a4b7310c-8ae6-4fba-8103-21fc4450d8f1', 'admin', '恢复定时任务', 'com.lcz.manage.sys.controller.ScheduleJobController.resume()', '[1]', '127.0.0.1', '2018-01-22 11:00:19');
INSERT INTO `sys_log` VALUES ('a5fa2ca8-035d-4655-9050-f73f8735ccf8', 'admin', '修改角色', 'com.lcz.manage.sys.controller.SysRoleController.update()', '{\"menuIdList\":[\"7\",\"5\",\"6\"],\"remark\":\"查看系统日志、sql监控。\",\"roleId\":\"d15422bd-780b-45dc-a67e-57c804cb35f1\",\"roleName\":\"系统管理员\"}', '127.0.0.1', '2018-01-04 21:40:22');
INSERT INTO `sys_log` VALUES ('a64750cb-001c-47f4-85cd-2c40e858be6f', 'admin', '删除配置', 'com.lcz.manage.sys.controller.SysConfigController.delete()', '[\"d4285677-467b-49be-ac88-ea0decfaf3cb\"]', '127.0.0.1', '2018-01-09 13:34:01');
INSERT INTO `sys_log` VALUES ('a66f3463-a0d1-486c-8ffd-22a77cfeee22', 'admin', '修改用户', 'com.lcz.manage.sys.controller.SysUserController.update()', '{\"createUserId\":\"1\",\"deptId\":\"2\",\"deptName\":\"长沙分公司\",\"email\":\"10776@xiaoming.com\",\"mobile\":\"13345336277\",\"roleIdList\":[\"34fc27bc-2ec1-43b0-b9c7-6cf44f441fc8\"],\"status\":0,\"userId\":\"5d4e7f35-7190-4e14-8655-5e387afb8815\",\"username\":\"小明\"}', '127.0.0.1', '2018-01-28 13:45:11');
INSERT INTO `sys_log` VALUES ('a872e21c-c478-44ef-9237-49551c8f50c1', 'admin', '立即执行任务', 'com.lcz.manage.sys.controller.ScheduleJobController.run()', '[1]', '127.0.0.1', '2018-01-22 10:59:39');
INSERT INTO `sys_log` VALUES ('aa0381e8-134a-4f40-84b3-dec250c063e3', 'admin', '暂停定时任务', 'com.lcz.manage.sys.controller.ScheduleJobController.pause()', '[1,2]', '127.0.0.1', '2018-01-27 22:52:52');
INSERT INTO `sys_log` VALUES ('ad1a2e56-da4f-48ac-9cd0-46fe4cda4504', 'admin', '删除角色', 'com.lcz.manage.sys.controller.SysRoleController.delete()', '[\"0798fa1c-3a04-4d6a-9778-ef2bad11f738\"]', '127.0.0.1', '2018-01-09 13:28:41');
INSERT INTO `sys_log` VALUES ('ad23b150-0038-407c-a5b4-95a4fadd6fe9', 'admin', '修改用户', 'com.lcz.manage.sys.controller.SysUserController.update()', '{\"createUserId\":\"1\",\"email\":\"Sophia@gmail.com\",\"mobile\":\"01074663211\",\"roleIdList\":[\"d15422bd-780b-45dc-a67e-57c804cb35f1\"],\"status\":1,\"userId\":\"381d6d76-dfff-411d-871a-c5d76c5d63ef\",\"username\":\"Sophia\"}', '127.0.0.1', '2018-01-04 21:42:15');
INSERT INTO `sys_log` VALUES ('b053600d-2914-4530-a28d-9cb221292eea', 'admin', '立即执行任务', 'com.lcz.manage.sys.controller.ScheduleJobController.run()', '[1,2]', '127.0.0.1', '2018-01-28 13:59:10');
INSERT INTO `sys_log` VALUES ('b1127ed7-ed9f-4ec3-b8c0-9f36787db69a', 'admin', '修改角色', 'com.lcz.manage.base.controller.SysRoleController.update()', '{\"menuIdList\":[\"1\",\"2\",\"21\",\"22\",\"23\",\"24\"],\"remark\":\"只拥有管理员列表管理的权限\",\"roleId\":\"3\",\"roleName\":\"用户管理员\"}', '127.0.0.1', '2017-12-30 16:03:01');
INSERT INTO `sys_log` VALUES ('b1549a12-16fa-4360-9555-a65ea768f069', 'admin', '修改密码', 'com.lcz.manage.sys.controller.SysUserController.password()', '\"1231\"', '127.0.0.1', '2018-01-16 09:07:06');
INSERT INTO `sys_log` VALUES ('b15f6ca5-4bf4-42a2-b8e7-530a58e089d7', 'admin', '修改角色', 'com.lcz.manage.base.controller.SysRoleController.update()', '{\"menuIdList\":[\"1\",\"2\",\"15\",\"16\",\"17\",\"18\"],\"remark\":\"测试用户的权限2\",\"roleId\":\"3\",\"roleName\":\"测试用户2\"}', '127.0.0.1', '2017-12-27 19:15:44');
INSERT INTO `sys_log` VALUES ('b19e9244-adbd-49b9-bd05-114342125caa', 'admin', '保存菜单', 'com.lcz.manage.sys.controller.SysMenuController.save()', '{\"icon\":\"\",\"menuId\":\"\",\"name\":\"cc\",\"orderNum\":32,\"parentId\":\"0\",\"parentName\":\"一级菜单\",\"perms\":\"\",\"type\":0,\"url\":\"\"}', '127.0.0.1', '2018-01-09 13:09:30');
INSERT INTO `sys_log` VALUES ('b19f6c45-7125-46b4-8e0d-ec9be4fdaa84', 'admin', '保存角色', 'com.lcz.manage.base.controller.SysRoleController.save()', '{\"menuIdList\":[\"1\",\"2\",\"15\",\"3\",\"19\"],\"remark\":\"m1\",\"roleId\":\"\",\"roleName\":\"member1\"}', '127.0.0.1', '2017-12-28 09:32:32');
INSERT INTO `sys_log` VALUES ('b3cd9ec6-0b13-4db9-936b-c731db5ee9d1', 'admin', '修改角色', 'com.lcz.manage.base.controller.SysRoleController.update()', '{\"menuIdList\":[\"1\",\"4\",\"26\"],\"remark\":\"测试用户的权限22\",\"roleId\":\"3\",\"roleName\":\"测试用户22\"}', '127.0.0.1', '2017-12-27 19:21:29');
INSERT INTO `sys_log` VALUES ('b4ea4dec-16a0-4355-ae25-52011893a8b0', 'admin', '批量删除用户', 'com.lcz.manage.sys.controller.SysUserController.deleteBatch()', '[\"ced646ad-733f-4446-8562-97bf019ca564\"]', '127.0.0.1', '2018-01-09 13:28:20');
INSERT INTO `sys_log` VALUES ('b8985dc8-7ba0-45a7-8807-8c462196a866', 'admin', '删除菜单', 'com.lcz.manage.sys.controller.SysMenuController.delete()', '\"0f3a5ee1-58b5-4ec3-966b-6c49d5eef45e\"', '127.0.0.1', '2018-02-07 09:46:17');
INSERT INTO `sys_log` VALUES ('b998cc3c-def3-40aa-8a6c-e1a164900270', 'admin', '暂停定时任务', 'com.lcz.manage.sys.controller.ScheduleJobController.pause()', '[1]', '127.0.0.1', '2018-01-28 13:58:38');
INSERT INTO `sys_log` VALUES ('bc410f28-863f-4a24-a6cd-b0e522b5fbf1', 'admin', '保存配置', 'com.lcz.manage.sys.controller.SysConfigController.save()', '{\"id\":\"\",\"key\":\"area\",\"remark\":\"省市县\",\"status\":1,\"value\":\"province\\\\cities\\\\counties\"}', '127.0.0.1', '2018-01-28 13:42:17');
INSERT INTO `sys_log` VALUES ('bd41de90-585b-496b-a7cf-95a4838ea4da', 'admin', '修改角色', 'com.lcz.manage.base.controller.SysRoleController.update()', '{\"menuIdList\":[\"1\",\"2\",\"21\",\"3\",\"31\",\"4\",\"41\",\"7\",\"5\",\"6\"],\"remark\":\"只拥有查看权限\",\"roleId\":\"2\",\"roleName\":\"普通用户\"}', '127.0.0.1', '2017-12-31 21:24:07');
INSERT INTO `sys_log` VALUES ('c3555d5b-b51c-4bb2-80f4-599d17b80b7d', 'admin', '修改用户', 'com.lcz.manage.base.controller.SysUserController.update()', '{\"createUserId\":\"1\",\"email\":\"jarrys@gmail.com\",\"mobile\":\"15367668711\",\"password\":\"\",\"roleIdList\":[\"on\",\"2\"],\"status\":0,\"userId\":\"3\",\"username\":\"jarrys\"}', '127.0.0.1', '2017-12-28 16:19:42');
INSERT INTO `sys_log` VALUES ('c6583541-c83e-407a-bc7f-f9730ae1c004', 'admin', '删除菜单', 'com.lcz.manage.base.controller.SysMenuController.delete()', '\"10\"', '127.0.0.1', '2018-01-02 14:32:44');
INSERT INTO `sys_log` VALUES ('c6bfba97-bd4b-47f4-9a66-299560d907f4', 'admin', '修改用户', 'com.lcz.manage.base.controller.SysUserController.update()', '{\"createUserId\":\"1\",\"email\":\"jarry@qq.com\",\"mobile\":\"13577487265\",\"roleIdList\":[\"3\"],\"status\":1,\"userId\":\"3\",\"username\":\"jarry\"}', '127.0.0.1', '2017-12-29 15:03:46');
INSERT INTO `sys_log` VALUES ('cc522390-87ce-458b-928d-995e2f23b568', 'admin', '修改用户', 'com.lcz.manage.base.controller.SysUserController.update()', '{\"createUserId\":\"1\",\"email\":\"\",\"mobile\":\"\",\"password\":\"\",\"roleIdList\":[],\"status\":1,\"userId\":\"3\",\"username\":\"\"}', '127.0.0.1', '2017-12-28 16:31:00');
INSERT INTO `sys_log` VALUES ('ccda3622-14db-4c42-8004-5b5e8d8abf2d', 'admin', '修改角色', 'com.lcz.manage.base.controller.SysRoleController.update()', '{\"menuIdList\":[\"1\",\"2\",\"21\",\"22\",\"23\",\"24\",\"3\",\"31\",\"32\",\"33\",\"34\",\"4\",\"41\",\"42\",\"43\",\"44\",\"5\",\"6\",\"69f0cfdc-11fd-43d2-b699-06fc7ab76a36\",\"54a9b95c-c214-4385-aab8-10e2588e3eab\"],\"remark\":\"此管理员拥有最高权限\",\"roleId\":\"1\",\"roleName\":\"超级管理员\"}', '127.0.0.1', '2017-12-31 11:09:05');
INSERT INTO `sys_log` VALUES ('ce540e2b-6e9a-4d65-abb7-d6d4202af207', 'admin', '立即执行任务', 'com.lcz.manage.sys.controller.ScheduleJobController.run()', '[1]', '127.0.0.1', '2018-01-28 13:58:42');
INSERT INTO `sys_log` VALUES ('d1d88a83-77f1-45d8-b803-c262ce2d071c', 'admin', '保存菜单', 'com.lcz.manage.sys.controller.SysMenuController.save()', '{\"icon\":\"\",\"menuId\":\"\",\"name\":\"cc\",\"orderNum\":32,\"parentId\":\"0\",\"parentName\":\"一级菜单\",\"perms\":\"\",\"type\":7,\"url\":\"\"}', '127.0.0.1', '2018-01-09 13:07:30');
INSERT INTO `sys_log` VALUES ('d38d1488-acec-4b58-a54e-fc7863b6953f', 'admin', '修改角色', 'com.lcz.manage.base.controller.SysRoleController.update()', '{\"menuIdList\":[\"1\",\"2\",\"21\",\"22\",\"23\",\"24\",\"3\",\"31\",\"32\",\"33\",\"34\",\"4\",\"41\",\"42\",\"43\",\"44\",\"5\",\"6\",\"7\",\"bc9dd436-5239-423c-99ec-a44253b8668b\",\"740c93c7-a814-4f75-a323-e6481e44f272\"],\"remark\":\"此管理员拥有最高权限\",\"roleId\":\"1\",\"roleName\":\"超级管理员\"}', '127.0.0.1', '2017-12-31 10:53:14');
INSERT INTO `sys_log` VALUES ('d3a311c8-f7f2-43d7-bbba-47fbac774391', 'admin', '修改角色', 'com.lcz.manage.base.controller.SysRoleController.update()', '{\"menuIdList\":[\"1\",\"2\",\"15\",\"16\"],\"remark\":\"测试用户的权限\",\"roleId\":\"3\",\"roleName\":\"测试用户\"}', '127.0.0.1', '2017-12-28 09:34:53');
INSERT INTO `sys_log` VALUES ('d6c4aabd-c34f-4dcb-a246-9fe9890c6ccb', 'admin', '保存角色', 'com.lcz.manage.base.controller.SysRoleController.save()', '{\"menuIdList\":[\"1\",\"2\",\"15\",\"16\",\"17\",\"18\"],\"remark\":\"测试用户的权限2\",\"roleId\":\"\",\"roleName\":\"测试用户2\"}', '127.0.0.1', '2017-12-27 18:54:43');
INSERT INTO `sys_log` VALUES ('d74e5413-085c-4ed4-8e37-3488aefc95e0', 'admin', '删除菜单', 'com.lcz.manage.sys.controller.SysMenuController.delete()', '\"2986b3ad-3de9-4422-9927-8d8eed7964dd\"', '127.0.0.1', '2018-02-07 09:46:12');
INSERT INTO `sys_log` VALUES ('d81718b1-37ff-4349-96dc-8c598e6be46f', 'admin', '修改配置', 'com.lcz.manage.sys.controller.SysConfigController.update()', '{\"id\":\"2\",\"key\":\"LOST_WISDOM\",\"remark\":\"失了智\",\"status\":1,\"value\":\"{\\\"id\\\":\\\"1\\\",\\\"name\\\":\\\"lost_wisdom\\\",\\\"remark\\\":\\\"失了智\\\"}\\t\"}', '127.0.0.1', '2018-01-07 15:44:10');
INSERT INTO `sys_log` VALUES ('dd16cb5b-4042-44d0-b428-dd11fb917872', 'admin', '删除用户', 'com.lcz.manage.base.controller.SysUserController.deleteBatch()', '[\"2\"]', '127.0.0.1', '2017-12-25 15:40:40');
INSERT INTO `sys_log` VALUES ('dd7c419a-6a6c-4710-9326-bda938ac7683', 'admin', '保存用户', 'com.lcz.manage.sys.controller.SysUserController.save()', '{\"createUserId\":\"\",\"email\":\"herry@cc.com\",\"mobile\":\"13655437717\",\"roleIdList\":[\"3\"],\"status\":1,\"userId\":\"\",\"username\":\"herry\"}', '127.0.0.1', '2018-01-09 11:04:37');
INSERT INTO `sys_log` VALUES ('e0e4c9ee-13a5-4fc6-b848-7d35ce4b073e', 'admin', '修改密码', 'com.lcz.manage.sys.controller.SysUserController.password()', '\"1\"', '127.0.0.1', '2018-01-16 09:06:28');
INSERT INTO `sys_log` VALUES ('e12e1316-92bb-4e41-96de-c24f9483f196', 'admin', '保存菜单', 'com.lcz.manage.base.controller.SysMenuController.save()', '{\"icon\":\"fa fa-cc\",\"menuId\":\"\",\"name\":\"关于\",\"orderNum\":1,\"parentId\":\"0\",\"parentName\":\"一级菜单\",\"perms\":\"\",\"type\":0,\"url\":\"\"}', '127.0.0.1', '2017-12-31 11:08:12');
INSERT INTO `sys_log` VALUES ('e46e4d7f-6990-429c-9cac-4b709db72bb1', 'admin', '保存菜单', 'com.lcz.manage.sys.controller.SysMenuController.save()', '{\"icon\":\"\",\"menuId\":\"\",\"name\":\"首页\",\"orderNum\":0,\"parentId\":\"0f3a5ee1-58b5-4ec3-966b-6c49d5eef45e\",\"parentName\":\"百度\",\"perms\":\"\",\"type\":1,\"url\":\"/index\"}', '127.0.0.1', '2018-01-28 13:38:17');
INSERT INTO `sys_log` VALUES ('e76b9ef2-dedd-49e5-8a60-35ac6589e56c', 'admin', '删除菜单', 'com.lcz.manage.base.controller.SysMenuController.delete()', '\"10\"', '127.0.0.1', '2018-01-02 14:35:40');
INSERT INTO `sys_log` VALUES ('e77cc3a2-2ffb-4755-8c7e-d9065095448e', 'admin', '保存用户', 'com.lcz.manage.base.controller.SysUserController.save()', '{}', '127.0.0.1', '2017-12-25 16:44:28');
INSERT INTO `sys_log` VALUES ('eb4ecf36-1613-4e0a-a586-2d7c3f4b4e02', 'admin', '保存菜单', 'com.lcz.manage.sys.controller.SysMenuController.save()', '{\"icon\":\"\",\"menuId\":\"\",\"name\":\"c1\",\"orderNum\":0,\"parentId\":\"e72fbe20-349f-4ccd-9199-e1e36aa33133\",\"parentName\":\"cc\",\"perms\":\"\",\"type\":1,\"url\":\"/sys/log\"}', '127.0.0.1', '2018-01-09 11:24:49');
INSERT INTO `sys_log` VALUES ('eb8560d6-b528-4d85-85e7-1821e77e373b', 'admin', '保存菜单', 'com.lcz.manage.sys.controller.SysMenuController.save()', '{\"icon\":\"\",\"menuId\":\"\",\"name\":\"你猜猜啊\",\"orderNum\":3,\"parentId\":\"7\",\"parentName\":\"系统管理\",\"perms\":\"\",\"type\":0,\"url\":\"\"}', '127.0.0.1', '2018-01-09 11:07:06');
INSERT INTO `sys_log` VALUES ('ec61c11a-ad44-4dcb-a77d-56b8f4a27b17', 'admin', '修改用户', 'com.lcz.manage.base.controller.SysUserController.update()', '{\"createUserId\":\"1\",\"email\":\"jarry@qq.com\",\"mobile\":\"13577487265\",\"roleIdList\":[\"3\"],\"status\":1,\"userId\":\"3\",\"username\":\"jarry\"}', '127.0.0.1', '2017-12-28 16:41:17');
INSERT INTO `sys_log` VALUES ('ed8fb47f-50d9-4072-b5f5-dc57bd851723', 'admin', '修改用户', 'com.lcz.manage.sys.controller.SysUserController.update()', '{\"createUserId\":\"1\",\"deptId\":\"a3d71c3c-a2c0-42de-a459-cda3e552e5e0\",\"deptName\":\"市场\",\"email\":\"sophia@beautiful.com\",\"mobile\":\"01064887524\",\"roleIdList\":[\"d15422bd-780b-45dc-a67e-57c804cb35f1\"],\"status\":0,\"userId\":\"381d6d76-dfff-411d-871a-c5d76c5d63ef\",\"username\":\"sophia\"}', '127.0.0.1', '2018-01-26 23:23:18');
INSERT INTO `sys_log` VALUES ('f049ebfa-0c04-42be-ab96-2aafcf82f340', 'admin', '修改用户', 'com.lcz.manage.base.controller.SysUserController.update()', '{\"createUserId\":\"1\",\"email\":\"jarry@qq.com\",\"mobile\":\"13577487265\",\"roleIdList\":[\"2\"],\"status\":1,\"userId\":\"3\",\"username\":\"jarry\"}', '127.0.0.1', '2017-12-29 15:03:28');
INSERT INTO `sys_log` VALUES ('f164cedc-9486-4067-9ba1-4c7b563fbd82', 'admin', '修改角色', 'com.lcz.manage.base.controller.SysRoleController.update()', '{\"menuIdList\":[\"1\",\"2\",\"21\",\"22\",\"23\",\"24\",\"3\",\"31\",\"32\",\"33\",\"34\",\"4\",\"41\",\"42\",\"43\",\"44\",\"7\",\"5\",\"6\"],\"remark\":\"此管理员拥有最高权限\",\"roleId\":\"1\",\"roleName\":\"超级管理员\"}', '127.0.0.1', '2017-12-31 21:23:55');
INSERT INTO `sys_log` VALUES ('f4acd94e-6023-4371-a98d-80ff93862c9e', 'admin', '修改角色', 'com.lcz.manage.base.controller.SysRoleController.update()', '{\"menuIdList\":[\"1\",\"2\",\"15\",\"3\",\"19\",\"4\",\"23\"],\"remark\":\"拥有一般权限\",\"roleId\":\"2\",\"roleName\":\"普通管理员\"}', '127.0.0.1', '2017-12-28 09:39:39');
INSERT INTO `sys_log` VALUES ('f9440f64-d540-49b3-9679-f3a459575806', 'admin', '修改角色', 'com.lcz.manage.base.controller.SysRoleController.update()', '{\"menuIdList\":[\"1\",\"2\",\"15\",\"3\",\"19\",\"4\",\"23\",\"6\",\"7\"],\"remark\":\"只拥有查看权限\",\"roleId\":\"2\",\"roleName\":\"普通用户\"}', '127.0.0.1', '2017-12-29 15:04:56');
INSERT INTO `sys_log` VALUES ('fa98c55d-92c6-4441-9e58-e6b21d278709', 'admin', '暂停定时任务', 'com.lcz.manage.sys.controller.ScheduleJobController.pause()', '[1,2]', '127.0.0.1', '2018-01-28 13:56:52');
INSERT INTO `sys_log` VALUES ('fb6e447f-8cd0-4a0f-ba57-8b95428dbd5f', 'admin', '立即执行任务', 'com.lcz.manage.sys.controller.ScheduleJobController.run()', '[1]', '127.0.0.1', '2018-01-22 10:20:34');
INSERT INTO `sys_log` VALUES ('fc800ead-81c0-43e2-904e-9144f9a7f802', 'admin', '修改用户', 'com.lcz.manage.sys.controller.SysUserController.update()', '{\"createUserId\":\"1\",\"email\":\"Sophia@gmail.com\",\"mobile\":\"01074663211\",\"roleIdList\":[\"2\",\"d15422bd-780b-45dc-a67e-57c804cb35f1\"],\"status\":1,\"userId\":\"381d6d76-dfff-411d-871a-c5d76c5d63ef\",\"username\":\"Sophia\"}', '127.0.0.1', '2018-01-04 21:41:42');
INSERT INTO `sys_log` VALUES ('fdf64afa-576b-445c-a739-64c8d5e8d415', 'admin', '修改配置', 'com.lcz.manage.sys.controller.SysConfigController.update()', '{\"id\":\"9b7f4b64-322c-46c1-9879-c5cdd7d94624\",\"key\":\"area\",\"remark\":\"省市县\",\"status\":0,\"value\":\"province\\\\cities\\\\counties\"}', '127.0.0.1', '2018-01-28 13:42:28');
INSERT INTO `sys_log` VALUES ('ffcaf05d-1306-43ef-9bf1-2898a82d4c89', 'admin', '立即执行任务', 'com.lcz.manage.sys.controller.ScheduleJobController.run()', '[2]', '127.0.0.1', '2018-01-28 13:55:36');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` varchar(255) NOT NULL,
  `parent_id` varchar(255) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `is_base` int(11) DEFAULT '0' COMMENT '是否为系统菜单：1：是；0：否',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '基础管理', null, null, '0', '1', 'fa fa-cog', '0');
INSERT INTO `sys_menu` VALUES ('2', '1', '用户管理', '/sys/user/page', null, '1', '1', 'fa fa-user', '1');
INSERT INTO `sys_menu` VALUES ('21', '2', '查看', null, 'sys:user:list,sys:user:info', '2', '1', null, '0');
INSERT INTO `sys_menu` VALUES ('22', '2', '新增', null, 'sys:user:save,sys:role:select', '2', '1', null, '0');
INSERT INTO `sys_menu` VALUES ('23', '2', '修改', null, 'sys:user:update,sys:role:select', '2', '1', null, '0');
INSERT INTO `sys_menu` VALUES ('24', '2', '删除', null, 'sys:user:delete', '2', '1', null, '0');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', '/sys/role', null, '1', '1', 'fa fa-user-secret', '2');
INSERT INTO `sys_menu` VALUES ('31', '3', '查看', null, 'sys:role:list,sys:role:info', '2', '1', null, '0');
INSERT INTO `sys_menu` VALUES ('32', '3', '新增', null, 'sys:role:save,sys:menu:perms', '2', '1', null, '0');
INSERT INTO `sys_menu` VALUES ('33', '3', '修改', null, 'sys:role:update,sys:menu:perms', '2', '1', null, '0');
INSERT INTO `sys_menu` VALUES ('34', '3', '删除', null, 'sys:role:delete', '2', '1', null, '0');
INSERT INTO `sys_menu` VALUES ('3c37fcfa-e89e-4555-8098-da6a7bff052a', 'c77961d7-d049-4763-bfa9-b97ffeeec5ff', '新增', '', 'sys:schedule:save', '2', '1', null, '0');
INSERT INTO `sys_menu` VALUES ('3ca1e1c4-65de-42c0-9d7b-09e0b560ccb9', '733ad909-2733-463a-aba0-f13d6cf5cade', '删除', '', 'sys:dept:delete', '2', '1', null, '0');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', '/sys/menu', null, '1', '1', 'fa fa-navicon', '3');
INSERT INTO `sys_menu` VALUES ('41', '4', '查看', null, 'sys:menu:list,sys:menu:info', '2', '1', null, '0');
INSERT INTO `sys_menu` VALUES ('42', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', '1', null, '0');
INSERT INTO `sys_menu` VALUES ('43', '4', '修改', null, 'sys:menu:update,sys:menu:select', '2', '1', null, '0');
INSERT INTO `sys_menu` VALUES ('44', '4', '删除', null, 'sys:menu:delete', '2', '1', null, '0');
INSERT INTO `sys_menu` VALUES ('5', '7', '系统日志', '/sys/log', 'sys:log:list', '1', '1', 'fa fa-file-text-o', '1');
INSERT INTO `sys_menu` VALUES ('5dbf9237-e8e3-45cd-bc1f-a3230290689f', 'c77961d7-d049-4763-bfa9-b97ffeeec5ff', '暂停', '', 'sys:schedule:pause', '2', '1', null, '0');
INSERT INTO `sys_menu` VALUES ('6', '7', 'SQL监控', '/druid/sql.html', null, '1', '1', 'fa fa-bug', '2');
INSERT INTO `sys_menu` VALUES ('632f18e0-ce4b-43c5-8148-1df216c37299', 'c77961d7-d049-4763-bfa9-b97ffeeec5ff', '立即执行', '', 'sys:schedule:run', '2', '1', null, '0');
INSERT INTO `sys_menu` VALUES ('6b03c9d4-9f26-49c7-a0be-0f68bd707afb', 'c77961d7-d049-4763-bfa9-b97ffeeec5ff', '修改', '', 'sys:schedule:update', '2', '1', null, '0');
INSERT INTO `sys_menu` VALUES ('7', '0', '系统管理', null, null, '0', '1', 'fa fa-cogs', '1');
INSERT INTO `sys_menu` VALUES ('733ad909-2733-463a-aba0-f13d6cf5cade', '1', '部门管理', '/sys/dept', '', '1', '1', 'fa fa-file-code-o', '3');
INSERT INTO `sys_menu` VALUES ('7468c3a4-0df9-40c8-be3a-ec52d038614e', '733ad909-2733-463a-aba0-f13d6cf5cade', '查看', '', 'sys:dept:list,sys:dept:info', '2', '1', null, '0');
INSERT INTO `sys_menu` VALUES ('8', '1', '数据字典', '/sys/config', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', '1', '1', 'fa fa-book', '5');
INSERT INTO `sys_menu` VALUES ('9', '1', '文件上传', '/sys/oss', 'sys:oss:all', '1', '1', 'fa fa-file-image-o', '6');
INSERT INTO `sys_menu` VALUES ('a2552209-90e3-456b-984c-01e43d91e597', 'c77961d7-d049-4763-bfa9-b97ffeeec5ff', '删除', '', 'sys:schedule:delete', '2', '1', null, '0');
INSERT INTO `sys_menu` VALUES ('ac54df09-6f2f-4529-abf1-98efae1a96ef', 'c77961d7-d049-4763-bfa9-b97ffeeec5ff', '日志列表', '', 'sys:schedule:log', '2', '1', null, '0');
INSERT INTO `sys_menu` VALUES ('aeab4bf5-30f7-4724-94da-6baaa31d4a41', 'c77961d7-d049-4763-bfa9-b97ffeeec5ff', '查看', '', 'sys:schedule:list,sys:schedule:info', '2', '1', null, '0');
INSERT INTO `sys_menu` VALUES ('c2c6c1e3-b48a-409a-966f-08962e06433f', '733ad909-2733-463a-aba0-f13d6cf5cade', '新增', '', 'sys:dept:save,sys:dept:select', '2', '1', null, '0');
INSERT INTO `sys_menu` VALUES ('c77961d7-d049-4763-bfa9-b97ffeeec5ff', '1', '定时任务', '/sys/schedule', '', '1', '1', 'fa fa-tasks', '7');
INSERT INTO `sys_menu` VALUES ('e7b1951e-54ca-4c5f-851c-d204507c31a3', '733ad909-2733-463a-aba0-f13d6cf5cade', '修改', '', 'sys:dept:update,sys:dept:select', '2', '1', null, '0');
INSERT INTO `sys_menu` VALUES ('f02fbbe1-d390-483b-88a9-f82498087df5', 'c77961d7-d049-4763-bfa9-b97ffeeec5ff', '恢复', '', 'sys:schedule:resume', '2', '1', null, '0');

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss` (
  `id` varchar(255) NOT NULL,
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件上传';

-- ----------------------------
-- Records of sys_oss
-- ----------------------------
INSERT INTO `sys_oss` VALUES ('8f9407d8-a3a9-40a8-927c-3f30e1f887fa', 'http://p20dkvms1.bkt.clouddn.com/upload/20180128/3332190f25aa4afcb3230f9967a571c4', '2018-01-28 13:43:23');
INSERT INTO `sys_oss` VALUES ('91f031ff-1185-41ea-95d2-1e4d0794f7a2', 'http://p20dkvms1.bkt.clouddn.com/upload/20180104/827643cd8fbd40f0a461904bf0f20c69', '2018-01-04 21:43:37');
INSERT INTO `sys_oss` VALUES ('e9262db1-e93b-4bf9-90bc-d3d43508e641', 'http://p20dkvms1.bkt.clouddn.com/upload/20180104/ff4c4908d71c4915a51bdf0940e88db6', '2018-01-04 18:45:26');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` varchar(255) NOT NULL,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user_id` varchar(255) DEFAULT NULL COMMENT '创建者ID',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '此管理员拥有最高权限', '1', '2017-12-27 08:19:35');
INSERT INTO `sys_role` VALUES ('2', '普通用户', '只拥有查看权限', '1', '2017-12-27 08:19:54');
INSERT INTO `sys_role` VALUES ('3', '用户管理员', '只拥有管理员列表管理的权限', '1', '2017-12-27 18:46:08');
INSERT INTO `sys_role` VALUES ('34fc27bc-2ec1-43b0-b9c7-6cf44f441fc8', '用户查看', '用户查看', '1', '2018-01-28 13:37:07');
INSERT INTO `sys_role` VALUES ('d15422bd-780b-45dc-a67e-57c804cb35f1', '系统管理员', '查看系统日志、SQL监控。', '1', '2018-01-04 21:40:12');
INSERT INTO `sys_role` VALUES ('e47e920c-1e21-4592-a03d-ca48a6c3509c', '菜单管理员', '管理菜单', '1', '2018-01-09 11:06:15');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` varchar(255) NOT NULL,
  `role_id` varchar(255) DEFAULT NULL COMMENT '角色ID',
  `menu_id` varchar(255) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('0380ce57-c817-4de4-8c60-70247f8eda75', '1', '5');
INSERT INTO `sys_role_menu` VALUES ('0551caf6-961d-45c8-a39f-c30ac9cb6265', '3', '24');
INSERT INTO `sys_role_menu` VALUES ('075a0cd7-cd33-4753-b68a-2d516c7ba424', 'e47e920c-1e21-4592-a03d-ca48a6c3509c', '42');
INSERT INTO `sys_role_menu` VALUES ('0ba9026d-5b34-420a-b54a-93f9d25416c7', '2', '4');
INSERT INTO `sys_role_menu` VALUES ('1d916269-b3b9-4af5-88fe-f6066cdb75cc', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('1dd54091-d3b5-4048-be51-6409a63b5e01', '3', '2');
INSERT INTO `sys_role_menu` VALUES ('21e63535-55ad-4fab-9b67-5766f6a4ab24', '2', '21');
INSERT INTO `sys_role_menu` VALUES ('2e00875b-3a7f-4656-a6a7-b637ffc13f11', '1', '5dbf9237-e8e3-45cd-bc1f-a3230290689f');
INSERT INTO `sys_role_menu` VALUES ('3214c45d-98d4-44d4-a1be-045cb5aa2ab0', '1', '42');
INSERT INTO `sys_role_menu` VALUES ('326377e9-8c40-4b81-8eff-6270464a854b', '1', 'ac54df09-6f2f-4529-abf1-98efae1a96ef');
INSERT INTO `sys_role_menu` VALUES ('370a1a32-762c-4ecf-b5c5-0340f65b1a8f', '1', '1df216c37299');
INSERT INTO `sys_role_menu` VALUES ('3cc80fc7-862f-4a4d-bb77-57d1effa5462', 'e47e920c-1e21-4592-a03d-ca48a6c3509c', '41');
INSERT INTO `sys_role_menu` VALUES ('3f263da5-4ba3-4224-8ee6-2559567fc125', '1', '31');
INSERT INTO `sys_role_menu` VALUES ('406fde05-2131-4c5c-89b2-e10ef22c3fff', '2', '3');
INSERT INTO `sys_role_menu` VALUES ('414f5efd-bc1b-4eb7-836e-7c52eb8a5b38', '1', 'aeab4bf5-30f7-4724-94da-6baaa31d4a41');
INSERT INTO `sys_role_menu` VALUES ('43352651-4255-4989-8328-ccabbdb4a826', '1', '8');
INSERT INTO `sys_role_menu` VALUES ('4838b35a-6bb7-4bfb-89ce-e0870541c8d8', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('598a4d9d-4083-4c59-808a-f878f7ab23fe', '1', 'a2552209-90e3-456b-984c-01e43d91e597');
INSERT INTO `sys_role_menu` VALUES ('5aa096ee-349a-40e1-b471-2fc36948d5d2', '2', '31');
INSERT INTO `sys_role_menu` VALUES ('5b2a9eb1-2414-4d03-b325-3b9b3b02f61d', 'd15422bd-780b-45dc-a67e-57c804cb35f1', '7');
INSERT INTO `sys_role_menu` VALUES ('5bc846fa-1744-41a3-8f21-5cd00f071ce8', '3', '1');
INSERT INTO `sys_role_menu` VALUES ('5c8d0378-ab14-4d41-94ea-972b941eef2c', '1', '24');
INSERT INTO `sys_role_menu` VALUES ('5f86d72f-f028-4442-ae11-6177845b3e9d', 'e47e920c-1e21-4592-a03d-ca48a6c3509c', '1');
INSERT INTO `sys_role_menu` VALUES ('63d65541-fb4b-452b-93e5-44d9962cde80', '1', '33');
INSERT INTO `sys_role_menu` VALUES ('6a28ca52-1563-4723-8ac7-e7d30c315f5a', '2', '2');
INSERT INTO `sys_role_menu` VALUES ('6dad8e20-3c41-414b-8a2c-7b673900c2e7', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('75b5fd89-27d3-4687-a5ea-1fe10c8047f9', '1', '3c37fcfa-e89e-4555-8098-da6a7bff052a');
INSERT INTO `sys_role_menu` VALUES ('76784078-a0c3-48ea-a8ac-6dc2de89afea', '1', '733ad909-2733-463a-aba0-f13d6cf5cade');
INSERT INTO `sys_role_menu` VALUES ('76c9688e-cf7f-483e-8b90-5efb12924351', '3', '21');
INSERT INTO `sys_role_menu` VALUES ('7983f6d2-d110-47e1-8938-7aedc3dd106a', '3', '23');
INSERT INTO `sys_role_menu` VALUES ('7bf4e4af-7457-453a-9ff7-180b603e83c9', 'e47e920c-1e21-4592-a03d-ca48a6c3509c', '44');
INSERT INTO `sys_role_menu` VALUES ('7dab1807-af63-4ad0-94c2-382394920532', '34fc27bc-2ec1-43b0-b9c7-6cf44f441fc8', '2');
INSERT INTO `sys_role_menu` VALUES ('81618e99-faec-4975-a276-56a8c744c01f', '2', '7');
INSERT INTO `sys_role_menu` VALUES ('8499a99f-d475-4cee-a229-0832772b64c3', '1', '21');
INSERT INTO `sys_role_menu` VALUES ('94c162f2-a952-4d71-ae50-6ee23c8a76ec', '34fc27bc-2ec1-43b0-b9c7-6cf44f441fc8', '21');
INSERT INTO `sys_role_menu` VALUES ('95816852-622c-4705-a72a-84e61227cd4a', '1', 'f82498087df5');
INSERT INTO `sys_role_menu` VALUES ('99c03055-9874-4249-ab50-2d13362479b2', '2', '41');
INSERT INTO `sys_role_menu` VALUES ('9bd4923e-e5a2-4870-a43f-74b6bccf8f05', 'd15422bd-780b-45dc-a67e-57c804cb35f1', '6');
INSERT INTO `sys_role_menu` VALUES ('9e0b4b89-3484-41cf-8811-414469d702ef', '1', '22');
INSERT INTO `sys_role_menu` VALUES ('a051e9c4-e164-4c17-8213-7be0fa026c58', '1', '3ca1e1c4-65de-42c0-9d7b-09e0b560ccb9');
INSERT INTO `sys_role_menu` VALUES ('a082faa9-4727-4bfd-b69d-f5ff3837dcec', '1', '6b03c9d4-9f26-49c7-a0be-0f68bd707afb');
INSERT INTO `sys_role_menu` VALUES ('a1d4434c-6af3-4ea0-b78a-de64c60c6fa7', 'd15422bd-780b-45dc-a67e-57c804cb35f1', '5');
INSERT INTO `sys_role_menu` VALUES ('a22397b3-3cd5-44f6-920f-06c5f57eed55', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('a281473a-2079-4e38-b68b-094b0f952e3e', '1', '41');
INSERT INTO `sys_role_menu` VALUES ('a802b168-d332-466d-9e30-44147b3f7150', '2', '5');
INSERT INTO `sys_role_menu` VALUES ('aaf12d04-c8ad-440b-ab4e-616df12b804d', '1', '34');
INSERT INTO `sys_role_menu` VALUES ('b20ea06b-7c6e-4846-a095-61b2ce626877', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('b2fa1c11-199e-429e-b355-caa51ac95172', '1', '9');
INSERT INTO `sys_role_menu` VALUES ('b33ceb12-054e-43c9-afa7-430b58744a96', '34fc27bc-2ec1-43b0-b9c7-6cf44f441fc8', '1');
INSERT INTO `sys_role_menu` VALUES ('bd81458c-2bfc-4c1b-a232-8d89862402a6', '1', 'c2c6c1e3-b48a-409a-966f-08962e06433f');
INSERT INTO `sys_role_menu` VALUES ('c16ac777-6c4f-466a-b69a-6025e91d1d0f', '1', '7468c3a4-0df9-40c8-be3a-ec52d038614e');
INSERT INTO `sys_role_menu` VALUES ('c43ac04c-b7a8-410d-ab5f-7d5affa841fb', '1', '44');
INSERT INTO `sys_role_menu` VALUES ('ca40f9f0-10b2-40d6-9d4b-ea7cb33b1864', 'e47e920c-1e21-4592-a03d-ca48a6c3509c', '4');
INSERT INTO `sys_role_menu` VALUES ('cfe1cdac-c58c-4514-95a9-56e586b04dff', '1', '23');
INSERT INTO `sys_role_menu` VALUES ('d02e8a73-490e-4836-aa73-6922d3a7b4f2', '3', '22');
INSERT INTO `sys_role_menu` VALUES ('d6298dd1-e231-4091-8250-5f59ef468196', '1', '32');
INSERT INTO `sys_role_menu` VALUES ('d87e7b4e-9103-43ee-b6b7-9bda8d2ba4cc', '1', '43');
INSERT INTO `sys_role_menu` VALUES ('de0667d6-43cf-4c90-97ed-96e6717dd3bb', '2', '6');
INSERT INTO `sys_role_menu` VALUES ('e9951225-b067-49ce-9162-fae165349244', '1', '7');
INSERT INTO `sys_role_menu` VALUES ('e9a09bab-c096-4bd0-80c5-52d51ea7a4b8', '1', 'c77961d7-d049-4763-bfa9-b97ffeeec5ff');
INSERT INTO `sys_role_menu` VALUES ('ebc31c79-0312-4ed4-9066-69a298f6033a', '1', '6');
INSERT INTO `sys_role_menu` VALUES ('f4643316-aaac-4e70-a7b3-8eb11cf98527', 'e47e920c-1e21-4592-a03d-ca48a6c3509c', '43');
INSERT INTO `sys_role_menu` VALUES ('f805db8c-6445-47f6-abca-720014d478dc', '1', 'e7b1951e-54ca-4c5f-851c-d204507c31a3');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` varchar(255) NOT NULL,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` varchar(255) DEFAULT NULL COMMENT '创建者ID',
  `dept_id` varchar(255) DEFAULT NULL COMMENT '部门ID',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '1090061055@qq.com', '15716145788', '1', '1', '1', '2016-12-13 09:40:11');
INSERT INTO `sys_user` VALUES ('2', 'tom', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '457712644@qq.com', '13354774811', '1', '1', '1', '2017-12-25 15:15:54');
INSERT INTO `sys_user` VALUES ('3', 'jarry', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'jarry@qq.com', '13577487265', '1', '1', '1', '2017-12-26 08:44:42');
INSERT INTO `sys_user` VALUES ('381d6d76-dfff-411d-871a-c5d76c5d63ef', 'sophia', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'sophia@beautiful.com', '01064887524', '0', '1', 'a3d71c3c-a2c0-42de-a459-cda3e552e5e0', '2018-01-04 21:39:15');
INSERT INTO `sys_user` VALUES ('5d4e7f35-7190-4e14-8655-5e387afb8815', '小明', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', '10776@xiaoming.com', '13345336277', '0', '1', '2', '2018-01-28 13:44:47');
INSERT INTO `sys_user` VALUES ('6e36f4c5-c6e4-4bfc-9f88-b64acdb12858', 'herry', 'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855', 'herry@cc.com', '13655437717', '0', '1', '1', '2018-01-09 11:04:37');
INSERT INTO `sys_user` VALUES ('d7e51bec-e6cd-4a0f-93a1-188f70ebe061', 'hebby', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'hebbyok@gmail.com', '13346335116', '1', '1', '6', '2018-01-26 23:24:51');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户ID',
  `role_id` varchar(255) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('149ef816-2791-433d-8220-4176390c477a', '6e36f4c5-c6e4-4bfc-9f88-b64acdb12858', 'e47e920c-1e21-4592-a03d-ca48a6c3509c');
INSERT INTO `sys_user_role` VALUES ('2', '2', '2');
INSERT INTO `sys_user_role` VALUES ('3', '3', '3');
INSERT INTO `sys_user_role` VALUES ('5772b278-df4c-474f-95db-966f1e1cc155', 'ced646ad-733f-4446-8562-97bf019ca564', 'd15422bd-780b-45dc-a67e-57c804cb35f1');
INSERT INTO `sys_user_role` VALUES ('776bb3aa-c9e7-4f4d-9b0f-13427baa6eea', '5d4e7f35-7190-4e14-8655-5e387afb8815', '34fc27bc-2ec1-43b0-b9c7-6cf44f441fc8');
INSERT INTO `sys_user_role` VALUES ('c33d42ac-0894-4ac5-a46d-04a5f4359227', '381d6d76-dfff-411d-871a-c5d76c5d63ef', 'd15422bd-780b-45dc-a67e-57c804cb35f1');
INSERT INTO `sys_user_role` VALUES ('dfacb677-01b0-413b-a7da-7f45b553ff21', 'd7e51bec-e6cd-4a0f-93a1-188f70ebe061', '2');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('1', 'd');
INSERT INTO `test` VALUES ('2', 'hello');
INSERT INTO `test` VALUES ('3', 'are you ok?');
