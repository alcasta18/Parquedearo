package co.ceiba.parqueadero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan({"co.ceiba.parqueadero.rest"})
@ComponentScan({"co.ceiba.parqueadero.dominio.servicios"})
@ComponentScan({"co.ceiba.parqueadero.mappers"})
@EntityScan("co.ceiba.parqueadero.entity")
@EnableJpaRepositories("co.ceiba.parqueadero.repositorio")
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
