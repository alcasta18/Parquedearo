package co.ceiba.parqueadero.dominio;

public class SolicitudSalidaVehiculo {
	private int parqueaderoId;
	private long facturaId;
	
	public SolicitudSalidaVehiculo() {
		super();
	}
	
	
	
	public SolicitudSalidaVehiculo(int parqueaderoId, int facturaId) {
		this.parqueaderoId = parqueaderoId;
		this.facturaId = facturaId;
	}



	public int getParqueaderoId() {
		return parqueaderoId;
	}
	
	public long getFacturaId() {
		return facturaId;
	}

	
	
}
