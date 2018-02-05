package co.ceiba.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.dominio.Vehiculo;
import co.ceiba.entity.VehiculoEntity;
import co.ceiba.repositorio.RepositorioVehiculo;

@RestController
@RequestMapping("/rest/vehiculo")
@EnableJpaRepositories("co.ceiba.repositorio")
@Import(VehiculoEntity.class)
public class VehiculoService {
	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private VehiculoEntity vehiculoEntity;
	@Autowired
	private RepositorioVehiculo vehiculoReposotory;
	
	@PostMapping("/crear")
	public void crear(@RequestBody Vehiculo vehiculo) {
		vehiculoEntity = modelMapper.map(vehiculo, VehiculoEntity.class);
		vehiculoReposotory.save(vehiculoEntity);
	}
	
	@RequestMapping("/buscar")
	public Vehiculo buscar(@RequestBody String placa) {
		return modelMapper.map(vehiculoReposotory.findByPlaca(placa),Vehiculo.class);
	}
}
