package co.ceiba.repositorio;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import co.ceiba.entity.FacturaEntity;

public interface RepositorioFactura extends JpaRepository<FacturaEntity,Serializable>{

}
