package co.ceiba.parqueadero.dominio;

public class Parqueadero {
	private int parqueaderoId;
	private int capacidadCarros;
	private int capacidadMotos;
	
	public Parqueadero() {
		super();
	}

	public Parqueadero(int parqueaderoId,int capacidadCarros, int capacidadMotos) {
		super();
		this.parqueaderoId = parqueaderoId;
		this.capacidadCarros = capacidadCarros;
		this.capacidadMotos = capacidadMotos;
	}
	
	public int getParqueaderoId() {
		return parqueaderoId;
	}

	public int getCapacidadCarros() {
		return capacidadCarros;
	}

	public void setCapacidadCarros(int capacidadCarros) {
		this.capacidadCarros = capacidadCarros;
	}

	public int getCapacidadMotos() {
		return capacidadMotos;
	}

	public void setCapacidadMotos(int capacidadMotos) {
		this.capacidadMotos = capacidadMotos;
	}

	public void setParqueaderoId(int parqueaderoId) {
		this.parqueaderoId = parqueaderoId;
	}
}
