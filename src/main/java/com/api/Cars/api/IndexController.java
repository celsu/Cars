package com.api.Cars.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping("/nome")
    public String hello(@RequestParam("nome") String nome){
        return "hello "+nome;
    }

    @GetMapping("/teste")
    public String teste(){
        return "hello teste";
    }
}
