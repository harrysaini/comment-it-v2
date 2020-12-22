ALTER TABLE `comments`
    ADD COLUMN `created_at` DATETIME NOT NULL AFTER `parent_comment_id`,
    ADD COLUMN `updated_at` DATETIME NOT NULL AFTER `created_at`;


ALTER TABLE `users`
    ADD COLUMN `created_at` DATETIME NOT NULL AFTER `password`,
    ADD COLUMN `updated_at` DATETIME NOT NULL AFTER `created_at`;