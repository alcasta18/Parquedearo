package co.ceiba.parqueadero.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.ceiba.parqueadero.dominio.Parqueadero;
import co.ceiba.parqueadero.entity.ParqueaderoEntity;
import co.ceiba.parqueadero.repositorio.RepositorioParqueadero;

@Component
public class RepositorioParqueaderoMapper {
	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private RepositorioParqueadero repositorioParqueadero;
	
	public void actualizarParqueadero(Parqueadero parqueadero) {
		repositorioParqueadero.save(modelMapper.map(parqueadero,ParqueaderoEntity.class));
	}
	
	public Parqueadero obtenerParqueadero(int parqueaderoId) {
		
		return modelMapper.map(repositorioParqueadero.findOne(parqueaderoId),Parqueadero.class);
	}

}
