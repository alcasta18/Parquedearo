package co.ceiba.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import co.ceiba.negocio.VehiculoNegocio;
import co.ceiba.repositorio.RepositorioFactura;
import co.ceiba.repositorio.RepositorioParqueadero;
import co.ceiba.repositorio.RepositorioVehiculo;

@RestController
@RequestMapping("/rest/servicios")
@EnableJpaRepositories("co.ceiba.repositorio")
@Import(FacturaEntity.class)

public class VigilanteService {
	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private FacturaEntity facturaEntity;
	@Autowired
	private RepositorioFactura facturaRepositorio;
	@Autowired
	private RepositorioVehiculo vehiculoRepository;
	@Autowired
	private RepositorioParqueadero repositorioParqueadero;
	@Autowired
	@Qualifier("parqueaderoNegocio")
	private ParqueaderoNegocio parqueaderoN;
	@Autowired
	@Qualifier("FacturaNegocio")
	private FacturaNegocio facturaN;
	@Autowired
	@Qualifier("VehiculoNegocio")
	private VehiculoNegocio vehiculoN;
	
	
	@PostMapping("/ingresar/vehiculo")
	public void ingresarVehiculo(@RequestBody Factura factura,String parqueaderoId,String placa) {
		Vehiculo vehiculo = modelMapper.map(vehiculoRepository.findByPlaca(placa),Vehiculo.class);
		Parqueadero parqueadero = modelMapper.map(repositorioParqueadero.findOne(parqueaderoId),Parqueadero.class);
		facturaN.empezarFactura(parqueadero, parqueaderoN, vehiculo, factura);
		facturaEntity = modelMapper.map(factura, FacturaEntity.class);
		facturaRepositorio.save(facturaEntity);
	}
	
	@PostMapping("sacar/vehiculo")
	public void sacarVehiculo(@RequestBody int facturaId,String parqueaderoId,String placa) {
		Factura factura = modelMapper.map(facturaRepositorio.findOne(facturaId),Factura.class);
		Vehiculo vehiculo = modelMapper.map(vehiculoRepository.findByPlaca(placa),Vehiculo.class);
		Parqueadero parqueadero = modelMapper.map(repositorioParqueadero.findOne(parqueaderoId),Parqueadero.class);
		facturaN.terminarFactura(parqueadero, parqueaderoN, vehiculo, vehiculoN, factura);
	}
}
