package co.ceiba.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.dominio.Factura;
import co.ceiba.dominio.Parqueadero;
import co.ceiba.dominio.Vehiculo;
import co.ceiba.entity.FacturaEntity;
import co.ceiba.negocio.FacturaNegocio;
import co.ceiba.negocio.ParqueaderoNegocio;
import co.ceiba.repositorio.RepositorioFactura;
import co.ceiba.repositorio.RepositorioParqueadero;

@RestController
@RequestMapping("/rest/servicios")
@EnableJpaRepositories("co.ceiba.repositorio")
@Import(FacturaEntity.class)
public class NegocioService {
	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private FacturaEntity facturaEntity;
	@Autowired
	private RepositorioFactura facturaRepositorio;
	
	@PostMapping("/ingresar/vehiculo")
	public void ingresarVehiculo(@RequestBody Factura factura,String parqueaderoId,String placa) {
		VehiculoService vehiculoService = new VehiculoService();
		Vehiculo vehiculo = vehiculoService.buscar(placa);
		ParqueaderoService parqService = new ParqueaderoService();
		Parqueadero parqueadero = parqService.consultar(parqueaderoId);
		ParqueaderoNegocio parqueaderoN = new ParqueaderoNegocio();
		FacturaNegocio facturaN = new FacturaNegocio();
		facturaN.empezarFactura(parqueadero, parqueaderoN, vehiculo, factura);
		facturaEntity = modelMapper.map(factura, FacturaEntity.class);
		facturaRepositorio.save(facturaEntity);
	}
}
