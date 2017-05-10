package com.lyy.snake.test;

import com.google.common.annotations.VisibleForTesting;
import com.lyy.snake.client.DefaultSnakeClient;
import org.junit.Test;

/**
 * Created by fanshuai on 17/5/9.
 */
public class TestDefaultSnakeClient {

    @Test
    public void test(){
        DefaultSnakeClient c = new DefaultSnakeClient();
        for (int i = 0;i<10000;i++){
            System.out.println(c.getConfig("demo","sdfa"));
            System.out.println(c.getConfig("demo","11"));
            System.out.println(c.getConfig("demo","22"));
            System.out.println(c.getConfig("demo","33"));
            try {
                Thread.sleep(3000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
