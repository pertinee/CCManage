CREATE TABLE `sys_dict` (
  `id` int(10) NOT NULL,
  `dict_name` varchar(32) NOT NULL COMMENT '字典名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';

CREATE TABLE `sys_dict_info` (
  `id` int(10) NOT NULL,
  `dict_value` varchar(6) NOT NULL COMMENT '字典数值',
  `dict_prompt` varchar(32) NOT NULL COMMENT '字典提示',
  `access_level` varchar(2) NOT NULL COMMENT '0-隐藏 1-只读 2-可修改',
  `order_id` int(10) NOT NULL COMMENT '排序，小在前',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`,`dict_value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典详情';