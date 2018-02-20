package co.ceiba.parqueadero.mappers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.ceiba.parqueadero.dominio.Factura;
import co.ceiba.parqueadero.dominio.ReporteEntradaDeVehiculo;
import co.ceiba.parqueadero.entity.FacturaEntity;
import co.ceiba.parqueadero.entity.VehiculoEntity;
import co.ceiba.parqueadero.repositorio.RepositorioFactura;
import co.ceiba.parqueadero.repositorio.RepositorioVehiculo;
@Component
public class GeneradorDeReportesMapper {
	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private RepositorioVehiculo vehiculoRepo;
	@Autowired
	private RepositorioFactura facturaRepo;
	
	public List<ReporteEntradaDeVehiculo> reporte(){
		List<ReporteEntradaDeVehiculo>reportes = new ArrayList<>();
		List<FacturaEntity> facturas = facturaRepo.findAll();
		for(FacturaEntity facturaE : facturas) {
			Factura factura = modelMapper.map(facturaE, Factura.class);
			if(factura.getTotal()==0) {
				VehiculoEntity vehiculo = vehiculoRepo.findByPlaca(factura.getPlaca());
				ReporteEntradaDeVehiculo reporte = new ReporteEntradaDeVehiculo(vehiculo.getPlaca(), 
						vehiculo.getTipo(), factura.getFechaEntrada());
				reportes.add(reporte);
			}
		}
		return reportes;
	}
}
