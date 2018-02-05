package co.ceiba.negocio;



import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import co.ceiba.dominio.Factura;
import co.ceiba.dominio.Parqueadero;
import co.ceiba.dominio.Vehiculo;
import co.ceiba.excepcion.EntradaDeVehiculoExcepcion;
import co.ceiba.interfaces.IFacturaNegocio;

public class FacturaNegocio implements IFacturaNegocio{

	public int minutosHoraEntradaAEntero(String horaEntrada) {
		String [] numHoraE = horaEntrada.split(":");
		return Integer.parseInt(numHoraE[1]);
	}
	
	public int minutosHoraSalidaAEntero(String horaSalida) {
		String [] numHoraS = horaSalida.split(":");
		return Integer.parseInt(numHoraS[1]);
	}
	
	public int diasAHoras(Date fechaEntrada,Date fechaSalida) {
		long  miliSegundosFechas = fechaSalida.getTime()-fechaEntrada.getTime();
		miliSegundosFechas = miliSegundosFechas/(1000*60*60);
		return (int) miliSegundosFechas;
	}
	
	public int diferenciaDeHoras(Date fechaEntrada,Date fechaSalida) {
		int diasEnHoras = diasAHoras(fechaEntrada,fechaSalida);
		String horaEntrada = obtenerHora(fechaEntrada.toString());
		String horaSalida = obtenerHora(fechaSalida.toString());		
		int minutosEntrada = minutosHoraEntradaAEntero(horaEntrada);		
		int minutosSalida = minutosHoraSalidaAEntero(horaSalida);
		if(minutosEntrada==minutosSalida)
			return diasEnHoras;
		else
			return diasEnHoras+1;		
	}
	
	public String obtenerHora(String fechaCompleta) {
		String [] obtenerHora = fechaCompleta.split(" ");
		return obtenerHora[3];
	}
	
	public void empezarFactura(Parqueadero parqueadero,ParqueaderoNegocio parqueaderoN,Vehiculo vehiculo,Factura factura) {
		Calendar calendario = new GregorianCalendar();
		if(parqueaderoN.hayCupo(vehiculo.getTipo(),parqueadero.getCapacidadCarros(),
				parqueadero.getCapacidadMotos())&&(parqueaderoN.vehiculoPuedeEntrar
						(vehiculo.getPlaca(),calendario.get(Calendar.DAY_OF_WEEK)))) {
			parqueaderoN.ingresarVehiculo(vehiculo.getTipo(), parqueadero.getCapacidadCarros(), 
					parqueadero.getCapacidadMotos());
			factura.setFechaEntrada(calendario.getTime());
			factura.setPlaca(vehiculo.getPlaca());			
			factura.setHoraEntrada(obtenerHora(calendario.getTime().toString()));
		}
		else
			throw new EntradaDeVehiculoExcepcion("El vehiculo no pudo ser ingresado en el parqueadero");
	}
	
	public void terminarFactura(Parqueadero parqueadero, ParqueaderoNegocio parqueaderoN,Vehiculo vehiculo,VehiculoNegocio vehiculoN,Factura factura) {
		Calendar calendario = new GregorianCalendar();
		factura.setFechaSalida(calendario.getTime());
		factura.setHoraSalida(obtenerHora(calendario.getTime().toString()));
		factura.setTotal(vehiculoN.calcularPrecio(diferenciaDeHoras(factura.getFechaEntrada(),
				factura.getFechaSalida()),vehiculo.getCilindraje(),vehiculo.getTipo()));
		parqueaderoN.sacarVehiculo(vehiculo.getTipo(), factura.getTotal(), 
				parqueadero.getCapacidadCarros(), parqueadero.getCapacidadMotos());
	}	
}
