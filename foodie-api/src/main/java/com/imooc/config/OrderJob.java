package com.wjw.config;

import com.wjw.service.OrderService;
import com.wjw.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 2 * @Author: 小王同学
 * 3 * @Date: 2021/1/2 21:40
 * 4
 */
@Component
public class OrderJob {

    @Autowired
    private OrderService orderService;

//    @Scheduled(cron = "0/3 * * * * ?")
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void autoCloseOrder() {
        orderService.closeOrder();
        System.out.println("执行定时任务，当前时间为："
            + DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
    }

}
