package com.nowavesnokings.firstwin.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;
import com.nowavesnokings.firstwin.properties.FirstwinProperties;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

/**
 * @author ssx
 * @version V1.0
 * @className JwtTokenUtils
 * @description jwt令牌工具类
 * @date 2020-12-14 15:04
 * @since 1.8
 */
@Component
public class JwtTokenUtils {

    /**
     * The constant jwtProperties.
     */
    private FirstwinProperties firstwinProperties;

    /**
     * Sets jwt properties.
     *
     * @param firstwinProperties the jwt properties
     */
    @Autowired
    public void setJwtProperties(FirstwinProperties firstwinProperties) {
        this.firstwinProperties = firstwinProperties;
    }

    /**
     * 生成jwt.
     *
     * @param uid   the uid
     * @param scope the scope
     * @return the string
     */
    public String makeToken(Long uid, Integer scope) {
        return this.getToken(uid, scope);
    }

    /**
     * 生成jwt(默认scope).
     *
     * @param uid the uid
     * @return the string
     */
    public String makeToken(Long uid) {
        return this.getToken(uid, firstwinProperties.getDefaultScope());
    }

    /**
     * 验证token是否有效.并解析用户数据
     *
     * @param token the token
     * @return the boolean
     */
    public Optional<Map<String, Claim>> getClaims(String token) {
        Algorithm algorithm = Algorithm.HMAC256(this.firstwinProperties.getJwt().getSecret());
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT;
        try {
            decodedJWT = jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            return Optional.empty();
        }
        return Optional.of(decodedJWT.getClaims());
    }

    /**
     * 验证token是否有效.
     *
     * @param token the token
     * @return the boolean
     */
    public Boolean verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(this.firstwinProperties.getJwt().getSecret());
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            return false;
        }
        return true;
    }

    /**
     * 生成令牌.
     *
     * @param uid   the uid
     * @param scope the scope
     * @return the token
     */
    private String getToken(Long uid, Integer scope) {
        Map<String, Date> expiredIssues = this.calculateExpiredIssues();
        Algorithm algorithm = Algorithm.HMAC256(this.firstwinProperties.getJwt().getSecret());
        return JWT.create().withClaim("uid", uid)
                .withClaim("scope", scope)
                .withExpiresAt(expiredIssues.get("expiredTime"))
                .withIssuedAt(expiredIssues.get("now"))
                .sign(algorithm);
    }

    /**
     * 计算过期时间.
     *
     * @return the map
     */
    private Map<String, Date> calculateExpiredIssues() {
        Map<String, Date> expiredIssuesMap = Maps.newHashMap();
        Date now = new Date();
        expiredIssuesMap.put("now", now);
        expiredIssuesMap.put("expiredTime", DateUtils.addSeconds(now, this.firstwinProperties.getJwt().getExpiredTime()));
        return expiredIssuesMap;
    }
}
