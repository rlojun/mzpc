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
시트: https://docs.google.com/spreadsheets/d/1RvZuKHDLNujCayDX9yQ5plQV7zA68mWr1TVSUbf1otA/edit#gid=1431469973

Trello : https://trello.com/b/BRaisO7G/mzpc

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


![mzpc](https://github.com/rlojun/mzpc/assets/137260250/6bc23cc8-ef72-4010-92fd-612e2b3b768b)


- Members Table : 유저의 회원정보 관련 테이블
- online_member Table : 온라인한 유저의 사용시간 파악을 위한 테이블
- chat Table : Q&A 채팅 관련 테이블
- admin Table : 관리자 정보 테이블
- times Table : 시간 상품 테이블
- time_purchase Table : 유저가 구매한 시간 상품 정보를 알기 위한 테이블
- mileage_info Table : 마일리지 적립, 사용을 파악하기 위한 테이블
- food Table : 음식 상품 테이블
- cart Table : 장바구니 테이블
- order Table : 결제 완료된 주문 테이블
- favorites Table : 즐겨찾기 기능을 위한 테이블
- category Table : 음식 분류를 위한 카테고리 테이블



## 💡주요 기능

> #### 🎢Flow Chart
- 로그인 페이지
<img width="817" alt="스크린샷 2023-11-08 오후 4 40 28" src="https://github.com/rlojun/mzpc/assets/137260250/f96e7f4f-426b-447d-aa95-7540c09070bf">


- 관리자 페이지
<img width="1181" alt="스크린샷 2023-11-08 오후 5 12 25" src="https://github.com/rlojun/mzpc/assets/137260250/eb957cc7-2aa5-4b55-9ac0-0e6169c2da01">



- 사용자 페이지
<img width="650" alt="스크린샷 2023-11-08 오후 5 15 03" src="https://github.com/rlojun/mzpc/assets/137260250/e1f6061d-e205-40b8-8273-009518b30676">


---
> #### 🖥️UI(화면 설계서)

1. ##### 로그인
- 로그인 화면
<img width="724" alt="스크린샷 2023-11-08 오후 5 16 53" src="https://github.com/rlojun/mzpc/assets/137260250/d136ef22-1dce-45fd-ba3b-72fbe30125dc">


- 아이디/비밀번호 찾기
<img width="931" alt="스크린샷 2023-11-08 오후 5 19 10" src="https://github.com/rlojun/mzpc/assets/137260250/9fab40f4-57b2-4c4b-909e-daa62486895f">


- 회원 가입 페이지
<img width="940" alt="스크린샷 2023-11-08 오후 5 20 05" src="https://github.com/rlojun/mzpc/assets/137260250/ed0faa70-934b-4151-ad7a-6df9ddd955e5">


- 시간 충전 페이지
<img width="932" alt="스크린샷 2023-11-08 오후 5 20 30" src="https://github.com/rlojun/mzpc/assets/137260250/12d93752-b177-41f1-97fc-6288ee622293">


2. ##### 관리자

- 음식 상품 목록
<img width="763" alt="스크린샷 2023-11-08 오후 5 21 13" src="https://github.com/rlojun/mzpc/assets/137260250/bbc0937e-7cae-4107-8acd-2ce831af0ffc">


- 음식 상세 화면
<img width="803" alt="스크린샷 2023-11-08 오후 5 21 44" src="https://github.com/rlojun/mzpc/assets/137260250/1a2cbefb-7254-4e89-8be3-13d8d87bdd5e">


- 음식 수정 화면
<img width="810" alt="스크린샷 2023-11-08 오후 5 22 03" src="https://github.com/rlojun/mzpc/assets/137260250/36f795ed-6a6a-48ce-a40b-bf290e2377e5">


- 음식 추가 화면
<img width="807" alt="스크린샷 2023-11-08 오후 5 22 30" src="https://github.com/rlojun/mzpc/assets/137260250/df02ec37-dd80-4248-b66e-05b6b9f4c780">


- 시간 상품 목록
<img width="770" alt="스크린샷 2023-11-08 오후 5 23 10" src="https://github.com/rlojun/mzpc/assets/137260250/7b54e66b-d37f-4022-9a0c-480c7092c781">


- 시간 상품 상세보기
<img width="812" alt="스크린샷 2023-11-08 오후 5 23 43" src="https://github.com/rlojun/mzpc/assets/137260250/c0da9823-ee8a-4858-be34-829b74c3091c">


- 시간 상품 수정
<img width="827" alt="스크린샷 2023-11-08 오후 5 24 08" src="https://github.com/rlojun/mzpc/assets/137260250/618afb16-61b0-48d6-8f15-52ef16a462d6">


- 시간 상품 등록
<img width="807" alt="스크린샷 2023-11-08 오후 5 24 31" src="https://github.com/rlojun/mzpc/assets/137260250/e5645f45-a664-4514-9f03-ae5fed2a890b">


- 주문 목록
<img width="781" alt="스크린샷 2023-11-08 오후 5 25 05" src="https://github.com/rlojun/mzpc/assets/137260250/266cf994-17bf-4c56-98dc-1a1b32bfb20f">



- 사용자 목록
<img width="824" alt="스크린샷 2023-11-08 오후 5 25 31" src="https://github.com/rlojun/mzpc/assets/137260250/f9132d9c-d342-418b-9cba-e914bd390d7b">


- 사용자 문의 화면
<img width="793" alt="스크린샷 2023-11-08 오후 5 26 30" src="https://github.com/rlojun/mzpc/assets/137260250/8b4c2c20-970e-4857-9453-58739d19110d">


3. ##### 사용자
- 먹거리 주문 화면
<img width="840" alt="스크린샷 2023-11-08 오후 5 26 55" src="https://github.com/rlojun/mzpc/assets/137260250/b482cef2-8429-4521-a01c-b2c8eb3511be">


- 토핑 선택 창
<img width="824" alt="스크린샷 2023-11-08 오후 5 27 53" src="https://github.com/rlojun/mzpc/assets/137260250/305352d2-ddd7-4b24-a857-8a5168c5b70f">


- 먹거리 주문, 결제
<img width="840" alt="스크린샷 2023-11-08 오후 5 28 12" src="https://github.com/rlojun/mzpc/assets/137260250/917df536-1ad7-4ef8-b42c-9fd02593c393">


- 즐겨찾기 목록
<img width="836" alt="스크린샷 2023-11-08 오후 5 28 31" src="https://github.com/rlojun/mzpc/assets/137260250/2c340365-d706-4b79-a15e-8c2670c18648">


- 시간 충전 페이지
<img width="840" alt="스크린샷 2023-11-08 오후 5 28 53" src="https://github.com/rlojun/mzpc/assets/137260250/e28ab1ec-e4c6-425a-bc26-a431d9ea00df">


- 문의 페이지
<img width="833" alt="스크린샷 2023-11-08 오후 5 29 18" src="https://github.com/rlojun/mzpc/assets/137260250/5d78ac69-f4b3-47bf-a08e-41a3c1c11da4">


- 마일리지 적립 목록
<img width="711" alt="스크린샷 2023-11-08 오후 5 29 38" src="https://github.com/rlojun/mzpc/assets/137260250/9dae437d-0edf-445c-b21d-0812b0383921">






![MuaKissGIF (2)](https://github.com/rlojun/final_project_bootcamp/assets/137135122/66f47423-290b-4933-9ace-4dbbca03dab8)
