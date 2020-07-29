package com.khrapkov.firstSpringBootApp.controllers;

import com.khrapkov.firstSpringBootApp.models.Post;
import com.khrapkov.firstSpringBootApp.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/blog")
    public String blog(Model model){
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts",posts);
        return "blog";
    }

    @GetMapping("/blog/add")
    public String add(){
       return "create";
    }

    @PostMapping("/blog/add")
    public String addPost(@RequestParam String title,
                          @RequestParam String anons,
                          @RequestParam String fullText ){
        Post post = new Post(title,anons,fullText);
        postRepository.save(post);

        return "redirect:/blog";
    }
}
