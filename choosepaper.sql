-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2017-10-17 15:01:50
-- 服务器版本： 10.1.8-MariaDB
-- PHP Version: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `choosepaper`
--

-- --------------------------------------------------------

--
-- 表的结构 `admin`
--

CREATE TABLE `admin` (
  `id` varchar(40) NOT NULL,
  `name` varchar(15) NOT NULL,
  `gender` tinyint(1) NOT NULL COMMENT '0：女，1：男',
  `tel` varchar(15) NOT NULL,
  `email` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `admin`
--

INSERT INTO `admin` (`id`, `name`, `gender`, `tel`, `email`) VALUES
('13240206', '开健', 1, '15555555555', '1967379019@qq.com');

-- --------------------------------------------------------

--
-- 表的结构 `comment`
--

CREATE TABLE `comment` (
  `id` varchar(40) NOT NULL,
  `context` varchar(200) NOT NULL,
  `create_time` datetime NOT NULL,
  `speak_name` varchar(20) NOT NULL,
  `topic_id` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `comment`
--

INSERT INTO `comment` (`id`, `context`, `create_time`, `speak_name`, `topic_id`) VALUES
('23c144f1-807a-4ab7-a45f-b277980b6665', '你说对就对', '2017-04-28 11:13:01', '刘继帅', '5fdb8853-c978-4697-b909-b929025d1a84'),
('26422a2e-10b3-40a1-9243-56177cbc63ae', '说的不错', '2017-04-13 15:01:13', '刘继帅', '5fdb8853-c978-4697-b909-b929025d1a84'),
('6fa8a5e6-b2b7-4f67-94db-c366e28c6d17', '给大忙人看的Java8', '2017-04-28 11:06:30', '刘继帅', '5fdb8853-c978-4697-b909-b929025d1a84'),
('8d0ff066-3960-449d-8608-7bdb3e1ad93a', '就是就是，说得很好', '2017-04-13 15:17:04', '刘继帅', '5fdb8853-c978-4697-b909-b929025d1a84'),
('af8a02e1-05f0-4ff1-99cd-326e6211e1e4', '说的不错', '2017-04-28 11:19:34', '刘继帅', '5fdb8853-c978-4697-b909-b929025d1a84');

-- --------------------------------------------------------

--
-- 表的结构 `paper`
--

CREATE TABLE `paper` (
  `id` varchar(40) NOT NULL,
  `name` varchar(30) NOT NULL,
  `demand` varchar(200) NOT NULL,
  `description` varchar(200) NOT NULL,
  `student_id` varchar(40) DEFAULT NULL,
  `teacher_id` varchar(40) NOT NULL,
  `create_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `paper`
--

INSERT INTO `paper` (`id`, `name`, `demand`, `description`, `student_id`, `teacher_id`, `create_time`) VALUES
('3d676853-efda-47b4-836c-0a1228854011', '计通学院门户网站', '1.一定的安全控制。\n2.bs结构。\n3.数据库使用MySQL或sqlserver', '用于展示兰州理工大学计算机与通信学院的门户网站', '13240218', '12345678', '2017-04-20 10:42:07'),
('5d18c98c-94ff-4c20-bb6c-6ae965bc5820', '1', '1111111', '1111', NULL, '12345678', '2017-06-14 10:44:46'),
('865d3fb9-0994-420e-a175-9ce1adfd8c0a', '在线学习网站', '1.bs架构\n2.mysql数据库', '设计一个在线学习的网站', NULL, '12345678', '2017-06-09 13:12:20'),
('f349373c-48bf-4ad7-8110-7a56ae6141d4', '毕业生选题系统', '1.azx\n2.qwe', '1.asd\n2.asd\n3.asd', NULL, '12345678', '2017-03-18 11:03:21');

-- --------------------------------------------------------

--
-- 表的结构 `replay`
--

CREATE TABLE `replay` (
  `id` varchar(40) NOT NULL,
  `context` varchar(200) NOT NULL,
  `create_time` datetime NOT NULL,
  `speak_name` varchar(20) NOT NULL,
  `comment_id` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `student`
--

CREATE TABLE `student` (
  `id` varchar(40) NOT NULL,
  `name` varchar(15) NOT NULL,
  `gender` tinyint(1) NOT NULL COMMENT '0：女，1：男',
  `tel` varchar(15) NOT NULL,
  `major` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `student`
--

INSERT INTO `student` (`id`, `name`, `gender`, `tel`, `major`) VALUES
('13240205', '楼畅', 1, '12233335555', '计算机科学与技术'),
('13240218', '刘继帅', 1, '13333333344', '计算机科学与技术'),
('13240220', '惠文华', 1, '12222222222', '计算机科学与技术'),
('13240232', '冯海霞', 0, '11111111111', '计算机科学与技术'),
('13240233', '李亚男', 0, '18888888888', '计算机科学与技术'),
('13240234', '钱焕娟', 0, '17777777777', '计算机科学与技术');

-- --------------------------------------------------------

--
-- 表的结构 `teacher`
--

CREATE TABLE `teacher` (
  `id` varchar(40) NOT NULL,
  `name` varchar(15) NOT NULL,
  `gender` tinyint(1) NOT NULL COMMENT '0：女，1：男',
  `tel` varchar(15) NOT NULL,
  `rank` tinyint(1) NOT NULL COMMENT '1：讲师，2：副教授，3：教授'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `teacher`
--

INSERT INTO `teacher` (`id`, `name`, `gender`, `tel`, `rank`) VALUES
('12345678', '王燕', 0, '15555555544', 3),
('12345679', '谢鹏寿', 1, '13333333333', 3),
('12345680', '张永', 1, '15555555555', 3),
('12345681', '李明', 1, '15555555555', 3),
('12345682', '曹来成', 1, '12233334444', 2),
('12345683', '庞茜之', 0, '16666668888', 3);

-- --------------------------------------------------------

--
-- 表的结构 `topic`
--

CREATE TABLE `topic` (
  `id` varchar(40) NOT NULL,
  `title` varchar(30) NOT NULL,
  `context` varchar(600) NOT NULL,
  `create_time` datetime NOT NULL,
  `owner_id` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `topic`
--

INSERT INTO `topic` (`id`, `title`, `context`, `create_time`, `owner_id`) VALUES
('032c5d1f-2f60-403e-8363-84b6028b075f', 'docker', 'docker是一个虚拟化技术，十分流行', '2017-06-14 08:29:15', '12345679'),
('210bba5d-bbfa-4584-b217-8bf2166d2a19', 'myabtis', 'mybatis是一个优秀的开源框架', '2017-06-14 08:28:49', '12345679'),
('4b85cbf6-65b3-49ba-ae05-d53cf9503fcd', 'html学习', 'HTML主要用于编写前台页面', '2017-06-14 08:05:21', '12345679'),
('6ac246eb-6adc-478c-895d-e0be616b62e1', 'maven', 'maven常用于在应用开发中管理各种依赖，十分受欢迎', '2017-06-09 13:40:45', '13240218'),
('8a0ac144-78d3-46ad-938f-e17bac2efb77', 'css', 'css全称为层叠样式表', '2017-06-14 08:29:46', '12345679'),
('a484def6-432f-41d3-a45e-0c21878a3c5b', 'bootstrap', 'bootstrap是一个css与HTML的框架', '2017-06-14 08:05:54', '12345679'),
('a77f0e37-b836-4c4b-9994-a0cc9d20e2ac', 'hibernate', 'hibernate是一个持久层框架', '2017-06-14 08:06:37', '12345679'),
('d376fa22-0824-46cc-821f-3e50441f9899', 'java学习', 'Java是一门十分重要的语言，也是目前编程语言中最受欢迎的语种之一', '2017-06-09 11:39:33', '12345678'),
('e2fc9123-e86e-4b6b-a317-59221db1f46b', 'Spring Boot', 'Spring boot 是一款轻量级的后台开发框架，主要是为了快速开发后台应用', '2017-06-09 13:37:09', '12345678');

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE `user` (
  `username` varchar(20) NOT NULL,
  `password` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`username`, `password`) VALUES
('12345678', '25D55AD283AA400AF464C76D713C07AD'),
('12345679', 'DEFAC44447B57F152D14F30CEA7A73CB'),
('12345680', '2661B5362E4EED2C62D90080B91B329E'),
('12345681', '1EE0A8CE9CD7E6971096AC9159260BF3'),
('12345682', '0D03D82B38E113235438D28869EE5E68'),
('12345683', 'B95A0A3ECE23CF05504F534B393CC3DD'),
('13240205', 'CB93DEC137C84F260206A5CEDBFDFB21'),
('13240206', '7822D69682D2631B1FBF991883C6508B'),
('13240218', '7C5B946B3AAC71F0D859AACAF00E4D9E'),
('13240220', 'F9FF4C4CBF0E672455483EEAD5394445'),
('13240232', '9794C6DA6E4A07E260B79874FA0B817B'),
('13240233', '2CC1E4C924181906EBAD8AAD3C3DF0AE'),
('13240234', '1A98FDA760ED80A69D2D11E9A5F09A0E');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `paper`
--
ALTER TABLE `paper`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `replay`
--
ALTER TABLE `replay`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `topic`
--
ALTER TABLE `topic`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
