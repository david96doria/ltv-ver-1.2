package Application;

import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DataBase;
import Database.SolicitudesTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Label;

public class AdministracionController {

	public ListView<String> listSolicitudes = new ListView<String>();
	public ObservableList<String> SolicitudesItems = FXCollections.observableArrayList();
	public ChoiceBox<String> choiceAgentes = new ChoiceBox<String>();
	public ObservableList<String> choiceItems = FXCollections.observableArrayList();
	public Label lblFecha; public Label lblCliente; public Label lblSolicitud;
	public ChoiceBox<String> chbUsuarios;
	public Button btnRefrescarEntrantes;
	
	DataBase database = new DataBase();
	SolicitudesTable Solicitud = new SolicitudesTable();
	Main objMain = new Main();
	ResultSet miResultset; TitledPane tltpEntrantes;

	public void callAsignarSol() {
		try {
			String agente = chbUsuarios.getSelectionModel().getSelectedItem();
			database.PrepareSolicitudes();
			ResultSet ret = database.FillSolicitud(this.miResultset.getString("FECHA"),
												   this.miResultset.getString("NOMBRE"),
												   this.miResultset.getString("DESTINO"));
			
			database.CloseDataBase();
		}catch(SQLException e) {
			database.PrintErrMss("Error al buscar solicitud.");
			System.out.println(e.getMessage()); 
		}
	}
	
	public void callCerrarSesion() {
		database.PrepareUsuarios();
		String user = database.findSesionIniciada();
		database.UpdateUsuarioINICIO(user, 0);
		database.CloseDataBase();
		btnRefrescarEntrantes.getScene().getWindow().hide();
		objMain.changeScene("InicioScene.fxml", 450, 300);
	}

	public void callSelectEntrante() {
		String [] args = listSolicitudes.getSelectionModel().getSelectedItem().split(" > ");
		String Fecha   = args[0],
			   Nombre  = args[1],
			   Destino = args[2];
		database.PrepareSolicitudes();
		ResultSet ret = database.FillSolicitud(Fecha, Nombre, Destino);
		try {
			lblFecha.setText(ret.getString("FECHA"));
			lblCliente.setText(ret.getString("NOMBRE"));
			lblSolicitud.setText(ret.getString("COMENTARIOS"));
		}catch(Exception e) {
			database.PrintErrMss("Error al buscar solicitud.");
			System.out.println(e.getMessage()); 
		}
		this.miResultset = ret;//save solicitud

		// fill choice-box with available users
		chbUsuarios.getItems().clear();
		database.PrepareUsuarios();
		ResultSet ret1 = database.CheckAvailableUsuarios(true);
		try {
			while(ret1.next()) {
				choiceItems.add(ret1.getString("NOMBRE"));
			}
		}catch(SQLException e) {
			database.PrintErrMss("Error al buscar solicitud.");
			System.out.println(e.getMessage());
		}
		database.CloseDataBase();
		chbUsuarios.setItems(choiceItems);
	}
	
	public void MostrarSolicitudesEntrantes() {
		listSolicitudes.getItems().clear();
		choiceAgentes.getItems().clear();
		
		try {
			database.PrepareSolicitudes();
			miResultset = database.CheckAllSolicitudes("P");
			while(miResultset.next()) {
				SolicitudesItems.add(miResultset.getString("fecha") + " > "+ 
									 miResultset.getString("nombre")+ " > "+
									 miResultset.getString("destino"));
			} 
			listSolicitudes.setItems(SolicitudesItems);
		}catch (Exception e) {
			System.out.println(e.getMessage()); 
			e.printStackTrace();
			System.out.println("Error al mostar solicitudes");
		} 

		try {
			database.PrepareUsuarios();
			miResultset = database.CheckAvailableUsuarios(true);
			while(miResultset.next()) {
				choiceItems.add(miResultset.getString("nombre"));
			}
			choiceAgentes.setItems(choiceItems);
		}catch (Exception e) {
			System.out.println(e.getMessage()); 
			e.printStackTrace();
			System.out.println("Error al mostar agentes");
		}
	}

	public void MostrarSolicitudesAsignadas() {
		
	}
	
	public void MostrarSolicitudesSeguimiento() {
		
	}
	
	public void btnAcercaDe() {
		
	}
}//end class
