package co.ceiba.interfaces;

import java.util.Date;

import co.ceiba.dominio.Factura;
import co.ceiba.dominio.Parqueadero;
import co.ceiba.dominio.Vehiculo;
import co.ceiba.negocio.ParqueaderoNegocio;
import co.ceiba.negocio.VehiculoNegocio;

public interface IFacturaNegocio {	
	public int minutosHoraEntradaAEntero(String horaEntrada);
	
	public int minutosHoraSalidaAEntero(String horaSalida);
	
	public int diferenciaDeHoras(Date fechaEntrada,Date fechaSalida);
	
	public void empezarFactura(Parqueadero parqueadero,ParqueaderoNegocio parqueaderoN,Vehiculo vehiculo,Factura factura);
	
	public void terminarFactura(Parqueadero parqueadero, ParqueaderoNegocio parqueaderoN,Vehiculo vehiculo,VehiculoNegocio vehiculoN,Factura factura);
}
