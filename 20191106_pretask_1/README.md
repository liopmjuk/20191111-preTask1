# 사전 과제 1

## 개발 프레임웍
>IDE - spring-tool-suite-4.4.1.2.RELEASE

>spring boot 2.1.5 Relase 사용

>spring-boot-starter-tomcat

>H2 내장 DB 

>Spring boot Jpa

>Spring boot Batch

>lombok project


## 문제해결 방법

### 1. 데이터 Import
> 1 ) data.sql 파일에 필요한 Table 이 생성되도록 작성

> 2 ) 지원지자체 csv를 생성해서 Application 기동시 DB에 지자체 데이터 저장 (data.sql)
 
> 3 ) 데이터저장 API 호출 시(/biz/saveAll) 지자체 코드컬럼과 데이터 생성일자 컬럼 전처리 후 데이터 저장(BizSuppoertProcessor.class)


### 2. Restful Api 개발
>  Spring-boot-starter-web 를 사용하여 Restful API 형식으로 개발
>  Controller에서 각 문제에 맞는 Service function 을 호출한 후 추출된 값 Return

>  문제 1 ) /biz/saveAll
>         데이터 저장 API

>  문제 2 ) /biz/getDataAll
>         모든 지자체 지원 정보 조회 API

>  문제 3 ) /biz/getData
>         지자체명을 입력받아 해당 지자체 지원 정보 조회 API

>  문제 4 ) /biz/update
>         지자체 지원 정보 수정 API (지자체명을 기준으로 함)

>  문제 5 ) /biz/boundList/n
>         지원한도 낮은 지자체명 조회 API (url로 조회할 n개 숫자를 입력받음)
>         지원한도 합산 시 숫자만 추출, 변환하여 합산

>  문제 6 ) /biz/lowestLimit
>         이차보전 비율 가장 낮은 지자체명 조회 API

### 3. DB에서 문제의 조건에 맞게 Data 추출 
>  1 ) H2 Database에서 제공하는 h2-console을 활용해서 API 개발 전 Query 유효성 검증
 
>  2 ) 복잡한 Query 수행을 위해 EntityManager에서 제공하는 NativeQuery 기능 사용 (BizSupportQueryProvider.class)

>  3 ) 단순 쿼리나 모든 데이터 조회하는 API는 JPARepository를 이용한 Repository에서 제공하도록 구현 (BizSupportRepository.class)
 
>  4 ) DB에서 조건에 맞게 추출된 값을 해당하는 객체에 매핑해서 Return 하는 방식으로 개발


### 4. Exception 제어
>  1 ) CustomException Class를 생성해서 모든 문제에서 발생할 수 있는 Exception Code, Message Customizing
 
>  2 ) 자체 ExceptionHadler Class를 생성해서 전역적으로 발생하는 Exception 을 제어


## 빌드 및 실행 방법

### 1. PreTaskApplicaion1.class 로 실행
### 2. PreTaskTest1.class - Juit을 사용해서 Unit Test 진행
