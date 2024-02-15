package com.example.lwp.controller;

import com.example.lwp.domain.Post;
import com.example.lwp.dto.CommentDto;
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
@CrossOrigin
@RequestMapping("/api")
public class CommunityController {

    private final CommunityService communityService;

    @Autowired
    public CommunityController(CommunityService communityService){
        this.communityService = communityService;
    }

    @PostMapping("/uploadPosting")
    @ResponseBody
    public boolean uploadPosting(@RequestBody PostingDto request) {
        if (communityService.uploadPosing(request)){
            return true;
        }else {
            return false;
        }
    }
    @PostMapping("/editPosting")
    @ResponseBody
    public boolean editPosting(@RequestBody PostingDto request) {
        if (communityService.editPosting(request)){
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

    @PostMapping("/lookCount")
    @ResponseBody
    public boolean lookCount(@RequestBody PostingDto request) {

        if (communityService.lookCount(request.getPid())){
            return true;
        }
        else{
            return false;
        }

    }

    @PostMapping("/likeUpdate")
    @ResponseBody
    public int likeUpdate(@RequestBody CommentDto request) {
        return communityService.likeUpdate(request);
    }

    @PostMapping("/likeCount")
    @ResponseBody
    public int likeCount(@RequestBody PostingDto request) {
        return communityService.likeCount(request);
    }
    @PostMapping("/getCommentcount")
    @ResponseBody
    public int getCommentcount(@RequestBody PostingDto request) {
        int count = communityService.getCommentcount(request);
        return count;
    }

    @PostMapping("/uploadCommenttoServer")
    @ResponseBody
    public boolean uploadCommenttoServer(@RequestBody CommentDto request) {

        if (communityService.uploadCommenttoServer(request)){
            return true;
        }
        else{
            return false;
        }

    }

    @PostMapping("/getComment")
    @ResponseBody
    public List<Object> getComment(@RequestBody CommentDto request) {

        List<Object> comments = communityService.getComment(request);

        if (!comments.isEmpty()){
            return comments;
        }
        else{
            return Collections.emptyList();
        }

    }


    @PostMapping("/deleteComment")
    @ResponseBody
    public boolean deleteComment(@RequestBody CommentDto request) {

        if (communityService.deleteComment(request)){
            return true;
        }
        else{
            return false;
        }

    }

    @PostMapping("/deletePost")
    @ResponseBody
    public String deletePost(@RequestBody PostingDto request) {
        String result = communityService.deletePost(request);
        return result;
    }

    @PostMapping("/editComment")
    @ResponseBody
    public boolean editComment(@RequestBody CommentDto request) {

        if (communityService.editComment(request)){
            return true;
        }
        else{
            return false;
        }

    }

    @PostMapping("/checkWriter")
    @ResponseBody
    public String checkWriter(@RequestBody PostingDto request) {

        String email = communityService.checkWriter(request);
        if (email.equals("false")){
            return "false";
        }
        else{
            return email;
        }

    }

    @PostMapping("/callPosting")
    @ResponseBody
    public Post callPosting(@RequestBody PostingDto request) {
        Post post = communityService.callPosting(request);

        if (post == null){
            return null;
        }
        else {
            return post;
        }
    }
}
