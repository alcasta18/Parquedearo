package co.ceiba.test;


import static org.junit.Assert.*;




import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.Main;
import co.ceiba.dominio.Factura;
import co.ceiba.excepcion.EntradaDeVehiculoExcepcion;
import co.ceiba.negocio.SolicitudEntradaVehiculo;
import co.ceiba.negocio.SolicitudSalidaVehiculo;
import co.ceiba.negocio.Vigilante;

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
}
