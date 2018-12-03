/*Database scripts for holiday hotel booking (Sunil Cyriac 29003164)*/

/*username=sunil
password=sunil*/

/* Create admin table */
CREATE TABLE  admin (
  id INT  PRIMARY KEY NOT NULL
GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  status varchar(20) NOT NULL DEFAULT 'active'
);

/* insert into admin */
INSERT INTO admin (username,password,status) VALUES ('sunil','sunil','active');
INSERT INTO admin (username,password,status) VALUES ('logan','logan','active');
INSERT INTO admin (username,password,status) VALUES ('myname','myname','active');
INSERT INTO admin (username,password,status) VALUES ('admin','admin','active');

/* Create customers table */
CREATE TABLE  customers (
  customerid INT   PRIMARY KEY NOT NULL
  GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
  firstname VARCHAR(100) NOT NULL,
  lastname VARCHAR(100) NOT NULL,
  email VARCHAR(255) NOT NULL,
  phone VARCHAR (50) NOT NULL,
  username VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL
);

/* insert into customers */
INSERT INTO customers (firstname,lastname,email,phone,username,password) VALUES  ('Sunil','Cyriac','sunilcyriac07@gmail.com','0401398095','sunil07','sunil07');
INSERT INTO customers (firstname,lastname,email,phone,username,password) VALUES  ('Benjamin','Franklin','benjamin@gmail.com','04123456785','benjamin','benjamin');
INSERT INTO customers (firstname,lastname,email,phone,username,password) VALUES  ('Naruto','Uzumaki','naruto@gmail.com','0412784589','naruto','shippuden');
INSERT INTO customers (firstname,lastname,email,phone,username,password) VALUES  ('Sasuke','Uchiha','sasuke@gmail.com','0412457896','sasuke','sasuke');



/* Create room_types table */
CREATE TABLE room_types (
  type_id INT PRIMARY KEY NOT NULL
  GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
  roomtype VARCHAR(80) NOT NULL,
  roomdetail VARCHAR(150) NOT NULL,
  price DOUBLE NOT NULL
);

/* insert into room_types */
INSERT INTO room_types (roomtype,roomdetail,price) VALUES ('Queen','A room with a queen-sized bed. May be occupied by one or more people',300.00);
INSERT INTO room_types (roomtype,roomdetail,price) VALUES ('Master Suite','A parlour or living room connected to one or more bedrooms',600.00);
INSERT INTO room_types (roomtype,roomdetail,price) VALUES ('Double-double','A room with two double (or perhaps queen) beds. May be occupied by one or more people',800.00);


/* Create room_status table */
CREATE TABLE room_status (
  room_status_id INT   PRIMARY KEY NOT NULL
  GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
  status VARCHAR(50) NOT NULL
);

/* insert into room_status */
INSERT INTO room_status (status)  VALUES ('Available');
INSERT INTO room_status (status) VALUES ('Booked');
INSERT INTO room_status (status) VALUES ('Under Maintainance');
INSERT INTO room_status (status) VALUES ('N/A');



/* Create rooms table */
CREATE TABLE rooms (
  roomid INT  PRIMARY KEY NOT NULL
  GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
  type_id INT NOT NULL,
  roomno INT NOT NULL,
  floorno INT NOT NULL,
  room_status_id INT NOT NULL,
  
  FOREIGN KEY (type_id) REFERENCES room_types (type_id) ON DELETE CASCADE,
  FOREIGN KEY (room_status_id) REFERENCES room_status (room_status_id) ON DELETE CASCADE
);


/* insert into rooms */
INSERT INTO rooms (type_id,roomno,floorno,room_status_id) VALUES (3,1111,1,1);
INSERT INTO rooms (type_id,roomno,floorno,room_status_id) VALUES (3,1112,1,1);
INSERT INTO rooms (type_id,roomno,floorno,room_status_id) VALUES (3,1113,1,1);
INSERT INTO rooms (type_id,roomno,floorno,room_status_id) VALUES (3,1114,1,1);
INSERT INTO rooms (type_id,roomno,floorno,room_status_id) VALUES (3,1115,1,1);
INSERT INTO rooms (type_id,roomno,floorno,room_status_id) VALUES (1,2221,2,1);
INSERT INTO rooms (type_id,roomno,floorno,room_status_id) VALUES (1,2222,2,1);
INSERT INTO rooms (type_id,roomno,floorno,room_status_id) VALUES (1,2223,2,1);
INSERT INTO rooms (type_id,roomno,floorno,room_status_id) VALUES (1,2224,2,1);
INSERT INTO rooms (type_id,roomno,floorno,room_status_id) VALUES (1,2225,2,1);
INSERT INTO rooms (type_id,roomno,floorno,room_status_id) VALUES (2,3331,3,1);
INSERT INTO rooms (type_id,roomno,floorno,room_status_id) VALUES (2,3332,3,1);
INSERT INTO rooms (type_id,roomno,floorno,room_status_id) VALUES (2,3333,3,1);
INSERT INTO rooms (type_id,roomno,floorno,room_status_id) VALUES (2,3334,3,1);
INSERT INTO rooms (type_id,roomno,floorno,room_status_id) VALUES (2,3335,3,1);



/* Create reservation table */
CREATE TABLE reservation (
  reservationid INT  PRIMARY KEY NOT NULL
  GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
  customerid INT NOT NULL,
  roomid INT NOT NULL,
  startdate DATE NOT NULL,
  enddate DATE NOT NULL,
  specialrequest VARCHAR(255),
  
  FOREIGN KEY (customerid) REFERENCES customers (customerid) ON DELETE CASCADE,
  FOREIGN KEY (roomid) REFERENCES rooms (roomid) ON DELETE CASCADE
);


/* insert into reservation */
INSERT INTO reservation (customerid,roomid,startdate,enddate,specialrequest)
VALUES (1,1,'2018-05-01','2018-05-10','Food for 4 people');
INSERT INTO reservation (customerid,roomid,startdate,enddate,specialrequest)
VALUES (1,2,'2018-06-01','2018-06-10','Kids care');
INSERT INTO reservation (customerid,roomid,startdate,enddate,specialrequest)
VALUES (3,5,'2018-10-01','2018-10-11','No special request');



/* Create transactions table */
CREATE TABLE transactions(
  transactionid INT  PRIMARY KEY NOT NULL
  GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
  customerid INT NOT NULL,
  paymenttype varchar(30) NOT NULL DEFAULT 'credit',
  price DOUBLE NOT NULL,
  cardholder VARCHAR(255),
  transactionname VARCHAR(255),
  cardno VARCHAR(255),
  expirydate VARCHAR(255),
  
  FOREIGN KEY (customerid) REFERENCES customers (customerid) ON DELETE CASCADE
);

/* insert into transactions */
INSERT INTO transactions (customerid,paymenttype,price,cardholder,transactionname,cardno,expirydate)
VALUES (1,'credit',600,'Sunil Cyriac','Holiday','4521-5456-2548-8961','2021/02');
INSERT INTO transactions (customerid,paymenttype,price,cardholder,transactionname,cardno,expirydate)
VALUES (3,'credit',800,'Naruto Uzumaki','Tour','4123-4587-7895-5478','2020/02');

INSERT INTO transactions (customerid,paymenttype,price,cardholder,transactionname,cardno,expirydate)
VALUES (1,'debit',1600,'Sunil Cyriac','Holiday','4527-4578-5689-1456','2021/02');

INSERT INTO transactions (customerid,paymenttype,price,cardholder,transactionname,cardno,expirydate)
VALUES (1,'debit',300,'Sunil Cyriac','Tour','4789-4789-1245-4568','2020/04');


INSERT INTO transactions (customerid,paymenttype,price,cardholder,transactionname,cardno,expirydate)
VALUES (1,'cash',1200,'Sunil Cyriac','Holiday','4789-5689-2145-6587','2022/01');






