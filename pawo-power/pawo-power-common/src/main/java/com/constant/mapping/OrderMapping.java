package com.constant.mapping;


import com.gm.config.orika.OrikaMapperFactoryConfigurer;
import com.gm.order.ShoppingOrderVo;
import com.gm.order.ShoppingOrdersRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import ma.glasnost.orika.MapperFactory;

/**
 * @author gmzhao
 */
@Component
@DependsOn("sysRegister")
public class OrderMapping implements OrikaMapperFactoryConfigurer{


    @Override
    public void configure(MapperFactory mapperFactory) {

            mapperFactory.classMap(ShoppingOrdersRequest.class, ShoppingOrderVo.class)
//                    .fieldMap("transactionType","transactionType").converter().add()
                    .byDefault()
                    .register();
    }
}
