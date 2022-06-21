/*
Navicat MySQL Data Transfer

Source Server         : 192.168.21.48
Source Server Version : 50725
Source Host           : 192.168.21.48:13306
Source Database       : ffcs

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-08-29 14:34:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ACT_EVT_LOG
-- ----------------------------
DROP TABLE IF EXISTS `ACT_EVT_LOG`;
CREATE TABLE `ACT_EVT_LOG` (
  `LOG_NR_` bigint(20) NOT NULL AUTO_INCREMENT,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_STAMP_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DATA_` longblob,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  `IS_PROCESSED_` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`LOG_NR_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ACT_EVT_LOG
-- ----------------------------

-- ----------------------------
-- Table structure for ACT_GE_BYTEARRAY
-- ----------------------------
DROP TABLE IF EXISTS `ACT_GE_BYTEARRAY`;
CREATE TABLE `ACT_GE_BYTEARRAY` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTES_` longblob,
  `GENERATED_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_BYTEARR_DEPL` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_BYTEARR_DEPL` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `ACT_RE_DEPLOYMENT` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ACT_GE_BYTEARRAY
-- ----------------------------

-- ----------------------------
-- Table structure for ACT_GE_PROPERTY
-- ----------------------------
DROP TABLE IF EXISTS `ACT_GE_PROPERTY`;
CREATE TABLE `ACT_GE_PROPERTY` (
  `NAME_` varchar(64) COLLATE utf8_bin NOT NULL,
  `VALUE_` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ACT_GE_PROPERTY
-- ----------------------------
INSERT INTO `ACT_GE_PROPERTY` VALUES ('next.dbid', '1', '1');
INSERT INTO `ACT_GE_PROPERTY` VALUES ('schema.history', 'create(5.22.0.0)', '1');
INSERT INTO `ACT_GE_PROPERTY` VALUES ('schema.version', '5.22.0.0', '1');

-- ----------------------------
-- Table structure for ACT_HI_ACTINST
-- ----------------------------
DROP TABLE IF EXISTS `ACT_HI_ACTINST`;
CREATE TABLE `ACT_HI_ACTINST` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin NOT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CALL_PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ACT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_ACT_INST_START` (`START_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_PROCINST` (`PROC_INST_ID_`,`ACT_ID_`),
  KEY `ACT_IDX_HI_ACT_INST_EXEC` (`EXECUTION_ID_`,`ACT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ACT_HI_ACTINST
-- ----------------------------

-- ----------------------------
-- Table structure for ACT_HI_ATTACHMENT
-- ----------------------------
DROP TABLE IF EXISTS `ACT_HI_ATTACHMENT`;
CREATE TABLE `ACT_HI_ATTACHMENT` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `URL_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CONTENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ACT_HI_ATTACHMENT
-- ----------------------------

-- ----------------------------
-- Table structure for ACT_HI_COMMENT
-- ----------------------------
DROP TABLE IF EXISTS `ACT_HI_COMMENT`;
CREATE TABLE `ACT_HI_COMMENT` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `MESSAGE_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `FULL_MSG_` longblob,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ACT_HI_COMMENT
-- ----------------------------

-- ----------------------------
-- Table structure for ACT_HI_DETAIL
-- ----------------------------
DROP TABLE IF EXISTS `ACT_HI_DETAIL`;
CREATE TABLE `ACT_HI_DETAIL` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_DETAIL_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_ACT_INST` (`ACT_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_TIME` (`TIME_`),
  KEY `ACT_IDX_HI_DETAIL_NAME` (`NAME_`),
  KEY `ACT_IDX_HI_DETAIL_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ACT_HI_DETAIL
-- ----------------------------

-- ----------------------------
-- Table structure for ACT_HI_IDENTITYLINK
-- ----------------------------
DROP TABLE IF EXISTS `ACT_HI_IDENTITYLINK`;
CREATE TABLE `ACT_HI_IDENTITYLINK` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_TASK` (`TASK_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ACT_HI_IDENTITYLINK
-- ----------------------------

-- ----------------------------
-- Table structure for ACT_HI_PROCINST
-- ----------------------------
DROP TABLE IF EXISTS `ACT_HI_PROCINST`;
CREATE TABLE `ACT_HI_PROCINST` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `END_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `PROC_INST_ID_` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PRO_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_PRO_I_BUSKEY` (`BUSINESS_KEY_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ACT_HI_PROCINST
-- ----------------------------

-- ----------------------------
-- Table structure for ACT_HI_TASKINST
-- ----------------------------
DROP TABLE IF EXISTS `ACT_HI_TASKINST`;
CREATE TABLE `ACT_HI_TASKINST` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `CLAIM_TIME_` datetime(3) DEFAULT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_TASK_INST_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ACT_HI_TASKINST
-- ----------------------------

-- ----------------------------
-- Table structure for ACT_HI_VARINST
-- ----------------------------
DROP TABLE IF EXISTS `ACT_HI_VARINST`;
CREATE TABLE `ACT_HI_VARINST` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `LAST_UPDATED_TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_PROCVAR_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PROCVAR_NAME_TYPE` (`NAME_`,`VAR_TYPE_`),
  KEY `ACT_IDX_HI_PROCVAR_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ACT_HI_VARINST
-- ----------------------------

-- ----------------------------
-- Table structure for ACT_ID_GROUP
-- ----------------------------
DROP TABLE IF EXISTS `ACT_ID_GROUP`;
CREATE TABLE `ACT_ID_GROUP` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ACT_ID_GROUP
-- ----------------------------

-- ----------------------------
-- Table structure for ACT_ID_INFO
-- ----------------------------
DROP TABLE IF EXISTS `ACT_ID_INFO`;
CREATE TABLE `ACT_ID_INFO` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `VALUE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD_` longblob,
  `PARENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ACT_ID_INFO
-- ----------------------------

-- ----------------------------
-- Table structure for ACT_ID_MEMBERSHIP
-- ----------------------------
DROP TABLE IF EXISTS `ACT_ID_MEMBERSHIP`;
CREATE TABLE `ACT_ID_MEMBERSHIP` (
  `USER_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `GROUP_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`USER_ID_`,`GROUP_ID_`),
  KEY `ACT_FK_MEMB_GROUP` (`GROUP_ID_`),
  CONSTRAINT `ACT_FK_MEMB_GROUP` FOREIGN KEY (`GROUP_ID_`) REFERENCES `ACT_ID_GROUP` (`ID_`),
  CONSTRAINT `ACT_FK_MEMB_USER` FOREIGN KEY (`USER_ID_`) REFERENCES `ACT_ID_USER` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ACT_ID_MEMBERSHIP
-- ----------------------------

-- ----------------------------
-- Table structure for ACT_ID_USER
-- ----------------------------
DROP TABLE IF EXISTS `ACT_ID_USER`;
CREATE TABLE `ACT_ID_USER` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `FIRST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LAST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PWD_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PICTURE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ACT_ID_USER
-- ----------------------------

-- ----------------------------
-- Table structure for ACT_PROCDEF_INFO
-- ----------------------------
DROP TABLE IF EXISTS `ACT_PROCDEF_INFO`;
CREATE TABLE `ACT_PROCDEF_INFO` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `INFO_JSON_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_IDX_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_INFO_JSON_BA` (`INFO_JSON_ID_`),
  CONSTRAINT `ACT_FK_INFO_JSON_BA` FOREIGN KEY (`INFO_JSON_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
  CONSTRAINT `ACT_FK_INFO_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ACT_PROCDEF_INFO
-- ----------------------------

-- ----------------------------
-- Table structure for ACT_RE_DEPLOYMENT
-- ----------------------------
DROP TABLE IF EXISTS `ACT_RE_DEPLOYMENT`;
CREATE TABLE `ACT_RE_DEPLOYMENT` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `DEPLOY_TIME_` timestamp(3) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ACT_RE_DEPLOYMENT
-- ----------------------------

-- ----------------------------
-- Table structure for ACT_RE_MODEL
-- ----------------------------
DROP TABLE IF EXISTS `ACT_RE_MODEL`;
CREATE TABLE `ACT_RE_MODEL` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LAST_UPDATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `VERSION_` int(11) DEFAULT NULL,
  `META_INFO_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_EXTRA_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_MODEL_SOURCE` (`EDITOR_SOURCE_VALUE_ID_`),
  KEY `ACT_FK_MODEL_SOURCE_EXTRA` (`EDITOR_SOURCE_EXTRA_VALUE_ID_`),
  KEY `ACT_FK_MODEL_DEPLOYMENT` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_MODEL_DEPLOYMENT` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `ACT_RE_DEPLOYMENT` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE` FOREIGN KEY (`EDITOR_SOURCE_VALUE_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE_EXTRA` FOREIGN KEY (`EDITOR_SOURCE_EXTRA_VALUE_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ACT_RE_MODEL
-- ----------------------------

-- ----------------------------
-- Table structure for ACT_RE_PROCDEF
-- ----------------------------
DROP TABLE IF EXISTS `ACT_RE_PROCDEF`;
CREATE TABLE `ACT_RE_PROCDEF` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DGRM_RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `HAS_START_FORM_KEY_` tinyint(4) DEFAULT NULL,
  `HAS_GRAPHICAL_NOTATION_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_PROCDEF` (`KEY_`,`VERSION_`,`TENANT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ACT_RE_PROCDEF
-- ----------------------------

-- ----------------------------
-- Table structure for ACT_RU_EVENT_SUBSCR
-- ----------------------------
DROP TABLE IF EXISTS `ACT_RU_EVENT_SUBSCR`;
CREATE TABLE `ACT_RU_EVENT_SUBSCR` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EVENT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EVENT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTIVITY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CONFIGURATION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EVENT_SUBSCR_CONFIG_` (`CONFIGURATION_`),
  KEY `ACT_FK_EVENT_EXEC` (`EXECUTION_ID_`),
  CONSTRAINT `ACT_FK_EVENT_EXEC` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ACT_RU_EVENT_SUBSCR
-- ----------------------------

-- ----------------------------
-- Table structure for ACT_RU_EXECUTION
-- ----------------------------
DROP TABLE IF EXISTS `ACT_RU_EXECUTION`;
CREATE TABLE `ACT_RU_EXECUTION` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_EXEC_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `IS_ACTIVE_` tinyint(4) DEFAULT NULL,
  `IS_CONCURRENT_` tinyint(4) DEFAULT NULL,
  `IS_SCOPE_` tinyint(4) DEFAULT NULL,
  `IS_EVENT_SCOPE_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `CACHED_ENT_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EXEC_BUSKEY` (`BUSINESS_KEY_`),
  KEY `ACT_FK_EXE_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_EXE_PARENT` (`PARENT_ID_`),
  KEY `ACT_FK_EXE_SUPER` (`SUPER_EXEC_`),
  KEY `ACT_FK_EXE_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_EXE_PARENT` FOREIGN KEY (`PARENT_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ACT_FK_EXE_SUPER` FOREIGN KEY (`SUPER_EXEC_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ACT_RU_EXECUTION
-- ----------------------------

-- ----------------------------
-- Table structure for ACT_RU_IDENTITYLINK
-- ----------------------------
DROP TABLE IF EXISTS `ACT_RU_IDENTITYLINK`;
CREATE TABLE `ACT_RU_IDENTITYLINK` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_IDENT_LNK_GROUP` (`GROUP_ID_`),
  KEY `ACT_IDX_ATHRZ_PROCEDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_TSKASS_TASK` (`TASK_ID_`),
  KEY `ACT_FK_IDL_PROCINST` (`PROC_INST_ID_`),
  CONSTRAINT `ACT_FK_ATHRZ_PROCEDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`),
  CONSTRAINT `ACT_FK_IDL_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_TSKASS_TASK` FOREIGN KEY (`TASK_ID_`) REFERENCES `ACT_RU_TASK` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ACT_RU_IDENTITYLINK
-- ----------------------------

-- ----------------------------
-- Table structure for ACT_RU_JOB
-- ----------------------------
DROP TABLE IF EXISTS `ACT_RU_JOB`;
CREATE TABLE `ACT_RU_JOB` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  CONSTRAINT `ACT_FK_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ACT_RU_JOB
-- ----------------------------

-- ----------------------------
-- Table structure for ACT_RU_TASK
-- ----------------------------
DROP TABLE IF EXISTS `ACT_RU_TASK`;
CREATE TABLE `ACT_RU_TASK` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DELEGATION_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_TASK_CREATE` (`CREATE_TIME_`),
  KEY `ACT_FK_TASK_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_TASK_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_TASK_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_TASK_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ACT_RU_TASK
-- ----------------------------

-- ----------------------------
-- Table structure for ACT_RU_VARIABLE
-- ----------------------------
DROP TABLE IF EXISTS `ACT_RU_VARIABLE`;
CREATE TABLE `ACT_RU_VARIABLE` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_VARIABLE_TASK_ID` (`TASK_ID_`),
  KEY `ACT_FK_VAR_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_VAR_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_VAR_BYTEARRAY` (`BYTEARRAY_ID_`),
  CONSTRAINT `ACT_FK_VAR_BYTEARRAY` FOREIGN KEY (`BYTEARRAY_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ACT_RU_VARIABLE
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_BLOB_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE `QRTZ_BLOB_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_BLOB_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_CALENDARS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE `QRTZ_CALENDARS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_CALENDARS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_CRON_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE `QRTZ_CRON_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_CRON_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_FIRED_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `QRTZ_FIRED_TRIGGERS` (
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
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_FIRED_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_JOB_DETAILS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
CREATE TABLE `QRTZ_JOB_DETAILS` (
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
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_JOB_DETAILS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_LOCKS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_LOCKS
-- ----------------------------
INSERT INTO `QRTZ_LOCKS` VALUES ('RenrenScheduler', 'STATE_ACCESS');

-- ----------------------------
-- Table structure for QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SCHEDULER_STATE
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE `QRTZ_SCHEDULER_STATE` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_SCHEDULER_STATE
-- ----------------------------
INSERT INTO `QRTZ_SCHEDULER_STATE` VALUES ('RenrenScheduler', 'emp011567054683132', '1567060485781', '15000');
INSERT INTO `QRTZ_SCHEDULER_STATE` VALUES ('RenrenScheduler', 'windysea1567059968039', '1567060483516', '15000');

-- ----------------------------
-- Table structure for QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_SIMPLE_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS` (
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
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_SIMPROP_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE `QRTZ_TRIGGERS` (
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
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(3) unsigned DEFAULT NULL COMMENT '任务状态  0：暂停  1：正常',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_create_date` (`create_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务';

-- ----------------------------
-- Records of schedule_job
-- ----------------------------

-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint(3) unsigned NOT NULL COMMENT '任务状态    0：失败    1：成功',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_job_id` (`job_id`),
  KEY `idx_create_date` (`create_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` bigint(20) DEFAULT NULL COMMENT '上级ID',
  `pids` varchar(500) DEFAULT NULL COMMENT '所有上级ID，用逗号分开',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `sort` int(10) unsigned DEFAULT NULL COMMENT '排序',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_pid` (`pid`),
  KEY `idx_sort` (`sort`)
) ENGINE=InnoDB AUTO_INCREMENT=1067246875800000069 DEFAULT CHARSET=utf8 COMMENT='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1067246875800000062', '1067246875800000063', '1067246875800000066,1067246875800000063', '技术部', '2', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_dept` VALUES ('1067246875800000063', '1067246875800000066', '1067246875800000066', '长沙分公司', '1', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_dept` VALUES ('1067246875800000064', '1067246875800000066', '1067246875800000066', '上海分公司', '0', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_dept` VALUES ('1067246875800000065', '1067246875800000064', '1067246875800000066,1067246875800000064', '市场部', '0', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_dept` VALUES ('1067246875800000066', '0', '0', '人人开源集团', '0', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_dept` VALUES ('1067246875800000067', '1067246875800000064', '1067246875800000066,1067246875800000064', '销售部', '0', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_dept` VALUES ('1067246875800000068', '1067246875800000063', '1067246875800000066,1067246875800000063', '产品部', '1', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` bigint(20) DEFAULT NULL COMMENT '上级ID，一级为0',
  `dict_type` varchar(50) NOT NULL COMMENT '字典类型',
  `dict_name` varchar(255) NOT NULL COMMENT '字典名称',
  `dict_value` varchar(255) DEFAULT NULL COMMENT '字典值',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `sort` int(10) unsigned DEFAULT NULL COMMENT '排序',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `dict_type` (`dict_type`,`dict_value`),
  KEY `idx_pid` (`pid`),
  KEY `idx_sort` (`sort`)
) ENGINE=InnoDB AUTO_INCREMENT=1067246875800000073 DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1067246875800000069', '0', 'gender', '性别', '', '', '0', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_dict` VALUES ('1067246875800000070', '1067246875800000069', 'gender', '男', '0', '', '0', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_dict` VALUES ('1067246875800000071', '1067246875800000069', 'gender', '女', '1', '', '1', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_dict` VALUES ('1067246875800000072', '1067246875800000069', 'gender', '保密', '2', '', '2', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');

-- ----------------------------
-- Table structure for sys_language
-- ----------------------------
DROP TABLE IF EXISTS `sys_language`;
CREATE TABLE `sys_language` (
  `table_name` varchar(32) NOT NULL COMMENT '表名',
  `table_id` bigint(20) NOT NULL COMMENT '表主键',
  `field_name` varchar(32) NOT NULL COMMENT '字段名',
  `field_value` varchar(200) NOT NULL COMMENT '字段值',
  `language` varchar(10) NOT NULL COMMENT '语言',
  PRIMARY KEY (`table_name`,`table_id`,`field_name`,`language`),
  KEY `idx_table_id` (`table_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='国际化';

-- ----------------------------
-- Records of sys_language
-- ----------------------------
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000002', 'name', 'Authority Management', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000002', 'name', '权限管理', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000002', 'name', '權限管理', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000003', 'name', 'Add', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000003', 'name', '新增', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000003', 'name', '新增', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000004', 'name', 'Edit', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000004', 'name', '修改', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000004', 'name', '修改', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000005', 'name', 'Delete', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000005', 'name', '删除', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000005', 'name', '刪除', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000006', 'name', 'Export', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000006', 'name', '导出', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000006', 'name', '導出', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000007', 'name', 'Role Management', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000007', 'name', '角色管理', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000007', 'name', '角色管理', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000008', 'name', 'View', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000008', 'name', '查看', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000008', 'name', '查看', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000009', 'name', 'Add', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000009', 'name', '新增', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000009', 'name', '新增', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000010', 'name', 'Edit', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000010', 'name', '修改', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000010', 'name', '修改', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000011', 'name', 'Delete', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000011', 'name', '删除', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000011', 'name', '刪除', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000012', 'name', 'Department Management', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000012', 'name', '部门管理', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000012', 'name', '部門管理', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000013', 'name', 'Work Process', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000013', 'name', '工作流程', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000013', 'name', '工作流程', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000014', 'name', 'View', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000014', 'name', '查看', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000014', 'name', '查看', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000015', 'name', 'Add', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000015', 'name', '新增', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000015', 'name', '新增', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000016', 'name', 'Edit', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000016', 'name', '修改', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000016', 'name', '修改', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000017', 'name', 'Delete', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000017', 'name', '删除', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000017', 'name', '刪除', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000018', 'name', 'Process Management', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000018', 'name', '流程管理', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000018', 'name', '流程管理', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000019', 'name', 'Model Management', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000019', 'name', '模型管理', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000019', 'name', '模型管理', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000020', 'name', 'Running Process', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000020', 'name', '运行中的流程', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000020', 'name', '運行中的流程', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000021', 'name', 'SMS Service', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000021', 'name', '短信服务', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000021', 'name', '短信服務', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000022', 'name', 'Mail Template', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000022', 'name', '邮件模板', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000022', 'name', '郵件模板', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000023', 'name', 'Mail Log', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000023', 'name', '邮件发送记录', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000023', 'name', '郵件發送記錄', 'zh-TW ');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000024', 'name', 'Message Management', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000024', 'name', '消息管理', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000024', 'name', '消息管理', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000025', 'name', 'Menu Management', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000025', 'name', '菜单管理', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000025', 'name', '菜單管理', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000026', 'name', 'View', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000026', 'name', '查看', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000026', 'name', '查看', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000027', 'name', 'Add', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000027', 'name', '新增', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000027', 'name', '新增', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000028', 'name', 'Edit', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000028', 'name', '修改', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000028', 'name', '修改', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000029', 'name', 'Delete', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000029', 'name', '删除', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000029', 'name', '刪除', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000030', 'name', 'Timed Task', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000030', 'name', '定时任务', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000030', 'name', '定時任務', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000031', 'name', 'View', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000031', 'name', '查看', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000031', 'name', '查看', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000032', 'name', 'Add', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000032', 'name', '新增', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000032', 'name', '新增', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000033', 'name', 'Edit', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000033', 'name', '修改', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000033', 'name', '修改', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000034', 'name', 'Delete', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000034', 'name', '删除', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000034', 'name', '刪除', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000035', 'name', 'Setting', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000035', 'name', '系统设置', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000035', 'name', '系統設置', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000036', 'name', 'Pause', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000036', 'name', '暂停', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000036', 'name', '暫停', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000037', 'name', 'Resume', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000037', 'name', '恢复', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000037', 'name', '恢復', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000038', 'name', 'Execute', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000038', 'name', '立即执行', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000038', 'name', '立即執行', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000039', 'name', 'Log List', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000039', 'name', '日志列表', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000039', 'name', '日誌列表', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000040', 'name', 'Parameter Management', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000040', 'name', '参数管理', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000040', 'name', '參數管理', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000041', 'name', 'Dict Management', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000041', 'name', '字典管理', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000041', 'name', '字典管理', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000042', 'name', 'View', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000042', 'name', '查看', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000042', 'name', '查看', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000043', 'name', 'Add', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000043', 'name', '新增', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000043', 'name', '新增', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000044', 'name', 'Edit', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000044', 'name', '修改', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000044', 'name', '修改', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000045', 'name', 'Delete', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000045', 'name', '删除', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000045', 'name', '刪除', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000046', 'name', 'Log Management', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000046', 'name', '日志管理', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000046', 'name', '日誌管理', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000047', 'name', 'File Upload', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000047', 'name', '文件上传', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000047', 'name', '文件上傳', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000048', 'name', 'Login Log', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000048', 'name', '登录日志', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000048', 'name', '登錄日誌', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000049', 'name', 'Operation Log', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000049', 'name', '操作日志', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000049', 'name', '操作日誌', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000050', 'name', 'Error Log', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000050', 'name', '异常日志', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000050', 'name', '異常日誌', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000051', 'name', 'SQL Monitoring', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000051', 'name', 'SQL监控', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000051', 'name', 'SQL監控', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000052', 'name', 'News Management', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000052', 'name', '新闻管理', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000052', 'name', '新聞管理', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000053', 'name', 'System Monitoring', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000053', 'name', '系统监控', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000053', 'name', '系統監控', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000054', 'name', 'Demo', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000054', 'name', '功能示例', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000054', 'name', '功能示例', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000055', 'name', 'User Management', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000055', 'name', '用户管理', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000055', 'name', '用戶管理', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000056', 'name', 'View', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000056', 'name', '查看', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000056', 'name', '查看', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000057', 'name', 'Add', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000057', 'name', '新增', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000057', 'name', '新增', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000058', 'name', 'Export', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000058', 'name', '导出', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000058', 'name', '導出', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000059', 'name', 'View', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000059', 'name', '查看', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000059', 'name', '查看', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000060', 'name', 'Edit', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000060', 'name', '修改', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000060', 'name', '修改', 'zh-TW');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000061', 'name', 'Delete', 'en-US');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000061', 'name', '删除', 'zh-CN');
INSERT INTO `sys_language` VALUES ('sys_menu', '1067246875800000061', 'name', '刪除', 'zh-TW');

-- ----------------------------
-- Table structure for sys_log_error
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_error`;
CREATE TABLE `sys_log_error` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `request_uri` varchar(200) DEFAULT NULL COMMENT '请求URI',
  `request_method` varchar(20) DEFAULT NULL COMMENT '请求方式',
  `request_params` text COMMENT '请求参数',
  `user_agent` varchar(500) DEFAULT NULL COMMENT '用户代理',
  `ip` varchar(32) DEFAULT NULL COMMENT '操作IP',
  `error_info` text COMMENT '异常信息',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_create_date` (`create_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='异常日志';

-- ----------------------------
-- Records of sys_log_error
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log_login
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_login`;
CREATE TABLE `sys_log_login` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `operation` tinyint(3) unsigned DEFAULT NULL COMMENT '用户操作   0：用户登录   1：用户退出',
  `status` tinyint(3) unsigned NOT NULL COMMENT '状态  0：失败    1：成功    2：账号已锁定',
  `user_agent` varchar(500) DEFAULT NULL COMMENT '用户代理',
  `ip` varchar(32) DEFAULT NULL COMMENT '操作IP',
  `creator_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_status` (`status`),
  KEY `idx_create_date` (`create_date`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='登录日志';

-- ----------------------------
-- Records of sys_log_login
-- ----------------------------
INSERT INTO `sys_log_login` VALUES ('1', '0', '0', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36 FirePHP/4Chrome', '192.168.13.19', 'admin', '1067246875800000001', '2019-08-29 13:15:05');
INSERT INTO `sys_log_login` VALUES ('2', '0', '0', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36 FirePHP/4Chrome', '192.168.13.19', 'admin', '1067246875800000001', '2019-08-29 13:15:56');
INSERT INTO `sys_log_login` VALUES ('3', '0', '0', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36 FirePHP/4Chrome', '192.168.13.19', 'admin', '1067246875800000001', '2019-08-29 13:16:07');
INSERT INTO `sys_log_login` VALUES ('4', '0', '0', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36 FirePHP/4Chrome', '192.168.13.19', 'admin', '1067246875800000001', '2019-08-29 13:17:16');
INSERT INTO `sys_log_login` VALUES ('5', '0', '0', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36 FirePHP/4Chrome', '192.168.13.19', 'admin', '1067246875800000001', '2019-08-29 13:17:24');
INSERT INTO `sys_log_login` VALUES ('6', '0', '0', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36 FirePHP/4Chrome', '192.168.13.19', 'admin', '1067246875800000001', '2019-08-29 13:17:30');
INSERT INTO `sys_log_login` VALUES ('7', '0', '0', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36 FirePHP/4Chrome', '127.0.0.1', 'admin', '1067246875800000001', '2019-08-29 14:28:55');
INSERT INTO `sys_log_login` VALUES ('8', '0', '0', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36 FirePHP/4Chrome', '127.0.0.1', 'admin', '1067246875800000001', '2019-08-29 14:29:59');
INSERT INTO `sys_log_login` VALUES ('9', '0', '1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36 FirePHP/4Chrome', '127.0.0.1', 'admin', '1067246875800000001', '2019-08-29 14:31:29');

-- ----------------------------
-- Table structure for sys_log_operation
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_operation`;
CREATE TABLE `sys_log_operation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `request_uri` varchar(200) DEFAULT NULL COMMENT '请求URI',
  `request_method` varchar(20) DEFAULT NULL COMMENT '请求方式',
  `request_params` text COMMENT '请求参数',
  `request_time` int(10) unsigned NOT NULL COMMENT '请求时长(毫秒)',
  `user_agent` varchar(500) DEFAULT NULL COMMENT '用户代理',
  `ip` varchar(32) DEFAULT NULL COMMENT '操作IP',
  `status` tinyint(3) unsigned NOT NULL COMMENT '状态  0：失败   1：成功',
  `creator_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_create_date` (`create_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志';

-- ----------------------------
-- Records of sys_log_operation
-- ----------------------------

-- ----------------------------
-- Table structure for sys_mail_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_mail_log`;
CREATE TABLE `sys_mail_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `template_id` bigint(20) NOT NULL COMMENT '邮件模板ID',
  `mail_from` varchar(200) DEFAULT NULL COMMENT '发送者',
  `mail_to` varchar(400) DEFAULT NULL COMMENT '收件人',
  `mail_cc` varchar(400) DEFAULT NULL COMMENT '抄送者',
  `subject` varchar(200) DEFAULT NULL COMMENT '邮件主题',
  `content` text COMMENT '邮件正文',
  `status` tinyint(3) unsigned DEFAULT NULL COMMENT '发送状态  0：失败  1：成功',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_create_date` (`create_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮件发送记录';

-- ----------------------------
-- Records of sys_mail_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_mail_template
-- ----------------------------
DROP TABLE IF EXISTS `sys_mail_template`;
CREATE TABLE `sys_mail_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(100) DEFAULT NULL COMMENT '模板名称',
  `subject` varchar(200) DEFAULT NULL COMMENT '邮件主题',
  `content` text COMMENT '邮件正文',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_create_date` (`create_date`)
) ENGINE=InnoDB AUTO_INCREMENT=1067246875800000078 DEFAULT CHARSET=utf8 COMMENT='邮件模板';

-- ----------------------------
-- Records of sys_mail_template
-- ----------------------------
INSERT INTO `sys_mail_template` VALUES ('1067246875800000077', '验证码模板', '人人开源注册验证码', '<p>人人开源注册验证码：${code}</p>', null, '2019-08-29 11:27:50', null, null);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` bigint(20) DEFAULT NULL COMMENT '上级ID，一级菜单为0',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `permissions` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：sys:user:list,sys:user:save)',
  `type` tinyint(3) unsigned DEFAULT NULL COMMENT '类型   0：菜单   1：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_pid` (`pid`),
  KEY `idx_sort` (`sort`)
) ENGINE=InnoDB AUTO_INCREMENT=1067246875800000062 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1067246875800000002', '0', null, null, '0', 'icon-safetycertificate', '0', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000003', '1067246875800000055', null, 'sys:user:save,sys:dept:list,sys:role:list', '1', null, '1', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000004', '1067246875800000055', null, 'sys:user:update,sys:dept:list,sys:role:list', '1', null, '2', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000005', '1067246875800000055', null, 'sys:user:delete', '1', null, '3', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000006', '1067246875800000055', null, 'sys:user:export', '1', null, '4', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000007', '1067246875800000002', 'sys/role', null, '0', 'icon-team', '2', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000008', '1067246875800000007', null, 'sys:role:page,sys:role:info', '1', null, '0', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000009', '1067246875800000007', null, 'sys:role:save,sys:menu:select,sys:dept:list', '1', null, '1', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000010', '1067246875800000007', null, 'sys:role:update,sys:menu:select,sys:dept:list', '1', null, '2', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000011', '1067246875800000007', null, 'sys:role:delete', '1', null, '3', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000012', '1067246875800000002', 'sys/dept', null, '0', 'icon-apartment', '1', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000013', '0', null, null, '0', 'icon-cluster', '2', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000014', '1067246875800000012', null, 'sys:dept:list,sys:dept:info', '1', null, '0', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000015', '1067246875800000012', null, 'sys:dept:save', '1', null, '1', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000016', '1067246875800000012', null, 'sys:dept:update', '1', null, '2', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000017', '1067246875800000012', null, 'sys:dept:delete', '1', null, '3', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000018', '1067246875800000013', 'activiti/process', 'sys:process:all', '0', 'icon-detail', '0', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000019', '1067246875800000013', 'activiti/model', 'sys:model:all', '0', 'icon-appstore-fill', '1', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000020', '1067246875800000013', 'activiti/running', 'sys:running:all', '0', 'icon-play-square', '2', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000021', '1067246875800000024', 'message/sms', 'sys:sms:all', '0', 'icon-message-fill', '0', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000022', '1067246875800000024', 'message/mail_template', 'sys:mail:all', '0', 'icon-mail', '1', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000023', '1067246875800000024', 'message/mail_log', 'sys:mail:log', '0', 'icon-detail-fill', '2', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000024', '0', null, null, '0', 'icon-message', '3', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000025', '1067246875800000035', 'sys/menu', null, '0', 'icon-unorderedlist', '0', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000026', '1067246875800000025', null, 'sys:menu:list,sys:menu:info', '1', null, '0', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000027', '1067246875800000025', null, 'sys:menu:save', '1', null, '1', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000028', '1067246875800000025', null, 'sys:menu:update', '1', null, '2', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000029', '1067246875800000025', null, 'sys:menu:delete', '1', null, '3', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000030', '1067246875800000035', 'job/schedule', null, '0', 'icon-dashboard', '3', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000031', '1067246875800000030', null, 'sys:schedule:page,sys:schedule:info', '1', null, '0', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000032', '1067246875800000030', null, 'sys:schedule:save', '1', null, '1', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000033', '1067246875800000030', null, 'sys:schedule:update', '1', null, '2', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000034', '1067246875800000030', null, 'sys:schedule:delete', '1', null, '3', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000035', '0', null, null, '0', 'icon-setting', '1', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000036', '1067246875800000030', null, 'sys:schedule:pause', '1', null, '4', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000037', '1067246875800000030', null, 'sys:schedule:resume', '1', null, '5', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000038', '1067246875800000030', null, 'sys:schedule:run', '1', null, '6', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000039', '1067246875800000030', null, 'sys:schedule:log', '1', null, '7', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000040', '1067246875800000035', 'sys/params', '', '0', 'icon-fileprotect', '1', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000041', '1067246875800000035', 'sys/dict', null, '0', 'icon-golden-fill', '2', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000042', '1067246875800000041', null, 'sys:dict:page,sys:dict:info', '1', null, '0', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000043', '1067246875800000041', null, 'sys:dict:save', '1', null, '1', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000044', '1067246875800000041', null, 'sys:dict:update', '1', null, '2', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000045', '1067246875800000041', null, 'sys:dict:delete', '1', null, '3', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000046', '0', null, null, '0', 'icon-container', '4', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000047', '1067246875800000035', 'oss/oss', 'sys:oss:all', '0', 'icon-upload', '4', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000048', '1067246875800000046', 'sys/log-login', 'sys:log:login', '0', 'icon-filedone', '0', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000049', '1067246875800000046', 'sys/log-operation', 'sys:log:operation', '0', 'icon-solution', '1', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000050', '1067246875800000046', 'sys/log-error', 'sys:log:error', '0', 'icon-file-exception', '2', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000051', '1067246875800000053', '{{ window.SITE_CONFIG[\"apiURL\"] }}/druid/sql.html', null, '0', 'icon-database', '0', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000052', '1067246875800000054', 'demo/news', 'demo:news:all', '0', 'icon-file-word', '0', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000053', '0', null, null, '0', 'icon-desktop', '5', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000054', '0', null, null, '0', 'icon-windows', '6', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000055', '1067246875800000002', 'sys/user', null, '0', 'icon-user', '0', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000056', '1067246875800000055', null, 'sys:user:page,sys:user:info', '1', null, '0', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000057', '1067246875800000040', null, 'sys:params:save', '1', null, '1', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000058', '1067246875800000040', null, 'sys:params:export', '1', null, '4', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000059', '1067246875800000040', '', 'sys:params:page,sys:params:info', '1', null, '0', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000060', '1067246875800000040', null, 'sys:params:update', '1', null, '2', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_menu` VALUES ('1067246875800000061', '1067246875800000040', '', 'sys:params:delete', '1', '', '3', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_create_date` (`create_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件上传';

-- ----------------------------
-- Records of sys_oss
-- ----------------------------

-- ----------------------------
-- Table structure for sys_params
-- ----------------------------
DROP TABLE IF EXISTS `sys_params`;
CREATE TABLE `sys_params` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `param_code` varchar(32) DEFAULT NULL COMMENT '参数编码',
  `param_value` varchar(2000) DEFAULT NULL COMMENT '参数值',
  `param_type` tinyint(3) unsigned DEFAULT '1' COMMENT '类型   0：系统参数   1：非系统参数',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_param_code` (`param_code`),
  KEY `idx_create_date` (`create_date`)
) ENGINE=InnoDB AUTO_INCREMENT=1067246875800000076 DEFAULT CHARSET=utf8 COMMENT='参数管理';

-- ----------------------------
-- Records of sys_params
-- ----------------------------
INSERT INTO `sys_params` VALUES ('1067246875800000073', 'CLOUD_STORAGE_CONFIG_KEY', '{\"type\":1,\"qiniuDomain\":\"http://pgzjvphhb.bkt.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"qiniuBucketName\":\"renren-oss\",\"aliyunDomain\":\"\",\"aliyunPrefix\":\"\",\"aliyunEndPoint\":\"\",\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qcloudBucketName\":\"\"}', '0', '云存储配置信息', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_params` VALUES ('1067246875800000074', 'SMS_CONFIG_KEY', '{\"platform\":1,\"aliyunAccessKeyId\":\"1\",\"aliyunAccessKeySecret\":\"1\",\"aliyunSignName\":\"1\",\"aliyunTemplateCode\":\"1\",\"qcloudAppId\":2,\"qcloudAppKey\":\"2\",\"qcloudSignName\":\"2\",\"qcloudTemplateId\":\"2\"}', '0', '短信配置信息', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');
INSERT INTO `sys_params` VALUES ('1067246875800000075', 'MAIL_CONFIG_KEY', '{\"smtp\":\"smtp.163.com\",\"port\":25,\"username\":\"renrenio_test@163.com\",\"password\":\"renren123456\"}', '0', '邮件配置信息', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_dept_id` (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色管理';

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_data_scope
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_data_scope`;
CREATE TABLE `sys_role_data_scope` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色数据权限';

-- ----------------------------
-- Records of sys_role_data_scope
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_menu_id` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色用户关系';

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_sms
-- ----------------------------
DROP TABLE IF EXISTS `sys_sms`;
CREATE TABLE `sys_sms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `platform` tinyint(3) unsigned NOT NULL COMMENT '平台类型',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `params_1` varchar(50) DEFAULT NULL COMMENT '参数1',
  `params_2` varchar(50) DEFAULT NULL COMMENT '参数2',
  `params_3` varchar(50) DEFAULT NULL COMMENT '参数3',
  `params_4` varchar(50) DEFAULT NULL COMMENT '参数4',
  `status` tinyint(3) unsigned DEFAULT NULL COMMENT '发送状态  0：失败  1：成功',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_create_date` (`create_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短信';

-- ----------------------------
-- Records of sys_sms
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `head_url` varchar(200) DEFAULT NULL COMMENT '头像',
  `gender` tinyint(3) unsigned DEFAULT NULL COMMENT '性别   0：男   1：女    2：保密',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `super_admin` tinyint(3) unsigned DEFAULT NULL COMMENT '超级管理员   0：否   1：是',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：停用   1：正常',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`),
  KEY `idx_create_date` (`create_date`)
) ENGINE=InnoDB AUTO_INCREMENT=1067246875800000002 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1067246875800000001', 'admin', '$2a$10$Lo5asN.aSunG5iJWdCS2AuuXk6tAfodTlmVvcZ.p92OnX8vOtFRm6', '管理员', null, '0', 'root@renren.io', '13612345678', null, '1', '1', '1067246875800000001', '2019-08-29 11:27:50', '1067246875800000001', '2019-08-29 11:27:50');

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `token` varchar(100) NOT NULL COMMENT '用户token',
  `expire_date` datetime DEFAULT NULL COMMENT '过期时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统用户Token';

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
INSERT INTO `sys_user_token` VALUES ('1', '1067246875800000001', '0abf35fdf706c43c25e231f9a931fb4d', '2019-08-30 02:31:29', '2019-08-29 14:31:29', '2019-08-29 14:31:29');

-- ----------------------------
-- Table structure for tb_news
-- ----------------------------
DROP TABLE IF EXISTS `tb_news`;
CREATE TABLE `tb_news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(100) NOT NULL COMMENT '标题',
  `content` mediumtext NOT NULL COMMENT '内容',
  `pub_date` datetime DEFAULT NULL COMMENT '发布时间',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '创建者dept_id',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新闻管理';

-- ----------------------------
-- Records of tb_news
-- ----------------------------
