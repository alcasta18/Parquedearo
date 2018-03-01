package co.ceiba.test;

import static org.junit.Assert.*;


import java.util.Calendar;
import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import co.ceiba.parqueadero.Main;
import co.ceiba.parqueadero.dominio.ReporteEntradaDeVehiculo;
import co.ceiba.parqueadero.dominio.SolicitudDeEntradaOSalida;
import co.ceiba.parqueadero.dominio.servicios.Vigilante;
import co.ceiba.parqueadero.mappers.GeneradorDeReportesMapper;
import co.ceiba.parqueadero.repositorio.RepositorioVehiculo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Main.class)
@Transactional
public class ReporteTest {
	
	@Autowired
	private GeneradorDeReportesMapper generadorDeReportes;
	@Autowired
	private RepositorioVehiculo vehiculoRepo;
	@Autowired
	private Vigilante vigilante;
	
	@Test
	public void ReportesTest() {
		vigilante.ingresarVehiculoAlParqueadero(new SolicitudDeEntradaOSalida(3,"vehiculoPruebas"));
		List<ReporteEntradaDeVehiculo> reportes = generadorDeReportes.reporte();
		int numeroDeReportes = vehiculoRepo.numeroDeReportes();
		assertEquals(numeroDeReportes,reportes.size());
	}
	
	@Test
	public void gettersYSettersTest() {
		ReporteEntradaDeVehiculo reporte= new ReporteEntradaDeVehiculo();
		reporte.setTipo("Carro");
		reporte.setPlaca("ACR187");
		reporte.setFechaEntrada(Calendar.getInstance().getTime());
		assertEquals("Carro",reporte.getTipo());
		assertEquals("ACR187",reporte.getPlaca());
		assertEquals(Calendar.getInstance().getTime(),reporte.getFechaEntrada());
	}

}
