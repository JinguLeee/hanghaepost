package com.sparta.hanghaepost.controller;

import com.sparta.hanghaepost.dto.PostRequestDto;
import com.sparta.hanghaepost.dto.PostResponseDto;
import com.sparta.hanghaepost.dto.ResultResponseDto;
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
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto) {
        return postService.createPost(postRequestDto);
    }

    @GetMapping("/api/post")
    public List<PostResponseDto> getMemos() {
        return postService.getPost();
    }

    @GetMapping("/api/post/{id}")
    public PostResponseDto getDetail(@PathVariable Long id) {
        return postService.getOnePost(id);
    }

    @PutMapping("/api/post/{id}")
    public PostResponseDto updatePost (@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        return postService.update(id, postRequestDto);
    }

    @DeleteMapping("/api/post/{id}")
    public ResultResponseDto deleteMemo(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        return postService.deleteMemo(id, postRequestDto);
    }
}
