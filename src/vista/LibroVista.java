package vista;

import java.util.Scanner;

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
			System.out.println("");
		}
		
		while(opcion != SALIR);
	}

}
