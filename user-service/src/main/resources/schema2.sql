DROP TABLE IF EXISTS USER;  
CREATE TABLE USER (  
userId INT AUTO_INCREMENT  PRIMARY KEY,  
userName VARCHAR(500) NOT NULL,  
password VARCHAR(500) NOT NULL,
profilePictureURL VARCHAR(500) NOT NULL,
createdDateTime Date NULL
);

Insert into USER values('2','AnushkhaThakur', 'AnushkhaThakur', 'C:\Pixogram _Workspace\user-service\user-service\src\main\resources\static\images\anu.jpg', NULL);