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
@RequestMapping("/rest/parqueadero")
@EnableJpaRepositories("co.ceiba.repositorio")
@Import(value = ParqueaderoEntity.class)
public class ParqueaderoRest {	
	@Autowired
	private ParqueaderoNegocio parqueaderoN;
	
	
/*	@PostMapping ("/crear")
	public void crear(@RequestBody Parqueadero parqueadero) {
		parqueaderoEntity = modelMapper.map(parqueadero, ParqueaderoEntity.class);
		repositorioParqueadero.save(parqueaderoEntity);		
	}*/
	
/*	@RequestMapping("/listar")
	public List<ParqueaderoEntity> consultar() {
		return repositorioParqueadero.findAll();
	}*/
	

	@RequestMapping(value = "consultar/parqueaderoId={parqueaderoId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Parqueadero consultar(@PathVariable("parqueaderoId") int parqueaderoId) {
		return parqueaderoN.obtenerParqueadero(parqueaderoId);
	}
	
	
}
