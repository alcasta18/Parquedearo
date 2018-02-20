package co.ceiba.parqueadero.rest;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.parqueadero.dominio.ReporteEntradaDeVehiculo;
import co.ceiba.parqueadero.dominio.Vehiculo;
import co.ceiba.parqueadero.dominio.servicios.CalculadoraDePrecio;
import co.ceiba.parqueadero.mappers.GeneradorDeReportesMapper;

@RestController
@RequestMapping("/api/vehiculo")
public class VehiculoRest {
	
	@Autowired
	private CalculadoraDePrecio vehiculoN;
	@Autowired
	private GeneradorDeReportesMapper vehiculoRepo;
	
	@PostMapping("/crear")
	public void crear(@RequestBody Vehiculo vehiculo) {
		vehiculoN.guardarVehiculoEnBD(vehiculo);
	}
	
	@RequestMapping(value = "/placa={placa}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Vehiculo buscar(@PathVariable("placa") String placa) {
		return vehiculoN.obtenerVehiculo(placa);
	}
	
	@GetMapping("/reportes")
	public List<ReporteEntradaDeVehiculo> reporte(){
		return vehiculoRepo.reporte();
	}

}
