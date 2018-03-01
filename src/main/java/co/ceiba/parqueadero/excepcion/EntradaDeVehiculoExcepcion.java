package co.ceiba.parqueadero.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class EntradaDeVehiculoExcepcion extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public EntradaDeVehiculoExcepcion(String message) {
		super(message);
	}
}
