CREATE DATABASE  IF NOT EXISTS `planettastore` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `planettastore`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: planettastore
-- ------------------------------------------------------
-- Server version	5.5.11

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `custID` int(11) NOT NULL,
  `fName` varchar(15) DEFAULT NULL,
  `otherNames` varchar(30) DEFAULT NULL,
  `phone` bigint(20) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `email` varchar(25) DEFAULT NULL,
  `nIDCardNo` bigint(20) DEFAULT NULL,
  `grade` int(3) DEFAULT '0',
  PRIMARY KEY (`custID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'2','3',12345678,'3','3',3,0);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `empID` int(11) NOT NULL,
  `fName` varchar(15) DEFAULT NULL,
  `otherNames` varchar(30) DEFAULT NULL,
  `userName` varchar(20) NOT NULL,
  `password` varchar(15) NOT NULL,
  `phone` bigint(20) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `email` varchar(25) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `POB` varchar(20) DEFAULT NULL,
  `nIDCardNo` bigint(20) DEFAULT NULL,
  `role` varchar(25) DEFAULT NULL,
  `salary` int(11) DEFAULT NULL,
  `RecruitDate` date DEFAULT NULL,
  PRIMARY KEY (`empID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'7','77','7','7',744444444,'7','7','1988-09-09','7',7,'Sales_person',7,'1988-09-09'),(2,'8','8','8','8',666666666,'88','8','1988-09-09','88',8,'Sales_person',8000,'1988-09-09');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expenditure`
--

DROP TABLE IF EXISTS `expenditure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expenditure` (
  `expID` int(11) NOT NULL,
  `sourceID` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `targetName` varchar(45) DEFAULT NULL,
  `reason` varchar(45) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  PRIMARY KEY (`expID`),
  KEY `sourceID_idx` (`sourceID`),
  CONSTRAINT `sourceID` FOREIGN KEY (`sourceID`) REFERENCES `employee` (`empID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expenditure`
--

LOCK TABLES `expenditure` WRITE;
/*!40000 ALTER TABLE `expenditure` DISABLE KEYS */;
INSERT INTO `expenditure` VALUES (1,1,5000,'5000','test expenditure','2013-09-14','13:46:21');
/*!40000 ALTER TABLE `expenditure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventoryupdate`
--

DROP TABLE IF EXISTS `inventoryupdate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventoryupdate` (
  `updateID` int(11) NOT NULL,
  `purchID` int(11) DEFAULT NULL,
  `empID` int(11) DEFAULT NULL,
  `prodID` int(11) DEFAULT NULL,
  `qtyAdded` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  PRIMARY KEY (`updateID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventoryupdate`
--

LOCK TABLES `inventoryupdate` WRITE;
/*!40000 ALTER TABLE `inventoryupdate` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventoryupdate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prodpurchase`
--

DROP TABLE IF EXISTS `prodpurchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prodpurchase` (
  `proID` int(11) NOT NULL,
  `purchID` int(11) NOT NULL,
  `expiryDate` date DEFAULT NULL,
  PRIMARY KEY (`proID`,`purchID`),
  KEY `proID_idx` (`proID`),
  KEY `proID_idx1` (`proID`),
  KEY `purchID_idx` (`purchID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodpurchase`
--

LOCK TABLES `prodpurchase` WRITE;
/*!40000 ALTER TABLE `prodpurchase` DISABLE KEYS */;
INSERT INTO `prodpurchase` VALUES (1,1,'2016-10-20'),(2,1,'2013-10-22'),(3,2,'2016-01-03');
/*!40000 ALTER TABLE `prodpurchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prodretids`
--

DROP TABLE IF EXISTS `prodretids`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prodretids` (
  `prodRetID` int(11) NOT NULL COMMENT 'Uniquely identifies a retail product.',
  `prodID` int(11) NOT NULL COMMENT 'Identifyies Warehouse inventory.',
  PRIMARY KEY (`prodRetID`),
  KEY `prodRetID_idx` (`prodRetID`),
  KEY `prodID_idx` (`prodID`),
  KEY `prodID_idx1` (`prodID`),
  CONSTRAINT `prodID` FOREIGN KEY (`prodID`) REFERENCES `whouseinventory` (`prodID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `prodRetID` FOREIGN KEY (`prodRetID`) REFERENCES `retailinventory` (`prodRetID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Links Retail Inventory to whosale Inventory.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodretids`
--

LOCK TABLES `prodretids` WRITE;
/*!40000 ALTER TABLE `prodretids` DISABLE KEYS */;
INSERT INTO `prodretids` VALUES (130002,1),(130001,3);
/*!40000 ALTER TABLE `prodretids` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase` (
  `purchaseID` int(11) NOT NULL,
  `suID` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `advance` int(11) DEFAULT NULL,
  `dueDate` date DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  PRIMARY KEY (`purchaseID`),
  KEY `suID_idx` (`suID`),
  CONSTRAINT `suID` FOREIGN KEY (`suID`) REFERENCES `supplier` (`supID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (1,1,10000,10000,'2013-10-20','2013-10-20','13:37:30'),(2,2,17500,17500,'2013-10-20','2013-10-20','18:37:25');
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `retailinventory`
--

DROP TABLE IF EXISTS `retailinventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `retailinventory` (
  `prodRetID` int(11) NOT NULL AUTO_INCREMENT,
  `retDesc` varchar(17) DEFAULT NULL,
  `retailPrice` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `location` varchar(30) DEFAULT NULL,
  `limitlevel` int(11) DEFAULT NULL,
  `shortagedate` date DEFAULT NULL,
  `shortagetime` time DEFAULT NULL,
  PRIMARY KEY (`prodRetID`)
) ENGINE=InnoDB AUTO_INCREMENT=130003 DEFAULT CHARSET=latin1 COMMENT='Retail products';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `retailinventory`
--

LOCK TABLES `retailinventory` WRITE;
/*!40000 ALTER TABLE `retailinventory` DISABLE KEYS */;
INSERT INTO `retailinventory` VALUES (130001,'Sugar-1kg-Nos',800,-1,'1C',5,'2013-10-20','19:18:53'),(130002,'parle-G-25',25,50,'1d',30,NULL,NULL);
/*!40000 ALTER TABLE `retailinventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `retailsales`
--

DROP TABLE IF EXISTS `retailsales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `retailsales` (
  `prodRetIDs` int(11) NOT NULL,
  `transID` int(11) NOT NULL,
  `retDesc` varchar(17) DEFAULT NULL COMMENT 'produt description',
  `retPrice` int(11) DEFAULT NULL,
  `qty` int(5) DEFAULT NULL COMMENT 'Quantity purchased',
  `total` int(11) DEFAULT NULL COMMENT 'Total amount per item.',
  PRIMARY KEY (`transID`,`prodRetIDs`),
  KEY `transID_idx` (`transID`),
  KEY `prodRetID_idx` (`prodRetIDs`),
  CONSTRAINT `prodRetIDs` FOREIGN KEY (`prodRetIDs`) REFERENCES `retailinventory` (`prodRetID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `transID` FOREIGN KEY (`transID`) REFERENCES `transaction` (`transID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `retailsales`
--

LOCK TABLES `retailsales` WRITE;
/*!40000 ALTER TABLE `retailsales` DISABLE KEYS */;
INSERT INTO `retailsales` VALUES (130001,1,'Sugar-1kg-Nos',800,10,8000),(130001,2,'Sugar-1kg-Nos',800,1,800),(130002,2,'parle-G-25',25,10,250);
/*!40000 ALTER TABLE `retailsales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `supID` int(11) NOT NULL,
  `fName` varchar(15) DEFAULT NULL,
  `otherNames` varchar(30) DEFAULT NULL,
  `bzName` varchar(30) DEFAULT NULL,
  `phone1` bigint(20) DEFAULT NULL,
  `phone2` bigint(20) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `email` varchar(25) DEFAULT NULL,
  `sampleProds` varchar(45) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  PRIMARY KEY (`supID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'afundoh',NULL,'palamino',NULL,NULL,NULL,NULL,NULL,NULL),(2,'chrise','re','Chris Cosmetics',22222222,222222222,'33','afudohafundoh@yahoo.co.uk','eeee',2),(3,'s','s','s',123456789,999999999,'e','e','d',5),(4,'444','4','4',12345678,12345678,'4','4','4',2);
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `transID` int(11) NOT NULL,
  `transType` varchar(17) NOT NULL,
  `transAmount` int(11) DEFAULT NULL,
  `transDate` date DEFAULT NULL,
  `transTime` time DEFAULT NULL,
  `salesPerIDt` int(11) DEFAULT NULL,
  `custIDt` int(11) DEFAULT NULL,
  `custName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`transID`,`transType`),
  KEY `custIDt_idx` (`custIDt`),
  CONSTRAINT `custIDt` FOREIGN KEY (`custIDt`) REFERENCES `customer` (`custID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,'retailPaid',8000,'2013-10-20','19:18:53',1,NULL,'customer'),(2,'retailPaid',1050,'2013-10-21','21:27:47',1,NULL,'customer');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wholesalesales`
--

DROP TABLE IF EXISTS `wholesalesales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wholesalesales` (
  `prodID` int(11) NOT NULL,
  `transIDw` int(11) NOT NULL,
  `wsDesc` varchar(17) DEFAULT NULL,
  `wsPrice` int(11) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  PRIMARY KEY (`prodID`,`transIDw`),
  KEY `prodID_idx` (`prodID`),
  KEY `transID_idx` (`transIDw`),
  CONSTRAINT `prodIDw` FOREIGN KEY (`prodID`) REFERENCES `whouseinventory` (`prodID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `transIDw` FOREIGN KEY (`transIDw`) REFERENCES `transaction` (`transID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wholesalesales`
--

LOCK TABLES `wholesalesales` WRITE;
/*!40000 ALTER TABLE `wholesalesales` DISABLE KEYS */;
/*!40000 ALTER TABLE `wholesalesales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `whouseinventory`
--

DROP TABLE IF EXISTS `whouseinventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `whouseinventory` (
  `prodID` int(11) NOT NULL,
  `noRetUPerlwu` int(11) DEFAULT NULL,
  `lwuDesc` varchar(17) DEFAULT NULL,
  `lwuQty` int(11) DEFAULT NULL,
  `lwuCp` int(11) DEFAULT NULL,
  `lwuSp` int(11) DEFAULT NULL,
  `lwuPhwu` int(11) DEFAULT NULL,
  `hwuDesc` varchar(17) DEFAULT NULL,
  `hwuQty` int(11) DEFAULT NULL,
  `hwuCp` int(11) DEFAULT NULL,
  `hwuSp` int(11) DEFAULT NULL,
  `lwuLimLev` int(11) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`prodID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `whouseinventory`
--

LOCK TABLES `whouseinventory` WRITE;
/*!40000 ALTER TABLE `whouseinventory` DISABLE KEYS */;
INSERT INTO `whouseinventory` VALUES (1,60,'roll parle-G-25',0,1150,1200,8,'carton parle-G_25',1,9200,9600,6,'Biscuits','shelf_1A'),(2,1,'bread-100',10,80,90,1,'bread-100',0,80,90,4,'bread','counter'),(3,1,'sugar-1kg',0,700,750,25,'carton sugar',1,17500,18000,5,'sugar','shelf-2A');
/*!40000 ALTER TABLE `whouseinventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `whprodbelowlim`
--

DROP TABLE IF EXISTS `whprodbelowlim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `whprodbelowlim` (
  `prodIDwl` int(11) NOT NULL,
  `lwuDes` varchar(17) DEFAULT NULL,
  `LwuqtyLeft` int(11) DEFAULT NULL,
  `limitLevel` int(11) DEFAULT NULL,
  `globalDesc` varchar(17) DEFAULT NULL,
  `globalQtyLeft` int(11) DEFAULT NULL,
  `iniDate` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  PRIMARY KEY (`prodIDwl`),
  KEY `prodIDbl_idx` (`prodIDwl`),
  CONSTRAINT `prodIDwl` FOREIGN KEY (`prodIDwl`) REFERENCES `whouseinventory` (`prodID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Holds information about products that are runnign out of stock';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `whprodbelowlim`
--

LOCK TABLES `whprodbelowlim` WRITE;
/*!40000 ALTER TABLE `whprodbelowlim` DISABLE KEYS */;
/*!40000 ALTER TABLE `whprodbelowlim` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-10-23 19:49:19
