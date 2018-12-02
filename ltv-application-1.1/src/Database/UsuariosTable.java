package Database;

public class UsuariosTable {

	private String NOMBRE;
	private String EMAIL;
	private String CARGO;
	private String USUARIO;
	private String CLAVE;
	private String DISPONIBLE;
	
	public UsuariosTable () {
		
	}
	
	public UsuariosTable (String Nombre, String Email, String Cargo, String Usuario, String Clave, String Disponible) {
		this.NOMBRE=Nombre;
		this.EMAIL=Email;
		this.CARGO=Cargo;
		this.USUARIO=Usuario;
		this.CLAVE=Clave;
		this.DISPONIBLE=Disponible;
	}
	
	public String getNOMBRE() {
		return NOMBRE;
	}
	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getCARGO() {
		return CARGO;
	}
	public void setCARGO(String cARGO) {
		CARGO = cARGO;
	}
	public String getUSUARIO() {
		return USUARIO;
	}
	public void setUSUARIO(String uSUARIO) {
		USUARIO = uSUARIO;
	}
	public String getCLAVE() {
		return CLAVE;
	}
	public void setCLAVE(String cLAVE) {
		CLAVE = cLAVE;
	}
	public String isDISPONIBLE() {
		return DISPONIBLE;
	}
	public void setDISPONIBLE(String dISPONIBLE) {
		DISPONIBLE = dISPONIBLE;
	}
	
	
}
