package co.ceiba.parqueadero.dominio.servicios;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.ceiba.parqueadero.dominio.Parqueadero;
import co.ceiba.parqueadero.interfaces.IParqueaderoNegocio;
import co.ceiba.parqueadero.mappers.RepositorioParqueaderoMapper;

@Service("ParqueaderoNegocio")


public class ParqueaderoNegocio implements IParqueaderoNegocio{
	public static final String CARRO = "Carro";
	public static final String MOTO = "Moto";
	public static final char AMAYUS = 'A';
	public static final char AMINUS = 'a';
	public static final int DOMINGO = 1;
	public static final int LUNES =  2;
	public static final int CERO = 0;
	private static final Log LOG = LogFactory.getLog(ParqueaderoNegocio.class);
	
	@Autowired
	private RepositorioParqueaderoMapper repositorioParqueaderoMapper;
	
	

	
	public void actualizarCapacidadAlIngresoDeVehiculo(String tipo, int parqueaderoId) {
		Parqueadero parqueadero = obtenerParqueadero(parqueaderoId);
		if(tipo.equals(CARRO))
			parqueadero.setCapacidadCarros(parqueadero.getCapacidadCarros()-1);
		else if(tipo.equals(MOTO))
			parqueadero.setCapacidadMotos(parqueadero.getCapacidadMotos()-1);
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
	@Transactional
	public Parqueadero obtenerParqueadero(int parqueaderoId) {
		Parqueadero parqueadero = null;
		try {
			parqueadero = repositorioParqueaderoMapper.obtenerParqueadero(parqueaderoId);
		}catch(Exception e) {
			LOG.info(e.getMessage());
		}
		return parqueadero;
	}
	
	@Transactional
	public void actualizarParqueadero(Parqueadero parqueadero) {
		repositorioParqueaderoMapper.actualizarParqueadero(parqueadero);
	}
}
