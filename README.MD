# RabbitMQ DEMO

[中文版说明点击这里](http://macdown.uranusjr.com)

the project contains 2 parts(**part 2 is recommend**),they are:

1. Official rabbitMQ implements(reference the documents of the rabbitMQ)
    - api
    - dispatcher
    - client
    - producer
    - consumer
2. The SpringAMQP implements
    - SpringAMQP:spring_amqp_client
    - SpringAMQP:spring_amqp_producer

## Normal rabbitMQ implements
### api

API module: provides interface for producers to add messages.

### dispatcher

The message dispatcher, peripheral system register here(the address and topic info),when there are some messages that matches the topic,It will dispatch the messages to the peripheral system.

### client

The client implementation，it contains 2 parts:
1. register to disptacher
2. interface to receive messages.

### producer

The message producer(Business related),in this demo,we will read records form db that are not processed, and then put it into MQ.

### consumer

The message consumer(Business ralated),in this demo,we will receive messages from the MQ,and then print the messages.

## The SpringAMQP implements

### SpringAMQP:spring_amqp_client
the amqp consumer demo.
- consumers binding queues to the exchange by different routingKeys.
- producers push messages to the exchange,the exchange dispatch messages to queues by the routingKeys.
- consumers receive messages from the queues that they bound(if there were multi consumers bound one queue,they will receive messages evenly).

### SpringAMQP:spring_amqp_producer
amqp messages producer,the producer push the messages to the exact exchange,and attach the routingKey,the exchange will dispatch the messages to all the Queues which match the routingKey.

## Install and config of rabbitMQ server 

### RabbitMQ install
The official documents：http://www.rabbitmq.com/download.html

### Dashboard

visit：http://localhost:15672 

account/password：guest/guest

### Usage

The official documents：http://www.rabbitmq.com/getstarted.html

