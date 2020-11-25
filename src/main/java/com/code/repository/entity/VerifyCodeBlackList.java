package com.code.repository.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * 短信验证码黑名单
 */
@Builder
@Table(name = "lettercode_blacklist")
@AllArgsConstructor
@NoArgsConstructor
@ToString (callSuper = true)
@Data
public class VerifyCodeBlackList {
    @Column(name =  "l_phone")
    private String phone;
    @Column(name =  "l_reason")
    private String reason;
    @Column(name = "l_happentime")
    private Date happenTime;
}
