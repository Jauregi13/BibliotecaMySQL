package vista;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import modelo.Libro;
import modelo.LibroModelo;
import modelo.Prestamo;
import modelo.PrestamoModelo;
import modelo.Usuario;
import modelo.UsuarioModelo;
import utilidades.UsuarioComparator;

public class PrestamoVista {

	static final int LISTAR_PRESTAMOS = 1;
	static final int TOMAR_PRESTADO = 2;
	static final int ENTREGAR_LIBRO = 3;
	static final int SALIR = 4;

	public void menuPrestamo() {
		PrestamoModelo prestamoModelo = new PrestamoModelo();
		Scanner scan = new Scanner(System.in);
		int opcion = 0;

		do {
			System.out.println("------ MENU PRESTAMOS -----");
			System.out.println(LISTAR_PRESTAMOS + ". Listar todos los prestamos");
			System.out.println(TOMAR_PRESTADO + ". Tomar prestado un libro");
			System.out.println(ENTREGAR_LIBRO + ". Entregar libro");
			System.out.println(SALIR + " Salir del menu de prestamos");

			opcion = Integer.parseInt(scan.nextLine());

			switch (opcion) {

			case LISTAR_PRESTAMOS:
				ArrayList<Prestamo> prestamos = new ArrayList();
				prestamos = prestamoModelo.selectAll();
				
				listarPrestamos(prestamos);

				ordenarListado(scan, prestamos);
				break;

			case TOMAR_PRESTADO:
				realizarPrestamo(scan, prestamoModelo);
				break;

			case ENTREGAR_LIBRO:
				entregarLibro(scan, prestamoModelo);
				break;

			case SALIR:

				break;

			default:
				break;
			}
		}

		while (opcion != SALIR);

	}

	private void ordenarListado(Scanner scan, ArrayList<Prestamo> prestamos) {

		PrestamoModelo prestamoModelo = new PrestamoModelo();
		final int TITULO = 1;
		final int NOMBRE = 2;
		final int FECHA_INICIO = 3;
		final int SALIR = 0;
		UsuarioComparator comparador = new UsuarioComparator();
		int opcion = 0;
		do {
			System.out.println("\nOrdenar listado de prestamos:");
			System.out.println(TITULO + ". Por titulo");
			System.out.println(NOMBRE + ". Por nombre");
			System.out.println(FECHA_INICIO + ". Por fecha de inicio");
			System.out.println(SALIR + ". Salir");

			switch (opcion) {
			case TITULO:
				prestamos.sort(comparador);

				listarPrestamos(prestamos);
				break;

			case NOMBRE:
				prestamos.sort(comparador);

				listarPrestamos(prestamos);
				break;

			case FECHA_INICIO:

				break;

			case SALIR:

				break;

			default:
				break;
			}

			opcion = Integer.parseInt(scan.nextLine());
		} while (opcion != SALIR);
	}

	private ArrayList<Prestamo> listarPrestamos(ArrayList<Prestamo> prestamos) {

		Iterator<Prestamo> i = prestamos.iterator();
		System.out.println("Titulo \t\t usuario \t dni \t fecha-Inicio \t fecha-final");
		while (i.hasNext()) {
			Prestamo prestamo = i.next();
			System.out.println(prestamo.getLibro().getTitulo() + "\t " + prestamo.getUsuario().getNombre() + "\t "
					+ prestamo.getUsuario().getDni() + "\t " + prestamo.getFechaPrestamo() + "\t"
					+ prestamo.getFechaLimite());
		}

		return prestamos;

	}

	private void entregarLibro(Scanner scan, PrestamoModelo prestamoModelo) {
		System.out.println("Introduce el DNI:");
		String dni = scan.nextLine();

		UsuarioModelo usuarioModelo = new UsuarioModelo();
		Usuario usuario = usuarioModelo.selectPorDni(dni);

		System.out.println("Introduce el titulo del libro a entregar:");
		String titulo = scan.nextLine();

		LibroModelo libroModelo = new LibroModelo();
		Libro libro = libroModelo.select(titulo);

		Prestamo prestamo = prestamoModelo.selectNoEntregado(libro, usuario);

		prestamoModelo.UpdateEntregar(prestamo);

	}

	private void realizarPrestamo(Scanner scan, PrestamoModelo prestamoModelo) {

		System.out.println("Introduce el nombre del titulo");
		String titulo = scan.nextLine();

		LibroModelo libroModelo = new LibroModelo();

		Libro libro = libroModelo.select(titulo);
		// El libro existe
		if (libro != null) {
			Usuario usuario = null;
			do {
				System.out.println("Introduce el DNI:");
				String dni = scan.nextLine();
				UsuarioModelo usuarioModelo = new UsuarioModelo();
				usuario = usuarioModelo.selectPorDni(dni);
				if (usuario == null) {
					System.out.println("No hay ningun usuario con ese dni");
				}
			} while (usuario == null);
			Prestamo comprobar_prestamo = prestamoModelo.SelectPorIdLibro(libro);

			if (comprobar_prestamo != null) {
				System.out.println("El libro está cogido prestado");
			} else {
				// crear prestamo
				Prestamo prestamo = new Prestamo();
				// rellenar prestamo
				prestamo.setLibro(libro);
				;
				prestamo.setUsuario(usuario);
				;

				Date fecha_actual = new Date();
				Calendar calendario = Calendar.getInstance();
				calendario.setTime(fecha_actual);
				prestamo.setFechaPrestamo(calendario.getTime());
				calendario.add(Calendar.DATE, 21);
				prestamo.setFechaLimite(calendario.getTime());
				prestamo.setEntregado(false);

				prestamoModelo.insertar(prestamo);
			}

		}
		// el libro no existe
		else {

		}

	}

}
