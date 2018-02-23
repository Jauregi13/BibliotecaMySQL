package Test;

import java.sql.SQLException;

public class MockUsuario {
	
	boolean fallo_conexion;
	
	
	public MockUsuario(boolean fallo_conexion){
		
		this.fallo_conexion = fallo_conexion;
		
	}
	
	public void compruebaConexion() throws SQLException{
		
		if(fallo_conexion){
			throw new SQLException("Fallo al conectar con BD");
		}
		
	}

}
