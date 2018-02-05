package co.ceiba.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import co.ceiba.dominio.Vehiculo;
import co.ceiba.interfaces.IVehiculoNegocio;
import co.ceiba.negocio.VehiculoNegocio;


public class VehiculoNegocioTest {
	
	private IVehiculoNegocio vehiculo = new VehiculoNegocio();
	
	@Test
	public void calcularPrecioTest() {
		//arrange
		int numHoras = 10;
		int cilindraje = 650;
		String tipo = "Moto";
		//act
		int totalMoto = vehiculo.calcularPrecio(numHoras,cilindraje,tipo);
		//assert
		assertEquals(6000, totalMoto);
	}
	
	@Test
	public void calcularPrecioTest2(){
		//arrange
		int numHoras = 32;
		int cilindraje = 650;
		String tipo = "Moto";		
		//act
		int totalMoto = vehiculo.calcularPrecio(numHoras,cilindraje,tipo);
		//assert
		assertEquals(10000, totalMoto);
	}
	
	@Test
	public void calcularPrecioTest3(){
		//arrange
		int numHoras = 35;
		int cilindraje = 150;
		String tipo = "Carro";
		//act
		int totalMoto = vehiculo.calcularPrecio(numHoras,cilindraje,tipo);
		//assert
		assertEquals(16000, totalMoto);
	}
	
	@Test
	public void calcularPrecioTest4(){
		//arrange
		int numHoras = 25;
		int cilindraje = 150;
		String tipo = "Carro";
		//act
		int totalMoto = vehiculo.calcularPrecio(numHoras,cilindraje,tipo);
		//assert
		assertEquals(9000, totalMoto);
	}
	
	@Test
	public void calcularPrecioTest5(){
		//arrange
		int numHoras = 5;
		int cilindraje = 150;
		String tipo = "Moto";
		//act
		int totalMoto = vehiculo.calcularPrecio(numHoras,cilindraje,tipo);
		//assert
		assertEquals(2500, totalMoto);
	}
}
