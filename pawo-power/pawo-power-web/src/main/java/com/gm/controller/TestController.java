package com.gm.controller;


import com.google.common.collect.Lists;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    private RedisTemplate redisTemplate;

    @PostMapping("add")
    public ResponseEntity getValues(@RequestBody TestDemo testDemo){
        redisTemplate.opsForHash().put("key","list_key",testDemo);
        return ResponseEntity.ok("");
    }


    @GetMapping("list")
    public ResponseEntity getList(){
        List list = redisTemplate.opsForHash().values("key");
        List<TestDemo> testList = Lists.newArrayList();
        for(Object obj:list){
            testList.add((TestDemo) obj);
        }
        return ResponseEntity.ok(testList);
    }


    @GetMapping("test")
    public void check(){
        for(int i = 0;i<100;i++){
            TestDemo testDemo = new TestDemo();
            testDemo.setId(i);
            testDemo.setName("测试"+i);
            testDemo.setAge(i+10);
            testDemo.setSex("男");
            testDemo.setWeight("150");
            redisTemplate.opsForHash().put("key","list_key"+i,testDemo);
        }
    }

}
