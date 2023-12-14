package com.example.lwp.service;

import com.example.lwp.domain.Comment;
import com.example.lwp.domain.Likepost;
import com.example.lwp.domain.Post;
import com.example.lwp.domain.UserDomain;
import com.example.lwp.dto.CommentDto;
import com.example.lwp.dto.PostingDto;
import com.example.lwp.repository.CommentRepository;
import com.example.lwp.repository.LikepostRepository;
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
    @Autowired
    private final LikepostRepository likepostRepository;
    @Autowired
    private final CommentRepository commentRepository;


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
    public boolean editPosting(PostingDto request){

        UserDomain user = userRepository.findByEmail(request.getValue());

        Post post = postRepository.findByPostId(request.getPid());
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
    public boolean lookCount(Long requset){

        Post post = postRepository.findByPostId(requset);

        if (post==null){
            return false;
        }

        post.setLook(post.getLook()+1);
        postRepository.save(post);
        return true;
    }
    public int likeUpdate(CommentDto request) {

        UserDomain userDomain = userRepository.findByEmail(request.getEmail());
        Long userIdToCheck = userDomain.getUserId();

        Likepost lp = likepostRepository.findByPostIdAndUserId(request.getPid(), userIdToCheck);

        if (lp!=null) {
            likepostRepository.delete(lp);
            int count = likepostRepository.countByPostId(request.getPid());
            return count;
        } else {
            Likepost newLike = new Likepost();
            newLike.setUserId(userIdToCheck);
            newLike.setPostId(request.getPid());
            likepostRepository.save(newLike);
            int count = likepostRepository.countByPostId(request.getPid());
            return count;
        }

    }
    public int likeCount(PostingDto request){
        int count = likepostRepository.countByPostId(request.getPid());
        return count;
    }
    public int getCommentcount(PostingDto request) {
        int commentCount = commentRepository.countByPostId(request.getPid());
        return commentCount;
    }
    public boolean uploadCommenttoServer(CommentDto requset){

        Comment comment = new Comment();
        UserDomain user = userRepository.findByEmail(requset.getEmail());
        if (user == null){
            return false;
        }else {
            comment.setText(requset.getText());
            comment.setUserId(user.getUserId());
            comment.setPostId(requset.getPid());
            Comment check = commentRepository.save(comment);

            if (check != null){
                return true;
            }else {
                return false;
            }

        }
    }
    public List<Object> getComment(CommentDto request){
        List<Object> comments = commentRepository.findCommentAndUserByPostId(request.getPid());

        if (!comments.isEmpty()){
            return comments;
        }else {
            return Collections.emptyList();
        }
    }
    public boolean deleteComment(CommentDto requset){

        Comment comment = commentRepository.findByCommentId(requset.getCid());

        UserDomain user = userRepository.findByUserId(comment.getUserId());

        if (user == null){
            return false;
        }else if (user.getEmail().equals(requset.getEmail())){
            commentRepository.delete(comment);
            return true;
        }else {
            return false;
        }
    }
    public String deletePost(PostingDto requset){

        Post post = postRepository.findByPostId(requset.getPid());

        UserDomain user = userRepository.findByUserId(post.getUserId());

        if (user == null){
            return "false";
        }else if (user.getEmail().equals(requset.getEmail())){
            postRepository.delete(post);
            return "true";
        }else {
            return "false2";
        }
    }
    public String checkWriter(PostingDto requset){

        Post post = postRepository.findByPostId(requset.getPid());
        UserDomain userDomain = userRepository.findByUserId(post.getUserId());

        if (!(userDomain == null)){
            return userDomain.getEmail();
        }else {
            return "false";
        }
    }
    public boolean editComment(CommentDto requset){

        Comment comment = commentRepository.findByCommentId(requset.getCid());
        comment.setText(requset.getText());
        Comment edit = commentRepository.save(comment);

        if (!(edit == null)){
            return true;
        }else {
            return false;
        }
    }
    public Post callPosting(PostingDto request){
        Post post = postRepository.findByPostId(request.getPid());

        if (post!=null){
            return post;
        }else {
            return null;
        }
    }
}
