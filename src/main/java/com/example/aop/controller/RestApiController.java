package com.example.aop.controller;

import com.example.aop.annotation.Timer;
import com.example.aop.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name){
        return id+" / "+name;
    }

    @PostMapping("/post")
    public User post(@RequestBody User user){
        return user;
    }

    @Timer
    @DeleteMapping("/delete")
    public void delete() throws Exception{
        //db logic - 삭제 시 1~2초 소요
        Thread.sleep(1000*2);
    }

}
