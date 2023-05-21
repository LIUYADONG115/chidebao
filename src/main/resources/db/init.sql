
CREATE TABLE IF NOT EXISTS t_ordinary_order (id integer not null, contract_id varchar(255),store_name varchar(255), payment_amount double, payment_status integer,sign_status integer, primary key (id));

CREATE TABLE IF NOT EXISTS t_message (id integer not null, business_id varchar(255), content varchar(255), primary key (id));

INSERT INTO t_ordinary_order (id, contract_id,store_name, payment_amount, payment_status,sign_status) VALUES (1,'123','paiyipai', 100.0, 1,1);

INSERT INTO t_ordinary_order (id, contract_id,store_name, payment_amount, payment_status,sign_status) VALUES (2,'456','paiyipai', 100.0, 1,1);