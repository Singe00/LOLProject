package com.example.lwp.repository;

import com.example.lwp.domain.Comment;
import com.example.lwp.domain.Likepost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikepostRepository extends JpaRepository<Likepost,Long> {
    List<Likepost> findAllByPostId(Long pid);

    int countByPostId(Long pid);
}
