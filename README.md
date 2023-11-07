# final_project_PC-Service
**Team : 5분 남았습니다.**

스프링부트 - PC방 서비스 프로젝트

## 🧑‍💻프로젝트 소개
PC방에서 사용하는 기능을 사용하는 사이트입니다.

## 👌개발 기간
- 2023.10.16 ~ 2023.12.21

## 🤼멤버 구성 및 역할
- 명원식 : 프로젝트 기능 브레인스토밍, WBS, Entity 생성
- 김태섭 : 프로젝트 기능 브레인스토밍, Flow Chart, ERD, MiniProject 1 발표, 화면 설계, DB 이미지 획득
- 이승준 : 프로젝트 기능 브레인스토밍, DB 이미지 획득, 화면 설계
- 최호준 : 프로젝트 기능 브레인스토밍, 초기 ReadMe 작성, 화면 설계, MiniProject 1 발표

### WBS
![image](https://github.com/rlojun/final_project_bootcamp/assets/137598528/faeb256c-1f24-414b-9f29-9cdb57492a31)
시트: https://docs.google.com/spreadsheets/d/1RvZuKHDLNujCayDX9yQ5plQV7zA68mWr1TVSUbf1otA/edit#gid=1431469973

## ⚙️기술 스택
- Language : Java 11
- IDE : IntelliJ
- Framework : Springboot(2.7.16)
- Database : MySQL
- ORM : Spring JPA
- Template : Thymeleaf
- Open Source Platform : Docker
- Cloud Service : AWS
## 🌱 ERD


<img width="1025" alt="스크린샷 2023-10-18 오후 3 08 11" src="https://github.com/rlojun/final_project_bootcamp/assets/137260250/94ab20dc-bb05-4055-8bcf-0ff17c4584ae">


- user Table : 유저의 회원정보 관련 테이블
- onlineuser Table : 온라인한 유저의 사용시간 파악을 위한 테이블
- qna Table : Q&A 관련 테이블
- admin Table : 관리자 정보 테이블
- store Table : 매장 정보 테이블
- time Table : 시간 상품 테이블
- timepayment Table : 유저가 구매한 시간 상품 정보를 알기 위한 테이블
- mileage Table : 마일리지 테이블
- usemileage Table : 마일리지 적립, 사용을 파악하기 위한 테이블
- food Table : 음식 상품 테이블
- cart Table : 장바구니 테이블
- order Table : 결제 완료된 주문 테이블
- favorites Table : 즐겨찾기 기능을 위한 테이블
- category Table : 음식 분류를 위한 카테고리 테이블


<img width="497" alt="스크린샷 2023-10-18 오후 3 18 59" src="https://github.com/rlojun/final_project_bootcamp/assets/137260250/e43a57b8-f0eb-49fe-bfe0-2dc58b3c27ec">



## 💡주요 기능

> #### 🎢Flow Chart
- 로그인 페이지
<img width="1079" alt="image" src="https://github.com/rlojun/final_project_bootcamp/assets/137135122/0c084f01-c9b1-4eb6-bf9c-6147f0988bed">

- 관리자 페이지
<img width="1078" alt="image" src="https://github.com/rlojun/final_project_bootcamp/assets/137135122/ca8f6f08-4f14-4c14-b77d-3ffa50e17095">

- 사용자 페이지
<img width="1041" alt="image" src="https://github.com/rlojun/final_project_bootcamp/assets/137135122/8999ce45-0f43-4cf1-82ea-6ae41b2fc20f">

---
> #### 🖥️UI(화면 설계서)

1. ##### 로그인
- 로그인 화면
<img width="705" alt="image" src="https://github.com/rlojun/final_project_bootcamp/assets/137135122/39d80adf-21a6-49ae-a5dd-f3604fa1556e">

- 아이디/비밀번호 찾기
<img width="705" alt="image" src="https://github.com/rlojun/final_project_bootcamp/assets/137135122/809938a2-2393-4e1f-9329-11994b0b0b6e">

- 회원 가입 페이지
<img width="705" alt="image" src="https://github.com/rlojun/final_project_bootcamp/assets/137135122/f856b166-f2e1-4b8e-9d73-671a6701cf01">

- 시간 충전 페이지
<img width="555" alt="image" src="https://github.com/rlojun/final_project_bootcamp/assets/137135122/e66cbbd1-3226-4434-bed1-da2cfee112da">

2. ##### 관리자

- 음식 상품 목록
<img width="742" alt="image" src="https://github.com/rlojun/final_project_bootcamp/assets/137135122/75c06a2a-3f4a-4afc-89ce-b23db42c7c16">

- 음식 상세 화면
<img width="781" alt="image" src="https://github.com/rlojun/final_project_bootcamp/assets/137135122/bcd785e4-82bc-429b-a238-e20534aa1f72">

- 음식 수정 화면
<img width="781" alt="image" src="https://github.com/rlojun/final_project_bootcamp/assets/137135122/83ae92c5-a0a7-4a03-ae12-d0569bf7a29c">

- 음식 추가 화면
<img width="648" alt="image" src="https://github.com/rlojun/final_project_bootcamp/assets/137135122/992e3a61-154e-4c79-b764-5e75b46d5ce3">

- 시간 상품 목록
<img width="785" alt="image" src="https://github.com/rlojun/final_project_bootcamp/assets/137135122/7d64e497-f92f-478a-a056-5d5117aea97c">

- 시간 상품 상세보기
<img width="827" alt="image" src="https://github.com/rlojun/final_project_bootcamp/assets/137135122/1260e5d8-4e8d-4752-8cd7-d9b4ac0743b6">

- 시간 상품 수정
<img width="694" alt="image" src="https://github.com/rlojun/final_project_bootcamp/assets/137135122/ded54cc4-8ebf-43d0-87aa-994b1fd9d34a">

- 시간 상품 등록
<img width="693" alt="image" src="https://github.com/rlojun/final_project_bootcamp/assets/137135122/379bd10d-0e0c-435b-8ca6-f9379ad85feb">

- 주문 목록
<img width="613" alt="image" src="https://github.com/rlojun/final_project_bootcamp/assets/137135122/215bc975-6b1a-45af-816e-cd05df2c1978">

- 사용자가 주문한 목록
<img width="788" alt="image" src="https://github.com/rlojun/final_project_bootcamp/assets/137135122/0b3ebe31-05fc-406a-94dc-f2abf42191af">

- 사용자 문의 화면
<img width="757" alt="image" src="https://github.com/rlojun/final_project_bootcamp/assets/137135122/ae43b4ac-7293-4e4a-84a2-10326f1fd0ca">

3. ##### 사용자
- 먹거리 주문 화면
<img width="799" alt="image" src="https://github.com/rlojun/final_project_bootcamp/assets/137135122/96037aef-2913-497c-b929-c5e178b43c39">

- 토핑 선택 창
<img width="663" alt="image" src="https://github.com/rlojun/final_project_bootcamp/assets/137135122/60dc0a6a-fa87-4b2f-a5fa-ac0c97fa4b67">

- 먹거리 주문, 결제
<img width="672" alt="image" src="https://github.com/rlojun/final_project_bootcamp/assets/137135122/02b8382f-4e22-4463-a985-4fd91dff0709">

- 즐겨찾기 목록
<img width="570" alt="image" src="https://github.com/rlojun/final_project_bootcamp/assets/137135122/a48a8546-0bcb-4dd3-a7a6-9129a56862b2">

- 시간 충전 페이지
<img width="803" alt="image" src="https://github.com/rlojun/final_project_bootcamp/assets/137135122/c42b55df-f7bc-4e1a-8139-c08ab12ba84b">

- 문의 페이지
<img width="803" alt="image" src="https://github.com/rlojun/final_project_bootcamp/assets/137135122/d947545a-324a-4d91-901d-2f8f2a89f6c0">

- 마일리지 적립 목록
<img width="572" alt="image" src="https://github.com/rlojun/final_project_bootcamp/assets/137135122/cd86d1bf-e9cf-4bc4-9b8d-4535459db524">





![MuaKissGIF (2)](https://github.com/rlojun/final_project_bootcamp/assets/137135122/66f47423-290b-4933-9ace-4dbbca03dab8)
