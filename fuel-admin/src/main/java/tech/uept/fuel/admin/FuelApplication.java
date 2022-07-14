package tech.uept.fuel.admin;

import org.apache.rocketmq.client.log.ClientLogger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("tech.uept.fuel.admin.basic.mapper")
@EnableScheduling
public class FuelApplication {

    public static void main(String[] args) {
        SpringApplication.run(FuelApplication.class, args);
    }

    static {
        System.setProperty(ClientLogger.CLIENT_LOG_USESLF4J, "true");
    }
}
