//package com.englishscriptclub.blog.config;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.jms.support.converter.SimpleMessageConverter;
//
//@Configuration
//public class JmsConfig {
//    @Bean
//    public JmsTemplate jmsTemplate() {
//        return new JmsTemplate();
//    }
//
//
//   @Bean
//    public RabbitTemplate rabbitTemplate() {
//        return new RabbitTemplate();
//   }
//
//   // spring boot 自动配置会自动装在到 rabbitmq 那里
//   @Bean
//    public MessageConverter messageConverter() {
//        return new Jackson2JsonMessageConverter();
//   }
//}
