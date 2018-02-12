package co.ceiba.interfaces;

import co.ceiba.dominio.Parqueadero;

public interface IParqueaderoNegocio {
	public boolean vehiculoPuedeEntrar(String placa ,int dia);
	
	public boolean hayCupo(String tipo,int capacidadCarros,int capacidadMotos);
	
	public void actualizarCapacidadAlIngresoDeVehiculo(String tipo, int parqueaderoId);
	
	public void actualizarCapacidadAlSalirUnVehiculo(String tipo, int parqueaderoId);

	public Parqueadero obtenerParqueadero(int parqueaderoId);
}
