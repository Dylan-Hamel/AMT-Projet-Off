-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Oct 20, 2018 at 07:24 PM
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

-- --------------------------------------------------------

--
-- Table structure for table `t_users_projects`
--

CREATE TABLE `t_users_projects` (
  `id` int(10) NOT NULL,
  `email` varchar(255) NOT NULL,
  `project` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `enable` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`firstname`, `lastname`, `email`, `password`, `address`, `zip`, `country`, `admin`, `enable`) VALUES
('Didier', 'Drogba', 'didier@me.me', '123123', 'Rue des Alpes 99', '1111', 'Suisse', 0, 1),
('Dylan', 'Hamel', 'dylan@me.me', '123123', 'Rue de Savoie 3B', '1196', 'Suisse', 1, 1),
('Yannis', 'Ansermoz', 'yannis@me.me', '123123', 'Rue du Plaisir 33', '1400', 'Suisse', 1, 1);

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
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;
