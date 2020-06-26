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
