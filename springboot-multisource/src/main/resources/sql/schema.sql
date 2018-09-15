-- -------------------------------------以下是pos业务库开始-------------------------------------------
CREATE DATABASE IF NOT EXISTS pos default charset utf8 COLLATE utf8_general_ci;
SET FOREIGN_KEY_CHECKS=0;
USE pos;

-- 后台管理用户表
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id`                        INT(11) PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  `username`                  VARCHAR(32) NOT NULL COMMENT '账号',
  `name`                      VARCHAR(16) DEFAULT '' COMMENT '名字',
  `password`                  VARCHAR(128) DEFAULT '' COMMENT '密码',
  `salt`                      VARCHAR(64) DEFAULT '' COMMENT 'md5密码盐',
  `phone`                     VARCHAR(32) DEFAULT '' COMMENT '联系电话',
  `tips`                      VARCHAR(255) COMMENT '备注',
  `state`                     TINYINT(1) DEFAULT 1 COMMENT '状态 1:正常 2:禁用',
  `created_time`              DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time`              DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='后台管理用户表';

-- 下面是pos数据库中的插入数据
INSERT INTO `t_user` VALUES (1,'admin','系统管理员','123456','www', '17890908889', '系统管理员', 1, '2017-12-12 09:46:12', '2017-12-12 09:46:12');
INSERT INTO `t_user` VALUES (2,'aix','张三','123456','eee', '17859569358', '', 1, '2017-12-12 09:46:12', '2017-12-12 09:46:12');


-- -------------------------------------以下biz业务库开始-------------------------------------------
CREATE DATABASE IF NOT EXISTS biz default charset utf8 COLLATE utf8_general_ci;
SET FOREIGN_KEY_CHECKS=0;
USE biz;

-- 后台管理用户表
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id`                        INT(11) PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  `username`                  VARCHAR(32) NOT NULL COMMENT '账号',
  `name`                      VARCHAR(16) DEFAULT '' COMMENT '名字',
  `password`                  VARCHAR(128) DEFAULT '' COMMENT '密码',
  `salt`                      VARCHAR(64) DEFAULT '' COMMENT 'md5密码盐',
  `phone`                     VARCHAR(32) DEFAULT '' COMMENT '联系电话',
  `tips`                      VARCHAR(255) COMMENT '备注',
  `state`                     TINYINT(1) DEFAULT 1 COMMENT '状态 1:正常 2:禁用',
  `created_time`              DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time`              DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='后台管理用户表';


-- 下面是biz数据库中的插入数据
INSERT INTO `t_user` VALUES (1,'admin1','系统管理员','123456','www', '17890908889', '系统管理员', 1, '2017-12-12 09:46:12', '2017-12-12 09:46:12');
INSERT INTO `t_user` VALUES (2,'aix1','张三','123456','eee', '17859569358', '', 1, '2017-12-12 09:46:12', '2017-12-12 09:46:12');
