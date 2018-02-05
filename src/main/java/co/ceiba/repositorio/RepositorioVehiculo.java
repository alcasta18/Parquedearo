package co.ceiba.repositorio;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.ceiba.entity.VehiculoEntity;

public interface RepositorioVehiculo extends JpaRepository<VehiculoEntity, Serializable> {
	@Query("select v from VehiculoEntity v where v = ?1")
	  VehiculoEntity findByPlaca(String placa);
}
