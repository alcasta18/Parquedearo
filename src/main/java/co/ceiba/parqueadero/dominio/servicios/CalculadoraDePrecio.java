package co.ceiba.parqueadero.dominio.servicios;


import org.springframework.stereotype.Service;

import co.ceiba.parqueadero.interfaces.IVehiculoNegocio;



@Service("VehiculoNegocio")
public class CalculadoraDePrecio implements IVehiculoNegocio {

	private static final int PRECIO_HORA_MOTO = 500;
	private static final int PRECIO_DIA_MOTO = 4000;
	private static final int PRECIO_HORA_CARRO = 1000;
	private static final int PRECIO_DIA_CARRO = 8000;
	private static final String CARRO = "Carro";
	private static final String MOTO = "Moto";
	private static final int SOBRECOSTO_CILINDRAJE = 2000;
	public static final int HORAS_DE_UN_DIA = 24;
	public static final int HORAS_TOPE_PARA_COBRAR_DIA = 9;
	

	@Override
	public int calcularPrecio(CalcularTiempoParqueadero parametroCalcularPrecio) {
		int numHoras = parametroCalcularPrecio.calcularDiasBasadoEnLasHoras();
		int precio = calcularPrecioTipo(parametroCalcularPrecio.getTipo(), parametroCalcularPrecio.getDias(), numHoras);
		if ((parametroCalcularPrecio.getTipo().equals(MOTO)) && (parametroCalcularPrecio.getCilindraje() > 500)) {
			precio += SOBRECOSTO_CILINDRAJE;
		}
		return precio;
	}

	@Override
	public int calcularPrecioTipo(String tipo, int dias, int horas) {
		if (tipo.equals(CARRO))
			return (dias * PRECIO_DIA_CARRO) + (horas * PRECIO_HORA_CARRO);
		else
			return (dias * PRECIO_DIA_MOTO) + (horas * PRECIO_HORA_MOTO);
	}

	
}
