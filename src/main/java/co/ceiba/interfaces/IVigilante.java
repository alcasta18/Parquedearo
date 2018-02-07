package co.ceiba.interfaces;

import java.util.Date;

import co.ceiba.dominio.Factura;
import co.ceiba.dominio.Parqueadero;
import co.ceiba.dominio.Vehiculo;
import co.ceiba.negocio.ParqueaderoNegocio;
import co.ceiba.negocio.VehiculoNegocio;

public interface IVigilante {
	public int buscarDia(Date fechaEntrada);
	public void empezarFactura(Parqueadero parqueadero,ParqueaderoNegocio parqueaderoN,Vehiculo vehiculo,Factura factura);
	public void terminarFactura(Parqueadero parqueadero, ParqueaderoNegocio parqueaderoN,Vehiculo vehiculo,VehiculoNegocio vehiculoN,Factura factura);
}
