package com.lyy.snake.spring;


import com.lyy.snake.customer.SnakeConfigClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringValueResolver;

/**
 * Created by fanshuai on 16/7/2.
 */
public class SnakeStringValueResolver implements StringValueResolver {
    private final Logger log = LoggerFactory.getLogger(SnakeStringValueResolver.class);
    @Override
    public String resolveStringValue(String value){
        if(!ConfigKeyUtils.isDynamicConfig(value)){
            return value;
        }
        try {
            String valueKey = ConfigKeyUtils.getConfigKeyFromDynamicKey(value);
            String newString = SnakeConfigClientFactory.getDefaultSnakeConfigClient().getConfigValue(valueKey);
            if(newString==null){
                log.warn("**************** setting name ["+value+"] not found really value set to null  ****************");
            }
            value = newString;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return value;
    }
}
