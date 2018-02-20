package co.ceiba.parqueadero.excepcion;

public class EntradaDeVehiculoExcepcion extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public EntradaDeVehiculoExcepcion(String message) {
		super(message);
	}
}
