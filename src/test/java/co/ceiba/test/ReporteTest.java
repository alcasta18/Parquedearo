package co.ceiba.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.parqueadero.Main;
import co.ceiba.parqueadero.dominio.ReporteEntradaDeVehiculo;
import co.ceiba.parqueadero.mappers.GeneradorDeReportesMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Main.class)
public class ReporteTest {
	
	
	@Test
	public void ReportesTest() {
		ReporteEntradaDeVehiculo reporte1 = new ReporteEntradaDeVehiculo("ACR18A", "Carro", null);
		ReporteEntradaDeVehiculo reporte2 = new ReporteEntradaDeVehiculo("BCR18A", "Moto", null);
		List<ReporteEntradaDeVehiculo> reportesMock = new ArrayList<>();
		reportesMock.add(reporte1);
		reportesMock.add(reporte2);
		GeneradorDeReportesMapper generadorDeReportes = Mockito.mock(GeneradorDeReportesMapper.class);
		Mockito.when(generadorDeReportes.reporte()).thenReturn(reportesMock);
		List<ReporteEntradaDeVehiculo> reportes = generadorDeReportes.reporte();
		assertEquals(2,reportes.size());
	}

}
