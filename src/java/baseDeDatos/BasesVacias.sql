-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.5.24-log - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para detector_mutantes_bd
CREATE DATABASE IF NOT EXISTS `detector_mutantes_bd` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci */;
USE `detector_mutantes_bd`;

CREATE USER mercadolibre IDENTIFIED BY 'MercadoLibre_2018';

-- DAMOS PERMISOS EN LA BBDD PARA EL NUEVO USUARIO
grant all privileges on detector_mutantes_bd.* to 'mercadolibre'@'%' identified by 'MercadoLibre_2018';
grant all privileges on detector_mutantes_bd.* to 'mercadolibre'@'localhost' identified by 'MercadoLibre_2018';
flush privileges;

-- Volcando estructura para tabla detector_mutantes_bd.estadistica
CREATE TABLE IF NOT EXISTS `estadistica` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count_mutant_dna` int(11) NOT NULL,
  `count_human_dna` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla detector_mutantes_bd.secuencias_adn_procesadas
CREATE TABLE IF NOT EXISTS `secuencias_adn_procesadas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `es_mutante` bit(1) NOT NULL,
  `secuencia` varchar(2500) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- La exportación de datos fue deseleccionada.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
