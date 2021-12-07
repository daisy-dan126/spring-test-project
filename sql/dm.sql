/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost
 Source Database       : dm

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : utf-8

 Date: 06/25/2021 15:15:07 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
CREATE DATABASE IF NOT EXISTS dm;

USE dm;

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usercode` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `locked` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `sys_user`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('1', 'a', 'a', 'a', 'a', 'a'), ('2', 'lijian', 'lijian', '123456', 'aaa', '1'), ('3', 'lijian', 'lijian', '123456', 'aaa', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
