package com.example.lwp.repository;

import com.example.lwp.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    Comment findByCommentId(Long cid);

    @Query("SELECT c, u FROM Comment c JOIN UserDomain u ON c.userId = u.userId WHERE c.postId = :postId order by c.commentId ASC")
    List<Object> findCommentAndUserByPostId(@Param("postId") Long postId);

    int countByPostId(Long pid);
}
