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
		stInsert = Conexion.prepareStatement("INSERT INTO solicitudestable VALUES (?,?,?,?,?,?,?,?)");
	    stCheck = Conexion.prepareStatement("SELECT * FROM solicitudestable WHERE status=?");
	    stDelete = Conexion.prepareStatement("DELETE FROM solicitudestable WHERE nombre=?");
	    stUpdate = Conexion.prepareStatement("UPDATE solicitudestable SET agente=?, estatus=? WHERE nombre=?");
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
	
	public void PrepareVentas() throws Exception {
		Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/ltv-database","root","");
		stInsert = Conexion.prepareStatement("INSERT INTO ventastable VALUES (?,?,?,?,?,?,?,?,?,?,?)");
	    stCheck = Conexion.prepareStatement("SELECT * FROM ventastable WHERE expediente=?");
	    //stDelete = Conexion.prepareStatement("DELETE FROM ventastable WHERE nombre=?");
	    stUpdate = Conexion.prepareStatement("UPDATE clientestable SET PROGRAMA=?, PROVEEDORES=?,TOTAL VENTA=?, DEPOSITO=?, FECHA LIMITE=?, FECHA VIAJE=?, EXPEDIENTE=?, TOTAL A PAGAR=?, FECHA=?, VENDEDOR=? WHERE CLIENTE=?");

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
	
public ResultSet CheckSolicitudes(String Estatus) {
		
		try {
			
			stCheck.setString(1,Estatus);
			ResultSet miResultset = stCheck.executeQuery();
			return miResultset;
			
		}catch(SQLException ex) {System.out.println("Error al consultar solicitud");
	         System.out.println(ex.getMessage());
	         ex.printStackTrace(); return null;}
		
	}
	
	
	public void UpdateSolicitudes(String Cliente) {
		try {
			SolicitudesTable Solicitud = new SolicitudesTable();
			stUpdate.setString(3, Cliente);
			stUpdate.setString(1, Solicitud.getAGENTE());
			stUpdate.setString(2, Solicitud.getSTATUS());
			stUpdate.executeUpdate();
		}catch(SQLException ex) {System.out.println("Error al actualizar solicitud");
        System.out.println(ex.getMessage());}
		
	}
	
	public void DeleteSolicitudes(String Cliente) {
		try {
			stDelete.setString(1, Cliente);
			stDelete.executeUpdate();
			
		}catch(SQLException ex) {System.out.println("Error al eliminar solicitud");
        System.out.println(ex.getMessage());}
	}
	
	public void InsertSolicitudes() {
		try {
			SolicitudesTable Solicitud = new SolicitudesTable();
			stInsert.setString(1, Solicitud.getFECHA());
			stInsert.setString(2, Solicitud.getNOMBRE());
			stInsert.setString(3, Solicitud.getCORREO());
			stInsert.setString(4, Solicitud.getTELEFONO());
			stInsert.setString(5, Solicitud.getDESTINO());
			stInsert.setString(6, Solicitud.getCOMENTARIOS());
			stInsert.setString(7, Solicitud.getAGENTE());
			stInsert.setString(8, Solicitud.getSTATUS());
			stInsert.executeUpdate();
		}catch(SQLException ex) {System.out.println("Error al ingresar solicitud");
        System.out.println(ex.getMessage());}
	}
	
	
	//////////// USUARIOS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public ResultSet CheckUsuarios(String Nombre) {
		
	try {
		
		stCheck.setString(1, Nombre);
		ResultSet miResultset=stCheck.executeQuery();
		
		return miResultset;
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
	
	public ResultSet CheckAvailableUsuarios(boolean Disponible) {
		
		try {
			stCheck=Conexion.prepareStatement("SELECT * FROM usuariostable WHERE disponible=?");
			stCheck.setBoolean(1, Disponible);
			ResultSet miResultset=stCheck.executeQuery();
			
			return miResultset;
		}catch(SQLException ex) {System.out.println("Error al consultar usuario");
	    System.out.println(ex.getMessage()); return null;}
		}
	
	public void UpdateUsuarios(String Nombre) {
		try {
			UsuariosTable Usuario = new UsuariosTable();
			stUpdate.setString(3, Nombre);
			stUpdate.setString(1, Usuario.getUSUARIO());
			stUpdate.setString(2, Usuario.getCLAVE());
			stUpdate.executeUpdate();
		}catch(SQLException ex) {System.out.println("Error al actualizar usuario");
        System.out.println(ex.getMessage());}
		
	}
	
	public void DeleteUsuarios(String Nombre) {
		try {
			stDelete.setString(1, Nombre);
			stDelete.executeUpdate();
			
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
		stInsert.executeUpdate();
	}catch(SQLException ex) {System.out.println("Error al ingresar usuario");
    System.out.println(ex.getMessage());}
	}
	
	
	//////////// CLIENTES \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public ResultSet CheckClientes(String Nombre) {
		
		try {
			
			stCheck.setString(1, Nombre);
			ResultSet miResultset=stCheck.executeQuery();
			
			return miResultset;
		}catch(SQLException ex) {System.out.println("Error al consultar cliente");
	    System.out.println(ex.getMessage()); return null;}
			
	}
	
	public void UpdateClientes(String Nombre) {
		try {
			ClientesTable Cliente = new ClientesTable();
			stUpdate.setString(3, Nombre);
			stUpdate.setString(1, Cliente.getTELEFONO());
			stUpdate.setString(2, Cliente.getEMAIL());
			stUpdate.executeUpdate();
		}catch(SQLException ex) {System.out.println("Error al actualizar cliente");
        System.out.println(ex.getMessage());}
	}
	
	public void DeleteClientes(String Nombre) {
		try {
			stDelete.setString(1, Nombre);
			stDelete.executeUpdate();
			
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
			stInsert.executeUpdate();
		}catch(SQLException ex) {System.out.println("Error al ingresar cliente");
	    System.out.println(ex.getMessage());}
	}
	
	//////////// VENTAS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	public ResultSet CheckVentas(String Expediente) {
		
		try {
			stCheck.setString(1, Expediente);
			ResultSet miResultset=stCheck.executeQuery();
				
		return miResultset;	
		
		}catch(SQLException ex) {System.out.println("Error al consultar venta");
	    System.out.println(ex.getMessage()); return null;}
		}
	
	public void UpdateVenta(String Cliente) {
		try {
			VentasTable Venta = new VentasTable();
			stUpdate.setString(11, Cliente);
			
			stUpdate.setString(1, Venta.getPROGRAMA());
			stUpdate.setString(2, Venta.getPROVEEDORES());
			stUpdate.setDouble(3, Venta.getTOTAL_VENTA());
			stUpdate.setDouble(4, Venta.getDEPOSITO());
			stUpdate.setString(5, Venta.getFECHA_LIMITE());
			stUpdate.setString(6, Venta.getFECHA_VIAJE());
			stUpdate.setString(7, Venta.getEXPEDIENTE());
			stUpdate.setDouble(8, Venta.getTOTAL_PAGAR());
			stUpdate.setString(9, Venta.getFECHA());
			stUpdate.setString(10, Venta.getVENDEDOR());
			stUpdate.executeUpdate();
		}catch(SQLException ex) {System.out.println("Error al modificar venta");
	    System.out.println(ex.getMessage());}
		}
	
	public void InsertVenta() {
		try {
			VentasTable Venta = new VentasTable();
			
			stInsert.setString(1, Venta.getCLIENTE());
			stInsert.setString(2, Venta.getPROGRAMA());
			stInsert.setString(3, Venta.getPROVEEDORES());
			stInsert.setDouble(4, Venta.getTOTAL_VENTA());
			stInsert.setDouble(5, Venta.getDEPOSITO());
			stInsert.setString(6, Venta.getFECHA_LIMITE());
			stInsert.setString(7, Venta.getFECHA_VIAJE());
			stInsert.setString(8, Venta.getEXPEDIENTE());
			stInsert.setDouble(9, Venta.getTOTAL_PAGAR());
			stInsert.setString(10, Venta.getFECHA());
			stInsert.setString(11, Venta.getVENDEDOR());
			stInsert.executeUpdate();
			
			
		}catch(SQLException ex) {System.out.println("Error al ingresar venta");
	    System.out.println(ex.getMessage());}
		
	}
}//end class
