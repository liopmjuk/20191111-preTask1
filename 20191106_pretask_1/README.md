# kakao-task
# 카카오 과제

## 개발 프레임웍
>IDE - spring-tool-suite-4.4.1.2.RELEASE

>spring boot 2.1.5 Relase 사용

>spring-boot-starter-tomcat - 내장 톰캣 

>H2 내장 DB 

>Spring boot Jpa

>Spring boot Bath


## 문제해결 방법

### 1. 데이터 Import
> 1 ) 
 
> 2 ) 
 
> 3 ) data.sql 파일에 필요한 Table 이 생성되도록 작성


### 2. Restful Api 개발
> 1 ) Spring-boot-starter-web 를 사용하여 Restful API 형식으로 개발
 
>  2 ) Controller에서 각 문제에 맞는 Service function 을 호출한 후 추출된 값 Return 


### 3. DB에서 문제의 조건에 맞게 Data 추출
>  1 ) H2 Database에서 제공하는 h2-console을 활용해서 API 개발 전 Query 유효성 검증
 
>  2 ) 복잡한 Query 수행을 위해 EntityManager에서 제공하는 NativeQuery 기능 사용
 
>  3 ) DB에서 조건에 맞게 추출된 값을 해당하는 Vo에 매핑해서 Return 하는 방식으로 개발


### 4. Exception 제어
>  1 ) CustomException Class를 생성해서 Q4에서 발생하는 Exception Code, 메시지 Customizing
 
>  2 ) 자체 ExceptionHadler Class를 생성해서 전역적으로 발생하는 Exception 을 제어


## 빌드 및 실행 방법

### 1. STS에서 개발된 WorkspaceApplicaion.class 로 실행
### 2. Juit을 사용해서 Unit Test 진행
>  src/test/java      
