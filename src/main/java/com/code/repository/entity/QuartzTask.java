package com.code.repository.entity;

import com.code.repository.entity.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 定时任务
 */
@Builder
@Table(name = "quartz_task")
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Data
public class QuartzTask extends BaseEntity {
    @Column(name = "l_name")
    private String name;
    @Column(name = "l_cron")
    private String cron;
    @Column(name = "l_bean")
    private String bean;
    @Column(name = "l_comment")
    private String comment;
}
