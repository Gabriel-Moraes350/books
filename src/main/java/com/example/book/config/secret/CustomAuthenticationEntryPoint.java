package com.example.book.config.secret;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {
        res.setContentType("application/json;charset=UTF-8");
        Map<String, String> map = new HashMap<>();
        map.put("Error", "Forbidden");
        map.put("Message", "Not allowed");
        map.put("path", req.getRequestURI());

        ObjectMapper mapper = new ObjectMapper();

        res.setStatus(403);
        res.getWriter().write(mapper.writeValueAsString(map));
    }

}
