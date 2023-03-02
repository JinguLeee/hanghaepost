package com.sparta.hanghaepost.dto;

import com.sparta.hanghaepost.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String password;
    private String title;
    private String username;
    private String contents;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.password = post.getPassword();
        this.title = post.getTitle();
        this.username = post.getUsername();
        this.contents = post.getContents();
    }

    public PostResponseDto(Long id, PostRequestDto postRequestDto) {
        this.password = postRequestDto.getPassword();
        this.title = postRequestDto.getTitle();
        this.username = postRequestDto.getUsername();
        this.contents = postRequestDto.getContents();
        this.id = id;
    }
}
