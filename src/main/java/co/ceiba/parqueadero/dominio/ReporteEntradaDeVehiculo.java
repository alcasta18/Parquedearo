package co.ceiba.parqueadero.dominio;

import java.util.Date;

public class ReporteEntradaDeVehiculo {
	private String placa;
	private String tipo;
	private Date fechaEntrada;
	
	public ReporteEntradaDeVehiculo() {
		super();
	}
	
	
	
	public ReporteEntradaDeVehiculo(String placa, String tipo, Date fechaEntrada) {
		this.placa = placa;
		this.tipo = tipo;
		this.fechaEntrada = fechaEntrada;
	}



	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	public String getPlaca() {
		return placa;
	}
	public String getTipo() {
		return tipo;
	}
	public Date getFechaEntrada() {
		return fechaEntrada;
	}
	
	
}
