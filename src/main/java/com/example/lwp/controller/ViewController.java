package com.example.lwp.controller;

import com.example.lwp.domain.Post;
import com.example.lwp.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {
    @Autowired
    private CommunityService communityService;

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

    @GetMapping("/community")
    public String cummunity(){
        return "view/community";
    }

    @GetMapping("/newPosting")
    public String newPosting(){
        return "view/newPosting";
    }

    @GetMapping("/post")
    public String post(){
        return "view/post";
    }

    @GetMapping("/info")
    public String info(@RequestParam(name = "summonerName") String summonerName, Model model) {
        model.addAttribute("summonerName", summonerName);
        return "view/info";
    }

}