package co.ceiba.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.Main;
import co.ceiba.dominio.Parqueadero;
import co.ceiba.servicios.ParqueaderoNegocio;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Main.class)
public class ParqueaderoNegocioTest {
	
	@Autowired
	private ParqueaderoNegocio parqueaderoN;
	
	@Test
	public void vehiculoPuedeEntrarTest() {
		//arrange
		int dia = 1;
		String placa = "AAA333";
		//act
		boolean mensajeDelParqueadero = parqueaderoN.vehiculoPuedeEntrar(placa, dia);
		//assert
		Assert.assertTrue(mensajeDelParqueadero);
	}
	
	@Test
	public void vehiculoPuedeEntrarTest2() {
		//arrange
		int dia = 1;
		String placa = "BAA333";
		//act
		boolean mensajeDelParqueadero = parqueaderoN.vehiculoPuedeEntrar(placa, dia);
		//assert
		Assert.assertTrue(mensajeDelParqueadero);
	}
	
	@Test
	public void vehiculoNoPuedeEntrarTest() {
		//arrange
		int dia = 3;
		String placa = "AAA333";
		//act
		boolean mensajeDelParqueadero = parqueaderoN.vehiculoPuedeEntrar(placa,dia);
		//assert
		Assert.assertFalse(mensajeDelParqueadero);
	}
	
	@Test
	public void vehiculoPuedeEntrarTest4() {
		//arrange
		int dia = 2;
		String placa = "aAA333";
		//act
		boolean mensajeDelParqueadero = parqueaderoN.vehiculoPuedeEntrar(placa, dia);
		//assert
		Assert.assertTrue(mensajeDelParqueadero);
	}
	
	@Test
	public void hayCupoTest() {
		//arrange
		String tipo = "Carro";
		//act
		boolean hayCupo = parqueaderoN.hayCupo(tipo,1,0);
		//assert
		Assert.assertTrue(hayCupo);
	}
	
	@Test
	public void noHayCupoTest() {
		//arrange
		String tipo = "Moto";
		//act
		boolean hayCupo = parqueaderoN.hayCupo(tipo,1,0);
		//assert
		Assert.assertFalse(hayCupo);
	}
	
	@Test
	public void noHayCupoTest2() {
		//arrange
		String tipo = "Carro";
		//act
		boolean hayCupo = parqueaderoN.hayCupo(tipo,0,0);
		//assert
		Assert.assertFalse(hayCupo);
	}
	
	@Test
	public void actualizarCapacidadAlIngresoDeVehiculoTest() {
		String tipo = "Carro";
		int parqueaderoId = 1;
		parqueaderoN.actualizarCapacidadAlIngresoDeVehiculo(tipo, parqueaderoId);
		parqueaderoN = Mockito.mock(ParqueaderoNegocio.class);
		Parqueadero parqueadero2 = new Parqueadero(4,19,0);
		Mockito.when(parqueaderoN.obtenerParqueadero(parqueaderoId)).thenReturn(parqueadero2);
		Parqueadero parqueadero = parqueaderoN.obtenerParqueadero(parqueaderoId);
		assertEquals(19,parqueadero.getCapacidadCarros());
	}
	
	@Test
	public void actualizarCapacidadAlIngresoDeVehiculoTest2() {
		String tipo = "Moto";
		int parqueaderoId = 1;
		parqueaderoN.actualizarCapacidadAlIngresoDeVehiculo(tipo, parqueaderoId);
		parqueaderoN = Mockito.mock(ParqueaderoNegocio.class);
		Parqueadero parqueadero2 = new Parqueadero(4,0,9);
		Mockito.when(parqueaderoN.obtenerParqueadero(parqueaderoId)).thenReturn(parqueadero2);
		Parqueadero parqueadero = parqueaderoN.obtenerParqueadero(parqueaderoId);
		assertEquals(9,parqueadero.getCapacidadMotos());
	}
	
	@Test
	public void actualizarCapacidadAlSalirVehiculoTest() {
		String tipo = "Carro";
		int parqueaderoId = 1;
		parqueaderoN.actualizarCapacidadAlSalirUnVehiculo(tipo, parqueaderoId);
		parqueaderoN = Mockito.mock(ParqueaderoNegocio.class);
		Parqueadero parqueadero2 = new Parqueadero(4,20,0);
		Mockito.when(parqueaderoN.obtenerParqueadero(parqueaderoId)).thenReturn(parqueadero2);
		Parqueadero parqueadero = parqueaderoN.obtenerParqueadero(parqueaderoId);
		assertEquals(20,parqueadero.getCapacidadCarros());
	}
	

	
	@Test
	public void actualizarCapacidadAlSalirVehiculoTest2() {
		String tipo = "Moto";
		int parqueaderoId = 1;
		parqueaderoN.actualizarCapacidadAlSalirUnVehiculo(tipo, parqueaderoId);
		parqueaderoN = Mockito.mock(ParqueaderoNegocio.class);
		Parqueadero parqueadero2 = new Parqueadero(4,1,10);
		Mockito.when(parqueaderoN.obtenerParqueadero(parqueaderoId)).thenReturn(parqueadero2);
		Parqueadero parqueadero = parqueaderoN.obtenerParqueadero(parqueaderoId);
		assertEquals(10,parqueadero.getCapacidadMotos());
	}

	
	@Test
	public void obtenerParqueaderoTest() {
		Parqueadero parqueadero = parqueaderoN.obtenerParqueadero(3);
		Parqueadero parqueadero2 = new Parqueadero(4,15,0);
		assertEquals(parqueadero2.getCapacidadCarros(),parqueadero.getCapacidadCarros());
		assertEquals(parqueadero2.getCapacidadMotos(),parqueadero.getCapacidadMotos());
	}
	
}
	

