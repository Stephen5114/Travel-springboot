/*
 Navicat MySQL Dump SQL

 Source Server         : a
 Source Server Type    : MySQL
 Source Server Version : 50744 (5.7.44-log)
 Source Host           : localhost:3306
 Source Schema         : code2025

 Target Server Type    : MySQL
 Target Server Version : 50744 (5.7.44-log)
 File Encoding         : 65001

 Date: 28/05/2025 21:54:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'account',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'password',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'name',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username_index`(`username`) USING BTREE COMMENT 'account'
) ENGINE = InnoDB AUTO_INCREMENT = 304 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'account info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'admin', 'admin', '24234234', 'admin@admin.com');
INSERT INTO `admin` VALUES (3, 'admin1', 'ssdsd', 'sdsd', 'sdsd', 'sadas@sds.com');
INSERT INTO `admin` VALUES (204, 'adminn1', 'password1', '张三', '13800138001', 'zhangsan@example.com');
INSERT INTO `admin` VALUES (205, 'admin2', 'password2', '李四', '13800138002', 'lisi@example.com');
INSERT INTO `admin` VALUES (206, 'admin3', 'password3', '王五', '13800138003', 'wangwu@example.com');
INSERT INTO `admin` VALUES (207, 'admin4', 'password4', '赵六', '13800138004', 'zhaoliu@example.com');
INSERT INTO `admin` VALUES (208, 'admin5', 'password5', '钱七', '13800138005', 'qianqi@example.com');
INSERT INTO `admin` VALUES (209, 'admin6', 'password6', '孙八', '13800138006', 'sunba@example.com');
INSERT INTO `admin` VALUES (210, 'admin7', 'password7', '周九', '13800138007', 'zhoujiu@example.com');
INSERT INTO `admin` VALUES (211, 'admin8', 'password8', '吴十', '13800138008', 'wushi@example.com');
INSERT INTO `admin` VALUES (212, 'admin9', 'password9', '郑十一', '13800138009', 'zhengshiyi@example.com');
INSERT INTO `admin` VALUES (213, 'admin10', 'password10', '王十二', '13800138010', 'wangshier@example.com');
INSERT INTO `admin` VALUES (214, 'admin11', 'password11', '李十三', '13800138011', 'lishisan@example.com');
INSERT INTO `admin` VALUES (215, 'admin12', 'password12', '张十四', '13800138012', 'zhangshisi@example.com');
INSERT INTO `admin` VALUES (216, 'admin13', 'password13', '刘十五', '13800138013', 'liushiwu@example.com');
INSERT INTO `admin` VALUES (217, 'admin14', 'password14', '陈十六', '13800138014', 'chenshiliu@example.com');
INSERT INTO `admin` VALUES (218, 'admin15', 'password15', '杨十七', '13800138015', 'yangshigi@example.com');
INSERT INTO `admin` VALUES (219, 'admin16', 'password16', '黄十八', '13800138016', 'huangshiba@example.com');
INSERT INTO `admin` VALUES (220, 'admin17', 'password17', '赵十九', '13800138017', 'zhaoshijiu@example.com');
INSERT INTO `admin` VALUES (221, 'admin18', 'password18', '周二十', '13800138018', 'zhouershi@example.com');
INSERT INTO `admin` VALUES (222, 'admin19', 'password19', '吴二一', '13800138019', 'wuerqi@example.com');
INSERT INTO `admin` VALUES (223, 'admin20', 'password20', '郑二二', '13800138020', 'zhengerer@example.com');
INSERT INTO `admin` VALUES (224, 'admin21', 'password21', '王二三', '13800138021', 'wangersan@example.com');
INSERT INTO `admin` VALUES (225, 'admin22', 'password22', '李二四', '13800138022', 'lierqi@example.com');
INSERT INTO `admin` VALUES (226, 'admin23', 'password23', '张二五', '13800138023', 'zhangerwu@example.com');
INSERT INTO `admin` VALUES (227, 'admin24', 'password24', '刘二六', '13800138024', 'liuerliu@example.com');
INSERT INTO `admin` VALUES (228, 'admin25', 'password25', '陈二七', '13800138025', 'chenerqi@example.com');
INSERT INTO `admin` VALUES (229, 'admin26', 'password26', '杨二八', '13800138026', 'yangerba@example.com');
INSERT INTO `admin` VALUES (230, 'admin27', 'password27', '黄二九', '13800138027', 'huangerjiu@example.com');
INSERT INTO `admin` VALUES (231, 'admin28', 'password28', '赵三十', '13800138028', 'zhaosanshi@example.com');
INSERT INTO `admin` VALUES (232, 'admin29', 'password29', '周三一', '13800138029', 'zhousanyi@example.com');
INSERT INTO `admin` VALUES (233, 'admin30', 'password30', '吴三二', '13800138030', 'wusaner@example.com');
INSERT INTO `admin` VALUES (234, 'admin31', 'password31', '郑三三', '13800138031', 'zhengsansan@example.com');
INSERT INTO `admin` VALUES (235, 'admin32', 'password32', '王三四', '13800138032', 'wangsansi@example.com');
INSERT INTO `admin` VALUES (236, 'admin33', 'password33', '李三五', '13800138033', 'lisanwu@example.com');
INSERT INTO `admin` VALUES (237, 'admin34', 'password34', '张三六', '13800138034', 'zhangsanliu@example.com');
INSERT INTO `admin` VALUES (238, 'admin35', 'password35', '刘三七', '13800138035', 'liusanqi@example.com');
INSERT INTO `admin` VALUES (239, 'admin36', 'password36', '陈三八', '13800138036', 'chensanba@example.com');
INSERT INTO `admin` VALUES (240, 'admin37', 'password37', '杨三九', '13800138037', 'yangsanjiu@example.com');
INSERT INTO `admin` VALUES (241, 'admin38', 'password38', '黄四十', '13800138038', 'huangsishi@example.com');
INSERT INTO `admin` VALUES (242, 'admin39', 'password39', '赵四一', '13800138039', 'zhaosiqi@example.com');
INSERT INTO `admin` VALUES (243, 'admin40', 'password40', '周四二', '13800138040', 'zhousier@example.com');
INSERT INTO `admin` VALUES (244, 'admin41', 'password41', '吴四三', '13800138041', 'wusisan@example.com');
INSERT INTO `admin` VALUES (245, 'admin42', 'password42', '郑四四', '13800138042', 'zhengsisi@example.com');
INSERT INTO `admin` VALUES (246, 'admin43', 'password43', '王四五', '13800138043', 'wangsiwu@example.com');
INSERT INTO `admin` VALUES (247, 'admin44', 'password44', '李四六', '13800138044', 'lisiliu@example.com');
INSERT INTO `admin` VALUES (248, 'admin45', 'password45', '张四七', '13800138045', 'zhangsiqi@example.com');
INSERT INTO `admin` VALUES (249, 'admin46', 'password46', '刘四八', '13800138046', 'liusiba@example.com');
INSERT INTO `admin` VALUES (250, 'admin47', 'password47', '陈四九', '13800138047', 'chensijiu@example.com');
INSERT INTO `admin` VALUES (251, 'admin48', 'password48', '杨五十', '13800138048', 'yangwushi@example.com');
INSERT INTO `admin` VALUES (252, 'admin49', 'password49', '黄五一', '13800138049', 'huangwuyi@example.com');
INSERT INTO `admin` VALUES (253, 'admin50', 'password50', '赵五二', '13800138050', 'zhaowuer@example.com');
INSERT INTO `admin` VALUES (254, 'admin51', 'password51', '周五三', '13800138051', 'zhouwusan@example.com');
INSERT INTO `admin` VALUES (255, 'admin52', 'password52', '吴五四', '13800138052', 'wuwusi@example.com');
INSERT INTO `admin` VALUES (256, 'admin53', 'password53', '郑五五', '13800138053', 'zhengwuwu@example.com');
INSERT INTO `admin` VALUES (257, 'admin54', 'password54', '王五六', '13800138054', 'wangwuli@example.com');
INSERT INTO `admin` VALUES (258, 'admin55', 'password55', '李五七', '13800138055', 'liwugi@example.com');
INSERT INTO `admin` VALUES (259, 'admin56', 'password56', '张五八', '13800138056', 'zhangwuba@example.com');
INSERT INTO `admin` VALUES (260, 'admin57', 'password57', '刘五九', '13800138057', 'liuwujiu@example.com');
INSERT INTO `admin` VALUES (261, 'admin58', 'password58', '陈六十', '13800138058', 'chenliushi@example.com');
INSERT INTO `admin` VALUES (262, 'admin59', 'password59', '杨六一', '13800138059', 'yangliuyi@example.com');
INSERT INTO `admin` VALUES (263, 'admin60', 'password60', '黄六二', '13800138060', 'huangliuer@example.com');
INSERT INTO `admin` VALUES (264, 'admin61', 'password61', '赵六三', '13800138061', 'zhaoliusan@example.com');
INSERT INTO `admin` VALUES (265, 'admin62', 'password62', '周六四', '13800138062', 'zhouliusi@example.com');
INSERT INTO `admin` VALUES (266, 'admin63', 'password63', '吴六五', '13800138063', 'wuliugwu@example.com');
INSERT INTO `admin` VALUES (267, 'admin64', 'password64', '郑六六', '13800138064', 'zhengliuliu@example.com');
INSERT INTO `admin` VALUES (268, 'admin65', 'password65', '王六七', '13800138065', 'wangliuqi@example.com');
INSERT INTO `admin` VALUES (269, 'admin66', 'password66', '李六八', '13800138066', 'liliuba@example.com');
INSERT INTO `admin` VALUES (270, 'admin67', 'password67', '张六九', '13800138067', 'zhangliujiu@example.com');
INSERT INTO `admin` VALUES (271, 'admin68', 'password68', '刘七十', '13800138068', 'liuqishi@example.com');
INSERT INTO `admin` VALUES (272, 'admin69', 'password69', '陈七一', '13800138069', 'chenqiyi@example.com');
INSERT INTO `admin` VALUES (273, 'admin70', 'password70', '杨七二', '13800138070', 'yangqier@example.com');
INSERT INTO `admin` VALUES (274, 'admin71', 'password71', '黄七三', '13800138071', 'huangqisan@example.com');
INSERT INTO `admin` VALUES (275, 'admin72', 'password72', '赵七四', '13800138072', 'zhaoqisi@example.com');
INSERT INTO `admin` VALUES (276, 'admin73', 'password73', '周七五', '13800138073', 'zhouqiwu@example.com');
INSERT INTO `admin` VALUES (277, 'admin74', 'password74', '吴七六', '13800138074', 'wuqiliu@example.com');
INSERT INTO `admin` VALUES (278, 'admin75', 'password75', '郑七七', '13800138075', 'zhengqiqi@example.com');
INSERT INTO `admin` VALUES (279, 'admin76', 'password76', '王七八', '13800138076', 'wangqiba@example.com');
INSERT INTO `admin` VALUES (280, 'admin77', 'password77', '李七九', '13800138077', 'liqijiu@example.com');
INSERT INTO `admin` VALUES (281, 'admin78', 'password78', '张八十', '13800138078', 'zhangbashi@example.com');
INSERT INTO `admin` VALUES (282, 'admin79', 'password79', '刘八一', '13800138079', 'liubayi@example.com');
INSERT INTO `admin` VALUES (283, 'admin80', 'password80', '陈八二', '13800138080', 'chenbaer@example.com');
INSERT INTO `admin` VALUES (284, 'admin81', 'password81', '杨八三', '13800138081', 'yangbasan@example.com');
INSERT INTO `admin` VALUES (285, 'admin82', 'password82', '黄八四', '13800138082', 'huangbasi@example.com');
INSERT INTO `admin` VALUES (286, 'admin83', 'password83', '赵八五', '13800138083', 'zhaobawu@example.com');
INSERT INTO `admin` VALUES (287, 'admin84', 'password84', '周八六', '13800138084', 'zhoubaliu@example.com');
INSERT INTO `admin` VALUES (288, 'admin85', 'password85', '吴八七', '13800138085', 'wubaqi@example.com');
INSERT INTO `admin` VALUES (289, 'admin86', 'password86', '郑八八', '13800138086', 'zhengbaba@example.com');
INSERT INTO `admin` VALUES (290, 'admin87', 'password87', '王八九', '13800138087', 'wangbajiu@example.com');
INSERT INTO `admin` VALUES (291, 'admin88', 'password88', '李九十', '13800138088', 'lijushi@example.com');
INSERT INTO `admin` VALUES (292, 'admin89', 'password89', '张九一', '13800138089', 'zhangjiuyi@example.com');
INSERT INTO `admin` VALUES (293, 'admin90', 'password90', '刘九二', '13800138090', 'liujuer@example.com');
INSERT INTO `admin` VALUES (294, 'admin91', 'password91', '陈九三', '13800138091', 'chenjiusan@example.com');
INSERT INTO `admin` VALUES (295, 'admin92', 'password92', '杨九四', '13800138092', 'yangjiusi@example.com');
INSERT INTO `admin` VALUES (296, 'admin93', 'password93', '黄九五', '13800138093', 'huangjiuwu@example.com');
INSERT INTO `admin` VALUES (297, 'admin94', 'password94', '赵九六', '13800138094', 'zhaojiuliu@example.com');
INSERT INTO `admin` VALUES (298, 'admin95', 'password95', '周九七', '13800138095', 'zhoujiuqi@example.com');
INSERT INTO `admin` VALUES (299, 'admin96', 'password96', '吴九八', '13800138096', 'wujiuba@example.com');
INSERT INTO `admin` VALUES (300, 'admin97', 'password97', '郑九九', '13800138097', 'zhengjiujiu@example.com');
INSERT INTO `admin` VALUES (301, 'admin98', 'password98', '王一百', '13800138098', 'wangyibai@example.com');
INSERT INTO `admin` VALUES (302, 'admin99', 'password99', '李一零一', '13800138099', 'liyilingyi@example.com');
INSERT INTO `admin` VALUES (303, 'admin100', 'password100', '张一零二', '13800138100', 'zhangyilinger@example.com');

SET FOREIGN_KEY_CHECKS = 1;
