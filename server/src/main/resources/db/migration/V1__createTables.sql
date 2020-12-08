CREATE TABLE `users` (
  `id` int NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`,`username`)
);

CREATE TABLE `comments` (
  `id` INT NOT NULL,
  `level` INT NULL DEFAULT 0,
  `text` VARCHAR(255) NOT NULL,
  `userId` INT NOT NULL,
  `parentCommentId` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `user`
    FOREIGN KEY (`userId`)
    REFERENCES `users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);


ALTER TABLE `comments`
ADD INDEX `parent_comment_idx` (`parentCommentId` ASC) VISIBLE;

ALTER TABLE `comments`
ADD CONSTRAINT `parent_comment`
  FOREIGN KEY (`parentCommentId`)
  REFERENCES `comments` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
