package com.uzak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Created by Administrator on 2018/8/26.
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ReportApplication {

    public static void main(String[] args) {
        System.setProperty("java.net.preferIPv4Stack" , "true");
        SpringApplication.run(ReportApplication.class, args);
    }

}
