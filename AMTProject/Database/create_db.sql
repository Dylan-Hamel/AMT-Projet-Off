-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Oct 29, 2018 at 03:17 PM
-- Server version: 5.7.23
-- PHP Version: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `AMT-DB`
--

-- --------------------------------------------------------

--
-- Table structure for table `projects`
--

CREATE TABLE `projects` (
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `api_key` varchar(255) NOT NULL,
  `api_secret` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `projects`
--

INSERT INTO `projects` (`name`, `description`, `api_key`, `api_secret`) VALUES
('testAppN1', 'testAppDesc1', '2bc75d59a5d292147f05d5124e64a211', '9d907a1a1d8078c160905860a01f7216740db83f82dd4721'),
('testAppN2', 'testAppDesc2', '2bc75d59a5d292147f05d5124e64a212', '9d907a1a1d8078c160905860a01f7216740db83f82dd4722'),
('testAppN3', 'testAppDesc3', '2bc75d59a5d292147f05d5124e64a213', '9d907a1a1d8078c160905860a01f7216740db83f82dd4723'),
('testAppN4', 'testAppDesc4', '2bc75d59a5d292147f05d5124e64a214', '9d907a1a1d8078c160905860a01f7216740db83f82dd4724'),
('testAppN5', 'testAppDesc5', '2bc75d59a5d292147f05d5124e64a215', '9d907a1a1d8078c160905860a01f7216740db83f82dd4725'),
('testAppN6', 'testAppDesc6', '2bc75d59a5d292147f05d5124e64a216', '9d907a1a1d8078c160905860a01f7216740db83f82dd4726'),
('testAppN7', 'testAppDesc7', '2bc75d59a5d292147f05d5124e64a217', '9d907a1a1d8078c160905860a01f7216740db83f82dd4727'),
('testAppN8', 'testAppDesc8', '2bc75d59a5d292147f05d5124e64a218', '9d907a1a1d8078c160905860a01f7216740db83f82dd4728'),
('testAppN9', 'testAppDesc9', '2bc75d59a5d292147f05d5124e64a219', '9d907a1a1d8078c160905860a01f7216740db83f82dd4729'),
('testAppN10', 'testAppDesc10', '2bc75d59a5d292147f05d5124e64a221', '9d907a1a1d8078c160905860a01f7216740db83f82dd4731'),
('testAppN11', 'testAppDesc11', '2bc75d59a5d292147f05d5124e64a222', '9d907a1a1d8078c160905860a01f7216740db83f82dd4732'),
('testAppN12', 'testAppDesc12', '2bc75d59a5d292147f05d5124e64a223', '9d907a1a1d8078c160905860a01f7216740db83f82dd4733'),
('testAppN13', 'testAppDesc13', '2bc75d59a5d292147f05d5124e64a224', '9d907a1a1d8078c160905860a01f7216740db83f82dd4734'),
('testAppN14', 'testAppDesc14', '2bc75d59a5d292147f05d5124e64a225', '9d907a1a1d8078c160905860a01f7216740db83f82dd4735'),
('testAppN15', 'testAppDesc15', '2bc75d59a5d292147f05d5124e64a226', '9d907a1a1d8078c160905860a01f7216740db83f82dd4736'),
('testAppN16', 'testAppDesc16', '2bc75d59a5d292147f05d5124e64a227', '9d907a1a1d8078c160905860a01f7216740db83f82dd4737'),
('testAppN17', 'testAppDesc17', '2bc75d59a5d292147f05d5124e64a228', '9d907a1a1d8078c160905860a01f7216740db83f82dd4738'),
('testAppN18', 'testAppDesc18', '2bc75d59a5d292147f05d5124e64a229', '9d907a1a1d8078c160905860a01f7216740db83f82dd4739'),
('testAppN19', 'testAppDesc19', '2bc75d59a5d292147f05d5124e64a231', '9d907a1a1d8078c160905860a01f7216740db83f82dd4741'),
('testAppN20', 'testAppDesc20', '2bc75d59a5d292147f05d5124e64a232', '9d907a1a1d8078c160905860a01f7216740db83f82dd4742'),
('testAppN21', 'testAppDesc21', '2bc75d59a5d292147f05d5124e64a233', '9d907a1a1d8078c160905860a01f7216740db83f82dd4743'),
('testAppN22', 'testAppDesc22', '2bc75d59a5d292147f05d5124e64a234', '9d907a1a1d8078c160905860a01f7216740db83f82dd4744'),
('testAppN23', 'testAppDesc23', '2bc75d59a5d292147f05d5124e64a235', '9d907a1a1d8078c160905860a01f7216740db83f82dd4745'),
('testAppN24', 'testAppDesc24', '2bc75d59a5d292147f05d5124e64a236', '9d907a1a1d8078c160905860a01f7216740db83f82dd4746'),
('testAppN25', 'testAppDesc25', '2bc75d59a5d292147f05d5124e64a237', '9d907a1a1d8078c160905860a01f7216740db83f82dd4747');

-- --------------------------------------------------------

--
-- Table structure for table `t_users_projects`
--

CREATE TABLE `t_users_projects` (
  `id` int(10) NOT NULL,
  `email` varchar(255) NOT NULL,
  `project` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_users_projects`
--

INSERT INTO `t_users_projects` (`id`, `email`, `project`) VALUES
(1, 'test@test.test', 'testAppN1'),
(2, 'test@test.test', 'testAppN2'),
(3, 'test@test.test', 'testAppN3'),
(4, 'test@test.test', 'testAppN4'),
(5, 'test@test.test', 'testAppN5'),
(6, 'test@test.test', 'testAppN6'),
(7, 'test@test.test', 'testAppN7'),
(8, 'test@test.test', 'testAppN8'),
(9, 'test@test.test', 'testAppN9'),
(10, 'test@test.test', 'testAppN10'),
(11, 'test@test.test', 'testAppN11'),
(12, 'test@test.test', 'testAppN12'),
(13, 'test@test.test', 'testAppN13'),
(14, 'test@test.test', 'testAppN14'),
(15, 'test@test.test', 'testAppN15'),
(16, 'test@test.test', 'testAppN16'),
(17, 'test@test.test', 'testAppN17'),
(18, 'test@test.test', 'testAppN18'),
(19, 'test@test.test', 'testAppN19'),
(20, 'test@test.test', 'testAppN20'),
(21, 'test@test.test', 'testAppN21'),
(22, 'test@test.test', 'testAppN22'),
(23, 'test@test.test', 'testAppN23'),
(24, 'test@test.test', 'testAppN24'),
(25, 'test@test.test', 'testAppN25');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `zip` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `admin` tinyint(1) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  `reset` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`firstname`, `lastname`, `email`, `password`, `address`, `zip`, `country`, `admin`, `enable`, `reset`) VALUES
('Dylan', 'Hamel', 'compte.dylan.hamel@gmail.com', '123123', 'Rue de Savoie 3B', '1196', 'Suisse', 0, 1, 0),
('Yannis', 'Ansermoz', 'yannis@me.me', '123123', 'Rue du Plaisir 33', '1400', 'Suisse', 1, 1, 0),
('testFN', 'testLN', 'test@test.test', 'testPWD', 'testAdd', 'testZIP', 'testCountry', 0, 1, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `projects`
--
ALTER TABLE `projects`
  ADD PRIMARY KEY (`name`),
  ADD UNIQUE KEY `api_key` (`api_key`);

--
-- Indexes for table `t_users_projects`
--
ALTER TABLE `t_users_projects`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `t_users_projects`
--
ALTER TABLE `t_users_projects`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
