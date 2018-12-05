package Database;

import java.util.*;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DataBase {

	private static final String path = "jdbc:mysql://localhost:3306/ltv-database";
	private static final String user = "root";
	private static final String pass = "";
	private Connection Conexion;
	private PreparedStatement stInsert;
	private PreparedStatement stCheck;
	private PreparedStatement stDelete;
	private PreparedStatement stUpdate;

	public void PrintErrMss(String mss) { JOptionPane.showMessageDialog(new JPanel(), mss, "Error", JOptionPane.ERROR_MESSAGE); }
	public void PrintInfMss(String mss) { JOptionPane.showMessageDialog(new JPanel(), mss, "OK", JOptionPane.INFORMATION_MESSAGE); }

	public void PrepareSolicitudes() {
		try {
			Conexion = DriverManager.getConnection(path, user, pass);
			stInsert = Conexion.prepareStatement("INSERT INTO solicitudestable VALUES (?,?,?,?,?,?,?,?)");
			stCheck  = Conexion.prepareStatement("SELECT * FROM solicitudestable WHERE agente=?");
			stDelete = Conexion.prepareStatement("DELETE FROM solicitudestable WHERE status=?");
			stUpdate = Conexion.prepareStatement("UPDATE solicitudestable SET agente=?, status=? WHERE fecha=? && nombre=? && destino=?");
		}catch(Exception e) {
			PrintErrMss("Error al conectar con la base de datos.");
			System.out.println(e.getMessage());
		}
		// still pending to be well defined
	}

	public void PrepareUsuarios() {
		try {
			Conexion = DriverManager.getConnection(path, user, pass);
			stInsert = Conexion.prepareStatement("INSERT INTO usuariostable VALUES (?,?,?,?,?,?,?)");
			stCheck  = Conexion.prepareStatement("SELECT * FROM usuariostable WHERE usuario=?");
			stDelete = Conexion.prepareStatement("DELETE FROM usuariostable WHERE usuario=?");
			stUpdate = Conexion.prepareStatement("UPDATE usuariostable SET inicio=? WHERE usuario=?");
		}catch(Exception e) {
			PrintErrMss("Error al conectar con la base de datos.");
			System.out.println(e.getMessage());
		}
	}

	public void PrepareClientes() {
		try {
			Conexion = DriverManager.getConnection(path, user, pass);
			stInsert = Conexion.prepareStatement("INSERT INTO clientestable VALUES (?,?,?,?)");
			stCheck = Conexion.prepareStatement("SELECT * FROM clientestable");
			stDelete = Conexion.prepareStatement("DELETE FROM clientestable WHERE nombre=?");
			stUpdate = Conexion.prepareStatement("UPDATE clientestable SET email=?, telefono=? WHERE nombre=?");
		}catch(Exception e) {
			PrintErrMss("Error al conectar con la base de datos.");
			System.out.println(e.getMessage());
		}
	}

	public void CloseDataBase() {
		try {
			Conexion.close();
		}catch (Exception e) {
			PrintErrMss("Error al cerrar la conexción con la base de datos.");
			System.out.println(e.getMessage());
		}
	}

	//////////// SOLICITUDES \\\\\\\\ \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
//	public SolicitudesTable CheckSolicitudes(String Cliente) {
//		SolicitudesTable Solicitud = null;
//		try {
//			stCheck.setString(1,Cliente);
//			ResultSet miResultset = stCheck.executeQuery();
//			
//			while(miResultset.next()) {
//				if(Cliente.equals(miResultset.getString("cliente"))) {
//					Solicitud = new SolicitudesTable(miResultset.getString("fecha"), miResultset.getString("cliente"),
//							miResultset.getString("solicitud"), miResultset.getString("estatus"), miResultset.getString("usuario"));
//					break;}
//			}
//			miResultset.close();
//			return Solicitud;
//			
//		}catch(SQLException ex) {System.out.println("Error al consultar solicitud");
//	         System.out.println(ex.getMessage()); return null;}
//	}

//	public void UpdateSolicitudes(String Cliente) {
//		try {
//			SolicitudesTable Solicitud = new SolicitudesTable();
//			stUpdate.setString(3, Cliente);
//			stUpdate.setString(1, Solicitud.getUSUARIO());
//			stUpdate.setString(2, Solicitud.getESTATUS());
//			stUpdate.executeQuery();
//		}catch(SQLException ex) {System.out.println("Error al actualizar solicitud");
//        System.out.println(ex.getMessage());}
//	}

	public void UpdateSolicitudStatus(SolicitudesTable sol) {
		try {
			stUpdate.setString(1, sol.getAGENTE());
			stUpdate.setString(2, "A");
			stUpdate.setString(3, sol.getFECHA());
			stUpdate.setString(4, sol.getNOMBRE());
			stUpdate.setString(5, sol.getDESTINO());
			stUpdate.executeUpdate();
		}catch(Exception e) {
			PrintErrMss("Error al actualizar solicitud.");
			System.out.println(e.getMessage());
		}
	}

	public ResultSet CheckAllSolicitudes(String status) {
		try {
			stCheck = Conexion.prepareStatement("SELECT * FROM solicitudestable WHERE status=?");
			stCheck.setString(1, status);
			ResultSet miResultset = stCheck.executeQuery();
			return miResultset;
		}catch(SQLException ex) {System.out.println("Error al consultar solicitud");
	         System.out.println(ex.getMessage());
	         ex.printStackTrace(); return null;}
		
	}
	
	public void DeleteSolicitudes(String Cliente) {
		try {
			stDelete.setString(1, Cliente);
			stDelete.executeQuery();
			
		}catch(SQLException ex) {System.out.println("Error al eliminar solicitud");
        System.out.println(ex.getMessage());}
	}

	public ResultSet FindSolicitud(String Agente) {
		try {
			stCheck.setString(1, Agente);
			return stCheck.executeQuery();
		}catch(Exception e) {
			PrintErrMss("Error al buscar una solicitud.");
			System.out.println(e.getMessage());
			return null;
		}
	}

	public ResultSet FillSolicitud(String Fecha, String Nombre, String Destino) {
		try {
			stCheck = Conexion.prepareStatement("SELECT * FROM solicitudestable WHERE fecha=? && nombre=? && destino=?");
			stCheck.setString(1, Fecha);
			stCheck.setString(2, Nombre);
			stCheck.setString(3, Destino);
			ResultSet ret = stCheck.executeQuery();
			ret.next();
			return ret;
		}catch(Exception e) {
			PrintErrMss("Error al buscar una solicitud.");
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void InsertSolicitud(SolicitudesTable Solicitud) {
		try {
			stInsert.setString(1, Solicitud.getFECHA());
			stInsert.setString(2, Solicitud.getNOMBRE());
			stInsert.setString(3, Solicitud.getCORREO());
			stInsert.setString(4, Solicitud.getTELEFONO());
			stInsert.setString(5, Solicitud.getDESTINO());
			stInsert.setString(6, Solicitud.getCOMENTARIOS());
			stInsert.setString(7, Solicitud.getAGENTE());
			stInsert.setString(8, String.valueOf(Solicitud.getSTATUS()));
			stInsert.executeUpdate(); //.executeQuery();
		}catch(SQLException e) {
			PrintErrMss("Error al ingresar solicitud.");
			System.out.println(e.getMessage());
		}
	}

	//////////// USUARIOS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public UsuariosTable findUsuario(String Usuario) {
		try {
			stCheck.setString(1, Usuario);
			ResultSet res=stCheck.executeQuery();
			res.next();
			return new UsuariosTable(res.getString("nombre"), 
									 res.getString("email"), 
									 res.getString("cargo"), 
									 res.getString("usuario"), 
									 res.getString("clave"), 
									 res.getString("disponible")
									);
		}catch(Exception e) {
			PrintErrMss("Error al buscar un usuario.");
			System.out.println(e.getMessage());
			return null;
		}
	}

	public String findSesionIniciada() {
		try {
			stCheck  = Conexion.prepareStatement("SELECT * FROM usuariostable WHERE inicio=?");
			stCheck.setInt(1, 1);
			ResultSet res = stCheck.executeQuery();
			res.next();
			return res.getString("USUARIO");
		}catch(Exception e) {
			PrintErrMss("Error al buscar sesion iniciada.");
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void UpdateUsuarioINICIO(String Usuario, int Inicio) {
		try {
			stUpdate.setInt(1, Inicio);
			stUpdate.setString(2, Usuario);
			stUpdate.executeUpdate();
		}catch(SQLException e) {
			PrintErrMss("Error al actualizar usuario");
			System.out.println(e.getMessage());
		}
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
	
	public ResultSet CheckAvailableUsuarios(boolean Disponible) {
		try {
			stCheck = Conexion.prepareStatement("SELECT * FROM usuariostable WHERE disponible=? && CARGO=?");
			stCheck.setBoolean(1, Disponible);
			stCheck.setString(2, "R");
			ResultSet miResultset = stCheck.executeQuery();
			return miResultset;
		}catch(SQLException e) {
			PrintErrMss("Error al consultar usuarios disponibles.");
			System.out.println(e.getMessage()); 
			return null;
		}
	}
	//////////// CLIENTES \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public ResultSet getClientes () {
		try {
			return stCheck.executeQuery();
		}catch(Exception e) {
			PrintErrMss("Error al acceder a Clientes");
			System.out.println(e.getMessage());
			return null;
		}
	}
	
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
	
	public void InsertClientes(ClientesTable Cliente) {
		try{
			stInsert.setString(1, Cliente.getNOMBRE());
			stInsert.setString(2, Cliente.getEMAIL());
			stInsert.setString(3, Cliente.getTELEFONO());
			stInsert.setString(4, Cliente.getUBICACION());
			stInsert.executeUpdate();
		}catch(SQLException e) {
			PrintErrMss("Error al ingresar cliente");
			System.out.println(e.getMessage());
		}
	}
	
}//end class
