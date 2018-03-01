package co.ceiba.parqueadero.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.parqueadero.dominio.Factura;
import co.ceiba.parqueadero.dominio.SolicitudDeEntradaOSalida;
import co.ceiba.parqueadero.dominio.servicios.Vigilante;
import co.ceiba.parqueadero.entity.FacturaEntity;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/servicios")
@Import(FacturaEntity.class)

public class VigilanteRest {
	@Autowired
	private Vigilante vigilante;

	
	
	@PostMapping("/generar")
	public void ingresarVehiculo(@RequestBody SolicitudDeEntradaOSalida solicitud) {
			vigilante.ingresarVehiculoAlParqueadero(solicitud);	
	}
	
	@PutMapping("/terminar")
	public Factura sacarVehiculo(@RequestBody SolicitudDeEntradaOSalida solicitud) {
		return vigilante.terminarFactura(solicitud);
	}
}
