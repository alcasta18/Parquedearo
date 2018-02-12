package co.ceiba.test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.Main;
import co.ceiba.servicios.CalculadorDeHoras;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Main.class)
public class CalculadorDeHorasTest {
	@Autowired
	private CalculadorDeHoras calculadorDeHoras;

	
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
