package co.ceiba.negocio;

import co.ceiba.interfaces.IVehiculoNegocio;

public class VehiculoNegocio implements IVehiculoNegocio{

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
}
