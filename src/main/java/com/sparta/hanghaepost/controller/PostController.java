package com.sparta.hanghaepost.controller;

import com.sparta.hanghaepost.dto.PostRequestDto;
import com.sparta.hanghaepost.entity.Post;
import com.sparta.hanghaepost.service.Postservice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final Postservice postService;

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    @PostMapping("/api/post")
    public Post createPost(@RequestBody PostRequestDto postRequestDto) {
        return postService.createPost(postRequestDto);
    }

    @GetMapping("/api/post")
    public List<Post> getMemos() {
        return postService.getPost();
    }

    @GetMapping("/api/post/{id}")
    public Post getDetail(@PathVariable Long id) {
        return postService.getPostDetail(id);
    }

    @PutMapping("/api/post/{id}")
    public Long updatePost (@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        return postService.update(id, postRequestDto);
    }

    @DeleteMapping("/api/post/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        return postService.deleteMemo(id);
    }

    @PostMapping("/api/getpassword/{id}")
    public void isPassword(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        postService.isPassword(id, postRequestDto);
    }
}
