package co.ceiba.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.Main;
import co.ceiba.dominio.Parqueadero;
import co.ceiba.interfaces.IParqueaderoNegocio;
import co.ceiba.negocio.ParqueaderoNegocio;

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
	public void ingresarVehiculoTest() {
		//arrange
		String tipo = "Carro";
		//act
		int resultado = parqueaderoN.ingresarVehiculo(tipo,1,0);
		//assert
		assertEquals(0,resultado);
	}
	
	@Test
	public void ingresarVehiculoTest2() {
		//arrange
		String tipo = "Moto";
		//act
		int resultado = parqueaderoN.ingresarVehiculo(tipo,1,4);
		//assert
		assertEquals(3,resultado);
	}
	
	@Test
	public void noIngresarVehiculoTest() {
		//arrange
		String tipo = "Moto";
		//act
		int resultado = parqueaderoN.ingresarVehiculo(tipo,1,0);
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
		int capacidadActualizada = parqueaderoN.sacarVehiculo(tipo, precioParqueo, capacidadCarros, capacidadMotos);
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
		int capacidadActualizada = parqueaderoN.sacarVehiculo(tipo, precioParqueo, capacidadCarros, capacidadMotos);
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
		int capacidadActualizada = parqueaderoN.sacarVehiculo(tipo, precioParqueo, capacidadCarros, capacidadMotos);
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
		int capacidadActualizada = parqueaderoN.sacarVehiculo(tipo, precioParqueo, capacidadCarros, capacidadMotos);
		//assert
		assertEquals(10,capacidadActualizada);
	}
	
	@Test
	public void obtenerParqueaderoTest() {
		Parqueadero parqueadero = parqueaderoN.obtenerParqueadero(1);
		Parqueadero parqueadero2 = new Parqueadero(1,20,10);
		assertEquals(parqueadero2.getParqueaderoId(),parqueadero.getParqueaderoId());
		assertEquals(parqueadero2.getCapacidadCarros(),parqueadero.getCapacidadCarros());
		assertEquals(parqueadero2.getCapacidadMotos(),parqueadero.getCapacidadMotos());
	}
}
