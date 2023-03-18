
CREATE TABLE IF NOT EXISTS t_ordinary_order (id integer not null, order_id varchar(255),store_name varchar(255), payment_amount double, payment_status integer,sign_status integer, primary key (id));

CREATE TABLE IF NOT EXISTS t_message (id integer not null, business_id varchar(255), content varchar(255), primary key (id));

INSERT INTO t_ordinary_order (id, order_id,store_name, payment_amount, payment_status,sign_status) VALUES (1,'326118','KFC', 35.42, 1,1);