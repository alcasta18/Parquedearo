package co.ceiba.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import co.ceiba.interfaces.IParqueaderoNegocio;
import co.ceiba.negocio.ParqueaderoNegocio;

public class ParqueaderoNegocioTest {
	
	private IParqueaderoNegocio parqueadero = new ParqueaderoNegocio();
	
	@Test
	public void vehiculoPuedeEntrarTest() {
		//arrange
		int dia = 1;
		String placa = "AAA333";
		//act
		boolean mensajeDelParqueadero = parqueadero.vehiculoPuedeEntrar(placa, dia);
		//assert
		Assert.assertTrue(mensajeDelParqueadero);
	}
	
	@Test
	public void vehiculoPuedeEntrarTest2() {
		//arrange
		int dia = 1;
		String placa = "BAA333";
		//act
		boolean mensajeDelParqueadero = parqueadero.vehiculoPuedeEntrar(placa, dia);
		//assert
		Assert.assertTrue(mensajeDelParqueadero);
	}
	
	@Test
	public void vehiculoNoPuedeEntrarTest() {
		//arrange
		int dia = 3;
		String placa = "AAA333";
		//act
		boolean mensajeDelParqueadero = parqueadero.vehiculoPuedeEntrar(placa,dia);
		//assert
		Assert.assertFalse(mensajeDelParqueadero);
	}
	
	@Test
	public void vehiculoPuedeEntrarTest4() {
		//arrange
		int dia = 2;
		String placa = "aAA333";
		//act
		boolean mensajeDelParqueadero = parqueadero.vehiculoPuedeEntrar(placa, dia);
		//assert
		Assert.assertTrue(mensajeDelParqueadero);
	}
	
	@Test
	public void hayCupoTest() {
		//arrange
		String tipo = "Carro";
		//act
		boolean hayCupo = parqueadero.hayCupo(tipo,1,0);
		//assert
		Assert.assertTrue(hayCupo);
	}
	
	@Test
	public void noHayCupoTest() {
		//arrange
		String tipo = "Moto";
		//act
		boolean hayCupo = parqueadero.hayCupo(tipo,1,0);
		//assert
		Assert.assertFalse(hayCupo);
	}
	
	@Test
	public void noHayCupoTest2() {
		//arrange
		String tipo = "Carro";
		//act
		boolean hayCupo = parqueadero.hayCupo(tipo,0,0);
		//assert
		Assert.assertFalse(hayCupo);
	}
	
	@Test
	public void ingresarVehiculoTest() {
		//arrange
		String tipo = "Carro";
		//act
		int resultado = parqueadero.ingresarVehiculo(tipo,1,0);
		//assert
		assertEquals(0,resultado);
	}
	
	@Test
	public void ingresarVehiculoTest2() {
		//arrange
		String tipo = "Moto";
		//act
		int resultado = parqueadero.ingresarVehiculo(tipo,1,4);
		//assert
		assertEquals(3,resultado);
	}
	
	@Test
	public void noIngresarVehiculoTest() {
		//arrange
		String tipo = "Moto";
		//act
		int resultado = parqueadero.ingresarVehiculo(tipo,1,0);
		//assert
		assertEquals(0,resultado);
	}
	
	@Test
	public void sacarVehiculoTest() {
		//arrange
		int precioParqueo = 5000;
		String tipo = "Carro";
		int capacidadCarros = 20;
		int capacidadMotos = 10;
		//act
		int capacidadActualizada = parqueadero.sacarVehiculo(tipo, precioParqueo, capacidadCarros, capacidadMotos);
		//assert
		assertEquals(19,capacidadActualizada);
	}
	
	@Test
	public void sacarVehiculoTest2() {
		//arrange
		int precioParqueo = 0;
		String tipo = "Carro";
		int capacidadCarros = 20;
		int capacidadMotos = 10;
		//act
		int capacidadActualizada = parqueadero.sacarVehiculo(tipo, precioParqueo, capacidadCarros, capacidadMotos);
		//assert
		assertEquals(20,capacidadActualizada);
	}
	
	@Test
	public void sacarVehiculoTest3() {
		//arrange
		int precioParqueo = 5000;
		String tipo = "Moto";
		int capacidadCarros = 20;
		int capacidadMotos = 10;
		//act
		int capacidadActualizada = parqueadero.sacarVehiculo(tipo, precioParqueo, capacidadCarros, capacidadMotos);
		//assert
		assertEquals(9,capacidadActualizada);
	}
	
	@Test
	public void sacarVehiculoTest4() {
		//arrange
		int precioParqueo = 0;
		String tipo = "Moto";
		int capacidadCarros = 20;
		int capacidadMotos = 10;
		//act
		int capacidadActualizada = parqueadero.sacarVehiculo(tipo, precioParqueo, capacidadCarros, capacidadMotos);
		//assert
		assertEquals(10,capacidadActualizada);
	}
}
