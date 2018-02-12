package co.ceiba.repositorio;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.ceiba.entity.VehiculoEntity;

import co.ceiba.dominio.ReporteEntradaDeVehiculo;

public interface RepositorioVehiculo extends JpaRepository<VehiculoEntity, Serializable> {
	@Query("select v from VehiculoEntity v where v.placa = ?1")
	  VehiculoEntity findByPlaca(String placa);
	@Query(value="select v.placa,v.tipo,f.fecha_entrada from vehiculo v natural join factura f",nativeQuery=true)
	  List<ReporteEntradaDeVehiculo> unirVehiculosConFactura();
}
