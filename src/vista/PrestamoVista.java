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

public class PrestamoVista {
	
	static final int LISTAR_PRESTAMOS = 1;
	static final int TOMAR_PRESTADO = 2;
	static final int ENTREGAR_LIBRO = 3;
	static final int SALIR = 4;
	
	public void menuPrestamo(){
		PrestamoModelo prestamoModelo = new PrestamoModelo();
		Scanner scan = new Scanner(System.in);
		int opcion = 0;
		
		
		do{
			System.out.println("------ MENU PRESTAMOS -----");
			System.out.println(LISTAR_PRESTAMOS + ". Listar todos los prestamos");
			System.out.println(TOMAR_PRESTADO + ". Tomar prestado un libro");
			System.out.println(ENTREGAR_LIBRO + ". Entregar libro" );
			System.out.println(SALIR + " Salir del menu de prestamos");
			
			opcion = Integer.parseInt(scan.nextLine());
			
			switch (opcion) {
			
			case LISTAR_PRESTAMOS:
				listarPrestamos(scan, prestamoModelo);
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
		
		while(opcion != SALIR);
		
		
	}

	private void listarPrestamos(Scanner scan, PrestamoModelo prestamoModelo) {
		
		ArrayList<Prestamo> prestamos = new ArrayList();
		prestamos = prestamoModelo.selectAll();
		
		Iterator<Prestamo> i = prestamos.iterator();
		
		while(i.hasNext()){
			Prestamo prestamo = i.next();
			System.out.println(prestamo.getUsuario().getDni() + " : " + prestamo.getUsuario().getNombre());
		}
		
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
		if(libro != null){
			Usuario usuario = null;
			do{
				System.out.println("Introduce el DNI:");
				String dni = scan.nextLine();
				UsuarioModelo usuarioModelo = new UsuarioModelo();
				usuario = usuarioModelo.selectPorDni(dni);
				if(usuario == null){
					System.out.println("No hay ningun usuario con ese dni");
				}
			}	
			while(usuario == null);
			Prestamo comprobar_prestamo = prestamoModelo.SelectPorIdLibro(libro);
			
			if(comprobar_prestamo != null){
				System.out.println("El libro está cogido prestado");
			}
			else{
				// crear prestamo
				Prestamo prestamo = new Prestamo();
				// rellenar prestamo
				prestamo.setLibro(libro);;
				prestamo.setUsuario(usuario);;
				
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
