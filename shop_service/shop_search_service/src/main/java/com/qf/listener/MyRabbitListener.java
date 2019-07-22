package com.qf.listener;

import com.qf.entity.Goods;
import com.qf.service.ISearchService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 王铭
 * @Date 2019/7/19
 */
@Component
public class MyRabbitListener {

    @Autowired(required = false)
    private ISearchService searchService;

    @RabbitListener(queues = "search_queue")
    public void msgHandler(Goods goods){
        //同步到索引库
        searchService.addGoods(goods);
    }


}
