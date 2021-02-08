package com.nowavesnokings.firstwin.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author ssx
 * @version V1.0
 * @className TokenDTO
 * @description token传输对象
 * @date 2020-12-21 15:55
 * @since 1.8
 */
@Setter
@Getter
@ToString
public class TokenDTO {
    /**
     * 令牌
     */
    @NotBlank(message = "{token.notBlank.message}")
    private String token;
}
