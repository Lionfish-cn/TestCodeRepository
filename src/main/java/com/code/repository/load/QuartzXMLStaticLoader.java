package com.code.repository.load;

import com.code.repository.quartz.SchedulerService;
import com.code.repository.redis.util.RedisUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Iterator;

@Component
public class QuartzXMLStaticLoader {
    @Autowired
    private SchedulerService schedulerService;

    public void launchQuartzXMLConfiguration() {
        try {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(new File("src/main/resources/static/quartz.xml"));
            Element root = doc.getRootElement();
            Iterator it = root.elementIterator();
            int i = 0;
            while (it.hasNext()) {
                i++;
                Element ele = (Element) it.next();
                String bean = ele.attributeValue("bean");
                String cron = ele.attributeValue("cron");
                RedisUtil.hashSetOperations("quartz", bean, cron, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
