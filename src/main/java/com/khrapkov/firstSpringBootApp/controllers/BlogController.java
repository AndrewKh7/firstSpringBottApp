package com.khrapkov.firstSpringBootApp.controllers;

import com.khrapkov.firstSpringBootApp.models.Post;
import com.khrapkov.firstSpringBootApp.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    private PostRepository postRepository;

    //TODO Add create date of each post
    //TODO Add views count

    @GetMapping("/blog")
    public String blog(Model model){
        Iterable<Post> posts = this.postRepository.findAll();
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
        this.postRepository.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogInfo(@PathVariable( value="id") long id, Model model){
        if(!this.postRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post = this.postRepository.findById(id);
        model.addAttribute("post",post.get());
        return "blog_info";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable( value="id") long id, Model model){
       Post post = this.postRepository.findById(id).orElseThrow();
       model.addAttribute("post",post);
       return "blog_edit";
    }


    @PostMapping("/blog/{id}/edit")
    public String editPost(@PathVariable( value="id") long id,
                           @RequestParam String title,
                           @RequestParam String anons,
                           @RequestParam String fullText ){
        Post post = this.postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFullText(fullText);
        this.postRepository.save(post);
        return "redirect:/blog/"+id;
    }

    @GetMapping("/blog/{id}/delete")
    public String deletePost(@PathVariable( value="id") long id){
        this.postRepository.deleteById(id);
        return "redirect:/blog";
    }
}
