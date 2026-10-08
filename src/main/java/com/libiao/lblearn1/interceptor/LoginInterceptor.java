package com.libiao.lblearn1.interceptor;

import java.util.Map;
import java.util.Objects;

import com.libiao.lblearn1.common.context.UserContext;
import com.libiao.lblearn1.common.properties.AuthProperties;
import com.libiao.lblearn1.common.result.Result;
import com.libiao.lblearn1.common.utils.JwtUtil;
import com.libiao.lblearn1.common.utils.TokenBlackList;
import com.libiao.lblearn1.domain.po.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.libiao.lblearn1.common.enums.ResultCodeEnum;
import com.libiao.lblearn1.common.exception.BusinessException;
import com.libiao.lblearn1.common.properties.JwtProperties;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private final JwtProperties jwtProperties;

    private final TokenBlackList  tokenBlackList;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String token = request.getHeader(jwtProperties.getTokenName());

        if (Objects.isNull(token) || token.isEmpty()) {
            throw new BusinessException(ResultCodeEnum.AUTHENTICATION_FAILED.getCode(), "请先进行登陆"); // ★
        }

        Claims claims = null;
        try {
            claims = JwtUtil.parseToken(token, jwtProperties.getSecret());
        } catch (ExpiredJwtException e) {
            throw new BusinessException(ResultCodeEnum.AUTHENTICATION_FAILED.getCode(), "token 已过期，请重新登录"); // ★
        } catch (Exception e) {
            throw new BusinessException(ResultCodeEnum.AUTHENTICATION_FAILED.getCode(),
                    ResultCodeEnum.AUTHENTICATION_FAILED.getMsg()); // ★
        }
        if (claims != null) {
            if(tokenBlackList.isBlackList(claims.getId())){
                throw new BusinessException(ResultCodeEnum.AUTHENTICATION_FAILED.getCode(), "token 已失效，请重新登录"); // ★
            }
            UserContext.setUser(User.builder().id(Long.parseLong(claims.getSubject()))
                    .username(claims.get("username").toString()).build());
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        UserContext.removeUser();
    }

}
