package co.ceiba.parqueadero.repositorio;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.ceiba.parqueadero.dominio.ReporteEntradaDeVehiculo;
import co.ceiba.parqueadero.entity.VehiculoEntity;


public interface RepositorioVehiculo extends JpaRepository<VehiculoEntity, Serializable> {
	@Query("select v from VehiculoEntity v where v.placa = ?1")
	  VehiculoEntity findByPlaca(String placa);
	@Query(value="select v.placa,v.tipo,f.fecha_entrada from vehiculo v natural join factura f "
			+ "where fecha_salida is null",nativeQuery=true)
	  List<ReporteEntradaDeVehiculo> unirVehiculosConFactura();
	@Query(value="select count(*) from vehiculo v natural join factura f "
			+ "where fecha_salida is null",nativeQuery=true)
		int numeroDeReportes();
}
