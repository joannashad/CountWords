 call getWords(false);
CREATE database word_count;
USE word_count;
DROP table IF EXISTS word ;
CREATE TABLE word (
     id MEDIUMINT NOT NULL AUTO_INCREMENT,
     word CHAR(255) NOT NULL,
     wordcount int NOT NULL,
     PRIMARY KEY (id)
     
);
DROP procedure IF EXISTS getWords;
DELIMITER //
CREATE PROCEDURE getWords(
	in top boolean
)
BEGIN
IF top=false THEN 
	
	SELECT * 
 	FROM word
	ORDER BY word.wordcount;

ELSE 
	SELECT word,SUM(wordcount) as 'wordcount' 
 	FROM word
	GROUP BY word
    ORDER BY SUM(wordcount) DESC
	LIMIT 20;
    
END IF;

END; //
GRANT execute ON word_count.getWords TO 'dbUser'@'localhost'  identified by 'password';
call insertWord 'joanna',1;
DELIMITER ;
DROP procedure IF EXISTS insertWord;
delimiter //
CREATE PROCEDURE insertWord(
	in word varchar(255),
    wordcount int
)
BEGIN

	INSERT INTO 
    word_count.word
    (word,wordcount)
    VALUES
    (word,wordcount);
    
    
END; //
GRANT execute ON word_count.insertWord TO 'dbUser'@'localhost'  identified by 'password';
DROP procedure IF EXISTS getWord;

delimiter //
CREATE PROCEDURE getWord (
	in myword varchar(255)
)
BEGIN

	SELECT word, SUM(wordcount) as wordcount
    from word_count.word
    WHERE word=myword
    group by word;
    
    
END; //
GRANT execute ON word_count.insertWord TO 'dbUser'@'localhost'  identified by 'password';

DELIMITER ;
DROP procedure IF EXISTS deleteWords;
delimiter //
CREATE PROCEDURE deleteWords()
BEGIN

	DELETE from 
    word_count.word;    
    
END; //

DROP procedure IF EXISTS getWordCount;
call getWordCount;
delimiter //
CREATE PROCEDURE getWordCount()
BEGIN

	SELECT sum(wordcount) as sum_count from 
    word_count.word
    ;    
    
END; //
GRANT execute ON word_count.deleteWords TO 'dbUser'@'localhost'  identified by 'password';
DELIMITER ;