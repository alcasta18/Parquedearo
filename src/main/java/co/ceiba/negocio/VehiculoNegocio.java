package co.ceiba.negocio;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import co.ceiba.dominio.Vehiculo;
import co.ceiba.entity.VehiculoEntity;
import co.ceiba.interfaces.IVehiculoNegocio;
import co.ceiba.repositorio.RepositorioVehiculo;

@Service("VehiculoNegocio")
@EnableJpaRepositories("co.ceiba.repositorio")

public class VehiculoNegocio implements IVehiculoNegocio{
	
	private static final int PRECIO_HORA_MOTO = 500;
	private static final int PRECIO_DIA_MOTO = 4000;
	private static final int PRECIO_HORA_CARRO = 1000;
	private static final int PRECIO_DIA_CARRO = 8000;
	private static final String CARRO = "Carro";
	private static final String MOTO = "Moto";
	private static final int SOBRECOSTO_CILINDRAJE = 2000;
	private static final int HORAS_DE_UN_DIA = 24;
	private static final int HORAS_TOPE_PARA_COBRAR_DIA = 9;
	@Autowired
	private RepositorioVehiculo vehiculoReposotory;
	
	
	
	@Override
	public int calcularPrecio(int numHoras, int cilindraje, String tipo) {
		int dias = 0;
		if(numHoras >= HORAS_TOPE_PARA_COBRAR_DIA) {
			if(numHoras < HORAS_DE_UN_DIA) {
				numHoras=0;
				dias+=1;
			}
			else {
				dias= numHoras/HORAS_DE_UN_DIA;
				numHoras -= HORAS_DE_UN_DIA*dias;
				if(numHoras >= HORAS_TOPE_PARA_COBRAR_DIA) {
					numHoras = 0;
					dias += 1;
				}
			}
		}
		int precio = calcularPrecioTipo(tipo, dias, numHoras);
		if((tipo.equals(MOTO))&&(cilindraje > 500)) {
			precio+=SOBRECOSTO_CILINDRAJE;
		}
		return precio;
	}

	@Override
	public int calcularPrecioTipo(String tipo, int dias, int horas) {
		if(tipo.equals(CARRO))
			return (dias*PRECIO_DIA_CARRO)+(horas*PRECIO_HORA_CARRO);
		else
			return (dias*PRECIO_DIA_MOTO)+(horas*PRECIO_HORA_MOTO);
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
