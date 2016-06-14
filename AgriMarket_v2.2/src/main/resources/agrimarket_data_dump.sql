-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: agrimarket_schema
-- ------------------------------------------------------
-- Server version	5.0.51b-community-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

-- -----------------------------------------------------
-- Schema agrimarket_schema
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `agrimarket_schema` DEFAULT CHARACTER SET utf8 ;
USE `agrimarket_schema` ;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL auto_increment,
  `name_ar` varchar(45) default NULL,
  `name_en` varchar(45) default NULL,
  `image_url` varchar(200) default NULL,
  `parent_category_id` int(11) default '-1',
  `sound_url` varchar(200) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_Category_Category1_idx` (`parent_category_id`),
  CONSTRAINT `fk_Category_Category1` FOREIGN KEY (`parent_category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'root','root','/images/categories/mango.jpg',1,NULL),(7,'محاصيل','Crops','/images/categories/cucumber.jpg',1,'/sound/categories/1.mp3'),(8,'مبيدات','Pesticides','/images/categories/mobeed.jpg',1,'/sound/categories/1.mp3'),(9,'أسمدة','Fertilizers','/images/categories/fertilizer.jpg',1,'/sound/categories/1.mp3'),(10,'تقاوي','Seeds','/images/categories/seed.jpg',1,'/sound/categories/1.mp3'),(11,'خضار','Vegetables','/images/categories/cucumber.jpg',7,'/sound/categories/1.mp3'),(12,'فاكهة','Fruits','/images/categories/mango.jpg',7,'/sound/categories/1.mp3');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `encryption`
--

DROP TABLE IF EXISTS `encryption`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `encryption` (
  `id` int(11) NOT NULL auto_increment,
  `user` varchar(25) NOT NULL,
  `private key` varchar(100) NOT NULL,
  `public key` varchar(100) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `encryption`
--

LOCK TABLES `encryption` WRITE;
/*!40000 ALTER TABLE `encryption` DISABLE KEYS */;
/*!40000 ALTER TABLE `encryption` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history` (
  `User_id` int(11) NOT NULL,
  `Product_id` int(11) NOT NULL,
  `Unit_id` int(11) NOT NULL,
  `price_per_unit_id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `price` float NOT NULL,
  `quantity` float NOT NULL,
  `user_phone` varchar(45) default NULL,
  `user_location` varchar(100) default NULL,
  `recommended` tinyint(1) default '0',
  `image_url` varchar(200) default NULL,
  `description` varchar(500) default NULL,
  `id` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`id`),
  KEY `fk_User_has_Product_Product3_idx` (`Product_id`),
  KEY `fk_User_has_Product_User2_idx` (`User_id`),
  KEY `fk_User_has_Product_Unit1_idx` (`Unit_id`),
  KEY `fk_User_has_Product_Unit2_idx` (`price_per_unit_id`),
  CONSTRAINT `fk_User_has_Product_Product3` FOREIGN KEY (`Product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Product_Unit1` FOREIGN KEY (`Unit_id`) REFERENCES `unit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Product_Unit2` FOREIGN KEY (`price_per_unit_id`) REFERENCES `unit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Product_User2` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plant_has_season`
--

DROP TABLE IF EXISTS `plant_has_season`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plant_has_season` (
  `Product_id` int(11) NOT NULL,
  `Season_id` int(11) NOT NULL,
  PRIMARY KEY  (`Product_id`,`Season_id`),
  KEY `fk_Product_has_Season_Season1_idx` (`Season_id`),
  KEY `fk_Product_has_Season_Product1_idx` (`Product_id`),
  CONSTRAINT `fk_Product_has_Season_Product1` FOREIGN KEY (`Product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Product_has_Season_Season1` FOREIGN KEY (`Season_id`) REFERENCES `season` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plant_has_season`
--

LOCK TABLES `plant_has_season` WRITE;
/*!40000 ALTER TABLE `plant_has_season` DISABLE KEYS */;
/*!40000 ALTER TABLE `plant_has_season` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL auto_increment,
  `name_ar` varchar(45) default NULL,
  `name_en` varchar(45) default NULL,
  `image_url` varchar(200) default NULL,
  `Category_id` int(11) NOT NULL,
  `organic` tinyint(1) default '0',
  `sound_url` varchar(200) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_Plant_Category1_idx` (`Category_id`),
  CONSTRAINT `fk_Plant_Category1` FOREIGN KEY (`Category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (30,'خيار','Cucumber','/images/products/cucumber.jpg',11,1,'/sound/products/1.mp3'),(31,'طماطم','Tomato','/images/products/tomato.jpg',11,1,'/sound/products/1.mp3'),(32,'موز','Banana','/images/products/banana.jpg',12,0,'/sound/products/1.mp3'),(33,'مبيد1','Mobeed1','/images/products/mobeed1.jpg',8,0,'/sound/products/1.mp3'),(34,'مبيد2','Mobeed2','/images/products/mobeed.jpg',8,0,'/sound/products/1.mp3'),(35,'سماد1','Semad1','/images/products/fertilizer1.jpg',9,0,'/sound/products/1.mp3'),(36,'سماد2','Semad2','/images/products/fertilizer2.jpg',9,0,'/sound/products/1.mp3'),(37,'تقاوي1','Ta2awi1','/images/products/seed1.jpg',10,0,'/sound/products/1.mp3'),(38,'تقاوي2','Ta2awi2','/images/products/seed2.jpg',10,0,'/sound/products/1.mp3'),(39,'مانجو','Mango','/images/products/mango.jpg',12,0,'/to be continue');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `season`
--

DROP TABLE IF EXISTS `season`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `season` (
  `id` int(11) NOT NULL auto_increment,
  `name_ar` varchar(45) default NULL,
  `name_en` varchar(45) default NULL,
  `start_date` date default NULL,
  `end_date` date default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `season`
--

LOCK TABLES `season` WRITE;
/*!40000 ALTER TABLE `season` DISABLE KEYS */;
INSERT INTO `season` VALUES (1,'صيف','summer','2016-12-12','2016-10-10');
/*!40000 ALTER TABLE `season` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unit` (
  `id` int(11) NOT NULL auto_increment,
  `name_ar` varchar(45) default NULL,
  `name_en` varchar(45) default NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` VALUES (2,'كيلو','Kilo','Weight'),(3,'طن','Ton','Weight'),(4,'رطل','Ratl','Weight'),(5,'فدان','Feddan','Area'),(6,'قيراط','Qirat','Area'),(7,'إردب','Erdab','Weight');
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `mail` varchar(100) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `mobile` varchar(45) default NULL,
  `registration_channel` int(11) default NULL,
  `lat` double default NULL,
  `long` double default NULL,
  `governerate` varchar(100) default NULL,
  `image_url` varchar(200) default NULL,
  `id` int(11) NOT NULL auto_increment,
  `rates_average` int(11) default '-1',
  `logged_in` tinyint(1) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_data`
--

DROP TABLE IF EXISTS `user_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_data` (
  `User_id` int(11) NOT NULL,
  `id` int(11) NOT NULL auto_increment,
  `IMEI` varchar(20) NOT NULL,
  `device_channel` int(11) NOT NULL,
  `device_language` varchar(10) NOT NULL,
  `os_id` varchar(45) default NULL,
  `os_version` varchar(45) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_User_Data_User1_idx` (`User_id`),
  CONSTRAINT `fk_User_Data_User1` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_data`
--

LOCK TABLES `user_data` WRITE;
/*!40000 ALTER TABLE `user_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_interested_in_product`
--

DROP TABLE IF EXISTS `user_interested_in_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_interested_in_product` (
  `User_id` int(11) NOT NULL,
  `Product_id` int(11) NOT NULL,
  PRIMARY KEY  (`User_id`,`Product_id`),
  KEY `fk_User_has_Product_Product1_idx` (`Product_id`),
  CONSTRAINT `fk_User_has_Product_Product1` FOREIGN KEY (`Product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Product_User` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_interested_in_product`
--

LOCK TABLES `user_interested_in_product` WRITE;
/*!40000 ALTER TABLE `user_interested_in_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_interested_in_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_offer_product_fixed`
--

DROP TABLE IF EXISTS `user_offer_product_fixed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_offer_product_fixed` (
  `User_id` int(11) NOT NULL,
  `Product_id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `price` float NOT NULL,
  `quantity` float NOT NULL,
  `user_phone` varchar(45) default NULL,
  `user_location` varchar(100) default NULL,
  `Unit_id` int(11) NOT NULL default '-1',
  `recommended` tinyint(1) default '0',
  `image_url` varchar(200) default NULL,
  `description` varchar(500) default NULL,
  `price_per_unit_id` int(11) NOT NULL,
  `id` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`id`),
  KEY `fk_User_has_Product_Product4_idx` (`Product_id`),
  KEY `fk_User_offer_Product_area_fixed_AreaUnit1_idx` (`Unit_id`),
  KEY `fk_User_offer_Product_area_fixed_Unit2_idx` (`price_per_unit_id`),
  KEY `fk_User_has_Product_User3` (`User_id`),
  CONSTRAINT `fk_User_has_Product_Product4` FOREIGN KEY (`Product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Product_User3` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_offer_Product_area_fixed_AreaUnit1` FOREIGN KEY (`Unit_id`) REFERENCES `unit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_offer_Product_area_fixed_Unit2` FOREIGN KEY (`price_per_unit_id`) REFERENCES `unit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_offer_product_fixed`
--

LOCK TABLES `user_offer_product_fixed` WRITE;
/*!40000 ALTER TABLE `user_offer_product_fixed` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_offer_product_fixed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_plants_plant`
--

DROP TABLE IF EXISTS `user_plants_plant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_plants_plant` (
  `User_id` int(11) NOT NULL,
  `Product_id` int(11) NOT NULL,
  `AreaUnit_id` int(11) NOT NULL,
  `Season_id` int(11) NOT NULL,
  `no_units` float NOT NULL,
  `id` int(11) NOT NULL auto_increment,
  `year` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_User_has_Product_Product2_idx` (`Product_id`),
  KEY `fk_User_plants_Product_AreaUnit1_idx` (`AreaUnit_id`),
  KEY `fk_User_plants_Product_Season1_idx` (`Season_id`),
  KEY `fk_User_has_Product_User1` (`User_id`),
  CONSTRAINT `fk_User_has_Product_Product2` FOREIGN KEY (`Product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Product_User1` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_plants_Product_AreaUnit1` FOREIGN KEY (`AreaUnit_id`) REFERENCES `unit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_plants_Product_Season1` FOREIGN KEY (`Season_id`) REFERENCES `season` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_plants_plant`
--

LOCK TABLES `user_plants_plant` WRITE;
/*!40000 ALTER TABLE `user_plants_plant` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_plants_plant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_rates_user`
--

DROP TABLE IF EXISTS `user_rates_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_rates_user` (
  `Rater_id` int(11) NOT NULL,
  `Rated_id` int(11) NOT NULL,
  `rate` int(11) NOT NULL,
  `review` mediumtext,
  PRIMARY KEY  (`Rater_id`,`Rated_id`),
  KEY `fk_User_has_User_User2_idx` (`Rated_id`),
  CONSTRAINT `fk_User_has_User_User1` FOREIGN KEY (`Rater_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_User_User2` FOREIGN KEY (`Rated_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_rates_user`
--

LOCK TABLES `user_rates_user` WRITE;
/*!40000 ALTER TABLE `user_rates_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_rates_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-14  1:17:39
