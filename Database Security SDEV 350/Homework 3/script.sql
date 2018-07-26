/* Creates a new user named justin and grant all required permissions needed to create tables and execute dbms_rls commands. */

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
GRANT EXECUTE ON DBMS_RLS TO justin;

/* Connect as the user we have just created. */

CONNECT justin/dwts

/* Create a table named customers and fields required when adding data to this table. */

CREATE TABLE customers(
  id    NUMBER(6),
  first_name    VARCHAR2(20) NOT NULL,
  last_name     VARCHAR2(20) NOT NULL,
  address       VARCHAR2(60) NOT NULL,
  city          VARCHAR2(20) NOT NULL,
  zipcode NUMBER(6)    NOT NULL,
  phone VARCHAR2(20),
  CONSTRAINT customer_id_pk PRIMARY KEY(id));

commit;

/* Create a table named products and fields required when adding data to this table. */

CREATE TABLE products(
  product_id    NUMBER(6),
  name          VARCHAR2(20) NOT NULL,
  price         VARCHAR2(20) NOT NULL,
  description   VARCHAR2(60),
  CONSTRAINT product_id_pk PRIMARY KEY(product_id));

commit;

/* Populates our newly created customers table with customer data. */

INSERT INTO customers VALUES ('000001', 'Justin', 'Casteel', '1 Main Street', 'Centreville', '21617', '4104651457' );

INSERT INTO customers VALUES ('000002', 'Jerreak', 'Purnell', '54 Romancoke Drive', 'Grasonville', '21638', ' ' );

INSERT INTO customers VALUES ('000003', 'Augustus', 'Gloop', '100 Elm Street', 'Centreville', '21617', '' );

INSERT INTO customers VALUES ('000004', 'Orlando', 'Hogan', '87 Obon', 'Centreville', '21617', '4105874525' );

INSERT INTO customers VALUES ('000005', 'Julia', 'Hogan', '16 Poplar Drive', 'Chester', '21619', '4102148965' );

commit;


/* Populates our newly created products table with product data. */

INSERT INTO products VALUES ('000001', 'Football', '10.99', 'NFL certified football.');

INSERT INTO products VALUES ('000002', 'Baseball', '8.99', 'MLB certified baseball.');

INSERT INTO products VALUES ('000003', 'Mousepad', '15.99', 'SteelSeries XL Mousepad');

INSERT INTO products VALUES ('000004', 'Mouse', '59.99', 'Logitech G500s Mouse');

INSERT INTO products VALUES ('000005', 'HyperX Headphones', '8.99', 'HyperX Cloud II Headphones');

commit;

/* Creates a new function that will not allow us to see the customer with an id number of 1 when retrieving information from this table. */

create or replace function no_customers_num1 (
  p_schema in varchar2,
  p_object in varchar2)
  return varchar2
  as
  begin
  return 'id != 1';
  end;
  /

/* Creates a new function that will not allow us to see the product with a product number of 5 when retrieving information from this table. */

create or replace function no_product_num5 (
  p_schema in varchar2,
  p_object in varchar2)
  return varchar2
  as
  begin
  return 'product_id != 5';
  end;
  /

/* Creates a new policy that enforces the function above labeled no_customers_num1. */

begin
  dbms_rls.add_policy
  (object_schema => 'JUSTIN',
  object_name => 'CUSTOMERS',
  policy_name => 'customers_vpd1',
  policy_function => 'no_customers_num1');
  end;
  /

/* Creates a new policy that enforces the function above labeled no_product_num5. */

begin
  dbms_rls.add_policy
  (object_schema => 'JUSTIN',
  object_name => 'PRODUCTS',
  policy_name => 'products_vpd1',
  policy_function => 'no_product_num5');
  end;
  /

/* Select statements showing how the function and policies work to not show product id 5 and customer id 1 when retrieving data from our newely created tables. */

select * from customers;                                                                                
select id, first_name FROM customers;                                                             
select * from products;  