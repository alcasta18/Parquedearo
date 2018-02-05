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
import co.ceiba.entity.FacturaEntity;
import co.ceiba.repositorio.RepositorioFactura;

@RestController
@RequestMapping("/rest/factura")
@EnableJpaRepositories("co.ceiba.repositorio")
@Import(FacturaEntity.class)
public class FacturaService {
	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private FacturaEntity facturaEntity;
	@Autowired
	private RepositorioFactura facturaRepositorio;
}
