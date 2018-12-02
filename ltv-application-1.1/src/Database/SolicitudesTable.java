package Database;

public class SolicitudesTable {

	private String FECHA;
	private String CLIENTE;
	private String SOLICITUD;
	private String ESTATUS;
	private String USUARIO;
	
	public SolicitudesTable() {
	
	}
	
	public SolicitudesTable(String Fecha, String Cliente, String Solicitud, String Estatus, String Usuario) {
		this.FECHA=Fecha;
		this.CLIENTE=Cliente;
		this.SOLICITUD=Solicitud;
		this.ESTATUS=Estatus;
		this.USUARIO=Usuario;
	}
	
	
	public String getFECHA() {
		return FECHA;
	}
	public void setFECHA(String fECHA) {
		FECHA = fECHA;
	}
	public String getCLIENTE() {
		return CLIENTE;
	}
	public void setCLIENTE(String cLIENTE) {
		CLIENTE= cLIENTE;
	}	
	public String getSOLICITUD() {
		return SOLICITUD;
	}
	public void setSOLICITUD(String sOLICITUD) {
		SOLICITUD = sOLICITUD;
	}
	public String getESTATUS() {
		return ESTATUS;
	}
	public void setESTATUS(String eSTATUS) {
		ESTATUS = eSTATUS;
	}
	public String getUSUARIO() {
		return USUARIO;
	}
	public void setUSUARIO(String uSUARIO) {
		USUARIO = uSUARIO;
	}
	
	
}
