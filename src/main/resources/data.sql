ALTER TABLE customer AUTO_INCREMENT = 1;
ALTER TABLE product AUTO_INCREMENT = 1;
ALTER TABLE basket AUTO_INCREMENT = 1;
ALTER TABLE basket_items AUTO_INCREMENT = 1;
ALTER TABLE orders AUTO_INCREMENT = 1;

INSERT INTO allra.customer
(created_at, name, updated_at) 
VALUES(current_timestamp(6), '홍길동', current_timestamp(6));

INSERT INTO allra.product
(stock, created_at, price, updated_at, description, name)
VALUES(100, current_timestamp(6), 200, current_timestamp(6), '모자', '모자');

INSERT INTO allra.product
(stock, created_at, price, updated_at, description, name)
VALUES(200, current_timestamp(6), 500, current_timestamp(6), '바지', '바지');

INSERT INTO allra.product
(stock, created_at, price, updated_at, description, name)
VALUES(50, current_timestamp(6), 700, current_timestamp(6), '양복', '양복');

INSERT INTO allra.product
(stock, created_at, price, updated_at, description, name)
VALUES(300, current_timestamp(6), 100, current_timestamp(6), '양말', '양말');

INSERT INTO allra.product
(stock, created_at, price, updated_at, description, name)
VALUES(700, current_timestamp(6), 300, current_timestamp(6), '셔츠', '셔츠');

INSERT INTO allra.basket
(c_id, created_at, updated_at, sold)
VALUES(1, current_timestamp(6), current_timestamp(6), 1);

INSERT INTO allra.basket_items (b_id,created_at,p_id,updated_at,pcount) VALUES
	 (1,current_timestamp(6),1,current_timestamp(6),1),
	 (1,current_timestamp(6),2,current_timestamp(6),1),
	 (1,current_timestamp(6),3,current_timestamp(6),1),
	 (1,current_timestamp(6),4,current_timestamp(6),1),
	 (1,current_timestamp(6),5,current_timestamp(6),1);

INSERT INTO allra.orders (b_id,created_at,updated_at) VALUES
	 (1,current_timestamp(6),current_timestamp(6));

