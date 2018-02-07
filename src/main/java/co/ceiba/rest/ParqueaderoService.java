package co.ceiba.rest;



import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.dominio.Parqueadero;
import co.ceiba.entity.ParqueaderoEntity;
import co.ceiba.repositorio.RepositorioParqueadero;

@RestController
@RequestMapping("/rest/parqueadero")
@EnableJpaRepositories("co.ceiba.repositorio")
@Import(value = ParqueaderoEntity.class)
public class ParqueaderoService {	
	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private ParqueaderoEntity parqueaderoEntity;
	@Autowired
	private RepositorioParqueadero repositorioParqueadero;
	
	
	@PostMapping ("/crear")
	public void crear(@RequestBody Parqueadero parqueadero) {
		parqueaderoEntity = modelMapper.map(parqueadero, ParqueaderoEntity.class);
		repositorioParqueadero.save(parqueaderoEntity);		
	}
	
	@RequestMapping("/listar")
	public List<ParqueaderoEntity> consultar() {
		return repositorioParqueadero.findAll();
	}
	

	@RequestMapping("/consultar")
	public Parqueadero consultar(@RequestBody String parqueaderoId) {
		return modelMapper.map(repositorioParqueadero.findOne(parqueaderoId),Parqueadero.class);
	}
	
	
}
