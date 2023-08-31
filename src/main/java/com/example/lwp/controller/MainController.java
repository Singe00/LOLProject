package com.example.lwp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(){
        return "view/main";
    }

    @GetMapping("/t")
    public String test(){
        return "view/content";
    }

    @GetMapping("/login")
    public String login(){
        return "view/login";
    }

    @GetMapping("/about")
    public String about(){
        return "view/about";
    }
    @GetMapping("/info")
    public String info(@RequestParam(name = "summonerName") String summonerName, Model model) {
        model.addAttribute("summonerName", summonerName);
        return "view/info";
    }

}