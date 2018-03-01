package co.ceiba.parqueadero.dominio;

public class SolicitudDeEntradaOSalida {
	private int parqueaderoId;
	private String placa;
		
	
	
	public SolicitudDeEntradaOSalida() {
		super();
	}

	public SolicitudDeEntradaOSalida(int parqueaderoId, String placa) {
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
