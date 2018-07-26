/* a.2 Create a test user named SDEV350User with access to unlimited space on the User's tablespace */

CREATE USER SDEV350User IDENTIFIED BY sdev QUOTA UNLIMITED ON users;

CREATE USER SDEV350User2 IDENTIFIED BY sdev QUOTA UNLIMITED ON users;

commit;

/* b. Allow the SDEV350User to be able to create sessions, create any table, drop any table, create users, drop user, and update any table. */

GRANT CREATE SESSION, SELECT ANY TABLE, INSERT ANY TABLE, CREATE ANY TABLE, DROP ANY TABLE, CREATE USER, DROP USER, UPDATE ANY TABLE TO SDEV350User;

GRANT CREATE SESSION, SELECT ANY TABLE, INSERT ANY TABLE, CREATE ANY TABLE, DROP ANY TABLE, CREATE USER, DROP USER, UPDATE ANY TABLE TO SDEV350User2;

commit;

/* c. Create policies for each of the 5 possible privileges */

CREATE AUDIT POLICY create_any_table_policy
  PRIVILEGES CREATE ANY TABLE
  WHEN    'SYS_CONTEXT(''USERENV'', ''SESSION_USER'') = ''SDEV350USER'''
  EVALUATE PER SESSION;

AUDIT POLICY create_any_table_policy;

commit;

CREATE AUDIT POLICY drop_any_table_policy
  PRIVILEGES DROP ANY TABLE
  WHEN    'SYS_CONTEXT(''USERENV'', ''SESSION_USER'') = ''SDEV350USER'''
  EVALUATE PER SESSION;

AUDIT POLICY drop_any_table_policy;

commit;

CREATE AUDIT POLICY create_user_policy
  PRIVILEGES CREATE USER
  WHEN    'SYS_CONTEXT(''USERENV'', ''SESSION_USER'') = ''SDEV350USER'''
  EVALUATE PER SESSION;

AUDIT POLICY create_user_policy;

commit;

CREATE AUDIT POLICY drop_user_policy
  PRIVILEGES DROP USER
  WHEN    'SYS_CONTEXT(''USERENV'', ''SESSION_USER'') = ''SDEV350USER'''
  EVALUATE PER SESSION;

AUDIT POLICY drop_user_policy;

commit;

CREATE AUDIT POLICY update_any_table_policy
  PRIVILEGES UPDATE ANY TABLE
  WHEN    'SYS_CONTEXT(''USERENV'', ''SESSION_USER'') = ''SDEV350USER'''
  EVALUATE PER SESSION;

AUDIT POLICY update_any_table_policy;

commit;

connect sdev350user2/sdev

CREATE TABLE customers(
   id   	 NUMBER(6),
   first_name    VARCHAR2(20) NOT NULL,
   last_name     VARCHAR2(20) NOT NULL,
   address       VARCHAR2(60) NOT NULL,
   city          VARCHAR2(20) NOT NULL,
   zipcode	 NUMBER(6)    NOT NULL,
   phone	 VARCHAR2(20),
   CONSTRAINT customer_id_pk PRIMARY KEY(id));
commit;

/* INSERT DATA INTO THE CUSTOMERS TABLE */
INSERT INTO customers VALUES ('000001', 'Justin', 'Casteel', '1 Main Street', 'Centreville', '21617', '4104651457' );

connect sdev350user/sdev

/* d. Create test scenarios (e.g. SQL scripts that you can run) that will demonstrate the audit of the
specific privileges is taking place. */

CREATE TABLE products(
   product_id    NUMBER(6),
   name          VARCHAR2(20) NOT NULL,
   price         VARCHAR2(20) NOT NULL,
   description   VARCHAR2(60),
   CONSTRAINT product_id_pk PRIMARY KEY(product_id));
commit;

/* UPDATE CUSTOMERS RECORD */
UPDATE sdev350user2.customers sdev2 set sdev2.first_name = 'Hopes' WHERE sdev2.id = 000001;

commit;

/* DROP THE CUSTOMERS TABLE */
DROP TABLE sdev350user2.customers;

commit;

/* CREATE A NEW USER */
CREATE USER justin
IDENTIFIED BY dwts
DEFAULT TABLESPACE USERS
QUOTA 10M ON USERS;

commit;

/* DROP A USER */
DROP USER justin;

commit;