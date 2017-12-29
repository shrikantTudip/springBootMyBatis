-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.20-0ubuntu0.16.04.1 - (Ubuntu)
-- Server OS:                    Linux
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for spring_demo
DROP DATABASE IF EXISTS `spring_demo`;
CREATE DATABASE IF NOT EXISTS `spring_demo` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `spring_demo`;

-- Dumping structure for table spring_demo.Product
DROP TABLE IF EXISTS `Product`;
CREATE TABLE IF NOT EXISTS `Product` (
  `id` int(11) unsigned NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `image` varchar(500) DEFAULT NULL,
  `color` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table spring_demo.Product: ~4 rows (approximately)
/*!40000 ALTER TABLE `Product` DISABLE KEYS */;
INSERT INTO `Product` (`id`, `name`, `image`, `color`) VALUES
	(1, 'Goggle', 'goggle4.jpg', '#ff0000'),
	(2, 'Shirt', 'shirt.jpg', 'white'),
	(3, 'Chair', 'chair4.jpg', 'gray'),
	(4, 'Shrikant', 'shrikant.jpg', '#bc6060');
/*!40000 ALTER TABLE `Product` ENABLE KEYS */;

-- Dumping structure for table spring_demo.sessions
DROP TABLE IF EXISTS `sessions`;
CREATE TABLE IF NOT EXISTS `sessions` (
  `session_id` varchar(250) NOT NULL DEFAULT '0',
  `user_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`session_id`),
  KEY `FK_sessions_user` (`user_id`),
  CONSTRAINT `FK_sessions_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table spring_demo.sessions: ~2 rows (approximately)
/*!40000 ALTER TABLE `sessions` DISABLE KEYS */;
INSERT INTO `sessions` (`session_id`, `user_id`) VALUES
	('0f269bd4-efff-4c08-a1fc-7e8dd85ab864', 1),
	('5d3b6dcd-a4cb-4e7c-a383-7769290b7e3a', 1),
	('796e71e3-faf0-4838-87ce-fa9fdb4b6074', 1);
/*!40000 ALTER TABLE `sessions` ENABLE KEYS */;

-- Dumping structure for table spring_demo.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `display_name` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `domain` varchar(255) NOT NULL,
  `inserted_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table spring_demo.user: ~0 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `email`, `password`, `status`, `display_name`, `role`, `domain`, `inserted_at`, `updated_at`) VALUES
	(1, 'shrikantpatwari@gmail.com', '$2a$10$/1luWbmNJJ3x8.LnhxUD/eZlS4uDBhJvcLyo110b6tLqpgZPfikXK', 1, 'Shrikant Patwari', 'Admin', 'localhost', '2017-12-21 12:28:29', '2017-12-21 15:16:36');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
