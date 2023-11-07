#ADMIN
INSERT INTO admin(admin_id,admin_pw,admin_name,admin_ssn,admin_email,phone_number,store_name) VALUES 
('hojun','q123','최호준','990117-1432223','hojun@hanmail.net','010-3222-4452','호준PC');

#---------------------------------------------------------------------------------------
#MEMBERS
INSERT INTO members(member_id,member_pw,member_name,member_ssn,phone_number,member_email,address, remaining_time, mileage,admin_id) VALUES
('cjun','w123','최준','880421-1256232','010-2452-3321','cjun@naver.com','경기도 고양시 덕양구 강촌로 191',0,0,'hojun');

INSERT INTO members(member_id,member_pw,member_name,member_ssn,phone_number,member_email, remaining_time, mileage,admin_id) VALUES
('base','vvs1234','강희준','960203-1423462','010-8291-4312','base1234@gmail.com',3600000+1200000,20,'hojun');

INSERT INTO members(member_id,member_pw,member_name,member_ssn,phone_number,member_email, remaining_time, mileage,admin_id) VALUES
('sudo','s1234','이수두','960203-2642321','010-4561-2212','sudo@gmail.com',3600000+100000,0,'hojun');


#-------------------------------------------------------------------------------------------
#Times

INSERT INTO times(time_id,time_name,add_time,time_price,save,admin_id)VALUES
(NULL,'1시간',3600000,1200,FALSE,'hojun');

INSERT INTO times(time_id,time_name,add_time,time_price,save,admin_id)VALUES
(NULL,'5시간',3600000*5,5500,TRUE,'hojun');

#--------------------------------------------------------------------------------------------------
#TIME_PAYMENT

INSERT INTO time_purchase(time_purchase_id,member_id,time_id) VALUES
(NULL,'cjun',2);

#-----------------------------------------------------------------------------------
#MILEAGE_INFO 

INSERT INTO mileage_info(mileage_id, save_mileage, mileage_date, time_purchase_id) VALUES
(NULL, 20, NOW(), 1);


INSERT INTO mileage_info(mileage_id, use_mileage, mileage_date, time_purchase_id) VALUES
(NULL,20, NOW(), 1);

#---------------------------------------------------------------------------------------
#CATEGORY


INSERT INTO category VALUES
(NULL,'라면','hojun');

INSERT INTO category VALUES
(NULL,'스낵','hojun');

INSERT INTO category VALUES
(NULL,'음료','hojun');


#---------------------------------------------------------------------------------------
#FOOD
INSERT INTO food VALUES
(NULL,'진라면',NULL , 1200, NULL , 100, 0, 'hojun',1);

INSERT INTO food VALUES
(NULL,'짜파게티',NULL , 1300, NULL , 120, 0, 'hojun',1);

INSERT INTO food(food_id, food_name, food_price, admin_id, category_id)VALUES
(NULL,'홈런볼', 900, 'hojun',2);







