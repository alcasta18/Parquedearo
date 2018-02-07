package co.ceiba.test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.mockito.Mockito;

import co.ceiba.CalendarioDia;
import co.ceiba.dominio.Factura;
import co.ceiba.dominio.Parqueadero;
import co.ceiba.dominio.Vehiculo;
import co.ceiba.excepcion.EntradaDeVehiculoExcepcion;
import co.ceiba.negocio.ParqueaderoNegocio;
import co.ceiba.negocio.VehiculoNegocio;
import co.ceiba.negocio.Vigilante;

public class VigilanteTest {
	private Vigilante vigilante = new Vigilante();
	@Test
	public void empezarFacturaTest() {
		//arrange
		Parqueadero parqueadero = new Parqueadero(1,20,10);
		Vehiculo vehiculo = new Vehiculo("Afewfe","Carro",2000);
		ParqueaderoNegocio parqueaderoN = new ParqueaderoNegocio();
		Factura factura = new Factura();
		Calendar calendario = new GregorianCalendar(2018, 1, 5, 7, 10);
		CalendarioDia fecha = Mockito.mock(CalendarioDia.class);
		Mockito.when(fecha.getFecha()).thenReturn(calendario.getTime());
		factura.setFechaEntrada(fecha.getFecha());
		//act
		vigilante.empezarFactura(parqueadero, parqueaderoN, vehiculo, factura);
		//assert
		assertNotNull(factura.getFechaEntrada());
		assertNotNull(factura.getPlaca());
		assertNotNull(factura.getHoraEntrada());
	}
		
	@Test(expected = EntradaDeVehiculoExcepcion.class)
	public void empezarFacturaTest2() {
		//arrange
		Parqueadero parqueadero = new Parqueadero(1,0,10);
		Vehiculo vehiculo = new Vehiculo("Afewfe","Carro",2000);
		Factura factura = new Factura();
		ParqueaderoNegocio parqueaderoN = new ParqueaderoNegocio();
		//act
		vigilante.empezarFactura(parqueadero, parqueaderoN, vehiculo, factura);
	}
	
	@Test(expected = EntradaDeVehiculoExcepcion.class)
	public void empezarFacturaTest3() {
		//arrange
		Parqueadero parqueadero = new Parqueadero(1,20,10);
		Vehiculo vehiculo = new Vehiculo("Afewfe","Carro",2000);
		Factura factura = new Factura();
		ParqueaderoNegocio parqueaderoN = new ParqueaderoNegocio();
		Calendar calendario = new GregorianCalendar(2018, 1, 6, 7, 10);
		CalendarioDia fecha = Mockito.mock(CalendarioDia.class);
		Mockito.when(fecha.getFecha()).thenReturn(calendario.getTime());
		factura.setFechaEntrada(fecha.getFecha());
		//act
		vigilante.empezarFactura(parqueadero, parqueaderoN, vehiculo, factura);
	}
	
	@Test
	public void terminarFacturaTest() {
		//arrange
		Parqueadero parqueadero = new Parqueadero(1,20,10);
		Vehiculo vehiculo = new Vehiculo("Afewfe","Carro",2000);
		Factura factura = new Factura();
		ParqueaderoNegocio parqueaderoN = new ParqueaderoNegocio();
		VehiculoNegocio vehiculoN = new VehiculoNegocio();
		Calendar calendario = new GregorianCalendar();
		factura.setFechaEntrada(calendario.getTime());
		//act
		vigilante.terminarFactura(parqueadero,parqueaderoN,vehiculo, vehiculoN,factura);
		//assert
		assertNotNull(factura.getFechaSalida());
		assertNotNull(factura.getHoraSalida());
		assertNotNull(factura.getTotal());
	}
}
