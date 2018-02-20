package co.ceiba.parqueadero.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.parqueadero.dominio.Factura;
import co.ceiba.parqueadero.dominio.SolicitudEntradaVehiculo;
import co.ceiba.parqueadero.dominio.SolicitudSalidaVehiculo;
import co.ceiba.parqueadero.dominio.servicios.Vigilante;
import co.ceiba.parqueadero.entity.FacturaEntity;
import co.ceiba.parqueadero.excepcion.EntradaDeVehiculoExcepcion;


@RestController
@RequestMapping("/api/servicios")
@EnableJpaRepositories("co.ceiba.repositorio")
@Import(FacturaEntity.class)

public class VigilanteRest {
	@Autowired
	@Qualifier("Vigilante")
	private Vigilante vigilante;

	
	
	@ExceptionHandler(EntradaDeVehiculoExcepcion.class)
	@PostMapping("/ingresar/vehiculo")
	public void ingresarVehiculo(@RequestBody SolicitudEntradaVehiculo solicitud) {
			vigilante.ingresarVehiculoAlParqueadero(solicitud);	
	}
	
	@ExceptionHandler(EntradaDeVehiculoExcepcion.class)
	@PostMapping("/terminar")
	public void sacarVehiculo(@RequestBody SolicitudSalidaVehiculo solicitud) {
		vigilante.terminarFactura(solicitud);
	}
	
	@RequestMapping(value = "buscar/facturaId={facturaId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Factura buscar(@PathVariable("facturaId") int facturaId) {
		return vigilante.buscarFactura(facturaId);
	}
	

}
