package com.example.lwp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {

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

    @GetMapping("/riot.txt")
    public String riot(){
        return "view/riot";
    }

    @GetMapping("/info")
    public String info(@RequestParam(name = "summonerName") String summonerName, Model model) {
        model.addAttribute("summonerName", summonerName);
        return "view/info";
    }

}