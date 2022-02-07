package com.termitpos.replicasyncserver.configuration;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_EX1 = "Job.Exchange.Ex1";
    public static final String QUEUE_Q1 = "Job.Queue.Q1";
    public static final String ROUTING_KEY_R1 = "Job.RoutingKey.R1";

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public DirectExchange routingKeyR1DirectEx() {
        return ExchangeBuilder.directExchange(EXCHANGE_EX1)
                .build();
    }

    @Bean
    public Queue queueQ1() {
        return QueueBuilder.durable(QUEUE_Q1).build();
    }

    @Bean
    public Binding bindingRoutingKeyR1(@Qualifier("queueQ1") Queue queue,
                                       DirectExchange routingKeyR1DirectEx) {
        return BindingBuilder.bind(queue)
                .to(routingKeyR1DirectEx)
                .with(ROUTING_KEY_R1);
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }
}