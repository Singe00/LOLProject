package com.example.lwp.service;

import com.example.lwp.domain.Post;
import com.example.lwp.domain.UserDomain;
import com.example.lwp.dto.PostingDto;
import com.example.lwp.repository.PostRepository;
import com.example.lwp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommunityService {

    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private final UserRepository userRepository;


    public boolean uploadPosing(PostingDto request){

        UserDomain user = userRepository.findByEmail(request.getValue());

        Post post = new Post();
        post.setCategory(request.getCategory());
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setUserId(user.getUserId());

        Post savePost = postRepository.save(post);

        if (savePost != null){
            return true;
        }
        else {
            return false;
        }
    }

    public List<Post> searchPosting(PostingDto requset){

        if (requset.getCategory() == 0){
            return postRepository.findAll(Sort.by(Sort.Order.desc("postId")));
        }
        else if (requset.getCategory() == 1) {
            return postRepository.findAllByTitleContaining(requset.getKeyword());
        }
        else if (requset.getCategory() == 2) {
            return postRepository.findAllByTitleContainingOrContentContaining(requset.getKeyword(),requset.getKeyword());
        }
        else {
            return Collections.emptyList();
        }
    }
    public List<Post> searchCategory(PostingDto requset){
        List<Post> posts = postRepository.findAllByCategory(requset.getCategory());

        if (!posts.isEmpty()){
            return posts;

        }
        else {
            return Collections.emptyList();
        }
    }

    public Optional<Object[]> findPost(Long requset){
        Optional<Object[]> post = postRepository.findPostAndUserByPostId(requset);

        return post;
    }
}
