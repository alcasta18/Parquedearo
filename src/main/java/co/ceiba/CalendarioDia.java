package co.ceiba;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component("CalendarioDia")
public class CalendarioDia {
	public Date getFecha(){
		return java.util.Calendar.getInstance().getTime();
	}
}
