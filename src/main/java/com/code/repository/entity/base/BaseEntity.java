package com.code.repository.entity.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.annotations.Update;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Id;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BaseEntity {
    @Id
    private String id;
    @UpdateTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;
}
