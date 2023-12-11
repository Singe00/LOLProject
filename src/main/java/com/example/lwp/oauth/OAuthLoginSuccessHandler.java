package com.example.lwp.oauth;

import com.example.lwp.domain.UserDomain;
import com.example.lwp.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
public class OAuthLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    UserService userService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;

        String email = null;
        String oauthType = token.getAuthorizedClientRegistrationId();

        if("google".equals(oauthType.toLowerCase())){
            email = token.getPrincipal().getAttribute("email").toString();
        }
        else if("naver".equals(oauthType.toLowerCase())){
            email=((Map<String, Object>)token.getPrincipal().getAttribute("response")).get("email").toString();
        }

        UserDomain user = userService.getUserByEmailAndOAuthType(email,oauthType);

        // 세션에 email 저장
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setMaxInactiveInterval(1800);

        // 클라이언트에게 LPGGCOOKIE라는 이름의 쿠키 전송
        Cookie sessionCookie = new Cookie("LPGGCOOKIE", email);
        sessionCookie.setPath("/");
        sessionCookie.setMaxAge(1800);
        response.addCookie(sessionCookie);

        super.onAuthenticationSuccess(request,response,authentication);
    }
}
