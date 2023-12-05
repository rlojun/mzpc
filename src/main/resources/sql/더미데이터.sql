#STORE
INSERT INTO store(store_idx,store_code,store_name) VALUES (1,152,'호준PC');
INSERT INTO store(store_idx,store_code,store_name) VALUES (2,153,'태섭PC');
INSERT INTO store(store_idx,store_code,store_name) VALUES (3,154,'원식PC');
INSERT INTO store(store_idx,store_code,store_name) VALUES (4,155,'승준PC');
#----------------------------------------------------------------------------------------


#ADMIN
INSERT INTO admin(admin_idx,admin_code,admin_id,admin_pw,admin_name,admin_ssn,admin_email,phone_number,store_idx) VALUES
(1, 211,'hojun','q123','최호준','990117-1432223','hojun@hanmail.net','010-3222-4452',1);

#---------------------------------------------------------------------------------------
#MEMBERS
INSERT INTO members(member_idx,member_code,member_id,member_pw,member_name,member_ssn,phone_number,member_email,address, remaining_time, mileage, store_idx) VALUES
(1, 312,'cjun','w123','최준','880421-1256232','010-2452-3321','cjun@naver.com','경기도 고양시 덕양구 강촌로 191',0,0,1);

INSERT INTO members(member_idx,member_code,member_id,member_pw,member_name,member_ssn,phone_number,member_email, remaining_time, mileage, store_idx) VALUES
(2, 313,'base','vvs1234','강희준','960203-1423462','010-8291-4312','base1234@gmail.com','02:00:00',20,1);

INSERT INTO members(member_idx,member_code,member_id,member_pw,member_name,member_ssn,phone_number,member_email, remaining_time, mileage, store_idx) VALUES
(3, 314,'sudo','s1234','이수두','960203-2642321','010-4561-2212','sudo@gmail.com','03:00:00',40,1);


#-------------------------------------------------------------------------------------------
#Times

INSERT INTO times(time_idx,time_code,time_name,add_time,time_price,save,store_idx)VALUES
(NULL,413,'1시간','1:00:00',1200,FALSE,1);

INSERT INTO times(time_idx, time_code,time_name,add_time,time_price,save,store_idx)VALUES
    (NULL,414,'5시간','05:00:00',5500,TRUE,1);

INSERT INTO times(time_idx, time_code,time_name,add_time,time_price,save,store_idx)VALUES
    (NULL,415,'10시간','10:00:00',10000,TRUE,1);

INSERT INTO times(time_idx, time_code,time_name,add_time,time_price,save,store_idx)VALUES
    (NULL,416,'20시간','20:00:00',18000,TRUE,1);

#---------------------------------------------------------------------------------------
#CATEGORY


INSERT INTO category VALUES
(NULL,711,'라면',1);

INSERT INTO category VALUES
(NULL,712,'스낵',1);

INSERT INTO category VALUES
(NULL,713,'음료',1);


#---------------------------------------------------------------------------------------
#FOOD
INSERT INTO food(food_idx, food_code, food_name, food_price,topping,stock ,category_idx) VALUES
(NULL,822, '진라면', 1200, false, 55,1);

INSERT INTO food(food_idx, food_code, food_name, food_price,topping,stock,category_idx) VALUES
(NULL,821, '짜파게티',1300, false,221 ,1);

INSERT INTO food(food_idx, food_code, food_name, food_price,topping, stock,category_idx)VALUES
(NULL,823, '홈런볼', 900,false, 55,2);







