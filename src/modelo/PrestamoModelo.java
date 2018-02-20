package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PrestamoModelo extends Conector {
	
	public void insertar(Prestamo prestamo){
		try {
			PreparedStatement pst = super.conexion.prepareStatement("INSERT INTO prestamos (id_libro, id_usuario, fecha_prestamo, fecha_limite, entregado) values (?,?,?,?,?)");
			
			pst.setInt(1, prestamo.getLibro().getId());
			pst.setInt(2, prestamo.getUsuario().getId());
			pst.setDate(3, new java.sql.Date(prestamo.getFechaPrestamo().getTime()));
			pst.setDate(4, new java.sql.Date(prestamo.getFechaLimite().getTime()));
			pst.setBoolean(5, prestamo.isEntregado());
			
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Prestamo> selectAll(){
		ArrayList<Prestamo> prestamos = new ArrayList();
		UsuarioModelo usuarioModelo = new UsuarioModelo();
		LibroModelo libroModelo = new LibroModelo();
		try {
			
			Statement st = super.conexion.createStatement();
			
			ResultSet rs = st.executeQuery("Select * from prestamos");
			
			while(rs.next()){
				Prestamo prestamo = new Prestamo();
				prestamo.setId(rs.getInt("id"));
				prestamo.setLibro(libroModelo.select(rs.getInt("id_libro")));
				prestamo.setUsuario(usuarioModelo.selectPorId(rs.getInt("id_usuario")));
				prestamo.setFechaPrestamo(rs.getDate("fecha_prestamo"));
				prestamo.setFechaLimite(rs.getDate("fecha_limite"));
				prestamo.setEntregado(rs.getBoolean("entregado"));
				
				prestamos.add(prestamo);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prestamos;

	}
	
	public Prestamo selectNoEntregado(int id_libro, int id_usuario){
			
		try {
			Statement st = super.conexion.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM prestamos WHERE id_libro = '"+id_libro+"'");
			
			if(rs.next()){
				Prestamo prestamo = new Prestamo();
				
				
				return prestamo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public Prestamo selectNoEntregado(Libro libro, Usuario usuario){
		UsuarioModelo usuarioModelo = new UsuarioModelo();
		LibroModelo libroModelo = new LibroModelo();
		try {
			PreparedStatement ps = super.conexion.prepareStatement("SELECT * FROM prestamos WHERE id_libro = ?  AND id_usuario = ? AND entregado= ?");
			
			ps.setInt(1, libro.getId());
			ps.setInt(2, usuario.getId());
			ps.setBoolean(3, false);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				Prestamo prestamo = new Prestamo();
				prestamo.setId(rs.getInt("id"));
				prestamo.setLibro(libroModelo.select(rs.getInt("id_libro")));
				prestamo.setUsuario(usuarioModelo.selectPorId(rs.getInt("id_usuario")));
				prestamo.setFechaPrestamo(rs.getDate("fecha_prestamo"));
				prestamo.setFechaLimite(rs.getDate("fecha_limite"));
				prestamo.setEntregado(rs.getBoolean("entregado"));
				return prestamo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}


	public void UpdateEntregar(Prestamo prestamo) {
		try {
			PreparedStatement ps = super.conexion.prepareStatement("UPDATE prestamos SET fecha_prestamo = ?, fecha_limite = ?, entregado = ? WHERE id_usuario = ? AND id_libro = ? AND entregado = ?");
			
			ps.setDate(1, new java.sql.Date(prestamo.getFechaPrestamo().getTime()));
			ps.setDate(2, new java.sql.Date(prestamo.getFechaLimite().getTime()));
			ps.setBoolean(3, true);
			ps.setInt(4, prestamo.getUsuario().getId());
			ps.setInt(5, prestamo.getLibro().getId());
			ps.setBoolean(6, false);
			
			ps.execute();
			
			System.out.println("El prestamo se ha terminado");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public Prestamo SelectPorIdLibro(Libro libro){
		LibroModelo libroModelo = new LibroModelo();
		try {
			PreparedStatement ps = super.conexion.prepareStatement("SELECT id_libro, entregado FROM prestamos WHERE  id_libro = ? AND entregado = ?");
			
			ps.setInt(1, libro.getId());
			ps.setBoolean(2, false);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				Prestamo prestamo = new Prestamo();
				prestamo.setLibro(libroModelo.select(rs.getInt("id_libro")));
				prestamo.setEntregado(rs.getBoolean("entregado"));
				return prestamo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
