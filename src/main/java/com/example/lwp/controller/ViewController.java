package com.example.lwp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

    @GetMapping("/")
    public String main(){
        return "view/main";
    }

    @GetMapping("/login")
    public String login(){
        return "view/login";
    }

    @GetMapping("/about")
    public String about(){
        return "view/about";
    }

    @GetMapping("/champion")
    public String champion(){
        return "view/champion";
    }

    @GetMapping("/statistics")
    public String statistics(){
        return "view/statistics";
    }

    @GetMapping("/ranking")
    public String ranking(){
        return "view/ranking";
    }

    @GetMapping("/info")
    public String info(@RequestParam(name = "summonerName") String summonerName, Model model) {
        model.addAttribute("summonerName", summonerName);
        return "view/info";
    }

}