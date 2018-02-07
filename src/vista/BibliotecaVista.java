package vista;

import java.util.Scanner;

public class BibliotecaVista {
	
	static final int MENU_USUARIO = 1;
	static final int MENU_LIBRO = 2;
	static final int MENU_PRESTAMO = 3;
	static final int SALIR = 4;
	
	
	public void menuBiblioteca(){
		Scanner scan = new Scanner(System.in);
		int opcion = 0;
		
		do{
			System.out.println("---- MENU BIBLIOTECA-----");
			System.out.println(MENU_USUARIO + ". Elegir el gestor de usuarios");
			System.out.println(MENU_LIBRO + ". Elegir el gestor de libros");
			System.out.println(MENU_PRESTAMO + ". Elegir el gestor de prestamos");
			System.out.println(SALIR + ". Salir de la aplicacion");
			
			opcion = Integer.parseInt(scan.nextLine());
			
			switch (opcion) {
			case MENU_USUARIO:
				UsuarioVista menu_usuario = new UsuarioVista();
				menu_usuario.menuDeUsuario();
				break;
			
			case MENU_LIBRO:
				LibroVista menu_libro = new LibroVista();
				menu_libro.menuLibros();
				break;
			
			case MENU_PRESTAMO:
				PrestamoVista menu_prestamo = new PrestamoVista();
				menu_prestamo.menuPrestamo();
				break;
			
			case SALIR:
				
				break;

			default:
				break;
			}
		}
		
		while(opcion != SALIR);

	}
	
	

}
