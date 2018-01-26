import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM usuarios");
			
			while(rs.next()){
				System.out.println(rs.getInt("id") + " "+ rs.getString("nombre") +" " + rs.getString("apellido") + " " + rs.getInt("edad"));
			}			
			Scanner scan = new Scanner(System.in);
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
					
					insertarUsuario(nombre, apellido, edad);
					break;
				
				case LISTAR_USUARIOS:
					
					break;
				
				case MODIFICAR_USUARIO:
					
					break;
				
				case ELIMINAR_USUARIO:
					
					break;
				
				case SALIR:
					
					break;

				default:
					break;
				}
			}
			
			while(opcion != SALIR);
			
			System.out.println("Introduce un nombre:");
			String nombre = scan.nextLine();
			
			System.out.println("introduce un apellido:");
			String apellido = scan.nextLine();
			
			System.out.println("Introduce la edad:");
			int edad = Integer.parseInt(scan.nextLine());
					
			PreparedStatement pst = con.prepareStatement("INSERT INTO usuarios (nombre, apellido, edad) VALUES (?,?,?)");
			
			pst.setString(1, nombre);
			pst.setString(2, apellido);
			pst.setInt(3, edad);
			
			pst.execute();
			
			//st.execute("DELETE FROM usuarios WHERE nombre ='Mauricio'");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void insertarUsuario(String nombre, String apellido, int edad) {
		// TODO Auto-generated method stub
		
	}

}
