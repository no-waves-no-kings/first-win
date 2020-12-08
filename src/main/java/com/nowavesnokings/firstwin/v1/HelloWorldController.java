package com.nowavesnokings.firstwin.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ssx
 * @version V1.0
 * @className HelloWorldController
 * @description 测试api
 * @date 2020-12-08 15:54
 * @since 1.8
 */
@RestController
public class HelloWorldController {
    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/world")
    public String world() {
        return "hello world2";
    }

}
