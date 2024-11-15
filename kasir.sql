-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 14, 2024 at 09:05 PM
-- Server version: 8.0.30
-- PHP Version: 8.3.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kasir`
--

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` bigint NOT NULL,
  `change_amount` double DEFAULT NULL,
  `payment` double DEFAULT NULL,
  `total_price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `change_amount`, `payment`, `total_price`) VALUES
(6, -15500, 30000, 45500),
(7, -15500, 30000, 45500),
(8, -25500, 30000, 55500),
(9, -25500, 30000, 55500),
(10, 4500, 60000, 55500),
(11, 4500, 60000, 55500),
(12, 4500, 60000, 55500),
(13, 4500, 60000, 55500),
(14, 9000, 60000, 51000),
(15, 27500, 60000, 32500),
(16, 27500, 60000, 32500),
(17, 0, 32500, 32500),
(18, 0, 32500, 32500),
(19, 43900, 100000, 56100);

-- --------------------------------------------------------

--
-- Table structure for table `order_items`
--

CREATE TABLE `order_items` (
  `id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `order_items`
--

INSERT INTO `order_items` (`id`, `quantity`, `order_id`, `product_id`, `name`) VALUES
(4, 2, 6, 1, NULL),
(5, 3, 6, 2, NULL),
(6, 2, 7, 1, NULL),
(7, 3, 7, 2, NULL),
(8, 3, 8, 1, NULL),
(9, 3, 8, 2, NULL),
(10, 3, 9, 1, NULL),
(11, 3, 9, 2, NULL),
(12, 3, 10, 1, NULL),
(13, 3, 10, 2, NULL),
(14, 3, 11, 1, NULL),
(15, 3, 11, 2, NULL),
(16, 3, 12, 1, NULL),
(17, 3, 12, 2, NULL),
(18, 3, 13, 1, NULL),
(19, 3, 13, 2, NULL),
(20, 3, 14, 2, 'Oreo'),
(21, 3, 14, 2, NULL),
(22, 3, 15, 2, 'Oreo'),
(23, 2, 15, 3, 'Mie Sedap'),
(24, 3, 16, 2, 'Oreo'),
(25, 2, 16, 3, 'Mie Sedap'),
(26, 3, 17, 2, 'Oreo'),
(27, 2, 17, 3, 'Mie Sedap'),
(28, 3, 18, 2, 'Oreo'),
(29, 2, 18, 3, 'Mie Sedap'),
(30, 3, 19, 2, 'Oreo'),
(31, 2, 19, 3, 'Mie Sedap'),
(32, 4, 19, 13, 'Pilus');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `stock` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `name`, `price`, `stock`) VALUES
(1, 'Nama Produk', 10000, 957),
(2, 'Oreo', 8500, 910),
(3, 'Mie Sedap', 3500, 984),
(4, 'Mie Indomie', 3500, 50),
(5, 'Fanta', 5500, 16),
(6, 'Sprite', 5500, 302),
(11, 'aa', 5500, 16),
(13, 'Pilus', 5900, 96);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `name`, `password`) VALUES
(1, 'kio@mail.com', 'kio', '$2a$10$MZws4Am..IGqw.P.uc8PAu475PNN2Kc30nD4AAgC/25MaP5UJEFWK'),
(4, 'flo@mail.com', 'flo', '$2a$10$3YstYK.fFj1lAUZZAr73UeInB20QYXS0oMmYQOunvX352B1N/ZP1G'),
(5, 'flo@mail.com', 'flo', '$2a$10$zQKztYdaLHhYaLYEkBXod.5vXb07a.UrpKppR00EFEOPv9de34pn.');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_items`
--
ALTER TABLE `order_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbioxgbv59vetrxe0ejfubep1w` (`order_id`),
  ADD KEY `FKocimc7dtr037rh4ls4l95nlfi` (`product_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `order_items`
--
ALTER TABLE `order_items`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `order_items`
--
ALTER TABLE `order_items`
  ADD CONSTRAINT `FKbioxgbv59vetrxe0ejfubep1w` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `FKocimc7dtr037rh4ls4l95nlfi` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
