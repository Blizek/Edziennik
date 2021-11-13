-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema dziennik
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dziennik
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dziennik` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `dziennik` ;

-- -----------------------------------------------------
-- Table `dziennik`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`user` (
  `user_id` INT NOT NULL,
  `user_email` VARCHAR(50) NOT NULL,
  `user_password` VARCHAR(50) NOT NULL,
  `user_role` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dziennik`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`admin` (
  `admin_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `admin_name` VARCHAR(50) NOT NULL,
  `admin_surname` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`admin_id`),
  INDEX `admin_user_id_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `admin_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `dziennik`.`user` (`user_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dziennik`.`principal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`principal` (
  `principal_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `principal_name` VARCHAR(50) NOT NULL,
  `principal_surname` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`principal_id`),
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `principal_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `dziennik`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dziennik`.`school`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`school` (
  `school_id` INT NOT NULL,
  `principal_id` INT NOT NULL,
  `school_name` VARCHAR(50) NOT NULL,
  `school_place` VARCHAR(100) NOT NULL,
  `school_email` VARCHAR(50) NOT NULL,
  `school_phone_number` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`school_id`),
  INDEX `school_principal_id_fk_idx` (`principal_id` ASC) VISIBLE,
  CONSTRAINT `school_principal_id_fk`
    FOREIGN KEY (`principal_id`)
    REFERENCES `dziennik`.`principal` (`principal_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dziennik`.`teacher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`teacher` (
  `teacher_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `school_id` INT NOT NULL,
  `teacher_name` VARCHAR(50) NOT NULL,
  `teacher_surname` VARCHAR(50) NOT NULL,
  `teacher_subject` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`teacher_id`),
  INDEX `teacher_user_id_fk_idx` (`user_id` ASC) VISIBLE,
  INDEX `teacher_school_id_fk_idx` (`school_id` ASC) VISIBLE,
  CONSTRAINT `teacher_school_id_fk`
    FOREIGN KEY (`school_id`)
    REFERENCES `dziennik`.`school` (`school_id`)
    ON DELETE CASCADE,
  CONSTRAINT `teacher_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `dziennik`.`user` (`user_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dziennik`.`class`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`class` (
  `class_id` INT NOT NULL,
  `class_supervising_teacher` INT NOT NULL,
  `class_name` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`class_id`),
  INDEX `class_teacher_id_fk_idx` (`class_supervising_teacher` ASC) VISIBLE,
  CONSTRAINT `class_teacher_id_fk`
    FOREIGN KEY (`class_supervising_teacher`)
    REFERENCES `dziennik`.`teacher` (`teacher_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dziennik`.`guardian`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`guardian` (
  `guardian_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `guardian_name` VARCHAR(50) NOT NULL,
  `guardian_surname` VARCHAR(50) NOT NULL,
  `guardian_date_of_birth` DATE NOT NULL,
  PRIMARY KEY (`guardian_id`),
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `guardian_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `dziennik`.`user` (`user_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dziennik`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`student` (
  `student_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `school_id` INT NOT NULL,
  `class_id` INT NOT NULL,
  `student_name` VARCHAR(50) NOT NULL,
  `student_surname` VARCHAR(50) NOT NULL,
  `student_date_of_birth` DATE NOT NULL,
  PRIMARY KEY (`student_id`),
  INDEX `student_user_id_fk_idx` (`user_id` ASC) VISIBLE,
  INDEX `student_school_id_fk_idx` (`school_id` ASC) VISIBLE,
  INDEX `student_class_id_fk_idx` (`class_id` ASC) VISIBLE,
  CONSTRAINT `student_class_id_fk`
    FOREIGN KEY (`class_id`)
    REFERENCES `dziennik`.`class` (`class_id`)
    ON DELETE CASCADE,
  CONSTRAINT `student_school_id_fk`
    FOREIGN KEY (`school_id`)
    REFERENCES `dziennik`.`school` (`school_id`)
    ON DELETE CASCADE,
  CONSTRAINT `student_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `dziennik`.`user` (`user_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dziennik`.`guardian_student_list`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`guardian_student_list` (
  `student_id` INT NOT NULL,
  `guardian_id` INT NOT NULL,
  INDEX `guardian_student_list_student_id_fk_idx` (`student_id` ASC) VISIBLE,
  INDEX `guardian_student_list_guardian_id_fk_idx` (`guardian_id` ASC) VISIBLE,
  CONSTRAINT `guardian_student_list_guardian_id_fk`
    FOREIGN KEY (`guardian_id`)
    REFERENCES `dziennik`.`guardian` (`guardian_id`)
    ON DELETE CASCADE,
  CONSTRAINT `guardian_student_list_student_id_fk`
    FOREIGN KEY (`student_id`)
    REFERENCES `dziennik`.`student` (`student_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dziennik`.`lesson`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`lesson` (
  `lesson_id` INT NOT NULL,
  `lesson_subject` VARCHAR(50) NOT NULL,
  `student_presence` TINYINT NOT NULL,
  PRIMARY KEY (`lesson_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dziennik`.`lesson_student_list`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`lesson_student_list` (
  `lesson_id` INT NOT NULL,
  `student_id` INT NOT NULL,
  INDEX `lesson_student_id_fk_idx` (`student_id` ASC) VISIBLE,
  INDEX `lesson_student_list_lesson_id_fk_idx` (`lesson_id` ASC) VISIBLE,
  CONSTRAINT `lesson_student_list_lesson_id_fk`
    FOREIGN KEY (`lesson_id`)
    REFERENCES `dziennik`.`lesson` (`lesson_id`)
    ON DELETE CASCADE,
  CONSTRAINT `lesson_student_list_student_id_fk`
    FOREIGN KEY (`student_id`)
    REFERENCES `dziennik`.`student` (`student_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dziennik`.`mark`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`mark` (
  `mark_id` INT NOT NULL,
  `student_id` INT NOT NULL,
  `teacher_id` INT NOT NULL,
  `mark_value` FLOAT NOT NULL,
  `mark_description` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`mark_id`),
  INDEX `mark_student_id_fk_idx` (`student_id` ASC) VISIBLE,
  INDEX `mark_teacher_id_fk_idx` (`teacher_id` ASC) VISIBLE,
  CONSTRAINT `mark_student_id_fk`
    FOREIGN KEY (`student_id`)
    REFERENCES `dziennik`.`student` (`student_id`)
    ON DELETE CASCADE,
  CONSTRAINT `mark_teacher_id_fk`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `dziennik`.`teacher` (`teacher_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dziennik`.`teacher_class_list`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`teacher_class_list` (
  `teacher_id` INT NOT NULL,
  `class_id` INT NOT NULL,
  INDEX `teacher_class_list_teacher_id_fk_idx` (`teacher_id` ASC) VISIBLE,
  INDEX `teacher_class_list_class_id_fk_idx` (`class_id` ASC) VISIBLE,
  CONSTRAINT `teacher_class_list_class_id_fk`
    FOREIGN KEY (`class_id`)
    REFERENCES `dziennik`.`class` (`class_id`)
    ON DELETE CASCADE,
  CONSTRAINT `teacher_class_list_teacher_id_fk`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `dziennik`.`teacher` (`teacher_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dziennik`.`teacher_lesson_list`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`teacher_lesson_list` (
  `teacher_id` INT NOT NULL,
  `lesson_id` INT NOT NULL,
  INDEX `teacher_lesson_list_teacher_id_fk_idx` (`teacher_id` ASC) VISIBLE,
  INDEX `teacher_lesson_list_lesson_id_fk_idx` (`lesson_id` ASC) VISIBLE,
  CONSTRAINT `teacher_lesson_list_lesson_id_fk`
    FOREIGN KEY (`lesson_id`)
    REFERENCES `dziennik`.`lesson` (`lesson_id`)
    ON DELETE CASCADE,
  CONSTRAINT `teacher_lesson_list_teacher_id_fk`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `dziennik`.`teacher` (`teacher_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
