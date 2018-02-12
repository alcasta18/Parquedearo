package co.ceiba.servicios;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import co.ceiba.dominio.Factura;
import co.ceiba.dominio.Parqueadero;
import co.ceiba.dominio.Vehiculo;
import co.ceiba.entity.FacturaEntity;
import co.ceiba.excepcion.EntradaDeVehiculoExcepcion;
import co.ceiba.interfaces.IVigilante;
import co.ceiba.repositorio.RepositorioFactura;

@Service("Vigilante")
@EnableJpaRepositories("co.ceiba.repositorio")
public class Vigilante implements IVigilante {
	private ModelMapper modelMapper = new ModelMapper();
	private CalculadorDeHoras calculadorDeHoras = new CalculadorDeHoras();
	private static final Log LOG = LogFactory.getLog(Vigilante.class);
	
	@Autowired
	private ParqueaderoNegocio parqueaderoN;
	@Autowired
	private VehiculoNegocio vehiculoN;
	@Autowired
	private RepositorioFactura facturaRepo;
	
	@Override
	public Factura ingresarVehiculoAlParqueadero(SolicitudEntradaVehiculo solicitud) {
		Parqueadero parqueadero = parqueaderoN.obtenerParqueadero(solicitud.getParqueaderoId());
		Vehiculo vehiculo = vehiculoN.obtenerVehiculo(solicitud.getPlaca());
		Factura factura = new Factura();
		
		int dia = java.util.Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

		validarIngreso(parqueadero, parqueaderoN, vehiculo, dia);

		parqueaderoN.actualizarCapacidadAlIngresoDeVehiculo(vehiculo.getTipo(), solicitud.getParqueaderoId());

		crearFactura(vehiculo, factura);
		return factura;
		
	}

	private void crearFactura(Vehiculo vehiculo, Factura factura) {
		Calendar calendario = new GregorianCalendar();
		factura.setFechaEntrada(calendario.getTime());
		factura.setPlaca(vehiculo.getPlaca());
		factura.setHoraEntrada(calculadorDeHoras.obtenerHora(calendario.getTime().toString()));
		facturaRepo.save(modelMapper.map(factura, FacturaEntity.class));
	}

	private void validarIngreso(Parqueadero parqueadero, ParqueaderoNegocio parqueaderoN, Vehiculo vehiculo, int dia) {
		if ((!parqueaderoN.hayCupo(vehiculo.getTipo(), parqueadero.getCapacidadCarros(),
				parqueadero.getCapacidadMotos()))) {
			throw new EntradaDeVehiculoExcepcion("El vehiculo no pudo ser ingresado en el parqueadero, el parqueadero esta lleno");
		}

		if (!(parqueaderoN.vehiculoPuedeEntrar(vehiculo.getPlaca(), dia)))
			throw new EntradaDeVehiculoExcepcion("El vehiculo no pudo ser ingresado, porque no es un dia habilitado para su ingreso");
	}

	@Override
	public Factura terminarFactura(SolicitudSalidaVehiculo solicitud) {
		Factura factura = buscarFactura(solicitud.getFacturaId());
		Parqueadero parqueadero = parqueaderoN.obtenerParqueadero(solicitud.getParqueaderoId());
		Vehiculo vehiculo = vehiculoN.obtenerVehiculo(factura.getPlaca());
		Calendar calendario = new GregorianCalendar();
		
		parqueaderoN.actualizarCapacidadAlSalirUnVehiculo(vehiculo.getTipo(), solicitud.getParqueaderoId());
		setFactura(factura, parqueadero, vehiculo, calendario);
		
		facturaRepo.save(modelMapper.map(factura, FacturaEntity.class));
		return factura;
	}


	public void setFactura(Factura factura, Parqueadero parqueadero, Vehiculo vehiculo, Calendar calendario) {
		factura.setFechaSalida(calendario.getTime());
		factura.setHoraSalida(calculadorDeHoras.obtenerHora(calendario.getTime().toString()));
		factura.setTotal(vehiculoN.calcularPrecio(
				calculadorDeHoras.diferenciaDeHorasConCalendar(factura.getFechaEntrada(), factura.getFechaSalida()),
				vehiculo.getCilindraje(), vehiculo.getTipo()));
		parqueaderoN.actualizarCapacidadAlSalirUnVehiculo(vehiculo.getTipo(), parqueadero.getParqueaderoId());
	}


	public Factura buscarFactura(int facturaId) {
		Factura factura = null;
		try {
			factura  = modelMapper.map(facturaRepo.findOne(facturaId),Factura.class);
		}catch(Exception e) {
			LOG.info(e.getMessage());
		}
		return factura;
	}
	
	public Date buscarFechaEntradaPorPlaca(String placa) {
		Date fecha = null;
		try {
			fecha = facturaRepo.consultarFechaEntrada(placa);
		}catch(Exception e){
			LOG.info(e.getMessage());
		}
		return fecha;
	}

}
