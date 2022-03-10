package com.example.board.security;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Component
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errMessage;

        if (exception instanceof BadCredentialsException) {
            errMessage = "비밀번호가 맞지 않습니다. 다시 확인해 주세요.";
        } else if (exception instanceof InternalAuthenticationServiceException) {
            errMessage = "내부 오류 발생 관리자에게 문의하세요.";
        } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
            errMessage = "인증 요청이 거부되었습니다. 관리자에게 문의하세요.";
        } else {
            errMessage = "로그인에 실패하였습니다 관리자에게 문의하세요.";
        }

        errMessage = URLEncoder.encode(errMessage, "UTF-8");
        setDefaultFailureUrl("/loginFail?error=true&exception="+errMessage);

        super.onAuthenticationFailure(request, response, exception);
    }
}
