package utilidades;

import java.util.Comparator;

import modelo.Prestamo;

public class FechaComparator implements Comparator<Prestamo>{

	@Override
	public int compare(Prestamo p1, Prestamo p2) {
		return p2.getFechaPrestamo().compareTo(p1.getFechaPrestamo());
	}

}
