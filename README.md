# hanghae99_books

**팀 프로젝트의 백엔드 Repository 입니다.**

[프론트엔드 Repositroy](https://github.com/greedysiru/hanghae99_books)

## 프로젝트 특징

* React, Spring을 기반으로 기존의 서비스를 클론 코딩
    * [리디셀렉트](https://select.ridibooks.com/home): 콘텐츠를 구독하거나 책에 대한 리뷰를 남길 수 있는 서비스
    
* 프론트엔드와 백엔드를 분리하여 프로젝트 개발
    * 각 파트의 별도 Repository를 생성 후 작업
    * 프론트 : AWS S3 정적 호스팅
    * 백엔드 : AWS EC2 서버 호스팅
    * 빌드 후, S3와 EC2 연동

* 회원가입, 로그인 및 컨텐츠에 대한 CRUD 중점으로 구현

* 초기 더미데이터는 셀리니움을 사용해 구현
  
* RestApi 방식으로 CRUD 구현
    * DB에 존재하는 서적에 대한 전체, 좋아요 순, 별점 순 조회
    * 별점 부여 및 리뷰 작성, 조회, 수정, 삭제
    * 서적에 좋아요 하기, 좋아요 취소

* 페이징 처리
    * 웹 사이트 진입시, 백엔드에게 서적 목록을 요청
    * 백엔드가 응답한 서적 목록 정보 및 총 서적 수, 페이지 수를 리덕스에 저장
    * 리덕스에 저장된 정보를 기반으로 사이트 메인 페이지 하단에 페이징 처리
    * DB 서적 목록 실시간 반영
    * 사용자가 클릭한 페이지에 해당하는 서적 목록을 서버에게 요청
    * 페이지 끝, 시작으로 가기 및 10페이지 단위 이동 기능
## 개요

* 명칭 : hanghae99_books
* 개발 인원 : 4명
* 개발 기간 : 2021.04.02 ~ 2021.04.08
* 주요 기능 : 서적 조회(카테고리 별), 회원 가입 및 로그인, 리뷰(작성, 조회, 수정, 삭제)
* 개발 환경 : Springboot 2.4, java 8, Mysql 8.0, Jpa
* 형상 관리 툴 : git
* 협업 툴 : Slack  
* 간단 소개 : 리디 셀렉트의 서적 리뷰 서비스를 클론한 프로젝트
* 사이트 : [hanghae99_books](http://hanghae99books.site/)
* 시연 영상 : [유투브 링크](https://www.youtube.com/watch?v=U8rmn8h4lPw)

## 테이블 설계
![스크린샷 2021-04-08 오후 2 12 25](https://user-images.githubusercontent.com/60464424/113971821-774b8b80-9874-11eb-9892-18662e64e759.png)

## API 설계
![스크린샷 2021-04-08 오후 2 21 01](https://user-images.githubusercontent.com/60464424/113972676-0016f700-9876-11eb-8306-c3f3335de8a7.png)
![스크린샷 2021-04-08 오후 2 21 55](https://user-images.githubusercontent.com/60464424/113972678-00af8d80-9876-11eb-975e-9fe273fd3620.png)
![스크린샷 2021-04-08 오후 2 22 12](https://user-images.githubusercontent.com/60464424/113972680-01482400-9876-11eb-9d84-7ef87558db77.png)
![스크린샷 2021-04-08 오후 2 22 42](https://user-images.githubusercontent.com/60464424/113972684-01e0ba80-9876-11eb-8cf3-6376744dfd33.png)