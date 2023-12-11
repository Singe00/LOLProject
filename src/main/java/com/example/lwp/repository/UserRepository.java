package com.example.lwp.repository;

import com.example.lwp.domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDomain,Long> {
    Optional<UserDomain> findByEmailAndOauthType(String email, String oauthType);
}
