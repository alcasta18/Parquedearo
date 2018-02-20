package co.ceiba.test;


import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.parqueadero.Main;
import co.ceiba.parqueadero.dominio.Factura;
import co.ceiba.parqueadero.dominio.SolicitudEntradaVehiculo;
import co.ceiba.parqueadero.dominio.SolicitudSalidaVehiculo;
import co.ceiba.parqueadero.dominio.servicios.Vigilante;
import co.ceiba.parqueadero.excepcion.EntradaDeVehiculoExcepcion;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Main.class)

public class VigilanteTest {
	@Autowired
	private Vigilante vigilante;
	
	@Test
	public void empezarFacturaTest() {
		//arrange
		SolicitudEntradaVehiculo solicitud = new SolicitudEntradaVehiculo(1,"CEI15A");
		//act
		Factura factura = vigilante.ingresarVehiculoAlParqueadero(solicitud);
		//assert
		assertNotNull(factura.getFechaEntrada());
		assertNotNull(factura.getPlaca());
		assertNotNull(factura.getHoraEntrada());
	}
		
	@Test(expected = EntradaDeVehiculoExcepcion.class)
	public void empezarFacturaTest2() {
		//arrange
		SolicitudEntradaVehiculo solicitud = new SolicitudEntradaVehiculo(2,"CEI15A");
		//act
		vigilante.ingresarVehiculoAlParqueadero(solicitud);
	}
	

	
	@Test
	public void terminarFacturaTest() {
		//arrange
		SolicitudSalidaVehiculo solicitud = new SolicitudSalidaVehiculo(1,1);
		//act
		Factura factura = vigilante.terminarFactura(solicitud);
		//assert
		assertNotNull(factura.getFechaSalida());
		assertNotNull(factura.getHoraSalida());
		assertNotNull(factura.getTotal());
	}
	
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
