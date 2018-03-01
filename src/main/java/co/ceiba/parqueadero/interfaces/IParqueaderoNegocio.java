package co.ceiba.parqueadero.interfaces;

import co.ceiba.parqueadero.dominio.Parqueadero;

public interface IParqueaderoNegocio {
	
	public void actualizarCapacidadAlIngresoDeVehiculo(String tipo, int parqueaderoId);
	
	public void actualizarCapacidadAlSalirUnVehiculo(String tipo, Parqueadero parqueadero);

	public Parqueadero obtenerParqueadero(int parqueaderoId);
}
