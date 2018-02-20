package co.ceiba.parqueadero.dominio.servicios;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.ceiba.parqueadero.dominio.Factura;
import co.ceiba.parqueadero.dominio.Parqueadero;
import co.ceiba.parqueadero.dominio.SolicitudEntradaVehiculo;
import co.ceiba.parqueadero.dominio.SolicitudSalidaVehiculo;
import co.ceiba.parqueadero.dominio.Vehiculo;
import co.ceiba.parqueadero.excepcion.EntradaDeVehiculoExcepcion;
import co.ceiba.parqueadero.interfaces.IVigilante;
import co.ceiba.parqueadero.mappers.RepositorioFacturaParaMapeo;
import co.ceiba.parqueadero.repositorio.RepositorioFactura;

@Service("Vigilante")

public class Vigilante implements IVigilante {
	public static final String CARRO = "Carro";
	public static final String MOTO = "Moto";
	public static final char AMAYUS = 'A';
	public static final char AMINUS = 'a';
	public static final int DOMINGO = 1;
	public static final int LUNES =  2;
	public static final int CERO = 0;
	private CalculadorDeHoras calculadorDeHoras = new CalculadorDeHoras();
	
	private static final Log LOG = LogFactory.getLog(Vigilante.class);

	@Autowired
	private ParqueaderoNegocio parqueaderoN;
	@Autowired
	private CalculadoraDePrecio vehiculoN;
	@Autowired
	private RepositorioFactura facturaRepo;
	@Autowired
	private RepositorioFacturaParaMapeo facturaRepoMapeo;

	@Override
	public Factura ingresarVehiculoAlParqueadero(SolicitudEntradaVehiculo solicitud) {
		Parqueadero parqueadero = parqueaderoN.obtenerParqueadero(solicitud.getParqueaderoId());
		Vehiculo vehiculo = vehiculoN.obtenerVehiculo(solicitud.getPlaca());
		Factura factura = new Factura();

		int dia = java.util.Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

		validarIngreso(parqueadero, vehiculo, dia);

		parqueaderoN.actualizarCapacidadAlIngresoDeVehiculo(vehiculo.getTipo(), solicitud.getParqueaderoId());

		crearFactura(vehiculo, factura);
		return factura;

	}

	private void crearFactura(Vehiculo vehiculo, Factura factura) {
		Calendar calendario = new GregorianCalendar();
		factura.setFechaEntrada(calendario.getTime());
		factura.setPlaca(vehiculo.getPlaca());
		factura.setHoraEntrada(calculadorDeHoras.obtenerHora(calendario.getTime().toString()));
		facturaRepoMapeo.guardarFactura(factura);
	}

	private void validarIngreso(Parqueadero parqueadero, Vehiculo vehiculo, int dia) {
		if ((!hayCupo(vehiculo.getTipo(), parqueadero.getCapacidadCarros(),
				parqueadero.getCapacidadMotos()))) {
			throw new EntradaDeVehiculoExcepcion(
					"El vehiculo no pudo ser ingresado en el parqueadero, el parqueadero esta lleno");
		}

		if (!(vehiculoPuedeEntrar(vehiculo.getPlaca(), dia)))
			throw new EntradaDeVehiculoExcepcion(
					"El vehiculo no pudo ser ingresado, porque no es un dia habilitado para su ingreso");
	}

	@Override
	public Factura terminarFactura(SolicitudSalidaVehiculo solicitud) {
		Factura factura = buscarFactura(solicitud.getFacturaId());
		Parqueadero parqueadero = parqueaderoN.obtenerParqueadero(solicitud.getParqueaderoId());
		Vehiculo vehiculo = vehiculoN.obtenerVehiculo(factura.getPlaca());
		Calendar calendario = new GregorianCalendar();

		parqueaderoN.actualizarCapacidadAlSalirUnVehiculo(vehiculo.getTipo(), solicitud.getParqueaderoId());
		setFactura(factura, parqueadero, vehiculo, calendario);

		facturaRepoMapeo.guardarFactura(factura);
		return factura;
	}

	public void setFactura(Factura factura, Parqueadero parqueadero, Vehiculo vehiculo, Calendar calendario) {
		factura.setFechaSalida(calendario.getTime());
		factura.setHoraSalida(calculadorDeHoras.obtenerHora(calendario.getTime().toString()));
		factura.setTotal(vehiculoN.calcularPrecio(
				new CalcularTiempoParqueadero(calculadorDeHoras.diferenciaDeHorasConCalendar(factura.getFechaEntrada(), factura.getFechaSalida()), vehiculo.getCilindraje(), vehiculo.getTipo())));
		parqueaderoN.actualizarCapacidadAlSalirUnVehiculo(vehiculo.getTipo(), parqueadero.getParqueaderoId());
	}

	public Factura buscarFactura(long facturaId) {
		Factura factura = null;
		try {
			factura = facturaRepoMapeo.getFactura(facturaId);
		} catch (Exception e) {
			LOG.info(e.getMessage());
		}
		return factura;
	}

	@Transactional
	public Date buscarFechaEntradaPorPlaca(String placa) {
		Date fecha = null;
		try {
			fecha = facturaRepo.consultarFechaEntrada(placa);
		} catch (Exception e) {
			LOG.info(e.getMessage());
		}
		return fecha;
	}
	
	public boolean vehiculoPuedeEntrar(String placa ,int dia) {
		boolean carroEntra= true;
		if((placa.charAt(0)==AMAYUS)||(placa.charAt(0)==AMINUS)){
			if((dia == DOMINGO)||(dia == LUNES)) {
				carroEntra = true;
			}
			else {
				carroEntra = false;
			}
		}
		else {
			carroEntra = true;
		}		
		return carroEntra;
	}
	
	
	public boolean hayCupo(String tipo,int capacidadCarros,int capacidadMotos) {
		boolean carroEntra = true;
		if(tipo.equals(CARRO)&&(capacidadCarros == CERO)) 
			carroEntra = false;
		if(tipo.equals(MOTO)&&(capacidadMotos == CERO)) 
			carroEntra = false;

		return carroEntra;
	}
	

}
