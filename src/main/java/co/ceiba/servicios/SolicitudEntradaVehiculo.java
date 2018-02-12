package co.ceiba.servicios;

public class SolicitudEntradaVehiculo {
	private int parqueaderoId;
	private String placa;
		
	
	
	public SolicitudEntradaVehiculo() {
		super();
	}

	public SolicitudEntradaVehiculo(int parqueaderoId, String placa) {
		super();
		this.parqueaderoId = parqueaderoId;
		this.placa = placa;
	}
	
	public int getParqueaderoId() {
		return parqueaderoId;
	}
	public String getPlaca() {
		return placa;
	}
	
	
}
