package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuarioModelo extends Conector{
	
	public void insert(Usuario usuario){
		
		try {
			PreparedStatement pst = super.conexion.prepareStatement("INSERT INTO usuarios (nombre, apellido, edad) values(?,?,?)");
			pst.setString(1, usuario.getNombre());
			pst.setString(2, usuario.getApellido());
			pst.setInt(3, usuario.getEdad());
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Usuario> selectAll(){
		Statement st;
		ArrayList<Usuario> usuarios = new ArrayList();
		try {
			st = super.conexion.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM usuarios");

			while(rs.next()){
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setEdad(rs.getInt("edad"));
				usuarios.add(usuario);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
		
	}
	
	public void Update(Usuario usuario){
		
	}
	
	public void select(int id){
		
	}
	public void delete(int id){
		try {
			Statement st = super.conexion.createStatement();
			st.execute("DELETE FROM usuarios WHERE id = " + id);
			System.out.println("El usuario se ha eliminado correctamente");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}