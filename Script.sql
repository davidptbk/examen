-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: examen
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `coder`
--

DROP TABLE IF EXISTS `coder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `documento` varchar(255) NOT NULL,
  `cohorte` int(11) DEFAULT NULL,
  `cv` text DEFAULT NULL,
  `clan` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `documento` (`documento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coder`
--

LOCK TABLES `coder` WRITE;
/*!40000 ALTER TABLE `coder` DISABLE KEYS */;
/*!40000 ALTER TABLE `coder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contratacion`
--

DROP TABLE IF EXISTS `contratacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contratacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vacante_id` int(11) DEFAULT NULL,
  `coder_id` int(11) DEFAULT NULL,
  `fecha_aplicacion` timestamp NOT NULL DEFAULT current_timestamp(),
  `estado` varchar(255) DEFAULT NULL,
  `salario` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_vacante_id` (`vacante_id`),
  KEY `fk_coder_id` (`coder_id`),
  CONSTRAINT `fk_coder_id` FOREIGN KEY (`coder_id`) REFERENCES `coder` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_vacante_id` FOREIGN KEY (`vacante_id`) REFERENCES `vacante` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contratacion`
--

LOCK TABLES `contratacion` WRITE;
/*!40000 ALTER TABLE `contratacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `contratacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empresa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `sector` varchar(255) NOT NULL,
  `ubicacion` varchar(255) DEFAULT NULL,
  `contacto` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES (1,'Evertec','Tecnologia','NOI COWORKING','5220405'),(2,'Riwi','Educacion','de Moda Outlet','5220405');
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vacante`
--

DROP TABLE IF EXISTS `vacante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vacante` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `empresa_id` int(11) DEFAULT NULL,
  `titulo` varchar(255) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `duracion` varchar(255) DEFAULT NULL,
  `estado` varchar(50) DEFAULT NULL,
  `tecnologia` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_empresa_id` (`empresa_id`),
  CONSTRAINT `fk_empresa_id` FOREIGN KEY (`empresa_id`) REFERENCES `empresa` (`id`) ON DELETE CASCADE,
  CONSTRAINT `CONSTRAINT_1` CHECK (`estado` in ('ACTIVO','INACTIVO'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vacante`
--

LOCK TABLES `vacante` WRITE;
/*!40000 ALTER TABLE `vacante` DISABLE KEYS */;
/*!40000 ALTER TABLE `vacante` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-09 12:55:03
