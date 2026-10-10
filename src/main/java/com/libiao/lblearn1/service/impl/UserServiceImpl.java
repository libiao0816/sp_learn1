package com.libiao.lblearn1.service.impl;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.libiao.lblearn1.common.properties.JwtProperties;
import com.libiao.lblearn1.common.utils.JwtUtil;
import com.libiao.lblearn1.common.utils.TokenBlackList;
import com.libiao.lblearn1.domain.dto.user.UserLoginDTO;
import com.libiao.lblearn1.domain.dto.user.UserRegisterDTO;
import com.libiao.lblearn1.domain.po.User;
import com.libiao.lblearn1.domain.po.UserRole;
import com.libiao.lblearn1.mapper.UserMapper;
import com.libiao.lblearn1.common.context.UserContext;
import com.libiao.lblearn1.common.enums.ResultCodeEnum;
import com.libiao.lblearn1.common.enums.UserErrorCodeEnum;
import com.libiao.lblearn1.common.exception.BusinessException;
import com.libiao.lblearn1.service.UserRoleService;
import com.libiao.lblearn1.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final JwtProperties jwtProperties;

    private final TokenBlackList tokenBlackList;

    private final UserRoleService userRoleService;

    @Override
    public String login(UserLoginDTO userLoginDTO) {
        // 只按用户名查询：BCrypt 每次 encode 的盐都不同，绝不能拿 encode 结果当查询条件
        User dbUser = lambdaQuery()
                .eq(User::getUsername, userLoginDTO.getUsername())
                .one();
        if (dbUser == null) {
            throw new BusinessException(UserErrorCodeEnum.USER_NOT_FOUND.getCode(),
                    UserErrorCodeEnum.USER_NOT_FOUND.getMsg());
        }
        if (!passwordEncoder.matches(userLoginDTO.getPassword(), dbUser.getPassword())) {
            throw new BusinessException(UserErrorCodeEnum.PASSWORD_ERROR.getCode(),
                    UserErrorCodeEnum.PASSWORD_ERROR.getMsg());
        }
        List<String> userRoles = userRoleService.findRoleByUserId(dbUser.getId());
        return JwtUtil.createToken(dbUser, jwtProperties.getSecret(), jwtProperties.getTtl(), userRoles);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer register(UserRegisterDTO userRegisterDTO) {
        User dbUser = lambdaQuery()
                .eq(User::getUsername, userRegisterDTO.getUsername())
                .one();
        if (Objects.nonNull(dbUser)) {
            throw new BusinessException(UserErrorCodeEnum.USERNAME_ALREADY_EXISTS.getCode(),
                    UserErrorCodeEnum.USERNAME_ALREADY_EXISTS.getMsg());
        }
        User user = User.builder()
                .username(userRegisterDTO.getUsername())
                .password(passwordEncoder.encode(userRegisterDTO.getPassword()))
                .build();

        int insert = userMapper.insert(user);

        if (insert <= 0) {
            throw new BusinessException(ResultCodeEnum.SYSTEM_ERROR);
        }
        return insert;
    }

    @Override
    public Boolean logout(String token) {
        Claims claims = JwtUtil.parseToken(token, jwtProperties.getSecret());
        tokenBlackList.addBlackList(claims.getId(), claims.getExpiration().getTime());
        return true;
    }

    @Override
    public User getUserDetail() {
        User contextUser = UserContext.getUser();
        return lambdaQuery()
                .eq(User::getId, contextUser.getId())
                .one();
    }
}
