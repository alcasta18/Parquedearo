package co.ceiba.parqueadero.dominio.servicios;

import java.util.Calendar;
import java.util.GregorianCalendar;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.parqueadero.dominio.Factura;
import co.ceiba.parqueadero.dominio.Parqueadero;
import co.ceiba.parqueadero.dominio.SolicitudDeEntradaOSalida;
import co.ceiba.parqueadero.dominio.Vehiculo;
import co.ceiba.parqueadero.interfaces.IVigilante;
import co.ceiba.parqueadero.mappers.RepositorioFacturaParaMapeo;
import co.ceiba.parqueadero.mappers.VehiculoMapper;

@Service("Vigilante")

public class Vigilante implements IVigilante {
	private CalculadorDeHoras calculadorDeHoras = new CalculadorDeHoras();
		
	@Autowired
	private ValidacionesDelVigilante validador;
	@Autowired
	private ParqueaderoNegocio parqueaderoN;
	@Autowired
	private CalculadoraDePrecio vehiculoN;
	@Autowired
	private RepositorioFacturaParaMapeo facturaRepoMapeo;
	@Autowired
	private VehiculoMapper vehiculoMapper;

	@Override
	public Factura ingresarVehiculoAlParqueadero(SolicitudDeEntradaOSalida solicitud) {
		Parqueadero parqueadero = parqueaderoN.obtenerParqueadero(solicitud.getParqueaderoId());
		Vehiculo vehiculo = obtenerVehiculo(solicitud.getPlaca());
		Factura factura = new Factura();

		int dia = java.util.Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

		validador.validarIngreso(parqueadero, vehiculo, dia);

		parqueaderoN.actualizarCapacidadAlIngresoDeVehiculo(vehiculo.getTipo(), solicitud.getParqueaderoId());

		crearFactura(vehiculo, factura);
		return factura;

	}

	private void crearFactura(Vehiculo vehiculo, Factura factura) {
		Calendar calendario = new GregorianCalendar();
		factura.setFechaEntrada(calendario.getTime());
		factura.setPlaca(vehiculo.getPlaca());
		factura.setHoraEntrada(calculadorDeHoras.obtenerHora(calendario.getTime().toString()));
		factura.setHoraSalida("");
		facturaRepoMapeo.guardarFactura(factura);
	}

	
	@Override
	public Factura terminarFactura(SolicitudDeEntradaOSalida solicitud) {
		Parqueadero parqueadero = parqueaderoN.obtenerParqueadero(solicitud.getParqueaderoId());
		Vehiculo vehiculo = obtenerVehiculo(solicitud.getPlaca());
		Factura factura = facturaRepoMapeo.getFacturaPorPlaca(solicitud.getPlaca());
		Calendar calendario = new GregorianCalendar();

		parqueaderoN.actualizarCapacidadAlSalirUnVehiculo(vehiculo.getTipo(), parqueadero);
		setFactura(factura, vehiculo, calendario);

		facturaRepoMapeo.guardarFactura(factura);
		return factura;
	}

	public void setFactura(Factura factura, Vehiculo vehiculo, Calendar calendario) {
		factura.setFechaSalida(calendario.getTime());
		factura.setHoraSalida(calculadorDeHoras.obtenerHora(calendario.getTime().toString()));
		factura.setTotal(vehiculoN.calcularPrecio(
				new CalcularTiempoParqueadero(calculadorDeHoras.diferenciaDeHorasConCalendar(factura.getFechaEntrada(), factura.getFechaSalida()), vehiculo.getCilindraje(), vehiculo.getTipo())));
	}

	
	public Vehiculo obtenerVehiculo(String placa) {
		return vehiculoMapper.obtenerVehiculo(placa);
	}
}
