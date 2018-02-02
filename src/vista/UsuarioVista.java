package vista;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import modelo.Usuario;
import modelo.UsuarioModelo;

public class UsuarioVista {
	static final int LISTAR = 1;
	static final int INSERTAR = 2;
	static final int SALIR = 3;

	public void menuDeUsuario(){
		UsuarioModelo usuarioModelo = new UsuarioModelo();
		SimpleDateFormat formato_fecha = new SimpleDateFormat("yyyy-MM/-dd");
		Scanner scan = new Scanner(System.in);
		int opcion = 0;
		do {
			
			System.out.println("-------MENU USUARIO------");
			System.out.println(LISTAR + ". Listar todos los usuarios");
			System.out.println(INSERTAR + ". Insertar un usuario");
			System.out.println(SALIR + ". Salir del programa");
			
			opcion = Integer.parseInt(scan.nextLine());
			
			switch (opcion) {
			case LISTAR:
				ArrayList<Usuario> usuarios = usuarioModelo.selectAll();
				mostrarUsuarios(usuarios);
				break;
			
			case INSERTAR:
				System.out.println("Introduce el nombre:");
				String nombre = scan.nextLine();
				
				System.out.println("Introduce el apellido:");
				String apellido = scan.nextLine();
				
				System.out.println("Introduce la edad:");
				int edad = Integer.parseInt(scan.nextLine());
				
				System.out.println("Introduce el dni:");
				String dni = scan.nextLine();
				
				System.out.println("Introduce la fecha de nacimiento");
				try {
					Date fecha = (Date)formato_fecha.parse(scan.nextLine());
					Usuario usuario = new Usuario();
					usuario.setNombre(nombre);
					usuario.setApellido(apellido);
					usuario.setEdad(edad);
					usuario.setDni(dni);
					usuario.setFecha_nacimiento(fecha);
					usuarioModelo.insert(usuario);
					break;
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				
				
			
			case SALIR:
				
				break;

			default:
				break;
			}
			
		}
		
		while(opcion != SALIR);
		
		
	}

	private void mostrarUsuarios(ArrayList<Usuario> usuarios) {
		
		Iterator<Usuario> i = usuarios.iterator();
		while(i.hasNext()){
			Usuario usuario = i.next();
			System.out.println(usuario.getId() +" " + usuario.getNombre() + " " + usuario.getApellido() + " " + usuario.getEdad() + " " + usuario.getDni() + " " + usuario.getFecha_nacimiento());
		}
		
	}

}
