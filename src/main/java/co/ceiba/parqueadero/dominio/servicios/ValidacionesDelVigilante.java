package co.ceiba.parqueadero.dominio.servicios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.parqueadero.dominio.Parqueadero;
import co.ceiba.parqueadero.dominio.Vehiculo;
import co.ceiba.parqueadero.excepcion.EntradaDeVehiculoExcepcion;
import co.ceiba.parqueadero.repositorio.RepositorioFactura;

@Service
public class ValidacionesDelVigilante {
	public static final String CARRO = "Carro";
	public static final String MOTO = "Moto";
	public static final char AMAYUS = 'A';
	public static final char AMINUS = 'a';
	public static final int DOMINGO = 1;
	public static final int LUNES =  2;
	public static final int CERO = 0;
	
	@Autowired
	private RepositorioFactura facturaRepo;
	
	
	public void validarIngreso(Parqueadero parqueadero, Vehiculo vehiculo, int dia) {
		if ((!hayCupo(vehiculo.getTipo(), parqueadero.getCapacidadCarros(),
				parqueadero.getCapacidadMotos()))) {
			throw new EntradaDeVehiculoExcepcion(
					"El vehiculo no pudo ser ingresado en el parqueadero, el parqueadero esta lleno");
		}

		if (!(vehiculoPuedeEntrar(vehiculo.getPlaca(), dia)))
			throw new EntradaDeVehiculoExcepcion(
					"El vehiculo no pudo ser ingresado, porque no es un dia habilitado para su ingreso");
		if(!estaDentroDelParqueadero(vehiculo.getPlaca()))
			throw new EntradaDeVehiculoExcepcion(
					"Este vehiculo ya se encuentra dentro del parqueadero, revise la placa digitada");
	}
	
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
	
	
	public boolean hayCupo(String tipo,int capacidadCarros,int capacidadMotos) {
		boolean carroEntra = true;
		if(tipo.equals(CARRO)&&(capacidadCarros == CERO)) 
			carroEntra = false;
		if(tipo.equals(MOTO)&&(capacidadMotos == CERO)) 
			carroEntra = false;

		return carroEntra;
	}
	
	public boolean estaDentroDelParqueadero(String placa) {
		int vehiculoEstaAdentro = facturaRepo.vehiculoEstaFacturado(placa);		
		return(vehiculoEstaAdentro == 0);	
	}

}
