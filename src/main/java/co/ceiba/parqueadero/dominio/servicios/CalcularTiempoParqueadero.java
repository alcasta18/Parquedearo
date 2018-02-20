package co.ceiba.parqueadero.dominio.servicios;

public class CalcularTiempoParqueadero {
	public int numHoras;
	public int cilindraje;
	public String tipo;
	private int dias;

	public CalcularTiempoParqueadero(int numHoras, int cilindraje, String tipo) {
		this.numHoras = numHoras;
		this.cilindraje = cilindraje;
		this.tipo = tipo;
		this.dias = 0;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	public int calcularDiasBasadoEnLasHoras() {

		if (numHoras >= CalculadoraDePrecio.HORAS_TOPE_PARA_COBRAR_DIA && numHoras < CalculadoraDePrecio.HORAS_DE_UN_DIA) {
			numHoras = 0;
			setDias(getDias() + 1);
		} else {
			setDias(numHoras / CalculadoraDePrecio.HORAS_DE_UN_DIA);
			numHoras -= CalculadoraDePrecio.HORAS_DE_UN_DIA * getDias();
			calcularDiasConTopeMayorDeHoras();

		}
		return numHoras;
	}

	public void calcularDiasConTopeMayorDeHoras() {
		if (numHoras >= CalculadoraDePrecio.HORAS_TOPE_PARA_COBRAR_DIA) {
			numHoras = 0;
			setDias(getDias() + 1);
		}
	}

}