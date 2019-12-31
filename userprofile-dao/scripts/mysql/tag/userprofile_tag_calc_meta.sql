create table userprofile_tag_calc_meta(
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `tag_theme` varchar(1128) COLLATE utf8_bin NOT NULL COMMENT '标签主题',
  `tag_type` varchar(16) COLLATE utf8_bin NOT NULL COMMENT '标签类型, cookie/user',
  `tag_ids` text COLLATE utf8_bin NOT NULL COMMENT '标签编号，统一标签主题有多个编号用逗号分隔',
  `calc_sql` text COLLATE utf8_bin NOT NULL COMMENT '标签计算sql语句',
  `is_valid` tinyint(4) DEFAULT '1' COMMENT '状态，1：正常，0：生效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='基础标签计算元数据表';