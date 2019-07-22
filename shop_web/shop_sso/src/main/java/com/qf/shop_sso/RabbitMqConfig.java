package com.qf.shop_sso;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ的配置
 * @author 王铭
 * @Date 2019/7/19
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue getQueue(){
        return  new Queue("email_queue");
    }


    @Bean
    public FanoutExchange getFanoutExchange(){
        return new FanoutExchange("email_exchange");
    }

    /**
     * 进行交换机的绑定
     */
    @Bean
    public Binding getBinding(Queue getQueue,FanoutExchange getFanoutExchange){
        return BindingBuilder.bind(getQueue).to(getFanoutExchange);
    }



}
