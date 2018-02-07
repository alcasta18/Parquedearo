package co.ceiba.test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import co.ceiba.negocio.CalculadorDeHoras;

public class CalculadorDeHorasTest {
	private CalculadorDeHoras calculadorDeHoras = new CalculadorDeHoras();
	
	@Test
	public void minutosHoraEntradaAEntero() {
		//arrange
		String horaEntrada="10:00";
		//act
		int minutosEntrada = this.calculadorDeHoras.minutosHoraEntradaAEntero(horaEntrada);
		//assert
		assertEquals(0,minutosEntrada);
	}
	
	@Test
	public void minutosHoraSalidaAEntero() {
		//arrange		
		String horaSalida="14:35";
		//act
		int minutosSalida = calculadorDeHoras.minutosHoraSalidaAEntero(horaSalida);
		//assert
		assertEquals(35,minutosSalida);
	}
	@Test
	public void diferenciaDeHorasIgualesTest() {
		//arrange
		Calendar calendario = new GregorianCalendar(2018, 2, 6, 7, 32);
		Date fechaEntrada=calendario.getTime();
		calendario.set(2018, 2, 6, 16, 32, 00);
		Date fechaSalida = calendario.getTime();
		//act
		int resultado = calculadorDeHoras.diferenciaDeHoras(fechaEntrada,fechaSalida);
		//assert
		assertEquals(9,resultado);
	}
	@Test
	public void diferenciaDeHorasDistintasTest() {
		Calendar calendario = new GregorianCalendar(2018, 2, 6, 7, 32);
		Date fechaEntrada=calendario.getTime();
		calendario.set(2018, 2, 6, 16, 35, 00);
		Date fechaSalida = calendario.getTime();
		//act
		int resultado = calculadorDeHoras.diferenciaDeHoras(fechaEntrada,fechaSalida);
		//assert
		assertEquals(10,resultado);
	}

}
