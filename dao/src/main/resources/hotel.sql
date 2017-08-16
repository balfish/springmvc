CREATE TABLE `hotel` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `hotel_id` int(10) unsigned NOT NULL COMMENT 'hotel_id',
  `hotel_name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'hotel_name',
`hotel_address` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'hotel_address',
`hotel_phone` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'hotel_phone',
  `hotel_grade_type` int(10) unsigned NOT NULL COMMENT 'hotel_grade_type',
`create_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'create_time',
`update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update_time',
  PRIMARY KEY (`id`),
  KEY `idx_hotel_id` (`hotel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='酒店表';