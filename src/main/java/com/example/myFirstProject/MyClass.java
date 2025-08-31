package com.example.myFirstProject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyClass {
    @GetMapping("path")
    public String sayHello() {
        return "Hello World From Naman Khater";
    }
    
}
