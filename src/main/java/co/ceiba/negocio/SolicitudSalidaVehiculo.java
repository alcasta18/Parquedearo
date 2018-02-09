package co.ceiba.negocio;

public class SolicitudSalidaVehiculo {
	private int parqueaderoId;
	private int facturaId;
	
	public SolicitudSalidaVehiculo() {
		super();
	}
	
	
	
	public SolicitudSalidaVehiculo(int parqueaderoId, int facturaId) {
		super();
		this.parqueaderoId = parqueaderoId;
		this.facturaId = facturaId;
	}



	public int getParqueaderoId() {
		return parqueaderoId;
	}
	
	public int getFacturaId() {
		return facturaId;
	}

	
	
}
