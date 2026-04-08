CREATE DATABASE IF NOT EXISTS MealDB;
USE MealDB;
CREATE TABLE Menu(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    creationDate DATE DEFAULT CURDATE(),
    lastUpdateDate DATE DEFAULT CURDATE(),
    creatorId INT NOT NULL  -- "Foreign" key to User(id)
);

DELIMITER //
CREATE TRIGGER before_menu_update
    BEFORE UPDATE ON Menu
    FOR EACH ROW
BEGIN
    SET NEW.lastUpdateDate = CURDATE();
END; //
DELIMITER ;
