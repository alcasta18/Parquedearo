package co.ceiba.principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages= {"co.ceiba.services"})
@EntityScan("co.ceiba.entity")
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
