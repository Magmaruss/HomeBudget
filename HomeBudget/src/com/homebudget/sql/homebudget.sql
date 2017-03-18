-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Czas generowania: 11 Mar 2017, 20:38
-- Wersja serwera: 10.1.16-MariaDB
-- Wersja PHP: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `homebudget`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `expenses_category`
--

CREATE TABLE `expenses_category` (
  `ID` int(12) NOT NULL,
  `CATEGORY_NAME` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `expenses_plan`
--

CREATE TABLE `expenses_plan` (
  `EXPENSES_ID` int(11) NOT NULL,
  `DATE` date NOT NULL,
  `AMOUNT` decimal(9,2) NOT NULL,
  `CATEGORY_ID` int(12) NOT NULL,
  `USER_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `expenses_real`
--

CREATE TABLE `expenses_real` (
  `EXPENSES_ID` int(11) NOT NULL,
  `DATE` date NOT NULL,
  `AMOUNT` decimal(9,2) NOT NULL,
  `CATEGORY_ID` int(12) NOT NULL,
  `USER_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `expenses_subcategory`
--

CREATE TABLE `expenses_subcategory` (
  `ID` int(12) NOT NULL,
  `SUBCATEGORY_NAME` varchar(20) NOT NULL,
  `SUBCATEGORY_ID` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `revenues_category`
--

CREATE TABLE `revenues_category` (
  `CATEGORY_ID` int(12) NOT NULL,
  `CATEGORY_NAME` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `revenues_plan`
--

CREATE TABLE `revenues_plan` (
  `REVENUES_ID` int(11) NOT NULL,
  `DATE` date NOT NULL,
  `AMOUNT` decimal(9,2) NOT NULL,
  `CATEGORY_ID` int(12) NOT NULL,
  `USER_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `revenues_real`
--

CREATE TABLE `revenues_real` (
  `REVENUES_ID` int(11) NOT NULL,
  `DATE` date NOT NULL,
  `AMOUNT` decimal(9,2) NOT NULL,
  `CATEGORY_ID` int(12) NOT NULL,
  `USER_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `USER_ID` int(11) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(128) NOT NULL,
  `LAST_LOGIN` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`USER_ID`, `NAME`, `PASSWORD`, `LAST_LOGIN`) VALUES
(1, 'Kamil', '3583052a475b234cb011ce8007627b6034a87d121c6a32593659184ceb1c74a34e25bd8ac7cfdb2dbe7340d382710460fec1e6f8beccbe1c028484580d372c92', '2017-03-05 01:51:11'),
(2, 'Adam', '5d9b142f48a635898f7caef7b5f86e8faa18877d8c6460f6ec1e24b4c85a12443eda20abe1bf7156fbfd149a8395409b9f82e55fd9bdf4cebe1a1dbd8cfe9473', NULL),
(3, 'Heniek', '24883f146f36163678854c189a7c46f18d054c834e34a6f74151961cf8db19e59200b6dcdecc054990d84a515bbed81e8a96a94d08ebee069eacf2bb92034690', NULL),
(4, 'Zenek', '2ac0761d218078b14c8c792dc326eaef047ce0da5e08e276dd1ae87d98a84136f4bb0d965182411014c1213155aa2aaca75264f9356e331878c22f1063b5d461', NULL),
(5, 'Heniek1', '0dc47417f08095cbd5d0d1b7366eb6dc4937f963dc3da30be543f7600862e13a0d56b0b379b1edd86c41c93b6969ca5407afa50bbf7f61376506d22badd2db15', NULL),
(6, 'Piotrek', '407c2dfff8594a7da3248982e7bad3cb5d3cf80637acde7a3e1b97c6df6b7894f23376ef0aeeed8e17bd1cbc700ebe928122a9d812c070187aa6fdc6b0eaab15', '2017-03-05 18:05:44');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `expenses_category`
--
ALTER TABLE `expenses_category`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `expenses_plan`
--
ALTER TABLE `expenses_plan`
  ADD PRIMARY KEY (`EXPENSES_ID`),
  ADD UNIQUE KEY `CATEGORY_ID` (`CATEGORY_ID`),
  ADD UNIQUE KEY `USER_ID` (`USER_ID`);

--
-- Indexes for table `expenses_real`
--
ALTER TABLE `expenses_real`
  ADD PRIMARY KEY (`EXPENSES_ID`),
  ADD UNIQUE KEY `CATEGORY_ID` (`CATEGORY_ID`),
  ADD UNIQUE KEY `USER_ID` (`USER_ID`);

--
-- Indexes for table `expenses_subcategory`
--
ALTER TABLE `expenses_subcategory`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `SUBCATEGORY_ID` (`SUBCATEGORY_ID`);

--
-- Indexes for table `revenues_category`
--
ALTER TABLE `revenues_category`
  ADD PRIMARY KEY (`CATEGORY_ID`);

--
-- Indexes for table `revenues_plan`
--
ALTER TABLE `revenues_plan`
  ADD PRIMARY KEY (`REVENUES_ID`),
  ADD UNIQUE KEY `CATEGORY_ID` (`CATEGORY_ID`),
  ADD UNIQUE KEY `USER_ID` (`USER_ID`);

--
-- Indexes for table `revenues_real`
--
ALTER TABLE `revenues_real`
  ADD PRIMARY KEY (`REVENUES_ID`),
  ADD UNIQUE KEY `CATEGORY_ID` (`CATEGORY_ID`),
  ADD UNIQUE KEY `USER_ID` (`USER_ID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`USER_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `expenses_category`
--
ALTER TABLE `expenses_category`
  MODIFY `ID` int(12) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT dla tabeli `expenses_plan`
--
ALTER TABLE `expenses_plan`
  MODIFY `EXPENSES_ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT dla tabeli `expenses_real`
--
ALTER TABLE `expenses_real`
  MODIFY `EXPENSES_ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT dla tabeli `expenses_subcategory`
--
ALTER TABLE `expenses_subcategory`
  MODIFY `ID` int(12) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT dla tabeli `revenues_category`
--
ALTER TABLE `revenues_category`
  MODIFY `CATEGORY_ID` int(12) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT dla tabeli `revenues_plan`
--
ALTER TABLE `revenues_plan`
  MODIFY `REVENUES_ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT dla tabeli `revenues_real`
--
ALTER TABLE `revenues_real`
  MODIFY `REVENUES_ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT dla tabeli `users`
--
ALTER TABLE `users`
  MODIFY `USER_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `expenses_plan`
--
ALTER TABLE `expenses_plan`
  ADD CONSTRAINT `expenses_plan_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`),
  ADD CONSTRAINT `expenses_plan_ibfk_2` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `expenses_subcategory` (`ID`);

--
-- Ograniczenia dla tabeli `expenses_real`
--
ALTER TABLE `expenses_real`
  ADD CONSTRAINT `expenses_real_ibfk_1` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `expenses_subcategory` (`ID`),
  ADD CONSTRAINT `expenses_real_ibfk_2` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`);

--
-- Ograniczenia dla tabeli `expenses_subcategory`
--
ALTER TABLE `expenses_subcategory`
  ADD CONSTRAINT `expenses_subcategory_ibfk_1` FOREIGN KEY (`SUBCATEGORY_ID`) REFERENCES `expenses_category` (`ID`);

--
-- Ograniczenia dla tabeli `revenues_plan`
--
ALTER TABLE `revenues_plan`
  ADD CONSTRAINT `revenues_plan_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`),
  ADD CONSTRAINT `revenues_plan_ibfk_2` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `revenues_category` (`CATEGORY_ID`);

--
-- Ograniczenia dla tabeli `revenues_real`
--
ALTER TABLE `revenues_real`
  ADD CONSTRAINT `revenues_real_ibfk_1` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `revenues_category` (`CATEGORY_ID`),
  ADD CONSTRAINT `revenues_real_ibfk_2` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
