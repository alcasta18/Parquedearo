package co.ceiba.negocio;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import co.ceiba.dominio.Parqueadero;
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
		boolean mensaje= true;
		if((placa.charAt(0)==AMAYUS)||(placa.charAt(0)==AMINUS)){
			if((dia == DOMINGO)||(dia == LUNES)) {
				mensaje = true;
			}
			else {
				mensaje = false;
			}
		}
		else {
			mensaje = true;
		}		
		return mensaje;
	}
	
	@Override
	public boolean hayCupo(String tipo,int capacidadCarros,int capacidadMotos) {
		boolean vacio = true;
		if(tipo.equals(CARRO)&&(capacidadCarros == CERO)) 
			vacio = false;
		if(tipo.equals(MOTO)&&(capacidadMotos == CERO)) 
			vacio = false;

		return vacio;
	}
	
	@Override
	public int ingresarVehiculo(String tipo,int capacidadCarros,int capacidadMotos) {
		if(hayCupo(tipo,capacidadCarros,capacidadMotos)) {
			if(tipo.equals(CARRO))
				return capacidadCarros-1;
			else
				return capacidadMotos-1;
		}
		else {
			return 0;
		}
	}

	@Override
	public int sacarVehiculo(String tipo,int precioParqueo,int capacidadCarros,int capacidadMotos) {
		if(precioParqueo != 0)
			if(tipo.equals(CARRO))
				return capacidadCarros-1;
			else
				return capacidadMotos-1;
		else {
			if(tipo.equals(CARRO))
				return capacidadCarros;
			else
				return capacidadMotos;
		}
	}
	
	@Override
	public Parqueadero obtenerParqueadero(int parqueaderoId) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(repositorioParqueadero.findOne(parqueaderoId),Parqueadero.class);
	}
}
