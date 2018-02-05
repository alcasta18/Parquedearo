package co.ceiba.interfaces;

public interface IVehiculoNegocio {
	public int calcularPrecio(int numHoras,int cilindraje,String tipo);
	
	public int calcularPrecioTipo(String tipo,int dias,int horas);
}
