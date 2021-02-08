package com.nowavesnokings.firstwin.service.impl;

import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.nowavesnokings.firstwin.core.enumeration.UnifyResponseCode;
import com.nowavesnokings.firstwin.core.exception.http.ParameterException;
import com.nowavesnokings.firstwin.dao.UserRepository;
import com.nowavesnokings.firstwin.pojo.dto.TokenGetDTO;
import com.nowavesnokings.firstwin.pojo.model.User;
import com.nowavesnokings.firstwin.properties.FirstwinProperties;
import com.nowavesnokings.firstwin.service.AuthenticationService;
import com.nowavesnokings.firstwin.util.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Map;

/**
 * @author ssx
 * @version V1.0
 * @className AuthenticationServiceImpl
 * @description
 * @date 2020-12-14 15:25
 * @since 1.8
 */
@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    /**
     * The User repository.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * The Object mapper.
     */
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FirstwinProperties firstwinProperties;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    /**
     * 通过微信验证.
     *
     * @param tokenGetDTO the token dto
     * @return the string
     */
    @Override
    public String verifyByWx(TokenGetDTO tokenGetDTO) {
        //根据account，code2session获取用户openid
        String code2session = MessageFormat.format(firstwinProperties.getWechat().getCode2session(),
                firstwinProperties.getWechat().getAppid(),
                firstwinProperties.getWechat().getAppSecret(), tokenGetDTO.getAccount());
        //TODO 将修改为okhttp请求
        String codeRes = HttpUtil.get(code2session);
        Map<String, Object> codeResMap = Maps.newHashMap();
        try {
            codeResMap = objectMapper.readValue(codeRes, Map.class);
        } catch (JsonProcessingException e) {
            log.error("微信登录返回结果{}，反序列化发生错误{}", codeRes, e.getMessage());
        }
        return this.registerUser(codeResMap);
    }

    /**
     * 验证token.
     *
     * @param token the token
     * @return the boolean
     */
    @Override
    public Boolean verifyToken(String token) {
        return jwtTokenUtils.verifyToken(token);
    }

    /**
     * 注册
     *
     * @param codeResMap 微信返回用户数据
     * @return the string
     */
    private String registerUser(Map<String, Object> codeResMap) {
        String openid = (String) codeResMap.get("openid");
        //判断openid是否存在
        if (StringUtils.isBlank(openid)) {
            throw new ParameterException(UnifyResponseCode.GET_OPENID_ERROR.getCode());
        }
        //判断用户是否注册
        User user = userRepository.getByOpenid(openid);
        //如果没有注册，先注册
        if (user == null) {
            user = User.builder().openid(openid)
                    .scope(firstwinProperties.getDefaultScope())
                    .build();
            userRepository.save(user);
        }
        //如果已注册，根据uid和用户scope创建token
        return jwtTokenUtils.makeToken(user.getId(), user.getScope());
    }
}
