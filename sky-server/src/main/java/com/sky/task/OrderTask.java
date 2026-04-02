package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class OrderTask {
    @Autowired
    OrderMapper orderMapper;
    @Scheduled(cron="0 * * * * ?")
    public void processOutTimeOrder(){

        //1.查询orders表：条件：状态-待付款，下单时间>当前时间-15分钟
        log.info("处理超时订单");
        LocalDateTime time=LocalDateTime.now().plusMinutes(-15);
        //select * from orders where status= 1 and order_time < 当地时间-15分钟
        List<Orders> ordersList= orderMapper.selectByStatusAndOrderTime(Orders.PENDING_PAYMENT,time);
        if(ordersList!=null&&ordersList.size()>0){
            ordersList.forEach(orders->{
                orders.setStatus(Orders.CANCELLED);
                orders.setCancelReason("订单超时，取消订单");
                orders.setCancelTime(LocalDateTime.now());
                orderMapper.update(orders);
            });
        }


    }
    @Scheduled(cron = "0 0 1 * * ?")
    public void processDeliveryOrder(){
        //1.查询数据库orders表，条件:状态：派送中，下单时间<当前时间-1h
        //select * from orders where status =4 and order_time< 当前时间-1h
        LocalDateTime time=LocalDateTime.now().minusMinutes(60);
        List<Orders> ordersList= orderMapper.selectByStatusAndOrderTime(Orders.DELIVERY_IN_PROGRESS,time);

        //2.如果查询到了数据，代表存在一直派送中的订单，需要修改订单的状态为"status=5"
        if(ordersList!=null&&ordersList.size()>0){
            ordersList.forEach(orders->{
                orders.setStatus(Orders.COMPLETED);
                orders.setDeliveryTime(LocalDateTime.now().minusHours(1));
                orderMapper.update(orders);
            });
        }
    }
}
