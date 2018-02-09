package co.ceiba.negocio;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import co.ceiba.dominio.Vehiculo;
import co.ceiba.entity.VehiculoEntity;
import co.ceiba.interfaces.IVehiculoNegocio;
import co.ceiba.repositorio.RepositorioVehiculo;

@Service("VehiculoNegocio")
@EnableJpaRepositories("co.ceiba.repositorio")

public class VehiculoNegocio implements IVehiculoNegocio{
	
	@Autowired
	private RepositorioVehiculo vehiculoReposotory;
	
	@Override
	public int calcularPrecio(int numHoras, int cilindraje, String tipo) {
		int dias = 0;
		if(numHoras >= 9) {
			if(numHoras < 24) {
				numHoras=0;
				dias+=1;
			}
			else {
				dias= numHoras/24;
				numHoras -= 24*dias;
				if(numHoras >= 9) {
					numHoras = 0;
					dias += 1;
				}
			}
		}
		int precio = calcularPrecioTipo(tipo, dias, numHoras);
		if((tipo.equals("Moto"))&&(cilindraje > 500)) {
			precio+=2000;
		}
		return precio;
	}

	@Override
	public int calcularPrecioTipo(String tipo, int dias, int horas) {
		if(tipo.equals("Carro"))
			return (dias*8000)+(horas*1000);
		else
			return (dias*4000)+(horas*500);
	}

	public Vehiculo obtenerVehiculo(String placa) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(vehiculoReposotory.findByPlaca(placa),Vehiculo.class);
	}
	
	public void guardarVehiculoEnBD(Vehiculo vehiculo) {
		ModelMapper modelMapper = new ModelMapper();
		vehiculoReposotory.save(modelMapper.map(vehiculo, VehiculoEntity.class));
	}
}
