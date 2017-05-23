package com.lyy.snake.test.action;

import com.lyy.snake.customer.SnakeConfigClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fanshuai on 17/5/23.
 */
@Controller
public class TestAction {
    @Value("demo.111")
    public static String objectPropertyValue;

    @RequestMapping(value = "/configValue")
    @ResponseBody
    public Object getProperty(String configKey){
        Map map = new HashMap();
        map.put("objectPropertyValue[demo.111]",objectPropertyValue);
        map.put("paramProperty["+configKey+"]", SnakeConfigClientFactory.getDefaultSnakeConfigClient().getConfigValue(configKey));
        return map;
    }
}
