CREATE SCHEMA `demo_schema` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `user_name` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `born_at` date DEFAULT NULL,
  `password` varchar(45) COLLATE utf8_bin NOT NULL,
  `name_cn` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '中文名',
  `name_en` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '英文名',
  `sex` SMALLINT(2) COLLATE utf8_bin DEFAULT NULL COMMENT '英文名',
  PRIMARY KEY (`id`,`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;