package com.code.repository.controller;

import com.code.repository.quartz.SchedulerService;
import com.code.repository.redis.util.RedisUtil;
import com.code.repository.util.EntityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@Slf4j
public class QuartzController {
    public static int index = 0;
    @Autowired
    private SchedulerService schedulerService;

    @RequestMapping("/startQuartz")
    public void startQuartz(HttpServletRequest request) {
        log.info("Starting quartz now");
        index++;
        String bean = request.getParameter("bean");
        Object cron = RedisUtil.stringGetOperations(bean, null, "");

        try {
            schedulerService.generateSchedule(bean, cron.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/pauseQuartz")
    public void pauseQuartz(HttpServletRequest request) {
        String bean = request.getParameter("bean");
        schedulerService.removeJob(EntityUtil.parseNameByModelName(bean));
    }

    @RequestMapping("/quartzList")
    public ModelAndView quartzList(HttpServletRequest request) {
        Map<Object, Object> map = (Map<Object, Object>) RedisUtil.hashGetStream("quartz", "");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("quartz/quartzlist");
        return modelAndView;
    }
}
