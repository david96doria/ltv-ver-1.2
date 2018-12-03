package Database;

public class VentasTable {
	
	private String CLIENTE;
	private String PROGRAMA;
	private String PROVEEDORES;
	private double TOTAL_VENTA;
	private double DEPOSITO;
	private String FECHA_LIMITE;
	private String FECHA_VIAJE;
	private String EXPEDIENTE;
	private double TOTAL_PAGAR;
	private String FECHA;
	private String VENDEDOR;
	
	public VentasTable() {
		
	}
	
	public VentasTable(String cLIENTE, String pROGRAMA, String pROVEEDORES, double tOTAL_VENTA, double dEPOSITO,
			String fECHA_LIMITE, String fECHA_VIAJE, String eXPEDIENTE, double tOTAL_PAGAR, String fECHA,
			String vENDEDOR) {
		super();
		CLIENTE = cLIENTE;
		PROGRAMA = pROGRAMA;
		PROVEEDORES = pROVEEDORES;
		TOTAL_VENTA = tOTAL_VENTA;
		DEPOSITO = dEPOSITO;
		FECHA_LIMITE = fECHA_LIMITE;
		FECHA_VIAJE = fECHA_VIAJE;
		EXPEDIENTE = eXPEDIENTE;
		TOTAL_PAGAR = tOTAL_PAGAR;
		FECHA = fECHA;
		VENDEDOR = vENDEDOR;
	}



	public String getCLIENTE() {
		return CLIENTE;
	}
	public void setCLIENTE(String cLIENTE) {
		CLIENTE = cLIENTE;
	}
	public String getPROGRAMA() {
		return PROGRAMA;
	}
	public void setPROGRAMA(String pROGRAMA) {
		PROGRAMA = pROGRAMA;
	}
	public String getPROVEEDORES() {
		return PROVEEDORES;
	}
	public void setPROVEEDORES(String pROVEEDORES) {
		PROVEEDORES = pROVEEDORES;
	}
	public double getTOTAL_VENTA() {
		return TOTAL_VENTA;
	}
	public void setTOTAL_VENTA(double tOTAL_VENTA) {
		TOTAL_VENTA = tOTAL_VENTA;
	}
	public double getDEPOSITO() {
		return DEPOSITO;
	}
	public void setDEPOSITO(double dEPOSITO) {
		DEPOSITO = dEPOSITO;
	}
	public String getFECHA_LIMITE() {
		return FECHA_LIMITE;
	}
	public void setFECHA_LIMITE(String fECHA_LIMITE) {
		FECHA_LIMITE = fECHA_LIMITE;
	}
	public String getFECHA_VIAJE() {
		return FECHA_VIAJE;
	}
	public void setFECHA_VIAJE(String fECHA_VIAJE) {
		FECHA_VIAJE = fECHA_VIAJE;
	}
	public String getEXPEDIENTE() {
		return EXPEDIENTE;
	}
	public void setEXPEDIENTE(String eXPEDIENTE) {
		EXPEDIENTE = eXPEDIENTE;
	}
	public double getTOTAL_PAGAR() {
		return TOTAL_PAGAR;
	}
	public void setTOTAL_PAGAR(double tOTAL_PAGAR) {
		TOTAL_PAGAR = tOTAL_PAGAR;
	}
	public String getFECHA() {
		return FECHA;
	}
	public void setFECHA(String fECHA) {
		FECHA = fECHA;
	}
	public String getVENDEDOR() {
		return VENDEDOR;
	}
	public void setVENDEDOR(String vENDEDOR) {
		VENDEDOR = vENDEDOR;
	}
	
	

}
