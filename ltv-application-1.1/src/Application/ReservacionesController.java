package Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ReservacionesController {

	///////////////////////////////////////////////////////////////////////////
	// Attributes /////////////////////////////////////////////////////////////
	public MenuItem   btnNuevo;
	public MenuItem   btnGuardar;
	public MenuItem   btnCerrarSesion;
	public MenuItem   btnSalir;
	public MenuItem   btnDeshacer;
	public MenuItem   btnPegar;
	public MenuItem   btnCopiar;
	public MenuItem   btnConsultaClientes;
	public MenuItem   btnAcercaDe;
	public TextArea   txtComentarios;
	public DatePicker txtFecha;
	public TextField  txtNombre;
	public TextField  txtCorreo;	
	public TextField  txtTelefono;
	public TextField  txtDestino;

	public ListView<String> lstSolicitudes;
	public ObservableList<String> solicitudes = FXCollections.observableArrayList();
	public Main objMain = new Main();

	///////////////////////////////////////////////////////////////////////////
	// Methods ////////////////////////////////////////////////////////////////
	public void callNuevo() {
//		
	}
	
	public void callGuardar() {
		
	}
}
