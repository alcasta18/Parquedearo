package co.ceiba.test;



import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.parqueadero.Main;
import co.ceiba.parqueadero.dominio.servicios.ValidacionesDelVigilante;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Main.class)
public class ValidacionesDelVigilanteTest {
	@Autowired
	private ValidacionesDelVigilante vigilante;
	
	@Test
	public void vehiculoPuedeEntrarTest() {
		//arrange
		int dia = 1;
		String placa = "AAA333";
		//act
		boolean mensajeDelParqueadero = vigilante.vehiculoPuedeEntrar(placa, dia);
		//assert
		Assert.assertTrue(mensajeDelParqueadero);
	}
	
	@Test
	public void vehiculoPuedeEntrarTest2() {
		//arrange
		int dia = 1;
		String placa = "BAA333";
		//act
		boolean mensajeDelParqueadero = vigilante.vehiculoPuedeEntrar(placa, dia);
		//assert
		Assert.assertTrue(mensajeDelParqueadero);
	}
	
	@Test
	public void vehiculoNoPuedeEntrarTest() {
		//arrange
		int dia = 3;
		String placa = "AAA333";
		//act
		boolean mensajeDelParqueadero = vigilante.vehiculoPuedeEntrar(placa,dia);
		//assert
		Assert.assertFalse(mensajeDelParqueadero);
	}
	
	@Test
	public void vehiculoPuedeEntrarTest4() {
		//arrange
		int dia = 2;
		String placa = "aAA333";
		//act
		boolean mensajeDelParqueadero = vigilante.vehiculoPuedeEntrar(placa, dia);
		//assert
		Assert.assertTrue(mensajeDelParqueadero);
	}
	
	@Test
	public void hayCupoTest() {
		//arrange
		String tipo = "Carro";
		//act
		boolean hayCupo = vigilante.hayCupo(tipo,1,0);
		//assert
		Assert.assertTrue(hayCupo);
	}
	
	@Test
	public void noHayCupoTest() {
		//arrange
		String tipo = "Moto";
		//act
		boolean hayCupo = vigilante.hayCupo(tipo,1,0);
		//assert
		Assert.assertFalse(hayCupo);
	}
	
	@Test
	public void noHayCupoTest2() {
		//arrange
		String tipo = "Carro";
		//act
		boolean hayCupo = vigilante.hayCupo(tipo,0,0);
		//assert
		Assert.assertFalse(hayCupo);
	}
}
