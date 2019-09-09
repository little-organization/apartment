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
INSERT INTO apartment.apartment (userid, houseType, roomArea, face, price, pattern, address, status, createtime) VALUES (2, '复式', 95, '朝东南', 6320, '押一付四', '上海市青浦区崧泽大道 7509 号', 1, '2019-08-21 16:25:12');
INSERT INTO apartment.apartment (userid, houseType, roomArea, face, price, pattern, address, status, createtime) VALUES (2, '独栋别墅', 230, '朝南', 63200, '押六付六', '上海市青浦区崧泽大道 7509 号', 1, '2019-08-21 16:25:12');
INSERT INTO apartment.apartment (userid, houseType, roomArea, face, price, pattern, address, status, createtime) VALUES (2, '跃式', 125, '朝东', 6500, '押三付二', '上海市青浦区崧泽大道', 1, '2019-08-21 16:25:12');
create table apartment_repair
(
  id         bigint auto_increment
    primary key,
  name       varchar(45)                         null comment '姓名',
  sex        int       default 1                 null comment '性别 ：2 女， 1 男',
  phone      varchar(45)                         null comment '电话
',
  idNumber   varchar(45)                         null comment '证件号',
  idType     varchar(45)                         null comment '证件类型',
  isLive     int       default 0                 null comment '状态 0=>在职，1=》离职',
  createtime timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '创建时间'
)
  comment '售后人员信息' charset = utf8;

INSERT INTO apartment.apartment_repair (name, sex, phone, idNumber, idType, isLive, createtime) VALUES ('售后', 1, '127621966839', '4235342625', '身份证', 1, '2019-09-09 06:33:23');
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
INSERT INTO apartment.apartment_user (name, sex, phone, idNumber, idType, isLive, createtime) VALUES ('asdf', 1, '21234412', '412341234', '工作证', 0, '2019-09-08 10:39:49');
create table file
(
  id        bigint auto_increment
    primary key,
  username  varchar(45)  null comment '报修租户名称',
  resource  varchar(255) null comment '文件地址',
  filetype  varchar(45)  null comment '文件类型',
  repairsid bigint       null comment '报修表 id',
  userid    bigint       null comment '报修租户id',
  filename  varchar(64)  null comment '文件名称'
)
  comment '文件表' charset = utf8;

INSERT INTO apartment.file (username, resource, filetype, repairsid, userid, filename) VALUES ('admin1', '/images/1567391605258297672853.jpg', 'jpg', 12, 2, '');
INSERT INTO apartment.file (username, resource, filetype, repairsid, userid, filename) VALUES ('admin1', '/images/1567391607190-1046328446.jpg', 'jpg', 12, 2, '');
INSERT INTO apartment.file (username, resource, filetype, repairsid, userid, filename) VALUES ('admin1', '/images/15673916037761566496170.jpg', 'jpg', 12, 2, '');
INSERT INTO apartment.file (username, resource, filetype, repairsid, userid, filename) VALUES ('admin1', '/images/1567391603317-356352297.jpg', 'jpg', 12, 2, '');
INSERT INTO apartment.file (username, resource, filetype, repairsid, userid, filename) VALUES ('admin1', '/images/15673916033141273699936.jpg', 'jpg', 12, 2, '');
INSERT INTO apartment.file (username, resource, filetype, repairsid, userid, filename) VALUES ('admin1', '/images/1567428590734-222436281.jpg', 'jpg', 13, 2, '');
INSERT INTO apartment.file (username, resource, filetype, repairsid, userid, filename) VALUES ('admin1', '/images/1567428597010-499036550.jpg', 'jpg', 13, 2, '');
INSERT INTO apartment.file (username, resource, filetype, repairsid, userid, filename) VALUES ('admin1', '/images/1567428597207449375115.jpg', 'jpg', 13, 2, '');
INSERT INTO apartment.file (username, resource, filetype, repairsid, userid, filename) VALUES ('admin1', '/images/1567428597260-1909207601.jpg', 'jpg', 13, 2, '');
INSERT INTO apartment.file (username, resource, filetype, repairsid, userid, filename) VALUES ('admin1', '/images/1567428893991-222436281.jpg', 'jpg', 14, 2, '');
INSERT INTO apartment.file (username, resource, filetype, repairsid, userid, filename) VALUES ('admin1', '/images/1567428895050-1909207601.jpg', 'jpg', 14, 2, '');
INSERT INTO apartment.file (username, resource, filetype, repairsid, userid, filename) VALUES ('admin1', '/images/1567428898308-499036550.jpg', 'jpg', 14, 2, '');
INSERT INTO apartment.file (username, resource, filetype, repairsid, userid, filename) VALUES ('admin1', '/images/1567428899829449375115.jpg', 'jpg', 14, 2, '');
INSERT INTO apartment.file (username, resource, filetype, repairsid, userid, filename) VALUES ('admin1', '/images/1567429086356-222436281.jpg', 'jpg', 15, 2, '');
INSERT INTO apartment.file (username, resource, filetype, repairsid, userid, filename) VALUES ('admin1', '/images/1567429087261-1909207601.jpg', 'jpg', 15, 2, '');
INSERT INTO apartment.file (username, resource, filetype, repairsid, userid, filename) VALUES ('admin1', '/images/1567429088787-376898486.jpg', 'jpg', 15, 2, '');
INSERT INTO apartment.file (username, resource, filetype, repairsid, userid, filename) VALUES ('admin1', '/images/1567429091201449375115.jpg', 'jpg', 15, 2, '');
create table msg_sign
(
  SignName   varchar(128)                        not null comment '短信签名。'
    primary key,
  SignStatus int(1)                              null comment '签名审核状态。其中：

0：审核中。
1：审核通过。
2：审核失败，请在返回参数Reason中查看审核失败原因。',
  Reason     varchar(500)                        null comment '审核备注。

如果审核状态为审核通过或审核中，参数Reason显示为“无审核备注”。
如果审核状态为审核未通过，参数Reason显示审核的具体原因。',
  Message    varchar(45)                         not null comment '状态码描述。',
  CreateDate timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '短信签名的创建日期和时间。',
  Code       varchar(45)                         not null comment '请求状态码。

返回OK代表请求成功。
其他错误码详见错误码列表。',
  RequestId  varchar(64)                         null comment '请求ID。'
)
  charset = utf8;

INSERT INTO apartment.msg_sign (SignName, SignStatus, Reason, Message, CreateDate, Code, RequestId) VALUES ('青沐公寓', 1, '无审核备注', 'OK', '2019-09-05 13:00:07', 'OK', '052CBC2F-E89A-4125-82E1-CD01A2BFD9FD');
create table msg_template
(
  TemplateCode    varchar(64)  not null comment '短信模板CODE。'
    primary key,
  TemplateName    varchar(128) null comment '模板名称。',
  TemplateContent varchar(500) null comment '模板内容。',
  TemplateStatus  int          null comment '模板审核状态。其中：

0：审核中。
1：审核通过。
2：审核失败，请在返回参数Reason中查看审核失败原因。',
  TemplateType    int          null comment '短信类型。其中：

0：验证码。
1：短信通知。
2：推广短信。
3：国际/港澳台消息。',
  Reason          varchar(500) null comment '审核备注。

如果审核状态为审核通过或审核中，参数Reason显示为“无审核备注”。
如果审核状态为审核未通过，参数Reason显示审核的具体原因。',
  Message         varchar(45)  not null comment '状态码的描述。',
  CreateDate      timestamp    null comment '短信模板的创建日期和时间。',
  Code            varchar(45)  null comment '请求状态码。

返回OK代表请求成功。
其他错误码详见错误码列表。',
  RequestId       varchar(64)  null comment '请求ID。'
)
  charset = utf8;

INSERT INTO apartment.msg_template (TemplateCode, TemplateName, TemplateContent, TemplateStatus, TemplateType, Reason, Message, CreateDate, Code, RequestId) VALUES ('SMS_172881631', '冻结模板', '亲爱的青沐公寓租客：您好！您本期的房租已经逾期，由于您未在规定时间内缴纳，您的门锁已被冻结。烦请在48小时内缴纳逾期房租，如未按时缴纳，我们将依照合同约定上门进行清退处理，感谢您的配合。', 1, 1, '无审批备注', 'OK', '2019-08-23 19:02:46', 'OK', '171F8B05-CA8E-4634-BFA8-B71FD2F79699');
INSERT INTO apartment.msg_template (TemplateCode, TemplateName, TemplateContent, TemplateStatus, TemplateType, Reason, Message, CreateDate, Code, RequestId) VALUES ('SMS_172886549', '催收模板', '亲爱的青沐公寓租客：您好！您本期的房租账单已到付款日，烦请在24小时内缴纳。如未按时缴纳，您的门锁将会被系统冻结。若在72小时内未缴纳，我们将依照合同约定，上门进行清退处理。感谢您的配合。', 1, 1, '无审批备注', 'OK', '2019-08-23 19:01:45', 'OK', 'C6D7A2E1-6C5C-4BC7-8EFA-B762E165462A');
INSERT INTO apartment.msg_template (TemplateCode, TemplateName, TemplateContent, TemplateStatus, TemplateType, Reason, Message, CreateDate, Code, RequestId) VALUES ('SMS_173476812', '门锁刷新通知模板', '亲爱的青沐公寓租客：您好！您本期的公寓门锁已经个更新，新的门锁密码请请登录进行查看，感谢您的配合。', 1, 1, '无审批备注', 'OK', '2019-09-05 13:13:42', 'OK', '6DCD75F2-8CEC-492C-9DF7-9D3DDDCBC5C3');
INSERT INTO apartment.msg_template (TemplateCode, TemplateName, TemplateContent, TemplateStatus, TemplateType, Reason, Message, CreateDate, Code, RequestId) VALUES ('SMS_173665282', '验证码模板', '您的验证码${code}，该验证码5分钟内有效，请勿泄漏于他人！', 1, 0, '无审批备注', 'OK', '2019-09-08 15:26:58', 'OK', '5F187BA2-C819-4EBE-895A-7636D14500AB');
create table phone_verify
(
  id         bigint auto_increment
    primary key,
  username   varchar(45)                         null comment '用户名',
  phone      varchar(45)                         null comment '手机号',
  barcode    varchar(45)                         null comment '验证码',
  state      int(1)    default 0                 null comment '0=未使用
1=已使用',
  createtime timestamp default CURRENT_TIMESTAMP not null comment '创建时间'
);

create index phone_verify_code_index
  on phone_verify (barcode);

INSERT INTO apartment.phone_verify (username, phone, barcode, state, createtime) VALUES ('admin', '17621966839', '526432', 0, '2019-09-08 16:50:21');
INSERT INTO apartment.phone_verify (username, phone, barcode, state, createtime) VALUES ('admin', '17621966839', '708415', 1, '2019-09-08 16:59:07');
INSERT INTO apartment.phone_verify (username, phone, barcode, state, createtime) VALUES ('admin', '17621966839', '254003', 0, '2019-09-08 17:00:58');
INSERT INTO apartment.phone_verify (username, phone, barcode, state, createtime) VALUES ('admin', '17621966839', '913432', 0, '2019-09-08 17:02:25');
INSERT INTO apartment.phone_verify (username, phone, barcode, state, createtime) VALUES ('admin', '17621966839', '300422', 1, '2019-09-08 17:42:31');
INSERT INTO apartment.phone_verify (username, phone, barcode, state, createtime) VALUES ('admin', '17621966839', '430835', 1, '2019-09-08 18:23:16');
INSERT INTO apartment.phone_verify (username, phone, barcode, state, createtime) VALUES ('admin', '17621966839', '736355', 0, '2019-09-08 18:24:27');
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
  hasfile          int         default 0                 not null comment '是否有文件',
  status           varchar(45) default '未修理'             not null comment '报修状态，默认值：未修理'
)
  comment '报修表' charset = utf8;

INSERT INTO apartment.repairs (userid, username, apartmentid, apartmentAddress, content, createtime, conductTime, hasfile, status) VALUES (2, 'admin1', 12, '上海市青浦区崧泽大道 7509 号友和道通', '测试', '2019-09-02 10:33:35', null, 1, '未修理');
INSERT INTO apartment.repairs (userid, username, apartmentid, apartmentAddress, content, createtime, conductTime, hasfile, status) VALUES (2, 'admin1', 15, '上海市青浦区崧泽大道 7509 号', '阿萨德', '2019-09-02 20:49:59', null, 1, '未修理');
INSERT INTO apartment.repairs (userid, username, apartmentid, apartmentAddress, content, createtime, conductTime, hasfile, status) VALUES (2, 'admin1', 16, '上海市青浦区崧泽大道', 'sad', '2019-09-02 20:55:01', null, 1, '未修理');
INSERT INTO apartment.repairs (userid, username, apartmentid, apartmentAddress, content, createtime, conductTime, hasfile, status) VALUES (2, 'admin1', 12, '上海市青浦区崧泽大道 7509 号友和道通', '阿斯顿发送到 2', '2019-09-02 20:58:21', '2019-09-03 17:43:40', 1, '进行中');
INSERT INTO apartment.repairs (userid, username, apartmentid, apartmentAddress, content, createtime, conductTime, hasfile, status) VALUES (2, 'admin1', 18, 'test底子', 'asdf', '2019-09-03 16:31:28', '2019-09-03 17:42:57', 0, '未修理');
INSERT INTO apartment.repairs (userid, username, apartmentid, apartmentAddress, content, createtime, conductTime, hasfile, status) VALUES (2, 'admin1', 18, 'test底子', 'asdf', '2019-09-03 16:31:28', '2019-09-03 17:43:46', 0, '未修理');
INSERT INTO apartment.repairs (userid, username, apartmentid, apartmentAddress, content, createtime, conductTime, hasfile, status) VALUES (2, 'admin1', 18, 'test底子', 'asdf', '2019-09-03 16:31:28', '2019-09-03 17:40:41', 0, '已完成');
INSERT INTO apartment.repairs (userid, username, apartmentid, apartmentAddress, content, createtime, conductTime, hasfile, status) VALUES (2, 'admin1', 15, '上海市青浦区崧泽大道 7509 号', 'asdftest', '2019-09-03 16:32:45', '2019-09-03 17:50:17', 0, '未修理');
INSERT INTO apartment.repairs (userid, username, apartmentid, apartmentAddress, content, createtime, conductTime, hasfile, status) VALUES (2, 'admin1', 15, '上海市青浦区崧泽大道 7509 号', 'asdf', '2019-09-03 16:36:15', '2019-09-03 17:49:36', 0, '进行中');
create table repairs_log
(
  logid            bigint auto_increment
    primary key,
  id               bigint                                not null comment '报修 id',
  userid           bigint                                not null comment '租户id',
  username         varchar(45)                           not null comment '报修租户姓名',
  apartmentid      bigint                                not null comment '报修公寓 id',
  apartmentAddress varchar(255)                          null comment '报修公寓地址',
  content          varchar(45)                           null comment '文本描述内容',
  createtime       timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '创建时间',
  conductTime      timestamp                             null comment '处理时间',
  hasfile          int         default 0                 not null comment '是否有文件',
  status           varchar(45) default '未修理'             not null comment '报修状态，默认值：未修理'
)
  comment '报修修改日志表' charset = utf8;

INSERT INTO apartment.repairs_log (id, userid, username, apartmentid, apartmentAddress, content, createtime, conductTime, hasfile, status) VALUES (16, 2, 'admin1', 18, 'test底子', 'asdf', '2019-09-03 16:31:28', '2019-09-03 17:42:57', 0, '未修理');
INSERT INTO apartment.repairs_log (id, userid, username, apartmentid, apartmentAddress, content, createtime, conductTime, hasfile, status) VALUES (16, 2, 'admin1', 18, 'test底子', 'asdf', '2019-09-03 16:31:28', '2019-09-03 17:42:57', 0, '未修理');
INSERT INTO apartment.repairs_log (id, userid, username, apartmentid, apartmentAddress, content, createtime, conductTime, hasfile, status) VALUES (15, 2, 'admin1', 12, '上海市青浦区崧泽大道 7509 号友和道通', '阿斯顿发送到 2', '2019-09-02 20:58:21', '2019-09-03 17:43:40', 1, '进行中');
INSERT INTO apartment.repairs_log (id, userid, username, apartmentid, apartmentAddress, content, createtime, conductTime, hasfile, status) VALUES (17, 2, 'admin1', 18, 'test底子', 'asdf', '2019-09-03 16:31:28', '2019-09-03 17:43:46', 0, '未修理');
INSERT INTO apartment.repairs_log (id, userid, username, apartmentid, apartmentAddress, content, createtime, conductTime, hasfile, status) VALUES (19, 2, 'admin1', 15, '上海市青浦区崧泽大道 7509 号', 'asdftest', '2019-09-03 16:32:45', '2019-09-03 17:47:21', 0, '进行中');
INSERT INTO apartment.repairs_log (id, userid, username, apartmentid, apartmentAddress, content, createtime, conductTime, hasfile, status) VALUES (19, 2, 'admin1', 15, '上海市青浦区崧泽大道 7509 号', 'asdftest', '2019-09-03 16:32:45', '2019-09-03 17:48:57', 0, '未修理');
INSERT INTO apartment.repairs_log (id, userid, username, apartmentid, apartmentAddress, content, createtime, conductTime, hasfile, status) VALUES (19, 2, 'admin1', 15, '上海市青浦区崧泽大道 7509 号', 'asdftest', '2019-09-03 16:32:45', '2019-09-03 17:49:03', 0, '进行中');
INSERT INTO apartment.repairs_log (id, userid, username, apartmentid, apartmentAddress, content, createtime, conductTime, hasfile, status) VALUES (20, 2, 'admin1', 15, '上海市青浦区崧泽大道 7509 号', 'asdf', '2019-09-03 16:36:15', '2019-09-03 17:49:13', 0, '未修理');
INSERT INTO apartment.repairs_log (id, userid, username, apartmentid, apartmentAddress, content, createtime, conductTime, hasfile, status) VALUES (20, 2, 'admin1', 15, '上海市青浦区崧泽大道 7509 号', 'asdf', '2019-09-03 16:36:15', '2019-09-03 17:49:14', 0, '未修理');
INSERT INTO apartment.repairs_log (id, userid, username, apartmentid, apartmentAddress, content, createtime, conductTime, hasfile, status) VALUES (20, 2, 'admin1', 15, '上海市青浦区崧泽大道 7509 号', 'asdf', '2019-09-03 16:36:15', '2019-09-03 17:49:37', 0, '进行中');
INSERT INTO apartment.repairs_log (id, userid, username, apartmentid, apartmentAddress, content, createtime, conductTime, hasfile, status) VALUES (19, 2, 'admin1', 15, '上海市青浦区崧泽大道 7509 号', 'asdftest', '2019-09-03 16:32:45', '2019-09-03 17:50:18', 0, '未修理');
create table ums_admin
(
  id         bigint auto_increment
    primary key,
  username   varchar(64)                         null,
  password   varchar(64)                         null,
  phone      varchar(45)                         not null comment '联系电话',
  roleId     bigint                              not null comment '0',
  roleNote   varchar(500)                        null comment '备注信息',
  status     int(1)    default 1                 null comment '帐号启用状态：0->禁用；1->启用',
  createTime timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
  loginTime  timestamp                           null comment '最后登录时间',
  userId     bigint                              null comment '租户表id',
  repairId   bigint                              null comment '售后人员 id'
)
  comment '后台用户表' charset = utf8;

create index ums_admin_ums_role_id_fk
  on ums_admin (roleId);

INSERT INTO apartment.ums_admin (username, password, phone, roleId, roleNote, status, createTime, loginTime, userId, repairId) VALUES ('admin', '123456', '17621966839', 1, '超级管理员', 1, '2019-10-08 13:32:47', '2019-09-09 16:38:34', 0, 0);
INSERT INTO apartment.ums_admin (username, password, phone, roleId, roleNote, status, createTime, loginTime, userId, repairId) VALUES ('admin1', '123456', '17621966839', 3, '租户', 1, '2019-08-29 05:16:03', '2019-09-09 15:49:02', 2, 0);
INSERT INTO apartment.ums_admin (username, password, phone, roleId, roleNote, status, createTime, loginTime, userId, repairId) VALUES ('admin2', '123456', '17621966839', 4, '售后人员', 1, '2019-08-29 06:19:57', '2019-09-09 15:49:11', 0, 1);
INSERT INTO apartment.ums_admin (username, password, phone, roleId, roleNote, status, createTime, loginTime, userId, repairId) VALUES ('admin3', '123456', '17621966839', 2, '系统管理员', 1, '2019-09-06 12:54:57', '2019-09-09 15:48:53', 0, 0);
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
  description varchar(500)     null comment '描述'
)
  comment '后台用户角色表' charset = utf8;

INSERT INTO apartment.ums_role (name, value, admin_count, create_time, status, sort, description) VALUES ('超级管理员', ' ', 1, '2019-09-30 15:46:11', 1, 0, '超级管理员');
INSERT INTO apartment.ums_role (name, value, admin_count, create_time, status, sort, description) VALUES ('系统管理员', ' ', 1, '2019-09-06 12:52:01', 1, 0, '系统管理员');
INSERT INTO apartment.ums_role (name, value, admin_count, create_time, status, sort, description) VALUES ('租户', ' ', 1, '2019-08-29 05:15:22', 1, 0, '租户');
INSERT INTO apartment.ums_role (name, value, admin_count, create_time, status, sort, description) VALUES ('售后人员', ' ', 1, '2019-08-29 06:21:03', 1, 0, '售后人员');