package co.ceiba.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="factura")
public class FacturaEntity {
	@Id
	@Column(name = "factura_id",nullable = false)
	private int facturaId;
	@Column(name = "fecha_entrada",nullable=true)
	private Date fechaEntrada;
	@Column(name = "fecha_salida",nullable=true)
	private Date fechaSalida;
	@Column(name = "hora_entrada", nullable=true)
	private String horaEntrada;
	@Column(name = "hora_salida",nullable=true)
	private String horaSalida;
	@Column(name = "total",nullable=true)
	private int total;
	@Column(name = "placa",nullable=false)
	private String placa;
	public int getFacturaId() {
		return facturaId;
	}
	public void setFacturaId(int facturaId) {
		this.facturaId = facturaId;
	}
	public Date getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	public Date getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public String getHoraEntrada() {
		return horaEntrada;
	}
	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	public String getHoraSalida() {
		return horaSalida;
	}
	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}
	public int getTotal() {
		return total;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public void setTotal(int total) {
		this.total = total;
	}	
}
