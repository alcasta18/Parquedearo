package co.ceiba.interfaces;

public interface IParqueaderoNegocio {
	public boolean vehiculoPuedeEntrar(String placa ,int dia);
	
	public boolean hayCupo(String tipo,int capacidadCarros,int capacidadMotos);
	
	public int ingresarVehiculo(String tipo,int capacidadCarros,int capacidadMotos);
	
	public int sacarVehiculo(String tipo,int precioParqueo,int capacidadCarros,int capacidadMotos);
}
