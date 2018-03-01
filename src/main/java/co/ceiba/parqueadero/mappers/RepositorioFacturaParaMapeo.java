package co.ceiba.parqueadero.mappers;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.ceiba.parqueadero.dominio.Factura;
import co.ceiba.parqueadero.entity.FacturaEntity;
import co.ceiba.parqueadero.excepcion.EntradaDeVehiculoExcepcion;
import co.ceiba.parqueadero.repositorio.RepositorioFactura;

@Component("RepositorioFacturaParaMapeo")
public class RepositorioFacturaParaMapeo{
	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private RepositorioFactura facturaRepo;
	
	public void guardarFactura(Factura factura) {
		facturaRepo.save(modelMapper.map(factura, FacturaEntity.class));
	}
	
	public Factura getFacturaPorPlaca(String placa) {
		if(facturaRepo.consultarPorPlaca(placa)!=null)
			return modelMapper.map(facturaRepo.consultarPorPlaca(placa), Factura.class);
		else
			throw new EntradaDeVehiculoExcepcion("El vehiculo no esta en el parqueadero, o no se encuentra registrado");
	}
}
