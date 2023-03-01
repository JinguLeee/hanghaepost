package com.sparta.hanghaepost.service;

import com.sparta.hanghaepost.dto.PostRequestDto;
import com.sparta.hanghaepost.entity.Post;
import com.sparta.hanghaepost.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Postservice {
    private final PostRepository postRepository;

    @Transactional
    public Post createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        return post;
    }

    @Transactional(readOnly = true)
    public List<Post> getPost() {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public Post getPostDetail(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글 입니다.")
        );
        return post;
    }

    @Transactional
    public Long update(Long id, PostRequestDto postRequestDto) {
        Post post = getPostDetail(id);
        isPassword(id, postRequestDto);
        post.update(postRequestDto);
        return post.getId();
    }

    @Transactional
    public Long deleteMemo(Long id, PostRequestDto postRequestDto) {
        getPostDetail(id);
        isPassword(id, postRequestDto);
        postRepository.deleteById(id);
        return id;
    }

    public void isPassword(Long id, PostRequestDto postRequestDto) {
        Optional<Post> optional = postRepository.findByIdAndPassword(id, postRequestDto.getPassword());
        if (optional.isEmpty()) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}