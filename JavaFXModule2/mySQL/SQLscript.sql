use wordcount;
CREATE database word_count;
USE word_count;

DROP table IF EXISTS word;
CREATE TABLE word (
     id MEDIUMINT NOT NULL AUTO_INCREMENT,
     word CHAR(100) NOT NULL,
     count int NOT NULL,
     PRIMARY KEY (id)
     
);
call getWords('true');
DROP procedure IF EXISTS getWords;
DELIMITER //
CREATE PROCEDURE getWords(
	in top varchar(10)
)
BEGIN
IF top='false' THEN 
	
	SELECT * 
 	FROM word
	ORDER BY word.count;

ELSE 
	SELECT word,SUM(count) as sum_count 
 	FROM word
    GROUP BY word
	ORDER BY sum(count) desc
	LIMIT 20;
    
END IF;

END; //
use wordcount;
GRANT EXECUTE ON wordcount.getWords TO 'mySQLuser'@'' IDENTIFIED BY 'secret' ;

DELIMITER ;
call insertWord('the',1);
DROP procedure IF EXISTS insertWord;
delimiter //

CREATE PROCEDURE insertWord(
	in word varchar(100),
    wordcount int
)
BEGIN

	INSERT INTO 
    word
    (word,count)
    VALUES
    (word,wordcount);
    
    
END; //
GRANT execute ON wordcount.insertWord TO 'dbUser'@'localhost'  identified by 'password';
DROP procedure IF EXISTS getWord;

delimiter //
CREATE PROCEDURE getWord (
	in myword varchar(100)
)
BEGIN

	SELECT word, SUM(count) as wordcount
    from word
    WHERE word=myword
    group by word;
    
    
END; //
GRANT execute ON wordcount.insertWord TO 'dbUser'@'localhost'  identified by 'password';

DELIMITER ;
DROP procedure IF EXISTS deleteWords;
delimiter //
CREATE PROCEDURE deleteWords()
BEGIN

	DELETE from 
    word;    
    
END; //

GRANT execute ON wordcount.deleteWords TO 'dbUser'@'localhost'  identified by 'password';
DELIMITER ;