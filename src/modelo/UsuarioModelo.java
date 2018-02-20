package modelo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuarioModelo extends Conector{
	
	public void insert(Usuario usuario){
		
		try {
			PreparedStatement pst = super.conexion.prepareStatement("INSERT INTO usuarios (nombre, apellido, edad, dni, fecha_nacimiento) values(?,?,?,?,?)");
			pst.setString(1, usuario.getNombre());
			pst.setString(2, usuario.getApellido());
			pst.setInt(3, usuario.getEdad());
			pst.setString(4, usuario.getDni());
			pst.setDate(5,new java.sql.Date(usuario.getFecha_nacimiento().getTime()));
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
				usuario.setDni(rs.getString("dni"));
				usuario.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
				usuarios.add(usuario);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
		
	}
	
	public void Update(Usuario usuario){
		try {
			PreparedStatement pst = super.conexion.prepareStatement("UPDATE usuarios SET nombre = ?, apellido = ?, edad = ?, dni = ?, fecha_nacimiento = ? where id = "+usuario.getId());
			
			pst.setString(1, usuario.getNombre());
			pst.setString(2, usuario.getApellido());
			pst.setInt(3, usuario.getEdad());
			pst.setString(4, usuario.getDni());
			pst.setDate(5, (Date)usuario.getFecha_nacimiento());
			
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Usuario> selectNombre(String nombre){
		ArrayList<Usuario> usuarios = new ArrayList();
		try {
			Statement st = super.conexion.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM WHERE nombre = " + nombre);
			
			while(rs.next()){
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setEdad(rs.getInt("edad"));
				usuario.setDni(rs.getString("dni"));
				usuario.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
				usuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
		
	}
	
	public Usuario selectPorId(int id){
		
		try {
			Statement st = super.conexion.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM usuarios WHERE id = " + id);
			
			if(rs.next()){
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setDni(rs.getString("dni"));
				usuario.setEdad(rs.getInt("edad"));
				usuario.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
				return usuario;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
		
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
	
	public int selectMayorEdad(){
		try {
			Statement st = super.conexion.createStatement();
			st.executeQuery("SELECT * FROM usuarios WHERE edad >= 18");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<Usuario> selectCadenaApellido(String cadena){
		ArrayList<Usuario> usuarios = new ArrayList();
		try {
			Statement st = super.conexion.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM usuarios WHERE apellido like '%" + cadena + "%'");
			while(rs.next()){
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setEdad(rs.getInt("edad"));
				usuario.setDni(rs.getString("dni"));
				usuario.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
				usuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuarios;
	}

	public Usuario selectPorDni(String dni) {
		try {
			Statement st = super.conexion.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM usuarios WHERE dni ='" + dni + "'");
			
			if(rs.next()){
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setDni(rs.getString("dni"));
				usuario.setEdad(rs.getInt("edad"));
				usuario.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
				
				return usuario;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
