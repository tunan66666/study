package com.example.study.controller;

import com.example.study.services.ConsistentHashingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hash")
public class HashController {

    @GetMapping("test")
    public void test() {

        System.out.println("192.168.0.0:111的哈希值：" + "192.168.0.0:1111".hashCode());
        System.out.println("192.168.0.1:111的哈希值：" + "192.168.0.1:1111".hashCode());
        System.out.println("192.168.0.2:111的哈希值：" + "192.168.0.2:1111".hashCode());
        System.out.println("192.168.0.3:111的哈希值：" + "192.168.0.3:1111".hashCode());
        System.out.println("192.168.0.4:111的哈希值：" + "192.168.0.4:1111".hashCode());
        // Hash值为负数
        System.out.println("192.168.1.0:1111的哈希值：" + "192.168.1.0:1111".hashCode());

    }

    @GetMapping("consistent")
    public void consistent() {
        String[] dataAry = {"127.0.0.1:1111", "221.226.0.1:2222", "10.211.0.1:3333"};
        for (int i = 0; i < dataAry.length; i++) {
            String server = ConsistentHashingService.getServer(dataAry[i]);
            System.out.println("数据："+ dataAry[i] +"，路由到"+ server +"服务器");
        }
    }

}
