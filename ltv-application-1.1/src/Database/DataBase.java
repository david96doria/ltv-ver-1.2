package Database;

import java.sql.*;

import javax.xml.stream.util.StreamReaderDelegate;

public class DataBase {
	private Connection Conexion;
	private PreparedStatement stInsert;
	private PreparedStatement stCheck;
	private PreparedStatement stDelete;
	private PreparedStatement stUpdate;
	
////////////PREPARACION DE STATEMENTS \\\\\\\\ \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	public void print(String mss) { System.out.print(mss); }
	
	public void PrepareSolicitudes() throws Exception {
		Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/ltv-database","root","");
		stInsert = Conexion.prepareStatement("INSERT INTO solicitudestable VALUES (?,?,?,?,?)");
	    stCheck = Conexion.prepareStatement("SELECT * FROM solicitudestable WHERE cliente=?");
	    stDelete = Conexion.prepareStatement("DELETE FROM solicitudestable WHERE cliente=?");
	    stUpdate = Conexion.prepareStatement("UPDATE solicitudestable SET usuario=?, estatus=? WHERE cliente=?");
	}

	public void PrepareUsuarios() throws Exception {
		Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/ltv-database","root","");
		stInsert = Conexion.prepareStatement("INSERT INTO usuariostable VALUES (?,?,?,?,?,?)");
	    stCheck = Conexion.prepareStatement("SELECT * FROM usuariostable WHERE usuario=?");
	    stDelete = Conexion.prepareStatement("DELETE FROM usuariostable WHERE nombre=?");
	    stUpdate = Conexion.prepareStatement("UPDATE usuariostable SET usuario=?, clave=? WHERE nombre=?");

	}

	public void PrepareClientes() throws Exception {
		Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/ltv-database","root","");
		stInsert = Conexion.prepareStatement("INSERT INTO clientestable VALUES (?,?,?,?)");
	    stCheck = Conexion.prepareStatement("SELECT * FROM clientestable WHERE nombre=?");
	    stDelete = Conexion.prepareStatement("DELETE FROM clientestable WHERE nombre=?");
	    stUpdate = Conexion.prepareStatement("UPDATE clientestable SET telefono=?, email=? WHERE nombre=?");

	}
	
	public void CloseDataBase() {
	      try {
	        Conexion.close();
	      } catch (SQLException ex) {
	          
	         System.out.println("Error al cerrar la Conexion a la BD");
	         System.out.println(ex.getMessage());
	          
	      }
	    }
	
	//////////// SOLICITUDES \\\\\\\\ \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	public SolicitudesTable CheckSolicitudes(String Cliente) {
		SolicitudesTable Solicitud = null;
		try {
			
			stCheck.setString(1,Cliente);
			ResultSet miResultset = stCheck.executeQuery();
			
			while(miResultset.next()) {
				if(Cliente.equals(miResultset.getString("cliente"))) {
					Solicitud = new SolicitudesTable(miResultset.getString("fecha"), miResultset.getString("cliente"),
							miResultset.getString("solicitud"), miResultset.getString("estatus"), miResultset.getString("usuario"));
					break;}
			}
			miResultset.close();
			return Solicitud;
			
		}catch(SQLException ex) {System.out.println("Error al consultar solicitud");
	         System.out.println(ex.getMessage()); return null;}
		
	}
	
	public void UpdateSolicitudes(String Cliente) {
		try {
			SolicitudesTable Solicitud = new SolicitudesTable();
			stUpdate.setString(3, Cliente);
			stUpdate.setString(1, Solicitud.getUSUARIO());
			stUpdate.setString(2, Solicitud.getESTATUS());
			stUpdate.executeQuery();
		}catch(SQLException ex) {System.out.println("Error al actualizar solicitud");
        System.out.println(ex.getMessage());}
		
	}
	
	public void DeleteSolicitudes(String Cliente) {
		try {
			stDelete.setString(1, Cliente);
			stDelete.executeQuery();
			
		}catch(SQLException ex) {System.out.println("Error al eliminar solicitud");
        System.out.println(ex.getMessage());}
	}
	
	public void InsertSolicitudes() {
		try {
			SolicitudesTable Solicitud = new SolicitudesTable();
			stInsert.setString(1, Solicitud.getFECHA());
			stInsert.setString(2, Solicitud.getCLIENTE());
			stInsert.setString(3, Solicitud.getSOLICITUD());
			stInsert.setString(4, Solicitud.getESTATUS());
			stInsert.setString(5, Solicitud.getUSUARIO());
			stInsert.executeQuery();
		}catch(SQLException ex) {System.out.println("Error al ingresar solicitud");
        System.out.println(ex.getMessage());}
	}
	
	
	//////////// USUARIOS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public UsuariosTable CheckUsuarios(String Nombre) {
		UsuariosTable Usuario = null;
	try {
		
		stCheck.setString(1, Nombre);
		ResultSet miResultset=stCheck.executeQuery();
		miResultset.next();
		print(miResultset.getString("clave"));
		
		while(miResultset.next()) {
			if(Nombre.equals(miResultset.getString("nombre"))) {
				Usuario = new UsuariosTable(miResultset.getString("nombre"), miResultset.getString("email"), 
						miResultset.getString("cargo"), miResultset.getString("usuario"), miResultset.getString("clave"), 
						miResultset.getString("disponible"));
				break;}
		}
		miResultset.close();
		return Usuario;
	}catch(SQLException ex) {System.out.println("Error al consultar usuario");
    System.out.println(ex.getMessage()); return null;}
	}
	
	public UsuariosTable findUsuario(String Usuario) {
		try {
			stCheck.setString(1, Usuario);
			ResultSet miResultset=stCheck.executeQuery();
			miResultset.next();
			return new UsuariosTable(miResultset.getString("nombre"), miResultset.getString("email"), 
					miResultset.getString("cargo"), miResultset.getString("usuario"), miResultset.getString("clave"), 
					miResultset.getString("disponible"));
		}catch(Exception e) {
			return null;
		}
	}
	
	public void UpdateUsuarios(String Nombre) {
		try {
			UsuariosTable Usuario = new UsuariosTable();
			stUpdate.setString(3, Nombre);
			stUpdate.setString(1, Usuario.getUSUARIO());
			stUpdate.setString(2, Usuario.getCLAVE());
			stUpdate.executeQuery();
		}catch(SQLException ex) {System.out.println("Error al actualizar usuario");
        System.out.println(ex.getMessage());}
		
	}
	
	public void DeleteUsuarios(String Nombre) {
		try {
			stDelete.setString(1, Nombre);
			stDelete.executeQuery();
			
		}catch(SQLException ex) {System.out.println("Error al eliminar usuario");
        System.out.println(ex.getMessage());}
		
	}
	
	public void InsertUsuarios() {
		try{
		UsuariosTable Usuario = new UsuariosTable();
		stInsert.setString(1, Usuario.getNOMBRE());
		stInsert.setString(2, Usuario.getEMAIL());
		stInsert.setString(3, Usuario.getCARGO());
		stInsert.setString(4, Usuario.getUSUARIO());
		stInsert.setString(5, Usuario.getCLAVE());
		stInsert.executeQuery();
	}catch(SQLException ex) {System.out.println("Error al ingresar usuario");
    System.out.println(ex.getMessage());}
	}
	
	
	//////////// CLIENTES \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public ClientesTable CheckClientes(String Nombre) {
		ClientesTable Cliente = null;
		try {
			
			stCheck.setString(1, Nombre);
			ResultSet miResultset=stCheck.executeQuery();
			
			while(miResultset.next()) {
				if(Nombre.equals(miResultset.getString("nombre"))) {
					Cliente = new ClientesTable(miResultset.getString("nombre"), miResultset.getString("email"), 
							miResultset.getString("telefono"), miResultset.getString("ubicacion"));
					break;}
			}
			miResultset.close();
			return Cliente;
		}catch(SQLException ex) {System.out.println("Error al consultar cliente");
	    System.out.println(ex.getMessage()); return null;}
			
	}
	
	public void UpdateClientes(String Nombre) {
		try {
			ClientesTable Cliente = new ClientesTable();
			stUpdate.setString(3, Nombre);
			stUpdate.setString(1, Cliente.getTELEFONO());
			stUpdate.setString(2, Cliente.getEMAIL());
			stUpdate.executeQuery();
		}catch(SQLException ex) {System.out.println("Error al actualizar cliente");
        System.out.println(ex.getMessage());}
	}
	
	public void DeleteClientes(String Nombre) {
		try {
			stDelete.setString(1, Nombre);
			stDelete.executeQuery();
			
		}catch(SQLException ex) {System.out.println("Error al eliminar cliente");
        System.out.println(ex.getMessage());}
	}
	
	public void InsertClientes() {
		try{
			ClientesTable Cliente = new ClientesTable();
			stInsert.setString(1, Cliente.getNOMBRE());
			stInsert.setString(2, Cliente.getEMAIL());
			stInsert.setString(3, Cliente.getTELEFONO());
			stInsert.setString(4, Cliente.getUBICACION());
			stInsert.executeQuery();
		}catch(SQLException ex) {System.out.println("Error al ingresar cliente");
	    System.out.println(ex.getMessage());}
	}
	
}//end class
