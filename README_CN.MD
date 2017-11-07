# RabbitMQ 测试DEMO

项目包含两个部分（推荐第二种方案），分别是
1. 参考官方的实现
    - api
    - dispatcher
    - client
    - producer
    - consumer
2. SpringAMQP实现
    - SpringAMQP:spring_amqp_client
    - SpringAMQP:spring_amqp_producer

## 参考官方的实现
### api

api模块用于对外部系统提供接口，方便外部接口向MQ中添加消息，业务无关

### dispatcher

消息分发器，各个业务系统到这里注册，（业务系统接口地址，关注的topic）注册成功后，当有满足条件的消息时，分发到该系统

### client

模拟接收端接收数据，包含两个部分；
1. 到dispatcher上注册
2. 接收消息接口

### producer

消息生产者，业务相关，本Demo的生产者的任务是从数据库中读取未处理的记录，然后丢到MQ

### consumer

消息消费者，业务相关，本Demo的消费者负责接收生产者生产的消息，然后做二次处理。

## SpringAMQP实现
### SpringAMQP:spring_amqp_client
amqp消费者demo，demo里面有两种消息分发方式，
1. 广播：所有绑定到该Exchange的队列都会收到消息；
2. Topics：按照routingKey来接收消息，具体描述如下：
    - 消费者通过routingKey将某个队列绑定到指定的Exchange
    - 生产者发送消息到Exchange，Exchange根据生产者携带的routingKey，将消息路由到相应的队列（可能会有多个）
    - 消息服务器将队列里面的消息发送到消费者（如果一个队列有多个消费者，将平均分配消息）

### SpringAMQP:spring_amqp_producer
amqp消息生产者，将消息发送到指定的Exchange，并携带routingKey参数，Exchange会根据routingKey将消息路由给相应的队列（可能会有多个）

## RabbitMQ服务器安装配置

### RabbitMQ安装
参考官方文档：http://www.rabbitmq.com/download.html

### Dashboard

访问：http://localhost:15672 

账号/密码：guest/guest

### 如何使用

参考官方向导：http://www.rabbitmq.com/getstarted.html

