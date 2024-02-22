-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 22-02-2024 a las 05:56:25
-- Versión del servidor: 8.2.0
-- Versión de PHP: 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `agency`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `app_user`
--

DROP TABLE IF EXISTS `app_user`;
CREATE TABLE IF NOT EXISTS `app_user` (
  `dni` varchar(255) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `app_user`
--

INSERT INTO `app_user` (`dni`, `last_name`, `name`) VALUES
('123423356R', 'Apruebame', 'Luisina'),
('36527377A', 'Espinosa', 'Javier'),
('453626657A', 'Gutierrez', 'Jose'),
('6383688638P', 'Perez', 'Juan');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bookinghotel_appuser`
--

DROP TABLE IF EXISTS `bookinghotel_appuser`;
CREATE TABLE IF NOT EXISTS `bookinghotel_appuser` (
  `booking_hotel_id` bigint NOT NULL,
  `app_user_id` varchar(255) NOT NULL,
  KEY `FKlhl1auwh6vd4dsniqmfbqg3pu` (`app_user_id`),
  KEY `FK5ldhno5eg9d593jqopqqhkust` (`booking_hotel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `booking_flight`
--

DROP TABLE IF EXISTS `booking_flight`;
CREATE TABLE IF NOT EXISTS `booking_flight` (
  `booking_flight_id` bigint NOT NULL AUTO_INCREMENT,
  `reserve_date` date DEFAULT NULL,
  `flight_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`booking_flight_id`),
  KEY `FKcllf1b5jasy6hmvusf88fjdxs` (`flight_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `booking_flight`
--

INSERT INTO `booking_flight` (`booking_flight_id`, `reserve_date`, `flight_id`) VALUES
(1, '2024-04-28', 'BADU-3434'),
(2, '2024-03-14', 'BAMA-1344');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `booking_passenger`
--

DROP TABLE IF EXISTS `booking_passenger`;
CREATE TABLE IF NOT EXISTS `booking_passenger` (
  `booking_flight_id` bigint NOT NULL,
  `app_user_id` varchar(255) NOT NULL,
  KEY `FKbypyq6xy5y8gbs2lnm7w22302` (`app_user_id`),
  KEY `FK3ffak3wxe10nypegaejax29jc` (`booking_flight_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `booking_room`
--

DROP TABLE IF EXISTS `booking_room`;
CREATE TABLE IF NOT EXISTS `booking_room` (
  `booking_room_id` bigint NOT NULL AUTO_INCREMENT,
  `date_from` date DEFAULT NULL,
  `date_to` date DEFAULT NULL,
  `room_id` bigint DEFAULT NULL,
  PRIMARY KEY (`booking_room_id`),
  KEY `FK4e002f18klgu08ekxnav2rwr9` (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `booking_room`
--

INSERT INTO `booking_room` (`booking_room_id`, `date_from`, `date_to`, `room_id`) VALUES
(1, '2024-04-01', '2024-04-07', 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `flight`
--

DROP TABLE IF EXISTS `flight`;
CREATE TABLE IF NOT EXISTS `flight` (
  `code` varchar(255) NOT NULL,
  `seats` int DEFAULT NULL,
  `departure_date` date DEFAULT NULL,
  `destination` varchar(255) DEFAULT NULL,
  `origin` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `seat_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `flight`
--

INSERT INTO `flight` (`code`, `seats`, `departure_date`, `destination`, `origin`, `price`, `seat_type`) VALUES
('BADU-3434', 50, '2024-04-24', 'Dubai', 'Barcelona', 660, 'Business'),
('BAMA-1344', 50, '2024-04-14', 'Madrid', 'Barcelona', 350, 'Business');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hotel`
--

DROP TABLE IF EXISTS `hotel`;
CREATE TABLE IF NOT EXISTS `hotel` (
  `hotel_id` bigint NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`hotel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `hotel`
--

INSERT INTO `hotel` (`hotel_id`, `city`, `name`) VALUES
(1, 'Barcelona', 'Hotel Catalonia'),
(2, 'Barcelona', 'Hotel Barcelona'),
(3, 'Madrid', 'Hotel Princesa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `room`
--

DROP TABLE IF EXISTS `room`;
CREATE TABLE IF NOT EXISTS `room` (
  `code` bigint NOT NULL AUTO_INCREMENT,
  `price_night` double DEFAULT NULL,
  `room_availability` bit(1) DEFAULT NULL,
  `room_type` varchar(255) DEFAULT NULL,
  `hotel_id` bigint DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `FKdosq3ww4h9m2osim6o0lugng8` (`hotel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `room`
--

INSERT INTO `room` (`code`, `price_night`, `room_availability`, `room_type`, `hotel_id`) VALUES
(12, 200, b'1', 'Single', 3),
(13, 300, b'1', 'Double', 3),
(14, 350, b'1', 'Double', 2),
(15, 450, b'1', 'Double', 1);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `bookinghotel_appuser`
--
ALTER TABLE `bookinghotel_appuser`
  ADD CONSTRAINT `FK5ldhno5eg9d593jqopqqhkust` FOREIGN KEY (`booking_hotel_id`) REFERENCES `booking_room` (`booking_room_id`),
  ADD CONSTRAINT `FKlhl1auwh6vd4dsniqmfbqg3pu` FOREIGN KEY (`app_user_id`) REFERENCES `app_user` (`dni`);

--
-- Filtros para la tabla `booking_flight`
--
ALTER TABLE `booking_flight`
  ADD CONSTRAINT `FKcllf1b5jasy6hmvusf88fjdxs` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`code`);

--
-- Filtros para la tabla `booking_passenger`
--
ALTER TABLE `booking_passenger`
  ADD CONSTRAINT `FK3ffak3wxe10nypegaejax29jc` FOREIGN KEY (`booking_flight_id`) REFERENCES `booking_flight` (`booking_flight_id`),
  ADD CONSTRAINT `FKbypyq6xy5y8gbs2lnm7w22302` FOREIGN KEY (`app_user_id`) REFERENCES `app_user` (`dni`);

--
-- Filtros para la tabla `booking_room`
--
ALTER TABLE `booking_room`
  ADD CONSTRAINT `FK4e002f18klgu08ekxnav2rwr9` FOREIGN KEY (`room_id`) REFERENCES `room` (`code`);

--
-- Filtros para la tabla `room`
--
ALTER TABLE `room`
  ADD CONSTRAINT `FKdosq3ww4h9m2osim6o0lugng8` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
