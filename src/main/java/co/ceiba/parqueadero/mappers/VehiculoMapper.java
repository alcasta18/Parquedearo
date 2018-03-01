package co.ceiba.parqueadero.mappers;

import org.springframework.stereotype.Component;

import co.ceiba.parqueadero.dominio.Vehiculo;
import co.ceiba.parqueadero.dominio.servicios.ValidacionesDelVigilante;
import co.ceiba.parqueadero.entity.VehiculoEntity;
import co.ceiba.parqueadero.excepcion.EntradaDeVehiculoExcepcion;
import co.ceiba.parqueadero.repositorio.RepositorioVehiculo;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;


@Component
public class VehiculoMapper {
	@Autowired
	private RepositorioVehiculo vehiculoRepo;
	@Autowired
	private ValidacionesDelVigilante validador;
	public Vehiculo obtenerVehiculo(String placa){		
		ModelMapper modelMapper = new ModelMapper();
		if(vehiculoRepo.exists(placa))
			return modelMapper.map(vehiculoRepo.findByPlaca(placa), Vehiculo.class);
		else
			throw new EntradaDeVehiculoExcepcion("No se encontro el vehiculo en la base de datos");		
	}

	public void guardarVehiculoEnBD(Vehiculo vehiculo) {
		vehiculo.setPlaca(vehiculo.getPlaca().toUpperCase());
		if(validador.estaDentroDelParqueadero(vehiculo.getPlaca())) {
			ModelMapper modelMapper = new ModelMapper();
			vehiculoRepo.save(modelMapper.map(vehiculo, VehiculoEntity.class));
		}
		else
			throw new EntradaDeVehiculoExcepcion("Este vehiculo ya se encuentra dentro del parqueadero, por favor revise la placa");
	}
	
}
