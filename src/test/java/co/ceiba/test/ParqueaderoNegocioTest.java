package co.ceiba.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.parqueadero.Main;
import co.ceiba.parqueadero.dominio.Parqueadero;
import co.ceiba.parqueadero.dominio.servicios.ParqueaderoNegocio;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Main.class)
public class ParqueaderoNegocioTest {
	
	@Autowired
	private ParqueaderoNegocio parqueaderoN;
	

	
	@Test
	public void actualizarCapacidadAlIngresoDeVehiculoTest() {
		String tipo = "Carro";
		int parqueaderoId = 3;
		parqueaderoN.actualizarCapacidadAlIngresoDeVehiculo(tipo, parqueaderoId);
		parqueaderoN = Mockito.mock(ParqueaderoNegocio.class);
		Parqueadero parqueadero2 = new Parqueadero(4,17,0);
		Mockito.when(parqueaderoN.obtenerParqueadero(parqueaderoId)).thenReturn(parqueadero2);
		Parqueadero parqueadero = parqueaderoN.obtenerParqueadero(parqueaderoId);
		assertEquals(17,parqueadero.getCapacidadCarros());
	}
	
	@Test
	public void actualizarCapacidadAlIngresoDeVehiculoTest2() {
		String tipo = "Moto";
		int parqueaderoId = 3;
		parqueaderoN.actualizarCapacidadAlIngresoDeVehiculo(tipo, parqueaderoId);
		parqueaderoN = Mockito.mock(ParqueaderoNegocio.class);
		Parqueadero parqueadero2 = new Parqueadero(4,0,6);
		Mockito.when(parqueaderoN.obtenerParqueadero(parqueaderoId)).thenReturn(parqueadero2);
		Parqueadero parqueadero = parqueaderoN.obtenerParqueadero(parqueaderoId);
		assertEquals(6,parqueadero.getCapacidadMotos());
	}
	
	@Test
	public void actualizarCapacidadAlSalirVehiculoTest() {
		String tipo = "Carro";
		int parqueaderoId = 3;
		parqueaderoN.actualizarCapacidadAlSalirUnVehiculo(tipo, parqueaderoId);
		parqueaderoN = Mockito.mock(ParqueaderoNegocio.class);
		Parqueadero parqueadero2 = new Parqueadero(4,18,0);
		Mockito.when(parqueaderoN.obtenerParqueadero(parqueaderoId)).thenReturn(parqueadero2);
		Parqueadero parqueadero = parqueaderoN.obtenerParqueadero(parqueaderoId);
		assertEquals(18,parqueadero.getCapacidadCarros());
	}
	

	
	@Test
	public void actualizarCapacidadAlSalirVehiculoTest2() {
		String tipo = "Moto";
		int parqueaderoId = 1;
		parqueaderoN.actualizarCapacidadAlSalirUnVehiculo(tipo, parqueaderoId);
		parqueaderoN = Mockito.mock(ParqueaderoNegocio.class);
		Parqueadero parqueadero2 = new Parqueadero(4,1,7);
		Mockito.when(parqueaderoN.obtenerParqueadero(parqueaderoId)).thenReturn(parqueadero2);
		Parqueadero parqueadero = parqueaderoN.obtenerParqueadero(parqueaderoId);
		assertEquals(7,parqueadero.getCapacidadMotos());
	}

	
	@Test
	public void obtenerParqueaderoTest() {
		Parqueadero parqueadero = parqueaderoN.obtenerParqueadero(2);
		Parqueadero parqueadero2 = new Parqueadero(4,0,0);
		assertEquals(parqueadero2.getCapacidadCarros(),parqueadero.getCapacidadCarros());
		assertEquals(parqueadero2.getCapacidadMotos(),parqueadero.getCapacidadMotos());
	}
	
}
	

