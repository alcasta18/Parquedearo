package co.ceiba.negocio;

import org.springframework.stereotype.Component;

import co.ceiba.interfaces.IParqueaderoNegocio;
@Component("ParqueaderoNegocio")
public class ParqueaderoNegocio implements IParqueaderoNegocio{
	public static final String CARRO = "Carro";
	@Override
	public boolean vehiculoPuedeEntrar(String placa ,int dia) {
		boolean mensaje= true;
		if((placa.charAt(0)=='A')||(placa.charAt(0)=='a')){
			if((dia == 1)||(dia == 2)) {
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
		if(tipo.equals(CARRO)&&(capacidadCarros == 0)) 
			vacio = false;
		if(tipo.equals("Moto")&&(capacidadMotos == 0)) 
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
}
