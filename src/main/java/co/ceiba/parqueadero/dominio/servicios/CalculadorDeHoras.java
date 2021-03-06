package co.ceiba.parqueadero.dominio.servicios;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Component;
@Component("CalculadorDeHoras")
public class CalculadorDeHoras {
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
	
	public int diferenciaDeHorasConCalendar(Date fechaEntrada,Date fechaSalida) {
		int diasEnHoras = diasAHoras(fechaEntrada,fechaSalida);
		String horaEntrada = obtenerHoraConCalendar(fechaEntrada);
		String horaSalida = obtenerHoraConCalendar(fechaSalida);		
		int minutosEntrada = minutosHoraEntradaAEntero(horaEntrada);		
		int minutosSalida = minutosHoraSalidaAEntero(horaSalida);
		if(minutosEntrada==minutosSalida)
			return diasEnHoras;
		else
			return diasEnHoras+1;		
	}
	
	public String obtenerHoraConCalendar(Date fecha) {
		Calendar c= new GregorianCalendar();
		c.setTime(fecha);
		return String.valueOf(c.get(Calendar.HOUR_OF_DAY)).concat(":").concat(String.valueOf(c.get(Calendar.MINUTE)));
	}
}
