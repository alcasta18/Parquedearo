package co.ceiba.repositorio;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import co.ceiba.entity.ParqueaderoEntity;



public interface RepositorioParqueadero extends JpaRepository<ParqueaderoEntity, Serializable> {
	
}
