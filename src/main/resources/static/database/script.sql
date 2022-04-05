CREATE TABLE board
(
    No INT(11) AUTO_INCREMENT primary key,
    title VARCHAR(200) not null,
    content text,
    writer varchar(50) not null,
    regdate datetime not null,
    viewno int(11) 
);

alter table board modify viewno int(11) default 0;

INSERT INTO BOARD(TITLE, CONTENT, WRITER, REGDATE)
VALUES ( 'TEST'
	   , 'TEST'
	   , 'TEST'
	   , NOW());
       
CREATE TABLE USER
(
    ID VARCHAR(20) primary key,
    PASSWORD VARCHAR(100) NOT NULL,
    NAME VARCHAR(12) NOT NULL,
    REGDATE datetime,
    ROLE VARCHAR(20)
);
       
alter table user 
change column role 
role enum('ROLE_USER','ROLE_ADMIN');
       
create table file(
	fno int not null auto_increment primary key,
	bno int not null,
	filename varchar(200) not null,
	original_filename varchar(300) not null,
	upload_path varchar(500) not null,
	filesize int,
	filetype varchar(20),
	createdate datetime,
	createuser varchar(20)
);