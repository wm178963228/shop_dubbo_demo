package com.qf.shop_goods_service;

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

    @Bean(name = "squeue")
    public Queue getQueue1(){
        return  new Queue("search_queue");
    }

    @Bean(name = "iqueue")
    public Queue getQueue2(){
        return  new Queue("item_queue");
    }

    @Bean(name = "gexchange")
    public FanoutExchange getFanoutExchange(){
        return new FanoutExchange("goods_exchange");
    }

    /**
     * 进行交换机的绑定
     */
    @Bean
    public Binding getBinding1(Queue squeue,FanoutExchange gexchange){
        return BindingBuilder.bind(squeue).to(gexchange);
    }

    @Bean
    public Binding getBinding2(Queue iqueue,FanoutExchange gexchange){
        return BindingBuilder.bind(iqueue).to(gexchange);
    }


}
