package co.ceiba.parqueadero.interfaces;



import co.ceiba.parqueadero.dominio.Factura;
import co.ceiba.parqueadero.dominio.SolicitudDeEntradaOSalida;

public interface IVigilante {
	public Factura ingresarVehiculoAlParqueadero(SolicitudDeEntradaOSalida solicitud);
	public Factura terminarFactura(SolicitudDeEntradaOSalida solicitud);
}
