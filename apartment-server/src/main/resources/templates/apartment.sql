create table apartment
(
  id         bigint auto_increment
    primary key,
  userid     bigint                              null comment '租户id',
  houseType  varchar(45)                         null comment '户型',
  roomArea   int                                 null comment '房间面积',
  face       varchar(45)                         null comment '朝向',
  price      int                                 null comment '对外标价',
  pattern    varchar(45)                         null comment '支付模式（如押一付一）',
  address    varchar(255)                        null comment '公寓地址',
  status     int       default 0                 null comment '状态：0->未出租，1->已出租',
  createtime timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '创建时间'
)
  comment '公寓信息' charset = utf8;

INSERT INTO apartment.apartment (userid, houseType, roomArea, face, price, pattern, address, status, createtime) VALUES (2, '复式', 34, '朝西北', 2300, '押一付四', '深证市友和道通集团', 0, '2019-08-21 16:25:12');
INSERT INTO apartment.apartment (userid, houseType, roomArea, face, price, pattern, address, status, createtime) VALUES (2, '复式', 12, '朝北', 800, '押一付三', '上海市青浦区崧泽大道 7509 号友和道通', 0, '2019-08-21 16:25:12');
INSERT INTO apartment.apartment (userid, houseType, roomArea, face, price, pattern, address, status, createtime) VALUES (2, '复式', 95, '朝东南', 6320, '押一付四', '上海市青浦区崧泽大道 7509 号', 0, '2019-08-21 16:25:12');
INSERT INTO apartment.apartment (userid, houseType, roomArea, face, price, pattern, address, status, createtime) VALUES (2, '独栋别墅', 230, '朝南', 63200, '押六付六', '上海市青浦区崧泽大道 7509 号', 1, '2019-08-21 16:25:12');
INSERT INTO apartment.apartment (userid, houseType, roomArea, face, price, pattern, address, status, createtime) VALUES (2, '跃式', 125, '朝东', 6500, '押三付二', '上海市青浦区崧泽大道', 1, '2019-08-21 16:25:12');
INSERT INTO apartment.apartment (userid, houseType, roomArea, face, price, pattern, address, status, createtime) VALUES (2, '复式', 37, '朝南', 10, '押一付四', 'test底子', 1, '2019-08-21 16:25:12');
create table apartment_user
(
  id         bigint auto_increment
    primary key,
  name       varchar(45)                         null comment '姓名',
  sex        int       default 1                 null comment '性别 ：2 女， 1 男',
  phone      varchar(45)                         null comment '电话
',
  idNumber   varchar(45)                         null comment '证件号',
  idType     varchar(45)                         null comment '证件类型',
  isLive     int       default 0                 null comment '居住状态',
  createtime timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '创建时间'
)
  comment '租户信息' charset = utf8;

INSERT INTO apartment.apartment_user (name, sex, phone, idNumber, idType, isLive, createtime) VALUES ('test', 2, '17890345673', '243523452', '退伍证', 1, '2019-08-21 15:15:12');
INSERT INTO apartment.apartment_user (name, sex, phone, idNumber, idType, isLive, createtime) VALUES ('test1', 1, '17890345672', '6584567', '学生证', 1, '2019-08-21 15:15:12');
INSERT INTO apartment.apartment_user (name, sex, phone, idNumber, idType, isLive, createtime) VALUES ('test', 2, '17890345672', '7465856', '身份证', 0, '2019-08-21 15:15:12');
INSERT INTO apartment.apartment_user (name, sex, phone, idNumber, idType, isLive, createtime) VALUES ('良民', 1, '123', '3246734562543', '工作证', 0, '2019-08-21 15:15:12');
INSERT INTO apartment.apartment_user (name, sex, phone, idNumber, idType, isLive, createtime) VALUES ('潘大佬', 1, '217', '123', '工作证', 0, '2019-08-21 15:15:12');
INSERT INTO apartment.apartment_user (name, sex, phone, idNumber, idType, isLive, createtime) VALUES ('阿苏勒', 1, '15328974709', '2134123123', '驾驶证', 0, '2019-08-21 15:15:12');
create table file
(
  id        bigint auto_increment
    primary key,
  username  varchar(45)  null comment '报修租户名称',
  resource  varchar(255) null comment '文件地址',
  filetype  varchar(45)  null comment '文件类型',
  repairsid bigint       null comment '报修表 id',
  userid    bigint       null comment '报修租户id'
)
  comment '文件表' charset = utf8;

INSERT INTO apartment.file (username, resource, filetype, repairsid, userid) VALUES ('test', 'https://images2018.cnblogs.com/blog/1103120/201807/1103120-20180711165502550-259548235.png', 'png', 1, 1);
create table repairs
(
  id               bigint auto_increment
    primary key,
  userid           bigint                                not null comment '租户id',
  username         varchar(45)                           not null comment '报修租户姓名',
  apartmentid      bigint                                not null comment '报修公寓 id',
  apartmentAddress varchar(255)                          null comment '报修公寓地址',
  content          varchar(45)                           null comment '文本描述内容',
  createtime       timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '创建时间',
  conductTime      timestamp                             null comment '处理时间',
  hasfile          int                                   null comment '是否有文件',
  status           varchar(45) default '未修理'             not null comment '报修状态，默认值：未修理'
)
  comment '报修表' charset = utf8;

INSERT INTO apartment.repairs (userid, username, apartmentid, apartmentAddress, content, createtime, conductTime, hasfile, status) VALUES (8, 'test', 9, '公寓地址', '测试文本内容', '2019-08-21 13:00:00', '2019-08-23 10:03:09', 1, '未修理');
create table ums_admin
(
  id          bigint auto_increment
    primary key,
  username    varchar(64)      null,
  password    varchar(64)      null,
  email       varchar(100)     null comment '邮箱',
  note        varchar(500)     null comment '备注信息',
  create_time datetime         null comment '创建时间',
  login_time  datetime         null comment '最后登录时间',
  status      int(1) default 1 null comment '帐号启用状态：0->禁用；1->启用'
)
  comment '后台用户表' charset = utf8;

INSERT INTO apartment.ums_admin (username, password, email, note, create_time, login_time, status) VALUES ('admin', '123456', 'liangming@qq.com', '系统管理员', '2019-10-08 13:32:47', '2019-03-20 15:38:50', 1);
create table ums_role
(
  id          bigint auto_increment
    primary key,
  name        varchar(100)     null comment '名称',
  value       varchar(500)     not null comment '权限',
  admin_count int              null comment '后台用户数量',
  create_time datetime         null comment '创建时间',
  status      int(1) default 1 null comment '启用状态：0->禁用；1->启用',
  sort        int    default 0 null,
  description varchar(500)     null comment '描述',
  admin_id    bigint           null comment '外键：管理员表 id'
)
  comment '后台用户角色表' charset = utf8;

INSERT INTO apartment.ums_role (name, value, admin_count, create_time, status, sort, description, admin_id) VALUES ('系统管理员', '/**', 0, '2019-09-30 15:46:11', 1, 0, '系统管理员', 1);