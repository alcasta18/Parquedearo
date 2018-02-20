package co.ceiba.parqueadero.dominio.servicios;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.parqueadero.dominio.Vehiculo;
import co.ceiba.parqueadero.entity.VehiculoEntity;
import co.ceiba.parqueadero.interfaces.IVehiculoNegocio;
import co.ceiba.parqueadero.repositorio.RepositorioVehiculo;

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
	private static final Log LOG = LogFactory.getLog(CalculadoraDePrecio.class);
	@Autowired
	private RepositorioVehiculo vehiculoReposotory;

	@Override
	public int calcularPrecio(CalcularTiempoParqueadero parametroCalcularPrecio) {
		int numHoras = parametroCalcularPrecio.calcularDiasBasadoEnLasHoras();
		int precio = calcularPrecioTipo(parametroCalcularPrecio.tipo, parametroCalcularPrecio.getDias(), numHoras);
		if ((parametroCalcularPrecio.tipo.equals(MOTO)) && (parametroCalcularPrecio.cilindraje > 500)) {
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

	public Vehiculo obtenerVehiculo(String placa) {
		Vehiculo vehiculo = null;
		ModelMapper modelMapper = new ModelMapper();
		try {
			vehiculo = modelMapper.map(vehiculoReposotory.findByPlaca(placa), Vehiculo.class);
		} catch (Exception e) {
			LOG.info(e.getMessage());
		}
		return vehiculo;
	}

	public void guardarVehiculoEnBD(Vehiculo vehiculo) {
		ModelMapper modelMapper = new ModelMapper();
		vehiculoReposotory.save(modelMapper.map(vehiculo, VehiculoEntity.class));
	}
}
