package co.ceiba.interfaces;

import co.ceiba.dominio.ReporteEntradaDeVehiculo;

public interface IVehiculoNegocio {
	public int calcularPrecio(int numHoras,int cilindraje,String tipo);
	
	public int calcularPrecioTipo(String tipo,int dias,int horas);
	
	public ReporteEntradaDeVehiculo reporte (String placa);
}
