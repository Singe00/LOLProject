package com.example.lwp.repository;

import com.example.lwp.domain.Comment;
import com.example.lwp.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findAll();
    List<Post> findAllByCategory(long category);
    List<Post> findAllByTitleContaining(String title);
    List<Post> findAllByTitleContainingOrContentContaining(String title,String content);
    Post findByPostId(Long postId);

    @Query("SELECT p, u FROM Post p JOIN UserDomain u ON p.userId = u.userId WHERE p.postId = :postId")
    Optional<Object[]> findPostAndUserByPostId(@Param("postId") Long postId);
}
