import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import modelo.Usuario;
import modelo.UsuarioModelo;

public class Main {

	public static void main(String[] args) {
		
			Scanner scan = new Scanner(System.in);
			UsuarioModelo usuarioModelo = new UsuarioModelo();
			final int INSERTAR_USUARIO = 1;
			final int LISTAR_USUARIOS = 2;
			final int MODIFICAR_USUARIO = 3;
			final int ELIMINAR_USUARIO = 4;
			final int SALIR = 5;
			
			int opcion = 0;
			do {
				System.out.println("------MENU------");
				System.out.println(INSERTAR_USUARIO + ". Insertar un usuario ");
				System.out.println(LISTAR_USUARIOS + ". Leer lista de usuarios");
				System.out.println(MODIFICAR_USUARIO + ". Actualizar datos de un usuario");
				System.out.println(ELIMINAR_USUARIO + ". Eliminar un usuario");
				System.out.println(SALIR + ". Salir del programa");
				
				opcion = Integer.parseInt(scan.nextLine());
				
				switch (opcion) {
				case INSERTAR_USUARIO:
					System.out.println("Introduce un nombre:");
					String nombre = scan.nextLine();
					
					System.out.println("Introduce el apellido:");
					String apellido = scan.nextLine();
					
					System.out.println("Introduce la edad:");
					int edad = Integer.parseInt(scan.nextLine());
					
					Usuario usuario = new Usuario();
					
					usuario.setNombre(nombre);
					usuario.setApellido(apellido);
					usuario.setEdad(edad);
					
					usuarioModelo.insert(usuario);
					
					break;
				
				case LISTAR_USUARIOS:
					
					ArrayList<Usuario> usuarios = usuarioModelo.selectAll();
					Iterator<Usuario> i = usuarios.iterator();
					while(i.hasNext()){
						Usuario listar_usuario = i.next();
						System.out.println(listar_usuario.getId() +" " + listar_usuario.getNombre() + " " + listar_usuario.getApellido() + " " + listar_usuario.getEdad());
					}
					break;
				
				case MODIFICAR_USUARIO:
					System.out.println("Introduce el id del usuario a modificar:");
					int id = Integer.parseInt(scan.nextLine());
					
					System.out.println("Introduce el nombre nuevo:");
					String nombre_nuevo = scan.nextLine();
					
					System.out.println("Introduce el apellido nuevo:");
					String apellido_nuevo = scan.nextLine();
					
					System.out.println("Introduce la edad nueva:");
					int edad_nueva = Integer.parseInt(scan.nextLine());
					
					Usuario usuario_modificado = new Usuario();
					
					usuario_modificado.setId(id);
					usuario_modificado.setNombre(nombre_nuevo);
					usuario_modificado.setApellido(apellido_nuevo);
					usuario_modificado.setEdad(edad_nueva);
					
					usuarioModelo.Update(usuario_modificado);
					
					break;
				
				case ELIMINAR_USUARIO:
					
					System.out.println("Introduce el id del usuario que quieres eliminar:");
					int id_eliminar = Integer.parseInt(scan.nextLine());
					
					usuarioModelo.delete(id_eliminar);
					
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
