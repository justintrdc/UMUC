/*
Creates a new user named justin and gives permissions required to add/drop/insert/update any table needed.
*/

CREATE USER justin
IDENTIFIED BY dwts
DEFAULT TABLESPACE USERS
QUOTA 10M ON USERS;

GRANT CREATE SESSION TO justin;
GRANT resource to justin;
GRANT CREATE TABLE to justin;
GRANT UNLIMITED TABLESPACE to justin;
GRANT SELECT ANY TABLE to justin;
GRANT UPDATE ANY TABLE to justin;
GRANT INSERT ANY TABLE to justin;
GRANT DROP ANY TABLE to justin;
GRANT CREATE VIEW TO justin;

CONNECT justin/dwts

/*
Creates new tables with 4 entities - customers, products, payment, and orders with attributes assigned to each with primary and foreign keys.
*/

CREATE TABLE customers(
   id   	 NUMBER(6),
   first_name    VARCHAR2(20) NOT NULL,
   last_name     VARCHAR2(20) NOT NULL,
   address       VARCHAR2(60) NOT NULL,
   city          VARCHAR2(20) NOT NULL,
   zipcode	 NUMBER(6)    NOT NULL,
   phone	 VARCHAR2(20),
   CONSTRAINT customer_id_pk PRIMARY KEY(id));

CREATE TABLE products(
   product_id    NUMBER(6),
   name          VARCHAR2(20) NOT NULL,
   price         VARCHAR2(20) NOT NULL,
   description   VARCHAR2(60),
   CONSTRAINT product_id_pk PRIMARY KEY(product_id));

CREATE TABLE payment(
   payment_id         NUMBER(6),
   customer_id        NUMBER(6),
   amount    	      VARCHAR2(20) NOT NULL,
   transaction_id     VARCHAR2(20) NOT NULL,
   date_paid          VARCHAR2(60) NOT NULL,
   CONSTRAINT payment_id_pk PRIMARY KEY(payment_id),
   CONSTRAINT customer_id_fk FOREIGN KEY (customer_id)REFERENCES customers (id));

CREATE TABLE orders(
   order_id       NUMBER(6),
   product_id     NUMBER(6),
   customer_id    NUMBER(6),
   quantity       NUMBER(8,2) NOT NULL,
   tax     	  NUMBER(8,2) NOT NULL,
   total_cost     NUMBER(8,2) NOT NULL,
   ship_date      NUMBER(8,2) NOT NULL,
   CONSTRAINT order_id_pk PRIMARY KEY(order_id),
   CONSTRAINT product_id_fk FOREIGN KEY (product_id)REFERENCES products (product_id),
   CONSTRAINT check_quantity CHECK(quantity > 0),
   CONSTRAINT check_total CHECK(total_cost > 0));

--commit
commit;

/*
Adds a comment to each new table telling the user what information each particular table is holding.
*/

COMMENT ON TABLE customers IS 'This is a table that contains the customers demographic information.';

COMMENT ON TABLE products IS 'This is a table that shows the selected products information.';

COMMENT ON TABLE payment IS 'This is a table that shows the information regarding how a transaction was carried out.';

COMMENT ON TABLE orders IS 'This is a table displaying the chosen products, quantities and costs.';

--commit
commit;

/*
Displays each table with the attribute name, null value, and data type.
*/

desc customers;
desc payment;
desc orders;
desc products;

/*
Displays the comments that were added above for each individual table.
*/

select * from user_tab_comments;

/*
Populates the newly created tables with 20 records total.
*/

INSERT INTO customers VALUES ('000001', 'Justin', 'Casteel', '1 Main Street', 'Centreville', '21617', '4104651457' );
INSERT INTO customers VALUES ('000002', 'Jerreak', 'Purnell', '54 Romancoke Drive', 'Grasonville', '21638', ' ' );
INSERT INTO customers VALUES ('000003', 'Augustus', 'Gloop', '100 Elm Street', 'Centreville', '21617', '' );
INSERT INTO customers VALUES ('000004', 'Orlando', 'Hogan', '87 Obon', 'Centreville', '21617', '4105874525' );
INSERT INTO customers VALUES ('000005', 'Julia', 'Hogan', '16 Poplar Drive', 'Chester', '21619', '4102148965' );

INSERT INTO products VALUES ('000001', 'Football', '10.99', 'NFL certified football.’);
INSERT INTO products VALUES ('000002', 'Baseball', '8.99', 'MLB certified baseball.’);
INSERT INTO products VALUES ('000003', 'Mousepad', '15.99', 'SteelSeries XL Mousepad’);
INSERT INTO products VALUES ('000004', 'Mouse', '59.99', 'Logitech G500s Mouse’);
INSERT INTO products VALUES ('000005', 'HyperX Headphones', '8.99', 'HyperX Cloud II Headphones’);

INSERT INTO payment VALUES ('000001', '000001', '125.97', '000001', 'August 18 2017');
INSERT INTO payment VALUES ('000002', '000002', '61.96', '000002', 'August 2 2017');
INSERT INTO payment VALUES ('000003', '000003', '69.93', '000003', 'July 15 2017');
INSERT INTO payment VALUES ('000004', '000004', '601.89', '000004', 'July 30 2017');
INSERT INTO payment VALUES ('000005', '000005', '249.95', '000005', 'October 4 2017');

INSERT INTO orders VALUES ('000001', '000005', '000001', '2', '5.99', '125.97', '082817');
INSERT INTO orders VALUES ('000002', '000003', '000002', '3', '13.99', '61.96', '083017');
INSERT INTO orders VALUES ('000003', '000002', '000003', '6', '15.99', '69.93', '093017');
INSERT INTO orders VALUES ('000004', '000004', '000004', '10', '1.99', '601.89', '101717');
INSERT INTO orders VALUES ('000005', '000004', '000005', '4', '9.99', '249.95', '101817');

/*
Join clause used to combine records from the customers and orders tables.
*/

SELECT id, first_name, last_name, product_id, quantity
   FROM CUSTOMERS, ORDERS
   WHERE CUSTOMERS.id = ORDERS.customer_id;

--commit
commit;

/*
Creating a database view
*/

CREATE VIEW customer_order AS
SELECT id, order_id, product_id, customer_id 
FROM customers, orders
WHERE id > 1;

--commit
commit;

/*
Shows the database view so only customers with an 'id' that is greater than 1 will be shown.
*/

SELECT * FROM customer_order;

/*
6 Select statements
*/

SELECT order_id, total_cost FROM orders;
SELECT * FROM customers WHERE ID = 2;
SELECT quantity FROM orders WHERE quantity > 3;
SELECT * FROM customer_order WHERE ID = 3;
SELECT * FROM customer_order WHERE product_id < 4;
SELECT * FROM customer_order WHERE product_id = 5 AND order_id = 1;