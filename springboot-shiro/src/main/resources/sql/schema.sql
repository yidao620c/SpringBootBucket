# -------------------------------------以下业务表开始-------------------------------------------
# CREATE DATABASE IF NOT EXISTS pos default charset utf8 COLLATE utf8_general_ci;
# SET FOREIGN_KEY_CHECKS=0;
# USE pos;

# -------------------------------------以下用户管理表开始-------------------------------------------

-- 后台管理用户表
DROP TABLE IF EXISTS `t_manager`;
CREATE TABLE `t_manager` (
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
INSERT INTO `t_manager` VALUES (1,'admin','系统管理员','4a496ba2a4172c71540fa643ddc8bb7c','b4752b4b73034de06afb2db30fe19061', '17890908889', '系统管理员', 1, '2017-12-12 09:46:12', '2017-12-12 09:46:12');
INSERT INTO `t_manager` VALUES (2,'aix','张三','2412d3972722eb186f69a8f4011fbd48','20545a7eaea0241ddf6652a3f9a4ae24', '17859569358', '', 1, '2017-12-12 09:46:12', '2017-12-12 09:46:12');

-- 角色表
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id`                        INT(11) PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  `role`                      VARCHAR(64) DEFAULT '' COMMENT '角色名称',
  `description`               VARCHAR(255) DEFAULT '' COMMENT '角色说明',
  `created_time`              DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time`              DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='角色表';
INSERT INTO `t_role` VALUES (1,'admin','超级管理员', '2017-12-12 09:46:12', '2017-12-12 09:46:12');
INSERT INTO `t_role` VALUES (2,'aix','系统监控员', '2017-12-12 09:46:12', '2017-12-12 09:46:12');

-- 用户角色关联表
DROP TABLE IF EXISTS `t_manager_role`;
CREATE TABLE `t_manager_role` (
  `id`                        INT(11) PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  `manager_id`                INT(11) NOT NULL COMMENT '管理用户ID',
  `role_id`                   INT(11) NOT NULL COMMENT '角色ID',
  `created_time`              DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time`              DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';
INSERT INTO `t_manager_role` VALUES (1, 1, 1, '2017-05-05 00:00:00','2017-05-05 00:00:00');
INSERT INTO `t_manager_role` VALUES (2, 2, 2, '2017-05-05 00:00:00','2017-05-05 00:00:00');

-- 权限表
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id`                        INT(11) PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  `permission`                VARCHAR(64) DEFAULT '' COMMENT '权限名称',
  `description`               VARCHAR(255) DEFAULT '' COMMENT '权限说明',
  `created_time`              DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time`              DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='权限表';
INSERT INTO `t_permission` VALUES (1,'permission:admin','超级管理权限', '2017-12-12 09:46:12', '2017-12-12 09:46:12');
INSERT INTO `t_permission` VALUES (2,'permission:aix','监控权限', '2017-12-12 09:46:12', '2017-12-12 09:46:12');
INSERT INTO `t_permission` VALUES (3,'permission:adduser','添加用户权限', '2017-12-12 09:46:12', '2017-12-12 09:46:12');

-- 角色权限关联表
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `id`                        INT(11) PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  `role_id`                   INT(11) NOT NULL COMMENT '角色ID',
  `permission_id`             INT(11) NOT NULL COMMENT '权限ID',
  `created_time`              DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time`              DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';
INSERT INTO `t_role_permission` VALUES (1, 1, 1, '2017-12-12 09:46:12', '2017-12-12 09:46:12');
INSERT INTO `t_role_permission` VALUES (2, 1, 3, '2017-12-12 09:46:12', '2017-12-12 09:46:12');
INSERT INTO `t_role_permission` VALUES (3, 2, 2, '2017-12-12 09:46:12', '2017-12-12 09:46:12');

-- 项目用户关联表
DROP TABLE IF EXISTS `t_project_user`;
CREATE TABLE `t_project_user` (
  `id`                        INT(11) PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  `user_id`                   INT(11) NOT NULL COMMENT '用户ID',
  `project_id`                INT(11) NOT NULL COMMENT '项目ID',
  `created_time`              DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time`              DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='项目用户关联表';
