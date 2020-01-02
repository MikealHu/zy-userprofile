SET FOREIGN_KEY_CHECKS = 0;

-- ---------------------------------------------
-- Table structure for tag calculate meta
-- ---------------------------------------------
DROP TABLE IF EXISTS `userprofile_tag_calc_meta`;
create table `userprofile_tag_calc_meta`(
    `id`        int(11)                        NOT NULL AUTO_INCREMENT COMMENT 'id',
    `tag_theme` varchar(1128) COLLATE utf8_bin NOT NULL COMMENT '标签主题',
    `tag_type`  varchar(16) COLLATE utf8_bin   NOT NULL COMMENT '标签类型, cookie/user',
    `tag_ids`   text COLLATE utf8_bin          NOT NULL COMMENT '标签编号，统一标签主题有多个编号用逗号分隔',
    `calc_sql`  text COLLATE utf8_bin          NOT NULL COMMENT '标签计算sql语句',
    `is_valid`  tinyint(4) DEFAULT '1' COMMENT '状态，1：正常，0：生效',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='基础标签计算元数据表';


-- ---------------------------------------------
-- Table structure for tag calculate monitor
-- ---------------------------------------------
DROP TABLE IF EXISTS `userprofile_tag_calc_monitor`;
create table `userprofile_tag_calc_monitor`(
    `data_date`   varchar(45)                   NOT NULL COMMENT '数据日期,格式：yyyyMMdd',
    `tag_id`      varchar(45)                   NOT NULL COMMENT '标签编号',
    `tag_theme`   varchar(128) COLLATE utf8_bin NOT NULL COMMENT '标签主题',
    `is_success`  tinyint(4) DEFAULT '1' COMMENT '完成状态，1：完成，0：未完成',
    `update_time` datetime   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`data_date`, `tag_id`),
    KEY `d_t_index` (`data_date`, `tag_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='基础标签计算监控表';