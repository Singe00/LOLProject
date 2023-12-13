package com.example.lwp.controller;

import com.example.lwp.domain.Post;
import com.example.lwp.dto.PostingDto;
import com.example.lwp.service.CommunityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api")
public class CommunityController {
    @Autowired
    private final CommunityService communityService;

    @PostMapping("/uploadPosting")
    @ResponseBody
    public boolean uploadPosting(@RequestBody PostingDto request) {
        if (communityService.uploadPosing(request)){
            return true;
        }else {
            return false;
        }
    }

    @PostMapping("/searchPosting")
    @ResponseBody
    public List<Post> searchPosting(@RequestBody PostingDto request) {
        List<Post> posts;
        if (request.getKeyword().equals("categorySearching")){
            posts = communityService.searchCategory(request);
        }else {
            posts = communityService.searchPosting(request);
        }

        if (posts != null && !posts.isEmpty()){
            return posts;
        }else {
            return Collections.emptyList();
        }
    }

    @PostMapping("/getPost")
    @ResponseBody
    public Optional<Object[]> getPost(@RequestBody PostingDto request) {

        Optional<Object[]> post = communityService.findPost(request.getPid());

        return post;
    }
}
