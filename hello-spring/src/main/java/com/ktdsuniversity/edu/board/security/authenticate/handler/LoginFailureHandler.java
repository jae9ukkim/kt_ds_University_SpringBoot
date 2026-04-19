package com.ktdsuniversity.edu.board.security.authenticate.handler;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.ktdsuniversity.edu.members.dao.MembersDao;
import com.ktdsuniversity.edu.members.vo.request.LoginVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler{
    
    private static final Logger logger = LoggerFactory.getLogger(LoginFailureHandler.class);
    
    @Autowired
    private MembersDao membersDao;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        
        logger.error(exception.getMessage(), exception);

        String email = request.getParameter("email");
        
        if(exception instanceof BadCredentialsException) {
            this.membersDao.updateIncreaseLoginFailCount(email);
            this.membersDao.updateBlock(email);
        }
        
        String loginPagePath = "/WEB-INF/view/members/login.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(loginPagePath);
        
        LoginVO loginVO = new LoginVO();
        loginVO.setEmail(email);
        
        request.setAttribute("inputData", loginVO);
        request.setAttribute("errorMessage", exception.getMessage());
        
        dispatcher.forward(request, response);
    }

}
