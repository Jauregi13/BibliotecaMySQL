package utilidades;

import java.util.Comparator;

import modelo.Prestamo;

public class UsuarioComparator implements Comparator<Prestamo> {


	public int compare(Prestamo prestamo1, Prestamo prestamo2) {

		return prestamo1.getUsuario().getNombre().compareTo(prestamo2.getUsuario().getNombre());
	}
	
}
