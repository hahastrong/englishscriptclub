package com.englishscriptclub.blog.service;

//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageProperties;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.stereotype.Component;

//@Component
//public class RabbitMQService {
//
//    private RabbitTemplate rabbitTemplate;
//
//    @Autowired
//    public RabbitMQService(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    public void sendMsg(Object obj) {
//        MessageConverter converter = rabbitTemplate.getMessageConverter();
//        MessageProperties props = new MessageProperties();
//        // 设置消息属性
//        props.setHeader("X_FROM", "HAHASTRONG");
//        Message message = converter.toMessage(obj, props);
//        rabbitTemplate.send("hello.world", message);
//    }
//
//    public void sendMsgAndConvert(Object obj) {
//        // 设置消息属性
//        rabbitTemplate.convertAndSend(obj,
//                message -> {
//                    MessageProperties props = message.getMessageProperties();
//                    props.setHeader("X_FROM", "HAHASTRONG");
//                    return message;
//                });
//    }
//
//    public Object receive() {
//        Message message = rabbitTemplate.receive("hello.world");
//        MessageConverter converter = rabbitTemplate.getMessageConverter();
//        return message != null ? (Object) converter.fromMessage(message) : null;
//    }
//
//    public Object receiveAndConvert() {
//        return (Object) rabbitTemplate.receiveAndConvert("hello.world");
//    }
//
//    // 将对象类型传递进去
//    public Object receiveAndConvertObject() {
//        return rabbitTemplate.receiveAndConvert("hello.world", new ParameterizedTypeReference<Object>() {});
//    }
//
//}
