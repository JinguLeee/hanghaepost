package com.sparta.hanghaepost.service;

import com.sparta.hanghaepost.dto.PostRequestDto;
import com.sparta.hanghaepost.dto.PostResponseDto;
import com.sparta.hanghaepost.dto.ResultResponseDto;
import com.sparta.hanghaepost.entity.Post;
import com.sparta.hanghaepost.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Postservice {
    private final PostRepository postRepository;

    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        return new PostResponseDto(postRepository.save(post));
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getPost() {
        List<Post> postList = postRepository.findAllByOrderByCreatedAtDesc();

        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        for (Post post : postList) {
            postResponseDtoList.add(new PostResponseDto(post));
        }

        return postResponseDtoList;
    }

    @Transactional
    public PostResponseDto getOnePost(Long id) {
        Post post = getPostDetail(id);
        return new PostResponseDto(post);
    }

    public  Post getPostDetail (Long id){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글 입니다.")
        );
        return post;
    }

    @Transactional
    public PostResponseDto update(Long id, PostRequestDto postRequestDto) {
        Post post = getPostDetail(id);
        if (isNotPassword(id, postRequestDto)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        post.update(postRequestDto);

        return new PostResponseDto(id, postRequestDto); // 업데이트 한 값 가져오고 시포서...
    }

    @Transactional
    public ResultResponseDto deleteMemo(Long id, PostRequestDto postRequestDto) {
        getPostDetail(id);

        ResultResponseDto result;   // 삭제 성공했는지 실행한 결과 보고 시포서.....
        if (isNotPassword(id, postRequestDto)) {
            result = new ResultResponseDto(false);
        } else {
            result = new ResultResponseDto(true);
            postRepository.deleteById(id);
        }
        return result;
    }

    public boolean isNotPassword(Long id, PostRequestDto postRequestDto) {
        Optional<Post> optional = postRepository.findByIdAndPassword(id, postRequestDto.getPassword());
        return optional.isEmpty();
    }
}