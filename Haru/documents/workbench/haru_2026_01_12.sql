-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: haru
-- ------------------------------------------------------
-- Server version	8.0.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `user_no` int unsigned NOT NULL,
  `task_no` int unsigned NOT NULL,
  `comment_no` int unsigned NOT NULL AUTO_INCREMENT,
  `comment_regdate` datetime NOT NULL,
  `comment_contents` text NOT NULL,
  `comment_state` enum('T','F') NOT NULL,
  PRIMARY KEY (`comment_no`),
  KEY `fk_comment_user1` (`user_no`),
  KEY `fk_comment_task1` (`task_no`),
  CONSTRAINT `fk_comment_task1` FOREIGN KEY (`task_no`) REFERENCES `task` (`task_no`),
  CONSTRAINT `fk_comment_user1` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file` (
  `task_no` int unsigned NOT NULL,
  `file_no` int unsigned NOT NULL AUTO_INCREMENT,
  `origin_name` text NOT NULL,
  `change_name` text NOT NULL,
  `file_path` text NOT NULL,
  `file_regdate` datetime NOT NULL,
  `file_state` enum('T','F') NOT NULL,
  `file_maker` text,
  PRIMARY KEY (`file_no`),
  KEY `fk_file_task1` (`task_no`),
  CONSTRAINT `fk_file_task1` FOREIGN KEY (`task_no`) REFERENCES `task` (`task_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `history` (
  `project_project_no` int unsigned NOT NULL,
  `log_no` int NOT NULL AUTO_INCREMENT,
  `log_date` datetime DEFAULT NULL,
  `log_contents` text,
  PRIMARY KEY (`log_no`),
  KEY `fk_history_project1` (`project_project_no`),
  CONSTRAINT `fk_history_project1` FOREIGN KEY (`project_project_no`) REFERENCES `project` (`project_no`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `noticemessage`
--

DROP TABLE IF EXISTS `noticemessage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `noticemessage` (
  `notice_no` int unsigned NOT NULL AUTO_INCREMENT,
  `notice_message` text NOT NULL,
  `notice_link` text,
  `notice_date` datetime DEFAULT NULL,
  PRIMARY KEY (`notice_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `noticemsgbox`
--

DROP TABLE IF EXISTS `noticemsgbox`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `noticemsgbox` (
  `notice_no` int unsigned NOT NULL,
  `user_no` int unsigned NOT NULL,
  `message_ck` enum('Y','N') NOT NULL,
  PRIMARY KEY (`notice_no`,`user_no`),
  KEY `fk_noticemsgbox_user1` (`user_no`),
  CONSTRAINT `fk_noticemsgbox_noticemessage1` FOREIGN KEY (`notice_no`) REFERENCES `noticemessage` (`notice_no`),
  CONSTRAINT `fk_noticemsgbox_user1` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `noticeset`
--

DROP TABLE IF EXISTS `noticeset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `noticeset` (
  `user_no` int unsigned NOT NULL,
  `noticeset_assigh` enum('Y','N') NOT NULL,
  `noticeset_comment` enum('Y','N') NOT NULL,
  PRIMARY KEY (`user_no`),
  CONSTRAINT `fk_noticeset_user` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `project_no` int unsigned NOT NULL AUTO_INCREMENT,
  `project_title` varchar(500) NOT NULL,
  `project_desc` text,
  `project_start` datetime DEFAULT NULL,
  `project_end` datetime DEFAULT NULL,
  `project_state` varchar(50) NOT NULL,
  `project_regdate` datetime NOT NULL,
  PRIMARY KEY (`project_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule` (
  `user_no` int unsigned NOT NULL,
  `schedule_no` int unsigned NOT NULL AUTO_INCREMENT,
  `schedule_start` datetime DEFAULT NULL,
  `schedule_end` datetime DEFAULT NULL,
  `schedule_contents` text,
  PRIMARY KEY (`schedule_no`),
  KEY `fk_schedule_user1` (`user_no`),
  CONSTRAINT `fk_schedule_user1` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `taglist`
--

DROP TABLE IF EXISTS `taglist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taglist` (
  `tag_no` int unsigned NOT NULL,
  `tag_name` varchar(500) NOT NULL,
  `tag_color` varchar(100) NOT NULL,
  PRIMARY KEY (`tag_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tagtask`
--

DROP TABLE IF EXISTS `tagtask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tagtask` (
  `user_no` int unsigned NOT NULL,
  `tag_no` int unsigned NOT NULL,
  PRIMARY KEY (`user_no`,`tag_no`),
  KEY `fk_table1_taglist1` (`tag_no`),
  CONSTRAINT `fk_table1_taglist1` FOREIGN KEY (`tag_no`) REFERENCES `taglist` (`tag_no`),
  CONSTRAINT `fk_table1_user1` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task` (
  `task_no` int unsigned NOT NULL AUTO_INCREMENT,
  `task_name` varchar(200) NOT NULL,
  `task_start` datetime DEFAULT NULL,
  `task_end` datetime DEFAULT NULL,
  `task_label` varchar(100) NOT NULL,
  `task_state` enum('do','done','del') NOT NULL,
  `task_contents` text NOT NULL,
  `task_order` int NOT NULL,
  `task_regdate` datetime NOT NULL,
  `tasklist_no` int unsigned NOT NULL,
  `task_writer` varchar(100) NOT NULL,
  `task_updatedate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`task_no`),
  KEY `fk_task_tasklist1` (`tasklist_no`),
  CONSTRAINT `fk_task_tasklist1` FOREIGN KEY (`tasklist_no`) REFERENCES `tasklist` (`tasklist_no`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tasklist`
--

DROP TABLE IF EXISTS `tasklist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tasklist` (
  `tasklist_no` int unsigned NOT NULL AUTO_INCREMENT,
  `tasklist_name` varchar(300) NOT NULL DEFAULT '새 리스트',
  `tasklist_order` int NOT NULL,
  `tasklist_state` enum('T','F') NOT NULL,
  `project_no` int unsigned DEFAULT NULL,
  PRIMARY KEY (`tasklist_no`),
  KEY `fk_tasklist_project1` (`project_no`),
  CONSTRAINT `fk_tasklist_project1` FOREIGN KEY (`project_no`) REFERENCES `project` (`project_no`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `taskuser`
--

DROP TABLE IF EXISTS `taskuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taskuser` (
  `user_no` int unsigned NOT NULL,
  `task_no` int unsigned NOT NULL,
  KEY `fk_taskuser_user1` (`user_no`),
  KEY `fk_taskuser_task1` (`task_no`),
  CONSTRAINT `fk_taskuser_task1` FOREIGN KEY (`task_no`) REFERENCES `task` (`task_no`),
  CONSTRAINT `fk_taskuser_user1` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_no` int unsigned NOT NULL AUTO_INCREMENT,
  `user_regdate` datetime DEFAULT CURRENT_TIMESTAMP,
  `user_email` varchar(350) NOT NULL,
  `user_name` varchar(250) NOT NULL,
  `user_password` text NOT NULL,
  `user_number` varchar(100) DEFAULT NULL,
  `user_birth` date DEFAULT NULL,
  `user_title` varchar(200) DEFAULT NULL,
  `user_dept` varchar(200) DEFAULT NULL,
  `user_timezone` varchar(50) DEFAULT 'Asia/Seoul',
  `usert_photo` varchar(255) DEFAULT '/assets/upUserimages/Default.png',
  `user_bg` text,
  `user_key` varchar(100) DEFAULT NULL,
  `user_key_status` varchar(255) DEFAULT NULL,
  `user_photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `userproject`
--

DROP TABLE IF EXISTS `userproject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userproject` (
  `user_no` int unsigned NOT NULL,
  `project_no` int unsigned NOT NULL,
  `ownership` enum('O','M') NOT NULL,
  PRIMARY KEY (`user_no`,`project_no`,`ownership`),
  KEY `fk_userproject_project1` (`project_no`),
  CONSTRAINT `fk_userproject_project1` FOREIGN KEY (`project_no`) REFERENCES `project` (`project_no`),
  CONSTRAINT `fk_userproject_user1` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-01-12 16:09:52
