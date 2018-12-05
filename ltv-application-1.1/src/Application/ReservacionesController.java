package Application;

import java.sql.ResultSet;

import Database.ClientesTable;
import Database.DataBase;
import Database.SolicitudesTable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReservacionesController {

	///////////////////////////////////////////////////////////////////////////
	// Attributes /////////////////////////////////////////////////////////////
	private String	USUARIO;
	Main objMain = new Main();
	DataBase objDatabase = new DataBase();
	SolicitudesTable objSolicitud = new SolicitudesTable();

	public MenuItem btnNuevo,
					btnGuardar,
					btnCerrarSesion,
					btnSalir,
					btnDeshacer,
					btnPegar,
					btnCopiar,
					btnConsultaClientes,
					btnAcercaDe;
	public TitledPane titAsignadas,
					  titCaptura;

	///////////////////////////////////////////////////////////////////////////
	// Nueva Solicitud ////////////////////////////////////////////////////////
	public TextArea		txtComentarios;
	public DatePicker	txtFecha;
	public TextField	txtNombre,
						txtCorreo,	
						txtTelefono,
						txtDestino;
	public Button		btnRegistrar;

	///////////////////////////////////////////////////////////////////////////
	// Nueva Captura //////////////////////////////////////////////////////////
	public Button btnRefrescar;	
	public ListView<String> lstSolicitudes = new ListView<String>();
	public ObservableList<String> itemsSolicitudes = FXCollections.observableArrayList();

	public TextField	Costeo_TelMovil,
						Costeo_TelFijo,
						Costeo_SolicitanteNombre,
						Costeo_origen,
						Costeo_menores,
						Costeo_Fecha,
						Costeo_Ejecutivo,
						Costeo_dias,
						Costeo_destino,
						Costeo_Correo,
						Costeo_Ciudad,
						Costeo_Agencia_Empresa,
						Costeo_adultos;
	public ComboBox<String> Costeo_aerolinea,
							Costeo_estrellas,
							Costeo_Programa,
							Costeo_tipoVuelo;
	public DatePicker	Costeo_llegada, 
						Costeo_salida;

	///////////////////////////////////////////////////////////////////////////
	// Clientes ///////////////////////////////////////////////////////////////
	public Button 		btnRefrescarClientes,
						btnAgregarCliente;
	public TextField	txtClienteNombre,
						txtClienteEmail,
						txtClienteTel,
						txtClienteUbicacion;

	///////////////////////////////////////////////////////////////////////////
	// Tabla de clientes //////////////////////////////////////////////////////
	public TableView<ClientesTable>	tableClientes;

	public TableColumn<ClientesTable, String> tabNombre = new TableColumn<>("NOMBRE");
	public TableColumn<ClientesTable, String> tabEmail = new TableColumn<>("EMAIL");
	public TableColumn<ClientesTable, String> tabTel = new TableColumn<>("TELEFONO");
	public TableColumn<ClientesTable, String> tabUbi = new TableColumn<>("UBICACION");
	public ObservableList<ClientesTable> itemsClientes = FXCollections.observableArrayList();


	///////////////////////////////////////////////////////////////////////////
	// Methods ////////////////////////////////////////////////////////////////
	public void callNuevo() {
		txtNombre.setText("");
		txtCorreo.setText("");
		txtTelefono.setText("");
		txtDestino.setText("");
		txtComentarios.setText("");
		txtClienteNombre.setText("");
		txtClienteEmail.setText("");
		txtClienteTel.setText("");
		txtClienteUbicacion.setText("");
	}

	public void callGuardar() {
	}

	public void callCerrarSesion () {
		objDatabase.PrepareUsuarios();
		String user = objDatabase.findSesionIniciada();
		objDatabase.UpdateUsuarioINICIO(user, 0);
		objDatabase.CloseDataBase();
		btnRefrescar.getScene().getWindow().hide();
		objMain.changeScene("InicioScene.fxml", 450, 300);
	}

	public void callRegistrar() {
		objDatabase.PrepareSolicitudes();
		objDatabase.InsertSolicitud(new SolicitudesTable(txtFecha.getValue().toString(), 
														 txtNombre.getText(), 
														 txtCorreo.getText(), 
														 txtTelefono.getText(), 
														 txtDestino.getText(), 
														 txtComentarios.getText(), 
														 "Sin Asignar",
														 'P'));
		objDatabase.CloseDataBase();
		objDatabase.PrintInfMss("Registro realizado correctamente.");
	}

	public void callRefrescar() {
		objDatabase.PrepareUsuarios();
		this.USUARIO = objDatabase.findSesionIniciada();
		objDatabase.CloseDataBase();

		lstSolicitudes.getItems().clear();
		objDatabase.PrepareSolicitudes();
		try {
				ResultSet ret = objDatabase.FindSolicitud(this.USUARIO);
			while(ret.next()) {
				itemsSolicitudes.add(ret.getString("FECHA") + " > " +
									 ret.getString("NOMBRE")+ " > " +
									 ret.getString("DESTINO"));
			}
			lstSolicitudes.setItems(itemsSolicitudes);
			objDatabase.CloseDataBase();
		}catch(Exception e) {
			objDatabase.PrintErrMss("Error al buscar una solicitud.");
			System.out.println(e.getMessage());
		}
	}

	public void callListSelect () {
		titAsignadas.setExpanded(false);
		titCaptura.setExpanded(true);
		String [] args = lstSolicitudes.getSelectionModel().getSelectedItem().split(" > ");
		String Fecha   = args[0],
			   Nombre  = args[1],
			   Destino = args[2];

		objDatabase.PrepareSolicitudes();
		try {
			ResultSet ret = objDatabase.FillSolicitud(Fecha, Nombre, Destino);
			Costeo_Fecha.setText(Fecha);
			Costeo_SolicitanteNombre.setText(ret.getString("NOMBRE"));
			Costeo_Correo.setText(ret.getString("CORREO"));
			Costeo_TelMovil.setText(ret.getString("TELEFONO"));
			Costeo_destino.setText(ret.getString("DESTINO"));
			
			objDatabase.PrepareUsuarios();
			Costeo_Ejecutivo.setText(objDatabase.findUsuario(this.USUARIO).getNOMBRE());
			objDatabase.CloseDataBase();
		}catch(Exception e) {
			objDatabase.PrintErrMss("Error al buscar una solicitud.");
			System.out.println(e.getMessage());
		}
	}

	public void callRefrescarClientes() {
//		objDatabase.PrepareClientes();
//		try {
//			ResultSet ret = objDatabase.getClientes();
//			tabNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
//			tabEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
//			tabTel.setCellValueFactory(new PropertyValueFactory<>("telefono"));
//			tabUbi.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));
//
//			tableClientes = new TableView<>();
//			itemsClientes.add(new ClientesTable("colombia", "es", "puto", "	u"));
//			itemsClientes.add(new ClientesTable("pero", "es", "una ", "verga"));
//			tableClientes.setItems(itemsClientes);
//			tableClientes.getColumns().addAll(tabNombre, tabEmail, tabTel, tabUbi);
//			
//			while(ret.next()) {
//				System.out.println(ret.getString("NOMBRE"));
////				tableClientes.getColumns().addAll(new TableColumn(ret.getString("NOMBRE")),
////												  new TableColumn(ret.getString("EMAIL")),
////												  new TableColumn(ret.getString("TELEFONO")),
////												  new TableColumn(ret.getString("UBICACION")));
//			}
////			tableClientes.setItems(itemsSolicitudes);
//
//			objDatabase.CloseDataBase();
//		} catch(Exception e) {
//			objDatabase.PrintErrMss("Error al buscar clientes");
//			System.out.println(e.getMessage());
//		}
	}

	public void callAgregarCliente(){
		objDatabase.PrepareClientes();
		objDatabase.InsertClientes(new ClientesTable(txtClienteNombre.getText(),
													 txtClienteEmail.getText(),
													 txtClienteTel.getText(),
													 txtClienteUbicacion.getText()));
		objDatabase.CloseDataBase();
		objDatabase.PrintInfMss("Cliente agregado.");
	}
}
