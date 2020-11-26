package com.code.repository.entity.base;

import lombok.*;
import org.apache.ibatis.annotations.Update;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private String id;
    @UpdateTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;
}
