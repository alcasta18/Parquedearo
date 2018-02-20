package co.ceiba.parqueadero.repositorio;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import co.ceiba.parqueadero.entity.ParqueaderoEntity;



public interface RepositorioParqueadero extends JpaRepository<ParqueaderoEntity, Serializable> {
	
}
