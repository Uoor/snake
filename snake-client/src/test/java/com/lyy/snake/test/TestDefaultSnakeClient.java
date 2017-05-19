package com.lyy.snake.test;

import com.lyy.snake.common.DefaultSnakeClient;
import com.lyy.snake.customer.SnakeConfigClient;
import com.lyy.snake.customer.SnakeConfigClientFactory;
import org.junit.Test;

/**
 * Created by fanshuai on 17/5/9.
 */
public class TestDefaultSnakeClient {

    @Test
    public void test(){
        SnakeConfigClient c = SnakeConfigClientFactory.getDefaultSnakeConfigClient();
        for (int i = 0;i<10000;i++){
            System.out.println(c.getConfigValue("demo", "1"));
            System.out.println(c.getConfigValue("demo", "2"));
            System.out.println(c.getConfigValue("demo", "3"));
            System.out.println(c.getConfigValue("demo","4"));
            try {
                Thread.sleep(3000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }




}
