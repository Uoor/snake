package com.lyy.snake.test.action;

import com.lyy.snake.customer.SnakeConfigClientFactory;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fanshuai on 17/5/23.
 */
public class PropertyAction implements Controller {
    public  String objectPropertyValue;

    public ModelAndView handleRequest(String configKey){
        Map map = new HashMap();
        map.put("objectPropertyValue[demo.111]",objectPropertyValue);
        map.put("paramProperty["+configKey+"]", SnakeConfigClientFactory.getDefaultSnakeConfigClient().getConfigValue(configKey));
        return new ModelAndView("",map);
    }


    public  String getObjectPropertyValue() {
        return objectPropertyValue;
    }

    public  void setObjectPropertyValue(String objectPropertyValue) {
        this.objectPropertyValue = objectPropertyValue;
    }

    @Override
    public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
        String configKey = request.getParameter("configKey");
        Map map = new HashMap();
        map.put("objectPropertyValue[demo.111]",objectPropertyValue);
        map.put("paramProperty["+configKey+"]", SnakeConfigClientFactory.getDefaultSnakeConfigClient().getConfigValue(configKey));
        System.out.println(map);
        return new ModelAndView("",map);
    }
}
