package co.ceiba.negocio;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Component;

import co.ceiba.dominio.Factura;
import co.ceiba.dominio.Parqueadero;
import co.ceiba.dominio.Vehiculo;
import co.ceiba.excepcion.EntradaDeVehiculoExcepcion;
import co.ceiba.interfaces.IVigilante;

@Component("Vigilante")
public class Vigilante implements IVigilante{
	

	private CalculadorDeHoras calculadorDeHoras = new CalculadorDeHoras();
	
	@Override
	public void empezarFactura(Parqueadero parqueadero, ParqueaderoNegocio parqueaderoN, Vehiculo vehiculo,
			Factura factura) {
		Calendar calendario = new GregorianCalendar();		
		int dia =buscarDia(factura.getFechaEntrada());
		if((parqueaderoN.hayCupo(vehiculo.getTipo(),parqueadero.getCapacidadCarros(),
				parqueadero.getCapacidadMotos()))&&(parqueaderoN.vehiculoPuedeEntrar
						(vehiculo.getPlaca(),dia))) {
			parqueaderoN.ingresarVehiculo(vehiculo.getTipo(), parqueadero.getCapacidadCarros(), 
					parqueadero.getCapacidadMotos());
			factura.setFechaEntrada(calendario.getTime());
			factura.setPlaca(vehiculo.getPlaca());			
			factura.setHoraEntrada(calculadorDeHoras.obtenerHora(calendario.getTime().toString()));
		}
		else
			throw new EntradaDeVehiculoExcepcion("El vehiculo no pudo ser ingresado en el parqueadero");
		
	}

	@Override
	public void terminarFactura(Parqueadero parqueadero, ParqueaderoNegocio parqueaderoN, Vehiculo vehiculo,
			VehiculoNegocio vehiculoN, Factura factura) {
		Calendar calendario = new GregorianCalendar();
		factura.setFechaSalida(calendario.getTime());
		factura.setHoraSalida(calculadorDeHoras.obtenerHora(calendario.getTime().toString()));
		factura.setTotal(vehiculoN.calcularPrecio(calculadorDeHoras.diferenciaDeHoras(factura.getFechaEntrada(),
				factura.getFechaSalida()),vehiculo.getCilindraje(),vehiculo.getTipo()));
		parqueaderoN.sacarVehiculo(vehiculo.getTipo(), factura.getTotal(), 
				parqueadero.getCapacidadCarros(), parqueadero.getCapacidadMotos());
	}

	@Override
	public int buscarDia(Date fechaEntrada) {
		if(fechaEntrada == null)
			return java.util.Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		else {
			Calendar calendario = new GregorianCalendar();
			calendario.setTime(fechaEntrada);
			return calendario.get(Calendar.DAY_OF_WEEK);
		}
	}



}
