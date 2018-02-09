package co.ceiba.repositorio;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.ceiba.entity.FacturaEntity;


public interface RepositorioFactura extends JpaRepository<FacturaEntity,Serializable>{
	@Query("update FacturaEntity set fechaSalida=?,horaSalida=?,total=? where facturaId=?")
	  FacturaEntity updateFactura(Date fechaSalida, String horaSalida,int total, int facturaId);
}
