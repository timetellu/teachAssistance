CREATE DATABASE IF NOT EXISTS syjf_system;

-- 管理员表
CREATE TABLE `t_admin` (
  `userId` INT(11) NOT NULL AUTO_INCREMENT, #管理员的编号
  `userName` VARCHAR(66) DEFAULT NULL,      #管理员的登录名
  `userPw` VARCHAR(55) DEFAULT NULL,        #管理员的密码
  PRIMARY KEY  (`userId`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `t_admin` VALUES ('1', '001', '123456');
INSERT INTO `t_admin` VALUES ('2', '002', '123456');
INSERT INTO `t_admin` VALUES ('3', '003', '123456');


-- 教师表
DROP TABLE IF EXISTS `t_tea`;
CREATE TABLE `t_tea` (
  `tea_id` INT(11) NOT NULL,              #教师id
  `tea_bianhao` VARCHAR(66) DEFAULT NULL UNIQUE, #教师编号
  `tea_realname` VARCHAR(55) DEFAULT NULL,#教师姓名 
  `tea_sex` VARCHAR(50) DEFAULT NULL,     #教师性别
  `tea_age` VARCHAR(50) DEFAULT NULL,     #教师年龄
  `login_name` VARCHAR(50) DEFAULT NULL,  #教师登录用户名
  `login_pw` VARCHAR(50) DEFAULT NULL,    #教师登录密码
  `del` VARCHAR(50) DEFAULT NULL,         #教师是否删除
  PRIMARY KEY  (`tea_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `t_tea` VALUES ('1', '001', '张老师', '男', '30', '001', '123', 'no');
INSERT INTO `t_tea` VALUES ('2', '002', '王老师', '女', '23', '002', '123', 'no');
INSERT INTO `t_tea` VALUES ('3', '003', '李老师', '男', '24', '002', '123', 'no');


-- 学生表
DROP TABLE IF EXISTS `t_stu`;
CREATE TABLE `t_stu` (
  `stuId` INT(11) NOT NULL AUTO_INCREMENT, #学生编号
  `stuNum` VARCHAR(66) DEFAULT NULL,       #学生学号
  `stuRealname` VARCHAR(50) DEFAULT NULL,  #学生真实姓名
  `stuSex` VARCHAR(50) DEFAULT NULL,       #学生性别
  `stuAge` VARCHAR(55) DEFAULT NULL,       #学生年龄 
  `loginPw` VARCHAR(50) DEFAULT NULL,      #学生登录密码
  `status` VARCHAR(50) DEFAULT NULL,       #学生是否毕业 y:已经毕业 n:未毕业  
  `del` VARCHAR(50) DEFAULT NULL,          #学生是否删除
  PRIMARY KEY  (`stuId`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO t_stu VALUES (NULL,'2018050366','tom','男','21','123456','n','no');
INSERT INTO t_stu VALUES (NULL,'2018050367','mary','女','21','123456','n','no');
INSERT INTO t_stu VALUES (NULL,'2018050368','lucy','女','22','123456','n','no');
INSERT INTO t_stu VALUES (NULL,'2018050369','jack','男','22','123456','n','no');
INSERT INTO t_stu VALUES (NULL,'2015050370','lily','女','23','123456','y','no');
INSERT INTO t_stu VALUES (NULL,'2016050371','smith','男','23','123456','y','no');
INSERT INTO t_stu VALUES (NULL,'2014050372','piter','男','21','123456','y','no');
SELECT * FROM t_stu;


-- 教学资料表
DROP TABLE IF EXISTS `t_doc`;  
CREATE TABLE `t_doc` (
  `docId` INT(11) NOT NULL AUTO_INCREMENT,  #资料编号
  `docName` VARCHAR(66) DEFAULT NULL,       #资料描述
  `docattachment` VARCHAR(50) DEFAULT NULL, #资料在目录中的真实名称
  `attachmentOldName` VARCHAR(55) DEFAULT NULL,#资料的原始名称
  `uploadTime` VARCHAR(50) DEFAULT NULL, #是否删除
  `del` VARCHAR(50) DEFAULT NULL,
  `tId` INT(11) default NULL ,
  PRIMARY KEY  (`docId`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

ALTER TABLE t_doc ADD CONSTRAINT FK_tea_doc FOREIGN KEY(tId) REFERENCES t_tea(teaId) ON DELETE CASCADE ON UPDATE CASCADE;

INSERT INTO `t_doc` VALUES ('1', 'Servlet-技术1', '111111111111.doc', 'Java学习笔记1.docx', '2019-10-19', 'no');
INSERT INTO `t_doc` VALUES ('2', 'GUI-技术', '/upload/1463664251344.doc', 'GUI.doc', '2019-10-19', 'no');
INSERT INTO `t_doc` VALUES ('3', 'Ajax-技术', '/upload/1463664282453.docx', 'SSH.docx', '2019-10-19', 'no');
INSERT INTO `t_doc` VALUES ('4', 'Spring-笔记', '/upload/1463664310222.java', 'Spring笔记.java', '2019-10-19', 'no');
INSERT INTO `t_doc` VALUES ('5', 'Velocity-技术', '/upload/1463664347977.doc', 'velocity.doc', '2019-10-19', 'no');

-- 教学视频表
DROP TABLE IF EXISTS `t_vedio`;
CREATE TABLE `t_vedio` ( 
  `vedioId` INT(11) NOT NULL AUTO_INCREMENT,   #视频编号
  `vedioName` VARCHAR(66) DEFAULT NULL,        #视频简介
  `vedioPro` VARCHAR(2000) DEFAULT NULL,       #视频描述
  `vedioAttachment` VARCHAR(55) DEFAULT NULL,  #视频在目录下的真实名称
  `attachmentOldName` VARCHAR(2000) DEFAULT NULL, #视频原始名称
  `uploadTime` VARCHAR(50) DEFAULT NULL,       #视频的上传时间
  `del` VARCHAR(50) DEFAULT NULL,              #视频是否删除
  `tId` INT(11) default NULL ,
  PRIMARY KEY  (`vedioId`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

ALTER TABLE t_vedio ADD CONSTRAINT FK_tea_vedio FOREIGN KEY(tId) REFERENCES t_tea(teaId) ON DELETE CASCADE ON UPDATE CASCADE;

INSERT  INTO `t_vedio`(`vedioId`,`vedioName`,`vedioPro`,`vedioAttachment`,`attachmentOldName`,`uploadTime`,`del`) VALUES 
(1,'jdk8新特性01','JDK8新特性1','11111111111111111111.mp4','JDK8新特性1.avi','2019-10-19','no'),
(2,'jdk8新特性02','JDK8新特性2','11111111111111111111.mp4','JDK8新特性2.avi','2019-10-20','no'),
(3,'jdk8新特性03','JDK8新特性3','11111111111111111111.mp4','JDK8新特性3.avi','2019-10-21','no'),
(4,'jdk8新特性04','JDK8新特性4','11111111111111111111.mp4','JDK8新特性4.avi','2019-10-22','no'),
(5,'jdk8新特性05','JDK8新特性5','11111111111111111111.mp4','JDK8新特性5.avi','2019-10-23','no'),
(6,'jdk8新特性06','JDK8新特性6','11111111111111111111.mp4','JDK8新特性6.avi','2019-10-24','no'),
(7,'jdk8新特性07','JDK8新特性7','11111111111111111111.mp4','JDK8新特性7.avi','2019-10-25','no'),
(8,'jdk8新特性08','JDK8新特性8','11111111111111111111.mp4','JDK8新特性8.avi','2019-10-26','no'),
(9,'jdk8新特性09','JDK8新特性9','11111111111111111111.mp4','JDK8新特性9.avi','2019-10-27','no'),
(10,'jdk8新特性10','JDK8新特性10','11111111111111111111.mp4','JDK8新特性10.avi','2019-10-28','no'),
(11,'jdk8新特性11','JDK8新特性11','11111111111111111111.mp4','JDK8新特性11.avi','2019-10-29','no'),
(12,'jdk8新特性12','JDK8新特性12','11111111111111111111.mp4','JDK8新特性12.avi','2019-10-30','no'),
(13,'jdk8新特性13','JDK8新特性13','11111111111111111111.mp4','JDK8新特性13.avi','2019-10-31','no'),
(14,'jdk8新特性14','JDK8新特性14','11111111111111111111.mp4','JDK8新特性14.avi','2019-10-01','no'),
(15,'jdk8新特性15','JDK8新特性15','11111111111111111111.mp4','JDK8新特性15.avi','2019-10-02','no'),
(16,'jdk8新特性16','JDK8新特性16','11111111111111111111.mp4','JDK8新特性16.avi','2019-10-03','no'),
(17,'jdk8新特性17','JDK8新特性17','11111111111111111111.mp4','JDK8新特性17.avi','2019-10-04','no'),
(18,'jdk8新特性18','JDK8新特性18','11111111111111111111.mp4','JDK8新特性18.avi','2019-10-05','no'),
(19,'jdk8新特性19','JDK8新特性19','11111111111111111111.mp4','JDK8新特性19.avi','2019-10-06','no'),
(20,'jdk8新特性20','JDK8新特性20','11111111111111111111.mp4','JDK8新特性20.avi','2019-10-07','no'),
(21,'jdk8新特性21','JDK8新特性21','11111111111111111111.mp4','JDK8新特性21.avi','2019-10-08','no'),
(22,'jdk8新特性22','JDK8新特性22','11111111111111111111.mp4','JDK8新特性22.avi','2019-10-09','no');

-- 试题表
DROP TABLE IF EXISTS `t_exam`;
CREATE TABLE `t_exam` (
  `examId` INT(11) NOT NULL AUTO_INCREMENT,   #试题编号
  `examName` VARCHAR(66) DEFAULT NULL,        #试题名称
  `attachment` VARCHAR(55) DEFAULT NULL,      #试题在目录下的真实名称
  `attachmentOldName` VARCHAR(50) DEFAULT NULL,#试题的原始名称
  `uploadTime` VARCHAR(50) DEFAULT NULL,      #试题的上传时间
  `del` VARCHAR(50) DEFAULT NULL,             #是否删除
  PRIMARY KEY  (`examId`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `t_exam` VALUES (NULL, 'java基础测试1', '1111111111111.doc', 'java基础测试题1.doc', '2019-10-19', 'no');
INSERT INTO `t_exam` VALUES (NULL, 'java基础测试2', '1111111111112.doc', 'java基础测试题2.doc', '2019-10-20', 'no');
INSERT INTO `t_exam` VALUES (NULL, 'java基础测试3', '1111111111113.doc', 'java基础测试题3.doc', '2019-10-21', 'no');
INSERT INTO `t_exam` VALUES (NULL, 'java基础测试4', '1111111111114.doc', 'java基础测试题4.doc', '2019-10-22', 'no');
INSERT INTO `t_exam` VALUES (NULL, 'java基础测试5', '1111111111115.doc', 'java基础测试题5.doc', '2019-10-23', 'no');
INSERT INTO `t_exam` VALUES (NULL, 'java基础测试6', '1111111111116.doc', 'java基础测试题6.doc', '2019-10-24', 'no');


-- 留言表
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
  `messageId` INT(11) NOT NULL AUTO_INCREMENT, #留言编号
  `content` VARCHAR(2000) DEFAULT NULL,        #留言内容
  `leaveWordTime` VARCHAR(55) DEFAULT NULL,     #留言时间
  `stuId` INT(11) DEFAULT NULL,           #留言学生的编号
  `replay` VARCHAR(2000) DEFAULT NULL,         #回复内容
  `replayTime` VARCHAR(50) DEFAULT NULL,       #回复时间
  PRIMARY KEY  (`messageId`)  
) ENGINE=INNODB DEFAULT CHARSET=utf8;

ALTER TABLE t_message ADD CONSTRAINT FK_ID FOREIGN KEY(stuId) REFERENCES t_stu(stuId) ON DELETE CASCADE ON UPDATE CASCADE;

INSERT INTO `t_message` VALUES (NULL, 'Javase中创建线程的方式1','2019-04-27','1','继承thread,实现Runnable接口，实现callback11','2019-04-27');
INSERT INTO `t_message` VALUES (NULL, 'Javase中创建线程的方式2','2019-04-26','2','继承thread,实现Runnable接口，实现callback22','2019-04-28');
INSERT INTO `t_message` VALUES (NULL, 'Javase中创建线程的方式3','2019-04-25','1','继承thread,实现Runnable接口，实现callback33','2019-04-29');
INSERT INTO `t_message` VALUES (NULL, 'Javase中创建线程的方式4','2019-04-24','2','继承thread,实现Runnable接口，实现callback44','2019-05-30');
INSERT INTO `t_message` VALUES (NULL, 'Javase中创建线程的方式5','2019-04-23','1','继承thread,实现Runnable接口，实现callback55','2019-05-01');
INSERT INTO `t_message` VALUES (NULL, 'Javase中创建线程的方式6','2019-04-22','3','继承thread,实现Runnable接口，实现callback66','2019-05-02');
INSERT INTO `t_message` VALUES (NULL, 'Javase中创建线程的方式7','2019-04-21','4','继承thread,实现Runnable接口，实现callback77','2019-05-03');
INSERT INTO `t_message` VALUES (NULL, 'Javase中创建线程的方式8','2019-04-21','5','继承thread,实现Runnable接口，实现callback77','2019-05-03');
INSERT INTO `t_message` VALUES (NULL, 'Javase中创建线程的方式9','2019-04-21','2','继承thread,实现Runnable接口，实现callback77','2019-05-02');
INSERT INTO `t_message` VALUES (NULL, 'Javase中创建线程的方式10','2019-04-21','1','继承thread,实现Runnable接口，实现callback77','2019-05-01');
INSERT INTO `t_message` VALUES (NULL, 'Javase中创建线程的方式11','2019-04-21','2','继承thread,实现Runnable接口，实现callback77','2019-05-03');
INSERT INTO `t_message` VALUES (NULL, 'Javase中创建线程的方式12','2019-04-21','1','继承thread,实现Runnable接口，实现callback77','2019-05-04');
INSERT INTO `t_message` VALUES (NULL, 'Javase中创建线程的方式13','2019-04-21','3','继承thread,实现Runnable接口，实现callback77','2019-05-05');
INSERT INTO `t_message` VALUES (NULL, 'Javase中创建线程的方式14','2019-04-21','3','继承thread,实现Runnable接口，实现callback77','2019-05-06');
INSERT INTO `t_message` VALUES (NULL, 'Javase中创建线程的方式15','2019-04-21','4','继承thread,实现Runnable接口，实现callback77','2019-05-07');
INSERT INTO `t_message` VALUES (NULL, 'Javase中创建线程的方式16','2019-04-21','1','继承thread,实现Runnable接口，实现callback77','2019-05-05');
INSERT INTO `t_message` VALUES (NULL, 'Javase中创建线程的方式17','2019-04-21','1','继承thread,实现Runnable接口，实现callback77','2019-05-06');
INSERT INTO `t_message` VALUES (NULL, 'Javase中创建线程的方式18','2019-04-21','6','继承thread,实现Runnable接口，实现callback77','2019-05-07');
INSERT INTO `t_message` VALUES (NULL, 'Javase中创建线程的方式19','2019-04-21','7','继承thread,实现Runnable接口，实现callback77','2019-05-04');
INSERT INTO `t_message` VALUES (NULL, 'Javase中创建线程的方式20','2019-04-21','2','继承thread,实现Runnable接口，实现callback77','2019-05-06');
INSERT INTO `t_message` VALUES (NULL, 'Javase中创建线程的方式21','2019-04-21','5','继承thread,实现Runnable接口，实现callback77','2019-05-08');
INSERT INTO `t_message` VALUES (NULL, 'Javase中创建线程的方式22','2019-04-21','5','继承thread,实现Runnable接口，实现callback77','2019-05-08');
INSERT INTO `t_message` VALUES (NULL, 'Javase中创建线程的方式23','2019-04-21','1','继承thread,实现Runnable接口，实现callback77','2019-05-09');

-- 课程表
DROP TABLE IF EXISTS t_course;
CREATE TABLE t_course(  
   cId INT(11) NOT NULL AUTO_INCREMENT ,  
   cNum VARCHAR(66) DEFAULT NULL UNIQUE, #课程编号
   cName VARCHAR(50),  
   tId INT(11) NOT NULL,  
   PRIMARY KEY  (cId)
); 
ALTER TABLE t_course ADD CONSTRAINT FK_course_tea FOREIGN KEY(tId) REFERENCES t_tea(teaId) ON DELETE CASCADE ON UPDATE CASCADE;

INSERT INTO t_course VALUES (1,'bx001','Java程序设计基础',2);  
INSERT INTO t_course VALUES (2,'bx002','计算机网络',3);  
INSERT INTO t_course VALUES (3,'bx003','网络安全技术',3);  
INSERT INTO t_course VALUES (4,'bx004','密码学原理',3);  
INSERT INTO t_course VALUES (5,'ts005','SQL SERVER 2008',3);  
INSERT INTO t_course VALUES (6,'ts006','C#',2);  
INSERT INTO t_course VALUES (7,'ts007','JavaScript',2);  
INSERT INTO t_course VALUES (8,'xx008','DIV+CSS',2);  
INSERT INTO t_course VALUES (9,'xx009','PHP',5);  
INSERT INTO t_course VALUES (10,'xx010','EJB3.0',5);  

-- 选课表
DROP TABLE IF EXISTS t_xuanke;
CREATE TABLE t_xuanke(  
   id INT(11) NOT NULL AUTO_INCREMENT, 
   cId INT(11) NOT NULL,   #课程id
   stuId INT(11) NOT NULL, #学生id
   PRIMARY KEY (id)
); 
ALTER TABLE t_xuanke ADD CONSTRAINT FK_xuanke_course FOREIGN KEY(cId) REFERENCES t_course(cId) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE t_xuanke ADD CONSTRAINT FK_xuanke_stu FOREIGN KEY(stuId) REFERENCES t_stu(stuId) ON DELETE CASCADE ON UPDATE CASCADE;


-- 随书配套源码表
DROP TABLE IF EXISTS `t_demo`;  
CREATE TABLE `t_demo` (
  `demoId` INT(11) NOT NULL AUTO_INCREMENT,  #资料编号
  `demoName` VARCHAR(66) DEFAULT NULL,       #资料描述
  `demoattachment` VARCHAR(50) DEFAULT NULL, #资料在目录中的真实名称
  `attachmentOldName` VARCHAR(55) DEFAULT NULL,#资料的原始名称
  `uploadTime` VARCHAR(50) DEFAULT NULL,   
  `del` VARCHAR(50) DEFAULT NULL,   #是否删除
  STATUS INT(11) DEFAULT 1,        #是否公开
  cId INT(11),
  PRIMARY KEY  (`demoId`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

ALTER TABLE t_demo ADD CONSTRAINT FK_course_demo FOREIGN KEY(cId) REFERENCES t_course(cId) ON DELETE CASCADE ON UPDATE CASCADE;

