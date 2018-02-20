package co.ceiba.parqueadero.interfaces;


import co.ceiba.parqueadero.dominio.Factura;
import co.ceiba.parqueadero.dominio.SolicitudEntradaVehiculo;
import co.ceiba.parqueadero.dominio.SolicitudSalidaVehiculo;

public interface IVigilante {
	public Factura ingresarVehiculoAlParqueadero(SolicitudEntradaVehiculo solicitud);
	public Factura terminarFactura(SolicitudSalidaVehiculo solicitud);
}
