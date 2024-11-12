# Kafka Tutorial

## 설치 경로
```shell  
/opt/homebrew/Cellar/kafka/3.8.1/libexec/bin  
```  

## 서버 설정 변경
```shell  
$ vim /opt/homebrew/etc/kafka/server.properties  
  
# host 정보 수정 필요  
34 | listeners=PLAINTEXT://localhost:9092  
...  
38 | advertised.listeners=PLAINTEXT://localhost:9092  
```  

## 실행
```shell  
# 1. brew 를 통한 실행  
$ brew services start kafka  
  
# 2-1. sh 를 통한 실행(zookeeper)  
/opt/homebrew/opt/kafka/bin/zookeeper-server-start /opt/homebrew/etc/kafka/zookeeper.properties  
  
# 2-2. sh 를 통한 실행(kafka)  
/opt/homebrew/opt/kafka/bin/kafka-server-start /opt/homebrew/etc/kafka/server.properties  
```  

## 토픽 생성
```shell  
# 파티션 3개, 레플리케이션 2개인 토픽 topic-example-1 생성
$ /opt/homebrew/opt/kafka/bin/kafka-topics --create --topic topic-example-1 --bootstrap-server localhost:9092 --partitions 3 --replication-factor 2 
  
# 파티션이나 레플리케이션같은 주요 설정을 해준다고 함  
$ kafka-topics --describe --topic topic-example-1 --bootstrap-server localhost:9092  
  
# 프로듀서를 통해 메세지 작성  
$ kafka-console-producer --topic topic-example-1 --bootstrap-server localhost:9092  

# 클러스터링 되어 있다면 브로커 포트를 지정하여 프로듀서 콘솔 실행  
$ kafka-console-producer --topic topic-example-1 --bootstrap-server localhost:9092,localhost:9093,localhost:9094
```

## 메세지 발행/수신
```shell
# 컨슈머 콘솔 실행
$ kafka-console-consumer --topic topic-example-1 --from-beginning --bootstrap-server localhost:9092

# 그룹을 지정하여 컨슈머 콘솔을 실행
$ kafka-console-consumer --topic topic-example-1 --group team-a --from-beginning --bootstrap-server localhost:9092
```

## 오프셋 확인
```shell
$ kafka-consumer-groups --bootstrap-server localhost:9092 --group team-a --describe



GROUP           TOPIC           PARTITION  CURRENT-OFFSET  LOG-END-OFFSET  LAG             CONSUMER-ID                                           HOST            CLIENT-ID
team-a          topic-example-1 0          5               5               0               console-consumer-084304b5-ead1-4930-884c-27e2c32facfd /127.0.0.1      console-consumer%
```