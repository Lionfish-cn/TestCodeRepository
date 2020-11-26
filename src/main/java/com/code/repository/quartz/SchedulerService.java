package com.code.repository.quartz;

import com.code.repository.util.EntityUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class SchedulerService {
    private Scheduler scheduler;

    public void generateSchedule(String bean, String cronExpression) throws Exception {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        scheduler = schedulerFactory.getScheduler();
        String triggerbean = EntityUtil.parseNameByModelName(bean);
        JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(bean)).withIdentity("job" + triggerbean, "group" + triggerbean).build();

        Trigger trigger = null;
        if (CronExpression.isValidExpression(cronExpression)) {
            trigger = TriggerBuilder.newTrigger().withIdentity("trigger" + triggerbean, "triggerGroup" + triggerbean).startAt(new Date()).withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
        } else {
            trigger = TriggerBuilder.newTrigger().withIdentity("trigger" + triggerbean, "triggerGroup" + triggerbean).startAt(new Date()).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(Long.parseLong(cronExpression))).build();
        }
        scheduler.scheduleJob(jobDetail, trigger);
        log.info("开始执行定时任务");
        scheduler.start();
        log.info("结束执行定时任务");
    }

    public void removeJob(String index) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey("trigger" + index, "triggerGroup" + index);
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(JobKey.jobKey("trigger" + index, "triggerGroup" + index));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
