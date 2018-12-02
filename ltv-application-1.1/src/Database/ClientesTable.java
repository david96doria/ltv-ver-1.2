package Database;

public class ClientesTable {

	private String NOMBRE;
	private String EMAIL;
	private String TELEFONO;
	private String UBICACION;
	
	public ClientesTable() {
		
	}
	
	public ClientesTable(String Nombre, String Email, String Telefono, String Ubicacion) {
		this.NOMBRE=Nombre;
		this.EMAIL=Email;
		this.TELEFONO=Telefono;
		this.UBICACION=Ubicacion;
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
	public String getTELEFONO() {
		return TELEFONO;
	}
	public void setTELEFONO(String tELEFONO) {
		TELEFONO = tELEFONO;
	}
	public String getUBICACION() {
		return UBICACION;
	}
	public void setUBICACION(String uBICACION) {
		UBICACION = uBICACION;
	}
	
	
}
