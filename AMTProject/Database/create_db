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
('Dylan-test01', 'Test project 01', 'QBg4uuH2Nsc45dMr', 'Bh5mbRGB6y54Kz5HMsnaBcPr8LCT4tAz'),
('Dylan-test02', 'Test project 02', 'TCmMvQSjXCqK4Bgq', 'yBeG8v9PfDVA9Gu2Nk5QLzRSLsFcHCSr'),
('Test-123123', '123123123', '9c504cdc65339d4b83c72b3d284b2efd', 'fbbbf00c622214079e3d32c074c6e439aabb9e9d68af1431'),
('test-insert', '05', 'b5a8b049f8f813e9', 'a1cddf700ed52094'),
('test02', '32', 'e9009acc770a236b3c144241e5dabfa7', '59bdf01458da1ab6c8a141441a3c42f3042fb22d9990b6f1'),
('testest', '123', '2bc75d59a5d292147f05d5124e64a21b', '9d907a1a1d8078c160905860a01f7216740db83f82dd472f'),
('yannis', 'yannis project', 'e978044c6416acd2ddf22ab9d8606030', 'b4994ce510962df7d53089dc3121b13e391fcdb912b82fd8');

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
(1, 'jean@me.me', 'Dylan-Test01'),
(2, 'jean@me.me', 'Dylan-Test02'),
(5, 'jean@me.me', 'test-insert'),
(9, 'jean@me.me', 'test02'),
(10, 'jean@me.me', 'testest'),
(11, 'jean@me.me', 'Test-123123'),
(12, 'jean@me.me', 'yannis');

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
('Dylan', 'Hamel', 'd@d.ch', '1122', 'Rue des chats 1', '1233', 'Suisse', 1, 1, 1),
('Diane', 'Latchoin', 'diane@dfinet.ch', '123123', 'Rue 123123', '1111', 'Suisse', 0, 1, 0),
('Didier', 'Drogba', 'didier@me.me', 'l6J8sD9P', 'Rue des Alpes 99', '1111', 'Suisse', 0, 1, 0),
('Dylan', 'Hamel', 'dy@d.ch', '123123', 'Rue des chats 1', '1233', 'Suisse', 1, 1, 1),
('Dylan', 'Hamel', 'dylan.hamel@heig-vd.ch', '123123', 'Rue de Savoie 3B', '1196', 'Suisse', 0, 1, 0),
('Dylan', 'Hamel', 'dylan.hamel@outlook.com', '123123', 'Rue de Savoie 3B', '1196', 'Suisse', 0, 1, 0),
('Dylan', 'Hamel', 'dylan@me.me', '123123', 'Rue de Savoie 3B', '1196', 'Suisse', 1, 0, 0),
('Jean', 'Dupond', 'jean@me.me', '123123', 'Rue des Ponts 10', '1122', 'France', 0, 1, 0),
('Yannis', 'Ansermoz', 'yannis@me.me', '123123', 'Rue du Plaisir 33', '1400', 'Suisse', 1, 1, 0);

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
