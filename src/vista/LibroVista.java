package vista;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import modelo.Libro;
import modelo.LibroModelo;

public class LibroVista {
	
	static final int LISTAR = 1;
	static final int INSERTAR = 2;
	static final int SALIR = 3;
	
	public void menuLibros(){
		LibroModelo libroModelo = new LibroModelo();
		Scanner scan = new Scanner(System.in);
		
		int opcion = 0;
		
		do{
			System.out.println("---- MENU LIBRO -----");
			System.out.println(LISTAR + ". Listar todos los libros");
			System.out.println(INSERTAR + ". Insertar un libro");
			System.out.println(SALIR + ". Salir del menu de libros");
			
			opcion = Integer.parseInt(scan.nextLine());
			
			switch (opcion) {
			case LISTAR:
				ArrayList<Libro> libros = libroModelo.selectAll();
				
				mostrarLibros(libros);
				break;
			
			case INSERTAR:
				System.out.println("Introduce el titulo del libro:");
				String titulo = scan.nextLine();
				
				System.out.println("Introduce el autor del libro:");
				String autor = scan.nextLine();
				
				Libro libro = new Libro();
				libro.setTitulo(titulo);
				libro.setAutor(autor);
				
				libroModelo.insert(libro);
				break;
			
			case SALIR:
				
				break;

			default:
				break;
			}
		}
		
		while(opcion != SALIR);
	}

	private void mostrarLibros(ArrayList<Libro> libros) {
		
		Iterator<Libro> i = libros.iterator();
		
		while(i.hasNext()){
			Libro libro = i.next();
			System.out.println(libro.getId() + " " + libro.getTitulo() + " " + libro.getAutor());
		}
		
	}

}
