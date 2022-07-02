package tech.uept.fuel.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("tech.uept.fuel.admin.basic.dao.mapper")
public class FuelApplication {
	public static void main(String[] args) {
		SpringApplication.run(FuelApplication.class, args);
	}
}
