create table s_user (
   uid varchar(36) not null,
   open_id varchar(64) not null,
   name varchar(16) null comment '用户名',
   password varchar(32) null comment '密码',
   salt varchar(8) null comment '盐',
   phone varchar(16) null comemnt '电话号码',
   email varchar(16) null comment '邮箱',
   status int(1) null comment '状态1-正常',
   gender int(1) null comment '性別1-男2-女',
   avatar_url varchar(64) comment '頭像',
   create_time datetime null comment '创建时间',
   primary key (uid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '用户表';

create table s_brand (
   id varchar(36) not null,
   name_cn varchar(12),
   name_en varchar(32),
   logo varchar(64),
   status int(1) null comment '状态1-正常',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '品牌表';

create table s_goods (
  id varchar(36) not null,
  brand_id varchar(36) null,
  name varchar(25) null comment '商品名称',
  description varchar(64) null comment '商品描述',
  images varchar(1024) null comment '商品圖片',
  status int(1) null comment '状态1-正常',
  create_time datetime null comment '创建时间',
  update_time datetime null comment '更新時間',
  primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '商品表';

create table s_goods_detail (
  id varchar(36) not null,
  goods_id varchar(36) null,
  goods_sku_ids varchar(256) null,
  description varchar(64) null comment 'sku描述',
  price int(5) null comment '单价',
  stock int(3) null comment '庫存',
  status int(1) null comment '状态1-正常',
  create_time datetime null comment '创建时间',
  update_time datetime null comment '更新時間',
  primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '商品详情表';

create table s_goods_sku (
  id varchar(36) not null,
  goods_id varchar(36) null,
  sku_id varchar(64) null comment 'skuid',
  primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '商品详情表';

create table s_shop_cart (
  id varchar(36) not null,
  user_id varchar(36) null comment '用戶id',
  goods_detail_id varchar(36) null comment 'skuid',
  quantity int(2) null comment '數量',
  create_time datetime null comment '創建時間',
  primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '购物车表';

create table s_sku (
  id varchar(36) not null,
  code varchar(10) null,
  name varchar(10) null comment 'sku名称',
  value varchar(16) null comment 'sku值',
  primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment 'sku表';

create table s_order (
  id bigint auto_increment not null,
  order_no varchar(20) null,
  uid varchar(36) null,
  total_amount int(5) null comment '总金额',
  status int(1) null comment '状态1-待付款2-待发货3-待收货4-换货中5-订单完成',
  address_id varchar(36) null,
  express_id varchar(36) null,
  pay_time datetime null comment '付款時間',
  create_time datetime null comment '創建時間',
  update_time datetime null comment '更新時間',
  primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '订单表';

create table s_order_detail (
  id bigint auto_increment not null,
  order_no varchar(20) null,
  sku_id varchar(36) null comment 'skuid',
  quantity int(2) null comment '數量',
  amount int(5) null comment '总价',
  create_time datetime null comment '創建時間',
  update_time datetime null comment '更新時間',
  primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '订单详情表';

create table s_express (
  id varchar(36) not null,
  order_no varchar(20) null,
  express_no varchar(40) null,
  express_name varchar(8) null,
  create_time datetime null comment '創建時間',
  primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '快遞單號表';

create table s_receive_address (
  id varchar(36) not null,
  uid varchar(36) null,
  address varchar(128) null,
  name varchar(10) null,
  phone varchar(16) null,
  primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '快遞單號表';

create table s_log (
  id bigint auto_increment not null,
  uid varchar(36) null,
  url varchar(16) null comment '请求url',
  create_time datetime null comment '創建時間',
  primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '请求日志表';
