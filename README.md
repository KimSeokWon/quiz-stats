# 인터넷뱅킹 이용현황 정보

데이터셋 : http://data.seoul.go.kr/dataList/datasetView.do?infId=10909&srvType=S&serviceKind=2&c
## 개발 프레임워크
### Software 구성
* 개발언어: Java SE 12
* Framework: Spring Boot 2.1.8
* Database: MongoDB

## 문제 해결 전략
### 요구사항 분석
* 기본 정책(확장성 여부 등)
> 요구사항을 만족하며, 요구사항에 기술되지 않은 확장성은 고려하지 않았다.
> 
* 입력데이터
> 이번 요구사항은 통계데이터에 대한 입력 및 출력이다. 데이터는 년간 데이터이므로 빈번한 변경이 이뤄지지 않고 년도별 통계 데이터이므로 최대 1년에 한번 업데이트된다.
* 출력
> REST API 의 경우에 인증정책을 포함하나 사용자 식별 데이터가 아니라 정보 접근만 허용할뿐 데이터는 사용자 식별되지 않는 데이터를 제공한다.
> 때문에 출력 데이터는 캐시를 이용하여 사용자간에 공유할 수 있도록 제공한다.

* 성능 이슈

> 10,000TPS 이상의 성능을 나타내기 위하여, 캐시를 추가하였으며 상용환경에서는 이러한 변경되지 않는 정보는 CDN 등을 이용하여 서버 부하 및 응답속도 향상을 이룰 수 있다. 
현재 코드에서는 컨트롤러에 ehCache 두어 동일한 요청이 올경우 바로 응답하도록 한다. 캐시의 TTL 값은 데이터가 빈번하게 변경되지 않으므로 2주로 하였다.
> 만약 2주내에 데이터가 변경되고 그것이 실시간에 반영된다는 요구사항이 추가로 발생할 경우 JMS 를 이용하여 캐시를 PURGE 하여 다시 데이터베이스로부터 값을 얻어 올수 있도록 한다. 

      
### 데이터 Collections
입력데이터(DATASET)와 출력데이터를 분석하여 데이터 컬렉션은 아래와 같이 두개의 컬렉션으로 나누어 저장한다.
* Device: 단말기 헤더 코드
* Connection: 연도별 사용률 정보

#### Device
```typescript
interface Device {
  "device_id": string;
  "device_name": string;  
}
```
#### Connection
년도별 단말기별 사용률 정보를 나타낸다.
```typescript
inteface Connection {
    "id": string;
    "year": number;
    "useRate": float;
   "device_id": string;
   "rate": float;
}
```
>
### API 기능 명세
|URL|설명|Parameters|
|---|----|----|
|/code-device/device|서비스 접속 기기 목록을 출력||
|/device-stat/by-year|각 년도별로 인터넷뱅킹을 가장 많이 이용하는 접속기기를 출력||
|/device-stat/year/{year}|특정 년도를 입력받아 그 해에 인터넷뱅킹에 가장 많이 접속하는 기기 이름을 출력| year: Integer, 연도(예, 2018)|
|/device-stat//device/{device}|디바이스 아이디를 입력받아 인터넷뱅킹에 접속 비율이 가장 많은 해를 출력| device: string, 단말기ID(예, DEVICE_00000)|
#### 기본 문제
- 데이터 파일(서울시 인터넷뱅킹 이용률 및 이용기기 통계 데이터)에서 각 레코드를 데이터베이스에
   저장하는 코드를 구현하세요.
> 프로젝트의 디렉토리에 있는 CSV 파일을 읽어 들여 데이터 베이스의 컬렉션은 단말기 코드 목록과 접속기록 목록으로 구분되어 저장한다.
> 저장이 완료되면 sample.csv.saved 라는 파일을 생성하여 재기동하더라도 데이터베이스에 저장하는 로직이 다시 실행되지 않도록 하였다.
>
> 접속 기록은 기본 데이터는 연도와 단말기간의 표로 구분되었으나 통계를 계산하기 편하게 하기 위하여 단말기, 연도당 접속 기록을 하나의 행으로 구분하여 저장하였다.
>
>
- 인터넷뱅킹 서비스 접속 기기 목록을 출력하는 API 를 개발하세요.
```
{
    "devices" : [
        { “device_id”: “DIS7864654”,
            “device_name”: “스마트폰”},
        { “device_id”: “DIS231434”,
            “device_name”: “데스크탑 컴퓨터”},
         ...
        { "device_id”: “DIS645389”,
            “device_name”: “스마트패드”}
    ]
}
```
> 데이터 베이스의 컬렉션은 단말기 코드 목록과 접속기록 목록으로 구분되어 저장한다.
> 접속 기기 목록은 단말기 코드 목록을 모두 조회여 얻을 수 있다.
> 단말기ID 는 DEVICE0000# 형식으로 # 는 0부터 숫자가 오름차순으로 생성된다.

- 각 년도별로 인터넷뱅킹을 가장 많이 이용하는 접속기기를 출력하는 API 를 개발하세요.
```
{
    “devices” : [
        {   “year”: 2011,
            “device_id”: “DIS231434”,
            “device_name”: “데스크탑 컴퓨터”,
            “rate”: 95.1 },
        {   “year”: 2012,
            “device_id”: “DIS231434”,
            “device_name”: “데스크탑 컴퓨터”
            “rate”: 93.9 },
        ...
        {   “year”: 2018,
            "device_id”: “DIS936595”,
            “device_name”: “스마트폰”,
            “rate”: 90.5 }
    ]
}
```
> 몽고디비에서 연도별 접속률이 가장 높은 값을 $GROUP 을 이용하여 얻어온다.
> 그리고 년도와 접속률을 이용하여 단말기ID 를 얻고, 단말기ID의 이름을 얻어 리턴한다.
  
- 특정 년도를 입력받아 그 해에 인터넷뱅킹에 가장 많이 접속하는 기기 이름을 출력하세요.
```
{
    “result” :
    {   “year”: 2011,
        “device_name”: “데스크탑 컴퓨터”,
        “rate”: 95.1
    }
}
```
> REST API 를 이용하여 마지막 경로에 년도 값을 받는다. 단지 년도 값이 숫자가 아닐 경우 오류를 리턴한다.
> 파라미터로 받은 연도를 이용하여 몽고디비에 해당 연도의 접속률이 가장 높은 값을 $GROUP 을 이용하여 얻어온다.
> 그리고 이전 문제와 마찬가지로 년도와 접속률을 이용하여 단말기ID 를 얻고, 단말기ID의 이름을 얻어 리턴한다.
> 본 응답에는 result 값이 배열로 표시 되지 않았나 제출된 코드에는 배열로 처리하였다. 이유는 접속률이 동점일 경우를 고려하있다.

- 디바이스 아이디를 입력받아 인터넷뱅킹에 접속 비율이 가장 많은 해를 출력하세요.
```
{
    “result” :
    {   “device_name”: 스마트폰,
        “year”: 2017,
        “rate”: 90.6 }
}
```
> REST API 를 이용하여 마지막 경로에 단말기코드 값을 받는다.
> 파라미터로 받은 단말기 코드를 이용하여 몽고디비에 해당 단말기의 접속률이 가장 높은 값을 $GROUP 을 이용하여 얻어온다.
> 그리고 이전 문제와 마찬가지로 단말기코드와 접속률을 이용하여 연도를 얻고, 단말기ID의 이름을 얻어 리턴한다.
> 본 응답에는 result 값이 배열로 표시 되지 않았나 제출된 코드에는 배열로 처리하였다. 이유는 접속률이 동점일 경우를 고려하있다.

   
#### 옵션 문제
- 인터넷뱅킹 접속 기기 ID 를 입력받아 2019 년도 인터넷뱅킹 접속 비율을 예측하는 API 를 개발하세요.
> 본 문제에 대한 알고리즘은 선형회귀분석 알고리즘(Linear Regression)을 이용하였다. 해당 알고리즘을 선택한 이유는 접속통계와 같은
> 사용자의 행테애 대해서는 추세선을 이용하여 예측하는것으로 방향을 잡았기 때문이다.
 
## 빌드 및 실행 방법
GIT 으로부터 소스 코드를 내려 받은 후 아래와 갈이 실행할 수 있다. 
```
C:/> git clone https://github.com/KimSeokWon/quiz-stats.git
C:/> mvn build  #빌드
C:/> mvn spring-boot:run #실행
C:/> mvn test #단위 시험 코드
```

