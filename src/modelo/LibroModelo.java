package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LibroModelo extends Conector{
	
	
	public ArrayList<Libro> selectAll(){
		Statement st;
		ArrayList<Libro> libros = new ArrayList();
		try {
			st = super.conexion.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM libros");
			
			while(rs.next()){
				Libro libro = new Libro();
				libro.setId(rs.getInt("id"));
				libro.setTitulo(rs.getString("titulo"));
				libro.setAutor(rs.getString("autor"));
				libros.add(libro);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return libros;
	}
	
	public Libro select(int id){
		Libro libro = new Libro();
		try {
			Statement st = super.conexion.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM libros WHERE id = " + id);
			
			while(rs.next()){
				libro.setId(rs.getInt("id"));
				libro.setTitulo(rs.getString("titulo"));
				libro.setAutor(rs.getString("autor"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return libro;
	}
	
	public Libro select(String titulo){
		try {
			Statement st = super.conexion.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM libros WHERE titulo = '" + titulo + "'");
			
			if(rs.next()){
				Libro libro = new Libro();
				libro.setId(rs.getInt("id"));
				libro.setTitulo(rs.getString("titulo"));
				libro.setAutor(rs.getString("autor"));
				return libro;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void update(Libro libro){
		try {
			PreparedStatement pst = super.conexion.prepareStatement("UPDATE libros SET titulo = ?, autor = ? WHERE id = " + libro.getId());
			
			pst.setString(1, libro.getTitulo());
			pst.setString(2, libro.getAutor());
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id){
		
		try {
			Statement st = super.conexion.createStatement();
			st.execute("DELETE FROM libros WHERE id = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	public void insert(Libro libro){
		
		try {
			PreparedStatement pst = super.conexion.prepareStatement("INSERT INTO libros (titulo,autor) values (?,?)");
			pst.setString(1, libro.getTitulo());
			pst.setString(2, libro.getAutor());
			
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
