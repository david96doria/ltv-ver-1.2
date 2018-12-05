package Database;

public class SolicitudesTable {

	private String FECHA;
	private String NOMBRE;
	private String CORREO;
	private String TELEFONO;
	private String DESTINO;
	private String COMENTARIOS;
	private String AGENTE;
	private String   STATUS;

	public String getFECHA() {
		return FECHA;
	}

	public void setFECHA(String fECHA) {
		FECHA = fECHA;
	}

	public String getNOMBRE() {
		return NOMBRE;
	}

	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}

	public String getCORREO() {
		return CORREO;
	}

	public void setCORREO(String cORREO) {
		CORREO = cORREO;
	}

	public String getTELEFONO() {
		return TELEFONO;
	}

	public void setTELEFONO(String tELEFONO) {
		TELEFONO = tELEFONO;
	}

	public String getDESTINO() {
		return DESTINO;
	}

	public void setDESTINO(String dESTINO) {
		DESTINO = dESTINO;
	}

	public String getCOMENTARIOS() {
		return COMENTARIOS;
	}

	public void setCOMENTARIOS(String cOMENTARIOS) {
		COMENTARIOS = cOMENTARIOS;
	}

	public String getAGENTE() {
		return AGENTE;
	}

	public void setAGENTE(String aGENTE) {
		AGENTE = aGENTE;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	public SolicitudesTable() {
	
	}

	public SolicitudesTable(String fECHA, String nOMBRE, String cORREO, String tELEFONO, String dESTINO, String cOMENTARIOS, String aGENTE, String sTATUS) {
		super();
		FECHA = fECHA;
		NOMBRE = nOMBRE;
		CORREO = cORREO;
		TELEFONO = tELEFONO;
		DESTINO = dESTINO;
		COMENTARIOS = cOMENTARIOS;
		AGENTE = aGENTE;
		STATUS = sTATUS;
	}
}
