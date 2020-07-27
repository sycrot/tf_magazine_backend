-- MariaDB dump 10.17  Distrib 10.4.11-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: tf_magazine
-- ------------------------------------------------------
-- Server version	10.4.11-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cep` varchar(255) DEFAULT NULL,
  `complement` varchar(255) DEFAULT NULL,
  `neighborhood` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `public_place` varchar(255) DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpo044ng5x4gynb291cv24vtea` (`city_id`),
  KEY `FK7156ty2o5atyuy9f6kuup9dna` (`client_id`),
  CONSTRAINT `FK7156ty2o5atyuy9f6kuup9dna` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  CONSTRAINT `FKpo044ng5x4gynb291cv24vtea` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'64320000','Próximo ao Caixa','São Paulo','324','Rua Igui Ligs',1,1),(2,'54678000',NULL,'São Caetano','734','Avenida Magrão',2,2);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Eletrodomésticos'),(2,'Informática'),(3,'Esporte e lazer'),(4,'Ferramentas'),(5,'Moda'),(6,'Cama, mesa e banho'),(7,'Instrumentos musicais'),(8,'Livros'),(9,'Papelaria'),(10,'Casa e Construção'),(11,'Artigos para festas'),(12,'Games');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `state_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6p2u50v8fg2y0js6djc6xanit` (`state_id`),
  CONSTRAINT `FK6p2u50v8fg2y0js6djc6xanit` FOREIGN KEY (`state_id`) REFERENCES `state` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Codó',1),(2,'Rio de Janeiro',2),(3,'Santos',2);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cpf_ou_cnpj` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'32324100304','purrmageddon@gmail.com','Josefino','$2a$10$bcOwQbxRfjhdcoBDgtt8UueqfBe5YWDEgD/AcDSX1JZnTY9vtUve2',1),(2,'98481603708','thiagofarias99@hotmail.com','Jack','$2a$10$EHVhlnMJdct/1DCsjhCpi.wegV9zguP2MoE5kErw0sbaTrOtrMlbu',1);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_request`
--

DROP TABLE IF EXISTS `item_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_request` (
  `amount` int(11) DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  `product_id` int(11) NOT NULL,
  `request_id` int(11) NOT NULL,
  PRIMARY KEY (`product_id`,`request_id`),
  KEY `FKe4qm9bo2qg2jp631mcgjka5ic` (`request_id`),
  CONSTRAINT `FK2q9x616akbtun5lwn6y8ci2oa` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKe4qm9bo2qg2jp631mcgjka5ic` FOREIGN KEY (`request_id`) REFERENCES `request` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_request`
--

LOCK TABLES `item_request` WRITE;
/*!40000 ALTER TABLE `item_request` DISABLE KEYS */;
INSERT INTO `item_request` VALUES (1,0,2100,1,1);
/*!40000 ALTER TABLE `item_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `request_id` int(11) NOT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`request_id`),
  CONSTRAINT `FK5pgyup82pk40rm2mo6jeqrbi7` FOREIGN KEY (`request_id`) REFERENCES `request` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,2),(2,1);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_with_billet`
--

DROP TABLE IF EXISTS `payment_with_billet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_with_billet` (
  `data_pagamento` datetime DEFAULT NULL,
  `data_vencimento` datetime DEFAULT NULL,
  `request_id` int(11) NOT NULL,
  PRIMARY KEY (`request_id`),
  CONSTRAINT `FKrj111f5gdn5pco0l75py1syb1` FOREIGN KEY (`request_id`) REFERENCES `payment` (`request_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_with_billet`
--

LOCK TABLES `payment_with_billet` WRITE;
/*!40000 ALTER TABLE `payment_with_billet` DISABLE KEYS */;
INSERT INTO `payment_with_billet` VALUES (NULL,'2020-07-28 03:00:00',2);
/*!40000 ALTER TABLE `payment_with_billet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_with_card`
--

DROP TABLE IF EXISTS `payment_with_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_with_card` (
  `numero_de_parcelas` int(11) DEFAULT NULL,
  `request_id` int(11) NOT NULL,
  PRIMARY KEY (`request_id`),
  CONSTRAINT `FK7u0tjgmt9hpndyuro6tsdpjoj` FOREIGN KEY (`request_id`) REFERENCES `payment` (`request_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_with_card`
--

LOCK TABLES `payment_with_card` WRITE;
/*!40000 ALTER TABLE `payment_with_card` DISABLE KEYS */;
INSERT INTO `payment_with_card` VALUES (4,1);
/*!40000 ALTER TABLE `payment_with_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phone`
--

DROP TABLE IF EXISTS `phone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phone` (
  `client_id` int(11) NOT NULL,
  `phones` varchar(255) DEFAULT NULL,
  KEY `FK3o48ec26lujl3kf01hwqplhn2` (`client_id`),
  CONSTRAINT `FK3o48ec26lujl3kf01hwqplhn2` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone`
--

LOCK TABLES `phone` WRITE;
/*!40000 ALTER TABLE `phone` DISABLE KEYS */;
INSERT INTO `phone` VALUES (1,'987337788'),(1,'998828384'),(2,'996543212'),(2,'976543243');
/*!40000 ALTER TABLE `phone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Notebook',2100),(2,'Impressora',1400),(3,'Mouse',40),(4,'Bola futebol',120),(5,'Chave philips',60),(6,'Bola basquete',140),(7,'Kit ferraments',400),(8,'Kit Bota Coturno Causal',64),(9,'Colete Cinta Modeladora',34.9),(10,'Óculos de Grau Feminino',249.04),(11,'Kit Camiseta Básica c/5 Peças Masculinas',89.99),(12,'Cobertor Casal Iury',79.9),(13,'Kit 2 Travesseiros fibra Siliconada',22.4),(14,'Jogo de Toalha Santista Royar Nut',59.9),(15,'Microfone de Lapela Sony Ecm-Cs3',239.9),(16,'Ukulele Tenor UK-30',123.9),(17,'Violão 5107 Cordas Nylon',324.9),(18,'Microfone Profissional Com Fio 3m High SM-58',59.9),(19,'Livro - As crônicas de Nárnia - Volume único',69.9),(20,'Livro - Quem mexeu no meu queijo',9.9),(21,'Lapis de Escrever Preto Escolar Graduado Caixa H Artools',6.9),(22,'Papel A4 Copimax 75g/m2 500 Folhas',16.9),(23,'Placas de Revestimentos 3D - Cairo Autoadesiva',17.9),(24,'Kit de Pilhas AA Pequena Alcalina 32 Unidades',89.9),(25,'Cimento Todas As Obras 50Kg',20.9),(26,'Torneira com Filtro Para Cozinha Acqubios',49.9),(27,'Lavadora de Alta Pressão K1 Karcher Black 1600lbs',342.9),(28,'Pia de Cozinha Inox 120x52cm Ghel Plus',151.9),(29,'Kit 2 Pijamas Monstros SA',284.9),(30,'Fantasia Homem Aranha',311.9),(31,'Playstation 4 1TB com 3 Jogos',2699.9),(32,'Fifa 20 PS4',129.9),(33,'Controle PS4 sem Fio Dualshock 4',349.9),(34,'Notebook Dell i15-3583 Intel Core i5 8GB',3894.9),(35,'Impressora Multifuncional Epson EcoTank',1044.9),(36,'HD Externo 1TB Toshiba Canvio',399.9),(37,'Bicicleta Caloi A18 Schwinn',1367.9),(38,'Tênis Air Jordan',414.9),(39,'Micro-ondas Electrolux',539.9),(40,'Lavadora de Roupas Brastemp',1679.9),(41,'Geladeira/Refrigerador Brastemp',2384.9);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_category` (
  `product_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  KEY `FKkud35ls1d40wpjb5htpp14q4e` (`category_id`),
  KEY `FK2k3smhbruedlcrvu6clued06x` (`product_id`),
  CONSTRAINT `FK2k3smhbruedlcrvu6clued06x` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKkud35ls1d40wpjb5htpp14q4e` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES (1,1),(1,2),(2,1),(2,2),(3,1),(3,2),(4,3),(5,4),(6,3),(7,4),(8,5),(9,5),(10,5),(11,5),(12,6),(13,6),(14,6),(15,7),(15,2),(16,7),(17,7),(18,7),(18,2),(19,8),(20,8),(21,9),(22,9),(23,9),(24,10),(24,2),(25,10),(26,10),(27,10),(27,4),(28,10),(29,11),(30,11),(31,12),(31,2),(32,12),(33,12),(34,2),(35,2),(36,2),(37,3),(38,3),(39,1),(40,1),(41,1);
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profiles`
--

DROP TABLE IF EXISTS `profiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profiles` (
  `client_id` int(11) NOT NULL,
  `profiles` int(11) DEFAULT NULL,
  KEY `FKt2y1e2fvhgusaxk49o6ti0osa` (`client_id`),
  CONSTRAINT `FKt2y1e2fvhgusaxk49o6ti0osa` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profiles`
--

LOCK TABLES `profiles` WRITE;
/*!40000 ALTER TABLE `profiles` DISABLE KEYS */;
INSERT INTO `profiles` VALUES (1,2),(2,1),(2,2);
/*!40000 ALTER TABLE `profiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `instant` datetime DEFAULT NULL,
  `delivery_address_id` int(11) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn579kjmk2jkthnj21hyv20h54` (`delivery_address_id`),
  KEY `FKdayt1j0e3kc0j52bn9b78dav` (`client_id`),
  CONSTRAINT `FKdayt1j0e3kc0j52bn9b78dav` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  CONSTRAINT `FKn579kjmk2jkthnj21hyv20h54` FOREIGN KEY (`delivery_address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (1,'2020-07-24 19:09:00',1,1),(2,'2020-07-24 19:11:00',1,1);
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `state` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `state`
--

LOCK TABLES `state` WRITE;
/*!40000 ALTER TABLE `state` DISABLE KEYS */;
INSERT INTO `state` VALUES (1,'Maranhão'),(2,'Rio de Janeiro');
/*!40000 ALTER TABLE `state` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-27  9:38:20
