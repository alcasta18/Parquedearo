package co.ceiba.parqueadero.repositorio;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.ceiba.parqueadero.entity.FacturaEntity;


public interface RepositorioFactura extends JpaRepository<FacturaEntity,Serializable>{
	@Query(value="select f.fecha_entrada from factura f where f.placa = ?1 and f.fecha_salida is null",nativeQuery=true)
	  Date consultarFechaEntrada(String placa);
	@Query(value="select count(*) from factura  where placa = ?1 and fecha_salida is null", nativeQuery=true)
	  int vehiculoEstaFacturado(String placa);
	@Query(value="select * from factura  where placa = ?1 and fecha_salida is null", nativeQuery=true)
	  FacturaEntity consultarPorPlaca(String placa);
	
}
