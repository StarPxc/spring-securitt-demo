package imooc.security.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 浦希成
 * 2018/10/17
 */
@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello() {
        return "test";
    }
}
