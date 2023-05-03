-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 03, 2023 at 05:24 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project53`
--

-- --------------------------------------------------------

--
-- Table structure for table `aspnetroleclaims`
--

CREATE TABLE `aspnetroleclaims` (
  `Id` int(11) NOT NULL,
  `RoleId` varchar(255) NOT NULL,
  `ClaimType` longtext DEFAULT NULL,
  `ClaimValue` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `aspnetroles`
--

CREATE TABLE `aspnetroles` (
  `Id` varchar(255) NOT NULL,
  `Name` varchar(256) DEFAULT NULL,
  `NormalizedName` varchar(256) DEFAULT NULL,
  `ConcurrencyStamp` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `aspnetuserclaims`
--

CREATE TABLE `aspnetuserclaims` (
  `Id` int(11) NOT NULL,
  `UserId` varchar(255) NOT NULL,
  `ClaimType` longtext DEFAULT NULL,
  `ClaimValue` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `aspnetuserlogins`
--

CREATE TABLE `aspnetuserlogins` (
  `LoginProvider` varchar(128) NOT NULL,
  `ProviderKey` varchar(128) NOT NULL,
  `ProviderDisplayName` longtext DEFAULT NULL,
  `UserId` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `aspnetuserroles`
--

CREATE TABLE `aspnetuserroles` (
  `UserId` varchar(255) NOT NULL,
  `RoleId` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `aspnetusers`
--

CREATE TABLE `aspnetusers` (
  `Id` varchar(255) NOT NULL,
  `UserName` varchar(256) DEFAULT NULL,
  `NormalizedUserName` varchar(256) DEFAULT NULL,
  `Email` varchar(256) DEFAULT NULL,
  `NormalizedEmail` varchar(256) DEFAULT NULL,
  `EmailConfirmed` tinyint(1) NOT NULL,
  `PasswordHash` longtext DEFAULT NULL,
  `SecurityStamp` longtext DEFAULT NULL,
  `ConcurrencyStamp` longtext DEFAULT NULL,
  `PhoneNumber` longtext DEFAULT NULL,
  `PhoneNumberConfirmed` tinyint(1) NOT NULL,
  `TwoFactorEnabled` tinyint(1) NOT NULL,
  `LockoutEnd` datetime(6) DEFAULT NULL,
  `LockoutEnabled` tinyint(1) NOT NULL,
  `AccessFailedCount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `aspnetusers`
--

INSERT INTO `aspnetusers` (`Id`, `UserName`, `NormalizedUserName`, `Email`, `NormalizedEmail`, `EmailConfirmed`, `PasswordHash`, `SecurityStamp`, `ConcurrencyStamp`, `PhoneNumber`, `PhoneNumberConfirmed`, `TwoFactorEnabled`, `LockoutEnd`, `LockoutEnabled`, `AccessFailedCount`) VALUES
('063fa442-e129-4c5b-aadc-ab63d055e4c8', 'gmail@com.name', 'GMAIL@COM.NAME', 'gmail@com.name', 'GMAIL@COM.NAME', 1, 'AQAAAAIAAYagAAAAEH541URj4l1qUWDl9hax8NsAXLM8l45B56myg/07f9luHUHjVaQLmsoWC196bl6niQ==', 'ZUZYVP5J7NPTPWSRXKN74J5OILTCA2UQ', '661c4abb-e745-45e3-a160-f3fc0ded5b31', NULL, 0, 0, NULL, 1, 0),
('62c22ee0-540f-4df3-979e-b490b5bd337c', 'deez@nuts.com', 'DEEZ@NUTS.COM', 'deez@nuts.com', 'DEEZ@NUTS.COM', 1, 'AQAAAAIAAYagAAAAEHlhAfOxExi3YphnSwoyIw/M7HWNUDtG7gvoTBwsL59nFNWVAEgAiZAAwJYq7yX4dw==', 'YI3RQLK2PNQRP2C5TVN2PYEKAEPBHC27', '8a6c166f-dcdf-4811-bfab-15e6fcee7d82', NULL, 0, 0, NULL, 1, 0),
('ee840294-c752-403d-bf8b-087d9521c63f', 'joe@mama.fat', 'JOE@MAMA.FAT', 'joe@mama.fat', 'JOE@MAMA.FAT', 1, 'AQAAAAIAAYagAAAAEB/ypRxR0ojVL8KODwX7ihwecEFUtQ9WuNl/ycNtMudTegGFIiqMt8TAfWu7JdMC+w==', '6SB43H5GV2Y4EJCEAFII76C5OD7RLPGE', 'd022101c-53e6-414c-91e8-37ca1323ae17', NULL, 0, 0, NULL, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `aspnetusertokens`
--

CREATE TABLE `aspnetusertokens` (
  `UserId` varchar(255) NOT NULL,
  `LoginProvider` varchar(128) NOT NULL,
  `Name` varchar(128) NOT NULL,
  `Value` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `the_catalog`
--

CREATE TABLE `the_catalog` (
  `year` year(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `the_catalog`
--

INSERT INTO `the_catalog` (`year`) VALUES
(2020),
(2021),
(2022);

-- --------------------------------------------------------

--
-- Table structure for table `the_catalog_courses`
--

CREATE TABLE `the_catalog_courses` (
  `cat_year` year(4) NOT NULL,
  `course_id` varchar(69) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `the_catalog_courses`
--

INSERT INTO `the_catalog_courses` (`cat_year`, `course_id`) VALUES
(2020, 'LIT-2330'),
(2021, 'BIO-1115'),
(2021, 'BTGE-1725'),
(2021, 'BTGE-2730'),
(2021, 'BTGE-2740'),
(2021, 'BTGE-3755'),
(2021, 'BTGE-3765'),
(2021, 'CHEM-1050'),
(2021, 'COM-1100'),
(2021, 'CS-1210'),
(2021, 'CS-1220'),
(2021, 'CS-2210'),
(2021, 'CS-3210'),
(2021, 'CS-3220'),
(2021, 'CS-3310'),
(2021, 'CS-3350'),
(2021, 'CS-3410'),
(2021, 'CS-3510'),
(2021, 'CS-3610'),
(2021, 'CS-4410'),
(2021, 'CS-4430'),
(2021, 'CS-4710'),
(2021, 'CS-4730'),
(2021, 'CS-4810'),
(2021, 'CS-4820'),
(2021, 'CY-1000'),
(2021, 'CY-2310'),
(2021, 'CY-3320'),
(2021, 'CY-3420'),
(2021, 'CY-4310'),
(2021, 'CY-4330'),
(2021, 'CY-4810'),
(2021, 'CY-4820'),
(2021, 'EDUC-1000'),
(2021, 'EGCP-1010'),
(2021, 'EGCP-2120'),
(2021, 'EGCP-3010'),
(2021, 'EGCP-3210'),
(2021, 'EGCP-4210'),
(2021, 'EGCP-4310'),
(2021, 'EGGN-3110'),
(2021, 'EGGN-4010'),
(2021, 'ENG-1400'),
(2021, 'GSS-1100'),
(2021, 'HIST-1110'),
(2021, 'HIST-1120'),
(2021, 'HUM-1400'),
(2021, 'LIT-2330'),
(2021, 'MATH-1710'),
(2021, 'MATH-1720'),
(2021, 'MATH-2520'),
(2021, 'MATH-3500'),
(2021, 'MATH-3610'),
(2021, 'MATH-3760'),
(2021, 'PEF-1990'),
(2021, 'PHYS-2110'),
(2021, 'PHYS-2120'),
(2022, 'BIO-1115'),
(2022, 'BTGE-1725'),
(2022, 'BTGE-2730'),
(2022, 'BTGE-2740'),
(2022, 'BTGE-3755'),
(2022, 'BTGE-3765'),
(2022, 'CHEM-1050'),
(2022, 'COM-1100'),
(2022, 'CS-1210'),
(2022, 'CS-1220'),
(2022, 'CS-2210'),
(2022, 'CS-3210'),
(2022, 'CS-3220'),
(2022, 'CS-3310'),
(2022, 'CS-3350'),
(2022, 'CS-3410'),
(2022, 'CS-3510'),
(2022, 'CS-3610'),
(2022, 'CS-4410'),
(2022, 'CS-4430'),
(2022, 'CS-4710'),
(2022, 'CS-4730'),
(2022, 'CS-4810'),
(2022, 'CS-4820'),
(2022, 'CY-1000'),
(2022, 'CY-2310'),
(2022, 'CY-3320'),
(2022, 'CY-3420'),
(2022, 'CY-4310'),
(2022, 'CY-4330'),
(2022, 'CY-4810'),
(2022, 'CY-4820'),
(2022, 'EDUC-1000'),
(2022, 'EGCP-1010'),
(2022, 'EGCP-2120'),
(2022, 'EGCP-3010'),
(2022, 'EGCP-3210'),
(2022, 'EGCP-4210'),
(2022, 'EGCP-4310'),
(2022, 'EGGN-3110'),
(2022, 'EGGN-4010'),
(2022, 'ENG-1400'),
(2022, 'GBIO-1000'),
(2022, 'GMTH-1020'),
(2022, 'GSS-1100'),
(2022, 'HIST-1110'),
(2022, 'HIST-1120'),
(2022, 'HUM-1400'),
(2022, 'ITM-3450'),
(2022, 'LIT-2300'),
(2022, 'LIT-2330'),
(2022, 'MATH-1710'),
(2022, 'MATH-1720'),
(2022, 'MATH-2520'),
(2022, 'MATH-3500'),
(2022, 'MATH-3610'),
(2022, 'MATH-3760'),
(2022, 'PEF-1990'),
(2022, 'PHYS-2110'),
(2022, 'PHYS-2120'),
(2022, 'PYCH-1600'),
(2022, 'PYCH-2610');

-- --------------------------------------------------------

--
-- Table structure for table `the_courses`
--

CREATE TABLE `the_courses` (
  `Id` varchar(69) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `name` varchar(69) NOT NULL,
  `description` varchar(420) NOT NULL,
  `credits` decimal(2,1) NOT NULL DEFAULT 3.0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `the_courses`
--

INSERT INTO `the_courses` (`Id`, `name`, `description`, `credits`) VALUES
('BIO-1115', 'Cell Biology', 'Like being in a real cell!', '4.0'),
('BTGE-1725', 'The Bible and the Gospel', 'Truly why we are here', '3.0'),
('BTGE-2730', 'Old Testament Literature', 'In the Beginning!', '3.0'),
('BTGE-2740', 'New Testament Literature', 'In the Beginning Part 2!', '3.0'),
('BTGE-3755', 'Theology I', 'Let\'s learn about God', '3.0'),
('BTGE-3765', 'Theology II', 'Let\'s learn more about God', '3.0'),
('CHEM-1050', 'Chemistry for Engineers', 'The lab is fun!', '3.5'),
('COM-1100', 'Fundamentals of Speech', 'Speech! Speech!', '3.0'),
('CS-1210', 'C++ Programming', 'Feeble effort to teach programming', '2.0'),
('CS-1220', 'Object-Oriented Design', 'Why do we still teach C++', '3.0'),
('CS-2210', 'Data Structures Using Java', 'What an awesome prof!!!!', '3.0'),
('CS-3210', 'Programming Language Survey', 'Three cheers for Prof Dude', '3.0'),
('CS-3220', 'Web Applications', 'Who won the Medal of Honor at Gettysburg', '3.0'),
('CS-3310', 'Operating Systems', 'Forget Windows; Let\'s do Linux!', '3.0'),
('CS-3350', 'Foundations of Computer Security', 'Authentication', '3.0'),
('CS-3410', 'Algorithms', 'The heart of Computer Science', '3.0'),
('CS-3510', 'Compiler Theory & Practice', 'The BEST! Way Cool!', '3.0'),
('CS-3610', 'Database Organization & Design', 'What\'s a left join?', '3.0'),
('CS-4410', 'Parallel Computing', 'Impossible', '3.0'),
('CS-4430', 'Machine Learning for Intelligent Agents', 'AI', '3.0'),
('CS-4710', 'Computer Graphics', 'Just games', '3.0'),
('CS-4730', 'Virtual Reality Applications', 'Is it real?', '3.0'),
('CS-4810', 'Software Engineering I', 'Love Senior Design!', '3.0'),
('CS-4820', 'Software Engineering II', 'To infinity and beyond', '4.0'),
('CY-1000', 'Introduction to Cybersecurity', 'Attack!', '3.0'),
('CY-2310', 'Cyber Forensics', 'Investigate!', '3.0'),
('CY-3320', 'Linux System Programming', 'Control!', '3.0'),
('CY-3420', 'Cyber Defense', 'Defend!', '3.0'),
('CY-4310', 'Cyber Operations', 'Adversarial Mindset', '3.0'),
('CY-4330', 'Software Security', 'buffer overflow', '3.0'),
('CY-4810', 'Secure Software Engineering I', 'Love Senior Design!', '3.0'),
('CY-4820', 'Secure Software Engineering II', 'To infinity and beyond', '4.0'),
('EDUC-1000', 'The Education Profession', 'Get taught how to teach!', '2.0'),
('EGCP-1010', 'Digital Logic Design', 'Cool course with AND, OR, and NOT', '3.0'),
('EGCP-2120', 'Microcontrollers', 'They are really tiny', '3.0'),
('EGCP-3010', 'Advanced Digital Logic Design', 'I AM ROBOT', '3.0'),
('EGCP-3210', 'Computer Architecture', 'Build the Pipeline!', '3.0'),
('EGCP-4210', 'Advanced Computer Architecture', 'We love Tomasulo', '3.0'),
('EGCP-4310', 'Computer Networks', 'Networking is very importing for finding a job', '3.0'),
('EGGN-3110', 'Professional Ethics', 'Politicians need to take this course!', '3.0'),
('EGGN-4010', 'Senior Seminar', 'Wrong Major!', '0.0'),
('ENG-1400', 'Composition', 'Procrastinate!', '3.0'),
('GBIO-1000', 'Principles of Biology', 'Basic principles of you!', '3.5'),
('GMTH-1020', 'College Algebra', 'Welcome to the real world!', '3.0'),
('GMTH-1030', 'Precalculus', 'Before Calculus', '4.0'),
('GSS-1100', 'Politics and American Culture', 'Least controversial class', '3.0'),
('HIST-1110', 'US History I', 'Don\'t we love America\'s History?', '3.0'),
('HIST-1120', 'US History II', 'We were never the bad guys!', '3.0'),
('HUM-1400', 'Intro to Humanities', 'Art, but worse', '3.0'),
('ITM-3450', 'IT Security and Risk Management', 'Secure IT!', '3.0'),
('LIT-2300', 'Intro to Literature', 'Finally, we learn how to read!', '3.0'),
('LIT-2330', 'World Mythology', '\"Easy B\" -Dr. Gallagher', '3.0'),
('MATH-1710', 'Calculus I', 'A weedout course', '5.0'),
('MATH-1720', 'Calculus II', 'For the few who passed Calc I', '5.0'),
('MATH-2520', 'Discrete Math w/ Prob', 'We should always be discrete', '3.0'),
('MATH-2710', 'Calculus III', 'For the few who passed Calc II', '5.0'),
('MATH-2740', 'Differential Equations', 'Diversity!', '3.0'),
('MATH-3500', 'Number Theory', 'Why?', '3.0'),
('MATH-3610', 'Linear Algebra', 'As opposed to non-linear algegra?', '3.0'),
('MATH-3760', 'Numerical Analysis', 'Painful!', '3.0'),
('PEF-1990', 'Physical Activity and Healthy Living', 'Touch Grass', '2.0'),
('PHYS-2110', 'General Physics I', 'Distance, Velocity, Acceleration', '4.0'),
('PHYS-2120', 'General Physics II', 'Why do we take this again?', '4.0'),
('POLS-2000', 'Intro to Political Science', 'Most objective version of science', '3.0'),
('POLS-2620', 'American State and Local Government', 'How America works!', '3.0'),
('PYCH-1600', 'General Psychology', 'Learn about Psychos!', '3.0'),
('PYCH-2610', 'Statistics', 'Stat\'s cool!', '3.0');

-- --------------------------------------------------------

--
-- Table structure for table `the_gened_reqs`
--

CREATE TABLE `the_gened_reqs` (
  `cat_year` year(4) NOT NULL,
  `course_id` varchar(69) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `the_gened_reqs`
--

INSERT INTO `the_gened_reqs` (`cat_year`, `course_id`) VALUES
(2022, 'COM-1100'),
(2022, 'ENG-1400'),
(2022, 'GSS-1100'),
(2022, 'HIST-1110'),
(2022, 'HUM-1400'),
(2022, 'LIT-2330'),
(2022, 'PEF-1990');

-- --------------------------------------------------------

--
-- Table structure for table `the_majors`
--

CREATE TABLE `the_majors` (
  `name` varchar(69) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `the_majors`
--

INSERT INTO `the_majors` (`name`) VALUES
('Computer Science'),
('Cyber Operations');

-- --------------------------------------------------------

--
-- Table structure for table `the_major_reqs`
--

CREATE TABLE `the_major_reqs` (
  `major_name` varchar(69) NOT NULL,
  `cat_year` year(4) NOT NULL,
  `course_id` varchar(69) NOT NULL,
  `course_type` varchar(69) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `the_major_reqs`
--

INSERT INTO `the_major_reqs` (`major_name`, `cat_year`, `course_id`, `course_type`) VALUES
('Computer Science', 2022, 'CS-1210', 'Core'),
('Computer Science', 2022, 'CS-1220', 'Core'),
('Computer Science', 2022, 'CS-2210', 'Core'),
('Computer Science', 2022, 'CS-3210', 'Core'),
('Computer Science', 2022, 'CS-3220', 'Core'),
('Computer Science', 2022, 'CS-3310', 'Core'),
('Computer Science', 2022, 'CS-3410', 'Core'),
('Computer Science', 2022, 'CS-3510', 'Core'),
('Computer Science', 2022, 'CS-3610', 'Core'),
('Computer Science', 2022, 'CS-4810', 'Core'),
('Computer Science', 2022, 'CS-4820', 'Core'),
('Computer Science', 2022, 'CY-1000', 'Core'),
('Computer Science', 2022, 'CY-3320', 'Elective'),
('Computer Science', 2022, 'CY-3420', 'Core'),
('Computer Science', 2022, 'CY-4310', 'Elective'),
('Computer Science', 2022, 'CY-4330', 'Elective'),
('Computer Science', 2022, 'EGCP-1010', 'Core'),
('Computer Science', 2022, 'EGCP-2120', 'Elective'),
('Computer Science', 2022, 'EGCP-3010', 'Elective'),
('Computer Science', 2022, 'EGCP-3210', 'Core'),
('Computer Science', 2022, 'EGCP-4210', 'Elective'),
('Computer Science', 2022, 'EGCP-4310', 'Core'),
('Computer Science', 2022, 'EGGN-3110', 'Core'),
('Computer Science', 2022, 'EGGN-4010', 'Core'),
('Computer Science', 2022, 'MATH-1710', 'Cognate'),
('Computer Science', 2022, 'MATH-1720', 'Cognate'),
('Computer Science', 2022, 'MATH-2520', 'Core'),
('Computer Science', 2022, 'MATH-3500', 'Elective'),
('Computer Science', 2022, 'MATH-3760', 'Elective'),
('Computer Science', 2022, 'PHYS-2110', 'Cognate'),
('Computer Science', 2022, 'PHYS-2120', 'Cognate'),
('Cyber Operations', 2022, 'CS-1210', 'Core'),
('Cyber Operations', 2022, 'CS-1220', 'Core'),
('Cyber Operations', 2022, 'CS-2210', 'Core'),
('Cyber Operations', 2022, 'CS-3220', 'Elective'),
('Cyber Operations', 2022, 'CS-3310', 'Core'),
('Cyber Operations', 2022, 'CS-3410', 'Core'),
('Cyber Operations', 2022, 'CS-3510', 'Elective'),
('Cyber Operations', 2022, 'CS-3610', 'Elective'),
('Cyber Operations', 2022, 'CY-1000', 'Core'),
('Cyber Operations', 2022, 'CY-2310', 'Core'),
('Cyber Operations', 2022, 'CY-3320', 'Core'),
('Cyber Operations', 2022, 'CY-3420', 'Core'),
('Cyber Operations', 2022, 'CY-4310', 'Core'),
('Cyber Operations', 2022, 'CY-4330', 'Core'),
('Cyber Operations', 2022, 'CY-4810', 'Core'),
('Cyber Operations', 2022, 'CY-4820', 'Core'),
('Cyber Operations', 2022, 'EGCP-1010', 'Core'),
('Cyber Operations', 2022, 'EGCP-2120', 'Elective'),
('Cyber Operations', 2022, 'EGCP-3010', 'Elective'),
('Cyber Operations', 2022, 'EGCP-3210', 'Core'),
('Cyber Operations', 2022, 'EGCP-4210', 'Elective'),
('Cyber Operations', 2022, 'EGCP-4310', 'Core'),
('Cyber Operations', 2022, 'EGGN-3110', 'Core'),
('Cyber Operations', 2022, 'EGGN-4010', 'Core'),
('Cyber Operations', 2022, 'ITM-3450', 'Core'),
('Cyber Operations', 2022, 'MATH-1710', 'Cognate'),
('Cyber Operations', 2022, 'MATH-1720', 'Cognate'),
('Cyber Operations', 2022, 'MATH-2520', 'Cognate'),
('Cyber Operations', 2022, 'PHYS-2110', 'Cognate');

-- --------------------------------------------------------

--
-- Table structure for table `the_minors`
--

CREATE TABLE `the_minors` (
  `name` varchar(69) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `the_minors`
--

INSERT INTO `the_minors` (`name`) VALUES
('Bible'),
('Math');

-- --------------------------------------------------------

--
-- Table structure for table `the_minor_reqs`
--

CREATE TABLE `the_minor_reqs` (
  `minor_name` varchar(69) NOT NULL,
  `cat_year` year(4) NOT NULL,
  `course_id` varchar(69) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `the_minor_reqs`
--

INSERT INTO `the_minor_reqs` (`minor_name`, `cat_year`, `course_id`) VALUES
('Bible', 2022, 'BTGE-1725'),
('Bible', 2022, 'BTGE-2730'),
('Bible', 2022, 'BTGE-2740'),
('Bible', 2022, 'BTGE-3755'),
('Bible', 2022, 'BTGE-3765'),
('Math', 2022, 'MATH-1710'),
('Math', 2022, 'MATH-1720'),
('Math', 2022, 'MATH-2710'),
('Math', 2022, 'MATH-2740'),
('Math', 2022, 'MATH-3610');

-- --------------------------------------------------------

--
-- Table structure for table `the_plan`
--

CREATE TABLE `the_plan` (
  `id` varchar(69) NOT NULL,
  `user` varchar(69) NOT NULL,
  `name` varchar(69) NOT NULL,
  `is_default` tinyint(1) NOT NULL,
  `major` varchar(69) NOT NULL,
  `dmajor` varchar(69) DEFAULT NULL,
  `minor` varchar(69) NOT NULL,
  `dminor` varchar(69) DEFAULT NULL,
  `cat_year` year(4) NOT NULL,
  `notes` text NOT NULL DEFAULT 'Please type notes here:'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `the_plan`
--

INSERT INTO `the_plan` (`id`, `user`, `name`, `is_default`, `major`, `dmajor`, `minor`, `dminor`, `cat_year`, `notes`) VALUES
('1234', 'josh', 'Cyber', 1, 'Cyber Operations', NULL, 'Bible', NULL, 2022, 'Please type notes here:'),
('420', 'chris', 'CS Plan', 1, 'Computer Science', NULL, 'Bible', NULL, 2021, 'Hi mom'),
('4312', 'josh', 'CY/CS', 0, 'Computer Science', 'Cyber Operations', 'Bible', NULL, 2022, 'Please type notes here:'),
('6543', 'joe', 'why are we still here?', 1, 'Computer Science', NULL, 'Bible', NULL, 2020, 'Please type notes here:'),
('69', 'chris', 'CS + Math', 0, 'Computer Science', NULL, 'Bible', 'Math', 2021, 'Please type notes here:');

-- --------------------------------------------------------

--
-- Table structure for table `the_planned_courses`
--

CREATE TABLE `the_planned_courses` (
  `plan_id` varchar(69) NOT NULL,
  `course_id` varchar(69) NOT NULL,
  `term` varchar(69) NOT NULL,
  `year` year(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `the_planned_courses`
--

INSERT INTO `the_planned_courses` (`plan_id`, `course_id`, `term`, `year`) VALUES
('1234', 'BTGE-1725', 'Fall', 2022),
('1234', 'BTGE-2730', 'Spring', 2023),
('1234', 'BTGE-2740', 'Spring', 2024),
('1234', 'BTGE-3755', 'Fall', 2024),
('1234', 'BTGE-3765', 'Spring', 2025),
('1234', 'COM-1100', 'Transfer', 2022),
('1234', 'CS-1210', 'Transfer', 2022),
('1234', 'CS-1220', 'Spring', 2023),
('1234', 'CS-2210', 'Transfer', 2022),
('1234', 'CS-3220', 'Spring', 2023),
('1234', 'CS-3310', 'Spring', 2024),
('1234', 'CS-3410', 'Fall', 2024),
('1234', 'CS-3510', 'Spring', 2025),
('1234', 'CY-1000', 'Spring', 2023),
('1234', 'CY-2310', 'Fall', 2023),
('1234', 'CY-3320', 'Fall', 2023),
('1234', 'CY-3420', 'Fall', 2023),
('1234', 'CY-4310', 'Spring', 2024),
('1234', 'CY-4330', 'Spring', 2025),
('1234', 'CY-4810', 'Fall', 2024),
('1234', 'CY-4820', 'Spring', 2025),
('1234', 'EGCP-1010', 'Fall', 2022),
('1234', 'EGCP-2120', 'Fall', 2024),
('1234', 'EGCP-3210', 'Spring', 2024),
('1234', 'EGCP-4310', 'Fall', 2024),
('1234', 'EGGN-3110', 'Spring', 2024),
('1234', 'EGGN-4010', 'Fall', 2024),
('1234', 'ENG-1400', 'Transfer', 2022),
('1234', 'GBIO-1000', 'Summer', 2023),
('1234', 'GMTH-1020', 'Transfer', 2022),
('1234', 'GMTH-1030', 'Transfer', 2022),
('1234', 'GSS-1100', 'Summer', 2023),
('1234', 'HIST-1110', 'Transfer', 2022),
('1234', 'HUM-1400', 'Fall', 2022),
('1234', 'ITM-3450', 'Spring', 2025),
('1234', 'LIT-2300', 'Fall', 2022),
('1234', 'LIT-2300', 'Fall', 2023),
('1234', 'MATH-1710', 'Fall', 2022),
('1234', 'MATH-1720', 'Spring', 2023),
('1234', 'MATH-2520', 'Spring', 2024),
('1234', 'PEF-1990', 'Fall', 2022),
('1234', 'PHYS-2110', 'Fall', 2023),
('1234', 'PYCH-1600', 'Transfer', 2022),
('1234', 'PYCH-2610', 'Transfer', 2022),
('420', 'BIO-1115', 'Fall', 2022),
('420', 'BTGE-1725', 'Fall', 2021),
('420', 'BTGE-2730', 'Fall', 2022),
('420', 'BTGE-2740', 'Spring', 2023),
('420', 'BTGE-3755', 'Summer', 2023),
('420', 'BTGE-3765', 'Fall', 2023),
('420', 'COM-1100', 'Fall', 2023),
('420', 'CS-1210', 'Fall', 2021),
('420', 'CS-1220', 'Spring', 2022),
('420', 'CS-2210', 'Fall', 2022),
('420', 'CS-3210', 'Fall', 2022),
('420', 'CS-3220', 'Spring', 2023),
('420', 'CS-3310', 'Spring', 2023),
('420', 'CS-3410', 'Fall', 2023),
('420', 'CS-3510', 'Spring', 2024),
('420', 'CS-3610', 'Spring', 2023),
('420', 'CS-4810', 'Fall', 2023),
('420', 'CS-4820', 'Spring', 2024),
('420', 'CY-1000', 'Spring', 2022),
('420', 'CY-3320', 'Fall', 2023),
('420', 'CY-3420', 'Fall', 2022),
('420', 'CY-4310', 'Spring', 2024),
('420', 'CY-4330', 'Spring', 2024),
('420', 'EDUC-1000', 'Spring', 2024),
('420', 'EGCP-1010', 'Fall', 2021),
('420', 'EGCP-3210', 'Spring', 2023),
('420', 'EGCP-4310', 'Fall', 2023),
('420', 'EGGN-3110', 'Spring', 2024),
('420', 'EGGN-4010', 'Fall', 2023),
('420', 'ENG-1400', 'Fall', 2021),
('420', 'GMTH-1030', 'Transfer', 2021),
('420', 'GSS-1100', 'Summer', 2023),
('420', 'HIST-1120', 'Summer', 2023),
('420', 'HUM-1400', 'Summer', 2022),
('420', 'LIT-2330', 'Summer', 2023),
('420', 'MATH-1710', 'Fall', 2021),
('420', 'MATH-1720', 'Spring', 2022),
('420', 'MATH-2520', 'Spring', 2023),
('420', 'PEF-1990', 'Spring', 2022),
('420', 'PHYS-2110', 'Spring', 2022),
('420', 'POLS-2000', 'Transfer', 2021),
('420', 'POLS-2620', 'Transfer', 2021),
('4312', 'BTGE-1725', 'Fall', 2022),
('4312', 'BTGE-2730', 'Spring', 2023),
('4312', 'BTGE-2740', 'Spring', 2024),
('4312', 'BTGE-3755', 'Fall', 2024),
('4312', 'BTGE-3765', 'Spring', 2025),
('4312', 'COM-1100', 'Transfer', 2022),
('4312', 'CS-1210', 'Transfer', 2022),
('4312', 'CS-1220', 'Spring', 2023),
('4312', 'CS-2210', 'Transfer', 2022),
('4312', 'CS-3210', 'Fall', 2023),
('4312', 'CS-3220', 'Spring', 2023),
('4312', 'CS-3310', 'Fall', 2023),
('4312', 'CS-3410', 'Fall', 2024),
('4312', 'CS-3510', 'Spring', 2026),
('4312', 'CS-3610', 'Spring', 2025),
('4312', 'CY-1000', 'Spring', 2023),
('4312', 'CY-2310', 'Fall', 2025),
('4312', 'CY-3320', 'Fall', 2024),
('4312', 'CY-3420', 'Fall', 2023),
('4312', 'CY-3420', 'Fall', 2024),
('4312', 'CY-4310', 'Spring', 2026),
('4312', 'CY-4330', 'Spring', 2025),
('4312', 'CY-4810', 'Fall', 2025),
('4312', 'CY-4820', 'Spring', 2026),
('4312', 'EGCP-1010', 'Fall', 2022),
('4312', 'EGCP-2120', 'Fall', 2025),
('4312', 'EGCP-3210', 'Spring', 2024),
('4312', 'EGGN-3110', 'Spring', 2026),
('4312', 'EGGN-4010', 'Fall', 2025),
('4312', 'ENG-1400', 'Transfer', 2022),
('4312', 'GBIO-1000', 'Spring', 2026),
('4312', 'GMTH-1020', 'Transfer', 2022),
('4312', 'GMTH-1030', 'Transfer', 2022),
('4312', 'GSS-1100', 'Fall', 2025),
('4312', 'HIST-1110', 'Transfer', 2022),
('4312', 'HUM-1400', 'Fall', 2022),
('4312', 'ITM-3450', 'Spring', 2025),
('4312', 'LIT-2300', 'Fall', 2022),
('4312', 'MATH-1710', 'Transfer', 2022),
('4312', 'MATH-1720', 'Spring', 2023),
('4312', 'MATH-2520', 'Spring', 2024),
('4312', 'PEF-1990', 'Fall', 2022),
('4312', 'PHYS-2110', 'Fall', 2023),
('4312', 'PHYS-2120', 'Spring', 2024),
('4312', 'PYCH-1600', 'Transfer', 2022),
('4312', 'PYCH-2610', 'Transfer', 2022),
('6543', 'CS-3220', 'Fall', 2020),
('6543', 'CS-3220', 'Fall', 2021),
('6543', 'CS-3220', 'Fall', 2022),
('6543', 'CS-3220', 'Fall', 2023),
('6543', 'CS-3220', 'Fall', 2024),
('6543', 'CS-3220', 'Fall', 2025),
('6543', 'CS-3220', 'Fall', 2026),
('6543', 'CS-3220', 'Spring', 2021),
('6543', 'CS-3220', 'Spring', 2022),
('6543', 'CS-3220', 'Spring', 2023),
('6543', 'CS-3220', 'Spring', 2024),
('6543', 'CS-3220', 'Spring', 2025),
('6543', 'CS-3220', 'Spring', 2026),
('6543', 'CS-3220', 'Spring', 2027),
('6543', 'CS-3220', 'Summer', 2021),
('6543', 'CS-3220', 'Summer', 2022),
('6543', 'CS-3220', 'Summer', 2023),
('6543', 'CS-3220', 'Summer', 2024),
('6543', 'CS-3220', 'Summer', 2025),
('6543', 'CS-3220', 'Summer', 2026),
('6543', 'CS-3220', 'Transfer', 2020),
('69', 'BIO-1115', 'Fall', 2022),
('69', 'BTGE-1725', 'Fall', 2021),
('69', 'BTGE-2730', 'Fall', 2022),
('69', 'BTGE-2740', 'Spring', 2023),
('69', 'BTGE-3755', 'Fall', 2023),
('69', 'BTGE-3765', 'Spring', 2024),
('69', 'COM-1100', 'Fall', 2024),
('69', 'CS-1210', 'Fall', 2021),
('69', 'CS-1220', 'Spring', 2022),
('69', 'CS-2210', 'Fall', 2022),
('69', 'CS-3210', 'Fall', 2022),
('69', 'CS-3220', 'Spring', 2023),
('69', 'CS-3310', 'Spring', 2023),
('69', 'CS-3410', 'Fall', 2023),
('69', 'CS-3510', 'Spring', 2025),
('69', 'CS-3610', 'Spring', 2023),
('69', 'CS-4810', 'Fall', 2024),
('69', 'CY-1000', 'Spring', 2022),
('69', 'CY-3320', 'Fall', 2024),
('69', 'CY-3420', 'Fall', 2022),
('69', 'CY-4310', 'Spring', 2025),
('69', 'CY-4330', 'Spring', 2024),
('69', 'CY-4820', 'Spring', 2025),
('69', 'EGCP-1010', 'Fall', 2021),
('69', 'EGCP-3210', 'Spring', 2023),
('69', 'EGCP-4310', 'Fall', 2023),
('69', 'EGGN-3110', 'Spring', 2025),
('69', 'EGGN-4010', 'Fall', 2024),
('69', 'ENG-1400', 'Fall', 2021),
('69', 'GMTH-1030', 'Transfer', 2021),
('69', 'GSS-1100', 'Summer', 2024),
('69', 'HIST-1110', 'Fall', 2024),
('69', 'HUM-1400', 'Summer', 2022),
('69', 'LIT-2330', 'Summer', 2023),
('69', 'MATH-1710', 'Fall', 2021),
('69', 'MATH-1720', 'Spring', 2022),
('69', 'MATH-2520', 'Spring', 2023),
('69', 'MATH-2710', 'Fall', 2023),
('69', 'MATH-2740', 'Spring', 2024),
('69', 'MATH-3610', 'Spring', 2024),
('69', 'PEF-1990', 'Spring', 2022),
('69', 'PHYS-2110', 'Spring', 2022),
('69', 'POLS-2000', 'Transfer', 2021),
('69', 'POLS-2620', 'Transfer', 2021);

-- --------------------------------------------------------

--
-- Table structure for table `the_user`
--

CREATE TABLE `the_user` (
  `name` varchar(69) NOT NULL,
  `password` varchar(69) NOT NULL,
  `type` varchar(7) NOT NULL DEFAULT 'Student',
  `asp_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `the_user`
--

INSERT INTO `the_user` (`name`, `password`, `type`, `asp_id`) VALUES
('chris', 'lafafafa', 'Student', '62c22ee0-540f-4df3-979e-b490b5bd337c'),
('joe', 'mama', 'Student', 'ee840294-c752-403d-bf8b-087d9521c63f'),
('josh', '1234', 'Faculty', '063fa442-e129-4c5b-aadc-ab63d055e4c8');

-- --------------------------------------------------------

--
-- Table structure for table `__efmigrationshistory`
--

CREATE TABLE `__efmigrationshistory` (
  `MigrationId` varchar(150) NOT NULL,
  `ProductVersion` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `__efmigrationshistory`
--

INSERT INTO `__efmigrationshistory` (`MigrationId`, `ProductVersion`) VALUES
('20230414005401_Inital', '7.0.5');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `aspnetroleclaims`
--
ALTER TABLE `aspnetroleclaims`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `IX_AspNetRoleClaims_RoleId` (`RoleId`);

--
-- Indexes for table `aspnetroles`
--
ALTER TABLE `aspnetroles`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `RoleNameIndex` (`NormalizedName`);

--
-- Indexes for table `aspnetuserclaims`
--
ALTER TABLE `aspnetuserclaims`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `IX_AspNetUserClaims_UserId` (`UserId`);

--
-- Indexes for table `aspnetuserlogins`
--
ALTER TABLE `aspnetuserlogins`
  ADD PRIMARY KEY (`LoginProvider`,`ProviderKey`),
  ADD KEY `IX_AspNetUserLogins_UserId` (`UserId`);

--
-- Indexes for table `aspnetuserroles`
--
ALTER TABLE `aspnetuserroles`
  ADD PRIMARY KEY (`UserId`,`RoleId`),
  ADD KEY `IX_AspNetUserRoles_RoleId` (`RoleId`);

--
-- Indexes for table `aspnetusers`
--
ALTER TABLE `aspnetusers`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `UserNameIndex` (`NormalizedUserName`),
  ADD KEY `EmailIndex` (`NormalizedEmail`);

--
-- Indexes for table `aspnetusertokens`
--
ALTER TABLE `aspnetusertokens`
  ADD PRIMARY KEY (`UserId`,`LoginProvider`,`Name`);

--
-- Indexes for table `the_catalog`
--
ALTER TABLE `the_catalog`
  ADD PRIMARY KEY (`year`);

--
-- Indexes for table `the_catalog_courses`
--
ALTER TABLE `the_catalog_courses`
  ADD PRIMARY KEY (`cat_year`,`course_id`),
  ADD KEY `cat_year` (`cat_year`),
  ADD KEY `course_id` (`course_id`);

--
-- Indexes for table `the_courses`
--
ALTER TABLE `the_courses`
  ADD PRIMARY KEY (`Id`,`name`);

--
-- Indexes for table `the_gened_reqs`
--
ALTER TABLE `the_gened_reqs`
  ADD PRIMARY KEY (`cat_year`,`course_id`),
  ADD KEY `course_id` (`course_id`);

--
-- Indexes for table `the_majors`
--
ALTER TABLE `the_majors`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `the_major_reqs`
--
ALTER TABLE `the_major_reqs`
  ADD PRIMARY KEY (`major_name`,`cat_year`,`course_id`),
  ADD KEY `cat_year` (`cat_year`),
  ADD KEY `course_id` (`course_id`),
  ADD KEY `major_name` (`major_name`) USING BTREE;

--
-- Indexes for table `the_minors`
--
ALTER TABLE `the_minors`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `the_minor_reqs`
--
ALTER TABLE `the_minor_reqs`
  ADD PRIMARY KEY (`minor_name`,`cat_year`,`course_id`),
  ADD KEY `cat_year` (`cat_year`),
  ADD KEY `course_id` (`course_id`),
  ADD KEY `minor_name` (`minor_name`) USING BTREE;

--
-- Indexes for table `the_plan`
--
ALTER TABLE `the_plan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user` (`user`),
  ADD KEY `cat_year` (`cat_year`),
  ADD KEY `!!!_plan_ibfk_2` (`major`),
  ADD KEY `!!!_plan_ibfk_3` (`minor`),
  ADD KEY `!!!_plan_ibfk_4` (`dmajor`),
  ADD KEY `!!!_plan_ibfk_5` (`dminor`);

--
-- Indexes for table `the_planned_courses`
--
ALTER TABLE `the_planned_courses`
  ADD PRIMARY KEY (`plan_id`,`course_id`,`term`,`year`),
  ADD KEY `course_id` (`course_id`);

--
-- Indexes for table `the_user`
--
ALTER TABLE `the_user`
  ADD PRIMARY KEY (`name`),
  ADD UNIQUE KEY `asp_id` (`asp_id`);

--
-- Indexes for table `__efmigrationshistory`
--
ALTER TABLE `__efmigrationshistory`
  ADD PRIMARY KEY (`MigrationId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `aspnetroleclaims`
--
ALTER TABLE `aspnetroleclaims`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `aspnetuserclaims`
--
ALTER TABLE `aspnetuserclaims`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `aspnetroleclaims`
--
ALTER TABLE `aspnetroleclaims`
  ADD CONSTRAINT `FK_AspNetRoleClaims_AspNetRoles_RoleId` FOREIGN KEY (`RoleId`) REFERENCES `aspnetroles` (`Id`) ON DELETE CASCADE;

--
-- Constraints for table `aspnetuserclaims`
--
ALTER TABLE `aspnetuserclaims`
  ADD CONSTRAINT `FK_AspNetUserClaims_AspNetUsers_UserId` FOREIGN KEY (`UserId`) REFERENCES `aspnetusers` (`Id`) ON DELETE CASCADE;

--
-- Constraints for table `aspnetuserlogins`
--
ALTER TABLE `aspnetuserlogins`
  ADD CONSTRAINT `FK_AspNetUserLogins_AspNetUsers_UserId` FOREIGN KEY (`UserId`) REFERENCES `aspnetusers` (`Id`) ON DELETE CASCADE;

--
-- Constraints for table `aspnetuserroles`
--
ALTER TABLE `aspnetuserroles`
  ADD CONSTRAINT `FK_AspNetUserRoles_AspNetRoles_RoleId` FOREIGN KEY (`RoleId`) REFERENCES `aspnetroles` (`Id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_AspNetUserRoles_AspNetUsers_UserId` FOREIGN KEY (`UserId`) REFERENCES `aspnetusers` (`Id`) ON DELETE CASCADE;

--
-- Constraints for table `aspnetusertokens`
--
ALTER TABLE `aspnetusertokens`
  ADD CONSTRAINT `FK_AspNetUserTokens_AspNetUsers_UserId` FOREIGN KEY (`UserId`) REFERENCES `aspnetusers` (`Id`) ON DELETE CASCADE;

--
-- Constraints for table `the_catalog_courses`
--
ALTER TABLE `the_catalog_courses`
  ADD CONSTRAINT `the_catalog_courses_ibfk_1` FOREIGN KEY (`cat_year`) REFERENCES `the_catalog` (`year`),
  ADD CONSTRAINT `the_catalog_courses_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `the_courses` (`id`);

--
-- Constraints for table `the_gened_reqs`
--
ALTER TABLE `the_gened_reqs`
  ADD CONSTRAINT `the_gened_reqs_ibfk_1` FOREIGN KEY (`cat_year`) REFERENCES `the_catalog` (`year`),
  ADD CONSTRAINT `the_gened_reqs_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `the_courses` (`id`);

--
-- Constraints for table `the_major_reqs`
--
ALTER TABLE `the_major_reqs`
  ADD CONSTRAINT `the_major_reqs_ibfk_1` FOREIGN KEY (`cat_year`) REFERENCES `the_catalog` (`year`),
  ADD CONSTRAINT `the_major_reqs_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `the_courses` (`id`),
  ADD CONSTRAINT `the_major_reqs_ibfk_3` FOREIGN KEY (`major_name`) REFERENCES `the_majors` (`name`);

--
-- Constraints for table `the_minor_reqs`
--
ALTER TABLE `the_minor_reqs`
  ADD CONSTRAINT `the_minor_reqs_ibfk_1` FOREIGN KEY (`cat_year`) REFERENCES `the_catalog` (`year`),
  ADD CONSTRAINT `the_minor_reqs_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `the_courses` (`id`),
  ADD CONSTRAINT `the_minor_reqs_ibfk_3` FOREIGN KEY (`minor_name`) REFERENCES `the_minors` (`name`);

--
-- Constraints for table `the_plan`
--
ALTER TABLE `the_plan`
  ADD CONSTRAINT `the_plan_ibfk_1` FOREIGN KEY (`user`) REFERENCES `the_user` (`name`),
  ADD CONSTRAINT `the_plan_ibfk_2` FOREIGN KEY (`major`) REFERENCES `the_majors` (`name`),
  ADD CONSTRAINT `the_plan_ibfk_3` FOREIGN KEY (`minor`) REFERENCES `the_minors` (`name`),
  ADD CONSTRAINT `the_plan_ibfk_4` FOREIGN KEY (`dmajor`) REFERENCES `the_majors` (`name`),
  ADD CONSTRAINT `the_plan_ibfk_5` FOREIGN KEY (`dminor`) REFERENCES `the_minors` (`name`),
  ADD CONSTRAINT `the_plan_ibfk_6` FOREIGN KEY (`cat_year`) REFERENCES `the_catalog` (`year`);

--
-- Constraints for table `the_planned_courses`
--
ALTER TABLE `the_planned_courses`
  ADD CONSTRAINT `the_planned_courses_ibfk_1` FOREIGN KEY (`plan_id`) REFERENCES `the_plan` (`id`),
  ADD CONSTRAINT `the_planned_courses_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `the_courses` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
