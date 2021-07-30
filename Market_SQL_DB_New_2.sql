CREATE SCHEMA `market_fp_db` DEFAULT CHARACTER SET utf8mb4 ;

CREATE TABLE `market_fp_db`.`roles_tbl` (
  `role_id` BIGINT NOT NULL AUTO_INCREMENT,
  `name_fld` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE INDEX `name_fld_UNIQUE` (`name_fld` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE `market_fp_db`.`users_tbl` (
  `user_id` BIGINT NOT NULL AUTO_INCREMENT,
  `username_fld` VARCHAR(255) NOT NULL,
  `password_fld` VARCHAR(255) NOT NULL,
  `email_fld` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE `market_fp_db`.`users_roles_tbl` (
  `user_id` BIGINT NOT NULL,
  `role_id` BIGINT NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `fk_role_id_role_idx` (`role_id` ASC),
  CONSTRAINT `fk_role_id_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `market_fp_db`.`roles_tbl` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_id_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `market_fp_db`.`users_tbl` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE `market_fp_db`.`category_tbl` (
  `category_id` BIGINT NOT NULL AUTO_INCREMENT,
  `title_fld` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE `market_fp_db`.`product_tbl` (
  `product_id` BIGINT NOT NULL AUTO_INCREMENT,
  `category_id` BIGINT NULL DEFAULT NULL,
  `title_fld` VARCHAR(255) NOT NULL,
  `price_fld` DECIMAL NULL DEFAULT NULL,
  `short_description_fld` VARCHAR(255) NULL DEFAULT NULL,
  `full_description_fld` VARCHAR(1020) NULL DEFAULT NULL,
  `photo_url_fld` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  INDEX `fk_categori_id_product_idx` (`category_id` ASC),
  CONSTRAINT `fk_categori_id_product`
    FOREIGN KEY (`category_id`)
    REFERENCES `market_fp_db`.`category_tbl` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE `market_fp_db`.`addresses_tbl` (
  `address_id` BIGINT NOT NULL AUTO_INCREMENT,
  `region_fld` VARCHAR(255) NULL DEFAULT 'NO REGION',
  `city_fld` VARCHAR(255) NOT NULL,
  `street_fld` VARCHAR(255) NOT NULL,
  `house_number_fld` VARCHAR(255) NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`address_id`),
  INDEX `_idx` (`user_id` ASC),
  CONSTRAINT `fk_address_id_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `market_fp_db`.`users_tbl` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE `market_fp_db`.`orders_tbl` (
  `order_id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `total_quantity_fld` INT NOT NULL,
  `total_cost_fld` DECIMAL NOT NULL,
  `address_id` BIGINT NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `fk_order_id_user_idx` (`user_id` ASC),
  INDEX `fk_order_id_address_idx` (`address_id` ASC),
  CONSTRAINT `fk_order_id_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `market_fp_db`.`users_tbl` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_id_address`
    FOREIGN KEY (`address_id`)
    REFERENCES `market_fp_db`.`addresses_tbl` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    CREATE TABLE `market_fp_db`.`order_items_tbl` (
  `order_item_id` BIGINT NOT NULL AUTO_INCREMENT,
  `order_id` BIGINT NOT NULL,
  `product_id` BIGINT NOT NULL,
  `quantity_fld` SMALLINT UNSIGNED NULL DEFAULT 1,
  `price_fld` DECIMAL NULL DEFAULT NULL,
  `cost_fld` DECIMAL NULL DEFAULT NULL,
  PRIMARY KEY (`order_item_id`),
  INDEX `fk_odrer_item_id_order_idx` (`order_id` ASC),
  INDEX `fk_order_item_id_product_idx` (`product_id` ASC),
  CONSTRAINT `fk_odrer_item_id_order`
    FOREIGN KEY (`order_id`)
    REFERENCES `market_fp_db`.`orders_tbl` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_item_id_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `market_fp_db`.`product_tbl` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

INSERT INTO `market_fp_db`.`category_tbl` (`title_fld`) VALUES ('Танцевальная обувь');
INSERT INTO `market_fp_db`.`category_tbl` (`title_fld`) VALUES ('Рейтинговые платья');
INSERT INTO `market_fp_db`.`category_tbl` (`title_fld`) VALUES ('Женская одежда');
INSERT INTO `market_fp_db`.`category_tbl` (`title_fld`) VALUES ('Мужская одежда');
INSERT INTO `market_fp_db`.`category_tbl` (`title_fld`) VALUES ('Аксессуары');

INSERT INTO `market_fp_db`.`users_tbl` (`username_fld`, `password_fld`, `email_fld`) VALUES ('user1', '$10$5fAKqEahjf2NsgPx0dD/g.BSrufqXUYcXEBC/oQk0/w.E1yRfJv9m', 'user1@user1.ru');
INSERT INTO `market_fp_db`.`users_tbl` (`username_fld`, `password_fld`, `email_fld`) VALUES ('user2', '$10$5fAKqEahjf2NsgPx0dD/g.BSrufqXUYcXEBC/oQk0/w.E1yRfJv9m', 'user2@user2.ru');
INSERT INTO `market_fp_db`.`users_tbl` (`username_fld`, `password_fld`, `email_fld`) VALUES ('user3', '$10$5fAKqEahjf2NsgPx0dD/g.BSrufqXUYcXEBC/oQk0/w.E1yRfJv9m', 'user3@user3.ru');
INSERT INTO `market_fp_db`.`users_tbl` (`username_fld`, `password_fld`, `email_fld`) VALUES ('bob', '$10$4VZ8eyVbHm.82pC1IETHKOR8WPtLKE9Se8nkJ/buTjvVPMuhPTLA.', 'qwe@qwe.qwe');

INSERT INTO `market_fp_db`.`roles_tbl` (`name_fld`) VALUES ('ROLE_USER');
INSERT INTO `market_fp_db`.`roles_tbl` (`name_fld`) VALUES ('ROLE_ADMIN');

INSERT INTO `market_fp_db`.`users_roles_tbl` (`user_id`, `role_id`) VALUES ('1', '1');
INSERT INTO `market_fp_db`.`users_roles_tbl` (`user_id`, `role_id`) VALUES ('2', '2');

INSERT INTO `market_fp_db`.`product_tbl` (`category_id`, `title_fld`, `price_fld`, `short_description_fld`, `full_description_fld`) VALUES ('1', 'Туфли Re', '3200', 'Туфли рейтинговые', 'Туфли рейтинговые, производитель Тансмастер, материал - сатин, модель 161, размеры с 16.0 по 27.0');
INSERT INTO `market_fp_db`.`product_tbl` (`category_id`, `title_fld`, `price_fld`, `short_description_fld`, `full_description_fld`) VALUES ('1', 'Туфли St м', '5900', 'Туфли мужские Стандарт', 'Туфли мужские для европейской программы, производитель Эксе, материал - искусственный лак, модель Алан, размеры с 24.0 по 30.0');
INSERT INTO `market_fp_db`.`product_tbl` (`category_id`, `title_fld`, `price_fld`, `short_description_fld`, `full_description_fld`) VALUES ('1', 'Туфли La ж', '6200', 'Туфли женские Латина', 'Туфли женские для латиноамериканской программы, производитель Эксе, материал - сатин, модель Марта, размеры с 21.0 по 26.0');
INSERT INTO `market_fp_db`.`product_tbl` (`category_id`, `title_fld`, `price_fld`, `short_description_fld`, `full_description_fld`) VALUES ('1', 'Туфли народные', '3500', 'Туфли женские народные', 'Туфли женские для народных танцев, производитель Танцмастер, материал - красная кожа, модель 770, размеры с 19.0 по 27.0');
INSERT INTO `market_fp_db`.`product_tbl` (`category_id`, `title_fld`, `price_fld`, `short_description_fld`, `full_description_fld`) VALUES ('2', 'Платье Re', '2200', 'Платье рейтинговое ПР-01', 'Платье рейтинговое с коротким рукавом отделанное ригелином, производитель АринаШарм, материал - бархат, модель ПР-01, размеры с 28 по 38');
INSERT INTO `market_fp_db`.`product_tbl` (`category_id`, `title_fld`, `price_fld`, `short_description_fld`, `full_description_fld`) VALUES ('2', 'Платье Re', '5200', 'Платье рейтинговое Муза', 'Платье рейтинговое с гипюром, производитель AltraNatura, материал бифлекс и гипюр, модель Муза, размеры с 28 по 44');
INSERT INTO `market_fp_db`.`product_tbl` (`category_id`, `title_fld`, `price_fld`, `short_description_fld`, `full_description_fld`) VALUES ('2', 'Платье Re', '15000', 'Платье рейтинрговое RDC', 'Платье рейтинговое с двумя юбками, производитель RDC, материал - атлас сатин, мадель 112-1305, размеры с 28 по 40');
INSERT INTO `market_fp_db`.`product_tbl` (`category_id`, `title_fld`, `price_fld`, `short_description_fld`, `full_description_fld`) VALUES ('3', 'Топ', '1100', 'Топ женский тренировочный', 'Топ женский для тернировок, производитель Танцующие, материал - масло, модель Блисс, размеры с 38 по 48');
INSERT INTO `market_fp_db`.`product_tbl` (`category_id`, `title_fld`, `price_fld`, `short_description_fld`, `full_description_fld`) VALUES ('3', 'Юбка La', '2900', 'Юбка латина с бахрамой', 'Юбка тренировочная для латиноамериканских танцев, призводитель AltraNatura, метреиал бахрома, модель Кира, размеры с 38 оп 48');
INSERT INTO `market_fp_db`.`product_tbl` (`category_id`, `title_fld`, `price_fld`, `short_description_fld`, `full_description_fld`) VALUES ('3', 'Юбка St', '3000', 'Юбка стандарт', 'Юбка тренировочная для европейской программы, производитель АринаШарм, материал - мерил, модель ЮС 2.1, размеры с 34 по 48');
INSERT INTO `market_fp_db`.`product_tbl` (`category_id`, `title_fld`, `price_fld`, `short_description_fld`, `full_description_fld`) VALUES ('4', 'Жилет', '3500', 'Жилет с бархатной отделкой', 'Жилет для европейской прогаммы с бархатной отделкой, производитель AltraNatura, материал - габардин, модель Эдгар, размеры с 36 по 48');
INSERT INTO `market_fp_db`.`product_tbl` (`category_id`, `title_fld`, `price_fld`, `short_description_fld`, `full_description_fld`) VALUES ('4', 'Брюки', '2600', 'Брюки с атласом', 'Брюки с атласным поясом и лампасами, производиель Танцующие, материал - габардин и атлас, модель Моне, размеры с 28 по 52');
INSERT INTO `market_fp_db`.`product_tbl` (`category_id`, `title_fld`, `price_fld`, `short_description_fld`, `full_description_fld`) VALUES ('4', 'Туника муж', '1500', 'Туника мужская тренировочная', 'Туника мужская для тренировок, производиетель Танцующие, материал - масло, модель Денвер, размеры с 32 по 54');
INSERT INTO `market_fp_db`.`product_tbl` (`category_id`, `title_fld`, `price_fld`, `short_description_fld`, `full_description_fld`) VALUES ('4', 'Рубашка La', '2400', 'Рубашка Латина', 'Рубашка для латиноамериканских танцев, произмодетель Танцующие, метриал - масло и сетка, модель Маурицио, размеры с 42 по 48');
INSERT INTO `market_fp_db`.`product_tbl` (`category_id`, `title_fld`, `price_fld`, `short_description_fld`, `full_description_fld`) VALUES ('5', 'Портплед', '3900', 'Портплед взрослый', 'Портплед для мужских костюмов взрослый. Очень вместительный и удобный. Ручка, ремень через плечо, петли для вешалок и множество корманов.');
INSERT INTO `market_fp_db`.`product_tbl` (`category_id`, `title_fld`, `price_fld`, `short_description_fld`, `full_description_fld`) VALUES ('5', 'Щётка для обуви', '350', 'Щётка для обуви с липучкой DF', 'Незаменимый аксессуар по уходу за танцевальной обувью - чпециальная щётка. Должен быть у каждого танцора. ');
INSERT INTO `market_fp_db`.`product_tbl` (`category_id`, `title_fld`, `price_fld`, `short_description_fld`, `full_description_fld`) VALUES ('5', 'Сумка для обуви', '850', 'Сумка для обуви с сеткой', 'Очень удобные сумки для танцевальной обуви и тренировочной одежды. Ручка, молния, кармашек, сеточка для того, чтобы обувь \"дышала\". Разные яркие цвета.');

ALTER TABLE `market_fp_db`.`category_tbl` 
RENAME TO  `market_fp_db`.`categories_tbl` ;

ALTER TABLE `market_fp_db`.`product_tbl` 
RENAME TO  `market_fp_db`.`products_tbl` ;

ALTER TABLE `market_fp_db`.`products_tbl` 
DROP FOREIGN KEY `fk_categori_id_product`;
ALTER TABLE `market_fp_db`.`products_tbl` 
DROP COLUMN `category_id`,
DROP INDEX `fk_categori_id_product_idx` ;

CREATE TABLE `market_fp_db`.`products_categories_tbl` (
  `product_id` BIGINT NOT NULL,
  `category_id` BIGINT NOT NULL,
  PRIMARY KEY (`product_id`, `category_id`),
  INDEX `fk_category_id_category_idx` (`category_id` ASC),
  CONSTRAINT `fk_product_id_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `market_fp_db`.`products_tbl` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_category_id_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `market_fp_db`.`categories_tbl` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

INSERT INTO `market_fp_db`.`products_categories_tbl` (`product_id`, `category_id`) VALUES ('1', '1');
INSERT INTO `market_fp_db`.`products_categories_tbl` (`product_id`, `category_id`) VALUES ('2', '1');
INSERT INTO `market_fp_db`.`products_categories_tbl` (`product_id`, `category_id`) VALUES ('3', '1');
INSERT INTO `market_fp_db`.`products_categories_tbl` (`product_id`, `category_id`) VALUES ('4', '1');
INSERT INTO `market_fp_db`.`products_categories_tbl` (`product_id`, `category_id`) VALUES ('5', '2');
INSERT INTO `market_fp_db`.`products_categories_tbl` (`product_id`, `category_id`) VALUES ('6', '2');
INSERT INTO `market_fp_db`.`products_categories_tbl` (`product_id`, `category_id`) VALUES ('7', '2');
INSERT INTO `market_fp_db`.`products_categories_tbl` (`product_id`, `category_id`) VALUES ('8', '3');
INSERT INTO `market_fp_db`.`products_categories_tbl` (`product_id`, `category_id`) VALUES ('9', '3');
INSERT INTO `market_fp_db`.`products_categories_tbl` (`product_id`, `category_id`) VALUES ('10', '3');
INSERT INTO `market_fp_db`.`products_categories_tbl` (`product_id`, `category_id`) VALUES ('11', '4');
INSERT INTO `market_fp_db`.`products_categories_tbl` (`product_id`, `category_id`) VALUES ('12', '4');
INSERT INTO `market_fp_db`.`products_categories_tbl` (`product_id`, `category_id`) VALUES ('13', '4');
INSERT INTO `market_fp_db`.`products_categories_tbl` (`product_id`, `category_id`) VALUES ('14', '4');
INSERT INTO `market_fp_db`.`products_categories_tbl` (`product_id`, `category_id`) VALUES ('15', '5');
INSERT INTO `market_fp_db`.`products_categories_tbl` (`product_id`, `category_id`) VALUES ('16', '5');
INSERT INTO `market_fp_db`.`products_categories_tbl` (`product_id`, `category_id`) VALUES ('17', '5');

