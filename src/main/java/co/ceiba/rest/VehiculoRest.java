package co.ceiba.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.dominio.Vehiculo;
import co.ceiba.negocio.VehiculoNegocio;

@RestController
@RequestMapping("/rest/vehiculo")
public class VehiculoRest {
	
	@Autowired
	private VehiculoNegocio vehiculoN;
	@PostMapping("/crear")
	public void crear(@RequestBody Vehiculo vehiculo) {
		vehiculoN.guardarVehiculoEnBD(vehiculo);
	}
	
	@RequestMapping(value = "/placa={placa}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Vehiculo buscar(@PathVariable("placa") String placa) {
		return vehiculoN.obtenerVehiculo(placa);
	}
	
}
