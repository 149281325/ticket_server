/* 
创建票务系统业务数据表的脚本。
表的设计不指定外键约束以保持灵活性与性能。

执行sql脚本,可以有2种方法:
  第一种方法:
 在命令行下(未连接数据库),输入 mysql -h localhost -u root -pwelcome1 -Dticket < D:/CP_System/ticket.mysql.create.sql
  第二种方法:
 在命令行下(已连接数据库,此时的提示符为 mysql> ),输入 source D:/CP_System/ticket.mysql.create.sql 或者 \. D:/CP_System/ticket.mysql.create.sql

附：在windows上修改MySQL root@localhost密码的方法：
D:\mysql-5.6.24-win32\bin> mysqld --init-file=D:/CP_System/mysql-init.txt
D:\mysql-5.6.24-win32\bin>mysql -u root -p
*/

/*
CREATE DATABASE ticket;
USE ticket;
*/

DROP TABLE T_USER;
DROP TABLE T_CARD;
DROP TABLE T_ACTIVITY;
DROP TABLE T_ORDER;
DROP TABLE T_REGISTRY;


/*
用户表：
  用户编号
  名称（邮箱或手机号或用户名）
  昵称
  用户类型（参考用户类型表）
  用户状态（参考状态表）
  账户余额
  扩展属性
  备注
*/
CREATE TABLE T_USER (
  ID INT NOT NULL,
  NAME VARCHAR(30) NOT NULL,
  PASSWORD VARCHAR(30) NOT NULL,
  NICK VARCHAR(30),
  USER_TYPE INT DEFAULT 1,
  USER_STATE INT DEFAULT 1,
  BALANCE DOUBLE DEFAULT 0,
  EXT_PROPS VARCHAR(512),
  NOTE VARCHAR(512),
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE T_USER MODIFY ID INT NOT NULL AUTO_INCREMENT;
ALTER TABLE T_USER AUTO_INCREMENT=100;


/*
卡表（唔..）：
  卡号
  卡类型（参考卡类型表）
  状态（参考状态表）
  余额
  有效期开始
  有效期截止
  剩余可使用次数
  扩展属性
*/
CREATE TABLE T_CARD (
  ID BIGINT NOT NULL,
  CARD_TYPE INT NOT NULL,
  CARD_STATE INT DEFAULT 1,
  BALANCE DOUBLE DEFAULT 0,
  VALID_FROM DATETIME,
  VALID_TO DATETIME,
  REMAIN_TIMES INT,
  EXT_PROPS VARCHAR(512),
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*
活动表：
  活动编号（一系列活动视为多个活动）
  名称
  状态（参考状态表）
  地址
  起始日期
  终止日期
  主办方
  扩展属性
  备注
*/
CREATE TABLE T_ACTIVITY (
  ID INT NOT NULL,
  NAME VARCHAR(50) NOT NULL,
  ACTIVITY_STATE INT DEFAULT 1,
  ADDRESS VARCHAR(300),
  TIME_FROM DATETIME,
  TIME_TO DATETIME,
  OWNER VARCHAR(300),
  EXT_PROPS VARCHAR(512),
  NOTE VARCHAR(512),
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE T_ACTIVITY MODIFY ID INT NOT NULL AUTO_INCREMENT;
ALTER TABLE T_ACTIVITY AUTO_INCREMENT=1000;

/*
订单表（入场记录）：
  订单号
  用户号
  卡号
  活动号
  应付
  实付
  支付方式（参考支付类型表）
  优惠券码
  折扣，支持70%（七折），-20（立减20）等格式
  折扣信息
  订单状态（参考状态表）
  扩展属性
  备注
*/
CREATE TABLE T_ORDER (
  ID INT NOT NULL,
  USER_ID INT,
  CARD_ID BIGINT,
  ACTIVITY_ID INT NOT NULL,
  PRICE DOUBLE,
  PAY DOUBLE,
  PAY_TYPE INT,
  COUPON_CODE VARCHAR(30),
  DISCOUNT VARCHAR(10),
  DISCOUNT_REASON VARCHAR(30),
  ORDER_STATE INT DEFAULT 101,
  EXT_PROPS VARCHAR(512),
  NOTE VARCHAR(512),
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE T_ORDER MODIFY ID INT NOT NULL AUTO_INCREMENT;
ALTER TABLE T_ORDER AUTO_INCREMENT=10;

/*
报名表（意向表，注册表），该表可选，用于用户统计
用user id和card id任选其一报名一个活动
*/
CREATE TABLE T_REGISTRY (
  ID INT NOT NULL AUTO_INCREMENT,
  USER_ID INT,
  CARD_ID BIGINT,
  ACTIVITY_ID INT NOT NULL,
  EXT_PROPS VARCHAR(512),
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
