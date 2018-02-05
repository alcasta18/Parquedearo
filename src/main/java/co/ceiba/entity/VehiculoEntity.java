package co.ceiba.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vehiculo")
public class VehiculoEntity {
	@Id
	@Column (name = "placa",nullable = false)
	private String placa;
	@Column(name = "tipo",nullable = false)
	private String tipo;
	@Column (name = "cilindraje",nullable = false)
	private int cilindraje;
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getCilindraje() {
		return cilindraje;
	}
	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}
	
	
}
