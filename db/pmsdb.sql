-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 28, 2024 at 08:28 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pmsdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `parking_category`
--

CREATE TABLE `parking_category` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(150) NOT NULL,
  `category_added_on` int(16) NOT NULL,
  `category_updated_on` int(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `parking_category`
--

INSERT INTO `parking_category` (`category_id`, `category_name`, `category_added_on`, `category_updated_on`) VALUES
(2, 'Two Wheelers', 1681823005, 0),
(3, 'Four Wheelers', 1681823014, 0),
(4, 'Bicycles', 1681823024, 0);

-- --------------------------------------------------------

--
-- Table structure for table `parking_customer`
--

CREATE TABLE `parking_customer` (
  `customer_id` int(11) NOT NULL,
  `customer_name` varchar(200) NOT NULL,
  `customer_contact_no` varchar(12) NOT NULL,
  `vehicle_category` int(11) NOT NULL,
  `vehicle_number` varchar(20) NOT NULL,
  `parking_slot_number` int(11) NOT NULL,
  `vehicle_in_datetime` datetime NOT NULL,
  `vehicle_out_datetime` datetime NOT NULL,
  `total_parking_duration` int(6) NOT NULL,
  `parking_charges` decimal(9,2) NOT NULL,
  `vehicle_status` enum('In','Out') NOT NULL,
  `enter_by` int(11) NOT NULL,
  `customer_created_on` int(16) NOT NULL,
  `customer_updated_on` int(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `parking_customer`
--

INSERT INTO `parking_customer` (`customer_id`, `customer_name`, `customer_contact_no`, `vehicle_category`, `vehicle_number`, `parking_slot_number`, `vehicle_in_datetime`, `vehicle_out_datetime`, `total_parking_duration`, `parking_charges`, `vehicle_status`, `enter_by`, `customer_created_on`, `customer_updated_on`) VALUES
(3, 'Donna Hubber', '7412589630', 4, 'BS 123', 2, '2023-05-02 16:14:00', '2023-05-02 22:14:00', 1, 10.00, 'Out', 1, 1683801859, 0),
(4, 'Wilbur Bond', '9734886031', 2, '7412', 12, '2023-06-01 15:30:00', '2023-06-01 20:25:00', 2, 15.00, 'Out', 1, 1685613794, 1685700305),
(5, 'Ruth Lee', '8593913711', 2, '9635', 13, '2023-06-01 15:35:00', '2023-06-02 21:25:00', 3, 10.00, 'Out', 1, 1685613867, 1685700291),
(6, 'Stephen Beattie', '9787871607', 4, '145', 2, '2023-06-01 15:35:00', '2023-06-02 19:35:00', 1, 10.00, 'Out', 1, 1685613933, 1685700277),
(7, 'Gregg Griffeth', '7068856830', 3, '7589', 27, '2023-06-01 14:30:00', '2023-06-02 18:45:00', 2, 30.00, 'Out', 1, 1685613998, 1685700267),
(8, 'Patty Stewart', '8804315272', 2, '8695', 14, '2023-06-01 15:30:00', '2023-06-01 17:00:00', 2, 15.00, 'Out', 1, 1685614089, 1685614704),
(9, 'Trevor Manns', '8456470191', 2, '8563', 15, '2023-06-01 15:35:00', '2023-06-02 19:30:00', 4, 25.00, 'Out', 1, 1685614156, 1685700253),
(10, 'Dwayne Stuck', '9406980159', 3, '1256', 28, '2023-06-01 15:40:00', '2023-06-02 19:15:00', 2, 30.00, 'Out', 1, 1685614216, 1685700241),
(11, 'Anna Thornburg', '8607796893', 4, '4526', 3, '2023-06-01 15:40:00', '2023-06-02 18:20:00', 1, 10.00, 'Out', 1, 1685614278, 1685700227),
(12, 'Frances Joiner', '8635788730', 2, '7412', 16, '2023-06-01 15:40:00', '2023-06-01 20:10:00', 4, 25.00, 'Out', 1, 1685614337, 1685700209),
(13, 'Celia Pickett', '9094460348', 3, '8523', 29, '2023-06-01 15:45:00', '2023-06-01 19:30:00', 1, 40.00, 'Out', 1, 1685614444, 1685700193),
(14, 'John Hall', '7742673121', 2, '3652', 17, '2023-06-01 15:45:00', '2023-06-01 18:35:00', 4, 25.00, 'Out', 1, 1685614500, 1685700177),
(15, 'George Simpson', '741447556', 2, '8569', 12, '2023-06-02 14:00:00', '2023-06-02 17:00:00', 1, 20.00, 'Out', 1, 1685703073, 1685792169),
(16, 'John Almanza', '9632587452', 2, '3658', 13, '2023-06-02 13:50:00', '2023-06-03 18:00:00', 2, 15.00, 'Out', 1, 1685703126, 1685792187),
(17, 'Ella Nance', '7539518523', 4, '1256', 11, '2023-06-02 15:00:00', '2023-06-03 19:30:00', 1, 10.00, 'Out', 1, 1685703179, 1685792200),
(18, 'Danae Capps', '7588965630', 3, '8563', 36, '2023-06-02 15:30:00', '2023-06-03 17:30:00', 2, 30.00, 'Out', 1, 1685703231, 1685792214),
(19, 'Julia Ramirez', '7412589630', 2, '8563', 14, '2023-06-02 16:00:00', '2023-06-03 18:00:00', 4, 25.00, 'Out', 1, 1685703289, 1685792227),
(20, 'Dawna Wood', '8569632520', 2, '9685', 21, '2023-06-03 14:15:00', '2023-06-03 18:05:00', 2, 15.00, 'Out', 1, 1685795117, 1685795409),
(21, 'Anita Delaney', '7456323252', 4, '3685', 2, '2023-06-03 14:29:00', '2023-06-03 18:05:00', 1, 10.00, 'Out', 1, 1685795171, 1685795399),
(22, 'Judy Westcott', '6985635235', 3, '6856', 36, '2023-06-03 14:45:00', '2023-06-03 17:45:00', 2, 30.00, 'Out', 1, 1685795222, 1685795383),
(23, 'Brittany Jones', '7458963526', 2, '8562', 22, '2023-06-03 15:00:00', '2023-06-03 17:55:00', 2, 15.00, 'Out', 1, 1685795273, 1685795371),
(24, 'Wm Davey', '7458963525', 3, '7456', 28, '2023-06-03 14:01:00', '2023-06-03 17:00:00', 2, 30.00, 'Out', 1, 1685795338, 1685795362),
(25, 'Fred Cloud', '8563524120', 3, '4125', 27, '2023-06-03 16:00:00', '2023-06-03 22:15:00', 2, 30.00, 'Out', 1, 1685795451, 1685940437),
(26, 'Angela Kinney', '7412589630', 2, '6352', 12, '2023-06-03 16:30:00', '2023-06-05 22:50:00', 1, 20.00, 'Out', 1, 1685795492, 1685940451),
(27, 'Adrienne Maitland', '8569852330', 3, '6325', 27, '2023-06-04 07:15:00', '0000-00-00 00:00:00', 2, 30.00, 'In', 1, 1685940527, 0),
(28, 'Bessie Johnson', '9632587520', 3, '4526', 36, '2023-06-04 08:30:00', '2023-06-04 13:30:00', 1, 40.00, 'Out', 1, 1685940591, 1685941100),
(29, 'Roberta Edwards', '7452368525', 2, '7412', 12, '2023-06-04 08:20:00', '0000-00-00 00:00:00', 2, 15.00, 'In', 1, 1685940648, 0),
(30, 'Patricia Banks', '9635522630', 3, '6352', 28, '2023-06-04 08:25:00', '2023-06-04 00:30:00', 2, 30.00, 'Out', 1, 1685940701, 1685941117),
(31, 'Kenneth Crider', '7412586652', 3, '6852', 29, '2023-06-04 09:00:00', '0000-00-00 00:00:00', 2, 30.00, 'In', 1, 1685940753, 0),
(32, 'Victor Soto', '7415585520', 2, '9632', 21, '2023-06-05 08:00:00', '2023-06-05 00:30:00', 2, 15.00, 'Out', 1, 1685940849, 1685941135),
(33, 'Karen Taylor', '7455636520', 3, '7532', 30, '2023-06-05 08:20:00', '0000-00-00 00:00:00', 1, 40.00, 'In', 1, 1685940899, 0),
(34, 'Catherine Cross', '8523668526', 2, '7541', 22, '2023-06-05 08:30:00', '0000-00-00 00:00:00', 1, 20.00, 'In', 1, 1685940951, 0),
(35, 'Lillia Chitwood', '7568633520', 2, '7545', 23, '2023-06-05 08:00:00', '2023-06-05 00:29:00', 1, 20.00, 'Out', 1, 1685941004, 1685941154),
(36, 'George Armstrong', '9685741525', 2, '7412589630', 24, '2023-06-05 09:30:00', '0000-00-00 00:00:00', 2, 15.00, 'In', 1, 1685941063, 0);

-- --------------------------------------------------------

--
-- Table structure for table `parking_duration`
--

CREATE TABLE `parking_duration` (
  `duration_id` int(11) NOT NULL,
  `duration_value` int(3) NOT NULL,
  `duration_created_on` int(16) NOT NULL,
  `duration_updated_on` int(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `parking_duration`
--

INSERT INTO `parking_duration` (`duration_id`, `duration_value`, `duration_created_on`, `duration_updated_on`) VALUES
(1, 6, 1681824039, 1681908704),
(2, 3, 1681824316, 1681908696),
(3, 1, 1681824355, 0),
(4, 12, 1681908714, 0),
(6, 24, 1681908774, 0);

-- --------------------------------------------------------

--
-- Table structure for table `parking_price`
--

CREATE TABLE `parking_price` (
  `price_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `duration_id` int(11) NOT NULL,
  `price_value` double(12,2) NOT NULL,
  `price_created_on` int(16) NOT NULL,
  `price_updated_on` int(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `parking_price`
--

INSERT INTO `parking_price` (`price_id`, `category_id`, `duration_id`, `price_value`, `price_created_on`, `price_updated_on`) VALUES
(1, 4, 3, 5.00, 1681970362, 0),
(2, 4, 2, 5.00, 1681971395, 1681972122),
(3, 4, 1, 10.00, 1681971405, 1681972130),
(4, 4, 4, 10.00, 1681971431, 1681972138),
(5, 4, 6, 15.00, 1681972108, 1681972146),
(6, 2, 3, 10.00, 1685612706, 0),
(7, 2, 2, 15.00, 1685613163, 0),
(8, 2, 1, 20.00, 1685613176, 1685613201),
(9, 2, 4, 25.00, 1685613226, 0),
(10, 2, 6, 30.00, 1685613237, 0),
(11, 3, 3, 20.00, 1685613282, 0),
(12, 3, 2, 30.00, 1685613302, 0),
(13, 3, 1, 40.00, 1685613322, 0),
(14, 3, 4, 50.00, 1685613332, 0),
(15, 3, 6, 60.00, 1685613342, 0);

-- --------------------------------------------------------

--
-- Table structure for table `parking_setting`
--

CREATE TABLE `parking_setting` (
  `parking_id` int(11) NOT NULL,
  `parking_name` varchar(200) NOT NULL,
  `parking_contact_person` varchar(150) NOT NULL,
  `parking_contact_number` varchar(15) NOT NULL,
  `parking_timezone` varchar(80) NOT NULL,
  `parking_currency` varchar(10) NOT NULL,
  `parking_datetime_format` varchar(30) NOT NULL,
  `parking_created_on` int(16) NOT NULL,
  `parking_updated_on` int(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `parking_setting`
--

INSERT INTO `parking_setting` (`parking_id`, `parking_name`, `parking_contact_person`, `parking_contact_number`, `parking_timezone`, `parking_currency`, `parking_datetime_format`, `parking_created_on`, `parking_updated_on`) VALUES
(1, 'John Smith Parking', 'John Smith', '7412589630', 'Asia/Kolkata', 'USD', 'd/m/Y H:i:s', 1681386085, 1685615706);

-- --------------------------------------------------------

--
-- Table structure for table `parking_slot`
--

CREATE TABLE `parking_slot` (
  `parking_slot_id` int(11) NOT NULL,
  `vehicle_category_id` int(11) NOT NULL,
  `parking_slot_name` varchar(100) NOT NULL,
  `parking_slot_status` enum('Available','Not Available') NOT NULL,
  `parking_slot_created_on` int(16) NOT NULL,
  `parking_slot_updated_on` int(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `parking_slot`
--

INSERT INTO `parking_slot` (`parking_slot_id`, `vehicle_category_id`, `parking_slot_name`, `parking_slot_status`, `parking_slot_created_on`, `parking_slot_updated_on`) VALUES
(2, 4, 'BC-1', 'Available', 1681979625, 1685613381),
(3, 4, 'BC-2', 'Available', 1685613364, 0),
(4, 4, 'BC-3', 'Available', 1685613390, 0),
(5, 4, 'BC-4', 'Available', 1685613400, 0),
(6, 4, 'BC-5', 'Available', 1685613410, 0),
(7, 4, 'BC-6', 'Available', 1685613419, 0),
(8, 4, 'BC-7', 'Available', 1685613427, 0),
(9, 4, 'BC-8', 'Available', 1685613436, 0),
(10, 4, 'BC-9', 'Available', 1685613444, 0),
(11, 4, 'BC-10', 'Available', 1685613452, 0),
(12, 2, 'TW-1', 'Not Available', 1685613462, 0),
(13, 2, 'TW-2', 'Available', 1685613469, 0),
(14, 2, 'TW-3', 'Available', 1685613476, 0),
(15, 2, 'TW-4', 'Available', 1685613483, 0),
(16, 2, 'TW-5', 'Available', 1685613490, 0),
(17, 2, 'TW-6', 'Available', 1685613497, 0),
(18, 2, 'TW-7', 'Available', 1685613505, 0),
(19, 2, 'TW-8', 'Available', 1685613512, 0),
(20, 2, 'TW-9', 'Available', 1685613519, 0),
(21, 2, 'TW-10', 'Available', 1685613527, 0),
(22, 2, 'TW-11', 'Not Available', 1685613535, 0),
(23, 2, 'TW-12', 'Available', 1685613543, 0),
(24, 2, 'TW-13', 'Not Available', 1685613552, 0),
(25, 2, 'TW-14', 'Available', 1685613559, 0),
(26, 2, 'TW-15', 'Available', 1685613566, 0),
(27, 3, 'FW-1', 'Not Available', 1685613574, 0),
(28, 3, 'FW-2', 'Available', 1685613582, 0),
(29, 3, 'FW-3', 'Not Available', 1685613589, 0),
(30, 3, 'FW-4', 'Not Available', 1685613597, 0),
(31, 3, 'FW-5', 'Available', 1685613605, 0),
(32, 3, 'FW-6', 'Available', 1685613613, 0),
(33, 3, 'FW-7', 'Available', 1685613621, 0),
(34, 3, 'FW-8', 'Available', 1685613629, 0),
(35, 3, 'FW-9', 'Available', 1685613637, 0),
(36, 3, 'FW-10', 'Available', 1685613645, 0);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `user_email` varchar(150) NOT NULL,
  `user_pass` varchar(30) NOT NULL,
  `user_type` enum('admin','User') NOT NULL,
  `user_created_on` int(16) NOT NULL,
  `user_updated_on` int(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `user_email`, `user_pass`, `user_type`, `user_created_on`, `user_updated_on`) VALUES
(1, 'admin@gmail.com', 'admin', 'admin', 1681386085, 1681975021),
(2, 'peterparker@gmail.com', 'password', 'User', 1683799906, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `parking_category`
--
ALTER TABLE `parking_category`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `parking_customer`
--
ALTER TABLE `parking_customer`
  ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `parking_duration`
--
ALTER TABLE `parking_duration`
  ADD PRIMARY KEY (`duration_id`);

--
-- Indexes for table `parking_price`
--
ALTER TABLE `parking_price`
  ADD PRIMARY KEY (`price_id`);

--
-- Indexes for table `parking_setting`
--
ALTER TABLE `parking_setting`
  ADD PRIMARY KEY (`parking_id`);

--
-- Indexes for table `parking_slot`
--
ALTER TABLE `parking_slot`
  ADD PRIMARY KEY (`parking_slot_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `parking_category`
--
ALTER TABLE `parking_category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `parking_customer`
--
ALTER TABLE `parking_customer`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `parking_duration`
--
ALTER TABLE `parking_duration`
  MODIFY `duration_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `parking_price`
--
ALTER TABLE `parking_price`
  MODIFY `price_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `parking_setting`
--
ALTER TABLE `parking_setting`
  MODIFY `parking_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `parking_slot`
--
ALTER TABLE `parking_slot`
  MODIFY `parking_slot_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
