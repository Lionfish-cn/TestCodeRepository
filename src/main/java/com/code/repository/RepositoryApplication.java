package com.code.repository;

import com.code.repository.launch.XMLStaticLaunch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Slf4j
public class RepositoryApplication implements ApplicationRunner {
	public static void main(String[] args) {
		SpringApplication.run(RepositoryApplication.class, args);
	}

	@Autowired
	private XMLStaticLaunch xmlStaticLaunch;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("loading quartz xml configuration now");
		xmlStaticLaunch.launchQuartzXMLConfiguration();
		log.info("loading quartz xml configuration end");
	}
}
