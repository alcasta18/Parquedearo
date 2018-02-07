package co.ceiba.rest;

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
import co.ceiba.negocio.ParqueaderoNegocio;
import co.ceiba.negocio.VehiculoNegocio;
import co.ceiba.negocio.Vigilante;
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
	@Qualifier("ParqueaderoNegocio")
	private ParqueaderoNegocio parqueaderoN;
	@Autowired
	@Qualifier("Vigilante")
	private Vigilante vigilante;
	@Autowired
	@Qualifier("VehiculoNegocio")
	private VehiculoNegocio vehiculoN;
	
	
	@PostMapping("/ingresar/vehiculo")
	public void ingresarVehiculo(@RequestBody Factura factura,int parqueaderoId) {
		try{
			Vehiculo vehiculo = modelMapper.map(vehiculoRepository.findByPlaca(factura.getPlaca()),Vehiculo.class);
			Parqueadero parqueadero = modelMapper.map(repositorioParqueadero.findOne(parqueaderoId),Parqueadero.class);
			vigilante.empezarFactura(parqueadero, parqueaderoN, vehiculo, factura);
			facturaEntity = modelMapper.map(factura, FacturaEntity.class);
			facturaRepositorio.save(facturaEntity);
		}
		catch(Exception e) {
			e.getMessage();
		}
		
	}
	
	@PostMapping("sacar/vehiculo")
	public void sacarVehiculo(@RequestBody int facturaId,String parqueaderoId) {
		try {
			Factura factura = modelMapper.map(facturaRepositorio.findOne(facturaId),Factura.class);
			Vehiculo vehiculo = modelMapper.map(vehiculoRepository.findByPlaca(factura.getPlaca()),Vehiculo.class);
			Parqueadero parqueadero = modelMapper.map(repositorioParqueadero.findOne(parqueaderoId),Parqueadero.class);
			vigilante.terminarFactura(parqueadero, parqueaderoN, vehiculo, vehiculoN, factura);
			facturaEntity = modelMapper.map(factura, FacturaEntity.class);
			facturaRepositorio.save(facturaEntity);
		}
		catch(Exception e) {
			e.getMessage();
		}
	
	}
}
