package com.gm.controller;


import com.google.common.collect.Lists;

import com.basic.util.IIDUtil;
import com.gm.page.util.PageRequest;
import com.gm.page.util.PageResponse;
import com.gm.page.util.PageUtil;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sun.swing.StringUIClientPropertyKey;

import javax.annotation.Resource;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户管理
 *
 * @author gmzhao
 */
@RestController
@RequestMapping("user")
@Slf4j
public class TestController {


    private RedisTemplate<String,Object> redisTemplate;


    @Autowired
    public TestController(RedisTemplate<String,Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostMapping("add")
    public ResponseEntity getValues(@RequestBody TestDemo testDemo){
        testDemo.setId(IIDUtil.getIid());
        log.info("新增用户========"+testDemo.toString());
        redisTemplate.opsForHash().put("key","list_key",testDemo);
        return ResponseEntity.ok("");
    }


    @GetMapping("list")
    public ResponseEntity getList(@RequestParam(value = "name",required = false) String name,
                                  PageRequest pageRequest){
        List list = redisTemplate.opsForHash().values("key");
        List<TestDemo> testList = Lists.newArrayList();
        if(StringUtils.isEmpty(name)){
            for(Object obj:list){
                TestDemo testDemo = (TestDemo) obj;
                testList.add(testDemo);
            }
            PageResponse<TestDemo> pageResponse = PageUtil.noSqlHandlePage(pageRequest,testList);
            return ResponseEntity.ok(pageResponse);
        }
        for(Object obj:list){
                TestDemo testDemo = (TestDemo) obj;
                String userName = testDemo.getName();
                if(name.equals(userName)){
                    testList.add(testDemo);
                }
        }
        PageResponse<TestDemo> pageResponse = PageUtil.noSqlHandlePage(pageRequest,testList);
        return ResponseEntity.ok(pageResponse);
    }
}
