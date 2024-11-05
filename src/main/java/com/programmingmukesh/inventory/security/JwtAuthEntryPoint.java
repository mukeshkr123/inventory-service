package com.programmingmukesh.inventory.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programmingmukesh.inventory.response.BaseResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        BaseResponse<String> baseResponse = BaseResponse.error(authException.getMessage());
        baseResponse.setError("Unauthorized");
        baseResponse.setData(null);

        mapper.writeValue(response.getOutputStream(), baseResponse);
    }
}
