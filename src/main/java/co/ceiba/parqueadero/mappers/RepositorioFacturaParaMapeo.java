package co.ceiba.parqueadero.mappers;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.ceiba.parqueadero.dominio.Factura;
import co.ceiba.parqueadero.entity.FacturaEntity;
import co.ceiba.parqueadero.repositorio.RepositorioFactura;

@Component("RepositorioFacturaParaMapeo")
public class RepositorioFacturaParaMapeo{
	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private RepositorioFactura facturaRepo;
	@Transactional
	public void guardarFactura(Factura factura) {
		facturaRepo.save(modelMapper.map(factura, FacturaEntity.class));
	}
	
	@Transactional
	public Factura getFactura(long facturaId) {
		return modelMapper.map(facturaRepo.findOne(facturaId), Factura.class);
	}
}
