# datapipeline

## 目录
kafka -- kafka的docker脚本

test -- producer test代码

## kafka on docker
### build
```
docker build -t datapipeline/kafka kafka/
```
### run
```
docker run -p 2181:2181 -p 9092:9092 datapipeline/kafka
```

## run test
> 需要准备[java+maven环境]()

### build
```
cd test
mvn clean package
```

### test
```
cd target
./kafka-test consumer kafka-test
./kafka-test producer kafka-test
```