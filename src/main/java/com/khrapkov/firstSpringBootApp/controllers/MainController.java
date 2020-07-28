package com.khrapkov.firstSpringBootApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String greeting(@RequestParam( required = false) String name,
                           Model model){
        model.addAttribute("title", "Главная страничка"); //Передача параметра в шаблон
        model.addAttribute("name", (name == null ? "unknown" : name));
        return "home";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

}
