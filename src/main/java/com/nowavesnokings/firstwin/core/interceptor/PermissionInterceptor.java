package com.nowavesnokings.firstwin.core.interceptor;

import com.auth0.jwt.interfaces.Claim;
import com.nowavesnokings.firstwin.core.annotation.ScopeLevel;
import com.nowavesnokings.firstwin.core.concurrent.local.LocalUser;
import com.nowavesnokings.firstwin.core.enumeration.UnifyResponseCode;
import com.nowavesnokings.firstwin.core.exception.http.ForbiddenException;
import com.nowavesnokings.firstwin.core.exception.http.UnauthorizedException;
import com.nowavesnokings.firstwin.pojo.model.User;
import com.nowavesnokings.firstwin.properties.FirstwinProperties;
import com.nowavesnokings.firstwin.service.UserService;
import com.nowavesnokings.firstwin.util.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

/**
 * @author ssx
 * @version V1.0
 * @className JwtTokenInterceptor
 * @description jwtTokenl令牌拦截器
 * @date 2020-12-16 14:09
 * @since 1.8
 */
@Slf4j
@Component
public class PermissionInterceptor extends HandlerInterceptorAdapter {
    /**
     * The User service.
     */
    @Autowired
    private UserService userService;

    @Autowired
    private FirstwinProperties firstwinProperties;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Optional<ScopeLevel> scopeLevel = this.getScopeLevel(handler);
        if (!scopeLevel.isPresent()) {
            return true;
        }
        String token = getToken(request);
        Optional<Map<String, Claim>> claimMapOptional = jwtTokenUtils.getClaims(token);
        Map<String, Claim> claimMap = claimMapOptional.orElseThrow(() -> new UnauthorizedException(UnifyResponseCode.TOKEN_ILLEGAL_OR_EXPIRE.getCode()));
        Boolean isValid = hasPermission(scopeLevel.get(), claimMap);
        if (Boolean.TRUE.equals(isValid)) {
            setThreadLocalUser(claimMap);
        }
        return isValid;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LocalUser.removeUser();
        super.afterCompletion(request, response, handler, ex);
    }

    /**
     * 获取header中token.
     *
     * @param request the request
     * @return the token
     */
    private String getToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isBlank(authorization)) {
            throw new UnauthorizedException(UnifyResponseCode.TOKEN_ILLEGAL_OR_EXPIRE.getCode());
        }
        if (!authorization.startsWith(firstwinProperties.getJwt().getTokenPrefix())) {
            throw new UnauthorizedException(UnifyResponseCode.TOKEN_ILLEGAL_OR_EXPIRE.getCode());
        }
        return authorization.split(" ")[1];
    }

    /**
     * 验证token是否有权限.
     *
     * @param scopeLevel the scope level
     * @param claimMap   the claim map
     * @return the boolean
     */
    private Boolean hasPermission(ScopeLevel scopeLevel, Map<String, Claim> claimMap) {
        //验证token，获取用户信息

        if (scopeLevel.level() > claimMap.get("scope").asInt()) {
            throw new ForbiddenException(UnifyResponseCode.UNAUTHORIZED_ERROR.getCode());
        }
        return true;
    }

    private void setThreadLocalUser(Map<String, Claim> claimMap) {
        Long uid = claimMap.get("uid").asLong();
        User user = userService.getUserById(uid);
        LocalUser.setUser(user);
    }

    /**
     * 判断方法是否有scopeLevel注解.
     *
     * @param handler the handler
     * @return the scope level
     */
    private Optional<ScopeLevel> getScopeLevel(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            ScopeLevel scopeLevel = handlerMethod.getMethod().getDeclaredAnnotation(ScopeLevel.class);
            if (scopeLevel == null) {
                return Optional.empty();
            }
            return Optional.of(scopeLevel);
        }

        return Optional.empty();
    }

}
