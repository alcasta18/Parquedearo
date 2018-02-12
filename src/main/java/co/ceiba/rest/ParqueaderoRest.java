package co.ceiba.rest;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.dominio.Parqueadero;
import co.ceiba.entity.ParqueaderoEntity;
import co.ceiba.negocio.ParqueaderoNegocio;

@RestController
@RequestMapping("/api/parqueadero")
@EnableJpaRepositories("co.ceiba.repositorio")
@Import(value = ParqueaderoEntity.class)
public class ParqueaderoRest {	
	@Autowired
	private ParqueaderoNegocio parqueaderoN;
	
	
	@RequestMapping(value = "consultar/parqueaderoId={parqueaderoId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Parqueadero consultar(@PathVariable("parqueaderoId") int parqueaderoId) {
		return parqueaderoN.obtenerParqueadero(parqueaderoId);
	}
	
	@PostMapping("/actualizar")
	public void actualizar(@RequestBody Parqueadero parqueadero) {
		parqueaderoN.actualizarParqueadero(parqueadero);
	}
	
	
}
