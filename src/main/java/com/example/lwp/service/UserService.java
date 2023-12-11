package com.example.lwp.service;

import com.example.lwp.domain.UserDomain;
import com.example.lwp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class UserService extends DefaultOAuth2UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        Map<String, Object> attributes = super.loadUser(userRequest).getAttributes();

        String email = null;
        String oauthType = userRequest.getClientRegistration().getRegistrationId();

        OAuth2User user2 = super.loadUser(userRequest);


        if("google".equals(oauthType.toLowerCase())){
            email = attributes.get("email").toString();
        }
        else if("naver".equals(oauthType.toLowerCase())){
            email=((Map<String, Object>)attributes.get("response")).get("email").toString();
        }

        if (getUserByEmailAndOAuthType(email,oauthType) == null) {
            UserDomain user = new UserDomain();
            user.setEmail(email);
            user.setOauthType(oauthType);

            save(user);
        }
        return super.loadUser(userRequest);
    }

    public void save(UserDomain user) {
        userRepository.save(user);
    }

    public UserDomain getUserByEmailAndOAuthType(String email,String oauthType){
        return userRepository.findByEmailAndOauthType(email,oauthType).orElse(null);
    }
}
