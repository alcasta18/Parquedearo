package co.ceiba.test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import org.junit.Test;

import co.ceiba.dominio.Factura;
import co.ceiba.dominio.Parqueadero;
import co.ceiba.dominio.Vehiculo;
import co.ceiba.excepcion.EntradaDeVehiculoExcepcion;
import co.ceiba.interfaces.IFacturaNegocio;
import co.ceiba.negocio.FacturaNegocio;
import co.ceiba.negocio.ParqueaderoNegocio;
import co.ceiba.negocio.VehiculoNegocio;


public class FacturaNegocioTest {
	
	private IFacturaNegocio facturaN=new FacturaNegocio();
	
	@Test
	public void minutosHoraEntradaAEntero() {
		//arrange
		String horaEntrada="10:00";
		//act
		int minutosEntrada = facturaN.minutosHoraEntradaAEntero(horaEntrada);
		//assert
		assertEquals(0,minutosEntrada);
	}
	
	@Test
	public void minutosHoraSalidaAEntero() {
		//arrange		
		String horaSalida="14:35";
		//act
		int minutosSalida = facturaN.minutosHoraSalidaAEntero(horaSalida);
		//assert
		assertEquals(35,minutosSalida);
	}
	@Test
	public void diferenciaDeHorasIgualesTest() {
		//arrange
		Calendar calendario = new GregorianCalendar();
		calendario.set(2018, 2, 6, 7, 32, 00);
		Date fechaEntrada=calendario.getTime();
		calendario.set(2018, 2, 6, 16, 32, 00);
		Date fechaSalida = calendario.getTime();
		//act
		int resultado = facturaN.diferenciaDeHoras(fechaEntrada,fechaSalida);
		//assert
		assertEquals(9,resultado);
	}
	@Test
	public void diferenciaDeHorasDistintasTest() {
		Calendar calendario = new GregorianCalendar();
		calendario.set(2018, 2, 6, 7, 32, 00);
		Date fechaEntrada=calendario.getTime();
		calendario.set(2018, 2, 6, 16, 35, 00);
		Date fechaSalida = calendario.getTime();
		//act
		int resultado = facturaN.diferenciaDeHoras(fechaEntrada,fechaSalida);
		//assert
		assertEquals(10,resultado);
	}
	
/*	@Test
	public void empezarFacturaTest() {
		//arrange
		Parqueadero parqueadero = new Parqueadero(1,20,10);
		ParqueaderoNegocio parqueaderoN = new ParqueaderoNegocio();
		Vehiculo vehiculo = new Vehiculo("Afewfe","Carro",2000);
		Factura factura = new Factura();
		Calendar c = new GregorianCalendar();
		c.set(2018, 1, 4, 7, 30);
		factura.setFechaEntrada(c.getTime());
		//act
		facturaN.empezarFactura(parqueadero, parqueaderoN, vehiculo, factura);
		//assert
		assertNotNull(factura.getFechaEntrada());
		assertNotNull(factura.getVehiculo());
		assertNotNull(factura.getHoraEntrada());
	}*/
		
	@Test(expected = EntradaDeVehiculoExcepcion.class)
	public void empezarFacturaTest2() {
		//arrange
		Parqueadero parqueadero = new Parqueadero(1,20,10);
		ParqueaderoNegocio parqueaderoN = new ParqueaderoNegocio();
		Vehiculo vehiculo = new Vehiculo("Afewfe","Carro",2000);
		Factura factura = new Factura();
		//act
		facturaN.empezarFactura(parqueadero, parqueaderoN, vehiculo, factura);
	}
	
	@Test
	public void terminarFacturaTest() {
		//arrange
		Parqueadero parqueadero = new Parqueadero(1,20,10);
		ParqueaderoNegocio parqueaderoN = new ParqueaderoNegocio();
		Vehiculo vehiculo = new Vehiculo("Afewfe","Carro",2000);
		VehiculoNegocio vehiculoN = new VehiculoNegocio();
		Factura factura = new Factura();
		Calendar calendario = new GregorianCalendar();
		factura.setFechaEntrada(calendario.getTime());
		//act
		facturaN.terminarFactura(parqueadero,parqueaderoN,vehiculo, vehiculoN,factura);
		//assert
		assertNotNull(factura.getFechaSalida());
		assertNotNull(factura.getHoraSalida());
		assertNotNull(factura.getTotal());
	}
	
	@Test
	public void ensayo() {
		Calendar c = new GregorianCalendar();
		c.set(2018, 1, 4, 13, 20);
		int dia = c.get(Calendar.DAY_OF_WEEK);
		assertEquals(1,dia);
	}
}
