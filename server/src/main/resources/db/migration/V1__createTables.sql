CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`,`username`)
);

CREATE TABLE `comments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `level` INT NULL DEFAULT 0,
  `text` VARCHAR(255) NOT NULL,
  `user_id` INT NOT NULL,
  `parent_comment_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user`
    FOREIGN KEY (`user_id`)
    REFERENCES `users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);


ALTER TABLE `comments`
ADD INDEX `parent_comment_idx` (`parent_comment_id` ASC) VISIBLE;

ALTER TABLE `comments`
ADD CONSTRAINT `parent_comment`
  FOREIGN KEY (`parent_comment_id`)
  REFERENCES `comments` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
