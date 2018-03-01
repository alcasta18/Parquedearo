package co.ceiba.test;


import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import co.ceiba.parqueadero.Main;
import co.ceiba.parqueadero.dominio.Factura;
import co.ceiba.parqueadero.dominio.SolicitudDeEntradaOSalida;
import co.ceiba.parqueadero.dominio.Vehiculo;
import co.ceiba.parqueadero.dominio.servicios.Vigilante;
import co.ceiba.parqueadero.excepcion.EntradaDeVehiculoExcepcion;
import co.ceiba.parqueadero.mappers.VehiculoMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Main.class)
@Transactional
public class VigilanteTest {
	@Autowired
	private Vigilante vigilante;
	@Autowired
	private VehiculoMapper vehiculoRepo;
	
	@Test
	public void empezarFacturaTest() {
		//arrange
		SolicitudDeEntradaOSalida solicitud = new SolicitudDeEntradaOSalida(1,"LRA60C");
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
		SolicitudDeEntradaOSalida solicitud = new SolicitudDeEntradaOSalida(2,"vigilantePruebas");
		//act
		vigilante.ingresarVehiculoAlParqueadero(solicitud);
	}
	

	
	@Test
	public void terminarFacturaTest() {
		//arrange
		SolicitudDeEntradaOSalida solicitud = new SolicitudDeEntradaOSalida(3,"vehiculoPruebas");
		vigilante.ingresarVehiculoAlParqueadero(solicitud);
		//act
		Factura factura = vigilante.terminarFactura(solicitud);
		//assert
		assertNotNull(factura.getFechaSalida());
		assertNotNull(factura.getHoraSalida());
		assertNotNull(factura.getTotal());
	}
	
	@Test(expected = EntradaDeVehiculoExcepcion.class)
	public void terminarFacturaTest2() {
		//arrange
		SolicitudDeEntradaOSalida solicitud = new SolicitudDeEntradaOSalida(1,"vehiculoPruebas");
		//act
		vigilante.terminarFactura(solicitud);
	}
	
	@Test
	public void guardarVehiculoEnBDTest() {
		Vehiculo vehiculo = new Vehiculo("Casta","Carro",5600);
		vehiculoRepo.guardarVehiculoEnBD(vehiculo);
		Vehiculo vehiculo2 = vigilante.obtenerVehiculo(vehiculo.getPlaca());
		assertEquals(vehiculo.getPlaca(),vehiculo2.getPlaca());
		assertEquals(vehiculo.getTipo(),vehiculo2.getTipo());
		assertEquals(vehiculo.getCilindraje(),vehiculo2.getCilindraje());
	}
	
}
