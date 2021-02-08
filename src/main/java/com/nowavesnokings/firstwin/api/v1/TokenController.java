package com.nowavesnokings.firstwin.api.v1;

import com.google.common.collect.Maps;
import com.nowavesnokings.firstwin.core.enumeration.UnifyResponseCode;
import com.nowavesnokings.firstwin.core.exception.http.NotFoundException;
import com.nowavesnokings.firstwin.pojo.dto.TokenDTO;
import com.nowavesnokings.firstwin.pojo.dto.TokenGetDTO;
import com.nowavesnokings.firstwin.service.AuthenticationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ssx
 * @version V1.0
 * @className TokenController
 * @description 令牌控制器
 * @date 2020-12-14 14:25
 * @since 1.8
 */
@RestController
@RequestMapping(value = "token")
@Validated
@Api(value = "jwt获取", tags = {"jwt获取"})
public class TokenController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public Map<String, String> getToken(@Validated @RequestBody TokenGetDTO tokenGetDTO) {
        Map<String, String> tokenMap = Maps.newHashMap();
        String token = null;
        switch (tokenGetDTO.getLoginType()) {
            case USER_WX:
                token = authenticationService.verifyByWx(tokenGetDTO);
                break;
            case USER_TELEPHONE:
                break;
            default:
                throw new NotFoundException(UnifyResponseCode.LOGIN_TYPE_NOT_FOUND.getCode());
        }
        tokenMap.put("token", token);
        return tokenMap;
    }

    @PostMapping("/verify")
    public Map<String, Boolean> verifyToken(@RequestBody TokenDTO tokenDTO) {
        Map<String, Boolean> verifyTokenMap = Maps.newHashMap();
        Boolean verifyToken = authenticationService.verifyToken(tokenDTO.getToken());
        verifyTokenMap.put("result", verifyToken);
        return verifyTokenMap;
    }

}
