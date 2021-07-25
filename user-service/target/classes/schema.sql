DROP TABLE IF EXISTS USER;  
CREATE TABLE USER (  
user_id INT AUTO_INCREMENT  PRIMARY KEY,  
user_name VARCHAR(500) NOT NULL,  
password VARCHAR(500) NOT NULL,
confirmed VARCHAR(500) NOT NULL,
profile_PictureURL VARCHAR(500) NOT NULL,
createdDateTime Date NULL
);

Insert into USER values('2','anushkhathakur', 'anushkhathakur', 'anushkhathakur','C:\Pixogram _Workspace\user-service\user-service\src\main\resources\static\images\anu.jpg', NULL);