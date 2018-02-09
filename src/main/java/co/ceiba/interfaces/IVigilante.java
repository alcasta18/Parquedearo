package co.ceiba.interfaces;

import java.util.Date;

import co.ceiba.dominio.Factura;
import co.ceiba.negocio.SolicitudEntradaVehiculo;
import co.ceiba.negocio.SolicitudSalidaVehiculo;

public interface IVigilante {
	public int buscarDia(Date fechaEntrada);
	public Factura ingresarVehiculoAlParqueadero(SolicitudEntradaVehiculo solicitud);
	public Factura terminarFactura(SolicitudSalidaVehiculo solicitud);
}
