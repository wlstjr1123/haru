-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema haru
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `haru` ;

-- -----------------------------------------------------
-- Schema haru
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `haru` DEFAULT CHARACTER SET utf8 ;
USE `haru` ;

-- -----------------------------------------------------
-- Table `haru`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `haru`.`user` ;

CREATE TABLE IF NOT EXISTS `haru`.`user` (
  `user_no` INT UNSIGNED NOT NULL,
  `user_regdate` DATETIME NOT NULL,
  `user_email` VARCHAR(350) NOT NULL,
  `user_name` VARCHAR(250) NOT NULL,
  `user_password` TEXT NOT NULL,
  `user_number` VARCHAR(100) NULL,
  `user_birth` DATE NULL,
  `user_title` VARCHAR(200) NULL,
  `user_dept` VARCHAR(200) NULL,
  `user_timezone` VARCHAR(200) NOT NULL,
  `usert_photo` TEXT NOT NULL,
  `user_bg` TEXT NULL,
  `user_key` VARCHAR(100) NULL,
  PRIMARY KEY (`user_no`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `haru`.`noticeset`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `haru`.`noticeset` ;

CREATE TABLE IF NOT EXISTS `haru`.`noticeset` (
  `user_no` INT UNSIGNED NOT NULL,
  `noticeset_assigh` ENUM('Y', 'N') NOT NULL,
  `noticeset_comment` ENUM('Y', 'N') NOT NULL,
  PRIMARY KEY (`user_no`),
  CONSTRAINT `fk_noticeset_user`
    FOREIGN KEY (`user_no`)
    REFERENCES `haru`.`user` (`user_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `haru`.`schedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `haru`.`schedule` ;

CREATE TABLE IF NOT EXISTS `haru`.`schedule` (
  `user_no` INT UNSIGNED NOT NULL,
  `schedule_no` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `schedule_start` DATETIME NULL,
  `schedule_end` DATETIME NULL,
  `schedule_contents` TEXT NULL,
  PRIMARY KEY (`schedule_no`),
  CONSTRAINT `fk_schedule_user1`
    FOREIGN KEY (`user_no`)
    REFERENCES `haru`.`user` (`user_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `haru`.`project`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `haru`.`project` ;

CREATE TABLE IF NOT EXISTS `haru`.`project` (
  `project_no` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `project_title` VARCHAR(500) NOT NULL,
  `project_desc` TEXT NULL,
  `project_start` DATETIME NULL,
  `project_end` DATETIME NULL,
  `project_state` VARCHAR(50) NOT NULL,
  `project_regdate` DATETIME NOT NULL,
  PRIMARY KEY (`project_no`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `haru`.`tasklist`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `haru`.`tasklist` ;

CREATE TABLE IF NOT EXISTS `haru`.`tasklist` (
  `tasklist_no` INT UNSIGNED NOT NULL,
  `tasklist_name` VARCHAR(300) NOT NULL,
  `tasklist_order` INT NOT NULL,
  `tasklist_state` ENUM('T', 'F') NOT NULL,
  `project_project_no` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`tasklist_no`),
  CONSTRAINT `fk_tasklist_project1`
    FOREIGN KEY (`project_project_no`)
    REFERENCES `haru`.`project` (`project_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `haru`.`task`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `haru`.`task` ;

CREATE TABLE IF NOT EXISTS `haru`.`task` (
  `task_no` INT UNSIGNED NOT NULL,
  `task_start` DATETIME NULL,
  `task_end` DATETIME NULL,
  `task_label` VARCHAR(100) NOT NULL,
  `task_state` ENUM('do', 'done', 'del') NOT NULL,
  `task_contents` TEXT NOT NULL,
  `task_order` INT NOT NULL,
  `task_regdate` DATETIME NOT NULL,
  `tasklist_tasklist_no` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`task_no`),
  CONSTRAINT `fk_task_tasklist1`
    FOREIGN KEY (`tasklist_tasklist_no`)
    REFERENCES `haru`.`tasklist` (`tasklist_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `haru`.`comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `haru`.`comment` ;

CREATE TABLE IF NOT EXISTS `haru`.`comment` (
  `user_no` INT UNSIGNED NOT NULL,
  `task_no` INT UNSIGNED NOT NULL,
  `comment_no` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `comment_regdate` DATETIME NOT NULL,
  `comment_contents` TEXT NOT NULL,
  `comment_state` ENUM('T', 'F') NOT NULL,
  PRIMARY KEY (`comment_no`),
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_no`)
    REFERENCES `haru`.`user` (`user_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_task1`
    FOREIGN KEY (`task_no`)
    REFERENCES `haru`.`task` (`task_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `haru`.`taskuser`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `haru`.`taskuser` ;

CREATE TABLE IF NOT EXISTS `haru`.`taskuser` (
  `user_no` INT UNSIGNED NOT NULL,
  `task_no` INT UNSIGNED NOT NULL,
  CONSTRAINT `fk_taskuser_user1`
    FOREIGN KEY (`user_no`)
    REFERENCES `haru`.`user` (`user_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_taskuser_task1`
    FOREIGN KEY (`task_no`)
    REFERENCES `haru`.`task` (`task_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `haru`.`file`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `haru`.`file` ;

CREATE TABLE IF NOT EXISTS `haru`.`file` (
  `task_no` INT NOT NULL,
  `file_no` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `origin_name` TEXT NOT NULL,
  `change_name` TEXT NOT NULL,
  `file_path` TEXT NOT NULL,
  `file_regdate` DATETIME NOT NULL,
  `file_state` ENUM('T', 'F') NOT NULL,
  `file_maker` TEXT NULL,
  PRIMARY KEY (`file_no`),
  CONSTRAINT `fk_file_task1`
    FOREIGN KEY (`task_no`)
    REFERENCES `haru`.`task` (`task_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `haru`.`taglist`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `haru`.`taglist` ;

CREATE TABLE IF NOT EXISTS `haru`.`taglist` (
  `tag_no` INT UNSIGNED NOT NULL,
  `tag_name` VARCHAR(500) NOT NULL,
  `tag_color` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`tag_no`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `haru`.`tagtask`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `haru`.`tagtask` ;

CREATE TABLE IF NOT EXISTS `haru`.`tagtask` (
  `user_no` INT UNSIGNED NOT NULL,
  `tag_no` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`user_no`, `tag_no`),
  CONSTRAINT `fk_table1_user1`
    FOREIGN KEY (`user_no`)
    REFERENCES `haru`.`user` (`user_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_taglist1`
    FOREIGN KEY (`tag_no`)
    REFERENCES `haru`.`taglist` (`tag_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `haru`.`noticemessage`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `haru`.`noticemessage` ;

CREATE TABLE IF NOT EXISTS `haru`.`noticemessage` (
  `notice_no` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `notice_message` TEXT NOT NULL,
  `notice_link` TEXT NULL,
  `notice_date` DATETIME NULL,
  PRIMARY KEY (`notice_no`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `haru`.`noticemsgbox`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `haru`.`noticemsgbox` ;

CREATE TABLE IF NOT EXISTS `haru`.`noticemsgbox` (
  `notice_no` INT UNSIGNED NOT NULL,
  `user_no` INT UNSIGNED NOT NULL,
  `message_ck` ENUM('Y', 'N') NOT NULL,
  PRIMARY KEY (`notice_no`, `user_no`),
  CONSTRAINT `fk_noticemsgbox_noticemessage1`
    FOREIGN KEY (`notice_no`)
    REFERENCES `haru`.`noticemessage` (`notice_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_noticemsgbox_user1`
    FOREIGN KEY (`user_no`)
    REFERENCES `haru`.`user` (`user_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `haru`.`userproject`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `haru`.`userproject` ;

CREATE TABLE IF NOT EXISTS `haru`.`userproject` (
  `user_user_no` INT UNSIGNED NOT NULL,
  `project_project_no` INT UNSIGNED NOT NULL,
  `ownership` ENUM('O', 'M') NOT NULL,
  PRIMARY KEY (`user_user_no`, `project_project_no`, `ownership`),
  CONSTRAINT `fk_userproject_user1`
    FOREIGN KEY (`user_user_no`)
    REFERENCES `haru`.`user` (`user_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_userproject_project1`
    FOREIGN KEY (`project_project_no`)
    REFERENCES `haru`.`project` (`project_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `haru`.`history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `haru`.`history` ;

CREATE TABLE IF NOT EXISTS `haru`.`history` (
  `project_project_no` INT UNSIGNED NOT NULL,
  `log_no` INT NOT NULL,
  `log_date` DATETIME NULL,
  `log_contents` TEXT NULL,
  PRIMARY KEY (`log_no`),
  CONSTRAINT `fk_history_project1`
    FOREIGN KEY (`project_project_no`)
    REFERENCES `haru`.`project` (`project_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
