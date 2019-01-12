package com.basic.clone.algorithmb;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. haiyi Inc.
 * pawo-power All rights reserved.
 */




import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> </p>
 *
 * <pre> Created: 2018-11-20 18:56  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
public class Demo {


  class Entity{
      public String getName() {
          return name;
      }

      public void setName(String name) {
          this.name = name;
      }

      private String name;

  }


    @Test
    public void testString(){
        List<String> list = new ArrayList<>();
        list.add("aaaaa");
        List<String> bigList = new ArrayList<>();
        bigList.addAll(list);
        list.add("bbbbb");
        for(String strt :bigList){
            System.out.println("String : "+strt);
        }
    }

    @Test
    public void testEntity(){
      Entity entity = new Entity();
      entity.setName("aaaa");
        List<Entity> list = new ArrayList<>();
        list.add(entity);
        List<Entity> bigList = new ArrayList<>();
        bigList.addAll(list);
        list.add(entity);
        for(Entity param :bigList){
            System.out.println("name : "+param.getName());
        }
    }

}
