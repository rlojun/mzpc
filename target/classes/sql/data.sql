#DB
#기존 db가 존재했을 때만 사용
DROP DATABASE mzpc;

#MySQL 인코딩 설정을 했으면
create database mzpc;

#설정을 안 했으면
create database mzpc default character set utf8 collate UTF8_GENERAL_CI;

#---------------------------------------------------------------------
#table

#기존 table이 있을 때만 사용
DROP TABLE chat;
DROP TABLE mileage_info;
DROP TABLE time_purchase;
DROP TABLE orders;
DROP TABLE cart;
DROP TABLE favorites;
DROP TABLE food;
DROP TABLE category;
DROP TABLE online_member;
DROP TABLE members;
DROP TABLE times;
DROP TABLE admin;

#-----------------------------------------------------------------------
USE mzpc;

CREATE TABLE admin(
                      admin_id VARCHAR(20) NOT NULL COMMENT '아이디' PRIMARY KEY,
                      admin_pw VARCHAR(32) NOT NULL COMMENT '비밀번호',
                      admin_name VARCHAR(50) NOT NULL COMMENT '이름',
                      admin_ssn VARCHAR(14) NOT NULL COMMENT '주민등록번호',
                      admin_email VARCHAR(100) NOT NULL COMMENT '이메일',
                      phone_number VARCHAR(13) NOT NULL COMMENT '휴대폰 번호',
                      store_name VARCHAR(20) NOT NULL COMMENT '업체명'
) COMMENT '관리자';

CREATE TABLE members(
                        member_id VARCHAR(20) NOT NULL COMMENT '아이디' PRIMARY KEY,
                        member_pw VARCHAR(32) NOT NULL COMMENT '비밀번호',
                        member_name VARCHAR(50) NOT NULL COMMENT '이름',
                        member_ssn VARCHAR(100) NOT NULL COMMENT '주민등록번호',
                        member_email VARCHAR(100) NOT NULL COMMENT '이메일',
                        phone_number VARCHAR(13) NOT NULL COMMENT '휴대폰 번호',
                        address VARCHAR(255) COMMENT '주소',
                        remaining_time BIGINT NOT NULL COMMENT '잔여시간',
                        mileage INT NOT NULL COMMENT '남은 마일리지',
                        admin_id VARCHAR(20) NOT NULL COMMENT '관리자 아이디',
                        FOREIGN KEY (admin_id)
                            REFERENCES admin(admin_id) ON DELETE RESTRICT
) COMMENT '사용자';

CREATE TABLE times(
                      time_id BIGINT NOT NULL COMMENT 'index' auto_increment PRIMARY KEY,
                      time_name VARCHAR(15) NOT NULL COMMENT '상품명',
                      add_time BIGINT NOT NULL COMMENT '추가 시간',
                      time_price INT NOT NULL COMMENT '가격',
                      save BOOLEAN NOT NULL COMMENT '저장 유무',
                      admin_id VARCHAR(20) NOT NULL COMMENT '관리자 아이디',
                      FOREIGN KEY (admin_id)
                          REFERENCES admin(admin_id) ON DELETE RESTRICT
) COMMENT '시간';

CREATE TABLE online_member(
                              online_member_id BIGINT NOT NULL COMMENT 'index' auto_increment PRIMARY KEY,
                              start_time DATETIME COMMENT '시작 시간',
                              member_id VARCHAR(20) COMMENT '관리자 아이디',
                              FOREIGN KEY (member_id)
                                  REFERENCES members(member_id) ON DELETE RESTRICT
) COMMENT '온라인 중인 사용자';

CREATE TABLE category(
                         category_id BIGINT NOT NULL COMMENT 'index' auto_increment PRIMARY KEY,
                         category_name VARCHAR(30) NOT NULL COMMENT '카테고리명',
                         admin_id VARCHAR(20) NOT NULL COMMENT '관리자 아이디',
                         FOREIGN KEY (admin_id)
                             REFERENCES admin(admin_id) ON DELETE RESTRICT
)COMMENT '음식 카테고리';

CREATE TABLE food(
                     food_id BIGINT NOT NULL COMMENT 'index' AUTO_INCREMENT PRIMARY KEY,
                     food_name VARCHAR(30) NOT NULL COMMENT '음식 명',
                     picture VARCHAR(255) COMMENT '음식 사진',
                     food_price INT NOT NULL COMMENT '가격',
                     description VARCHAR(255) COMMENT '설명',
                     stock INT NOT NULL DEFAULT 0 COMMENT '재고',
                     topping BOOLEAN NOT NULL DEFAULT 0 COMMENT '토핑?',
                     admin_id VARCHAR(20) NOT NULL COMMENT '관리자 아이디',
                     category_id BIGINT NOT NULL COMMENT '카테고리 index',
                     FOREIGN KEY (admin_id)
                         REFERENCES admin(admin_id) ON DELETE RESTRICT,
                     FOREIGN KEY (category_id)
                         REFERENCES category(category_id) ON DELETE RESTRICT
) COMMENT '음식';

CREATE TABLE favorites(
                          favorites_id BIGINT NOT NULL COMMENT 'index' AUTO_INCREMENT PRIMARY KEY,
                          member_id VARCHAR(20) NOT NULL COMMENT '사용자 아이디',
                          food_id BIGINT NOT NULL COMMENT '음식 index',
                          FOREIGN KEY(member_id)
                              REFERENCES members(member_id) ON DELETE RESTRICT,
                          FOREIGN KEY(food_id)
                              REFERENCES food(food_id) ON DELETE RESTRICT
) COMMENT '즐겨찾기';

CREATE TABLE cart(
                     cart_id BIGINT NOT NULL COMMENT 'index' AUTO_INCREMENT PRIMARY KEY,
                     payments VARCHAR(15) NOT NULL COMMENT '결제 방식',
                     food_id BIGINT NOT NULL COMMENT '음식 index',
                     member_id VARCHAR(20) NOT NULL COMMENT '사용자 아이디',
                     FOREIGN KEY(food_id)
                         REFERENCES food(food_id),
                     FOREIGN KEY(member_id)
                         REFERENCES members(member_id)
) COMMENT '장바구니';

CREATE TABLE orders(
                       order_id BIGINT NOT NULL COMMENT 'index' AUTO_INCREMENT PRIMARY KEY,
                       cook_complete BOOLEAN DEFAULT 0 NOT NULL COMMENT '조리 여부',
                       purchase_status BOOLEAN DEFAULT 0 COMMENT '결제 여부',
                       cart_id BIGINT NOT NULL COMMENT '장바구니 index',
                       FOREIGN KEY (cart_id)
                           REFERENCES cart(cart_id)
) COMMENT '주문';

CREATE TABLE time_purchase(
                            time_purchase_id BIGINT NOT NULL COMMENT 'index' AUTO_INCREMENT PRIMARY KEY,
                             member_id VARCHAR(20) COMMENT '사용자 아이디',
                             time_id BIGINT COMMENT '시간 index',
                             FOREIGN KEY (member_id)
                                 REFERENCES members(member_id),
                             FOREIGN KEY (time_id)
                                 REFERENCES times(time_id)
) COMMENT '시간 추가 목록';

CREATE TABLE mileage_info(
                             mileage_id BIGINT NOT NULL COMMENT '마일리지 index' AUTO_INCREMENT PRIMARY KEY,
                             use_mileage INT COMMENT '사용한 마일리지',
                             save_mileage INT COMMENT '마일리지 적립',
                             mileage_date TIMESTAMP COMMENT '마일리지 적립 or 사용 날짜',
                             time_purchase_id BIGINT COMMENT '시간 추가 목록index',
                             FOREIGN KEY (time_purchase_id)
                                 REFERENCES time_purchase(time_purchase_id) ON DELETE RESTRICT
) COMMENT '마일리지 사용 및 적립 내역';

CREATE TABLE chat(
                     pc_num BIGINT NOT NULL COMMENT 'PC 자리' AUTO_INCREMENT PRIMARY KEY ,
                     chat_type VARCHAR(5) NOT NULL COMMENT '메시지 유형',
                     member_chat VARCHAR(255) COMMENT '사용자 채팅',
                     admin_chat VARCHAR(255) COMMENT '관리자 채팅',
                     chat_date BIGINT COMMENT '채팅한 날짜',
                     member_id VARCHAR(20) NOT NULL COMMENT '사용자 아이디',
                     FOREIGN KEY (member_id)
                         REFERENCES members(member_id) ON DELETE RESTRICT
) COMMENT '채팅';

COMMIT;
