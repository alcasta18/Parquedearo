package co.ceiba.interfaces;


import co.ceiba.dominio.Factura;
import co.ceiba.servicios.SolicitudEntradaVehiculo;
import co.ceiba.servicios.SolicitudSalidaVehiculo;

public interface IVigilante {
	public Factura ingresarVehiculoAlParqueadero(SolicitudEntradaVehiculo solicitud);
	public Factura terminarFactura(SolicitudSalidaVehiculo solicitud);
}
