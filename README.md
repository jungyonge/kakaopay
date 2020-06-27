# 카카오페이 사전과제 - 카카오페이 돈뿌리기 API
- 이 프로젝트는 카카오페이의 돈뿌리기 기능을 구현한 API 프로젝트 입니다.

## 개발 환경
- 기본 환경
    - IDE: IntelliJ IDEA
    - OS: Windows
    - GIT
- Server
    - Java8
    - Spring Boot 2.3.1
    - JPA
    - Maven
    - Junit5

## 빌드 및 실행
- 빌드 
 ~~~
 mvn install
 ~~~
- 실행
~~~
java -jar JAR경로
~~~

## 문제 해결 전략
1. 돈뿌리기 이벤트 생성
    - 예상이 불가능한 3자리수 token 생성
        - Math.Random()은 seed를 재설정 할 수가 없어서 예측이 가능
        - util.Random()은 seed가 계속적으로 변하여 예측이 불가능한 난수 생성
    - 뿌릴 돈을 나눌 때도 util.Random을 사용하여 고르게 분배함.
    
2. 돈뿌리기 이벤트 참여
    - 많은 제약사항을 체크 할 수 있는 validateShareEvent 함수 생성
        - 중복 참여 유저 방지
        - 이벤트 생성자는 이벤트 참여 불가
        - 이미 돈뿌리기를 다 받았거나, 10분이 지난 이벤트 체크
        - 계정정보가 없거나, 방에 없는 유저 체크
    - 제약사항이 많아 ShareEventException과 ShareEventExceptionHandler를 생성하여  
        일괄적으로 Exception 관리
3. 돈뿌리기 이벤트 조회
    - 이벤트는 생성되었지만 수령하지 않은 자식이벤트를 제거하기 위해 loop문으로 User변수를 null 체크하여   
    제거 하려 했으나 , loop문에서 List.remove()할 경우 ConcurrentModificationException 발생  
    이에 대한 해결책을 Iterator 사용하여 제거함
    
4. 공통
    - 요구한 API 기능사항에 따라 HTTP Method만으로 구성
    - 각 API마다 다른 Response, Request를 사용하여 혼용방지
    - Enum으로 에러코드 관리
    - Test Code
        - 많은 제약사항이 있어 validation 체크를 위해 고의로 Exception 발생시키는 Test Code 구성
                                          
                                         
    

## API 목록
| Method | Path | Description |
|--------|-----------------------|-----------------------------------------------------------------|
| POST | /share-event | 돈뿌리기 이벤트를 생성합니다.
| PUT | /share-event | 돈뿌리기 이벤트를 참여합니다.
| GET | /share-event | 돈뿌리기 이벤트 내역을 조회합니다.

## 응답코드

| CODE | Message                       | 
|--------|----------------------------------------------------------------------------------------|
| C0000 | 정상
| E0001 | 이미 참여한 계정입니다.
| E0002 | 해당 돈 뿌리기를 만드신 계정은 참여 불가능 합니다.
| E0003 | 만료된 뿌리기 입니다.
| E0004 | 돈 뿌리기 이벤트가 존재하지 않습니다.
| E0005 | 계정 정보가 존재하지 않습니다.
| E0006 | 채팅방에 속한 유저만 이벤트에 참여 가능합니다.
| E0007 | 참여하신 채팅방이 아닙니다.
| E0008 | 돈 뿌리기 이벤트조회는 7일동안 가능합니다.
| E0009 | 유효한 돈뿌리기 이벤트가 없습니다.
