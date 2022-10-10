package com.example.coffee.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {

    @GetMapping({"/","/home"})
    public String doHome(){
        return "/home/home";
    }

}
