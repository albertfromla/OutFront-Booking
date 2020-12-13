-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 02, 2020 at 08:56 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `flightreservation`
--

-- --------------------------------------------------------

--
-- Table structure for table `airlines`
--

CREATE TABLE `airlines` (
  `id` varchar(10) COLLATE utf8_bin NOT NULL,
  `name` varchar(30) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `airlines`
--

INSERT INTO `airlines` (`id`, `name`) VALUES
('AA', 'American Airlines'),
('AB', 'Air Berlin'),
('AJ', 'Air Japan'),
('AM', 'Air Madagascar'),
('BA', 'British Airways'),
('DA', 'Delta Airlines'),
('JA', 'JetBlue Airways'),
('L', 'Lufthansa'),
('SA', 'Southwest Airlines'),
('UA', 'United Airlines');

-- --------------------------------------------------------

--
-- Table structure for table `airports`
--

CREATE TABLE `airports` (
  `id` varchar(10) COLLATE utf8_bin NOT NULL,
  `name` varchar(30) COLLATE utf8_bin NOT NULL,
  `city` varchar(20) COLLATE utf8_bin NOT NULL,
  `country` varchar(30) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `airports`
--

INSERT INTO `airports` (`id`, `name`, `city`, `country`) VALUES
('ACE', 'LaGuardia', 'New York', 'United States of America'),
('ATL', 'Hartsfield-Jackson Atlanta Int', 'Atlanta', 'United States of America'),
('BTA', 'Berlin Tegel', 'Berlin', 'Germany'),
('COI', 'Chicago O\'Hare International', 'Chicago', 'Illinois'),
('III', 'Ivato International', 'Antananarivo', 'Madagascar'),
('JFK', 'John F. Kennedy International', 'New York', 'United States of America'),
('LAX', 'Los Angeles International', 'Los Angeles	', 'United States of America'),
('LHL', 'London Heathrow', 'London', 'United Kingdom'),
('LIB', 'Logan International', 'Boston', 'United States of America'),
('SFI', 'San Francisco International', 'San Francisco', 'United States of America'),
('TIT', 'Tokyo International', 'Tokyo', 'Japan');

-- --------------------------------------------------------

--
-- Table structure for table `flights`
--

CREATE TABLE `flights` (
  `id` int(10) NOT NULL,
  `DaysOperating` int(7) NOT NULL,
  `TotalSeats` int(5) NOT NULL,
  `AirlineId` varchar(10) COLLATE utf8_bin NOT NULL,
  `fare` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `flights`
--

INSERT INTO `flights` (`id`, `DaysOperating`, `TotalSeats`, `AirlineId`, `fare`) VALUES
(111, 1010100, 100, 'AA', 300),
(111, 1111111, 150, 'JA', 150),
(1337, 1100011, 33, 'AM', 500);

-- --------------------------------------------------------

--
-- Table structure for table `legs`
--

CREATE TABLE `legs` (
  `id` int(10) NOT NULL,
  `FlightId` int(10) NOT NULL,
  `AirlineId` varchar(10) COLLATE utf8_bin NOT NULL,
  `ResrId` int(10) NOT NULL,
  `fromStopNo` int(10) NOT NULL,
  `legNo` int(10) NOT NULL,
  `deptDate` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `legs`
--

INSERT INTO `legs` (`id`, `FlightId`, `AirlineId`, `ResrId`, `fromStopNo`, `legNo`, `deptDate`) VALUES
(1, 111, 'AA', 1, 1, 1, '2020-04-29 11:00:00'),
(2, 111, 'AA', 1, 2, 2, '2020-04-29 19:00:00'),
(3, 111, 'AA', 2, 2, 1, '2020-04-30 22:05:06'),
(4, 1337, 'AM', 3, 1, 1, '2020-04-13 19:00:00'),
(5, 111, 'JA', 4, 1, 1, '2020-04-30 00:16:54'),
(12, 111, 'AA', 7, 1, 1, '2020-05-05 11:00:00'),
(13, 111, 'AA', 8, 1, 1, '2020-05-05 11:00:00'),
(14, 111, 'AA', 9, 2, 1, '2020-05-02 19:00:00'),
(15, 111, 'AA', 9, 3, 2, '2020-05-03 09:30:00');

-- --------------------------------------------------------

--
-- Table structure for table `managers`
--

CREATE TABLE `managers` (
  `id` int(10) NOT NULL,
  `name` varchar(30) COLLATE utf8_bin NOT NULL,
  `email` varchar(30) COLLATE utf8_bin NOT NULL,
  `password` varchar(30) COLLATE utf8_bin NOT NULL,
  `role` varchar(20) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `managers`
--

INSERT INTO `managers` (`id`, `name`, `email`, `password`, `role`) VALUES
(1, 'Hamza Israr', 'admin@admin.com', 'admin', 'Manager');

-- --------------------------------------------------------

--
-- Table structure for table `passengers`
--

CREATE TABLE `passengers` (
  `id` int(10) NOT NULL,
  `fname` varchar(30) COLLATE utf8_bin NOT NULL,
  `lname` varchar(30) COLLATE utf8_bin NOT NULL,
  `password` varchar(20) COLLATE utf8_bin NOT NULL,
  `Address` varchar(150) COLLATE utf8_bin NOT NULL,
  `state` varchar(20) COLLATE utf8_bin NOT NULL,
  `City` varchar(20) COLLATE utf8_bin NOT NULL,
  `zipCode` varchar(10) COLLATE utf8_bin NOT NULL,
  `Phone` varchar(20) COLLATE utf8_bin NOT NULL,
  `Email` varchar(40) COLLATE utf8_bin NOT NULL,
  `cardNo` varchar(20) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `passengers`
--

INSERT INTO `passengers` (`id`, `fname`, `lname`, `password`, `Address`, `state`, `City`, `zipCode`, `Phone`, `Email`, `cardNo`) VALUES
(1, 'Jane', 'Smit', '123', '100 Nicolls Rd, Stony', 'New York', 'New York', '17790', '555-555-5555', 'awesomejane@ftw.com', '19482899321235'),
(2, 'John', 'Doe', '123', '123 N Fake Street', 'New York', 'New York', '10001', '123-123-1234', 'jdoe@woot.com', '10293859102344'),
(3, 'Rick', 'Astley', '123', '1337 Internet Lane', 'Los Angeles', 'California', '90001', '314-159-2653', 'rickroller@rolld.com', '10293847561029'),
(4, 'Hamza', 'Israr', '123', 'E1/ D1 Alnoor Town', 'Punjab', 'Lahore', '54810', '03234219431', 'HamzaIsrar12@gmail.com', '121121212121');

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `Id` int(10) NOT NULL,
  `totalFare` int(10) NOT NULL,
  `meal` varchar(20) COLLATE utf8_bin NOT NULL,
  `PassengerId` int(10) NOT NULL,
  `class` varchar(10) COLLATE utf8_bin NOT NULL,
  `seatNo` varchar(10) COLLATE utf8_bin NOT NULL,
  `departure` varchar(10) COLLATE utf8_bin NOT NULL,
  `arrival` varchar(10) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`Id`, `totalFare`, `meal`, `PassengerId`, `class`, `seatNo`, `departure`, `arrival`) VALUES
(1, 1200, 'Chips', 2, 'Economy', '33F', 'ACE', 'TIT'),
(2, 500, 'Fish', 1, 'First', '13A', 'BTA', 'TIT'),
(3, 3333, 'Sushi', 3, 'First', '1A', 'SFI', 'ACE'),
(4, 2000, 'Chips', 1, 'First', '12A', 'COI', 'ATL'),
(7, 600, 'Chips', 4, 'Economy', '28A', 'ACE', 'BTA'),
(8, 600, 'Chips', 4, 'Economy', '29A', 'ACE', 'BTA'),
(9, 300, 'Chips', 4, 'Economy', '6A', 'TIT', 'ACE');

-- --------------------------------------------------------

--
-- Table structure for table `stopsat`
--

CREATE TABLE `stopsat` (
  `id` int(10) NOT NULL,
  `s_d_id` int(10) NOT NULL,
  `FlightId` int(10) NOT NULL,
  `AirlineId` varchar(10) COLLATE utf8_bin NOT NULL,
  `stopNo` int(10) NOT NULL,
  `airportIdOrigin` varchar(10) COLLATE utf8_bin NOT NULL,
  `airportIdDestination` varchar(10) COLLATE utf8_bin NOT NULL,
  `arrTime` datetime NOT NULL,
  `deptTime` datetime NOT NULL,
  `OnTime` int(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `stopsat`
--

INSERT INTO `stopsat` (`id`, `s_d_id`, `FlightId`, `AirlineId`, `stopNo`, `airportIdOrigin`, `airportIdDestination`, `arrTime`, `deptTime`, `OnTime`) VALUES
(1, 1, 111, 'AA', 1, 'ACE', 'BTA', '2020-05-05 09:00:00', '2020-05-05 11:00:00', 1),
(2, 1, 111, 'AA', 2, 'BTA', 'TIT', '2020-05-05 17:00:00', '2020-05-05 19:00:00', 1),
(3, 1, 111, 'AA', 3, 'TIT', 'LIB', '2020-05-06 07:30:00', '2020-05-06 09:30:00', 1),
(4, 2, 1337, 'AM', 1, 'SFI', 'ACE', '2020-04-30 19:30:00', '2020-04-30 22:14:50', 0),
(5, 3, 111, 'AA', 1, 'LIB', 'TIT', '2020-05-02 09:00:00', '2020-05-02 11:00:00', 1),
(6, 3, 111, 'AA', 2, 'TIT', 'BTA', '2020-05-02 17:00:00', '2020-05-02 19:00:00', 1),
(7, 3, 111, 'AA', 3, 'BTA', 'ACE', '2020-05-03 07:30:00', '2020-05-03 09:30:00', 1),
(8, 4, 1337, 'AM', 1, 'ACE', 'LHL', '2020-05-12 07:54:03', '2020-05-14 07:54:03', 0),
(10, 5, 111, 'AA', 1, 'ACE', 'BTA', '2020-05-20 09:00:00', '2020-05-21 11:00:00', 0),
(11, 6, 111, 'JA', 1, 'COI', 'ATL', '2020-05-10 05:46:31', '2020-05-10 10:46:31', 1);

-- --------------------------------------------------------

--
-- Table structure for table `totalfare`
--

CREATE TABLE `totalfare` (
  `id` int(11) NOT NULL,
  `advPurc` int(11) NOT NULL,
  `lengthOfStay` int(11) NOT NULL,
  `BookFee` int(11) NOT NULL,
  `Rstr` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `airlines`
--
ALTER TABLE `airlines`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `airports`
--
ALTER TABLE `airports`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `flights`
--
ALTER TABLE `flights`
  ADD PRIMARY KEY (`id`,`AirlineId`),
  ADD KEY `AirlineId` (`AirlineId`);

--
-- Indexes for table `legs`
--
ALTER TABLE `legs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `legs_ibfk_1` (`FlightId`,`AirlineId`),
  ADD KEY `ResrId` (`ResrId`);

--
-- Indexes for table `managers`
--
ALTER TABLE `managers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `passengers`
--
ALTER TABLE `passengers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `PassengerId` (`PassengerId`),
  ADD KEY `departure` (`departure`),
  ADD KEY `arrival` (`arrival`);

--
-- Indexes for table `stopsat`
--
ALTER TABLE `stopsat`
  ADD PRIMARY KEY (`id`),
  ADD KEY `airportIdOrigin` (`airportIdOrigin`),
  ADD KEY `stopsat_ibfk_3` (`airportIdDestination`),
  ADD KEY `stopsat_ibfk_1` (`FlightId`,`AirlineId`);

--
-- Indexes for table `totalfare`
--
ALTER TABLE `totalfare`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `legs`
--
ALTER TABLE `legs`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `managers`
--
ALTER TABLE `managers`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `passengers`
--
ALTER TABLE `passengers`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `Id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `stopsat`
--
ALTER TABLE `stopsat`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `totalfare`
--
ALTER TABLE `totalfare`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `flights`
--
ALTER TABLE `flights`
  ADD CONSTRAINT `flights_ibfk_1` FOREIGN KEY (`AirlineId`) REFERENCES `airlines` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `legs`
--
ALTER TABLE `legs`
  ADD CONSTRAINT `legs_ibfk_1` FOREIGN KEY (`FlightId`,`AirlineId`) REFERENCES `flights` (`id`, `AirlineId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `legs_ibfk_2` FOREIGN KEY (`ResrId`) REFERENCES `reservation` (`Id`);

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`PassengerId`) REFERENCES `passengers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`departure`) REFERENCES `airports` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reservation_ibfk_3` FOREIGN KEY (`arrival`) REFERENCES `airports` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `stopsat`
--
ALTER TABLE `stopsat`
  ADD CONSTRAINT `stopsat_ibfk_1` FOREIGN KEY (`FlightId`,`AirlineId`) REFERENCES `flights` (`id`, `AirlineId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `stopsat_ibfk_2` FOREIGN KEY (`airportIdOrigin`) REFERENCES `airports` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `stopsat_ibfk_3` FOREIGN KEY (`airportIdDestination`) REFERENCES `airports` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
