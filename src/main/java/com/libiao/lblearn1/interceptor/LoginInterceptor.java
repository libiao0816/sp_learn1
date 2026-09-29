package com.libiao.lblearn1.interceptor;

import java.util.Objects;

import com.libiao.lblearn1.common.result.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.libiao.lblearn1.common.enums.ResultCodeEnum;
import com.libiao.lblearn1.common.properties.JwtProperties;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private final JwtProperties jwtProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String token = request.getHeader(jwtProperties.getTokenName());

        if (Objects.isNull(token) || token.isEmpty()) {
            response.setStatus(ResultCodeEnum.AUTHENTICATION_FAILED.getCode());
            response.getWriter().write(Result.Error(ResultCodeEnum.AUTHENTICATION_FAILED.getCode(),
                    ResultCodeEnum.AUTHENTICATION_FAILED.getMsg()).toString());
            return false;
        }

        return true;
    }
}
