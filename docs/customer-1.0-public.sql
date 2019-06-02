-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.3.10-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for customer
DROP DATABASE IF EXISTS `customer`;
CREATE DATABASE IF NOT EXISTS `customer` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `customer`;

-- Dumping structure for table customer.customer
DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
  `idCustomer` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) NOT NULL,
  `MI` varchar(45) DEFAULT NULL,
  `LName` varchar(45) NOT NULL,
  `Address1` varchar(45) DEFAULT NULL,
  `Address2` varchar(45) DEFAULT NULL,
  `City` varchar(45) DEFAULT NULL,
  `State` varchar(45) DEFAULT NULL,
  `Country` varchar(45) DEFAULT NULL,
  `ZipCode` varchar(45) DEFAULT NULL,
  `Phone1` varchar(45) DEFAULT NULL,
  `Phone2` varchar(45) DEFAULT NULL,
  `Phone3` varchar(45) DEFAULT NULL,
  `Email` varchar(80) DEFAULT NULL,
  `Users_idUsers` int(11) NOT NULL,
  `Created` datetime NOT NULL,
  PRIMARY KEY (`idCustomer`,`Users_idUsers`),
  UNIQUE KEY `idCustomer_UNIQUE` (`idCustomer`),
  KEY `fk_Customer_Users1_idx` (`Users_idUsers`),
  CONSTRAINT `fk_Customer_Users1` FOREIGN KEY (`Users_idUsers`) REFERENCES `users` (`idUsers`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table customer.customer: ~0 rows (approximately)
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;

-- Dumping structure for table customer.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `idUsers` int(11) NOT NULL AUTO_INCREMENT,
  `First` varchar(45) NOT NULL,
  `Last` varchar(45) NOT NULL,
  `UserName` varchar(45) NOT NULL,
  `SecretKey` varchar(1024) NOT NULL,
  `A` int(11) DEFAULT 1,
  `A1` int(11) DEFAULT NULL,
  `A2` varchar(45) DEFAULT NULL,
  `B` int(11) DEFAULT 2,
  `B1` int(11) DEFAULT NULL,
  `B2` varchar(45) DEFAULT NULL,
  `C` int(11) DEFAULT 3,
  `C1` int(11) DEFAULT NULL,
  `C2` varchar(45) DEFAULT NULL,
  `D` int(11) DEFAULT 1,
  `PwReset` tinyint(1) NOT NULL DEFAULT 0,
  `Creation` datetime NOT NULL,
  PRIMARY KEY (`idUsers`,`UserName`),
  UNIQUE KEY `idUsers_UNIQUE` (`idUsers`),
  UNIQUE KEY `UserName_UNIQUE` (`UserName`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
