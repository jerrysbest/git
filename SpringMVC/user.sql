/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2015-05-13 11:56:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL auto_increment,
  `username` varchar(100) default NULL,
  `password` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'zlj', '123');
INSERT INTO `user` VALUES ('2', 'admin', 'admin');
INSERT INTO `user` VALUES ('7', '719459589@qq.com', '123456');
INSERT INTO `user` VALUES ('8', '719459589@qq.com', '123456');
INSERT INTO `user` VALUES ('9', '719459589@qq.com', '123456');
INSERT INTO `user` VALUES ('10', '719459589@qq.com', '123456');
INSERT INTO `user` VALUES ('11', '719459589@qq.com', '123456');
INSERT INTO `user` VALUES ('12', '719459589@qq.com', '123456');
