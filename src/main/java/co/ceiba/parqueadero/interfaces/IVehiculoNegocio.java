package co.ceiba.parqueadero.interfaces;

import co.ceiba.parqueadero.dominio.servicios.CalcularTiempoParqueadero;

public interface IVehiculoNegocio {
	public int calcularPrecio(CalcularTiempoParqueadero parameterObject);
	
	public int calcularPrecioTipo(String tipo,int dias,int horas);
	
}
