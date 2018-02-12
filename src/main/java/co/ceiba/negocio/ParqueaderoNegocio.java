package co.ceiba.negocio;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import co.ceiba.dominio.Parqueadero;
import co.ceiba.entity.ParqueaderoEntity;
import co.ceiba.interfaces.IParqueaderoNegocio;
import co.ceiba.repositorio.RepositorioParqueadero;

@Service("ParqueaderoNegocio")
@EnableJpaRepositories("co.ceiba.repositorio")


public class ParqueaderoNegocio implements IParqueaderoNegocio{
	public static final String CARRO = "Carro";
	public static final String MOTO = "Moto";
	public static final char AMAYUS = 'A';
	public static final char AMINUS = 'a';
	public static final int DOMINGO = 1;
	public static final int LUNES =  2;
	public static final int CERO = 0;
	
	@Autowired
	private RepositorioParqueadero repositorioParqueadero;
	
	@Override
	public boolean vehiculoPuedeEntrar(String placa ,int dia) {
		boolean carroEntra= true;
		if((placa.charAt(0)==AMAYUS)||(placa.charAt(0)==AMINUS)){
			if((dia == DOMINGO)||(dia == LUNES)) {
				carroEntra = true;
			}
			else {
				carroEntra = false;
			}
		}
		else {
			carroEntra = true;
		}		
		return carroEntra;
	}
	
	@Override
	public boolean hayCupo(String tipo,int capacidadCarros,int capacidadMotos) {
		boolean carroEntra = true;
		if(tipo.equals(CARRO)&&(capacidadCarros == CERO)) 
			carroEntra = false;
		if(tipo.equals(MOTO)&&(capacidadMotos == CERO)) 
			carroEntra = false;

		return carroEntra;
	}
	
	public void actualizarCapacidadAlIngresoDeVehiculo(String tipo, int parqueaderoId) {
		Parqueadero parqueadero = obtenerParqueadero(parqueaderoId);
		if(hayCupo(tipo,parqueadero.getCapacidadCarros(),parqueadero.getCapacidadMotos())) {
			if(tipo.equals(CARRO))
				parqueadero.setCapacidadCarros(parqueadero.getCapacidadCarros()-1);
			else if(tipo.equals(MOTO))
				parqueadero.setCapacidadMotos(parqueadero.getCapacidadMotos()-1);
		}
		actualizarParqueadero(parqueadero);
	}
	
	public void actualizarCapacidadAlSalirUnVehiculo(String tipo, int parqueaderoId) {
		Parqueadero parqueadero = obtenerParqueadero(parqueaderoId);
		if(tipo.equals(CARRO))
			parqueadero.setCapacidadCarros(parqueadero.getCapacidadCarros()+1);
		else if(tipo.equals(MOTO))
			parqueadero.setCapacidadMotos(parqueadero.getCapacidadMotos()+1);
		actualizarParqueadero(parqueadero);
		
	}
	
	
	@Override
	public Parqueadero obtenerParqueadero(int parqueaderoId) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(repositorioParqueadero.findOne(parqueaderoId),Parqueadero.class);
	}
	
	public void actualizarParqueadero(Parqueadero parqueadero) {
		ModelMapper modelMapper = new ModelMapper();
		repositorioParqueadero.save(modelMapper.map(parqueadero,ParqueaderoEntity.class));
	}
}
