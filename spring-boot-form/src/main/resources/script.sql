/* eslint-disable */
DELIMITER $$
CREATE DEFINER=`root`@`%` PROCEDURE `FIND_BOOK`(IN book INT)
BEGIN
	SELECT * FROM person WHERE id_book = p_book;
END$$
DELIMITER ;



DELIMITER $$
CREATE DEFINER=`root`@`%` PROCEDURE GET_TOTAL_BOOKS(OUT count_out INT)
BEGIN
	SELECT COUNT(*) into count_out FROM book;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`%` PROCEDURE DELETE_BOOK(IN P_ID_BOOK INT)
BEGIN
	DELETE FROM book WHERE ID_BOOK = P_ID_BOOK;
END$$
DELIMITER ;



USE `springmysql`;
DROP function IF EXISTS `GET_NAME`;

DELIMITER $$
USE `springmysql`$$
CREATE FUNCTION GET_NAME ( P_BOOK_ID INT)
RETURNS varchar(200)
READS SQL DATA
DETERMINISTIC
BEGIN
	DECLARE P_NAME VARCHAR(200);
    SELECT name into P_NAME from book where id_book = P_BOOK_ID;
    RETURN P_NAME;
END$$

DELIMITER ;
