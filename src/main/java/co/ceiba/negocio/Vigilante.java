package co.ceiba.negocio;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
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
		
		int dia = buscarDia(null);

		validarIngreso(parqueadero, parqueaderoN, vehiculo, dia);

		parqueaderoN.ingresarVehiculo(vehiculo.getTipo(), parqueadero.getCapacidadCarros(),
				parqueadero.getCapacidadMotos());

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
		parqueaderoN.sacarVehiculo(vehiculo.getTipo(), factura.getTotal(), parqueadero.getCapacidadCarros(),
				parqueadero.getCapacidadMotos());
	}

	@Override
	public int buscarDia(Date fechaEntrada) {
		if (fechaEntrada == null)
			return java.util.Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		else {
			Calendar calendario = new GregorianCalendar();
			calendario.setTime(fechaEntrada);
			return calendario.get(Calendar.DAY_OF_WEEK);
		}
	}


	public Factura buscarFactura(int facturaId) {
		return modelMapper.map(facturaRepo.findOne(facturaId),Factura.class);
	}



}
