package co.ceiba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"co.ceiba.rest"})
@ComponentScan({"co.ceiba.negocio"})
@EntityScan("co.ceiba.entity")
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
